package com.apiplaces.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.apiplaces.model.City;
import com.apiplaces.model.Place;
import com.apiplaces.model.State;
import com.apiplaces.repository.PlaceRepository;
import com.apiplaces.repository.PlaceRepositoryQuery;
import com.apiplaces.service.PlaceServiceImpl;

public class PlaceServiceTest {
	
	@Mock
	private PlaceRepository repository;
	
	@Mock
	PlaceRepositoryQuery placeRepositoryQuery;
	
	@InjectMocks
	private PlaceServiceImpl service;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void whenSave_thenReturnPlace() {
		Place place = new Place("Test", "Test", new City("Test"), new State("Test"), 
				LocalDateTime.now(), LocalDateTime.now());
		
		when(repository.save(place)).thenReturn(place);
		Place place2 = service.createPlace(place);
		assertThat(place2.getName()).isEqualTo(place.getName());
		
	}
	
	@Test
	public void whenUpdate_thenReturnPlace() {
		Place place = new Place("Test", "Test", new City("Test"), new State("Test"), 
				LocalDateTime.now(), LocalDateTime.now());
		place.setId(1L);
		
		when(repository.findPlaceById(1L)).thenReturn(place);
		Place place2 = service.updatePlace(place);
		assertThat(place2.getName()).isEqualTo(place2.getName());
		
	}
	
	@Test
	public void whenFindAll_thenReturnPlaces() {
		List<Place> listPlaces = Stream.of(new Place("TestList", "Test", new City("Test"), new State("Test"), 
				LocalDateTime.now(), LocalDateTime.now()), new Place("TestList", "Test", new City("Test"), new State("Test"), 
				LocalDateTime.now(), LocalDateTime.now())).collect(Collectors.toList());
		
		when(repository.findAll()).thenReturn(listPlaces);
		Stream<Place> stream = service.getPlaces().stream().filter(place -> place.getName().equals("TestList"));
		assertTrue(stream.toArray().length == 2);
		
	}
	
	@Test
	public void whenFindById_thenReturnPlace() {
		Place place = new Place("Test", "Test", new City("Test"), new State("Test"), 
				LocalDateTime.now(), LocalDateTime.now());
		
		when(repository.findPlaceById(1L)).thenReturn(place);
		Place place2 = service.getPlaceById(1L);
		assertThat(place2.getName()).isEqualTo(place.getName());
		
	}
	
	@Test
	public void whenFindByName_thenReturnPlace() {
		List<Place> listPlaces = Stream.of(new Place("Test2", "Test", new City("Test"), new State("Test"), 
				LocalDateTime.now(), LocalDateTime.now()), new Place("Test2", "Test", new City("Test"), new State("Test"), 
				LocalDateTime.now(), LocalDateTime.now())).collect(Collectors.toList()); 
		
		when(repository.findAll()).thenReturn(listPlaces);
		List<Place> listPlaces2 = service.getPlaceByName("Test2");
		assertTrue(listPlaces2.size() == 2);
		
	}

}
