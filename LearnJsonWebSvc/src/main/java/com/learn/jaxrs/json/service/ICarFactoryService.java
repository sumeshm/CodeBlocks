package com.learn.jaxrs.json.service;

import java.util.List;


public interface ICarFactoryService {

	public List<CarPojo> getCarList();
	
	public List<CarPojo> create(List<CarPojo> newCarList);

	public CarPojo update(String name, CarPojo updatedCar);

	public Boolean delete(String name);
}
