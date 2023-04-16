package it.revo.revoservice.component;

import it.revo.revoservice.entity.Role;
import it.revo.revoservice.entity.User;
import it.revo.revoservice.entity.WeekDays;
import it.revo.revoservice.entity.enums.RoleName;
import it.revo.revoservice.entity.enums.WeekDayName;
import it.revo.revoservice.repository.HistoryRepository;
import it.revo.revoservice.repository.RoleRepository;
import it.revo.revoservice.repository.UserRepository;
import it.revo.revoservice.repository.WeekDayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final WeekDayRepository weekDayRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String initMode;

    @Override
    public void run(String... args) {
        if (initMode.equals("create-drop") || initMode.equals("create")) {
            for (WeekDayName value : WeekDayName.values()) {
                weekDayRepository.save(
                        new WeekDays(value)
                );
            }
            for (RoleName value : RoleName.values()) {
                roleRepository.save(
                        new Role(
                                value
                        )
                );
            }
            userRepository.save(
                    new User(
                            "Sayfullo",
                            "To'xtayev",
                            "990763246",
                            passwordEncoder.encode("root123"),
                            roleRepository.findById(1).orElseThrow(() -> new ResourceNotFoundException("getRole")),
                            "root123"
                    )
            );
        }
    }
}