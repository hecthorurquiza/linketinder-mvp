package aczg.groovy.linketinder.domain.Company

import groovy.sql.Sql

class CompanyDAO {
    Sql sql

    CompanyDAO(Sql connection) {
        this.sql = connection
    }

    List<Object> create(Company company) {
        return sql.executeInsert(
            '''
                    INSERT INTO 
                    companies (name, cnpj, email, description, country, cep, password) 
                    VALUES (?, ?, ?, ?, ?, ?, ?)
                ''',
            [
                company.name,
                company.cnpj,
                company.email,
                company.description,
                company.country,
                company.cep,
                12345678
            ]
        )[0]
    }
}
