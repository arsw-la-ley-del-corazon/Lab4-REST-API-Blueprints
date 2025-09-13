package edu.eci.arsw.blueprints.services;

import edu.eci.arsw.blueprints.filters.BlueprintsFilter;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistence;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BlueprintsServicesTest {

    @Test
    void saveDelegatesToPersistence() throws Exception {
        var persistence = mock(BlueprintPersistence.class);
        var filter = mock(BlueprintsFilter.class);
        var services = new BlueprintsServices(persistence, filter);

        var bp = new Blueprint("a","n", List.of());
        services.addNewBlueprint(bp);

        verify(persistence).saveBlueprint(bp);
    }

    @Test
    void getBlueprintAppliesFilter() throws Exception {
        var persistence = mock(BlueprintPersistence.class);
        var filter = mock(BlueprintsFilter.class);
        var services = new BlueprintsServices(persistence, filter);

        var original = new Blueprint("a","n", List.of(new Point(1,1)));
        var filtered = new Blueprint("a","n", List.of(new Point(2,2)));

        when(persistence.getBlueprint("a","n")).thenReturn(original);
        when(filter.apply(original)).thenReturn(filtered);

        var out = services.getBlueprint("a","n");
        assertEquals(filtered.getPoints(), out.getPoints());
    }

    @Test
    void exceptionsPropagate() throws Exception {
        var persistence = mock(BlueprintPersistence.class);
        var filter = mock(BlueprintsFilter.class);
        var services = new BlueprintsServices(persistence, filter);

        when(persistence.getBlueprintsByAuthor("z")).thenThrow(new BlueprintNotFoundException("x"));
        assertThrows(BlueprintNotFoundException.class, () -> services.getBlueprintsByAuthor("z"));

        doThrow(new BlueprintPersistenceException("dup")).when(persistence).saveBlueprint(any());
        assertThrows(BlueprintPersistenceException.class, () -> services.addNewBlueprint(new Blueprint("a","b", List.of())));
    }
}
