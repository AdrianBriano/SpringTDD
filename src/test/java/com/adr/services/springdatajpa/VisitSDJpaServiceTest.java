package com.adr.services.springdatajpa;

import com.adr.model.Visit;
import com.adr.repositories.VisitRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VisitSDJpaServiceTest {

    @Mock
    VisitRepository visitRepository;

    @InjectMocks
    VisitSDJpaService visitSDJpaService;

    @Test
    void findAll() {
        Visit visit = new Visit();

        Set<Visit> visits = new HashSet<>();
        visits.add(visit);

        when(visitRepository.findAll()).thenReturn(visits);

        Set<Visit> foundVisits = visitSDJpaService.findAll();

        assertThat(visitRepository.findAll()).isInstanceOf(Set.class);
        assertThat(foundVisits).hasSize(1);

        verify(visitRepository, atLeastOnce()).findAll();

    }

    @Test
    void findById() {
        Visit visit = new Visit();

        when(visitRepository.findById(anyLong())).thenReturn(Optional.of(visit));

        Visit foundVisit = visitSDJpaService.findById(1L);

        assertThat(foundVisit).isExactlyInstanceOf(Visit.class);

        verify(visitRepository, atLeastOnce()).findById(1L);
        verify(visitRepository, atLeastOnce()).findById(anyLong());
    }

    @Test
    void save() {
        Visit visit = new Visit();

        when(visitRepository.save(any(Visit.class))).thenReturn(visit);

        Visit savedVisit = visitSDJpaService.save(visit);

        assertThat(savedVisit).isExactlyInstanceOf(Visit.class);

        verify(visitRepository, atLeastOnce()).save(any());
    }

    @Test
    void delete() {
        Visit visit = new Visit();

        visitSDJpaService.delete(visit);

        verify(visitRepository, atLeastOnce()).delete(visit);
        verify(visitRepository, atLeastOnce()).delete(any(Visit.class));
    }

    @Test
    void deleteById() {
        Visit visit = new Visit(1l);

        visitSDJpaService.deleteById(1L);

        verify(visitRepository, atLeastOnce()).deleteById(1L);
        verify(visitRepository, atLeastOnce()).deleteById(anyLong());
    }
}