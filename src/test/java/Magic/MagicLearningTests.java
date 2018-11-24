package Magic;

import Backend.Rest.Entities.Archetype;
import Backend.Rest.Entities.Character;
import Backend.Rest.Entities.Magic.*;
import Backend.Rest.Entities.Race;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MagicLearningTests {
    private Character cha = new Character("Test", 5, Archetype.MAGE, Race.TEMPEST);
    private Spell one, two, three, four;
    private MagicUtils utils = new MagicUtils();
    private PrimaryElement a, b;
    private SecondaryElement c;
    private TertiaryElement d;
    private SpellLevel uno;

    @Before
    public void LearnSpellInit() {
        a = new PrimaryElement("Fire", "", 5);
        b = new PrimaryElement("Water", "", 5);
        c = new SecondaryElement("Mist", "", 5, a, b, 1);
        d = new TertiaryElement("Time", "", 5, 3, 3, Race.TEMPEST);
        uno = new SpellLevel(1, 1, 1, 4, 5);
        one = new Spell("Test One", "", 1, false, false);
        two = new Spell("Test Two", "", 1, false, false);
        three = new Spell("Test Three", "", 1, false, false);
        four = new Spell("Test Four", "", 1, false, false);
        utils.addSpell(one, uno, a);
        utils.addSpell(two, uno, b);
        utils.addSpell(three, uno, c);
        utils.addSpell(four, uno, d);

    }

    @Test
    void elemNullTest() {
        Spell s = new Spell("Wrong", "Completely", 1, false, false);
        assertFalse(utils.learnSpell(cha, s));
    }

    @Test
    void noXpTest() {
        cha.setXp(0);
        assertFalse(utils.learnSpell(cha, one));
    }

    @Test
    void alreadyLearntTest() {
        cha.addToSpellbook(one, 1);
        assertFalse(utils.learnSpell(cha, one));
    }

    @Test
    void primaryOkayTest() {
        assertTrue(utils.learnSpell(cha, one));
    }

    @Test
    void secondaryNopeTest() {
        assertFalse(utils.learnSpell(cha, three));
    }

    @Test
    void secondaryOkayTest() {
        utils.learnSpell(cha, one);
        utils.learnSpell(cha, two);
        assertTrue(utils.learnSpell(cha, three));
    }

    @Test
    void tertiaryNopeTest() {
        assertFalse(utils.learnSpell(cha, four));
    }

    @Test
    void tertiaryOkayTest() {
        utils.learnSpell(cha, one);
        utils.learnSpell(cha, two);
        utils.learnSpell(cha, three);
        assertTrue(utils.learnSpell(cha, four));
    }
}

