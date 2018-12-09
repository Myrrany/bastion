package Backend.Rest;

import Backend.Rest.Entities.Archetype;
import Backend.Rest.Entities.Character;
import Backend.Rest.Entities.CharacterUtils;
import Backend.Rest.Entities.Crafting.Craft;
import Backend.Rest.Entities.Crafting.CraftingUtils;
import Backend.Rest.Entities.Crafting.CraftsSet;
import Backend.Rest.Entities.Crafting.Level;
import Backend.Rest.Entities.Magic.*;
import Backend.Rest.Entities.Race;
import Backend.Rest.Entities.Skills.Skill;
import Backend.Rest.Entities.Skills.SkillUtils;
import Backend.Rest.Entities.Skills.Skillset;
import Backend.Rest.Repositories.CharacterRepository;
import Backend.Rest.Repositories.Crafting.CraftsSetRepository;
import Backend.Rest.Repositories.Magic.ElementRepository;
import Backend.Rest.Repositories.Magic.SpellLevelRepository;
import Backend.Rest.Repositories.Magic.SpellRepository;
import Backend.Rest.Repositories.Skills.SkillRepository;
import Backend.Rest.Repositories.Skills.SkillsetRepository;
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
    private final SkillsetRepository skillsetRepository;
    private final ElementRepository elementRepository;
    private final SpellRepository spellRepository;
    private final SpellLevelRepository spellLevelRepository;
    MagicUtils magicUtils = new MagicUtils();
    SkillUtils skillUtils = new SkillUtils();
    CraftingUtils craftingUtils = new CraftingUtils();
    CharacterUtils characterUtils = new CharacterUtils();

    @Autowired
    public BastionCommandLineRunner(CharacterRepository characterRepository,
                                    CraftsSetRepository craftsSetRepository,
                                    SkillRepository skillRepository,
                                    SkillsetRepository skillsetRepository,
                                    ElementRepository elementRepository,
                                    SpellRepository spellRepository,
                                    SpellLevelRepository spellLevelRepository) {
        this.characterRepository = characterRepository;
        this.craftsSetRepository = craftsSetRepository;
        this.skillRepository = skillRepository;
        this.skillsetRepository = skillsetRepository;
        this.elementRepository = elementRepository;
        this.spellRepository = spellRepository;
        this.spellLevelRepository = spellLevelRepository;
    }


    @Override
    public void run(String... args) {
        Character doctor = new Character("NONE YET", 5, Archetype.PHYSICIAN, Race.VOIDLING);
        PrimaryElement uno = new PrimaryElement("Fire", "Hurts when you touch it", 5);
        PrimaryElement dos = new PrimaryElement("Water", "Soft and soothing", 5);
        SecondaryElement tres = new SecondaryElement("Steam", "Very punk", 5, uno, dos, 3);
        TertiaryElement quatro = new TertiaryElement("Void", "It demands chicken", 5, 3, 12, Race.VOIDLING);
        TertiaryElement cinq = new TertiaryElement("Newspaper", "\uD83D\uDDDE️ \uD83D\uDCA5", 69, 420, 9001, Race.TEMPEST);
        SpellLevel one = new SpellLevel(2, 1, 1, 10, 60);
        Spell spell = new Spell("Whap", "You whap someone with a newspaper", 0, true, true);
        spell.setLevel(one);
        spell.setElement(cinq);

        characterRepository.deleteAll();

        // save a couple of customers
        characterRepository.save(new Character("Jack", 20, Archetype.CRAFTER, Race.TEMPEST));
        characterRepository.save(new Character("Kim", 18, Archetype.FIGHTER, Race.VOIDLING));
        characterRepository.save(new Character("Jack", 10, Archetype.MAGE, Race.VOIDLING));
        characterRepository.save(new Character("Michelle", 25, Archetype.PHYSICIAN, Race.TEMPEST));
        characterRepository.save(doctor);
        CraftsSet set = new CraftsSet(doctor, Craft.MENTAL, Level.BASIC);
        craftsSetRepository.save(set);
        doctor.addCraftToSet(set, 3);

        skillRepository.deleteAll();

        Skill track = new Skill("Tracking / Hunting", "You can track and hunt creatures.", 5, 3, Arrays.asList(Archetype.HUNTER), null, true);
        Skill trap = new Skill("Trapping", "You can set traps. Does require materials of some kind, depending on the type of trap one wants to set.", 5, 3, Arrays.asList(Archetype.HUNTER), track, true);
        Skill stealth = new Skill("Stealth", "As long as you remain still and at a place you could hide, you are invisible for the naked eye. Does not work in open space.", 5, 3, Arrays.asList(Archetype.HUNTER), trap, false);

        skillRepository.save(track);
        skillRepository.save(trap);
        skillRepository.save(stealth);
        Skillset tracking = new Skillset(doctor, track, 1);
        Skillset trapping = new Skillset(doctor, trap, 1);
        skillsetRepository.save(tracking);
        skillsetRepository.save(trapping);
        doctor.addSkillToSet(tracking, 0);
        doctor.addSkillToSet(trapping, 0);
        doctor.addSkillToSet(tracking, 0);
        doctor.addSkillToSet(tracking, 0);

        spellRepository.deleteAll();
        spellLevelRepository.deleteAll();
        elementRepository.deleteAll();

        elementRepository.save(uno);
        elementRepository.save(dos);
        elementRepository.save(tres);
        elementRepository.save(quatro);
        elementRepository.save(cinq);


        spellLevelRepository.save(one);
        spellRepository.save(spell);

        doctor.addToSpellbook(spell, 2);
        characterRepository.save(doctor);


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
