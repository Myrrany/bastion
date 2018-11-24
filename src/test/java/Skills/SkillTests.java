package Skills;

import Backend.Rest.Entities.Archetype;
import Backend.Rest.Entities.Character;
import Backend.Rest.Entities.Race;
import Backend.Rest.Entities.Skills.Skill;
import Backend.Rest.Entities.Skills.SkillUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class SkillTests {

    private Skill one, two;
    private Character cha;
    private SkillUtils utils = new SkillUtils();

    @Before
    public void init() {
        one = new Skill("One", "", 2, 1, Arrays.asList(Archetype.HUNTER, Archetype.FIGHTER), null);
        two = new Skill("Two", "", 2, 1, Arrays.asList(Archetype.CRAFTER, Archetype.HUNTER), one);
        cha = new Character("Test", 10, Archetype.CRAFTER, Race.TEMPEST);
    }

    @Test
    void addPrerequisiteTest() {
        assertTrue(utils.getPrerequisites().size() == 0);
        utils.addPrerequisite(one);
        assertTrue(utils.getPrerequisites().size() == 1);
        utils.addPrerequisite(two);
        assertTrue(utils.getPrerequisites().size() == 2);
    }

    @Test
    void addDiscountTypesTest() {
        assertTrue(utils.getArchDiscounts().size() == 0);
        utils.addDiscountTypes(one);
        assertTrue(utils.getArchDiscounts().size() == 1);
        utils.addDiscountTypes(two);
        assertTrue(utils.getArchDiscounts().size() == 2);
    }

    @Test
    void addSkillTest() {
        assertTrue(utils.getSkillList().size() == 0);
        utils.addSkill(one);
        assertTrue(utils.getSkillList().contains(one));
    }

    @Test
    void checkDiscountTest() {
        utils.addSkill(one);
        utils.addSkill(two);
        assertFalse(utils.checkDiscount(one, cha));
        assertTrue(utils.checkDiscount(two, cha));
    }


    @After
    void cleanUp() {
        utils.setArchDiscounts(new HashMap<>());
        utils.setPrerequisites(new HashMap<>());
        utils.setSkillList(new ArrayList<>());
    }

}
