import aczg.groovy.linketinder.domain.Candidate.Candidate
import aczg.groovy.linketinder.domain.Candidate.CandidateDAO
import aczg.groovy.linketinder.repository.DatabaseConn
import groovy.sql.Sql

import java.text.SimpleDateFormat
import java.time.LocalDate

static void main(String[] args) {
  Sql connection = DatabaseConn.newInstance()
  CandidateDAO candidateDAO = new CandidateDAO(connection)
  Candidate candidate = new Candidate(
          "John",
          "Whick",
          LocalDate.of(1990, 3, 10),
          "babayaga@example.com",
          "12345678901",
          "Brazil",
          "12345-678",
          "Software Engineer with 5 years of experience"
  );
  def result = candidateDAO.create(candidate)
  def result2 = candidateDAO.findByEmail("babayaga@example.com")
  print(result2[0])
}