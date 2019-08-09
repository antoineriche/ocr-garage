package fr.ar.occ.garage.model.vehicule.options;

public class VitreElectrique implements Option {

    @Override
    public Double getPrix() {
        return 50d;
    }

    @Override
    public String getName() {
        return "Vitre electrique";
    }
}
