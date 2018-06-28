package me.lucasfelix.beerdelivery.data.repository;

import com.vividsolutions.jts.geom.Point;
import me.lucasfelix.beerdelivery.model.PDV;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PDVRepository extends Repository<PDV, Long> {

    PDV save(PDV newPDV);
    Optional<PDV> findById(Long id);

    @Query("select p from PDV p where contains(p.coverageArea, :point) = true " +
            "order by distance(p.address, :point) asc")
    Iterable<PDV> findAvaliablePDVs(@Param("point") Point point);
}
