package me.lucasfelix.beerdelivery.data.repository;

import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import me.lucasfelix.beerdelivery.model.PDV;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
@ActiveProfiles("test")
@Sql(scripts = "classpath:data.sql")
public class PDVRepositoryTest {

    @Autowired
    private PDVRepository pdvRepository;

    private PDV pdvAdegaOsasco;
    private PDV pdvBarLegal;

    @Before
    public void setUp() {
        pdvAdegaOsasco = new PDV();
        pdvAdegaOsasco.setDocument("02.453.716/000170");

        pdvBarLegal = new PDV();
        pdvBarLegal.setDocument("20.053.623/0001-30");
    }

    @Test
    public void givenAPointThatIsCoveredByOneArea_whenfindAvaliablePDVs_returnTheNearestPDV() {
        var aPoint =  createPoint("POINT(-43.3025 -23.0138)");
        var pdvs = pdvRepository.findAvaliablePDVs(aPoint);

        assertThat(pdvs)
                .hasSize(1)
                .contains(pdvAdegaOsasco);
    }

    @Test
    public void givenAPointThatNotIsCoveredByOneArea_whenfindAvaliablePDVs_returnZeroPoints() {
        var aPoint =  createPoint("POINT(-43.194 -23.958)");

        var pdvs = pdvRepository.findAvaliablePDVs(aPoint);

        assertThat(pdvs)
                .hasSize(0);
    }

    @Test
    public void givenAPointThatIsCoveredByManyAreas_whenfindAvaliablePDVs_returnPDVs() {
        var aPoint =  createPoint("POINT(-43.32898 -22.97055)");

        var pdvs = pdvRepository.findAvaliablePDVs(aPoint);

        assertThat(pdvs)
                .hasSize(2)
                .containsExactly(pdvBarLegal, pdvAdegaOsasco);
    }

    private Point createPoint(String wktPoint) {
        try {
            return (Point) new WKTReader().read(wktPoint);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}