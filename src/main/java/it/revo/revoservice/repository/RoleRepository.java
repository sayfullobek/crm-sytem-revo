package it.revo.revoservice.repository;

import it.revo.revoservice.entity.Role;
import it.revo.revoservice.entity.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:4000")
public interface RoleRepository extends JpaRepository<Role, Integer> {

}
