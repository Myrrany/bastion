package Backend.Rest.Entities.Crafting;

import lombok.Getter;

@Getter
public enum Level {
    BASIC(2, 1), ADEPT(4, 3), EXPERT(6, 4), MASTER(8, 6);

    private int cost;
    private int discountCost;
    private static Level[] vals = values();

    Level(final int cost, final int discountCost) {
        this.cost = cost;
        this.discountCost = discountCost;
    }

    public Level next() {
        if (this != MASTER) {
            return vals[(this.ordinal() + 1)];
        } else {
            return MASTER;
        }
    }

    public Level previous() {
        if (this != BASIC) {
            return vals[(this.ordinal() - 1)];
        } else {
            return BASIC;
        }
    }

}
