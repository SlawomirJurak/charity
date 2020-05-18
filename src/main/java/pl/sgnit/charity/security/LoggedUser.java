package pl.sgnit.charity.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class LoggedUser extends User {

    private pl.sgnit.charity.model.User user;

    public LoggedUser(String username, String password, Collection<? extends GrantedAuthority> authorities,
                      pl.sgnit.charity.model.User user) {
        super(username, password, authorities);
        this.user = user;
    }

    public pl.sgnit.charity.model.User getUser() {
        return user;
    }
}
