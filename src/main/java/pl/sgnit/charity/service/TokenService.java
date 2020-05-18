package pl.sgnit.charity.service;

import org.springframework.stereotype.Service;
import pl.sgnit.charity.model.Token;
import pl.sgnit.charity.model.User;
import pl.sgnit.charity.repository.TokenRepository;
import pl.sgnit.charity.util.RandomStringGenerator;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAmount;
import java.util.Optional;

@Service
public class TokenService {

    private final RandomStringGenerator stringGenerator;
    private final TokenRepository tokenRepository;

    public TokenService(RandomStringGenerator stringGenerator, TokenRepository tokenRepository) {
        this.stringGenerator = stringGenerator;
        this.tokenRepository = tokenRepository;
    }

    public Optional<Token> findTokenByToken(String token) {
        return tokenRepository.findByToken(token);
    }

    public void deleteToken(Token token) {
        tokenRepository.delete(token);
    }

    public Token generateTokenForUser(User user, TemporalAmount expiryPeriod) {
        Token token = generateToken();
        LocalDateTime localDateTime = LocalDateTime.now();

        token.setUser(user);
        token.setExpiryDate(localDateTime.plus(expiryPeriod));
        tokenRepository.save(token);
        return token;
    }

    private Token generateToken() {
        Token token = new Token();

        token.setToken(stringGenerator.generate(32));
        return token;
    }
}
