package fr.ar.occ.garage.error.vehicule.moteur;

import fr.ar.occ.garage.error.vehicule.VehiculeException;

public abstract class MoteurException extends VehiculeException {

    public MoteurException(String message) {
        super(message);
    }
}
