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
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SpecialitySDJpaServiceTest {

    @Mock
    SpecialtyRepository specialtyRepository;

    @InjectMocks
    SpecialitySDJpaService service;

    @Test
    void deleteById() {
        service.deleteById(1L);

        verify(specialtyRepository).deleteById(1L);
    }

    @Test
    void deleteByIdTimes2() {
        service.deleteById(1L);
        service.deleteById(1L);

        verify(specialtyRepository, times(2)).deleteById(1L);
    }

    @Test
    void deleteByIdAtLeastOnce() {
        service.deleteById(1L);
        service.deleteById(1L);

        verify(specialtyRepository, atLeastOnce()).deleteById(1L);
    }

    @Test
    void deleteByIdAtMost() {
        service.deleteById(1L);
        service.deleteById(1L);

        verify(specialtyRepository, atMost(5)).deleteById(1L);
    }

    @Test
    void deleteByNever() {
        service.deleteById(1L);
        service.deleteById(1L);

        verify(specialtyRepository, atMost(5)).deleteById(1L);
        verify(specialtyRepository, never()).deleteById(5L);
    }

    @Test
    void delete() {
        service.delete(new Speciality());
    }

    @Test
    void findByIdTest() {
        Speciality speciality = new Speciality();

        when(specialtyRepository.findById(1L)).thenReturn(Optional.of(speciality));

        Speciality foundSpecialtu = service.findById(1L);

        assertThat(foundSpecialtu).isNotNull();

        verify(specialtyRepository).findById(1L);
        verify(specialtyRepository).findById(anyLong());

    }

    @Test
    void testDeleteByObject() {

        Speciality speciality = new Speciality();

        service.delete(speciality);

        verify(specialtyRepository).delete(any(Speciality.class));
    }
}