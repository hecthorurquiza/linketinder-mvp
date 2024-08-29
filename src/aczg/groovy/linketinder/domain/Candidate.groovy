package aczg.groovy.linketinder.domain

class Candidate extends Person {
    String cpf
    int age

    Candidate(String name, String email, String state, String cep, String description, List<String> competences,
              String cpf, int age) {
        super(name, email, state, cep, description, competences)
        this.cpf = cpf
        this.age = age
    }
}
