package com.github.emailsender;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmailSenderApplication {

    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
        System.setProperty("SMTP_HOST", dotenv.get("SMTP_HOST"));
        System.setProperty("SMTP_PORT", dotenv.get("SMTP_PORT"));
        System.setProperty("SMTP_USERNAME", dotenv.get("SMTP_USERNAME"));
        System.setProperty("SMTP_PASSWORD", dotenv.get("SMTP_PASSWORD"));

        SpringApplication.run(EmailSenderApplication.class, args);
    }
}
