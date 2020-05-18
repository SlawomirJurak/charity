package pl.sgnit.charity.restcontroller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.sgnit.charity.model.User;
import pl.sgnit.charity.service.UserService;
import pl.sgnit.charity.util.NewPassword;

import java.util.Optional;

@RestController
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register/user")
    public String registerNewUser(@RequestBody User user) {

        return userService.createUser(user);
    }

    @PostMapping("/register/administrator")
    public String registerNewAdministrator(@RequestBody User user) {

        return userService.createAdministrator(user);
    }

    @PostMapping("/user/activate/{id}")
    public String activateUser(@PathVariable Long id) {
        return activateDeactivateUser(id, true);
    }

    @PostMapping("/user/deactivate/{id}")
    public String deactivateUser(@PathVariable Long id) {
        return activateDeactivateUser(id, false);
    }

    @PostMapping("user/change/{id}")
    public String changeUserData(@PathVariable Long id, @RequestBody User user) {
        return userService.changeUserData(id, user);
    }

    @PostMapping("/user/changePassword/{id}")
    public String changePassword(@PathVariable Long id, @RequestBody NewPassword newPassword) {
        return userService.changePassword(id, newPassword);
    }

    private String activateDeactivateUser(Long id, boolean activate) {
        Optional<User> user = userService.findById(id);

        if (!user.isPresent()) {
            return "Użytkownik nie istnieje";
        }
        user.get().setActive(activate);
        userService.saveUser(user.get());
        return "OK";
    }
}
