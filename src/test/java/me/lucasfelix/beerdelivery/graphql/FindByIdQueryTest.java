package me.lucasfelix.beerdelivery.graphql;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.TextNode;
import me.lucasfelix.beerdelivery.util.GraphQLTestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:data.sql")
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class FindByIdQueryTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private String findPDVByIdPayload;

    @Autowired
    private GraphQLTestUtils graphQLTestUtils;

    @Test
    public void givenAQuery_whenFindPDVById_thenReturnResponse() {

        var payload = graphQLTestUtils.createJsonQuery(findPDVByIdPayload);

        var headers = new HttpHeaders();
        headers.setContentType(APPLICATION_JSON);

        var httpEntity = new HttpEntity<>(payload, headers);

        var response =
                restTemplate.exchange(GraphQLTestUtils.ENDPOINT_LOCATION, HttpMethod.POST, httpEntity, String.class);

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.OK);

        JsonNode parsedResponse = graphQLTestUtils.parse(response.getBody());

        assertThat(parsedResponse)
                .isNotNull();

        assertThat(parsedResponse.get("data"))
                .isNotNull();

        assertThat(parsedResponse.get("data").get("findPDVById"))
                .isNotNull();

        assertThat(parsedResponse.get("data").get("findPDVById").get("document"))
                .isNotNull()
                .isEqualTo(new TextNode("02.453.716/000170"));

        assertThat(parsedResponse.get("errors")).isNull();
    }
}