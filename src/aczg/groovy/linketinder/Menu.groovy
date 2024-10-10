package aczg.groovy.linketinder

import aczg.groovy.linketinder.domain.Candidate.Candidate
import aczg.groovy.linketinder.domain.Company.Company

import javax.management.InstanceAlreadyExistsException

class Menu {
    Scanner sc = new Scanner(System.in)
    Persistence persistence

    Menu(Persistence persistence) {
        this.persistence = persistence
    }

    void showMenu() {
        boolean stop = false

        while (!stop) {
            print("\n1. Listar candidatos\n2. Listar empresas\n3. Cadastrar candidato\n" +
                    "4. Cadastrar empresa\n\n0. Sair\n\n-> ")

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
                    showCandidates()
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

    private void showCandidates() {
        this.persistence.getCandidates().forEach { println "$it----------------------" }
    }

    private void showCompanies() {
        this.persistence.getCompanies().forEach { println "$it----------------------" }
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

    private void registerCandidate() {
        List<String> form = ["Nome", "Email", "Estado", "CEP", "Descrição", "Competências (separadas por virgula)",
                             "CPF", "Idade"]
        List<String> candidateData = showRegisterForm(form)
        Candidate newCandidate = new Candidate(
                candidateData[0].trim(),
                candidateData[1].trim(),
                candidateData[2].trim(),
                candidateData[3].trim(),
                candidateData[4].trim(),
                candidateData[5].trim().split(",").collect { str -> str.trim() }.toList(),
                candidateData[6].trim(),
                Integer.parseInt(candidateData[7].trim()))
        try {
            this.persistence.saveCandidate(newCandidate)
            println "\nCandidato cadastrado com sucesso!\n"
        }
        catch (InstanceAlreadyExistsException ex) {
            println "\nJá existe um candidato com este email"
        }
    }

    private void registerCompany() {
        List<String> form = ["Nome", "Email", "Estado", "CEP", "Descrição", "Competências (separadas por virgula)",
                             "CNPJ", "País"]
        List<String> companyData = showRegisterForm(form)
        Company newCompany = new Company(
                companyData[0].trim(),
                companyData[1].trim(),
                companyData[2].trim(),
                companyData[3].trim(),
                companyData[4].trim(),
                companyData[5].trim().split(",").collect { str -> str.trim() }.toList(),
                companyData[6].trim(),
                companyData[7].trim())
        try {
            this.persistence.saveCompany(newCompany)
            println "\nEmpresa cadastrada com sucesso!\n"
        }
        catch (InstanceAlreadyExistsException ex) {
            println "\nJá existe uma empresa com este email"
        }
    }
}
