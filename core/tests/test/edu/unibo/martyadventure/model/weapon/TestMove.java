package test.edu.unibo.martyadventure.model.weapon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import edu.unibo.martyadventure.model.weapon.Move;

public class TestMove {

    Move HOOK1 = Move.HOOK;

    @Test
    void testLoadingMove() {
        assertEquals(HOOK1.getName(), Move.HOOK.getName());
        assertEquals(HOOK1.getDamage(), Move.HOOK.getDamage());
        assertEquals(HOOK1.getFailRatio(), Move.HOOK.getFailRatio());
        assertEquals(HOOK1.getReloadTime(), Move.HOOK.getReloadTime());
        assertEquals(HOOK1.getType(), Move.HOOK.getType());
    }

    @Test
    void testIsUsable() {
        // HOOK1 reloadTime -> 1
        // HOOK1 lastUse -> 1
        assertFalse(HOOK1.isUsable(2, 2));
        assertTrue(HOOK1.isUsable(2, 1));
    }

    @Test
    void testGetRandomMeleeMove() {
        assertEquals(Move.getRandomMeleeMove().getType(), Move.MoveType.MELEE);
    }

    @Test
    void testGetRandomRangedMove() {
        assertEquals(Move.getRandomRangedMove().getType(), Move.MoveType.RANGED);
    }
}
