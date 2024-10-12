package test

import aczg.groovy.linketinder.domain.Competence.CompetenceDAO
import aczg.groovy.linketinder.domain.Vacancy.Vacancy
import aczg.groovy.linketinder.domain.Vacancy.VacancyDAO
import aczg.groovy.linketinder.repository.DatabaseConn
import groovy.sql.GroovyRowResult
import groovy.sql.Sql
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

import java.sql.SQLException

import static org.junit.jupiter.api.Assertions.assertEquals

class CompetenceTest {
    private static CompetenceDAO competenceDAO;
    private static Sql connection;
    private static VacancyDAO vacancyDAO;

    @BeforeAll
    static void setUp() {
        connection = DatabaseConn.newInstance();
        competenceDAO = new CompetenceDAO(connection);
        vacancyDAO = new VacancyDAO(connection);
    }

    @AfterAll
    static void tearDown() {
        connection.close();
    }

    @Test
    void testCreateForCandidateMethod() throws SQLException {
        Object result = competenceDAO.createForCandidate("Java", 1);
        assertEquals("Java", result['name']);
        assertEquals(1, result['candidateId']);

        connection.execute("DELETE FROM candidate_competence WHERE competence_id = ?", [result['competenceId']]);
        connection.execute("DELETE FROM competences WHERE id = ?", [result['competenceId']]);
    }

    @Test
    void testCreateForVacancyMethod() throws SQLException {
        Vacancy vacancy = new Vacancy(
            "Software Engineer",
            "We are looking for a software engineer to join our team.",
            "São Paulo",
            "São Paulo"
        )
        List<Object> created =  vacancyDAO.create(vacancy, 2);
        Object result = competenceDAO.createForVacancy("Java", created.get(0) as int);
        assertEquals("Java", result['name']);
        assertEquals(created.get(0), result['vacancyId']);

        connection.execute("DELETE FROM vacancy_competence WHERE competence_id = ?", [result['competenceId']]);
        connection.execute("DELETE FROM competences WHERE id = ?", [result['competenceId']]);
        connection.execute("DELETE FROM vacancies WHERE id = ?", [created.get(0)]);
    }

    @Test
    void testFindCandidateCompetencesMethod() {
        competenceDAO.createForCandidate("Java", 1);
        competenceDAO.createForCandidate("Python", 1);
        List<Object> result = competenceDAO.findCandidateCompetences(1);

        assertEquals("Java", result.get(0));
        assertEquals("Python", result.get(1));

        connection.execute("DELETE FROM candidate_competence WHERE candidate_id = ?", [1]);
        connection.execute("DELETE FROM competences");
    }

    @Test
    void testFindVacancyCompetencesMethod() {
        Vacancy vacancy = new Vacancy(
            "Software Engineer",
            "We are looking for a software engineer to join our team.",
            "São Paulo",
            "São Paulo"
        )
        List<Object> created =  vacancyDAO.create(vacancy, 2);
        competenceDAO.createForVacancy("Java", created.get(0) as int);
        competenceDAO.createForVacancy("Python", created.get(0) as int);
        List<Object> result = competenceDAO.findVacancyCompetences(created.get(0) as int);

        assertEquals("Java", result.get(0));
        assertEquals("Python", result.get(1));

        connection.execute("DELETE FROM vacancy_competence WHERE vacancy_id = ?", [created.get(0)]);
        connection.execute("DELETE FROM competences");
    }

    @Test
    void testFindAllMethod() {
        competenceDAO.createForCandidate("Java", 1);
        competenceDAO.createForCandidate("Python", 1);

        Vacancy vacancy = new Vacancy(
            "Software Engineer",
            "We are looking for a software engineer to join our team.",
            "São Paulo",
            "São Paulo"
        )
        List<Object> created =  vacancyDAO.create(vacancy, 2);
        competenceDAO.createForVacancy("CI/CD", created.get(0) as int);

        Object result = competenceDAO.findAll();
        assertEquals(3, result.size());

        connection.execute("DELETE FROM candidate_competence");
        connection.execute("DELETE FROM vacancy_competence");
        connection.execute("DELETE FROM competences");
        connection.execute("DELETE FROM vacancies");
    }

    @Test
    void testFindByIdMethod() {
        Object created = competenceDAO.createForCandidate("Java", 1);
        GroovyRowResult result = competenceDAO.findById(created['competenceId'] as int);

        assertEquals("Java", result['name']);

        connection.execute("DELETE FROM candidate_competence");
        connection.execute("DELETE FROM competences");
    }

    @Test
    void testDeleteByIdMethod() {
        Vacancy vacancy = new Vacancy(
            "Software Engineer",
            "We are looking for a software engineer to join our team.",
            "São Paulo",
            "São Paulo"
        )
        List<Object> created = vacancyDAO.create(vacancy, 2);
        Object result1 = competenceDAO.createForVacancy("Java", created.get(0) as int);
        Object result2 = competenceDAO.createForCandidate("Python", 1);

        int deleted1 = competenceDAO.delete(result1['competenceId'] as int);
        assertEquals(1, deleted1);

        int deleted2 = competenceDAO.delete(result2['competenceId'] as int);
        assertEquals(1, deleted2);
    }

    @Test
    void testUpdateByIdMethod() {
        Object created = competenceDAO.createForCandidate("Java", 1);
        assertEquals("Java", created['name']);

        int result = competenceDAO.update(created['competenceId'] as int, "Python");
        assertEquals(1, result);

        GroovyRowResult resultDB = competenceDAO.findById(created['competenceId'] as int);
        assertEquals("Python", resultDB['name']);
        assertEquals(created['competenceId'], resultDB['id']);

        connection.execute("DELETE FROM candidate_competence WHERE competence_id = ?", [created['competenceId']]);
        connection.execute("DELETE FROM competences");
    }
}
