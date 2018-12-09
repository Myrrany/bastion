package Backend.Rest.Entities.Skills;

import Backend.Rest.Naming;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import Backend.Rest.Entities.Character;
import org.springframework.data.rest.core.annotation.RestResource;

@Data
@Entity
@RestResource
@Table(name = Naming.SKILLSET)
public class Skillset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Naming.ID)
    private int id;
    @JsonBackReference
    @ManyToOne(targetEntity = Character.class)
    @JoinColumn(name = Naming.CHAR_ID)
    private Character character;
    @ManyToOne(targetEntity = Skill.class)
    @JoinColumn(name = Naming.SKILL_ID)
    private Skill skill;
    private int timesTaken;

    protected Skillset() {
    }

    public Skillset(Character character, Skill skill, int timesTaken) {
        this.character = character;
        this.skill = skill;
        this.timesTaken = timesTaken;
    }
}