# O projeto

Api Rest para controle de empréstimos de livros desenvolvido orientado a tdd com spring-boot e java e rodando os testes unitários e integrados com Junit e Mockito.


## Começando

Para executar o projeto, será necessário instalar os seguintes programas:

- [JDK 8: Necessário para executar o projeto Java](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html)
- [Maven 3.8.1: Necessário para realizar o build do projeto Java](http://mirror.nbtelecom.com.br/apache/maven/maven-3/3.8.1/source/apache-maven-3.8.1-src.zip)
- [Spring Tools 4 for Eclipse: Para desenvolvimento do projeto](https://download.springsource.com/release/STS4/4.10.0.RELEASE/dist/e4.19/spring-tool-suite-4-4.10.0.RELEASE-e4.19.0-win32.win32.x86_64.self-extracting.jar) 

## Construção (Build)

Para construir o projeto com o Maven, executar os comando abaixo:

- mvn clean install

O comando irá baixar todas as dependências do projeto e criar um diretório target com os artefatos construídos, que incluem o arquivo jar do projeto. Além disso, serão executados os testes unitários, e se algum falhar, o Maven exibirá essa informação no console.

## Testes

Para rodar os testes, utilize o comando abaixo:

 - mvn test
 
## Contribuições

Contribuições são sempre bem-vindas! 
Para contribuir lembre-se sempre de adicionar testes unitários para as novas classes com a devida documentação.
