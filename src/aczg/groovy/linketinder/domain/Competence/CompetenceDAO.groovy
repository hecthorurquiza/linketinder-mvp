package aczg.groovy.linketinder.domain.Competence

import groovy.sql.GroovyRowResult
import groovy.sql.Sql

class CompetenceDAO {
    Sql sql

    CompetenceDAO(Sql connection) {
        this.sql = connection
    }

    Object createForCandidate(String name, int candidateId) {
        List<Object> created = sql.executeInsert("INSERT INTO competences (name) VALUES (?)", [name])[0]
        GroovyRowResult candidateDB = sql.firstRow("SELECT * FROM candidates WHERE id = ?", [candidateId])
        if (!candidateDB) {
            throw new RuntimeException("Candidato não encontrado")
        }

        List<Object> relation = sql.executeInsert(
                '''
            INSERT INTO
            candidate_competence (candidate_id, competence_id)
            VALUES (?, ?)
            ''',
                [candidateDB['id'], created.get(0)]
        )[0]

        return [name: created.get(1), candidateId: relation.get(0), competenceId: created.get(0)]
    }

    Object createForVacancy(String name, int vacancyId) {
        List<Object> created = sql.executeInsert("INSERT INTO competences (name) VALUES (?)", [name])[0]
        GroovyRowResult vacancyDB = sql.firstRow("SELECT * FROM vacancies WHERE id = ?", [vacancyId])
        if (!vacancyDB) {
            throw new RuntimeException("Vaga não encontrada")
        }

        List<Object> relation = sql.executeInsert(
                '''
            INSERT INTO
            vacancy_competence (vacancy_id, competence_id)
            VALUES (?, ?)
            ''',
                [vacancyDB['id'], created.get(0)]
        )[0]

        return [name: created.get(1), vacancyId: relation.get(0), competenceId: created.get(0)]
    }

    List<String> findCandidateCompetences(int candidateId) {
        List<String> competences = []

        List<Integer> competencesId = sql.rows(
                "SELECT competence_id FROM candidate_competence WHERE candidate_id = ?", [candidateId]
        ).collect(({ it['competence_id'] } as Closure<Integer>))

        for (int id in competencesId) {
            GroovyRowResult competenceDB = sql.firstRow("SELECT * FROM competences WHERE id = ?", [id])
            competences.add(competenceDB['name'] as String)
        }

        return competences
    }

    List<String> findVacancyCompetences(int vacancyId) {
        List<String> competences = []

        List<Integer> competencesId = sql.rows(
                "SELECT competence_id FROM vacancy_competence WHERE vacancy_id = ?", [vacancyId]
        ).collect(({ it['competence_id'] } as Closure<Integer>))

        for (int id in competencesId) {
            GroovyRowResult competenceDB = sql.firstRow("SELECT * FROM competences WHERE id = ?", [id])
            competences.add(competenceDB['name'] as String)
        }

        return competences
    }

    GroovyRowResult findById(int id) {
        return sql.firstRow("SELECT * FROM competences WHERE id = ?", [id])
    }

    int deleteById(int id) {
        sql.executeUpdate("DELETE FROM candidate_competence WHERE competence_id = ?", [id])
        sql.executeUpdate("DELETE FROM vacancy_competence WHERE competence_id = ?", [id])
        return sql.executeUpdate("DELETE FROM competences WHERE id = ?", [id])
    }

    int updateById(int id, String name) {
        return sql.executeUpdate("UPDATE competences SET name = ? WHERE id = ?", [name, id])
    }
}
