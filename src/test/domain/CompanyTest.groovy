//package test.domain;
//
//import aczg.groovy.linketinder.domain.Company.Company;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.Arrays;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class CompanyTest {
//    private static Company company;
//
//    @BeforeEach
//    void setUp() throws Exception {
//        company = new Company(
//                "Google Inc.",
//                "google@example.com",
//                "Califórnia",
//                "12345-678",
//                "Empresa de tecnologia multinacional",
//                Arrays.asList("Inteligência Artificial", "Python", "Aprendizado de Máquina"),
//                "12.345.678/0001-12",
//                "EUA"
//        );
//    }
//
//    @Test
//    void checkCompanyInfos() {
//        assertEquals("Google Inc.", company.getName());
//        assertEquals("google@example.com", company.getEmail());
//        assertEquals("Califórnia", company.getState());
//        assertEquals("12345-678", company.getCep());
//        assertEquals("Empresa de tecnologia multinacional", company.getDescription());
//        assertEquals(Arrays.asList("Inteligência Artificial", "Python", "Aprendizado de Máquina"), company.getCompetences());
//        assertEquals("12.345.678/0001-12", company.getCnpj());
//        assertEquals("EUA", company.getCountry());
//    }
//
//    @Test
//    void newCNPJAttribution() {
//        company.setCnpj("11.111.111/0001-11");
//        assertEquals("11.111.111/0001-11", company.getCnpj());
//    }
//
//    @Test
//    void newCountryAttribution() {
//        company.setCountry("Brasil");
//        assertEquals("Brasil", company.getCountry());
//    }
//}