package fr.ar.occ.garage.model.vehicule.moteur;

public enum TypeMoteur {
    DIESEL("Diesel"),
    ESSENCE("Essence"),
    HYBRIDE("Hybride"),
    ELECTRIQUE("Electrique");

    private String name;

    TypeMoteur(String name) {
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
