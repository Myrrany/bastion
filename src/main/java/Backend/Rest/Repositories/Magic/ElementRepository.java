package Backend.Rest.Repositories.Magic;

import Backend.Rest.Entities.Magic.Element;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ElementRepository extends PagingAndSortingRepository<Element, Integer>{

    List<Element> findByNameContains(String name);
}
