package Backend.Rest.Repositories;

import Backend.Rest.Entities.Character;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends CrudRepository<Character, Long>{

   List<Character> findByCharacterName(String name);


}
