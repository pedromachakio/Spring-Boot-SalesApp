package pedromachakio.com.github.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pedromachakio.com.github.services.impl.UserServiceImpl;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserServiceImpl userService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userService)
                .passwordEncoder(passwordEncoder());


    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/clients/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/api/orders/**").hasAnyRole("USER", "ADMIN")// ** representa parâmetro a receber
                .antMatchers("/api/products/**").hasRole("ADMIN") // admins é que registam novos produtos etc
                .antMatchers(HttpMethod.POST, "/api/users/**").permitAll() // para qualquer pessoa se poder registar
                .anyRequest().authenticated() // in case I forget to map a specific request, isto vai cover everything e make sure que estão autenticados
                .and()
                .headers().frameOptions().disable()
                .and()
                //.formLogin();
                .httpBasic(); // em vez de ser login form é passado nos headers
    }
}
