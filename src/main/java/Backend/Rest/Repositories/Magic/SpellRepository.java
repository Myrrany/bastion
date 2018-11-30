package Backend.Rest.Repositories.Magic;

import Backend.Rest.Entities.Magic.Spell;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface SpellRepository extends PagingAndSortingRepository <Spell, Integer>{

    List<Spell> findByNameContains(String name);
}
