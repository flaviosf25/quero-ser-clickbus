package com.apiplaces.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.apiplaces.model.Place;

@Repository
public interface PlaceRepositoryQuery {
	
	List<Place> searchPlace(Place place);
}
