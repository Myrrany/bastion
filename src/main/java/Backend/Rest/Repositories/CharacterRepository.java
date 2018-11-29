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

    List<Character> findByXpBetweenAndCharacterNameContains(int min, int max, String name);

    List<Character> findByXpBetweenAndArchetypeAndCharacterNameContains(int min, int max, Archetype archetype, String name);

    List<Character> findByXpBetweenAndRaceAndCharacterNameContains(int min, int max, Race race, String name);

    List<Character> findByXpBetweenAndArchetypeAndRaceAndCharacterNameContains(int min, int max, Archetype archetype, Race race, String name);
}
