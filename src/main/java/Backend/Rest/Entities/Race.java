package Backend.Rest.Entities;

import lombok.Getter;

@Getter
public enum Race {
    VOIDLING(""), TEMPEST("");
    private String description;

    Race(String desc) {
        this.description = desc;
    }

    public void setDescription(String desc) {
        this.description = desc;
    }
}
