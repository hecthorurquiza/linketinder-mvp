package aczg.groovy.linketinder.repository

import groovy.sql.Sql

class DatabaseConn {
    private static Map dbConnParams = [
        url: 'jdbc:postgresql://localhost:5432/linketinder',
        user: 'gafanhoto',
        password: 'gafanhoto@aczg',
        driver: 'org.postgresql.Driver'
    ]

    static Sql newInstance(){
        return Sql.newInstance(dbConnParams.url, dbConnParams.user, dbConnParams.password, dbConnParams.driver)
    }
}
