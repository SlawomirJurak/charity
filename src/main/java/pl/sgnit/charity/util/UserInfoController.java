package pl.sgnit.charity.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.sgnit.charity.security.LoggedUser;

@ControllerAdvice
public class UserInfoController {

    @ModelAttribute("loggedUserName")
    public String userName(@AuthenticationPrincipal LoggedUser loggedUser) {
        if (loggedUser == null) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication.getPrincipal().equals("anonymousUser")) {
                return "";
            } else {
                return ((org.springframework.security.core.userdetails.User) authentication.getPrincipal()).getUsername();
            }
        }
        return loggedUser.getUser().getFirstName().isEmpty() ? loggedUser.getUser().getUserName() : loggedUser.getUser().getFirstName();
    }

    @ModelAttribute("loggedUserType")
    public String userType(@AuthenticationPrincipal LoggedUser loggedUser) {
        if (loggedUser == null) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication.getPrincipal().equals("anonymousUser")) {
                return "anonymous";
            } else {
                return "siteAdmin";
            }
        }
        return loggedUser.getUser().getAdministrator() ? "admin" : "user";
    }

    @ModelAttribute("loggedUserId")
    public Long userId(@AuthenticationPrincipal LoggedUser loggedUser) {
        if (loggedUser == null) {
            return -1l;
        }
        return loggedUser.getUser().getId();
    }
}
