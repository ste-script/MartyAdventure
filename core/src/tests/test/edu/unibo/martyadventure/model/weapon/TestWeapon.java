package tests.test.edu.unibo.martyadventure.model.weapon;

import java.util.ArrayList;
import java.util.List;

import edu.unibo.martyadventure.model.weapon.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class TestWeapon {

    String name = "Weapon1";
    String type = "Meele";
    int damageMultiplier = 10;
    List<Move> moveList = new ArrayList<>(List.of(Move.SHOOT, Move.HOOK, Move.SHOOT, Move.HOOK));
    Weapon weaponTest = new WeaponFactory().newWeapon(name, type, damageMultiplier, moveList);

    @Test
    void testSetMoveList() {
        List<Move> moveList2 = new ArrayList<>(List.of(Move.HOOK, Move.SHOOT, Move.HOOK, Move.HOOK));
        weaponTest.setMoveList(moveList2);
        assertEquals(moveList2, weaponTest.getMoveList());
        // System.err.println("testSetMoveList ok");
    }

}
