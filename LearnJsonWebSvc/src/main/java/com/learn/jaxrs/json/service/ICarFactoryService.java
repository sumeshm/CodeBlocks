package com.learn.jaxrs.json.service;

import java.util.List;

//@Path("/carfactory")
public interface ICarFactoryService {

//	@GET
//	@Path("/cars")
//	@Produces(MediaType.APPLICATION_JSON)
	public List<CarPojo> getCarList();
}
