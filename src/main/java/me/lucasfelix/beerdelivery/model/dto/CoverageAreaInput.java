package me.lucasfelix.beerdelivery.model.dto;

import java.util.List;

public class CoverageAreaInput {

    private String type;
    private List<List<List<List<Float>>>> cordinates;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<List<List<List<Float>>>> getCordinates() {
        return cordinates;
    }

    public void setCordinates(List<List<List<List<Float>>>> cordinates) {
        this.cordinates = cordinates;
    }
}
