package aczg.groovy.linketinder.domain.Vacancy

import groovy.sql.GroovyRowResult
import groovy.sql.Sql

import javax.management.InstanceAlreadyExistsException

class VacancyDAO {
    Sql sql

    VacancyDAO(Sql connection) {
        this.sql = connection
    }

    List<Object> create(Vacancy vacancy, int companyId) {
        GroovyRowResult companyDB = sql.firstRow('SELECT * FROM companies WHERE id = ?', [companyId])
        if (!companyDB) {
            throw new InstanceAlreadyExistsException("Company not found")
        }

        return sql.executeInsert(
            '''
                    INSERT INTO 
                    vacancies (name, description, state, city, company_id) 
                    VALUES (?, ?, ?, ?, ?)
                ''',
            [
                vacancy.name,
                vacancy.description,
                vacancy.state,
                vacancy.city,
                companyDB['id']
            ]
        )[0]
    }
}
