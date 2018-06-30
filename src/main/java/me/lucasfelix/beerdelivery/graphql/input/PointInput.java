package me.lucasfelix.beerdelivery.model.dto;

public class PointInput {

    private String type;
    private Float lat;
    private Float lng;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Float getLat() {
        return lat;
    }

    public void setLat(Float lat) {
        this.lat = lat;
    }

    public Float getLng() {
        return lng;
    }

    public void setLng(Float lng) {
        this.lng = lng;
    }
}
