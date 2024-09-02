package aczg.groovy.linketinder

import aczg.groovy.linketinder.domain.Candidate
import aczg.groovy.linketinder.domain.Company

class Menu {
    Scanner sc = new Scanner(System.in)

    void showMenu() {
        boolean stop = false
        Persistence.loadCandidates()
        Persistence.loadCompanies()

        while (!stop) {
            print("\n1. Listar candidatos\n2. Listar empresas\n3. Cadastrar candidato\n" +
                    "4. Cadastrar empresa\n\n0. Sair\n\n-> ")

            int option = 0
            try {
                option = sc.nextInt()
                println()
                sc.nextLine()
            } catch (InputMismatchException ex) {
                println "Opção inválida. Digite um valor inteiro.\n"
                sc.next()
                continue
            }

            switch (option) {
                case 1:
                    Persistence.candidates.forEach { println "$it----------------------" }
                    break
                case 2:
                    Persistence.companies.forEach { println "$it----------------------" }
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
        Persistence.candidates.add(newCandidate)
        println "Candidato cadastrado com sucesso!\n"
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
        Persistence.companies.add(newCompany)
        println "Empresa cadastrada com sucesso!\n"
    }
}
