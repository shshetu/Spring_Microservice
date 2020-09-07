package com.shetu.moviecatalogservice.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.shetu.moviecatalogservice.models.CatalogItem;
import com.shetu.moviecatalogservice.models.Movie;
import com.shetu.moviecatalogservice.models.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieInfoService {
    // Movie Info Resource
    private final String MOVIE_INFO_API = "http://movie-info-service/movies";

    @Autowired
    private RestTemplate restTemplate;

    // Bulkhead pattern
    /*
    *  @HystrixCommand(fallbackMethod = "getFallbackCatalogItem",
            threadPoolKey = "movieInfoPool",
            threadPoolProperties = {
                            @HystrixProperty(name="coreSize", value="20") // max request it will take
                            @HystrixProperty(name="maxQueueSize", value="10) // max number of request that will wait in queue
             }
    )
    * */

    @HystrixCommand(fallbackMethod = "getFallbackCatalogItem",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000"),
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "5"),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "50"),
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "5000")

            }
    )
    public CatalogItem getCatalogItem(Rating userRating) {
        Movie movie = restTemplate.getForObject(MOVIE_INFO_API + "/" + userRating.getMovieId(), Movie.class);
        return new CatalogItem(movie.getTitle(), movie.getOverview(), userRating.getRating());
    }

    // fall back method
    public CatalogItem getFallbackCatalogItem(Rating userRating) {
        return new CatalogItem("Movie name not found","",0);
    }

}
