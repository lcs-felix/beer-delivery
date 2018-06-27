package me.lucasfelix.beerdelivery;

import me.lucasfelix.beerdelivery.repository.PDVRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BeerDeliveryApplicationIntegrationTest {

    @Autowired
    private PDVRepository pdvRepository;

	@Test
	public void givenAValidId_should() {
	}

}
