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

@SpringBootApplication
public class Bastion {
    private static final Logger log = LoggerFactory.getLogger(Bastion.class);

    public static void main(String[] args) {
        SpringApplication.run(Bastion.class);
    }

    @Bean
    public CommandLineRunner demo(CharacterRepository repository) {
        return (args) -> {
            // save a couple of customers
            repository.save(new Character("Jack", 20, Archetype.ALCHEMIST, Race.TEMPEST));
            repository.save(new Character("Chloe", 14, Archetype.HUNTER, Race.VOIDLING));
            repository.save(new Character("Kim", 18, Archetype.FIGHTER, Race.VOIDLING));
            repository.save(new Character("Jack", 10, Archetype.MAGE, Race.VOIDLING));
            repository.save(new Character("Michelle", 25, Archetype.PHYSICIAN, Race.TEMPEST));

            // fetch all customers
            log.info("Characters found with findAll():");
            log.info("-------------------------------");
            for (Character character : repository.findAll()) {
                log.info(character.toString());
            }
            log.info("");

            // fetch an individual customer by ID
            repository.findById(1L)
                    .ifPresent(character -> {
                        log.info("Character found with findById(1L):");
                        log.info("--------------------------------");
                        log.info(character.toString());
                        log.info("");
                    });

            // fetch customers by last characterName
            log.info("Character found with findByLastName('Jack'):");
            log.info("--------------------------------------------");
            repository.findByCharacterName("Jack").forEach(jack -> {
                log.info(jack.toString());
            });
            log.info("");
        };
    }

}
