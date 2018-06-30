package me.lucasfelix.beerdelivery.graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import me.lucasfelix.beerdelivery.model.PDV;
import me.lucasfelix.beerdelivery.service.PDVService;
import org.springframework.stereotype.Component;

@Component
public class Query implements GraphQLQueryResolver {

    private PDVService pdvService;

    public Query(PDVService pdvService) {
        this.pdvService = pdvService;
    }

    public PDV findPDVById(Long id) {
        return pdvService.findById(id).get();
    }

    public Iterable<PDV> findNearestPDVs(Double longitude , Double latitude) {

        var geometryFactory = new GeometryFactory();
        var point = geometryFactory.createPoint(new Coordinate(longitude, latitude));

        return pdvService.findAvaliablePDVs(point);
    }
}
