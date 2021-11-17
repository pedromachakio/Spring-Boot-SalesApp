package pedromachakio.com.github.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Bean
    public PasswordEncoder passwordEncoder() { // para encriptar e desencriptar as passes dos utilizadores
        return new BCryptPasswordEncoder(); // gera sempre um hash random, logo é mais seguro
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception { // configurar autenticação
        auth.inMemoryAuthentication()
                .passwordEncoder(passwordEncoder())
                .withUser("fulano")
                .password(passwordEncoder().encode("123"))
                .roles("USER");

        // criando um utilizador em memória só para testar (não vem de base de dados nem nada)
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception { // configurar autorização (depois de ter sido autenticado anteriormente) do GET, POST, etc
        // por exemplo um cliente autenticado não tem authorization para aceder à info de admins/empregados do banco
        http
                .csrf().disable()
                .authorizeRequests() // hasRole("USER"); // tem que ter este role para aceder
                .antMatchers("/api/clients/**").authenticated() // para dizer que para ter acesso a esse endpoint tem que estar autenticado
                .and() // volta para a raiz
                .formLogin();
    }
}
