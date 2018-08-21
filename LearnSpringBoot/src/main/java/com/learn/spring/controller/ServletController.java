package com.learn.spring.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.spring.model.CarPojo;
import com.learn.spring.service.ICarFactoryService;

@RestController
@RequestMapping("/LearnSpringBoot")
public class ServletController {

	// http://localhost:8080/LearnSpringBoot/cars/

	private Logger logger = LoggerFactory.getLogger(ServletController.class);

	@Autowired
	protected ICarFactoryService carService;

	@RequestMapping(path = "/cars", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public List<CarPojo> getCars() {
		logger.info("sumesh: Get the list of cars");
		return carService.getCarList();
	}

	@GetMapping(path = "/cars/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CarPojo> getCar(@PathVariable String name) {
		CarPojo car = carService.getCar(name);
		if (car != null) {
			return new ResponseEntity<CarPojo>(car, HttpStatus.OK);
		} else {
			// todo, respond back with link for crating car
			return new ResponseEntity<CarPojo>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping(path = "/cars", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CarPojo> createCar(@RequestBody CarPojo car) {
		CarPojo retVal = carService.createCar(car);
		if (retVal != null) {
			return new ResponseEntity<CarPojo>(retVal, HttpStatus.CREATED);
		} else {
			// todo, respond back with some error info
			return new ResponseEntity<CarPojo>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping(path = "/cars/{name}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CarPojo> updateCar(@PathVariable String name, @RequestBody CarPojo car) {

		if (carService.updateCar(name, car) == Boolean.TRUE) {
			CarPojo retVal = carService.getCar(name);
			return new ResponseEntity<CarPojo>(retVal, HttpStatus.OK);
		} else {
			// todo, respond back with some error info
			return new ResponseEntity<CarPojo>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/cars/{name}")
	public ResponseEntity<String> deleteCar(@PathVariable String name) {

		if (carService.deleteCar(name) == Boolean.TRUE) {
			return new ResponseEntity<String>("Deleted", HttpStatus.OK);
		} else {
			// todo, respond back with some error info
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
	}
}
