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

//
//    @Override
//    String toString() {
//        return "Nome: $name\nEmail: $email\nEstado: $state\nCEP: $cep\nDescrição: $description\n" +
//                "Competências: $competences\nCPF: $cpf\nIdade: $age\n"
//    }
}
