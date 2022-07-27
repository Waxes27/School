package com.waxes27.School.Services.auth;

import com.waxes27.School.Controllers.auth.ConfirmationToken.ConfirmationToken;
import com.waxes27.School.Repositories.auth.ConfirmationTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {
    private final ConfirmationTokenRepository confirmationTokenRepository;

    public void saveConfirmationToken(ConfirmationToken token){
        confirmationTokenRepository.save(token);
    }

    public ConfirmationToken getToken(String token){
        Optional<ConfirmationToken> confirmationTokenOptional = confirmationTokenRepository.findByToken(token);
        if (confirmationTokenOptional.isPresent()){
            return confirmationTokenOptional.get();
        }else{
            throw new IllegalStateException("Token not found");
        }
    }


}
