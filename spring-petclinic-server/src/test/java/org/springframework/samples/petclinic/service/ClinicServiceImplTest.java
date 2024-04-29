package org.springframework.samples.petclinic.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.Visit;
import org.springframework.samples.petclinic.repository.OwnerRepository;
import org.springframework.samples.petclinic.repository.PetRepository;
import org.springframework.samples.petclinic.repository.VetRepository;
import org.springframework.samples.petclinic.repository.VisitRepository;

class ClinicServiceImplTest {

    private ClinicServiceImpl clinicService;
    @Mock
    private PetRepository petRepository;
    @Mock
    private VetRepository vetRepository;
    @Mock
    private OwnerRepository ownerRepository;

    @Mock
    private VisitRepository visitRepository;

    @Before
    public void setup() {
        clinicService = new ClinicServiceImpl(petRepository, vetRepository, ownerRepository, null);
    }

    @Test
    public void testGetLastVisit() {
        // gerate 3 Visits witha diffrent dates
        Visit visit1 = new Visit();
        visit1.setDate(new Date(2020, 1, 1));
        Visit visit2 = new Visit();
        visit2.setDate(new Date(2020, 1, 2));
        Visit visit3 = new Visit();
        visit3.setDate(new Date(2020, 1, 3));

        // create a pet and add the visits
        Pet pet = new Pet();
        pet.addVisit(visit1);
        pet.addVisit(visit2);
        pet.addVisit(visit3);
        // assert last visit
        assertEquals(visit3, clinicService.getLastVisit(pet));

    }

    //Generate test with no Visits for a pet
    @Test
    public void testGetLastVisitNoVisits() {
        Pet pet = new Pet();
        assertEquals(null, clinicService.getLastVisit(pet));
    }

    //Generate test with one Visit for a pet
    @Test
    public void testGetLastVisitOneVisit() {
        Visit visit1 = new Visit();
        visit1.setDate(new Date(2020, 1, 1));
        Pet pet = new Pet();
        pet.addVisit(visit1);
        assertEquals(visit1, clinicService.getLastVisit(pet));
    }

}