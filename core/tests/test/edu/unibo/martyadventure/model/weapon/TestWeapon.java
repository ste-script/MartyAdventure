package test.edu.unibo.martyadventure.model.weapon;

import java.util.ArrayList;
import java.util.List;
import edu.unibo.martyadventure.model.weapon.*;
import edu.unibo.martyadventure.model.weapon.Weapon.WeaponType;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TestWeapon {

    String name = "Weapon1";
    WeaponType type = WeaponType.MELEE;
    int damageMultiplier = 10;
    List<Move> moveList = new ArrayList<>(List.of(Move.UPPERCUT, Move.HOOK, Move.JAB, Move.SUPERMANPUNCH));
    Weapon weaponTest = WeaponFactory.newWeapon(name, type, damageMultiplier, moveList);

    @Test
    void testSetMoveList() {
        List<Move> moveList2 = new ArrayList<>(List.of(Move.UPPERCUT, Move.HOOK, Move.JAB, Move.SUPERMANPUNCH));
        weaponTest.setMoveList(moveList2);
        assertEquals(moveList2, weaponTest.getMoveList());
    }
}
