package me.lucasfelix.beerdelivery.graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import me.lucasfelix.beerdelivery.data.repository.PDVRepository;
import me.lucasfelix.beerdelivery.model.PDV;
import org.springframework.stereotype.Component;

@Component
public class Query implements GraphQLQueryResolver {

    private PDVRepository pdvRepository;

    public Query(PDVRepository pdvRepository) {
        this.pdvRepository = pdvRepository;
    }

    public PDV findPDVById(Long id) {
        return pdvRepository.findById(id).get();
    }

    public Iterable<PDV> findNearestPDVs(Double longitude , Double latitude) {

        var geometryFactory = new GeometryFactory();
        var point = geometryFactory.createPoint(new Coordinate(longitude, latitude));

        return pdvRepository.findAvaliablePDVs(point);
    }
}
