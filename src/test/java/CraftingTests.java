import Backend.Rest.Entities.Archetype;
import Backend.Rest.Entities.Character;
import Backend.Rest.Entities.Crafting.Craft;
import Backend.Rest.Entities.Crafting.CraftingUtils;
import Backend.Rest.Entities.Crafting.CraftsSet;
import Backend.Rest.Entities.Crafting.Level;
import Backend.Rest.Entities.Race;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
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
        cha.setCraftsSet(new ArrayList<>());
    }


    @Test
    public void basicCraftNormal() {
        assertTrue(utils.checkPrerequisites(cha, Craft.PHYSICAL, Level.BASIC));
    }

    @Test
    public void basicCraftWrong() {
        assertFalse(utils.checkPrerequisites(cha, Craft.PHYSICAL, Level.ADEPT));
    }

    @Test
    public void nonBasicCraftBasic() {
        assertFalse(utils.checkPrerequisites(cha, Craft.GUNSMITH, Level.BASIC));
    }

    @Test
    public void spellweavingAdeptCorrect() {
        cha.addCraftToSet(new CraftsSet(cha, Craft.MENTAL, Level.BASIC), 1);
        cha.addCraftToSet(new CraftsSet(cha, Craft.PHYSICAL, Level.BASIC), 1);
        assertTrue(utils.checkPrerequisites(cha, Craft.SPELLWEAVING, Level.ADEPT));
    }

    @Test
    public void spellweavingAdeptIncorrect() {
        cha.addCraftToSet(new CraftsSet(cha, Craft.MENTAL, Level.BASIC), 1);
        assertFalse(utils.checkPrerequisites(cha, Craft.SPELLWEAVING, Level.ADEPT));
    }

    @Test
    public void physicalAdeptCorrect() {
        cha.addCraftToSet(new CraftsSet(cha, Craft.PHYSICAL, Level.BASIC), 1);
        assertTrue(utils.checkPrerequisites(cha, Craft.SMITHING, Level.ADEPT));
    }

    @Test
    public void physicalAdeptInorrect() {
        cha.addCraftToSet(new CraftsSet(cha, Craft.MENTAL, Level.BASIC), 1);
        assertFalse(utils.checkPrerequisites(cha, Craft.SMITHING, Level.ADEPT));
    }

    @Test
    public void mentalAdeptCorrect() {
        cha.addCraftToSet(new CraftsSet(cha, Craft.MENTAL, Level.BASIC), 1);
        assertTrue(utils.checkPrerequisites(cha, Craft.ALCHEMY, Level.ADEPT));
    }

    @Test
    public void mentalAdeptIncorrect() {
        cha.addCraftToSet(new CraftsSet(cha, Craft.PHYSICAL, Level.BASIC), 1);
        assertFalse(utils.checkPrerequisites(cha, Craft.ALCHEMY, Level.ADEPT));
    }

    @Test
    public void adeptPlusCorrect() {
        cha.addCraftToSet(new CraftsSet(cha, Craft.SMITHING, Level.ADEPT), 1);
        assertTrue(utils.checkPrerequisites(cha, Craft.SMITHING, Level.EXPERT));
    }

    @Test
    public void adeptPlusIncorrect() {
        cha.addCraftToSet(new CraftsSet(cha, Craft.SMITHING, Level.ADEPT), 1);
        assertFalse(utils.checkPrerequisites(cha, Craft.SMITHING, Level.MASTER));
    }


    @Test
    public void alreadyMastered() {
        cha.addCraftToSet(new CraftsSet(cha, Craft.SMITHING, Level.MASTER), 1);
        assertFalse(utils.levelUpCraft(cha, Craft.SMITHING));
    }

    @Test
    public void attemptToLevelBasic() {
        cha.addCraftToSet(new CraftsSet(cha, Craft.PHYSICAL, Level.BASIC), 1);
        assertFalse(utils.levelUpCraft(cha, Craft.PHYSICAL));
    }

    @Test
    public void noAdeptPrereqs() {
        assertFalse(utils.levelUpCraft(cha, Craft.SMITHING));
    }

    @Test
    public void firstLearnOfBasic() {
        assertTrue(utils.levelUpCraft(cha, Craft.PHYSICAL));
    }

    @Test
    public void prereqsMade() {
        cha.addCraftToSet(new CraftsSet(cha, Craft.SMITHING, Level.ADEPT), 1);
        assertTrue(utils.levelUpCraft(cha, Craft.SMITHING));
        assertTrue(utils.levelUpCraft(cha, Craft.SMITHING));
    }

    @Test
    public void noXpBasic() {
        cha.setXp(0);
        assertFalse(utils.levelUpCraft(cha, Craft.PHYSICAL));
    }

    @Test
    public void noXpAdvanced() {
        cha.addCraftToSet(new CraftsSet(cha, Craft.SMITHING, Level.ADEPT), 20);
        assertFalse(utils.levelUpCraft(cha, Craft.SMITHING));
    }

    @Test
    public void learnAdept() {
        cha.addCraftToSet(new CraftsSet(cha, Craft.PHYSICAL, Level.BASIC), 1);
        assertTrue(utils.levelUpCraft(cha, Craft.SMITHING));
        assertFalse(utils.levelUpCraft(cha, Craft.ALCHEMY));
    }

}
