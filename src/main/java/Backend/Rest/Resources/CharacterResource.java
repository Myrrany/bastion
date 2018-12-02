package Backend.Rest.Resources;

import Backend.Rest.Entities.Archetype;
import Backend.Rest.Entities.Character;
import Backend.Rest.Entities.Race;
import Backend.Rest.Repositories.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/")
public class CharacterResource {

    private final CharacterRepository characterRepository;

    @Autowired
    public CharacterResource(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    @GetMapping("/characters")
    public @ResponseBody
    Iterable<Character> getAllCharacters(@RequestParam(name = "xp_min", required = false, defaultValue = "0") Integer min,
                                         @RequestParam(name = "xp_max", required = false, defaultValue = "2147483647") Integer max,
                                         @RequestParam(name = "archetype", required = false, defaultValue = "") String archetype,
                                         @RequestParam(name = "race", required = false, defaultValue = "") String race,
                                         @RequestParam(name = "name", required = false, defaultValue = "") String name) {
        Archetype type = null;
        Race racey = null;
        try {
            type = Archetype.valueOf(archetype.toUpperCase());
        } catch (IllegalArgumentException e){
            if (!archetype.equals("")) {
                System.out.println("Sorry, but " + archetype + " is not a valid archetype.");
                archetype = "";
            }
        }
        try {
            racey = Race.valueOf(race.toUpperCase());
        } catch (IllegalArgumentException e){
            if (!race.equals("")) {
                System.out.println("Sorry, but " + race + " is not a valid race.");
                race = "";
            }
        }


        if (race.equals("") && archetype.equals("")) {
            System.out.println("yes hello i get here" + min + " " + max);
            return characterRepository.findByXpBetweenAndCharacterNameContains(min, max, name);
        } else if (!race.equals("") && archetype.equals("")) {
            return characterRepository.findByXpBetweenAndRaceAndCharacterNameContains(min, max, racey, name);
        } else if (!archetype.equals("") && race.equals("")) {
            return characterRepository.findByXpBetweenAndArchetypeAndCharacterNameContains(min, max, type, name);
        } else {
            return characterRepository.findByXpBetweenAndArchetypeAndRaceAndCharacterNameContains(min, max, type, racey, name);
        }
    }

    @GetMapping("/characters/{id}")
    public @ResponseBody
    Character findCharacterById(@PathVariable int id) {
        return characterRepository.findById(id).orElse(null);
    }

    @PostMapping("/characters")
    Character newCharacter(@RequestBody Character newCharacter) {
        return characterRepository.save(newCharacter);
    }

    @DeleteMapping("/characters/{id}")
    void deleteCharacter(@PathVariable int id) {
        characterRepository.deleteById(id);
    }
}
