package pedromachakio.com.github.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pedromachakio.com.github.domain.entity.User;
import pedromachakio.com.github.services.impl.UserServiceImpl;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User saveUser(@RequestBody @Valid User user) {
        String cryptoPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(cryptoPassword);
        return userService.saveUser(user);
    }
}
