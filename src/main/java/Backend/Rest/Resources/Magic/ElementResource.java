package Backend.Rest.Resources.Magic;

import Backend.Rest.Entities.Magic.Element;
import Backend.Rest.Entities.Skills.Skill;
import Backend.Rest.Repositories.Magic.ElementRepository;
import Backend.Rest.Repositories.Skills.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/")
public class ElementResource {

    private final ElementRepository elementRepository;

    @Autowired
    public ElementResource(ElementRepository elementRepository) {
        this.elementRepository = elementRepository;
    }

    @GetMapping("/elements")
    public @ResponseBody
    Iterable<Element> getAllSkills(@RequestParam(name = "name", required = false, defaultValue = "") String name) {
        return elementRepository.findByNameContains(name);
    }

    @GetMapping("/elements/{id}")
    public @ResponseBody
    Element findElementById(@PathVariable int id) {
        return elementRepository.findById(id).orElse(null);
    }
}

