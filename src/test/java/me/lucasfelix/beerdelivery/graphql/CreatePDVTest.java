package me.lucasfelix.beerdelivery.graphql;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.TextNode;
import me.lucasfelix.beerdelivery.util.GraphQLTestUtils;
import me.lucasfelix.beerdelivery.util.RestTemplateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CreatePDVTest {

    @Autowired
    private String createPDVMutationPayload;

    @Autowired
    private String createPDVVariables;

    @Autowired
    private GraphQLTestUtils graphQLTestUtils;

    @Autowired
    private RestTemplateUtils restTemplateUtils;

    @Test
    public void givenAPDVPayload_whenCallMutation_thenCreateNewPDV() {

        var jsonMutation = graphQLTestUtils.createJsonMutation(createPDVMutationPayload, createPDVVariables);

        var response = restTemplateUtils.doRequest(jsonMutation);

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.OK);

        var parsedResponse = graphQLTestUtils.parse(response.getBody());

        assertThat(parsedResponse)
                .isNotNull();

        assertThat(parsedResponse.get("data"))
                .isNotNull();

        assertThat(parsedResponse.get("data").get("newPDV"))
                .isNotNull();

        assertThat(parsedResponse.get("data").get("newPDV").get("document"))
                .isNotNull()
                .isEqualTo(new TextNode("1434562120945/0003"));

        assertThat(parsedResponse.get("errors"))
                .isNull();
    }
}
