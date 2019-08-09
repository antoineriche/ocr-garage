package fr.ar.occ.garage.model.vehicule.options;

public class GPS implements Option {
    @Override
    public Double getPrix() {
        return 150d;
    }

    @Override
    public String getName() {
        return "GPS";
    }
}
