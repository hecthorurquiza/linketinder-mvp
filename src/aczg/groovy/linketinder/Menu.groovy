package aczg.groovy.linketinder

import aczg.groovy.linketinder.domain.Candidate.Candidate
import aczg.groovy.linketinder.domain.Candidate.CandidateDAO
import aczg.groovy.linketinder.domain.Company.Company
import aczg.groovy.linketinder.domain.Company.CompanyDAO
import aczg.groovy.linketinder.domain.Competence.CompetenceDAO
import aczg.groovy.linketinder.domain.Vacancy.VacancyDAO
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
//            print("\n1. Listar candidatos\n2. Listar empresas\n3. Cadastrar candidato\n" +
//                    "4. Cadastrar empresa\n\n0. Sair\n\n-> ")

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
                    registerCandidate()
                    break
                case 4:
                    registerCompany()
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
}
