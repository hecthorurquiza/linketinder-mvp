package aczg.groovy.linketinder

import aczg.groovy.linketinder.domain.Candidate.Candidate
import aczg.groovy.linketinder.domain.Candidate.CandidateDAO
import aczg.groovy.linketinder.domain.Company.Company
import aczg.groovy.linketinder.domain.Company.CompanyDAO
import aczg.groovy.linketinder.domain.Competence.CompetenceDAO
import aczg.groovy.linketinder.domain.Vacancy.Vacancy
import aczg.groovy.linketinder.domain.Vacancy.VacancyDAO
import groovy.sql.GroovyRowResult
import groovy.sql.Sql

import java.time.LocalDate

class Menu {
    Scanner sc = new Scanner(System.in)

    CandidateDAO candidateDAO
    CompanyDAO companyDAO
    CompetenceDAO competenceDAO
    VacancyDAO vacancyDAO

    Menu(Sql connection) {
        this.candidateDAO = new CandidateDAO(connection)
        this.companyDAO = new CompanyDAO(connection)
        this.competenceDAO = new CompetenceDAO(connection)
        this.vacancyDAO = new VacancyDAO(connection)
    }

    void showMenu() {
        boolean stop = false

        while (!stop) {
            print("Escolha um modelo para ver as ações disponíveis: \n1. Candidato \n2. Empresa \n3. Competências" +
                    "\n4. Vagas \n0. Sair \n\n-> ")

            int option = 0
            try {
                option = sc.nextInt()
                sc.nextLine()
            } catch (InputMismatchException ex) {
                println "Opção inválida. Digite um valor inteiro.\n"
                sc.next()
                continue
            }

            switch (option) {
                case 1:
                    showCandidateActions()
                    break
                case 2:
                    showCompanyActions()
                    break
                case 3:
                    showCompetenceActions()
                    break
                case 4:
                    showVacancyActions()
                    break
                case 0:
                    stop = true
                    break
            }
        }
    }

    private List<String> showRegisterForm(List<String> formFields) {
        List<String> formData = []
        println "Preencha as seguintes informações:"
        for (int i = 0; i < formFields.size(); i++) {
            print "${formFields[i]}: "
            String data = sc.nextLine()
            if (!data.isBlank()) {
                formData.add(data)
            } else {
                println("Input inválido para o campo ${formFields[i]}. Tente novamente.\n")
                i--
            }
        }
        return formData
    }

//    Candidate
    private void showCandidateActions() {
        boolean stop = false

        while (!stop) {
            print("\n\n1. Cadastrar candidato \n2. Listar candidatos \n3. Deletar candidato \n4. Atualizar candidato" +
                    "\n0. Voltar \n\n-> ")

            int option = 0
            try {
                option = sc.nextInt()
                sc.nextLine()
            } catch (InputMismatchException ex) {
                println "Opção inválida. Digite um valor inteiro.\n"
                sc.next()
                continue
            }

            switch (option) {
                case 1:
                    createCandidate()
                    break
                case 2:
                    showCandidates()
                    break
                case 3:
                    deleteCandidate()
                    break
                case 4:
                    updateCandidate()
                    break
                case 0:
                    stop = true
                    break
            }
        }
    }

    private void createCandidate() {
        List<String> labels = ["Primeiro nome", "Sobrenome", "Nascimento", "Email", "CPF", "País", "CEP", "Descrição"]
        List<String> data = showRegisterForm(labels)
        Candidate newCandidate = new Candidate(
                data[0].trim(),
                data[1].trim(),
                LocalDate.parse(data[2].trim()),
                data[3].trim(),
                data[4].trim(),
                data[5].trim(),
                data[6].trim(),
                data[7].trim()
        )
        List<Object> created = this.candidateDAO.create(newCandidate)

        if (created.size() > 0) {
            println("** Candidato criado com sucesso **")
        } else {
            println("!! Erro ao cadastrar candidato !!")
        }
    }

    private void showCandidates() {
        List<Candidate> candidates = []
        this.candidateDAO.findAll().forEach { it ->
            Candidate candidate = new Candidate(
                    it['first_name'] as String,
                    it['last_name'] as String,
                    LocalDate.parse(it['birthday'] as String),
                    it['email'] as String,
                    it['cpf'] as String,
                    it['country'] as String,
                    it['cep'] as String,
                    it['description'] as String
            )
            List<String> competences = competenceDAO.findCandidateCompetences(it['id'] as int)
            candidate.setCompetences(competences)
            candidates.add(candidate)
        }

        candidates.forEach { print "$it\n------------------------\n" }
        print("\n")
    }

    private void deleteCandidate() {
        print "Informe o email do usuário que deseja deletar: "
        String email = sc.nextLine()
        int count = this.candidateDAO.delete(email)
        if (count == 1) {
            println "** Candidato deletado com sucesso **"
        } else {
            println "!! Nenhum candidato foi deletado !!"
        }
    }

