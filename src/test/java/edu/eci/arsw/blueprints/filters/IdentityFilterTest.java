package edu.eci.arsw.blueprints.filters;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IdentityFilterTest {

    @Test
    void applyReturnsSameBlueprintInstanceOrEquivalent() {
        Blueprint original = new Blueprint("a","n", List.of(new Point(1,1), new Point(2,2)));
        IdentityFilter f = new IdentityFilter();
        Blueprint out = f.apply(original);
        assertEquals(original, out);
        assertEquals(original.getPoints(), out.getPoints());
    }
}