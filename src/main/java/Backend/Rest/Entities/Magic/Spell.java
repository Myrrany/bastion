package Backend.Rest.Entities.Magic;

import Backend.Rest.Naming;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Table(name = Naming.SPELLS)
public class Spell {

    /**
     * Contains the template for Spells.
     *
     * @Author Myrthe Hultermans
     * @Version 0.1
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Naming.SPELL_ID)
    private int id;
    @Column(name = Naming.SPELL_NAME)
    public String name;
    @Column(name = Naming.SPELL_DESC)
    private String description;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Naming.ELEM_ID)
    private Element element;
    @OneToOne
    @JoinColumn(name = Naming.SPELL_LVL)
    private SpellLevel level;
    private int range;
    private boolean touch;
    private boolean self;

    protected Spell() {
    }

    public Spell(String name, String descr, int range, boolean t, boolean s) {
        this.name = name;
        this.description = descr;
        this.range = range;
        this.touch = t;
        this.self = s;
    }

}
