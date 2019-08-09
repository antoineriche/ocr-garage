package fr.ar.occ.garage.model.vehicule.options;

public class BarreDeToit implements Option {

    @Override
    public Double getPrix() {
        return 100d;
    }

    @Override
    public String getName() {
        return "Barre de toit";
    }
}
