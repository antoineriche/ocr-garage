package fr.ar.occ.garage.model.vehicule;

public enum Marque {
    RENO("Reno"),
    PIGEOT("Pigeot"),
    TROEN("Troen");

    private String name;

    Marque(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getName();
    }
}
