package ua.od.onpu.crm.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.od.onpu.crm.dao.model.security.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUserName(String userName);
}
