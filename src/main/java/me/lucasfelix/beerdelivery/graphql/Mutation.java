package me.lucasfelix.beerdelivery.graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import me.lucasfelix.beerdelivery.model.PDV;
import me.lucasfelix.beerdelivery.model.dto.PDVInput;
import me.lucasfelix.beerdelivery.service.PDVService;
import org.springframework.stereotype.Component;

@Component
public class Mutation implements GraphQLMutationResolver {

    private final PDVService pdvService;

    public Mutation(PDVService pdvService) {
        this.pdvService = pdvService;
    }

    public PDV newPDV(PDVInput pdvInput) {
        var newPDV = pdvInput.toPDV();

        pdvService.save(newPDV);

        return newPDV;
    }
}
