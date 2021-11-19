package pedromachakio.com.github.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pedromachakio.com.github.domain.entity.User;
import pedromachakio.com.github.exception.InvalidPasswordException;
import pedromachakio.com.github.rest.dto.CredentialsDTO;
import pedromachakio.com.github.rest.dto.TokenDTO;
import pedromachakio.com.github.security.jwt.JWTService;
import pedromachakio.com.github.services.impl.UserServiceImpl;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User saveUser(@RequestBody @Valid User user) {
        String cryptoPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(cryptoPassword);
        return userService.saveUser(user);
    }

    @PostMapping("/auth")
    public TokenDTO authenticate(@RequestBody CredentialsDTO credentials) {
        try {
            User user = User.builder()
                    .username(credentials.getUsername())
                    .password(credentials.getPassword()).build();

            UserDetails authenticatedUser = userService.authenticate(user);
            String token = jwtService.generateToken(user);

            return new TokenDTO(user.getUsername(), token);

        } catch (UsernameNotFoundException | InvalidPasswordException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }
}
