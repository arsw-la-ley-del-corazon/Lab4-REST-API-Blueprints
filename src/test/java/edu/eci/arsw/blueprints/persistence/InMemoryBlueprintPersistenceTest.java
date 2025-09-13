package edu.eci.arsw.blueprints.persistence;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryBlueprintPersistenceTest {

    @Test
    void saveAndRetrieveBlueprint() throws Exception {
        var repo = new InMemoryBlueprintPersistence();
        var bp = new Blueprint("alice","house", List.of(new Point(1,1)));
        repo.saveBlueprint(bp);

        var found = repo.getBlueprint("alice","house");
        assertEquals(bp, found);
        assertEquals(1, found.getPoints().size());
    }

    @Test
    void preventsDuplicateSave() throws Exception {
        var repo = new InMemoryBlueprintPersistence();
        var bp = new Blueprint("a","x", List.of());
        repo.saveBlueprint(bp);
        assertThrows(BlueprintPersistenceException.class, () -> repo.saveBlueprint(new Blueprint("a","x", List.of())));
    }

    @Test
    void getByAuthorAndAll() throws Exception {
        var repo = new InMemoryBlueprintPersistence();
        repo.saveBlueprint(new Blueprint("bob","b1", List.of()));
        repo.saveBlueprint(new Blueprint("bob","b2", List.of()));
        repo.saveBlueprint(new Blueprint("carol","c1", List.of()));
        Set<Blueprint> bob = repo.getBlueprintsByAuthor("bob");
        assertEquals(2, bob.size());
        assertTrue(repo.getAllBlueprints().size() >= 3);
    }

    @Test
    void addPointWorksAndThrowsWhenMissing() throws Exception {
        var repo = new InMemoryBlueprintPersistence();
        repo.saveBlueprint(new Blueprint("a","n", List.of(new Point(0,0))));
        repo.addPoint("a","n", 5, 6);
        assertEquals(2, repo.getBlueprint("a","n").getPoints().size());

        assertThrows(BlueprintNotFoundException.class, () -> repo.addPoint("no","no", 1,1));
    }
}
