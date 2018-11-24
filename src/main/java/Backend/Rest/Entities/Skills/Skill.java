package Backend.Rest.Entities.Skills;

import Backend.Rest.Entities.Archetype;
import Backend.Rest.Naming;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = Naming.SKILLS)
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int skill_id;
    @Column(name = Naming.SKILL_NAME)
    public String name;
    private String skill_description;
    private int cost;
    private int discountCost;
    @ElementCollection
    @Enumerated
    @JoinTable(name = Naming.SKILL_ARCH,
            joinColumns = {@JoinColumn(name = Naming.ARCH_ID)})
    @Column(name = Naming.SKILL_ID)
    private List<Archetype> discountArchetypes;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Naming.SKILL_ID)
    private Skill prerequisite;

    protected Skill() {
    }

    public Skill(String name, String skill_description, int cost, int discountCost, List<Archetype> discountArchetypes, Skill prerequisite) {
        this.name = name;
        this.skill_description = skill_description;
        this.cost = cost;
        this.discountCost = discountCost;
        this.discountArchetypes = discountArchetypes;
        this.prerequisite = prerequisite;
    }

}