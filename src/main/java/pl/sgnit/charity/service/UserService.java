package pl.sgnit.charity.service;

import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.sgnit.charity.model.Token;
import pl.sgnit.charity.model.User;
import pl.sgnit.charity.repository.UserRepository;
import pl.sgnit.charity.util.EmailService;
import pl.sgnit.charity.util.LoggedUserInfo;
import pl.sgnit.charity.util.NewPassword;
import pl.sgnit.charity.util.PasswordChecker;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final LoggedUserInfo loggedUserInfo;
    private final PasswordChecker passwordChecker;
    private final TokenService tokenService;
    private final EmailService emailService;
    private final Environment environment;

    private String serverPort;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, LoggedUserInfo loggedUserInfo, PasswordChecker passwordChecker,
                       TokenService tokenService, EmailService emailService, Environment environment) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.loggedUserInfo = loggedUserInfo;
        this.passwordChecker = passwordChecker;
        this.tokenService = tokenService;
        this.emailService = emailService;
        this.environment = environment;
        serverPort = environment.getProperty("server.port").trim();
    }

    public String createUser(User user) {
        if (user.getFirstName() == null || user.getFirstName().isEmpty()) {
            return "Proszę uzupełnić pole Imię";
        }
        if (user.getLastName() == null || user.getLastName().isEmpty()) {
            return "Proszę uzupełnić pole Nazwisko";
        }
        if (user.getUserName() == null || user.getUserName().isEmpty()) {
            return "Proszę uzupełnić pole Email";
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            return "Proszę wpisać hasło";
        }
        if (user.getPassword2() == null || user.getPassword2().isEmpty()) {
            return "Proszę powtórzyć hasło";
        }
        if (!user.getPassword().equals(user.getPassword2())) {
            return "Wpisane hasła nie są takie same";
        }
        String passwordState = passwordChecker.isPasswordValid(user.getPassword());
        if (!"OK".equals(passwordState)) {
            return passwordState;
        }
        User existingUser = userRepository.findByUserName(user.getUserName());
        if (existingUser != null) {
            return "Użytkownik o podanym e-mailu już jest zarejestrowany";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        Token token = tokenService.generateTokenForUser(user, Duration.ofHours(48));
        StringBuilder message = new StringBuilder();
        message.append("Witaj ")
                .append(user.getFirstName())
                .append((char)13).append((char)10)
                .append("link aktywacyjny do konta na Charity.pl")
                .append((char)13).append((char)10)
                .append("http://localhost:"+serverPort+"/user/activate/").append(token.getToken());
        emailService.sendEmail(user.getUserName(), "Charity.pl - Aktywacja konta", message.toString());
        return "OK";
    }

    public String createAdministrator(User user) {
        return createUser(user);
    }

    public Optional<User> getUserWithCheckIfLogged(Long id) {
        Long loggedUserId = loggedUserInfo.getLoggedUserId();

        if (id != loggedUserId) {
            return Optional.empty();
        }
        return userRepository.findById(id);
    }

    public List<User> findAllUsers() {
        return userRepository.findByAdministratorIsFalse();
    }

    public List<User> finaAllAdministrators() {
        return userRepository.findByAdministratorIsTrue();
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public void deleteUser(Long id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isPresent()) {
            user.get().setDeleted(true);
            user.get().setActive(false);
            userRepository.save(user.get());
        }
    }

    public String changeUserData(Long id, User user) {
        Optional<User> dbUser = userRepository.findById(id);

        if (dbUser.isPresent() && dbUser.get().getId() == id) {
            dbUser.get().setFirstName(user.getFirstName());
            dbUser.get().setLastName(user.getLastName());
            saveUser(dbUser.get());
            return "OK";
        }
        return "Błąd podczas aktualizacji danych. Proszę spróbować później.";
    }

    public String changePassword(Long id, NewPassword newPassword) {
        Optional<User> dbUser = userRepository.findById(id);

        if (dbUser.isPresent() && dbUser.get().getId() == id) {
            if (!newPassword.getNewPassword().equals(newPassword.getNewPassword2())) {
                return "Wpisane hasła nie są takie same";
            }
            String passwordState = passwordChecker.isPasswordValid(dbUser.get().getPassword());
            if (!"OK".equals(passwordState)) {
                return passwordState;
            }
            if (passwordEncoder.matches(newPassword.getCurrentPassword(), dbUser.get().getPassword())) {
                dbUser.get().setPassword(passwordEncoder.encode(newPassword.getNewPassword()));
                saveUser(dbUser.get());
                return "OK";
            } else {
                return "Nieprawidłowe hasło użytkownika";
            }
        } else {
            return "Nie można zaktualizować danych";
        }
    }

    public String activateUser(String stringToken) {
        Optional<Token> token = tokenService.findTokenByToken(stringToken);
        if (token.isPresent() && token.get().getExpiryDate().isAfter(LocalDateTime.now())) {
            token.get().getUser().setActive(true);
            userRepository.save(token.get().getUser());
            tokenService.deleteToken(token.get());
            return "OK";
        }
        return "Niewłaściwy token";
    }
}
