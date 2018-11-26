package Backend.Rest;

import Backend.Rest.Entities.Archetype;
import Backend.Rest.Entities.Character;
import Backend.Rest.Entities.Race;
import Backend.Rest.Repositories.CharacterRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BastionCommandLineRunner implements CommandLineRunner {

    private final CharacterRepository characterRepository;

    @Autowired
    public BastionCommandLineRunner(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }


    @Override
    public void run(String... args) {

        characterRepository.deleteAll();

        // save a couple of customers
        characterRepository.save(new Character("Jack", 20, Archetype.ALCHEMIST, Race.TEMPEST));
        characterRepository.save(new Character("Chloe", 14, Archetype.HUNTER, Race.VOIDLING));
        characterRepository.save(new Character("Kim", 18, Archetype.FIGHTER, Race.VOIDLING));
        characterRepository.save(new Character("Jack", 10, Archetype.MAGE, Race.VOIDLING));
        characterRepository.save(new Character("Michelle", 25, Archetype.PHYSICIAN, Race.TEMPEST));

        // fetch all customers
        log.info("Characters found with findAll():");
        log.info("-------------------------------");
        for (Character character : characterRepository.findAll()) {
            log.info(character.toString());
        }
        log.info("");

        // fetch an individual customer by ID
        characterRepository.findById(1)
                .ifPresent(character -> {
                    log.info("Character found with findById(1):");
                    log.info("--------------------------------");
                    log.info(character.toString());
                    log.info("");
                });

        // fetch customers by characterName
        log.info("Character found with findByCharacterName('Jack'):");
        log.info("--------------------------------------------");
        characterRepository.findByCharacterName("Jack").forEach(jack -> log.info(jack.toString()));
        log.info("");

        // fetch customers by characterName contains
        log.info("Character found with findByCharacterNameContains('e'):");
        log.info("--------------------------------------------");
        characterRepository.findByCharacterNameContains("e").forEach(e -> log.info(e.toString()));
        log.info("");

    }
}
