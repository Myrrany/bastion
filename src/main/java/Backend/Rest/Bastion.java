package Backend.Rest;

import Backend.Rest.Entities.Archetype;
import Backend.Rest.Entities.Race;
import Backend.Rest.Repositories.CharacterRepository;
import Backend.Rest.Entities.Character;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Bastion {
    private static final Logger log = LoggerFactory.getLogger(Bastion.class);

    public static void main(String[] args) {
        SpringApplication.run(Bastion.class, args);
    }



}
