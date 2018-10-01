package com.learn.spring.rest;

import java.util.List;

import javax.ws.rs.core.Response;

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

	private static final Logger LOGGER = LoggerFactory.getLogger(ServletController.class);

	@Autowired
	protected ICarFactoryService carService;

	@RequestMapping(path = "/cars", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public Response getCars() {
		List<CarPojo> carList = carService.getCarList();
		LOGGER.info("Car List: " + carList.size());
		if (carList.size() > 0) {
			return Response.status(HttpStatus.OK.value()).entity(carList).build();
		} else {
			return Response.status(HttpStatus.OK.value()).entity("Cars NOT Found").build();
		}
	}

	@GetMapping(path = "/car/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Response getCar(@PathVariable String name) {
		CarPojo car = carService.getCar(name);
		LOGGER.info("retval[{}]={}", name, car);
		if (car != null) {
			return Response.status(HttpStatus.OK.value()).entity(car).build();
		} else {
			return Response.status(HttpStatus.OK.value()).entity("Car NOT Found").build();
		}
	}

	@PostMapping(path = "/car", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createCar(@RequestBody CarPojo car) {
		LOGGER.info("input car=" + car);
		CarPojo retVal = carService.createCar(car);
		LOGGER.info("retVal=" + retVal);
		if (retVal != null) {
			return new ResponseEntity<CarPojo>(retVal, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("Failed to create Car", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(path = "/cars", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> createCars(@RequestBody List<CarPojo> carList) {
		LOGGER.info("input car list=" + carList.size() + " - " + carList);
		Boolean retVal = carService.createCars(carList);
		if (retVal) {
			return new ResponseEntity<String>(retVal.toString(), HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping(path = "/car/{name}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateCar(@PathVariable String name, @RequestBody CarPojo car) {
		LOGGER.info("input car=" + car);
		LOGGER.info("target car=" + name);
		if (carService.updateCar(name, car) == Boolean.TRUE) {
			CarPojo retVal = carService.getCar(name);
			return new ResponseEntity<CarPojo>(retVal, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Car NOT Updated", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/cars/{name}")
	public ResponseEntity<String> deleteCar(@PathVariable String name) {
		LOGGER.info("target car=" + name);
		if (carService.deleteCar(name) == Boolean.TRUE) {
			return new ResponseEntity<String>("Car Deleted", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Car NOT Found", HttpStatus.NOT_FOUND);
		}
	}
}
