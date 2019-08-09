package fr.ar.occ.garage.model.vehicule.options;

public class Climatisation implements Option {

    @Override
    public Double getPrix() {
        return 200d;
    }

    @Override
    public String getName() {
        return "Climatisation";
    }
}
