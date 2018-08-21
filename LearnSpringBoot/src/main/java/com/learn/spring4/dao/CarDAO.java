package com.learn.spring4.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.learn.spring.model.CarPojo;

@Component
public class CarDAO {

	private static List<CarPojo> carList = new ArrayList<>();
	
	/**
	 * Returns list of cars from database.
	 * 
	 * @return list of customers
	 */
	public List<CarPojo> getList() {
		return carList;
	}

	/**
	 * Return car object for given name
	 * If car is not found, returns null.
	 * 
	 * @param name	car-name
	 * @return car	object for given name
	 */
	public CarPojo get(String name) {

		for (CarPojo car : carList) {
			if (car.getModel().equals(name)) {
				return car;
			}
		}

		return null;
	}

	/**
	 * Create new car on the database.
	 * 
	 * @param car	car-object
	 * @return car	car object
	 */
	public CarPojo create(CarPojo car) {
		carList.add(car);
		return car;
	}

	/**
	 * Update the car object on database.
	 * If car is not found, returns null.
	 * 
	 * @param name	car new name
	 * @return car	object for given name
	 */
	public CarPojo update(String name, CarPojo updatedCar) {

		for (CarPojo car : carList) {
			if (car.getModel().equals(name)) {
				car.setModel(updatedCar.getModel());
				car.setVersion(updatedCar.getVersion());
				return car;
			}
		}

		return null;
	}

	/**
	 * Delete the car object from database
	 * If car not found for given name, returns null.
	 * 
	 * @param name	car-name
	 * @return name	car model name
	 */
	public Boolean delete(String name) {

		for (CarPojo car : carList) {
			if (car.getModel().equals(name)) {
				carList.remove(car);
				return Boolean.TRUE;
			}
		}

		return Boolean.FALSE;
	}
}