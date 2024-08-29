package aczg.groovy.linketinder.domain

class Company extends Person {
    String cnpj
    String country

    Company(String name, String email, String state, String cep, String description, List<String> competences,
            String cnpj, String country) {
        super(name, email, state, cep, description, competences)
        this.cnpj = cnpj
        this.country = country
    }

    @Override
    String toString() {
        return "Name: ${name}\nEmail: ${email}\nState: ${state}\nCep: ${cep}\nDescription: ${description}\n" +
                "Competences: ${competences}\nCNPJ: ${cnpj}\nCountry: ${country}\n"
    }
}
