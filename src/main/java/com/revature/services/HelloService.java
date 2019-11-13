package com.revature.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.revature.models.CustomResponse;
import com.revature.repositories.HelloRepository;

@Service
public class HelloService {
	
	HelloRepository helloRepository;

	@Autowired
	public HelloService(HelloRepository helloRepository) {
		super();
		this.helloRepository = helloRepository;
	}

	public String getHello() {
		return helloRepository.getHello();
	}

	/**
	 * Get object from repo or throw HttpClientErrorException with
	 * 404 if it does not exist
	 * @param id
	 * @return
	 */
	public CustomResponse getResponseById(int id) {
		return helloRepository
			.getResponseById(id)
			.orElseThrow(
				() -> new HttpClientErrorException(HttpStatus.NOT_FOUND,
						"Response not found with id " + id));
	}

	public CustomResponse create(CustomResponse customResponse) {
		return helloRepository.create(customResponse);
	}
	
	
	

}
