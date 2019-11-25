package com.apiplaces.service;

import java.util.List;

import com.apiplaces.model.Place;

public interface PlaceService {
	
	Place createPlace(Place place);
	
	Place updatePlace(Place place);
	
	Place getPlaceById(Long id);
	
	List<Place> getPlaceByName(String name);
	
	List<Place> getPlaces();
	
	void deletePlace(Place place);
}
