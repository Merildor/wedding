package com.example.wedding.services;

import com.example.wedding.registration.token.ConfirmationToken;
import com.example.wedding.dao.ConfirmationTokenDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {

    private final ConfirmationTokenDao confirmationTokenDao;

    public void saveConfirmationToken(ConfirmationToken token) {
        confirmationTokenDao.save(token);
    }

    public Optional<ConfirmationToken> getToken(String token) {
        return confirmationTokenDao.findByToken(token);
    }

    public int setConfirmedAt(String token) {
        return confirmationTokenDao.updateConfirmedAt(token, LocalDateTime.now());
    }
}