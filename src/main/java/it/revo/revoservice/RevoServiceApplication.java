package it.revo.revoservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@SpringBootApplication
public class RevoServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(RevoServiceApplication.class, args);
    }
}
