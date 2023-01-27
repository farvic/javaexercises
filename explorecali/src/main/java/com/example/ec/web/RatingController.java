package com.example.ec.web;

import com.example.ec.service.TourRatingService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * Created by Mary Ellen Bowman
 */
@RestController
@RequestMapping(path = "/ratings")
@Tag(name = "Rating", description = "Rating API")
public class RatingController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RatingController.class);
    private TourRatingService tourRatingService;

    private RatingAssembler assembler;

    @Autowired
    public RatingController(TourRatingService tourRatingService, RatingAssembler assembler) {
        this.tourRatingService = tourRatingService;
        this.assembler = assembler;
    }

    // @GetMapping
    // public CollectionModel<RatingDto> getAll() {
    // LOGGER.info("GET /ratings");
    // return assembler.toCollectionModel(tourRatingService.lookupAll());
    // }

    @GetMapping
    @Operation(summary = "Get all ratings", description = "Get all ratings for all tours")
    @ApiResponse(responseCode = "200", description = "OK")
    public List<RatingDto> getAll() {
        LOGGER.info("GET /ratings");
        return tourRatingService.lookupAll().stream()
                .map(t -> new RatingDto(t.getScore(), t.getComment(), t.getCustomerId())).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a rating by id", description = "Get rating for a tour")
    @ApiResponse(responseCode = "200", description = "OK")
    public RatingDto getRating(@PathVariable("id") Integer id) {
        LOGGER.info("GET /ratings/{id}", id);
        return assembler.toModel(tourRatingService.lookupRatingById(id)
                .orElseThrow(() -> new NoSuchElementException("Rating " + id + " not found")));
    }

    /**
     * Exception handler if NoSuchElementException is thrown in this Controller
     *
     * @param ex exception
     * @return Error message String.
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public String return400(NoSuchElementException ex) {
        LOGGER.error("Unable to complete transaction", ex);
        return ex.getMessage();
    }
}
