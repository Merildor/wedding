package com.example.wedding.services;

import com.example.wedding.registration.token.ConfirmationToken;
import com.example.wedding.user.User;
import com.example.wedding.dao.UserDao;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;


@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG = "User with email %s not found.";

    private final UserDao userDao;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userDao.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                String.format(USER_NOT_FOUND_MSG, email)));
    }

    public String signUpUser(User user) {
        boolean userExists = userDao
                .findByEmail(user.getEmail())
                .isPresent();

        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user
        );

        if (userExists){
            throw new IllegalStateException("Email already taken.");
        }

        String encodedPassword = bCryptPasswordEncoder
                .encode(user.getPassword());

        user.setPassword(encodedPassword);

        userDao.save(user);

        confirmationTokenService.saveConfirmationToken(
                confirmationToken);

//        TODO: SEND EMAIL

        return token;
    }
    public int enableUser(String email) {
        return userDao.enableUser(email);
    }
}
