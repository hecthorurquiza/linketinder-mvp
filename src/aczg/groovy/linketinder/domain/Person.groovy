package aczg.groovy.linketinder.domain

abstract class Person {
    String name
    String email
    String state
    String cep
    String description
    List<String> competences

    Person(String name, String email, String state, String cep, String description, List<String> competences) {
        this.name = name
        this.email = email
        this.state = state
        this.cep = cep
        this.description = description
        this.competences = competences
    }
}
