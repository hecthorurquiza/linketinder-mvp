package test;

import aczg.groovy.linketinder.Persistence;
import aczg.groovy.linketinder.domain.Candidate;
import aczg.groovy.linketinder.domain.Company;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import javax.management.InstanceAlreadyExistsException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersistenceTest {
    private Persistence persistence;

    @BeforeEach
    void setUp() {
        persistence = new Persistence();
    }

    @Test
    void loadCandidatesOnConstructor() {
        assertEquals(5, persistence.getCandidates().size());
    }

    @Test
    void loadCompaniesOnConstructor() {
        assertEquals(5, persistence.getCompanies().size());
    }

    @Test
    void saveCandidateMustNoThrowAnException() {
        Candidate newCandidate = new Candidate(
                "Joaquim",
                "joaquim.doe@example.com",
                "Califoronnia",
                "12345-678",
                "Candidato altamente motivado e experiente",
                List.of("Java", "C++", "Python"),
                "123.456.789-01",
                30
        );
        assertDoesNotThrow(() -> persistence.saveCandidate(newCandidate));
        assertEquals(6, persistence.getCandidates().size());
    }

    @Test
    void saveCandidateMustThrowAnExceptionForSameEmail() {
        Candidate newCandidate = new Candidate(
                "Joaquim",
                "joao.doe@example.com",
                "Califoronnia",
                "12345-678",
                "Candidato altamente motivado e experiente",
                List.of("Java", "C++", "Python"),
                "123.456.789-01",
                30
        );
        assertThrows(InstanceAlreadyExistsException.class, () -> persistence.saveCandidate(newCandidate));
        assertEquals(5, persistence.getCandidates().size());
    }

    @Test
    void saveCompanyMustNoThrowAnException() {
        Company newCompany = new Company(
                "HBO MAX",
                "hbo@example.com",
                "Washington",
                "98765-432",
                "Plataforma de computação em nuvem",
                Arrays.asList("Computação em Nuvem", "JavaScript", "Segurança Cibernética"),
                "98.765.432/0001-98",
                "EUA"
        );
        assertDoesNotThrow(() -> persistence.saveCompany(newCompany));
        assertEquals(6, persistence.getCompanies().size());
    }

    @Test
    void saveCompanyMustThrowAnExceptionForSameEmail() {
        Company newCompany = new Company(
                "HBO MAX",
                "aws@example.com",
                "Washington",
                "98765-432",
                "Plataforma de computação em nuvem",
                Arrays.asList("Computação em Nuvem", "JavaScript", "Segurança Cibernética"),
                "98.765.432/0001-98",
                "EUA"
        );
        assertThrows(InstanceAlreadyExistsException.class, () -> persistence.saveCompany(newCompany));
        assertEquals(5, persistence.getCompanies().size());
    }
}