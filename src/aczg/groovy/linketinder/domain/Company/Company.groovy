package aczg.groovy.linketinder.domain.Company

class Company {
    String name
    String cnpj
    String email
    String description
    String country
    String cep
    List<String> competences

    Company(
        String name,
        String cnpj,
        String email,
        String description,
        String country,
        String cep
    ){
        this.name = name
        this.cnpj = cnpj
        this.email = email
        this.description = description
        this.country = country
        this.cep = cep
        this.competences = []
    }
}
