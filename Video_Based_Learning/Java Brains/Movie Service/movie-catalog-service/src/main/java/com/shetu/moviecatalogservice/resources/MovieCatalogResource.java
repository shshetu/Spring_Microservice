package com.shetu.moviecatalogservice.resources;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.shetu.moviecatalogservice.models.CatalogItem;
import com.shetu.moviecatalogservice.models.Movie;
import com.shetu.moviecatalogservice.models.Rating;
import com.shetu.moviecatalogservice.models.UserRating;
import com.shetu.moviecatalogservice.services.MovieInfoService;
import com.shetu.moviecatalogservice.services.UserRatingInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
    // Call Url using Rest Template
    @Autowired // @Autowired -> consumer , @Bean -> producer
    private RestTemplate restTemplate;
    @Autowired
    private WebClient.Builder webClientBuilder;
    @Autowired
    private UserRatingInfoService userRatingInfo;
    @Autowired
    private MovieInfoService movieInfo;
    // Get all movie

    @GetMapping("/{userId}")
    public List<CatalogItem> getCatalogs(@PathVariable("userId") String userId) {

        // Replace Rest Template using WebClient


//        List<Rating> ratings = restTemplate.getForObject(MOVIE_INFO_API_FOR_INDIVIDUAL_USER+"/"+userId,
        UserRating rating = userRatingInfo.getUserRating(userId);

//        return ratings.stream().map(
//                rating -> {
//                    Movie movie = restTemplate.getForObject(MOVIE_INFO_API+"/"+ rating.getMovieId(), Movie.class);
////                    Movie movie = getMovie(rating);
//                    return new CatalogItem(movie.getName(), "Description", rating.getRating());
//                }
//        ).collect(Collectors.toList());

        return rating.getUserRating().stream().map(
                userRating -> movieInfo.getCatalogItem(userRating)
        ).collect(Collectors.toList());
    }



    // Asynchronous programming to get a movie object
    /*private Movie getMovie(Rating rating) {
        return webClientBuilder.build()
                .get()
                .uri(MOVIE_INFO_API + "/" + rating.getMovieId())
                .retrieve()
                .bodyToMono(Movie.class)
                .block();
    }*/
}
