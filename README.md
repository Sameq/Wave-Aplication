# Wave-Application
Projeto Wave - Divulgação de Eventos
## Descrição:
O projeto Wave é uma aplicação desenvolvida em Spring Boot que permite a divulgação de eventos. Ele utiliza a API da Twilio para enviar mensagens pelo WhatsApp e a funcionalidade de e-mail do JavaMailSender para enviar e-mails. Além disso, o projeto faz uso de um banco de dados MySQL para armazenamento de dados.

## Pré-requisitos:
- Java Development Kit (JDK) 8 ou superior
- Maven
- MySQL Server
- Spring Boot
- Conta na Twilio
- JavaEmail

## Config properties:
- spring.datasource.url=jdbc:mysql://localhost:3306/wave
- spring.datasource.username=root
- spring.datasource.password=
- spring.jpa.hibernate.ddl-auto=update
- spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
### config twilio:
- twilio.accountSid=<your Sid Twilio>
- twilio.authToken=<your Token Twilio>
- twilio.phoneNumber=<number Twilio>

### config EmailSend:
- spring.mail.host=<yout host>
- spring.mail.port=587
- pring.mail.username=<your userName>
- spring.mail.password=<your userName>
- spring.mail.properties.mail.smtp.auth=true
- spring.mail.properties.mail.smtp.starttls.enable=true

## Instalação e Configuração
Faça o clone deste repositório para o seu ambiente local.
```bash
  git clone https://github.com/Sameq/Wave-Aplication
```
- Certifique-se de ter o MySQL Server instalado e em execução em sua máquina.
Importe o projeto em sua IDE de preferência (como IntelliJ IDEA ou Eclipse).
Abra o arquivo application.properties na pasta src/main/resources e configure as informações de acesso ao banco de dados MySQL, bem como as credenciais da Twilio e as configurações de e-mail SMTP.
Compile o projeto usando o Maven: mvn clean install.
Execute o projeto Spring Boot: mvn spring-boot:run.
Uso
Após a execução bem-sucedida do projeto, você poderá acessar os endpoints da aplicação para enviar mensagens pelo WhatsApp e/ou e-mails, conforme necessário para a divulgação de eventos. Certifique-se de usar os endpoints corretos e fornecer os dados necessários para o envio das mensagens.

Contribuição
Contribuições são bem-vindas! Se você deseja contribuir para o projeto, siga estas etapas:

Faça um fork do projeto.
Crie uma branch para sua nova funcionalidade ou correção de bug: git checkout -b feature/nova-funcionalidade ou git checkout -b bugfix/correcao.
Faça suas alterações e commit: git commit -am 'Adiciona nova funcionalidade'.
Faça push para a branch: git push origin feature/nova-funcionalidade.
Abra um pull request para revisão.
