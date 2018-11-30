import Backend.Rest.Entities.Archetype;
import Backend.Rest.Entities.Character;
import Backend.Rest.Entities.CharacterUtils;
import Backend.Rest.Entities.Crafting.Craft;
import Backend.Rest.Entities.Crafting.CraftsSet;
import Backend.Rest.Entities.Crafting.Level;
import Backend.Rest.Entities.Magic.PrimaryElement;
import Backend.Rest.Entities.Magic.Spell;
import Backend.Rest.Entities.Magic.SpellLevel;
import Backend.Rest.Entities.Race;
import Backend.Rest.Entities.Skills.Skill;
import Backend.Rest.Entities.Skills.Skillset;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;

@RunWith(MockitoJUnitRunner.class)
public class CharacterTests {

    private Character c;
    private CharacterUtils utils = new CharacterUtils();

    @Before
    public void initialize() {
        c = new Character("Test", 5, Archetype.MAGE, Race.TEMPEST);
        }

    @Test
    public void addToSpellbookTest() {
        PrimaryElement a = new PrimaryElement("Fire", "", 5);
        PrimaryElement b = new PrimaryElement("Water", "", 5);
        SpellLevel uno = new SpellLevel(1, 1, 1, 4, 5);
        Spell one = new Spell("Test One", "", 1,  false, false);
        Spell two = new Spell("Test Two", "", 1, false, false);
        Spell three = new Spell("Test Three", "", 1, false, false);
        one.setLevel(uno);
        two.setLevel(uno);
        three.setLevel(uno);
        one.setElement(a);
        two.setElement(a);
        three.setElement(b);
        assert c.getSpellbook().size() == 0;
        assert utils.getElementsKnown(c).size() == 0;
        c.addToSpellbook(one, 1);
        assert c.getSpellbook().size() == 1;
        assert utils.getElementsKnown(c).size() == 1;
        c.addToSpellbook(two, 1);
        assert c.getSpellbook().size() == 2;
        assert utils.getElementsKnown(c).size() == 1;
        c.addToSpellbook(three, 1);
        assert c.getSpellbook().size() == 3;
        assert utils.getElementsKnown(c).size() == 2;
        assert c.getXp() == 2;
    }

    @Test
    public void addToSkillsetTest() {
        Skill s = new Skill("Test", "", 1, 1, Arrays.asList(Archetype.FIGHTER, Archetype.HUNTER), null, false);
        assert c.getSkillset().size() == 0;
        c.addSkillToSet(new Skillset(c, s, 1), s.getCost());
        assert c.getSkillset().size() == 1;
        assert c.getXp() == 4;
    }

    @Test
    public void addToCraftsSetTest() {
        Craft craft = Craft.PHYSICAL;
        assert c.getCraftsSet().size() == 0;
        c.addCraftToSet(new CraftsSet(c, craft, Level.BASIC), Level.BASIC.getCost());
        assert c.getCraftsSet().size() == 1;
        assert c.getXp() == 3;
    }
}
