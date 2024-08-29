package aczg.groovy.linketinder

import aczg.groovy.linketinder.domain.Candidate
import aczg.groovy.linketinder.domain.Company

class Persistence {
    static List<Candidate> candidates
    static List<Company> companies

    static void loadCondidates() {
        candidates = List.of(
            new Candidate(
                    "John Doe",
                    "john.doe@example.com",
                    "California",
                    "12345-678",
                    "Highly motivated and experienced candidate",
                    List.of("Java", "C++", "Python"),
                    "123.456.789-01",
                    30
            ),
            new Candidate(
                    "Jane Smith",
                    "jane.smith@example.com",
                    "New York",
                    "98765-432",
                    "Results-driven and passionate candidate",
                    List.of("JavaScript", "React", "C++"),
                    "987.654.321-09",
                    28
            ),
            new Candidate(
                    "Bob Johnson",
                    "bob.johnson@example.com",
                    "Florida",
                    "55555-123",
                    "Experienced and skilled candidate",
                    List.of("Java", "Angular", "Agile"),
                    "555.123.456-78",
                    40
            ),
            new Candidate(
                    "Alice Brown",
                    "alice.brown@example.com",
                    "Texas",
                    "22222-901",
                    "Innovative and creative candidate",
                    List.of("Python", "C#", "SQL"),
                    "222.901.234-56",
                    32
            ),
            new Candidate(
                    "Mike Davis",
                    "mike.davis@example.com",
                    "Illinois",
                    "77777-567",
                    "Ambitious and driven candidate",
                    List.of("JavaScript", "Angular", "TypeScript"),
                    "777.567.890-12",
                    35
            )
        )
    }

    static void loadCompanies() {
        companies = List.of(
            new Company(
                    "Google Inc.",
                    "google@example.com",
                    "California",
                    "12345-678",
                    "Multinational technology company",
                    Arrays.asList("Artificial Intelligence", "Python", "Machine Learning"),
                    "12.345.678/0001-12",
                    "USA"
            ),
            new Company(
                    "Amazon Web Services",
                    "aws@example.com",
                    "Washington",
                    "98765-432",
                    "Cloud computing platform",
                    Arrays.asList("Cloud Computing", "JavaScript", "Cybersecurity"),
                    "98.765.432/0001-98",
                    "USA"
            ),
            new Company(
                    "Microsoft Corporation",
                    "microsoft@example.com",
                    "Washington",
                    "55555-123",
                    "Multinational technology company",
                    Arrays.asList("Software Development", "Cloud Computing", "Typescript"),
                    "55.555.555/0001-55",
                    "USA"
            ),
            new Company(
                    "IBM Corporation",
                    "ibm@example.com",
                    "New York",
                    "22222-901",
                    "Multinational technology company",
                    Arrays.asList("Artificial Intelligence", "Cloud Computing", "Data Analytics"),
                    "22.222.222/0001-22",
                    "USA"
            ),
            new Company(
                    "SAP SE",
                    "sap@example.com",
                    "Baden-Württemberg",
                    "77777-567",
                    "Multinational software company",
                    Arrays.asList("C++", "Cloud Computing", "Angular"),
                    "77.777.777/0001-77",
                    "Germany"
            )
        )
    }
}
