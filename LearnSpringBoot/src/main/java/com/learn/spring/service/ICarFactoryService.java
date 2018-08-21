package com.learn.spring.service;

import java.util.List;

import com.learn.spring.model.CarPojo;

public interface ICarFactoryService {

	public CarPojo getCar(String carName);

	public List<CarPojo> getCarList();

	public CarPojo createCar(CarPojo car);

	public Boolean updateCar(String carName, CarPojo car);

	public Boolean deleteCar(String carName);
}
