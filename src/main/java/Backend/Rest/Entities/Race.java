package Backend.Rest.Entities;

public enum Race {
    VOIDLING(""), TEMPEST("");
    private String description;

    Race(String desc) {
        this.description = desc;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String desc) {
        this.description = desc;
    }
}
