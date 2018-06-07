package com.learn.jaxrs.json.service;

import java.util.ArrayList;
import java.util.List;


// http://localhost:8080/LearnJsonWebSvcMvn/carfactory/cars

public class CarFactoryServiceImpl implements ICarFactoryService
{
	public List<CarPojo> getCarList()
	{
		List<CarPojo> carList = new ArrayList<>();
		carList.add(new CarPojo(1, "Kwid"));
		carList.add(new CarPojo(1, "Duster"));
		carList.add(new CarPojo(1, "Fluence"));

		return carList;
	}
}