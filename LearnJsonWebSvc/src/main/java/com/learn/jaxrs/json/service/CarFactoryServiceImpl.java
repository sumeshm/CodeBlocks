package com.learn.jaxrs.json.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/carfactory")
public class CarFactoryServiceImpl implements ICarFactoryService
{
	static List<CarPojo> carList = new ArrayList<>();

	/**
	 * Return list of car objects
	 * If car is not found, returns empty list.
	 * 
	 * @param name	car-name
	 * @return car	object for given name
	 */
	@GET
	@Path("/cars")
    @Produces({MediaType.APPLICATION_JSON})
	public List<CarPojo> getCarList()
	{
		return carList;
	}

	/**
	 * Create new car ond add it to the list
	 * 
	 * @param car	car-object
	 * @return car	car object
	 */
	@POST
	@Path("/cars")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<CarPojo> create(List<CarPojo> newCarList) {
		for (CarPojo newCar : newCarList) {
			carList.add(newCar);
		}

		return carList;
	}

	/**
	 * Update the car object in list
	 * If car is not found, returns null.
	 * 
	 * @param name	car new name
	 * @return car	object for given name
	 */
	@PUT
	@Path("/cars/{name}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public CarPojo update(@PathParam("name") String name, CarPojo updatedCar) {

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
	 * Delete the car object from list
	 * If car not found for given name, returns null.
	 * 
	 * @param name	car-name
	 * @return name	car model name
	 */
	@DELETE
	@Path("/cars/{name}")
    @Produces({MediaType.APPLICATION_JSON})
	public Boolean delete(@PathParam("name") String name) {

		for (CarPojo car : carList) {
			if (car.getModel().equals(name)) {
				carList.remove(car);
				return Boolean.TRUE;
			}
		}

		return Boolean.FALSE;
	}
	
	
}