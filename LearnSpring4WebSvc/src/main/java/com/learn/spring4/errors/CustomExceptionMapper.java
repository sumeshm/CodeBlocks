package com.learn.spring4.errors;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class CustomExceptionMapper implements ExceptionMapper<DataNotFoundException> {

	@Override
	public Response toResponse(DataNotFoundException exception) {
		return Response
				.status(Status.NOT_FOUND)
				.entity(new DataNotFoundMessage(exception.getMessage(), "404"))
				.build();
	}

}
