package com.revature.repositories;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.revature.models.CustomResponse;

@Repository
public class HelloRepository {

	static private HashMap<Integer, CustomResponse> responses = new HashMap<>();
	private int idCount = 3;
	{
		responses.put(1, new CustomResponse(1, "Hello"));
		responses.put(2,  new CustomResponse(2, "Goodbye"));
	}
	
	public String getHello() {
		return "Hello world!";
	}

	public Optional<CustomResponse> getResponseById(int id) {
		return Optional.ofNullable(responses.get(id));
	}

	public CustomResponse create(CustomResponse customResponse) {
		customResponse.setResponseValue(idCount++);
		responses.put(customResponse.getResponseValue(), customResponse);
		return customResponse;
	}

	
	
}
