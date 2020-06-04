package com.adr.services.springdatajpa;

import com.adr.model.Speciality;
import com.adr.repositories.SpecialtyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SpecialitySDJpaServiceTest {

    @Mock
    SpecialtyRepository specialtyRepository;

    @InjectMocks
    SpecialitySDJpaService service;

    @Test
    void deleteById() {
        //given - none

        //then
        service.deleteById(1L);
        service.deleteById(1L);

        //when
        //verify(specialtyRepository).deleteById(1L);
        then(specialtyRepository).should(times(2)).deleteById(1L);
    }

    @Test
    void deleteByIdAtLeastOnce() {
        //given - none

        //then
        service.deleteById(1L);
        service.deleteById(1L);

        //when
        //verify(specialtyRepository).deleteById(1L);
        then(specialtyRepository).should(atLeastOnce()).deleteById(1L);
    }

    @Test
    void deleteByIdAtMost() {
        //then
        service.deleteById(1L);
        service.deleteById(1L);

        //when
        //verify(specialtyRepository).deleteById(1L);
        then(specialtyRepository).should(atMost(5)).deleteById(1L);
    }

    @Test
    void deleteByIdNever() {
        //then
        service.deleteById(1L);
        service.deleteById(1L);

        //when
        //verify(specialtyRepository).deleteById(1L);
        then(specialtyRepository).should(atLeastOnce()).deleteById(1L);
        then(specialtyRepository).should(never()).deleteById(5L);
    }

    @Test
    void delete() {
        //given - none

        //when
        service.delete(new Speciality());

        //then
        then(specialtyRepository).should().delete(any(Speciality.class));
    }

    @Test
    void findByIdTest() {
        //Given
        Speciality speciality = new Speciality();
        given(specialtyRepository.findById(1L)).willReturn(Optional.of(speciality));

        //When
        Speciality foundSpecialty = service.findById(1L);

        //Then
        assertThat(foundSpecialty).isNotNull();
        then(specialtyRepository).should(times(1)).findById(anyLong());
        then(specialtyRepository).shouldHaveNoMoreInteractions();

    }

    @Test
    void testDeleteByObject() {
        //given
        Speciality speciality = new Speciality();

        //when
        service.delete(speciality);

        //then
        //verify(specialtyRepository).delete(any(Speciality.class));
        then(specialtyRepository).should().delete(any(Speciality.class));
    }

    @Test
    void testDoThrow() {
        doThrow(new RuntimeException("Boom")).when(specialtyRepository).delete(any());

        assertThrows(RuntimeException.class, () -> specialtyRepository.delete(new Speciality()));
        assertThrows(RuntimeException.class, () -> specialtyRepository.delete(new Speciality()));

        verify(specialtyRepository).delete(any());
    }

    @Test
    void testFindByIdThrows() {
        given(specialtyRepository.findById(1L)).willThrow(new RuntimeException("Boom"));

        assertThrows(RuntimeException.class, () -> service.findById(anyLong()));

        then(specialtyRepository).should().findById(anyLong());
    }

    @Test
    void testDeleteBDD() {
        willThrow(new RuntimeException("Boom")).given(specialtyRepository).delete(any());

        assertThrows(RuntimeException.class, () -> specialtyRepository.delete(new Speciality()));

        then(specialtyRepository).should().delete(any(Speciality.class));

    }
}