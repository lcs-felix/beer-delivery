package me.lucasfelix.beerdelivery.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
public class RestTemplateUtils {

    @Autowired
    private TestRestTemplate restTemplate;

    public ResponseEntity<String> doRequest(String jsonPayload) {
        this.restTemplate = restTemplate;

        var headers = new HttpHeaders();
        headers.setContentType(APPLICATION_JSON);

        var httpEntity = new HttpEntity<>(jsonPayload, headers);

        return restTemplate.exchange(GraphQLTestUtils.ENDPOINT_LOCATION, HttpMethod.POST, httpEntity, String.class);
    }
}
