package Magic;

import Backend.Rest.Entities.Archetype;
import Backend.Rest.Entities.Character;
import Backend.Rest.Entities.Magic.*;
import Backend.Rest.Entities.Race;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class MagicTestPrereqsTests {
    private Character cha = new Character("Test", 5, Archetype.MAGE, Race.TEMPEST);
    private Spell one, two, three;
    private MagicUtils utils = new MagicUtils();
    private PrimaryElement a;
    private SecondaryElement c;
    private TertiaryElement d;
    private SpellLevel uno;

    @Before
    public void SecondaryInit() {
        a = new PrimaryElement("Fire", "", 5);
        PrimaryElement b = new PrimaryElement("Water", "", 5);
        c = new SecondaryElement("Mist", "", 5, a, b, 1);
        d = new TertiaryElement("Time", "", 5, 3, 3, Race.TEMPEST);
        uno = new SpellLevel(1, 1, 1, 4, 5);
        one = new Spell("Test One", "", 1, false, false);
        two = new Spell("Test Two", "", 1, false, false);
        three = new Spell("Test Three", "", 1, false, false);
        one.setLevel(uno);
        two.setLevel(uno);
        three.setLevel(uno);
        utils.addSpell(one, uno, a);
        utils.addSpell(two, uno, b);
        utils.addSpell(three, uno, c);
    }

    @Test
    public void secondaryCheckRegularTrueTest() {
        cha.addToSpellbook(one, 1);
        cha.addToSpellbook(two, 1);
        assertTrue(utils.SecondaryCheck(cha, c));
    }

    @Test
    public void secondaryCheckRegularFalseTest() {
        cha.addToSpellbook(one, 1);
        assertFalse(utils.SecondaryCheck(cha, c));
    }

    @Test
    public void secondaryCheckIrregularTest() {
        cha.addToSpellbook(three, 1);
        assertTrue(utils.SecondaryCheck(cha, c));
    }

    @Test
    public void tertiaryCheckRegularTrueTest() {
        cha.addToSpellbook(one, 1);
        cha.addToSpellbook(two, 1);
        cha.addToSpellbook(three, 1);
        assertTrue(utils.TertiaryCheck(cha, d));
    }

    @Test
    public void tertiaryCheckRegularFalseRaceTest() {
        cha.addToSpellbook(one, 1);
        cha.addToSpellbook(two, 1);
        cha.addToSpellbook(three, 1);
        cha.setRace(Race.VOIDLING);
        assertFalse(utils.TertiaryCheck(cha, d));
    }

    @Test
    public void tertiaryCheckRegularFalseElementsTest() {
        Spell four = new Spell("Test Three", "", 1, false, false);
        utils.addSpell(four, uno, a);
        cha.addToSpellbook(one, 1);
        cha.addToSpellbook(three, 1);
        cha.addToSpellbook(four, 1);
        assertFalse(utils.TertiaryCheck(cha, d));
    }

    @Test
    public void tertiaryCheckRegularFalseAmountTest() {
        d.setPrerequisiteSpells(4);
        cha.addToSpellbook(one, 1);
        cha.addToSpellbook(two, 1);
        cha.addToSpellbook(three, 1);
        assertFalse(utils.TertiaryCheck(cha, d));
    }

    @Test
    public void tertiaryCheckIrregular() {
        Spell four = new Spell("Test Three", "", 1, false, false);
        utils.addSpell(four, uno, d);
        cha.addToSpellbook(four, 1);
        assertTrue(utils.TertiaryCheck(cha, d));
    }
}
