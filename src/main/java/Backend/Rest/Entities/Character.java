package Backend.Rest.Entities;

import Backend.Rest.Entities.Crafting.Craft;
import Backend.Rest.Entities.Crafting.Level;
import Backend.Rest.Entities.Magic.Spell;
import Backend.Rest.Entities.Skills.Skill;
import Backend.Rest.Naming;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
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
            joinColumns = {@JoinColumn(name = Naming.SPELL_ID)},
            inverseJoinColumns = {@JoinColumn(name = Naming.CHAR_ID)})
    private List<Spell> spellbook;
    @Enumerated(EnumType.STRING)
    @JoinColumn(name = Naming.ARCH_ID)
    private Archetype archetypeId;
    @Enumerated(EnumType.STRING)
    @JoinColumn(name = Naming.RACE_ID)
    private Race raceId;
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = Naming.SKILLSET,
            joinColumns = {@JoinColumn(name = Naming.SKILL_ID)},
            inverseJoinColumns = {@JoinColumn(name = Naming.CHAR_ID)})
    private List<Skill> skillset;
    @ElementCollection
    @Enumerated(EnumType.STRING)
    @JoinTable(name = Naming.CRAFTSET,
            joinColumns = {@JoinColumn(name = Naming.CHAR_ID, referencedColumnName = Naming.CHAR_ID)})
    @MapKeyColumn(name = Naming.CRAFT_ID)
    @Column(name = Naming.CRAFT_LVL)
    private Map<Craft, Level> craftsSet;


    public Character(String characterName, int xp, Archetype a, Race race) {
        this.characterName = characterName;
        this.xp = xp;
        this.archetypeId = a;
        this.raceId = race;
        this.spellbook = new ArrayList<>();
        this.skillset = new ArrayList<>();
        this.craftsSet = new HashMap<>();
    }

    protected Character() {
    }

    public void addToSpellbook(Spell s, int cost) {
        this.spellbook.add(s);
        this.xp -= cost;
    }

    public void addSkillToSet(Skill s, int cost) {
        this.skillset.add(s);
        this.xp -= cost;
    }

    public void addCraftToSet(Craft c, Level l, int cost) {
        this.craftsSet.put(c, l);
        this.xp -= cost;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ");
        sb.append(this.characterName);
        sb.append("\nXP: ");
        sb.append(this.xp);
        sb.append("\nRace: ");
        sb.append(this.raceId);
        sb.append("\nArchetype: ");
        sb.append(this.archetypeId);
        sb.append("\n");
        return sb.toString();
    }
}
