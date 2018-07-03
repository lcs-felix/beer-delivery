package me.lucasfelix.beerdelivery.service;

import com.vividsolutions.jts.geom.Point;
import me.lucasfelix.beerdelivery.data.repository.PDVRepository;
import me.lucasfelix.beerdelivery.model.PDV;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PDVService {

    private PDVRepository repository;

    public PDVService(PDVRepository repository) {
        this.repository = repository;
    }

    public void save(PDV newPDV) {
        repository.save(newPDV);
    }

    public Optional<PDV> findById(Long id) {
        return repository.findById(id);
    }

    public Iterable<PDV> findAvaliablePDVs(Point point) {
        return repository.findNearestPDVs(point);
    }
}
