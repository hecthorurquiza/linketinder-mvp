//package test.domain;
//
//import aczg.groovy.linketinder.domain.Candidate.Candidate;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//class CandidateTest {
//    private static Candidate candidate;
//
//    @BeforeEach
//    void setUp() {
//         candidate = new Candidate(
//                "João Doe",
//                "joao.doe@example.com",
//                "Califoronnia",
//                "12345-678",
//                "Candidato altamente motivado e experiente",
//                List.of("Java", "C++", "Python"),
//                "123.456.789-01",
//                30
//        );
//    }
//
//    @Test
//    void checkCandidateInfos() {
//        assertEquals("João Doe", candidate.getName());
//        assertEquals("joao.doe@example.com", candidate.getEmail());
//        assertEquals("Califoronnia", candidate.getState());
//        assertEquals("12345-678", candidate.getCep());
//        assertEquals("Candidato altamente motivado e experiente", candidate.getDescription());
//        assertEquals(List.of("Java", "C++", "Python"), candidate.getCompetences());
//        assertEquals("123.456.789-01", candidate.getCpf());
//        assertEquals(30, candidate.getAge());
//    }
//
//    @Test
//    void newCpfAttribution() {
//        candidate.setCpf("111.111.111-11");
//        assertEquals("111.111.111-11", candidate.getCpf());
//    }
//
//
//    @Test
//    void newAgeAttribution() {
//        candidate.setAge(25);
//        assertEquals(25, candidate.getAge());
//    }
//}