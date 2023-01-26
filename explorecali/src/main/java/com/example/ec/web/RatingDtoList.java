package com.example.ec.web;

import java.util.ArrayList;
import java.util.List;

public class RatingDtoList {
    private List<RatingDto> ratingDtoList;

    public List<RatingDto> getRatingDtoList() {
        return ratingDtoList;
    }

    public void setRatingDtoList(List<RatingDto> ratingDtoList) {
        this.ratingDtoList = ratingDtoList;
    }

    public int size() {
        return ratingDtoList.size();
    }

    private RatingDtoList() {
        ratingDtoList = new ArrayList<>();
    }
}
