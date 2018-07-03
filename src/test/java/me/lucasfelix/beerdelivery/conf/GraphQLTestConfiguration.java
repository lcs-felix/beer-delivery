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

    @Value("classpath:graphql/pdv/find-pdv-by-id.graphql")
    private Resource findPDVByIdPayload;

    @Bean
    public String findPDVByIdPayload() {
        return copyToString(findPDVByIdPayload);
    }

    @Bean
    public String queryWrapper() {
        return copyToString(queryWrapperFile);
    }

    private String copyToString(Resource payload) {
        try {
            return StreamUtils.copyToString(payload.getInputStream(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
