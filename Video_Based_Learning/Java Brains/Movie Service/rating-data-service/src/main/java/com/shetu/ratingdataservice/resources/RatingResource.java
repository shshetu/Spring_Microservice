package com.shetu.ratingdataservice.resources;

import com.shetu.ratingdataservice.models.Rating;
import com.shetu.ratingdataservice.models.UserRating;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ratingData")
public class RatingResource {
    @GetMapping("/{movieId}")
    public Rating getRating(@PathVariable("movieId") String movieId){
        return new Rating(movieId,4);
    }

    @GetMapping("/users/{userId}")
    public UserRating getRatingByUserId(@PathVariable("userId") String userId){
        List<Rating> ratings = Arrays.asList(
                new Rating(userId,"1234", 4),
                new Rating(userId,"5678", 3)
        );
        UserRating userRating = new UserRating();
        userRating.setUserRating(ratings);
        return userRating;
    }
}
