package it.revo.revoservice.repository;

import it.revo.revoservice.entity.Contact;
import it.revo.revoservice.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.UUID;

@CrossOrigin(origins = "http://localhost:4000")
public interface ContactRepository extends JpaRepository<Contact, Integer> {
}
