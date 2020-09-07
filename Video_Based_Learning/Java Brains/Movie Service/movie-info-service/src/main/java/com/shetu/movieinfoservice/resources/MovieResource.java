package com.shetu.movieinfoservice.resources;

import com.shetu.movieinfoservice.models.Movie;
import com.shetu.movieinfoservice.models.MovieSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/movies")
public class MovieResource {

    @Value("${api.key}")
    private String apiKey;

    @Value("${movie-site}")
    private String movieSite;
    
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{movieId}")
    public Movie getMovieInfo(@PathVariable("movieId") String movieId){
        MovieSummary movieSummary = restTemplate.getForObject(movieSite+"?api_key="+apiKey, MovieSummary.class);
        return new Movie(movieId,movieSummary.getTitle(),movieSummary.getOverview());
    }
}
