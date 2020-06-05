package com.adr.service;

import com.adr.model.PetType;
import com.adr.repository.PetRepository;
import com.adr.repository.VisitRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class ClinicServiceImplTest {

    @Mock
    PetRepository petRepository;

    @InjectMocks
    ClinicServiceImpl clinicService;

    @Test
    void findPetTypesTest() {
        //given
        List<PetType> petTypesList = new ArrayList<>();

        given(petRepository.findPetTypes()).willReturn(petTypesList);

        //when
        Collection<PetType> servicePetTypes = clinicService.findPetTypes();

        //then
        assertThat(servicePetTypes).isNotNull();

        then(petRepository).should(times(1)).findPetTypes();
        then(petRepository).shouldHaveNoMoreInteractions();
    }
}