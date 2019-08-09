package fr.ar.occ.garage.model.vehicule.moteur;

import fr.ar.occ.garage.error.vehicule.moteur.CylindreInconnueException;
import fr.ar.occ.garage.error.vehicule.PriceException;

public class MoteurElectrique extends Moteur {

    public MoteurElectrique(String cylindre, Double prix) throws CylindreInconnueException, PriceException {
        super(cylindre, prix);
        this.type = TypeMoteur.ELECTRIQUE;
    }
}
