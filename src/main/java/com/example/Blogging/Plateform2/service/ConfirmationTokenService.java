package com.example.Blogging.Plateform2.service;

import com.example.Blogging.Plateform2.model.AppUser;
import com.example.Blogging.Plateform2.model.ConfirmationToken;
import com.example.Blogging.Plateform2.repository.AppUserRepository;
import com.example.Blogging.Plateform2.repository.ConfirmationTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final AppUserRepository appUserRepository;

    public void saveConfirmationToken(ConfirmationToken token) {
        confirmationTokenRepository.save(token);
    }

    public Optional<ConfirmationToken> getToken(String token) {
        return confirmationTokenRepository.findByToken(token);
    }

    public Optional<AppUser> getUser(Long id) {return appUserRepository.findById(id);
    }

    public int setConfirmedAt(String token) {
        return confirmationTokenRepository.updateConfirmedAt(
                token, LocalDateTime.now());
    }

}
