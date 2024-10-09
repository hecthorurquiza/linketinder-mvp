package aczg.groovy.linketinder

import aczg.groovy.linketinder.domain.Candidate.Candidate
import aczg.groovy.linketinder.domain.Company

import javax.management.InstanceAlreadyExistsException

class Persistence {
    List<Candidate> candidates
    List<Company> companies

    Persistence() {
        loadCandidates()
        loadCompanies()
    }

    private void loadCandidates() {
        candidates = new ArrayList<Candidate>( List.of(
                new Candidate(
                        "João Doe",
                        "joao.doe@example.com",
                        "Califórnia",
                        "12345-678",
                        "Candidato altamente motivado e experiente",
                        List.of("Java", "C++", "Python"),
                        "123.456.789-01",
                        30
                ),
                new Candidate(
                        "Joana Silva",
                        "joana.silva@example.com",
                        "Nova York",
                        "98765-432",
                        "Candidato orientado a resultados e apaixonado",
                        List.of("JavaScript", "React", "C++"),
                        "987.654.321-09",
                        28
                ),
                new Candidate(
                        "Roberto João",
                        "roberto.joao@example.com",
                        "Flórida",
                        "55555-123",
                        "Candidato experiente e habilidoso",
                        List.of("Java", "Angular", "Agile"),
                        "555.123.456-78",
                        40
                ),
                new Candidate(
                        "Alice Souza",
                        "alice.souza@example.com",
                        "Texas",
                        "22222-901",
                        "Candidato inovador e criativo",
                        List.of("Python", "C#", "SQL"),
                        "222.901.234-56",
                        32
                ),
                new Candidate(
                        "Miguel Dias",
                        "miguel.dias@example.com",
                        "Illinois",
                        "77777-567",
                        "Candidato ambicioso e motivado",
                        List.of("JavaScript", "Angular", "TypeScript"),
                        "777.567.890-12",
                        35
                )
        ) )
    }

    private void loadCompanies() {
        companies = new ArrayList<Company>( List.of(
                new Company(
                        "Google Inc.",
                        "google@example.com",
                        "Califórnia",
                        "12345-678",
                        "Empresa de tecnologia multinacional",
                        Arrays.asList("Inteligência Artificial", "Python", "Aprendizado de Máquina"),
                        "12.345.678/0001-12",
                        "EUA"
                ),
                new Company(
                        "Amazon Web Services",
                        "aws@example.com",
                        "Washington",
                        "98765-432",
                        "Plataforma de computação em nuvem",
                        Arrays.asList("Computação em Nuvem", "JavaScript", "Segurança Cibernética"),
                        "98.765.432/0001-98",
                        "EUA"
                ),
                new Company(
                        "Microsoft Corporation",
                        "microsoft@example.com",
                        "Washington",
                        "55555-123",
                        "Empresa de tecnologia multinacional",
                        Arrays.asList("Desenvolvimento de Software", "Computação em Nuvem", "Typescript"),
                        "55.555.555/0001-55",
                        "EUA"
                ),
                new Company(
                        "IBM Corporation",
                        "ibm@example.com",
                        "Nova York",
                        "22222-901",
                        "Empresa de tecnologia multinacional",
                        Arrays.asList("Inteligência Artificial", "Computação em Nuvem", "Análise de Dados"),
                        "22.222.222/0001-22",
                        "EUA"
                ),
                new Company(
                        "SAP SE",
                        "sap@example.com",
                        "Baden-Württemberg",
                        "77777-567",
                        "Empresa de software multinacional",
                        Arrays.asList("C++", "Computação em Nuvem", "Angular"),
                        "77.777.777/0001-77",
                        "Alemanha"
                )
        ) )
    }

    void saveCandidate(Candidate newCandidate) {
        for (Candidate candidate : candidates) {
            if (candidate.email == newCandidate.email) {
                throw new InstanceAlreadyExistsException("Já existe um candidato com este email")
            }
        }
        candidates.add(newCandidate)
    }

    void saveCompany(Company newCompany) {
        for (Company company : companies) {
            if (company.email == newCompany.email) {
                throw new InstanceAlreadyExistsException("Já existe uma empresa com este email")
            }
        }
        companies.add(newCompany)
    }
}
