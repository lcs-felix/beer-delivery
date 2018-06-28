package me.lucasfelix.beerdelivery.model.dto;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import me.lucasfelix.beerdelivery.model.PDV;

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

        System.out.println(coverageArea);

        return new PDV("Teste", ownerName, document, null, address);
    }
}
