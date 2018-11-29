package Backend.Rest;

import Backend.Rest.Entities.Archetype;
import Backend.Rest.Entities.Character;
import Backend.Rest.Entities.Crafting.Craft;
import Backend.Rest.Entities.Crafting.CraftsSet;
import Backend.Rest.Entities.Crafting.Level;
import Backend.Rest.Entities.Race;
import Backend.Rest.Entities.Skills.Skill;
import Backend.Rest.Repositories.CharacterRepository;
import Backend.Rest.Repositories.Crafting.CraftsSetRepository;
import Backend.Rest.Repositories.Skills.SkillRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Slf4j
public class BastionCommandLineRunner implements CommandLineRunner {

    private final CharacterRepository characterRepository;
    private final CraftsSetRepository craftsSetRepository;
    private final SkillRepository skillRepository;

    @Autowired
    public BastionCommandLineRunner(CharacterRepository characterRepository, CraftsSetRepository craftsSetRepository, SkillRepository skillRepository) {
        this.characterRepository = characterRepository;
        this.craftsSetRepository = craftsSetRepository;
        this.skillRepository = skillRepository;
    }


    @Override
    public void run(String... args) {
        Character chloe = new Character("Chloe", 14, Archetype.HUNTER, Race.VOIDLING);

        characterRepository.deleteAll();

        // save a couple of customers
        characterRepository.save(new Character("Jack", 20, Archetype.ALCHEMIST, Race.TEMPEST));
        characterRepository.save(new Character("Kim", 18, Archetype.FIGHTER, Race.VOIDLING));
        characterRepository.save(new Character("Jack", 10, Archetype.MAGE, Race.VOIDLING));
        characterRepository.save(new Character("Michelle", 25, Archetype.PHYSICIAN, Race.TEMPEST));
        characterRepository.save(chloe);
        CraftsSet set = new CraftsSet(chloe, Craft.MENTAL, Level.BASIC);
        craftsSetRepository.save(set);
        chloe.addCraftToSet(set, 3);

        skillRepository.deleteAll();

        Skill track = new Skill("Tracking / Hunting", "You can track and hunt creatures.", 5, 3, Arrays.asList(Archetype.HUNTER), null);
        Skill trap = new Skill("Trapping", "You can set traps. Does require materials of some kind, depending on the type of trap one wants to set.", 5, 3, Arrays.asList(Archetype.HUNTER), track);
        Skill stealth = new Skill("Stealth", "As long as you remain still and at a place you could hide, you are invisible for the naked eye. Does not work in open space.", 5, 3, Arrays.asList(Archetype.HUNTER), trap);

        skillRepository.save(track);
        skillRepository.save(trap);
        skillRepository.save(stealth);
        chloe.addSkillToSet(track, 3);
        chloe.addSkillToSet(trap, 3);
        characterRepository.save(chloe);

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
