package me.lucasfelix.beerdelivery.model.dto;

import com.bedatadriven.jackson.datatype.jts.JtsModule;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vividsolutions.jts.geom.*;
import me.lucasfelix.beerdelivery.model.PDV;

import java.io.IOException;
import java.io.UncheckedIOException;

public class PDVInput {

    private String tradingName;
    private String ownerName;
    private String document;
    private PointInput address;
    private CoverageAreaInput coverageArea;

    public String getTradingName() {
        return tradingName;
    }

    public void setTradingName(String tradingName) {
        this.tradingName = tradingName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public PointInput getAddress() {
        return address;
    }

    public void setAddress(PointInput address) {
        this.address = address;
    }

    public CoverageAreaInput getCoverageArea() {
        return coverageArea;
    }

    public void setCoverageArea(CoverageAreaInput coverageArea) {
        this.coverageArea = coverageArea;
    }

    public PDV toPDV() {
        var geometryFactory = new GeometryFactory();

        var addressCoordinate = new Coordinate(address.getLng(), address.getLat());
        var address = geometryFactory.createPoint(addressCoordinate);

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JtsModule());

        try {
            var json = mapper.writeValueAsString(coverageArea);
            System.out.println(json);
            var multiPolygon = mapper.readValue(json, MultiPolygon.class);
            return new PDV(tradingName, ownerName, document, multiPolygon, address);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
