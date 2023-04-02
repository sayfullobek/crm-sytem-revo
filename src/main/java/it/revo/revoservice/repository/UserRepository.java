package it.revo.revoservice.repository;

import it.revo.revoservice.entity.Role;
import it.revo.revoservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:4000")
public interface UserRepository extends JpaRepository<User, UUID> {
    boolean existsByPhoneNumber(String phoneNumber);

    Optional<User> findUserByPhoneNumber(String phoneNumber);

    List<User> findUsersByRoleId(Integer role_id);

//    @Query("select * from users where id=ids")
//    Optional<User> findById(Integer ids);
}
