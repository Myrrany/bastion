package Backend.Rest.Repositories.Skills;

import Backend.Rest.Entities.Skills.Skill;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillRepository extends PagingAndSortingRepository<Skill, Integer>{

    List<Skill> findBySkillNameContains(String name);


}
