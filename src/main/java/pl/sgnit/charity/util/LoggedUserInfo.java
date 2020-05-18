package pl.sgnit.charity.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import pl.sgnit.charity.security.LoggedUser;

@Component
public class LoggedUserInfo {

    public Long getLoggedUserId() {
        Authentication authentication = getAuthentication();
        if (authentication.getPrincipal().equals("anonymousUser")) {
            return -2l;
        }

        org.springframework.security.core.userdetails.User user = ((org.springframework.security.core.userdetails.User) authentication.getPrincipal());
        if (user instanceof LoggedUser) {
            LoggedUser loggedUser = (LoggedUser) user;

            return loggedUser.getUser().getId();
        } else {
            /* logged as site admin*/
            return -1l;
        }
    }

    public String getLoggedUserType() {
        Authentication authentication = getAuthentication();
        if (authentication.getPrincipal().equals("anonymous")) {
            return "anonymous";
        }

        org.springframework.security.core.userdetails.User user = ((org.springframework.security.core.userdetails.User) authentication.getPrincipal());
        if (user instanceof LoggedUser) {
            LoggedUser loggedUser = (LoggedUser) user;

            return loggedUser.getUser().getAdministrator() ? "admin" : "user";
        } else {
            /* logged as site admin*/
            return "siteAdmin";
        }
    }

    private Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
