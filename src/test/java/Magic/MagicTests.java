package Magic;

import Backend.Rest.Entities.Magic.*;
import Backend.Rest.Entities.Race;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;

@RunWith(MockitoJUnitRunner.class)
public class MagicTests {

    private MagicUtils utils = new MagicUtils();

    @Before
    public void initialize() {
        utils.setPrimaries(new ArrayList<>());
        utils.setSecondaries(new ArrayList<>());
        utils.setTertiaries(new ArrayList<>());
    }

    @Test
    void addElementsTest() {
        assert utils.getPrimaries().size() == 0;
        assert utils.getSecondaries().size() == 0;
        assert utils.getTertiaries().size() == 0;
        PrimaryElement a = new PrimaryElement("Fire", "", 5);
        System.out.println(utils.getPrimaries().size());
        assert utils.getPrimaries().size() == 1;
        PrimaryElement b = new PrimaryElement("Water", "", 5);
        SecondaryElement c = new SecondaryElement("Mist", "", 5, a, b, 3);
        assert utils.getSecondaries().size() == 1;
        TertiaryElement d = new TertiaryElement("Time", "", 5, 3, 4, Race.TEMPEST);
        assert utils.getTertiaries().size() == 1;
    }

    @Test
    void secondaryPrerequisitesTest() {
        PrimaryElement a = new PrimaryElement("Fire", "", 5);
        PrimaryElement b = new PrimaryElement("Water", "", 5);
        SecondaryElement c = new SecondaryElement("Mist", "", 5, a, b, 3);
        List<PrimaryElement> list = c.getPrerequisites();
        assert list.size() == 2;
        assert list.contains(a);
        assert list.contains(b);
        assertFalse(c.setPrerequisites(a, a));
    }

}
