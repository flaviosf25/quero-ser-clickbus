package com.apiplaces.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.apiplaces.model.City;
import com.apiplaces.model.Place;
import com.apiplaces.model.State;

@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@DataJpaTest
public class PlaceRepositoryTest {
	
	@Autowired
	private PlaceRepository repository;
	
	@Autowired
	private TestEntityManager entityManager;

	@Test
	public void whenSave_thenReturnPlace() {
		Place place = new Place("Test", "Test", new City("Test"), new State("Test"), 
				LocalDateTime.now(), LocalDateTime.now());
		
		entityManager.persistAndFlush(place);
		Place placeCreated = repository.findPlaceById(1L);
		assertThat(placeCreated.getName()).isEqualTo(place.getName());
		
	}
	
	@Test
	public void whenUpdate_thenReturnPlace() {
		Place place = new Place("Test", "Test", new City("Test"), new State("Test"), 
				LocalDateTime.now(), LocalDateTime.now());
		
		Place place2 = entityManager.persistAndFlush(place);
		place2.setName("TestUpdate");
		entityManager.persistAndFlush(place2);
		
		Place placeUpdated = repository.findPlaceByNameContainingIgnoreCase("TestUpdate").get(0);
		assertThat(placeUpdated.getName()).isEqualTo(place2.getName());
		
	}
	
	@Test
	public void whenFindAll_thenReturnPlaces() {
		List<Place> listPlaces = Stream.of(new Place("TestList", "Test", new City("Test"), new State("Test"), 
				LocalDateTime.now(), LocalDateTime.now())).collect(Collectors.toList());
		
		listPlaces.forEach(place -> entityManager.persistAndFlush(place));
		Stream<Place> stream = repository.findAll().stream().filter(place -> place.getName().equals("TestList"));
		assertTrue(stream.toArray().length == 1);
		
	}
	
	@Test
	public void whenFindById_thenReturnPlace() {
		Place place = new Place("Test", "Test", new City("Test"), new State("Test"), 
				LocalDateTime.now(), LocalDateTime.now());
		
		entityManager.persistAndFlush(place);
		Place place2 = repository.findPlaceById(1L);
		assertThat(place2.getName()).isEqualTo(place.getName());
		
	}
	
	@Test
	public void whenFindByName_thenReturnPlace() {
		List<Place> listPlaces = Stream.of(new Place("Test2", "Test", new City("Test"), new State("Test"), 
				LocalDateTime.now(), LocalDateTime.now()), new Place("Test2", "Test", new City("Test"), new State("Test"), 
				LocalDateTime.now(), LocalDateTime.now())).collect(Collectors.toList()); 
		
		listPlaces.forEach(place -> entityManager.persistAndFlush(place));
		List<Place> listPlaces2 = repository.findPlaceByNameContainingIgnoreCase("Test2");
		assertTrue(listPlaces2.size() == 2);
		
	}

}
