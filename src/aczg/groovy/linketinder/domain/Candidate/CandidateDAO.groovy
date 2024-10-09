package aczg.groovy.linketinder.domain.Candidate

import aczg.groovy.linketinder.repository.DatabaseConn
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


//
//        def sql
//
//        MyDAO() {
//            sql = Sql.newInstance(dbUrl, username, password, driver)
//        }
//
//        def findAll() {
//            def results = sql.rows('SELECT * FROM mytable')
//            results
//        }
//
//        def findById(id) {
//            def result = sql.firstRow('SELECT * FROM mytable WHERE id = ?', [id])
//            result
//        }
//
//        def create(data) {
//            sql.executeInsert('INSERT INTO mytable (name, email) VALUES (?, ?)', [data.name, data.email])
//        }
//
//        def update(id, data) {
//            sql.executeUpdate('UPDATE mytable SET name = ?, email = ? WHERE id = ?', [data.name, data.email, id])
//        }
//
//        def delete(id) {
//            sql.executeUpdate('DELETE FROM mytable WHERE id = ?', [id])
//        }
//
//        def close() {
//            sql.close()
//        }
//    }
}
