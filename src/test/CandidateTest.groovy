package test

import aczg.groovy.linketinder.domain.Candidate.Candidate
import aczg.groovy.linketinder.domain.Candidate.CandidateDAO
import aczg.groovy.linketinder.repository.DatabaseConn
import groovy.sql.GroovyRowResult
import groovy.sql.Sql
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

import java.sql.SQLException
import java.time.LocalDate

import static org.junit.jupiter.api.Assertions.assertEquals

class CandidateTest {
    private static CandidateDAO candidateDAO;
    private static Sql connection;

    @BeforeAll
    static void setUp() {
        connection = DatabaseConn.newInstance();
        candidateDAO = new CandidateDAO(connection);
    }

    @AfterAll
    static void tearDown() {
        connection.close();
    }

    @Test
    void testCreateMethod() throws SQLException {
        Candidate candidate = new Candidate(
                "Ben",
                "Aflikan",
                LocalDate.of(1990, 3, 10),
                "aflikan@example.com",
                "12345678901",
                "Brazil",
                "12345-678",
                "Software Engineer with 5 years of experience"
        );
        List<Object> result = candidateDAO.create(candidate);
        assertEquals("Ben", result.get(1));
        assertEquals("Aflikan", result.get(2));
        assertEquals("1990-03-10", result.get(3).toString());
        assertEquals("aflikan@example.com", result.get(4));
        assertEquals("12345678901", result.get(5));
        assertEquals("Brazil", result.get(6));
        assertEquals("12345-678", result.get(7));
        assertEquals("Software Engineer with 5 years of experience", result.get(8));

        connection.execute("DELETE FROM candidates WHERE email = 'aflikan@example.com'");
    }

    @Test
    void testFindByEmailMethod() throws SQLException {
        Candidate candidate = new Candidate(
                "Ben",
                "Aflikan",
                LocalDate.of(1990, 3, 10),
                "aflikan@example.com",
                "12345678901",
                "Brazil",
                "12345-678",
                "Software Engineer with 5 years of experience"
        );
        candidateDAO.create(candidate);
        GroovyRowResult result = candidateDAO.findByEmail(candidate.getEmail());

        assertEquals("Ben", result['first_name']);
        assertEquals("Aflikan", result['last_name']);
        assertEquals("1990-03-10", result['birthday'].toString());
        assertEquals("aflikan@example.com", result['email']);
        assertEquals("12345678901", result['cpf']);
        assertEquals("Brazil", result['country']);
        assertEquals("12345-678", result['cep']);
        assertEquals("Software Engineer with 5 years of experience", result['description']);

        connection.execute("DELETE FROM candidates WHERE email = 'aflikan@example.com'");
    }

    @Test
    void testFindAllMethod() throws SQLException {
        Candidate candidate = new Candidate(
                "Ben",
                "Aflikan",
                LocalDate.of(1990, 3, 10),
                "aflikan@example.com",
                "12345678901",
                "Brazil",
                "12345-678",
                "Software Engineer with 5 years of experience"
        );
        candidateDAO.create(candidate);
        List<GroovyRowResult> result = candidateDAO.findAll();
        assertEquals(6, result.size());

        connection.execute("DELETE FROM candidates WHERE email = 'aflikan@example.com'");
    }

    @Test
    void testDeleteByEmailMethod() throws SQLException {
        Candidate candidate = new Candidate(
                "Ben",
                "Aflikan",
                LocalDate.of(1990, 3, 10),
                "aflikan@example.com",
                "12345678901",
                "Brazil",
                "12345-678",
                "Software Engineer with 5 years of experience"
        );
        candidateDAO.create(candidate);
        int deleted = candidateDAO.delete(candidate.getEmail());
        assertEquals(1, deleted);

        GroovyRowResult result = candidateDAO.findByEmail(candidate.getEmail());
        assertEquals(null, result);
    }

    @Test
    void testUpdateMethod() throws SQLException {
        Candidate candidate = new Candidate(
                "Ben",
                "Aflikan",
                LocalDate.of(1990, 3, 10),
                "aflikan@example.com",
                "12345678901",
                "Brazil",
                "12345-678",
                "Software Engineer with 5 years of experience"
        );
        candidateDAO.create(candidate);

        int updated = candidateDAO.update(
                candidate.getEmail(),
                "12345-679",
                "USA",
                "DataScience Engineer with 2 years of experience"
        );
        assertEquals(1, updated);

        GroovyRowResult result = candidateDAO.findByEmail(candidate.getEmail());
        assertEquals("12345-679", result['cep']);
        assertEquals("USA", result['country']);
        assertEquals("DataScience Engineer with 2 years of experience", result['description']);

        connection.execute("DELETE FROM candidates WHERE email = 'aflikan@example.com'");
    }
}