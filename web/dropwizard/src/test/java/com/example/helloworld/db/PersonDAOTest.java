package com.example.helloworld.db;

import com.example.helloworld.core.Person;
import io.dropwizard.testing.junit.DAOTestRule;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class PersonDAOTest {

    @Rule
    public DAOTestRule daoTestRule = DAOTestRule.newBuilder()
        .addEntityClass(Person.class)
        .build();

    private PersonDAO personDAO;

    @Before
    public void setUp() throws Exception {
        personDAO = new PersonDAO(daoTestRule.getSessionFactory());
    }

    @Test
    public void createPerson() {
        final Person jeff = daoTestRule.inTransaction(() -> personDAO.create(new Person("Jeff", "Mon", 5, 7, "11/06/2016")));
        assertThat(jeff.getId()).isGreaterThan(0);
        assertThat(jeff.getName()).isEqualTo("Jeff");
        assertThat(jeff.getDay()).isEqualTo("Mon");
        assertThat(jeff.getStart()).isEqualTo(5);
        assertThat(jeff.getEnd()).isEqualTo(7);
        assertThat(jeff.getWeekStartDate()).isEqualTo("11/06/2016");
        assertThat(personDAO.findById(jeff.getId())).isEqualTo(Optional.of(jeff));
    }

    @Test
    public void findAll() {
        daoTestRule.inTransaction(() -> {
            personDAO.create(new Person("Jeff", "Mon", 5, 7, "11/06/2016"));
            personDAO.create(new Person("Ervin", "Tue", 5, 7, "11/07/2016"));
          //  personDAO.create(new Person("Randy", "The watchman"));
        });

        final List<Person> persons = personDAO.findAll();
        assertThat(persons).extracting("name").containsOnly("Jeff", "Ervin");
        assertThat(persons).extracting("day").containsOnly("Mon", "Tue");
        assertThat(persons).extracting("start").containsOnly(5, 5);
        assertThat(persons).extracting("end").containsOnly(7, 7);
        assertThat(persons).extracting("weekstartdate").containsOnly("11/06/2016", "11/07/2016");
    }

    
}
