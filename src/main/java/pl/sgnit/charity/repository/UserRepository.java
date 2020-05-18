package pl.sgnit.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sgnit.charity.model.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserNameAndActiveIsTrue(String userName);

    User findByUserName(String userName);

    List<User> findByAdministratorIsFalse();

    List<User> findByAdministratorIsTrue();
}
