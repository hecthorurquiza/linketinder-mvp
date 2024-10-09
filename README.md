**Linketinder MVP - by Heithor Urquiza Silva**
------------

Linketinder é um sistema baseado em Groovy, projetado para conectar candidatos e empresas de uma forma única e inovadora. O Linketinder tem como objetivo revolucionar a forma como as pessoas encontram oportunidades de trabalho e como as empresas descobrem talentos de alto nível.

**Funcionalidades**
------------

### Funcionalidades Implementadas

* **Registro**: Candidatos e empresas podem se registrar fornecendo seus dados.
* **Leitura**: Candidatos e empresas registrados podem ser listados.

**Requisitos Técnicos**
-------------------------

* **Linguagem**: Groovy
* **Execução**: Java Virtual Machine (JVM)
* **Banco de Dados**: Postgres (um banco de dados Postgres pode ser criado para testes utilizando um arquivo `docker-compose`)

**Executando o Programa**
-------------------------

Para executar o programa, siga estes passos:

1. **Clonar o repositório**: Clone o repositório mvp-linketinder-groovy usando o Git.
2. **Navegar até o diretório do projeto**: Navegue até o diretório do projeto no seu terminal.
3. **Compilar o programa**: Compile o programa Groovy usando o seguinte comando:
```bash
groovyc *.groovy
```
4. **Executar o programa**: Execute o programa usando o seguinte comando:
```bash
groovy Main
```
5. **Usar o programa**: Utilize o programa selecionando as opções no menu:

```
1. Listar candidatos
2. Listar empresas
3. Registrar candidato
4. Registrar empresa

0. Sair

->
```

**Modelo Entidade-Relacionamento (DER)**
-------------------------

[Lucidchart DER Linketinder](https://lucid.app/lucidchart/29450b35-7207-48f5-93d1-9ee8553c7639/edit?viewport_loc=-5108%2C-1568%2C2219%2C1045%2C0_0&invitationId=inv_164f1118-18b3-43cf-806b-81405cefdca9)
