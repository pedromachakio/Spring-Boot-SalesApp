package pedromachakio.com.github.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception { // configurar autenticação
        super.configure(auth);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception { // configurar autorização (depois de ter sido autenticado anteriormente)
        // por exemplo um cliente autenticado não tem authorization para aceder à info de admins/empregados do banco
        super.configure(http);
    }
}
