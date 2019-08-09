package fr.ar.occ.garage.model.vehicule.options;

public class SiegeChauffant implements Option {

    @Override
    public Double getPrix() {
        return 120d;
    }

    @Override
    public String getName() {
        return "Siege chauffant";
    }
}