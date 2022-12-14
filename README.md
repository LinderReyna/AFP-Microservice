<h1 align="center">AFP Microservice</h1>

### 1 - Build Projects

To build from source, you need the following installed and available in your `$PATH:`

* [Java 11](https://www.oracle.com/technetwork/java/index.html)

* [Apache Maven 3.3.4 or greater](https://maven.apache.org/)

* [PostgreSQL 10 or greater](https://www.postgresql.org/download/)

* [Vault](https://developer.hashicorp.com/vault/downloads)

create table `afp`

After cloning the project, you can build it from source with this command:
```sh
mvn clean install
```

If you don't have maven installed, you may directly use the included [maven wrapper](https://github.com/takari/maven-wrapper), and build with the command:
```sh
./mvnw clean install
```

After you install Vault, launch it in a console window. This command also starts up a server process.
```sh
$ vault server --dev --dev-root-token-id="00000000-0000-0000-0000-000000000000"
```

You can verify if everything works by running from project root:
```sh
mvn spring-boot:run
```