package Skills;

import Backend.Rest.Entities.Archetype;
import Backend.Rest.Entities.Character;
import Backend.Rest.Entities.Race;
import Backend.Rest.Entities.Skills.Skill;
import Backend.Rest.Entities.Skills.SkillUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class SkillLearningTests {

    private Skill one, two;
    private Character cha;
    private SkillUtils utils = new SkillUtils();
        @Before
        public void learnInit(){
            one = new Skill("One", "", 2, 1, Arrays.asList(Archetype.HUNTER, Archetype.FIGHTER), null);
            two = new Skill("Two", "", 2, 1, Arrays.asList(Archetype.CRAFTER, Archetype.HUNTER), one);
            cha = new Character("Test", 10, Archetype.CRAFTER, Race.TEMPEST);
            utils.addSkill(one);
            utils.addSkill(two);
        }

        @Test
        public void alreadyLearntTest(){
            cha.addSkillToSet(one, 1);
            assertFalse(utils.learnSkill(cha, one));
        }

        @Test
        public void missingPrereq(){
            assertFalse(utils.learnSkill(cha, two));
        }

        @Test
        public void noXp(){
            cha.setXp(0);
            assertFalse(utils.learnSkill(cha, one));
        }

        @Test
        public void correctNoPrereq(){
            assertTrue(utils.learnSkill(cha, one));
        }

        @Test
        public void correctPrereq(){
            cha.addSkillToSet(one, 1);
            assertTrue(utils.learnSkill(cha, two));
        }

}
