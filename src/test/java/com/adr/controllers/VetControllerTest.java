package com.adr.controllers;


import com.adr.ControllerTests;
import com.adr.fauxspring.Model;
import com.adr.fauxspring.ModelMapImpl;
import com.adr.model.Vet;
import com.adr.services.SpecialtyService;
import com.adr.services.VetService;
import com.adr.services.map.SpecialityMapService;
import com.adr.services.map.VetMapService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class VetControllerTest implements ControllerTests {

    VetService vetService;
    SpecialtyService specialtyService;

    VetController vetController;
    Vet vet;

    @BeforeEach
    void setUp() {
        specialtyService = new SpecialityMapService();
        vetService = new VetMapService(specialtyService);
        vetController = new VetController(vetService);

        Vet vet1 = new Vet(1L, "Adrian", "Briano", null);
        Vet vet2 = new Vet(2L, "Andres", "Briano", null);

        vetService.save(vet1);
        vetService.save(vet2);
    }

    @Test
    void listVets() {

        Model model = new ModelMapImpl();

        String view = vetController.listVets(model);

        assertThat("vets/index").isEqualTo(view);

        Set modelAttribute = (Set) ((ModelMapImpl)model).getMap().get("vets");

        assertThat(modelAttribute.size()).isEqualTo(2);

    }
}