package me.lucasfelix.beerdelivery.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;

@Configuration
public class GraphQLTestConfiguration {

    @Value("classpath:graphql/query-wrapper.json")
    private Resource queryWrapperFile;

    @Value("classpath:graphql/pdv/query/find-pdv-by-id.graphql")
    private Resource findPDVByIdPayload;

    @Value("classpath:graphql/pdv/query/find-nearest-pdvs.graphql")
    private Resource findNearestPDVsPayload;

    @Value("classpath:graphql/pdv/mutation/create-pdv.graphql")
    private Resource createPDVMutationPayload;

    @Value("classpath:graphql/pdv/mutation/variables-create-pdv.json")
    private Resource createPDVVariables;

    @Bean
    public String findPDVByIdPayload() {
        return copyToString(findPDVByIdPayload);
    }

    @Bean
    public String findNearestPDVsPayload() {
        return copyToString(findNearestPDVsPayload);
    }

    @Bean
    public String queryWrapper() {
        return copyToString(queryWrapperFile);
    }

    @Bean
    public String createPDVMutationPayload() {
        return copyToString(createPDVMutationPayload);
    }

    @Bean
    public String createPDVVariables() {
        return copyToString(createPDVVariables);
    }

    private String copyToString(Resource payload) {
        try {
            return StreamUtils.copyToString(payload.getInputStream(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
