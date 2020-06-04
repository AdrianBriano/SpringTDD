package com.adr.controllers;

import com.adr.fauxspring.BindingResult;
import com.adr.model.Owner;
import com.adr.services.OwnerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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