package Backend.Rest.Resources.Skills;

import Backend.Rest.Entities.Skills.Skill;
import Backend.Rest.Repositories.CharacterRepository;
import Backend.Rest.Repositories.Skills.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class SkillResource {

    private final SkillRepository skillRepository;

    @Autowired
    public SkillResource(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    @GetMapping("/skills")
    public @ResponseBody
    Iterable<Skill> getAllSkills(@RequestParam(name = "name", required = false, defaultValue = "") String name) {
        return skillRepository.findBySkillNameContains(name);
    }

    @GetMapping("/skills/{id}")
    public @ResponseBody
    Skill findSkillById(@PathVariable int id) {
        return skillRepository.findById(id).orElse(null);
    }
}
