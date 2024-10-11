package aczg.groovy.linketinder

import aczg.groovy.linketinder.domain.Candidate.Candidate
import aczg.groovy.linketinder.domain.Candidate.CandidateDAO
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
                    showCompanies()
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

    private List<String> showRegisterForm(List<String> formFields) {
        List<String> formData = []
        println "Preencha as seguintes informações:"
        for (int i = 0; i < formFields.size(); i++) {
            print "${formFields[i]}: "
            String data = sc.nextLine()
            if (!data.isBlank()) {
                formData.add(data)
            }
            else {
                println("Input inválido para o campo ${formFields[i]}. Tente novamente.\n")
                i--
            }
        }
        return formData
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
        }
        else {
            println("Erro ao cadastrar candidato")
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
            println "Candidato deletado com sucesso"
        }
        else {
            println "Nenhum candidato foi deletado"
        }
    }

    private void updateCandidate() {
        print "Informe o email do usuário que deseja atualizar: "
        String email = sc.nextLine()
        List<String> labels = ["CEP", "País", "Descrição"]
        List<String> data = showRegisterForm(labels)
        int count = this.candidateDAO.update(email, data[0].trim(), data[1].trim(), data[2].trim())

        if (count == 1) {
            println "Candidato atualizado com sucesso"
        }
        else {
            println "Nenhum candidato foi atualizado"
        }
    }

//    private void showCandidates() {
//        this.persistence.getCandidates().forEach { println "$it----------------------" }
//    }
//
//    private void showCompanies() {
//        this.persistence.getCompanies().forEach { println "$it----------------------" }
//    }
//

//
//    private void registerCandidate() {
//        List<String> form = ["Nome", "Email", "Estado", "CEP", "Descrição", "Competências (separadas por virgula)",
//                             "CPF", "Idade"]
//        List<String> candidateData = showRegisterForm(form)
//        Candidate newCandidate = new Candidate(
//                candidateData[0].trim(),
//                candidateData[1].trim(),
//                candidateData[2].trim(),
//                candidateData[3].trim(),
//                candidateData[4].trim(),
//                candidateData[5].trim().split(",").collect { str -> str.trim() }.toList(),
//                candidateData[6].trim(),
//                Integer.parseInt(candidateData[7].trim()))
//        try {
//            this.persistence.saveCandidate(newCandidate)
//            println "\nCandidato cadastrado com sucesso!\n"
//        }
//        catch (InstanceAlreadyExistsException ex) {
//            println "\nJá existe um candidato com este email"
//        }
//    }
//
//    private void registerCompany() {
//        List<String> form = ["Nome", "Email", "Estado", "CEP", "Descrição", "Competências (separadas por virgula)",
//                             "CNPJ", "País"]
//        List<String> companyData = showRegisterForm(form)
//        Company newCompany = new Company(
//                companyData[0].trim(),
//                companyData[1].trim(),
//                companyData[2].trim(),
//                companyData[3].trim(),
//                companyData[4].trim(),
//                companyData[5].trim().split(",").collect { str -> str.trim() }.toList(),
//                companyData[6].trim(),
//                companyData[7].trim())
//        try {
//            this.persistence.saveCompany(newCompany)
//            println "\nEmpresa cadastrada com sucesso!\n"
//        }
//        catch (InstanceAlreadyExistsException ex) {
//            println "\nJá existe uma empresa com este email"
//        }
//
}
