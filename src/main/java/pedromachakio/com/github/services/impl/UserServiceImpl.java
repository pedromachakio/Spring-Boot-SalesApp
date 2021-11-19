package pedromachakio.com.github.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pedromachakio.com.github.domain.entity.User;
import pedromachakio.com.github.domain.repository.UserDAO;
import pedromachakio.com.github.exception.InvalidPasswordException;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDAO userDAO;

    @Transactional
    public User saveUser(User user) {
        return userDAO.save(user);
    }

    public UserDetails authenticate(User user) {
        UserDetails userDetails = loadUserByUsername(user.getUsername());
        boolean doPasswordsMatch = passwordEncoder.matches(user.getPassword(), userDetails.getPassword());

        if (doPasswordsMatch) {
            return userDetails;
        } else {
            throw new InvalidPasswordException();
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User foundUser = userDAO.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found in DB"));

        String[] roles = foundUser.isAdmin() ? new String[]{"ADMIN", "USER"} : new String[]{"USER"};

        return org.springframework.security.core.userdetails.User
                .builder()
                .username(foundUser.getUsername())
                .password(foundUser.getPassword())
                .roles(roles)
                .build();
    }
}
