package com.learn.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.spring.model.CarPojo;
import com.learn.spring4.dao.CarDAO;

@Service("carService")
public class CarFactoryServiceImpl implements ICarFactoryService {

	@Autowired
	private CarDAO carDao;

	@Override
	public CarPojo getCar(String carName) {
		CarPojo car = carDao.get(carName);
		if (car != null) {
			return car;
		}

		return null;
	}

	@Override
	public List<CarPojo> getCarList() {
		return carDao.getList();
	}

	@Override
	public CarPojo createCar(CarPojo car) {
		return carDao.create(car);
	}

	@Override
	public Boolean createCars(List<CarPojo> carList) {
		int count = 0;
		for (CarPojo car : carList) {
			carDao.create(car);
			count++;
		}

		return (carList.size() == count) ? Boolean.TRUE : Boolean.FALSE;
	}

	@Override
	public Boolean updateCar(String carName, CarPojo car) {
		CarPojo updatedCar = carDao.update(carName, car);
		if (updatedCar != null) {
			return Boolean.TRUE;
		}

		return Boolean.FALSE;
	}

	@Override
	public Boolean deleteCar(String carName) {
		if (carDao.delete(carName)) {
			return Boolean.TRUE;
		}

		return Boolean.FALSE;
	}
}
