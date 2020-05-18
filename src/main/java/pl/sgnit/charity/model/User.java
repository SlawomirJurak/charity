package pl.sgnit.charity.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Size(max = 50)
    @NotBlank
    public String firstName;

    @Size(max = 50)
    @NotBlank
    public String lastName;

    @Email
    @NotBlank
    public String userName;

    @NotBlank
    @Size(min = 8)
    public String password;

    @Transient
    public String password2;

    public Boolean administrator;

    public Boolean active;

    public Boolean deleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String email) {
        this.userName = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public Boolean getAdministrator() {
        return administrator;
    }

    public void setAdministrator(Boolean administrator) {
        this.administrator = administrator == null ? false : administrator;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active == null ? false : active;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted == null ? false : deleted;
    }

    @PrePersist
    public void prePersist() {
        if (administrator == null) {
            administrator = false;
        }
        if (active == null) {
            active = false;
        }
        if (deleted == null) {
            deleted = false;
        }
    }
}
