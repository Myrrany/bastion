package Backend.Rest.Entities.Magic;

import Backend.Rest.Naming;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@RestResource
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
