package pl.sgnit.charity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.sgnit.charity.model.User;
import pl.sgnit.charity.service.UserService;
import pl.sgnit.charity.util.LoggedUserInfo;

import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    private final UserService userService;
    private final LoggedUserInfo loggedUserInfo;

    public UserController(UserService userService, LoggedUserInfo loggedUserInfo) {
        this.userService = userService;
        this.loggedUserInfo = loggedUserInfo;
    }

    @GetMapping("/user/register")
    public String initRegister() {
        return "user/register";
    }

    @GetMapping("/user/postRegister")
    public String postRegister(Model model) {
        model.addAttribute("communicate", "Na Twój adres email został wysłany link aktywacyjny");
        return "communicate";
    }

    @GetMapping("/user/{id}")
    public String showProfile(Model model, @PathVariable Long id) {
        Optional<User> user = userService.getUserWithCheckIfLogged(id);
        if (!user.isPresent()) {
            return "redirect:/";
        }
        model.addAttribute("user", user.get());
        return "user/profile";
    }

    @GetMapping("/user/users")
    public String userList(Model model) {
        List<User> userAll = userService.findAllUsers();

        model.addAttribute("userListTitle", "Użytkownicy");
        model.addAttribute("userAll", userAll);
        return "user/users";
    }

    @GetMapping("/user/administrators")
    public String administratorList(Model model) {
        List<User> administratorAll = userService.finaAllAdministrators();

        model.addAttribute("userListTitle", "Administratorzy");
        model.addAttribute("userAll", administratorAll);
        model.addAttribute("administrators", true);
        return "user/users";
    }

    @GetMapping("/user/administrator/new")
    public String initAdministratorRegister(Model model) {

        model.addAttribute("administrator", true);
        return "user/register";
    }

    @GetMapping("/user/delete/{id}")
    public String initDelete(Model model, @PathVariable Long id) {
        User user = userService.findById(id).get();

        model.addAttribute("user", user);
        return "user/delete";
    }

    @PostMapping("user/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        String userType = loggedUserInfo.getLoggedUserType();
        Optional<User> user = userService.findById(id);
        String url;

        if ("admin".equals(userType) || "siteAdmin".equals(userType)) {
            userService.deleteUser(id);
        }
        if (user.get().getAdministrator()) {
            url = "/user/administrators";
        } else {
            url = "/user/users";
        }
        return url;
    }

    @GetMapping("/user/activate/{token}")
    public String activateUser(@PathVariable String token) {
        String answer = userService.activateUser(token);

        return "OK".equals(answer) ? "user/accountActive" : "redirect:/";
    }
}
