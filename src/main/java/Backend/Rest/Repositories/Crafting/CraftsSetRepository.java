package Backend.Rest.Repositories.Crafting;

import Backend.Rest.Entities.Character;
import Backend.Rest.Entities.Crafting.Craft;
import Backend.Rest.Entities.Crafting.CraftsSet;
import Backend.Rest.Entities.Crafting.Level;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CraftsSetRepository extends CrudRepository<CraftsSet, Integer>{

    List<CraftsSet> findByCharacter(Character character);

    List<CraftsSet> findByCraft(Craft craft);

    List<CraftsSet> findByLevel(Level level);

    List<CraftsSet> findByCraftAndLevel(Craft craft, Level level);

    List<CraftsSet> deleteByCharacter(Character character);
}