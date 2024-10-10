package test

import aczg.groovy.linketinder.domain.Vacancy.Vacancy
import aczg.groovy.linketinder.domain.Vacancy.VacancyDAO
import aczg.groovy.linketinder.repository.DatabaseConn
import groovy.sql.Sql
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

import javax.management.InstanceAlreadyExistsException
import java.sql.SQLException

import static org.junit.jupiter.api.Assertions.assertEquals
import static org.junit.jupiter.api.Assertions.assertThrows

class VacancyTest {
    private static VacancyDAO vacancyDAO;
    private static Sql connection;

    @BeforeAll
    static void setUp() {
        connection = DatabaseConn.newInstance();
        vacancyDAO = new VacancyDAO(connection);
    }

    @AfterAll
    static void tearDown() {
        connection.close();
    }

    @Test
    void testCreateMethod() throws SQLException {
        Vacancy vacancy = new Vacancy(
                "Software Engineer",
                "We are looking for a software engineer to join our team.",
                "São Paulo",
                "Brazil"
        );
        List<Object> result = vacancyDAO.create(vacancy, 2);
        assertEquals("Software Engineer", result.get(1));
        assertEquals("We are looking for a software engineer to join our team.", result.get(2));
        assertEquals("São Paulo", result.get(3));
        assertEquals("Brazil", result.get(4));
        assertEquals(2, result.get(5));

        connection.execute("DELETE FROM vacancies WHERE id = ?", [result.get(0)]);
    }

    @Test
    void testFailWhenCompanyIdIsInvalid() throws SQLException {
        Vacancy vacancy = new Vacancy(
                "Software Engineer",
                "We are looking for a software engineer to join our team.",
                "São Paulo",
                "Brazil"
        );
        assertThrows(InstanceAlreadyExistsException.class, () -> {
           vacancyDAO.create(vacancy, 100);
        });
    }
}
