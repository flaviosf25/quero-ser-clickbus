package com.apiplaces.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apiplaces.model.Place;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long>{

	Place findPlaceById(Long id);
	
	List<Place> findPlaceByNameContainingIgnoreCase(String name);
}
