package com.sestevez;

import com.datastax.oss.quarkus.runtime.api.session.QuarkusCqlSession;
import com.sestevez.mapper.Creature;
import com.sestevez.mapper.CreatureDao;
import com.sestevez.mapper.CreatureDto;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.Optional;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
public class AstraDemoMapperTest {

    private final CreatureDto cassandra = new CreatureDto("Cassandra", 30, "Warrior");
    private final CreatureDto gandalf = new CreatureDto("Gandalf", 2019, "Warlock");

    @Inject
    QuarkusCqlSession session;

    @Inject
    CreatureDao dao;

    @BeforeEach
    void setUp() {
        session.execute("TRUNCATE creature");
    }

    @Test
    public void testGetCreatureEndpoint() {

        session.execute("INSERT INTO creature (name, age, type) VALUES ('Cassandra', 30, 'Warrior')");

        CreatureDto actual = given()
                .when().get("/creatures/Cassandra")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(CreatureDto.class);

        // then
        assertThat(actual).isEqualTo(cassandra);
    }

    @Test
    public void testGetAllCreaturesEndpoint() {

        session.execute("INSERT INTO creature (name, age, type) VALUES ('Cassandra', 30, 'Warrior')");
        session.execute("INSERT INTO creature (name, age, type) VALUES ('Gandalf', 2019, 'Warlock')");

        CreatureDto[] actual = given()
                .when().get("/creatures")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(CreatureDto[].class);

        // then
        assertThat(actual).contains(cassandra, gandalf);
    }

    @Test
    public void testAddCreatureEndpoint() {

        given()
                .body(cassandra)
                .contentType(ContentType.JSON)
                .when()
                .post("/creatures")
                .then()
                .statusCode(204);

        // then
        Optional<Creature> maybeCassandra = dao.findById("Cassandra");
        assertThat(maybeCassandra).contains(cassandra.toEntity());

    }

}