package com.learn.spring4.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.spring4.dao.CarDAO;
import com.learn.spring4.service.CarPojo;

@RestController
public class ServletController {

	// http://localhost:8080/LearnSpring4RestService/cars/

	@Autowired
	private CarDAO carDao;

	@RequestMapping("/cars")
	public List<CarPojo> getCars() {
		return carDao.getList();
	}

	@GetMapping("/cars/{name}")
	public CarPojo getCar(@PathVariable String name) {
		return carDao.get(name);
	}

	@PostMapping(path = "/cars", consumes = "application/json")
	public ResponseEntity<CarPojo> createCar(@RequestBody CarPojo car) {
		return new ResponseEntity<CarPojo>(carDao.create(car), HttpStatus.OK);
	}

	@PutMapping(path = "/cars/{name}", consumes = "application/json")
	public ResponseEntity<CarPojo> updateCar(@PathVariable String name, @RequestBody CarPojo car) {
		
		CarPojo updatedCar = carDao.update(name, car);
		if (updatedCar == null)
		{
			return new ResponseEntity<CarPojo>(updatedCar, HttpStatus.OK);
		}

		return new ResponseEntity<CarPojo>(new CarPojo(), HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/cars/{name}")
	public ResponseEntity<String> deleteCar(@PathVariable String name) {

		if (carDao.delete(name))
		{
			return new ResponseEntity<String>(name, HttpStatus.OK);
		}

		return new ResponseEntity<String>(name + " not found", HttpStatus.NOT_FOUND);
	}
}
