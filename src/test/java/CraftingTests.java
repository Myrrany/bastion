import Backend.Rest.Entities.Archetype;
import Backend.Rest.Entities.Character;
import Backend.Rest.Entities.Crafting.Craft;
import Backend.Rest.Entities.Crafting.CraftingUtils;
import Backend.Rest.Entities.Crafting.Level;
import Backend.Rest.Entities.Race;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashMap;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class CraftingTests {

    private CraftingUtils utils = new CraftingUtils();
    private static Character cha;

    @BeforeClass
    public static void init() {
        cha = new Character("Test", 20, Archetype.CRAFTER, Race.TEMPEST);
    }

    @Before
    public void prepare() {
        cha.setXp(20);
        cha.setCraftsSet(new HashMap<>());
    }


    @Test
    void basicCraftNormal() {
        assertTrue(utils.checkPrerequisites(cha, Craft.PHYSICAL, Level.BASIC));
    }

    @Test
    void basicCraftWrong() {
        assertFalse(utils.checkPrerequisites(cha, Craft.PHYSICAL, Level.ADEPT));
    }

    @Test
    void nonBasicCraftBasic() {
        assertFalse(utils.checkPrerequisites(cha, Craft.GUNSMITH, Level.BASIC));
    }

    @Test
    void spellweavingAdeptCorrect() {
        cha.addCraftToSet(Craft.MENTAL, Level.BASIC, 1);
        cha.addCraftToSet(Craft.PHYSICAL, Level.BASIC, 1);
        assertTrue(utils.checkPrerequisites(cha, Craft.SPELLWEAVING, Level.ADEPT));
    }

    @Test
    void spellweavingAdeptIncorrect() {
        cha.addCraftToSet(Craft.MENTAL, Level.BASIC, 1);
        assertFalse(utils.checkPrerequisites(cha, Craft.SPELLWEAVING, Level.ADEPT));
    }

    @Test
    void physicalAdeptCorrect() {
        cha.addCraftToSet(Craft.PHYSICAL, Level.BASIC, 1);
        assertTrue(utils.checkPrerequisites(cha, Craft.SMITHING, Level.ADEPT));
    }

    @Test
    void physicalAdeptInorrect() {
        cha.addCraftToSet(Craft.MENTAL, Level.BASIC, 1);
        assertFalse(utils.checkPrerequisites(cha, Craft.SMITHING, Level.ADEPT));
    }

    @Test
    void mentalAdeptCorrect() {
        cha.addCraftToSet(Craft.MENTAL, Level.BASIC, 1);
        assertTrue(utils.checkPrerequisites(cha, Craft.ALCHEMY, Level.ADEPT));
    }

    @Test
    void mentalAdeptIncorrect() {
        cha.addCraftToSet(Craft.PHYSICAL, Level.BASIC, 1);
        assertFalse(utils.checkPrerequisites(cha, Craft.ALCHEMY, Level.ADEPT));
    }

    @Test
    void adeptPlusCorrect() {
        cha.addCraftToSet(Craft.SMITHING, Level.ADEPT, 1);
        assertTrue(utils.checkPrerequisites(cha, Craft.SMITHING, Level.EXPERT));
    }

    @Test
    void adeptPlusIncorrect() {
        cha.addCraftToSet(Craft.SMITHING, Level.ADEPT, 1);
        assertFalse(utils.checkPrerequisites(cha, Craft.SMITHING, Level.MASTER));
    }


    @Test
    void alreadyMastered() {
        cha.addCraftToSet(Craft.SMITHING, Level.MASTER, 1);
        assertFalse(utils.levelUpCraft(cha, Craft.SMITHING));
    }

    @Test
    void attemptToLevelBasic() {
        cha.addCraftToSet(Craft.PHYSICAL, Level.BASIC, 1);
        assertFalse(utils.levelUpCraft(cha, Craft.PHYSICAL));
    }

    @Test
    void noAdeptPrereqs() {
        assertFalse(utils.levelUpCraft(cha, Craft.SMITHING));
    }

    @Test
    void firstLearnOfBasic() {
        assertTrue(utils.levelUpCraft(cha, Craft.PHYSICAL));
    }

    @Test
    void prereqsMade() {
        cha.addCraftToSet(Craft.SMITHING, Level.ADEPT, 1);
        assertTrue(utils.levelUpCraft(cha, Craft.SMITHING));
        assertTrue(utils.levelUpCraft(cha, Craft.SMITHING));
    }

    @Test
    void noXpBasic() {
        cha.setXp(0);
        assertFalse(utils.levelUpCraft(cha, Craft.PHYSICAL));
    }

    @Test
    void noXpAdvanced() {
        cha.addCraftToSet(Craft.SMITHING, Level.ADEPT, 20);
        assertFalse(utils.levelUpCraft(cha, Craft.SMITHING));
    }

    @Test
    void learnAdept() {
        cha.addCraftToSet(Craft.PHYSICAL, Level.ADEPT, 1);
        assertTrue(utils.levelUpCraft(cha, Craft.SMITHING));
        assertFalse(utils.levelUpCraft(cha, Craft.ALCHEMY));
    }

}
