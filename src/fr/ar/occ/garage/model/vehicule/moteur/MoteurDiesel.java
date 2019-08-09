package fr.ar.occ.garage.model.vehicule.moteur;

import fr.ar.occ.garage.error.vehicule.moteur.CylindreInconnueException;
import fr.ar.occ.garage.error.vehicule.PriceException;

public class MoteurDiesel extends Moteur {

    public MoteurDiesel(String cylindre, Double prix) throws CylindreInconnueException, PriceException {
        super(cylindre, prix);
        this.type = TypeMoteur.DIESEL;
    }

}