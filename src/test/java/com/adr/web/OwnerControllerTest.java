package com.adr.web;

import com.adr.model.Owner;
import com.adr.service.ClinicService;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@SpringJUnitWebConfig(locations = {"classpath:spring/mvc-text-config.xml", "classpath:spring/mvc-core-config.xml"})
class OwnerControllerTest {

    @Autowired
    OwnerController ownerController;

    @Autowired
    ClinicService clinicService;

    @Captor
    ArgumentCaptor<String> stringArgumentCaptor;

    private static final String VIEWS_OWNER_CREATE_OR_UPDATE_FORM = "owners/createOrUpdateOwnerForm";

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(ownerController).build();
    }

    @AfterEach
    void tearDown() {
        reset(clinicService);
    }

    @Test
    void initCreationFormTest() throws Exception {
        mockMvc.perform(get("/owners/new"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("owner"))
                .andExpect(view().name(VIEWS_OWNER_CREATE_OR_UPDATE_FORM));
    }

    @Test
    void testFindByNameNotFound() throws Exception {
        mockMvc.perform(get("/owners")
                .param("lastName", "Don't find me"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/findOwners"));

    }

    @Test
    void testOneOwnerEmptyLastName() throws Exception {

        Owner owner = new Owner();
        owner.setId(1);

        when(clinicService.findOwnerByLastName("")).thenReturn(Lists.newArrayList(owner));

        mockMvc.perform(get("/owners"))
                .andExpect(redirectedUrl("/owners/1"));

        then(clinicService).should().findOwnerByLastName(stringArgumentCaptor.capture());
        assertThat(stringArgumentCaptor.getValue()).isEqualToIgnoringCase("");
    }

    @Test
    void testMultipleOwners() throws Exception {

        when(clinicService.findOwnerByLastName(anyString())).thenReturn(Lists.newArrayList(new Owner(), new Owner()));

        mockMvc.perform(get("/owners"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("selections"))
                .andExpect(view().name("owners/ownersList"));
    }

    @Test
    void testNewOwnerPostValid() throws Exception {
        mockMvc.perform(post("/owners/new")
                    .param("firstName", "Adrian")
                    .param("lastName", "Briano")
                    .param("telephone", "123456")
                    .param("Address", "Mexico")
                    .param("City", "Mexico"))
                .andExpect(status().is3xxRedirection());
    }
}