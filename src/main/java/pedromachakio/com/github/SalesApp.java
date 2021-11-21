package pedromachakio.com.github;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SalesApp extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(SalesApp.class, args);

        // mvn clean package to build the JAR file
        // java -jar nomeDoFicheiro.jar para correr a packaged app

        // add war to <packaging> in pom to package as a war file instead of jar and also respective dependency, starter-tomcat
        // e extends SpringBootServletInitializer na main class para se tornar web app que gera war
        // comandos are the same:
        // mvn clean package


        // mvn clean package -P nomeDoPerfilQueCriei para alternar entre development e production e ser mais facil mudar de jar para war
        // sem ter que ir alterar tudo manualmente de todas as vezes
    }
}
