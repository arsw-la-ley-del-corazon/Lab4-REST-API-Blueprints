package edu.eci.arsw.blueprints.filters;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RedundancyFilterTest {

    @Test
    void removesConsecutiveDuplicatePoints() {
        var points = List.of(new Point(0,0), new Point(0,0), new Point(1,1), new Point(1,1), new Point(2,2));
        var bp = new Blueprint("a","n", points);
        var f = new RedundancyFilter();
        var out = f.apply(bp);
        assertEquals(List.of(new Point(0,0), new Point(1,1), new Point(2,2)), out.getPoints());
    }

    @Test
    void keepsNonConsecutiveDuplicates() {
        var points = List.of(new Point(0,0), new Point(1,1), new Point(0,0));
        var bp = new Blueprint("a","n", points);
        var f = new RedundancyFilter();
        var out = f.apply(bp);
        assertEquals(points, out.getPoints());
    }
}
