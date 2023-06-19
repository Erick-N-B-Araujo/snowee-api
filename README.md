# Spring-boot 2.4.4 API
aa
Repositório destinado a API  de backend da Snowee  Gamecorp.

[TOC]

## Conteúdos

### 1. Configurações do Spring-boot

Os arquivos a seguir encontram-se em /src/main/resources/.

#### 1.1 Application.properties

Aqui temos duas configurações:

```yml
spring.profiles.active=prod
spring.application.name=Snowee Gamecorp Spring boot API
```

spring.profiles.active: define o perfil ativo para executar.

spring.application.name: define o nome da aplicação.

#### 1.2 application.yml

A seguir, as configurações base do spring:

##### 1.2.1 app.message

```yml
app:
  message: This is the property file to the ${spring.application.name} specific for PROD environment

```

Configure o app.message para exibir uma mensagem sempre que o spring-boot iniciar.

##### 1.2.2 spring

```yml
spring:
```

Tag pai para iniciar a declaração dos addons do spring.

###### 1.2.2.1 hikari

```yml
  hikari:
    connection-test-query: SELECT 1
    minimum-idle: 1
    maximum-pool-size: 10
    pool-name: snowee-database
    auto-commit: FALSE
    connection-timeout: 30000
```

Hikari é o driver que gerencia as conexões ao banco de dados, aumentando sua performance.

###### 1.2.2.2 datasource

```yml
datasource:
    url: jdbc:postgresql://database/dbsnowee
    driverClassName: org.postgresql.Driver
    platform: postgres
    username: postgres
    password: snowee
```

Indica ao spring as informações para a conexão com o banco de dados.

###### 1.2.2.3 jpa

```yml
jpa:
    generate-ddl: true
    show-sql: true
    properties:
      hibernate:
        ddl-auto: update
        show_sql: true
        format_sql: true
```

JPA é a camada de avesso ao banco de dados, auxiliando na criação dos repositorios.

###### 1.2.2.4 thymeleaf

```yml
thymeleaf:
    cache: true
```

O Thymeleaf é um template engine para projetos Java que facilita a criação de páginas HTML.

Para exibirmos algum valor vindo do código Java que está executando o template, devemos utilizar uma propriedade especial do **Thymeleaf** chamada th:text e então como valor de tal propriedade colocar o nome do atributo que queremos exibir entre ${} .

##### 1.2.3 security

```yml
security:
```

Tag pai para iniciar a declaração dos addons do spring.

###### 1.2.3.1 oauth2

```yml
  oauth2:
    client:
      client-id: ${CLIENT_ID:snoweeapi}
      client-secret: ${CLIENT_SECRET:snoweeapi}
```

Oauth2 é o servidor de autorização, e necessita das credenciais da aplicação para aceitar requests.

##### 1.2.4 jwt

```yml
jwt:
  secret: ${JWT_SECRET:MY-JWT-SECRET}
  duration: ${JWT_DURATION:86400}
```

JWT gera um access_token para o usuário poder fazer requests, ele é validado a partir da permissão concedida no momento do registro.

##### 1.2.5 server

```yml
server:
   port: 8080
   ssl:
      keyStoreType: PKCS12
      keyAlias: snoweeapi
      key-store: src/main/resources/snoweeapi.p12
      key-store-password: AdcOP1997!
```

Para que o spring seja capaz de lidar com HTTPS foi necessario a geração, converção e importação do certificado SSL do Letsencrypt para as propriedades da aplicação.

##### 1.2.6 application-dev.yml

```yml
 datasource:
    url: jdbc:h2:mem:dbsnowee;DB_CLOSE_ON_EXIT=FALSE
    driver-class-name: org.h2.Driver
    username: root
    password: root
```

Para o perfil de desenvolvimento, direcionamos o datasource para utilizarmos o banco de dados em memoria, o H2.

##### 1.2.7 application-prod.yml

```yaml
 datasource:
    url: jdbc:postgresql://database/dbsnowee
    driverClassName: org.postgresql.Driver
    platform: postgres
    username: postgres
    password: snowee
```

Para o perfil de produção, direcionamos o datasource para utilizarmos o banco de dados Postgresql, que roda em um container no servidor.

### 2. Configurações do Maven

Os arquivo a seguir encontra-se em /java-app.

#### 2.1 pom.xml

As configurações especificas para o bom funcionamento do projeto foram ajustadas em apenas algumas tags.

##### 2.1.1 build

```xml
	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
				<executions>
					<execution>
						<goals>
							<goal>repackage</goal>
						</goals>
						<configuration>
							<executable>true</executable>
							<classifier>spring-boot</classifier>
							<mainClass>
						com.snoweegamecorp.backend.BackendApplication
							</mainClass>
						</configuration>
					</execution>
				</executions>
				<configuration>
					<excludes>
						<exclude>
							<groupId>org.projectlombok</groupId>
							<artifactId>lombok</artifactId>
						</exclude>
					</excludes>
				</configuration>
			</plugin>
		</plugins>
	</build>
```

A tag build foi otimizada para rodar no servidor remoto, onde o target é repackage e necessita que a mainClass esteja explicita para sua execução.

### 3. Referencias

A seguir, algumas referencias que ajudaram a resolver alguns problemas no desenvolvimento.

#### 3.1 Docker

- https://www.youtube.com/watch?v=4wnQuNmGnQ8
- https://www.baeldung.com/spring-boot-postgresql-docker
- https://stackoverflow.com/questions/37695036/cannot-use-vim-vi-nano-yum-inside-docker-container

#### 3.2 Firewall

- https://linuxhint.com/fix-firewalld-not-running-error-centos/
- https://ajuda.rapidcloud.com.br/pt-BR/articles/2531164-como-gerenciar-regras-no-firewall-do-centos-7
- https://access.redhat.com/documentation/pt-br/red_hat_enterprise_linux/6/html/security_guide/sect-security_guide-server_security-verifying_which_ports_are_listening
- https://linuxconfig.org/how-to-install-missing-ifconfig-command-on-debian-linux
- https://www.djwebsystem.com.br/blog/abrir-porta-servidor-linux-centos/16

#### 3.3 Maven

- https://stackoverflow.com/questions/23217002/how-do-i-tell-spring-boot-which-main-class-to-use-for-the-executable-jar

#### 3.4 SSL

- https://www.thomasvitale.com/https-spring-boot-ssl-certificate/
- https://www.baeldung.com/spring-boot-https-self-signed-certificate
- https://keepgrowing.in/java/springboot/fix-cors-issues-between-spring-boot-and-angular-on-localhost/

