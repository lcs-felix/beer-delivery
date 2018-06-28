package me.lucasfelix.beerdelivery.model.dto;

import java.util.List;

public class CoverageAreaInput {

    private String type;
    private List<List<List<List<Float>>>> coordinates;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<List<List<List<Float>>>> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<List<List<List<Float>>>> coordinates) {
        this.coordinates = coordinates;
    }
}
