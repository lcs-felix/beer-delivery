package me.lucasfelix.beerdelivery.graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import me.lucasfelix.beerdelivery.data.repository.PDVRepository;
import me.lucasfelix.beerdelivery.model.PDV;
import me.lucasfelix.beerdelivery.model.dto.PDVInput;
import org.springframework.stereotype.Component;

@Component
public class Mutation implements GraphQLMutationResolver {

    private PDVRepository pdvRepository;

    public Mutation(PDVRepository pdvRepository) {
        this.pdvRepository = pdvRepository;
    }

    public PDV newPDV(PDVInput pdvInput) {

        return pdvInput.toPDV();
    }
}
