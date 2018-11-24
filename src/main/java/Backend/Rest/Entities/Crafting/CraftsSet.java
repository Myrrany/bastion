package Backend.Rest.Entities.Crafting;

import Backend.Rest.Naming;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import Backend.Rest.Entities.Character;

@Entity
@Getter
@Setter
@Table(name = Naming.CRAFTSET)
public class CraftsSet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Naming.SET_ID)
    private int id;
    @ManyToOne(targetEntity = Character.class)
    @JoinColumn(name = Naming.CHAR)
    private Character character;
    @Enumerated(EnumType.STRING)
    private Craft craft;
    @Enumerated(EnumType.STRING)
    private Level level;

    protected CraftsSet() {
    }

    public CraftsSet(Character character, Craft craft, Level level) {
        this.character = character;
        this.craft = craft;
        this.level = level;
    }
}