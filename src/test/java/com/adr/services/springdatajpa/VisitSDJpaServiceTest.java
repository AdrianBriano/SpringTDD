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
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VisitSDJpaServiceTest {

    @Mock
    VisitRepository visitRepository;

    @InjectMocks
    VisitSDJpaService visitSDJpaService;

    @Test
    void findAll() {
        //given
        Visit visit = new Visit();
        Set<Visit> visits = new HashSet<>();
        visits.add(visit);

        //when
        given(visitRepository.findAll()).willReturn(visits);
        Set<Visit> foundVisits = visitSDJpaService.findAll();

        //then
        assertThat(visitRepository.findAll()).isInstanceOf(Set.class);
        assertThat(foundVisits).hasSize(1);

        then(visitRepository).should(atLeastOnce()).findAll();
    }

    @Test
    void findById() {
        //given
        Visit visit = new Visit();
        given(visitRepository.findById(anyLong())).willReturn(Optional.of(visit));

        //when
        Visit foundVisit = visitSDJpaService.findById(1L);

        //then
        assertThat(foundVisit).isExactlyInstanceOf(Visit.class);

        then(visitRepository).should().findById(1L);
        then(visitRepository).should().findById(anyLong());
    }

    @Test
    void save() {
        //given
        Visit visit = new Visit();
        given(visitRepository.save(any(Visit.class))).willReturn(visit);

        //when
        Visit savedVisit = visitSDJpaService.save(visit);

        //then
        then(visitRepository).should(atLeastOnce()).save(any(Visit.class));
        assertThat(savedVisit).isNotNull();
    }

    @Test
    void delete() {
        //given
        Visit visit = new Visit();

        //then
        visitSDJpaService.delete(visit);

        //when
        then(visitRepository).should(atLeastOnce()).delete(visit);
        then(visitRepository).should(atLeastOnce()).delete(any(Visit.class));
    }

    @Test
    void deleteById() {
        //when
        visitSDJpaService.deleteById(1L);

        //then
        then(visitRepository).should(atLeastOnce()).deleteById(1L);
        then(visitRepository).should(atLeastOnce()).deleteById(anyLong());
    }
}