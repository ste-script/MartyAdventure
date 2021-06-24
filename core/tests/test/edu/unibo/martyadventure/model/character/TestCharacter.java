package test.edu.unibo.martyadventure.model.character;

import static org.junit.jupiter.api.Assertions.*;

import edu.unibo.martyadventure.model.character.Character;
import test.edu.unibo.martyadventure.model.TestCharacterFactory;

class TestCharacter {

    protected void testLoadingCharacter(final Character characterTest, final String name) {
        assertEquals(name, characterTest.getName());
        assertEquals(TestCharacterFactory.HP, characterTest.getHp());
        assertEquals(TestCharacterFactory.WEAPON, characterTest.getWeapon());
    }
}
