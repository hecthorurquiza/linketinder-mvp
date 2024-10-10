package aczg.groovy.linketinder.domain.Company

import groovy.sql.GroovyRowResult
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

    GroovyRowResult findByEmail(String email) {
        return sql.firstRow('SELECT * FROM companies WHERE email = ?', [email])
    }

    int deleteByEmail(String email) {
        return sql.executeUpdate('DELETE FROM companies WHERE email = ?', [email])
    }

    int update(String email, String description, String cep, String country) {
        return sql.executeUpdate(
            '''
                UPDATE companies SET description = ?, cep = ?, country = ? WHERE email = ?
            ''',
            [description, cep, country, email]
        )
    }

}
