package Backend.Rest.Repositories;

import Backend.Rest.Entities.Archetype;
import Backend.Rest.Entities.Character;
import Backend.Rest.Entities.Race;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharacterRepository extends PagingAndSortingRepository<Character, Integer> {

    List<Character> findByCharacterName(String name);

    List<Character> findByCharacterNameContains(String sub);

    List<Character> findByArchetype(Archetype archetype);

    List<Character> findByRace(Race race);

    List<Character> findByXpGreaterThanEqualAndXpLessThanEqual(int min, int max);

}
