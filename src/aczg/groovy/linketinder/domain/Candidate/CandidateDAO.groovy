package aczg.groovy.linketinder.domain.Candidate

import groovy.sql.GroovyRowResult
import groovy.sql.Sql

class CandidateDAO {
    Sql sql

    CandidateDAO(Sql connection) {
        this.sql = connection
    }

    List<Object> create(Candidate candidate) {
        return sql.executeInsert(
            '''
                    INSERT INTO 
                    candidates (first_name, last_name, birthday, email, cpf, country, cep, description, password) 
                    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
                ''',
            [
                candidate.firstName,
                candidate.lastName,
                candidate.birthday,
                candidate.email,
                candidate.cpf,
                candidate.country,
                candidate.cep,
                candidate.description,
                12345678
            ]
        )[0]
    }

    GroovyRowResult findByEmail(String email) {
        return sql.firstRow('SELECT * FROM candidates WHERE email = ?', [email])
    }

    List<GroovyRowResult> findAll() {
        return sql.rows('SELECT * FROM candidates')
    }

    int delete(String email) {
        return sql.executeUpdate('DELETE FROM candidates WHERE email = ?', [email])
    }

    int update(String email, String cep, String country, String description) {
        return sql.executeUpdate(
                'UPDATE candidates SET cep = ?, country = ?, description = ? WHERE email = ?',
                [cep, country, description, email]
        )
    }

}