    private void updateCandidate() {
        print "Informe o email do usuário que deseja atualizar: "
        String email = sc.nextLine()
        List<String> labels = ["CEP", "País", "Descrição"]
        List<String> data = showRegisterForm(labels)
        int count = this.candidateDAO.update(email, data[0].trim(), data[1].trim(), data[2].trim())

        if (count == 1) {
            println "** Candidato atualizado com sucesso **"
        } else {
            println "!! Nenhum candidato foi atualizado !!"
        }
    }

//    Company
    private void showCompanyActions() {
        boolean stop = false

        while (!stop) {
            print("\n\n1. Cadastrar empresa \n2. Listar empresas \n3. Deletar empresa \n4. Atualizar empresa" +
                    "\n0. Voltar \n\n-> ")

            int option = 0
            try {
                option = sc.nextInt()
                sc.nextLine()
            } catch (InputMismatchException ex) {
                println "Opção inválida. Digite um valor inteiro.\n"
                sc.next()
                continue
            }

            switch (option) {
                case 1:
                    createCompany()
                    break
                case 2:
                    showCompanies()
                    break
                case 3:
                    deleteCompany()
                    break
                case 4:
                    updateCompany()
                    break
                case 0:
                    stop = true
                    break
            }
        }
    }

    private void createCompany() {
        List<String> labels = ["Nome", "CNPJ", "Email", "Descrição", "País", "CEP"]
        List<String> data = showRegisterForm(labels)
        Company newCompany = new Company(
            data[0].trim() as String,
            data[1].trim() as String,
            data[2].trim() as String,
            data[3].trim() as String,
            data[4].trim() as String,
            data[5].trim() as String
        )
        List<Object> created = this.companyDAO.create(newCompany)

        if (created.size() > 0) {
            println("** Empresa registrada com sucesso **")
        } else {
            println("!! Erro ao cadastrar empresa !!")
        }
    }

    private void showCompanies() {
        List<Company> companies = []
        this.companyDAO.findAll().forEach { it ->
            Company company = new Company(
                    it['name'] as String,
                    it['cnpj'] as String,
                    it['email'] as String,
                    it['description'] as String,
                    it['country'] as String,
                    it['cep'] as String
            )
            companies.add(company)
        }

        companies.forEach { print "$it\n------------------------\n" }
        print("\n")
    }

    private void deleteCompany() {
        print "Informe o email da empresa que deseja deletar: "
        String email = sc.nextLine()
        int count = this.companyDAO.delete(email)
        if (count == 1) {
            println "** Empresa deletada com sucesso **"
        } else {
            println "!! Nenhuma empresa foi deletada !!"
        }
    }

    private void updateCompany() {
        print "Informe o email da empresa que deseja atualizar: "
        String email = sc.nextLine()
        List<String> labels = ["CEP", "País", "Descrição"]
        List<String> data = showRegisterForm(labels)
        int count = this.companyDAO.update(email, data[0].trim(), data[1].trim(), data[2].trim())

        if (count == 1) {
            println "** Empresa atualizada com sucesso **"
        } else {
            println "!! Nenhuma empresa foi atualizada !!"
        }
    }

//    Competence
    private void showCompetenceActions() {
        boolean stop = false

        while (!stop) {
            print("\n\n1. Cadastrar competência(s) para um candidato \n2. Cadastrar competência(s) para uma vaga" +
                    "\n3. Listar competências existentes \n4. Deletar competência \n5. Atualizar competência" +
                    "\n0. Voltar \n\n-> ")

            int option = 0
            try {
                option = sc.nextInt()
                sc.nextLine()
            } catch (InputMismatchException ex) {
                println "Opção inválida. Digite um valor inteiro.\n"
                sc.next()
                continue
            }

            switch (option) {
                case 1:
                    createCompetenceForCandidate()
                    break
                case 2:
                    createCompetenceForVacancy()
                    break
                case 3:
                    showCompetences()
                    break
                case 4:
                    deleteCompetence()
                    break
                case 5:
                    updateCompetence()
                    break
                case 0:
                    stop = true
                    break
            }
        }
    }

    private void createCompetenceForCandidate() {
        print "Informe o email do candidato: "
        String email = sc.nextLine()
        GroovyRowResult candidate = candidateDAO.findByEmail(email)

        print "Informe uma ou mais competência(s) separadas por (,): "
        String input = sc.nextLine()
        List<String> competences = input.split(",")

        int count = 0
        for (String competence : competences) {
            Object created = competenceDAO.createForCandidate(competence.trim(), candidate['id'] as int)
            if (created['competenceId']) {
                count++
            }
        }

        if (count == competences.size()) {
            println "** Competências cadastradas com sucesso **"
        } else {
            println "!! Falha no cadastro de competências !!"
        }
    }

