package Backend.Rest.Resources.Magic;

import Backend.Rest.Entities.Magic.Spell;
import Backend.Rest.Repositories.Magic.SpellRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class SpellResource {

    private final SpellRepository spellRepository;

    @Autowired
    public SpellResource(SpellRepository spellRepository) {
        this.spellRepository = spellRepository;
    }

    @GetMapping("/spells")
    public @ResponseBody
    Iterable<Spell> getAllSpells(@RequestParam(name = "name", required = false, defaultValue = "") String name) {
        return spellRepository.findByNameContains(name);
    }

    @GetMapping("/spells/{id}")
    public @ResponseBody
    Spell findSpellById(@PathVariable int id) {
        return spellRepository.findById(id).orElse(null);
    }
}
