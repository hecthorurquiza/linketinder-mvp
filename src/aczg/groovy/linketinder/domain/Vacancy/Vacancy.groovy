package aczg.groovy.linketinder.domain.Vacancy

class Vacancy {
    String name
    String description
    String state
    String city

    Vacancy(
        String name,
        String description,
        String state,
        String city
    ){
        this.name = name
        this.description = description
        this.state = state
        this.city = city
    }

    @Override
    String toString() {
        return "$name ($state, $city) -> $description"
    }
}
