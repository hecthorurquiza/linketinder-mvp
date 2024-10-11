package test

import aczg.groovy.linketinder.domain.Company.Company
import aczg.groovy.linketinder.domain.Company.CompanyDAO
import aczg.groovy.linketinder.repository.DatabaseConn
import groovy.sql.GroovyRowResult
import groovy.sql.Sql
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

import java.sql.SQLException

import static org.junit.jupiter.api.Assertions.assertEquals

class CompanyTest {
    private static CompanyDAO companyDAO;
    private static Sql connection;

    @BeforeAll
    static void setUp() {
        connection = DatabaseConn.newInstance();
        companyDAO = new CompanyDAO(connection);
    }

    @AfterAll
    static void tearDown() {
        connection.close();
    }

    @Test
    void testCreateMethod() throws SQLException {
        Company company = new Company(
                "ABC Corporation",
                "12.345.678/0001-93",
                "contact@abccorp.com",
                "We are a leading provider of innovative solutions.",
                "Brazil",
                "12345-678"
        );
        List<Object> result = companyDAO.create(company);
        assertEquals("ABC Corporation", result.get(1));
        assertEquals("12.345.678/0001-93", result.get(2));
        assertEquals("contact@abccorp.com", result.get(3));
        assertEquals("We are a leading provider of innovative solutions.", result.get(4));
        assertEquals("Brazil", result.get(5));
        assertEquals("12345-678", result.get(6));

        connection.execute("DELETE FROM companies WHERE email = 'contact@abccorp.com'");
    }

    @Test
    void testFindByEmailMethod() throws SQLException {
        Company company = new Company(
                "ABC Corporation",
                "12.345.678/0001-93",
                "contact@abccorp.com",
                "We are a leading provider of innovative solutions.",
                "Brazil",
                "12345-678"
        );
        companyDAO.create(company);
        GroovyRowResult result = companyDAO.findByEmail(company.getEmail());

        assertEquals("ABC Corporation", result['name']);
        assertEquals("12.345.678/0001-93", result['cnpj']);
        assertEquals("contact@abccorp.com", result['email']);
        assertEquals("We are a leading provider of innovative solutions.", result['description']);
        assertEquals("Brazil", result['country']);
        assertEquals("12345-678", result['cep']);

        connection.execute("DELETE FROM companies WHERE email = 'contact@abccorp.com'");
    }

    @Test
    void testDeleteByEmailMethod() throws SQLException {
        Company company = new Company(
                "ABC Corporation",
                "12.345.678/0001-93",
                "contact@abccorp.com",
                "We are a leading provider of innovative solutions.",
                "Brazil",
                "12345-678"
        );
        companyDAO.create(company);
        int deleted = companyDAO.delete(company.getEmail());
        assertEquals(1, deleted);

        GroovyRowResult result = companyDAO.findByEmail(company.getEmail());
        assertEquals(null, result);
    }

    @Test
    void testUpdateMethod() throws SQLException {
        Company company = new Company(
                "ABC Corporation",
                "12.345.678/0001-93",
                "contact@abccorp.com",
                "We are a leading provider of innovative solutions.",
                "Brazil",
                "12345-678"
        );
        companyDAO.create(company);

        int updated = companyDAO.update(
                company.getEmail(),
                "New company for digital inovations",
                "12345-679",
                "USA"
        );
        assertEquals(1, updated);

        GroovyRowResult result = companyDAO.findByEmail(company.getEmail());
        assertEquals("New company for digital inovations", result['description']);
        assertEquals("12345-679", result['cep']);
        assertEquals("USA", result['country']);

        connection.execute("DELETE FROM companies WHERE email = 'contact@abccorp.com'");
    }
}
