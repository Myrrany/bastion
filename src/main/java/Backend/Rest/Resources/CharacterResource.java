package Backend.Rest.Resources;

import Backend.Rest.Entities.Archetype;
import Backend.Rest.Entities.Character;
import Backend.Rest.Entities.Race;
import Backend.Rest.Repositories.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class CharacterResource {

    private final CharacterRepository characterRepository;

    @Autowired
    public CharacterResource(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    @GetMapping("/characters")
    public @ResponseBody
    Iterable<Character> getAllCharacters(@RequestParam(name = "xp_min", required = false, defaultValue = "0") Integer min, @RequestParam(name = "xp_max", required = false, defaultValue = "2147483647") Integer max) {
        return characterRepository.findByXpGreaterThanEqualAndXpLessThanEqual(min, max);
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

    @GetMapping("/characters?archetype={archetype}")
    public @ResponseBody
    Iterable<Character> findCharactersByArchetype(@PathVariable String archetype) {
        return characterRepository.findByArchetype(Archetype.valueOf(archetype.toUpperCase()));
    }

    @GetMapping("/characters?race={race}")
    public @ResponseBody
    Iterable<Character> findCharactersByRace(@PathVariable String race) {
        return characterRepository.findByRace(Race.valueOf(race.toUpperCase()));
    }

    @GetMapping("/characters?name={name}")
    public @ResponseBody
    Iterable<Character> findCharactersByName(@PathVariable String name) {
        return characterRepository.findByCharacterNameContains(name);
    }
}
