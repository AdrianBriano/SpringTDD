package com.adr.web;

import com.adr.model.Vet;
import com.adr.model.Vets;
import com.adr.service.ClinicService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class VetControllerTest {

    private static final String VETS_VET_LIST = "vets/vetList";

    @Mock
    ClinicService clinicService;
    @Mock
    Map<String, Object> modelMap;

    @InjectMocks
    VetController vetController;

    List<Vet> vets = new ArrayList<>();

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        vets.add(new Vet());

        given(clinicService.findVets()).willReturn(vets);

        mockMvc = MockMvcBuilders.standaloneSetup(vetController).build();
    }

    @Test
    void testControllerShowVetList() throws Exception {
        mockMvc.perform(get("/vets.html"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("vets"))
                .andExpect(view().name("vets/vetList"));
    }

    @Test
    void showVetListTest() {
        //when
        String view = vetController.showVetList(modelMap);

        //then
        assertThat(view).isEqualToIgnoringCase(VETS_VET_LIST);

        then(clinicService).should(times(1)).findVets();
        then(clinicService).shouldHaveNoMoreInteractions();
        then(modelMap).should().put(anyString(), any());
    }

    @Test
    void showResourcesVetListTest() {
        //when
        Vets returnedVets = vetController.showResourcesVetList();

        //then
        assertThat(returnedVets).isInstanceOf(Vets.class);
        assertThat(returnedVets.getVetList()).hasSize(1);

        then(clinicService).should(times(1)).findVets();
        then(clinicService).shouldHaveNoMoreInteractions();

    }
}