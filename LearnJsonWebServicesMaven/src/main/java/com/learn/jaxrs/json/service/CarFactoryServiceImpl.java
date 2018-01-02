package com.learn.jaxrs.json.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/carfactory")
public class CarFactoryServiceImpl implements ICarFactoryService
{
	@GET
	@Path("/cars")
	@Produces(MediaType.APPLICATION_JSON)
	public List<CarPojo> getCarList()
	{
		List<CarPojo> listPerson = new ArrayList<>();
		
		CarPojo kwid = new CarPojo(1, "Fluence");
		listPerson.add(kwid);
		
		CarPojo duster = new CarPojo(1, "Duster");
		listPerson.add(duster);
		
		return listPerson;
	}
}