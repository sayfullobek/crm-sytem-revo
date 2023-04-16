package it.revo.revoservice;

import it.revo.revoservice.config.InitConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@SpringBootApplication
public class RevoServiceApplication {
    public static void main(String[] args) {
        if (InitConfig.isStart())
            SpringApplication.run(RevoServiceApplication.class, args);
        else System.out.println("Malumotlar o'chmadi");
    }
}
