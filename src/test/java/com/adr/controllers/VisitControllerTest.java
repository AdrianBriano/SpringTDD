package com.adr.controllers;

import com.adr.model.Pet;
import com.adr.model.Visit;
import com.adr.services.PetService;
import com.adr.services.VisitService;
import com.adr.services.map.PetMapService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class VisitControllerTest {

    @Mock
    VisitService visitService;

    @Spy
    PetMapService  petMapServiceService;

    @InjectMocks
    VisitController visitController;

    @Test
    void loadPetWithVisit() {
        //given
        Map<String, Object> model = new HashMap<>();
        Pet pet = new Pet(12L);
        Pet pet3 = new Pet(3L);

        petMapServiceService.save(pet);
        petMapServiceService.save(pet3);

        given(petMapServiceService.findById(anyLong())).willCallRealMethod();

        //when
        Visit visit = visitController.loadPetWithVisit(12L, model);

        //Then
        assertThat(visit).isNotNull();
        assertThat(visit.getPet()).isNotNull();
        assertThat(visit.getPet().getId()).isEqualTo(12L);
        verify(petMapServiceService, times(1)).findById(anyLong());
    }

    @Test
    void loadPetWithVisitStubbing() {
        //given
        Map<String, Object> model = new HashMap<>();
        Pet pet = new Pet(12L);
        Pet pet3 = new Pet(3L);

        petMapServiceService.save(pet);
        petMapServiceService.save(pet3);

        given(petMapServiceService.findById(anyLong())).willReturn(pet3);

        //when
        Visit visit = visitController.loadPetWithVisit(12L, model);

        //Then
        assertThat(visit).isNotNull();
        assertThat(visit.getPet()).isNotNull();
        assertThat(visit.getPet().getId()).isEqualTo(3L);
        verify(petMapServiceService, times(1)).findById(anyLong());
    }
}