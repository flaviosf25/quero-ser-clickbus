package com.apiplaces.controller;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import java.time.LocalDateTime;

import org.junit.Test;
import org.springframework.http.HttpStatus;

import com.apiplaces.model.City;
import com.apiplaces.model.Place;
import com.apiplaces.model.State;

import io.restassured.http.ContentType;

public class PlaceControllerTest {

	@Test
	public void whenSave_thenReturnPlace() {
		Place place = new Place("Test", "Test", new City("Test"), new State("Test"), 
				LocalDateTime.now(), LocalDateTime.now());
		
		 given()
		 	.request()
		 	.header("Accept", ContentType.ANY)
		 	.header("Content-type", ContentType.JSON).body(place)
		 	
		 .when()
		 	.post("/places")
		 .then()
		 	.log().headers().and().log().body().and()
		 	.statusCode(HttpStatus.CREATED.value())
		 .body("name", equalTo("Test"), "slug", equalTo("Test"));
		 
	}
	
	@Test
	public void whenUpdate_thenReturnPlace() {
		Place place = new Place("Test", "Test", new City("Test"), new State("Test"), 
				LocalDateTime.now(), LocalDateTime.now());
		place.setId(1L);
		
		given()
		.request()
		.header("Accept", ContentType.ANY)
		.header("Content-type", ContentType.JSON).body(place)
		
		.when()
		.put("/places")
		.then()
		.log().headers().and().log().body().and()
		.statusCode(HttpStatus.OK.value())
		.body("name", equalTo("Test"), "slug", equalTo("Test"));
		
	}
	
	@Test
	public void whenFindAll_thenReturnPlaces() {
		
		given()
		
		.get("/places")
		.then()
		.log().headers().and().log().body().and()
		.statusCode(HttpStatus.OK.value())
		.body("name", equalTo("Test"));
		
	}
	
	@Test
	public void whenFindById_thenReturnPlace() {
		
		given()
		.pathParam("id", "1")
		.get("/places/{id}")
		.then()
		.log().headers().and().log().body().and()
		.statusCode(HttpStatus.OK.value())
		.body("name", equalTo("Test"), "slug", equalTo("Test"));
		
	}
	
	@Test
	public void whenFindByName_thenReturnPlace() {
		
		given()
		.pathParam("name", "Test")
		.get("/places/search/{name}")
		.then()
		.log().headers().and().log().body().and()
		.statusCode(HttpStatus.OK.value())
		.body("name", equalTo("Test"), "slug", equalTo("Test"));
		
	}

}
