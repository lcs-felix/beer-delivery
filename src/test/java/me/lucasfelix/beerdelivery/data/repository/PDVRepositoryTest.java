package me.lucasfelix.beerdelivery.data.repository;

import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import me.lucasfelix.beerdelivery.model.PDV;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
@ActiveProfiles("test")
public class PDVRepositoryTest {

    @Autowired
    private PDVRepository pdvRepository;

    @Autowired
    private TestEntityManager manager;

    @Test
    public void givenAValidId_whenFinById_thenShouldReturnPDV() {

        MultiPolygon coverageArea = null;
        Point point = null;

        try {
            coverageArea = (MultiPolygon) new WKTReader().read(
                    "MULTIPOLYGON(((-39.147419929504395 -7.24837674958899, " +
                            "-39.14748430252075 -7.250484077831977, -39.14527416229248 -7.250601151334155, -39.144737720489495 " +
                            "-7.248727971647482, -39.147419929504395 -7.24837674958899)))");

            point = (Point) new WKTReader().read("POINT(-39.14636850357056 -7.249590060993639)");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        var newPDV = new PDV("Bar Lagoa", "Gabriel Jesus", "91081267/0006", coverageArea, point);

        manager.persist(newPDV);

        var optionalPdv = pdvRepository.findById(newPDV.getId());

        assertThat(optionalPdv).isPresent();

        assertThat(optionalPdv).contains(newPDV);
    }
}