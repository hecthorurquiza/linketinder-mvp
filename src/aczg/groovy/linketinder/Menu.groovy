package aczg.groovy.linketinder

class Menu {
    Scanner sc = new Scanner(System.in)

    void showMenu() {
        boolean stop = false
        Persistence.loadCondidates()
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
                case 0:
                    stop = true
                    break
            }
        }
    }


}
