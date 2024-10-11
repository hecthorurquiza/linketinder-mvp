package aczg.groovy.linketinder.domain.Candidate

import java.time.LocalDate

class Candidate {

    String firstName
    String lastName
    LocalDate birthday
    String email
    String cpf
    String country
    String cep
    String description
    List<String> competences

    Candidate(
            String firstName,
            String lastName,
            LocalDate birthday,
            String email,
            String cpf,
            String country,
            String cep,
            String description
    ) {
        this.firstName = firstName
        this.lastName = lastName
        this.birthday = birthday
        this.email = email
        this.cpf = cpf
        this.country = country
        this.cep = cep
        this.description = description
        this.competences = []
    }


    @Override
    String toString() {
        return "Nome completo: $firstName $lastName\nNascimento: $birthday\nEmail: $email\nCPF: $cpf\nPaís: $country" +
                "\nCEP: $cep\nDescricão: $description\nCompetências: $competences"
    }
}
