package com.interview;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ch.qos.logback.core.net.ObjectWriter;

public class Driver {

	public static void main(String[] args) throws JsonProcessingException {

		Map<String, String> data = new HashMap<>();
		data.put("1", "one");
		data.put("2", "two");
		data.put("3", "three");

		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = mapper.writeValueAsString(data);
		System.out.println(jsonInString);
		
	}

}
