package com.adr.controllers;

import com.adr.fauxspring.BindingResult;
import com.adr.fauxspring.Model;
import com.adr.model.Owner;
import com.adr.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    @Mock
    OwnerService ownerService;

    @Mock
    BindingResult bindingResult;

    @InjectMocks
    OwnerController controller;

    @Captor
    ArgumentCaptor<String> stringArgumentCaptor;

    @BeforeEach
    void setUp() {
        given(ownerService.findAllByLastNameLike(stringArgumentCaptor.capture())).willAnswer(invocationOnMock -> {

            List<Owner> ownerList = new ArrayList<>();

            String name = invocationOnMock.getArgument(0);

            if (name.equals("%DontFindMe%")) {
                return ownerList;
            } else if (name.equals("%Briano%")) {
                ownerList.add(new Owner(5L, "Adrian", "Briano"));
                return ownerList;
            } else if (name.equals("%FindMe%")) {
                ownerList.add(new Owner(1L, "Adrian", "FindMe"));
                ownerList.add(new Owner(2L, "Adrian", "FindMe"));
                return ownerList;
            }
            throw new RuntimeException("Invalid argument");
        });
    }

    @Test
    void processFindFormWildcardNotFound() {
        //given
        Owner owner = new Owner(5L, "Adrian", "DontFindMe");

        //when
        String viewName = controller.processFindForm(owner, bindingResult, null);

        //then
        assertThat(stringArgumentCaptor.getValue()).isEqualToIgnoringCase("%DontFindMe%");
        assertThat(viewName).isEqualToIgnoringCase("owners/findOwners");
    }

    @Test
    void processFindFormWildcarOneRecord() {
        //given
        Owner owner = new Owner(5L, "Adrian", "Briano");

        //when
        String viewName = controller.processFindForm(owner, bindingResult, null);

        //then
        assertThat(stringArgumentCaptor.getValue()).isEqualToIgnoringCase("%Briano%");
        assertThat(viewName).isEqualToIgnoringCase("redirect:/owners/5");
    }

    @Test
    void processFindFormWildcardMultipleRecords() {
        //given
        Owner owner = new Owner(5L, "Adrian", "FindMe");

        //when
        String viewName = controller.processFindForm(owner, bindingResult, Mockito.mock(Model.class));

        //then
        assertThat(stringArgumentCaptor.getValue()).isEqualToIgnoringCase("%FindMe%");
        assertThat(viewName).isEqualToIgnoringCase("owners/ownersList");
    }

    @Test
    void processCreationFormWithErrors() {
        //when
        final String ERRORS_VIEW = "owners/createOrUpdateOwnerForm";

        Owner owner = new Owner(1L, "Adrian", "Briano");
        given(bindingResult.hasErrors()).willReturn(true);

        //given
        String viewName = controller.processCreationForm(owner, bindingResult);

        //then
        assertThat(viewName).isEqualToIgnoringCase(ERRORS_VIEW);
    }

    @Test
    void processCreationFormWithNoErrors() {

        //when
        final String VIEW_5 = "redirect:/owners/5";

        Owner owner = new Owner(5L, "Adrian", "Briano");
        given(bindingResult.hasErrors()).willReturn(false);
        given(ownerService.save(any(Owner.class))).willReturn(owner);

        //given
        String viewName = controller.processCreationForm(owner, bindingResult);

        //then
        assertThat(viewName).isEqualToIgnoringCase(VIEW_5);
    }


}