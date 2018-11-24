package Backend.Rest.Entities.Magic;

import Backend.Rest.Naming;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = Naming.SPELL_LVLS)
public class SpellLevel {

    @Id
    @Column(name = Naming.SPELL_LVL)
    private int level;
    private int cost;
    private int discountCost;
    private int castingTime;
    private int cooldown;

    protected SpellLevel() {
    }

    public SpellLevel(int cost, int discountCost, int level, int castingTime, int cooldown) {
        this.cost = cost;
        this.discountCost = discountCost;
        this.level = level;
        this.castingTime = castingTime;
        this.cooldown = cooldown;
        MagicUtils.addLevel(this);
    }
}
