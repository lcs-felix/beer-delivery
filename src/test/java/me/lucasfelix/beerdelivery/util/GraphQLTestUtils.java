package me.lucasfelix.beerdelivery.util;

import com.fasterxml.jackson.core.io.JsonStringEncoder;
import com.fasterxml.jackson.core.util.BufferRecyclers;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UncheckedIOException;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
public class GraphQLTestUtils {

    public static final String ENDPOINT_LOCATION = "/graphql";

    @Autowired
    private String queryWrapper;

    private JsonStringEncoder jsonStringEncoder = BufferRecyclers.getJsonStringEncoder();

    public String createJsonQuery(String graphQL) {
        return queryWrapper.replace("__payload__", escapeQuery(graphQL));
    }

    public String createJsonMutation(String graphQL, String jsonVariables) {

        final String queryWithoutVariables = createJsonQuery(graphQL);

        if(jsonVariables == null) {
            return queryWithoutVariables;
        }

        return queryWithoutVariables.replace("null", jsonVariables);
    }

    private String escapeQuery(String graphQL) {
        return String.valueOf(jsonStringEncoder.quoteAsString(graphQL));
    }

    public JsonNode parse(String payload) {
        try {
            return new ObjectMapper().readTree(payload);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
