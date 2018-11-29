package Backend.Rest.Entities.Skills;

import Backend.Rest.Entities.Archetype;
import Backend.Rest.Naming;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = Naming.SKILLS)
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@JsonSerialize
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Naming.ID)
    private int id;
    public String skillName;
    private String skillDescription;
    private int cost;
    private int discountCost;
    @ElementCollection
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = Naming.SKILL_ARCH)
    @Column(name = Naming.ARCH)
    private List<Archetype> discountArchetypes;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = Naming.SKILL_PREREQ,
    joinColumns = {@JoinColumn(name = Naming.PARENT)},
    inverseJoinColumns = {@JoinColumn(name = Naming.CHILD)})
    private Skill prerequisite;

    protected Skill() {
    }

    public Skill(String name, String skill_description, int cost, int discountCost, List<Archetype> discountArchetypes, Skill prerequisite) {
        this.skillName = name;
        this.skillDescription = skill_description;
        this.cost = cost;
        this.discountCost = discountCost;
        this.discountArchetypes = discountArchetypes;
        this.prerequisite = prerequisite;
    }

}
