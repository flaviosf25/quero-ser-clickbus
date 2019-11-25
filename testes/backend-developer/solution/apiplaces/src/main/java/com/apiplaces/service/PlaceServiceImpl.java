package com.apiplaces.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apiplaces.exception.PlaceAlreadyExistsException;
import com.apiplaces.exception.PlaceNotFoundException;
import com.apiplaces.model.Place;
import com.apiplaces.repository.PlaceRepository;
import com.apiplaces.repository.PlaceRepositoryQuery;

@Service
public class PlaceServiceImpl implements PlaceService{
	
	@Autowired
	PlaceRepository placeRepository;

	@Autowired
	PlaceRepositoryQuery placeRepositoryQuery;

	@Override
	public Place createPlace(Place place) {
		List<Place> places = placeRepositoryQuery.searchPlace(place);
		if(!places.isEmpty()) {
			throw new PlaceAlreadyExistsException();
		}
		place.setCreated(LocalDateTime.now());
		Place placeCreated = placeRepository.save(place);
		return placeCreated;
	}

	@Override
	public Place updatePlace(Place place) {
		Place placeUpdated = placeRepository.findPlaceById(place.getId());
		BeanUtils.copyProperties(place, placeUpdated, "created");
		placeUpdated.setUpdated(LocalDateTime.now());
		placeUpdated = placeRepository.save(placeUpdated);
		return placeUpdated;
	}

	public Place getPlaceById(Long id) {
		Place place = placeRepository.findPlaceById(id);
		if(place == null) {
			throw new PlaceNotFoundException();
		}
		return place;
	}

	public List<Place> getPlaceByName(String name) {
		List<Place> places = placeRepository.findPlaceByNameContainingIgnoreCase(name);
		if(places.isEmpty()) {
			throw new PlaceNotFoundException();
		}
		return places;
	}

	@Override
	public List<Place> getPlaces() {
		List<Place> listPlaces = placeRepository.findAll();
		return listPlaces;
	}
	
	@Override
	public void deletePlace(Place place) {
		Place placeDelete = placeRepository.findPlaceById(place.getId());
		if(placeDelete == null) {
			throw new PlaceNotFoundException();
		}
		placeRepository.delete(placeDelete);
	}

	
}
