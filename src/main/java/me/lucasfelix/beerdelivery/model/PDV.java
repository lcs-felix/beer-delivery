package me.lucasfelix.beerdelivery.model;

import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Point;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "pdvs")
public class PDV {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String tradingName;
    private String ownerName;
    private String document;
    private MultiPolygon coverageArea;
    private Point address;

    public PDV(String tradingName, String ownerName, String document, MultiPolygon coverageArea, Point address) {
        this.tradingName = tradingName;
        this.ownerName = ownerName;
        this.document = document;
        this.coverageArea = coverageArea;
        this.address = address;
    }

    public PDV() {
    }

    public Long getId() {
        return id;
    }

    public String getTradingName() {
        return tradingName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getDocument() {
        return document;
    }

    public MultiPolygon getCoverageArea() {
        return coverageArea;
    }

    public Point getAddress() {
        return address;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PDV pdv = (PDV) o;
        return Objects.equals(document, pdv.document);
    }

    @Override
    public int hashCode() {

        return Objects.hash(document);
    }

    @Override
    public String toString() {
        return "PDV{" +
                "id=" + id +
                ", tradingName='" + tradingName + '\'' +
                ", ownerName='" + ownerName + '\'' +
                ", document='" + document + '\'' +
                ", coverageArea=" + coverageArea +
                ", address=" + address +
                '}';
    }
}
