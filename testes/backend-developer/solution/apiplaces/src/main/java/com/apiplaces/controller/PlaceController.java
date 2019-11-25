package com.apiplaces.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.apiplaces.model.Place;
import com.apiplaces.service.PlaceService;

@Controller
@RequestMapping("/places")
public class PlaceController {
	
	@Autowired
	PlaceService placeService;

	@PostMapping
	public ResponseEntity<Place> createPlace(@Valid @RequestBody Place place){
		Place placeCreated = placeService.createPlace(place);
		return ResponseEntity.status(HttpStatus.CREATED).body(placeCreated);
	}
	
	@PutMapping
	public ResponseEntity<Place> updatePlace(@Valid @RequestBody Place place){
		Place placeUpdated = placeService.updatePlace(place);
		return ResponseEntity.status(HttpStatus.OK).body(placeUpdated);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Place> getPlaceById(@PathVariable Long id){
		Place place = placeService.getPlaceById(id);
		return ResponseEntity.status(HttpStatus.OK).body(place); 
	}
	
	@GetMapping("/search/{name}")
	public ResponseEntity<List<Place>> getPlaceByName(@PathVariable String name){
		List<Place> listPlaces = placeService.getPlaceByName(name);
		return ResponseEntity.status(HttpStatus.OK).body(listPlaces); 
	}
	
	@GetMapping
	public ResponseEntity<List<Place>> getPlaces(){
		List<Place> listPlaces = placeService.getPlaces();
		return ResponseEntity.status(HttpStatus.OK).body(listPlaces); 
	}
	
	// OPTIONAL: Have the ability to do a delete by place id
	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping("/{id}")
	public void deletePlace(@PathVariable Long id) {
		Place place = placeService.getPlaceById(id);
		placeService.deletePlace(place);
	}
}
