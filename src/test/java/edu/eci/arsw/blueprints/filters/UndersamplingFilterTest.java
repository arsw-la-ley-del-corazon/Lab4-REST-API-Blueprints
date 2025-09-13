package edu.eci.arsw.blueprints.filters;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UndersamplingFilterTest {

    @Test
    void keepsEveryOtherPointStartingFromIndexZero() {
        var bp = new Blueprint("a","n", List.of(
                new Point(0,0), // 0 keep
                new Point(1,1), // 1 skip
                new Point(2,2), // 2 keep
                new Point(3,3), // 3 skip
                new Point(4,4)  // 4 keep
        ));
        var f = new UndersamplingFilter();
        var out = f.apply(bp);
        assertEquals(List.of(new Point(0,0), new Point(2,2), new Point(4,4)), out.getPoints());
    }

    @Test
    void smallBlueprintsRemainUnchanged() {
        var bp = new Blueprint("a","n", List.of(new Point(0,0), new Point(1,1)));
        var f = new UndersamplingFilter();
        var out = f.apply(bp);
        assertEquals(bp.getPoints(), out.getPoints());
    }
}
