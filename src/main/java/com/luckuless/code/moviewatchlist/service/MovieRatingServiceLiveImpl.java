package com.luckuless.code.moviewatchlist.service;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.node.ObjectNode;

@Profile({"prod", "default"})
@Service("omdbService")
public class MovieRatingServiceLiveImpl implements MovieRatingService{
    String apiUrl = "http://www.omdbapi.com/?i=tt3896198&apikey=3337d9b7&t=";
    
    @Override
	public Optional<String> getMovieRating(String title) {

		String rating;
		
		try {
			RestTemplate template = new RestTemplate();
			
			ResponseEntity<ObjectNode> response = 
					template.getForEntity(apiUrl + title, ObjectNode.class);
			
			ObjectNode jsonObject = response.getBody();

			
			rating = jsonObject.path("imdbRating").asText();
			
			if (StringUtils.equals(rating, "N/A")) {
				return Optional.empty();
			}


			return Optional.of(rating);
		} catch (Exception e) {
			System.out.println("Something went wront while calling OMDb API" + e.getMessage());
			return null;
		}
	}   
}