package test

import aczg.groovy.linketinder.domain.Vacancy.Vacancy
import aczg.groovy.linketinder.domain.Vacancy.VacancyDAO
import aczg.groovy.linketinder.repository.DatabaseConn
import groovy.sql.GroovyRowResult
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
                "São Paulo",
        );
        List<Object> result = vacancyDAO.create(vacancy, 2);
        assertEquals("Software Engineer", result.get(1));
        assertEquals("We are looking for a software engineer to join our team.", result.get(2));
        assertEquals("São Paulo", result.get(3));
        assertEquals("São Paulo", result.get(4));
        assertEquals(2, result.get(5));

        connection.execute("DELETE FROM vacancies WHERE id = ?", [result.get(0)]);
    }

    @Test
    void testFailWhenCompanyIdIsInvalid() throws SQLException {
        Vacancy vacancy = new Vacancy(
                "Software Engineer",
                "We are looking for a software engineer to join our team.",
                "São Paulo",
                "São Paulo"
        );
        assertThrows(InstanceAlreadyExistsException.class, () -> {
           vacancyDAO.create(vacancy, 100);
        });
    }

    @Test
    void testFindVacanciesByNameMethod() {
        Vacancy vacancy1 = new Vacancy(
                "Software Engineer",
                "We are looking for a software engineer to join our team.",
                "São Paulo",
                "São Paulo"
        )
        Vacancy vacancy2 = new Vacancy(
                "Software Developer",
                "We are looking for a software developer to join our team.",
                "São Paulo",
                "São Paulo"
        )

        List<Object> created1 =  vacancyDAO.create(vacancy1, 2);
        List<Object> created2 =  vacancyDAO.create(vacancy2, 2);

        List<GroovyRowResult> result = vacancyDAO.findByName("Software");
        assertEquals(2, result.size());

        connection.execute("DELETE FROM vacancies WHERE id = ?", [created1.get(0)]);
        connection.execute("DELETE FROM vacancies WHERE id = ?", [created2.get(0)]);
    }
    
    @Test
    void testFindVacancyByIdMethod() throws SQLException {
        Vacancy vacancy = new Vacancy(
                "Software Engineer",
                "We are looking for a software engineer to join our team.",
                "São Paulo",
                "São Paulo"
        );
        List<Object> created = vacancyDAO.create(vacancy, 2);
        GroovyRowResult result = vacancyDAO.findById(Integer.parseInt(created.get(0).toString()));

        assertEquals("Software Engineer", result['name']);
        assertEquals("We are looking for a software engineer to join our team.", result['description']);
        assertEquals("São Paulo", result['state']);
        assertEquals("São Paulo", result['city']);
        assertEquals(2, result['company_id']);

        connection.execute("DELETE FROM vacancies WHERE id = ?", [created.get(0)]);
    }

    @Test
    void testDeleteVacancyByIdMethod() throws SQLException {
        Vacancy vacancy = new Vacancy(
                "Software Engineer",
                "We are looking for a software engineer to join our team.",
                "São Paulo",
                "São Paulo"
        );
        List<Object> created = vacancyDAO.create(vacancy, 2);
        int result = vacancyDAO.delete(Integer.parseInt(created.get(0).toString()));
        assertEquals(1, result);

        GroovyRowResult vacancyDB = vacancyDAO.findById(Integer.parseInt(created.get(0).toString()))
        assertEquals(null, vacancyDB);
    }

    @Test
    void testUpdateVacancyByIdMethod() throws SQLException {
        Vacancy vacancy = new Vacancy(
                "Software Engineer",
                "We are looking for a software engineer to join our team.",
                "São Paulo",
                "São Paulo"
        );
        List<Object> created = vacancyDAO.create(vacancy, 2);
        int result = vacancyDAO.update(
            Integer.parseInt(created.get(0).toString()),
            "Software Developer",
            "It's your chance to be a part of our team.",
            "Colorado",
            "California"
        );
        assertEquals(1, result);

        GroovyRowResult resultDB = vacancyDAO.findById(Integer.parseInt(created.get(0).toString()));
        assertEquals("Software Developer", resultDB['name']);
        assertEquals("It's your chance to be a part of our team.", resultDB['description']);
        assertEquals("Colorado", resultDB['state']);
        assertEquals("California", resultDB['city']);

        connection.execute("DELETE FROM vacancies WHERE id = ?", [created.get(0)]);
    }
}
