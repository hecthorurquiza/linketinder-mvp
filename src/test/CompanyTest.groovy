package test

import aczg.groovy.linketinder.domain.Company.Company
import aczg.groovy.linketinder.domain.Company.CompanyDAO
import aczg.groovy.linketinder.repository.DatabaseConn
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
}
