package Backend.Rest.Entities;

import Backend.Rest.Entities.Crafting.CraftsSet;
import Backend.Rest.Entities.Magic.Spell;
import Backend.Rest.Entities.Skills.Skill;
import Backend.Rest.Entities.Skills.Skillset;
import Backend.Rest.Naming;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Table(name = Naming.CHARS)
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Naming.CHAR_ID)
    private int characterId;
    private String characterName;
    private int xp;
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = Naming.SPELLBOOK,
            joinColumns = {@JoinColumn(name = Naming.CHAR_ID)},
            inverseJoinColumns = {@JoinColumn(name = Naming.SPELL_ID)})
    private List<Spell> spellbook;
    @Enumerated(EnumType.STRING)
    private Archetype archetype;
    @Enumerated(EnumType.STRING)
    private Race race;
    @JsonManagedReference
    @OneToMany(mappedBy = "character")
    private List<Skillset> skillset;
    @JsonManagedReference
    @OneToMany(mappedBy = "character")
    private List<CraftsSet> craftsSet;


    public Character(String characterName, int xp, Archetype a, Race race) {
        this.characterName = characterName;
        this.xp = xp;
        this.archetype = a;
        this.race = race;
        this.spellbook = new ArrayList<>();
        this.skillset = new ArrayList<>();
        this.craftsSet = new ArrayList<>();
    }

    protected Character() {
    }

    public void addToSpellbook(Spell s, int cost) {
        this.spellbook.add(s);
        this.xp -= cost;
    }

    public void addSkillToSet(Skillset s, int cost) {
        boolean notNew = false;
        for (Skillset skills : this.skillset) {
            if (skills.getSkill() == s.getSkill()) {
                notNew = true;
                skills.setTimesTaken(skills.getTimesTaken() + 1);
            }
        }
        if (!notNew) {
            this.skillset.add(s);

        }
        this.xp -= cost;
    }

    public void addCraftToSet(CraftsSet c, int cost) {
        boolean notNew = false;
        for (CraftsSet crafts : this.craftsSet) {
            if (crafts.getCraft() == c.getCraft()) {
                notNew = true;
                crafts.setLevel(c.getLevel());
            }
        }
        if (!notNew) {
            this.craftsSet.add(c);

        }
        this.xp -= cost;
    }

    public String toString() {
        return "Name: " +
                this.characterName +
                ", XP: " +
                this.xp +
                ", Race: " +
                this.race +
                ", Archetype: " +
                this.archetype;
    }
}