    private void createCompetenceForVacancy() {
        print "Informe o id da vaga: "
        int id = sc.nextLine().toInteger()
        GroovyRowResult vacancy = vacancyDAO.findById(id)

        print "Informe uma ou mais competência(s) separadas por (,): "
        String input = sc.nextLine()
        List<String> competences = input.split(",")

        int count = 0
        for (String competence : competences) {
            Object created = competenceDAO.createForVacancy(competence.trim(), vacancy['id'] as int)
            if (created['competenceId']) {
                count++
            }
        }

        if (count == competences.size()) {
            println "** Competências cadastradas com sucesso **"
        } else {
            println "!! Falha no cadastro de competências !!"
        }
    }

    private void showCompetences() {
        List<GroovyRowResult> competences = competenceDAO.findAll()

        print "Competências: \n--------------------\n"
        competences.forEach { println it['name']}
        print("--------------------\n")
    }

    private void deleteCompetence() {
        print "Informe o id da competencia que deseja deletar: "
        int id = sc.nextLine().toInteger()
        int count = competenceDAO.delete(id)
        if (count == 1) {
            println "** Competência deletada com sucesso **"
        } else {
            println "!! Nenhuma competência foi deletada !!"
        }
    }

    private void updateCompetence() {
        print "Informe o id da competência que deseja atualizar: "
        int id = sc.nextLine().toInteger()

        print "Informe a nova competência: "
        String name = sc.nextLine()
        int count = competenceDAO.update(id, name)
        if (count == 1) {
            println "** Competência atualizada com sucesso **"
        } else {
            println "!! Nenhuma competência foi atualizada !!"
        }
    }

//    Vacancy
    private void showVacancyActions() {
        boolean stop = false

        while (!stop) {
            print("\n\n1. Cadastrar vaga \n2. Procurar vaga \n3. Deletar vaga \n4. Atualizar vaga" +
                    "\n0. Voltar \n\n-> ")

            int option = 0
            try {
                option = sc.nextInt()
                sc.nextLine()
            } catch (InputMismatchException ex) {
                println "Opção inválida. Digite um valor inteiro.\n"
                sc.next()
                continue
            }

            switch (option) {
                case 1:
                    createVacancy()
                    break
                case 2:
                    searchVacancy()
                    break
                case 3:
                    deleteVacancy()
                    break
                case 4:
                    updateVacancy()
                    break
                case 0:
                    stop = true
                    break
            }
        }
    }

    private void createVacancy() {
        print "Insira o email da empresa responsável pela vaga: "
        String companyEmail = sc.nextLine()
        GroovyRowResult company = this.companyDAO.findByEmail(companyEmail)

        List<String> labels = ["Nome", "Descrição", "Estado", "Cidade"]
        List<String> data = showRegisterForm(labels)
        Vacancy newVacancy = new Vacancy(
                data[0].trim() as String,
                data[1].trim() as String,
                data[2].trim() as String,
                data[3].trim() as String
        )
        List<Object> created = this.vacancyDAO.create(newVacancy, company['id'] as int)


        if (created.size() > 0) {
            println("** Vaga registrada com sucesso para a empresa ${company['name']} **")
        } else {
            println("!! Erro ao cadastrar vaga !!")
        }
    }

    private void searchVacancy() {
        print "Insiro o nome da vaga(s) que deseja pesquisar: "
        String name = sc.nextLine()
        List<Vacancy> vacancies = []
        this.vacancyDAO.findByName(name).forEach { it ->
            Vacancy vacancy = new Vacancy(
                    it['name'] as String,
                    it['description'] as String,
                    it['state'] as String,
                    it['city'] as String
            )
            List<String> competences = competenceDAO.findVacancyCompetences(it['id'] as int)
            vacancy.setCompetences(competences)
            vacancies.add(vacancy)
        }

        if (vacancies.size() > 0) {
            println("Vagas encontradas:")
            vacancies.forEach { print "$it\n------------------------\n" }
        } else {
            println("!! Nenhuma vaga encontrada !!")
        }
    }

    private void deleteVacancy() {
        print "Insiro o id da vaga que deseja deletar: "
        int id = sc.nextInt()
        sc.nextLine()
        int count = this.vacancyDAO.delete(id)
        if (count == 1) {
            println "** Vaga deletada com sucesso **"
        } else {
            println "!! Nenhuma vaga foi deletada !!"
        }
    }

    private void updateVacancy() {
        print "Insiro o id da vaga que deseja atualizar: "
        int id = sc.nextInt()
        sc.nextLine()
        List<String> labels = ["Nome", "Descrição", "Estado", "Cidade"]
        List<String> data = showRegisterForm(labels)
        int count = this.vacancyDAO.update(id, data[0].trim(), data[1].trim(), data[2].trim(), data[3].trim())

        if (count == 1) {
            println "** Vaga atualizada com sucesso **"
        } else {
            println "!! Nenhuma vaga foi atualizada !!"
        }
    }
}
