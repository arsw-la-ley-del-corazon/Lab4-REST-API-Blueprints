package edu.eci.arsw.blueprints.model;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BlueprintTest {

    @Test
    void equalsAndHashCodeUseAuthorAndNameOnly() {
        Blueprint a = new Blueprint("alice","house", List.of(new Point(1,1)));
        Blueprint b = new Blueprint("alice","house", List.of(new Point(2,2), new Point(3,3)));

        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test
    void addPointMutatesPointsButGetterIsUnmodifiable() {
        Blueprint bp = new Blueprint("bob","bridge", List.of(new Point(0,0)));
        bp.addPoint(new Point(1,1));
        assertEquals(2, bp.getPoints().size());

        assertThrows(UnsupportedOperationException.class, () -> bp.getPoints().add(new Point(9,9)));
    }
}