package fr.ar.occ.garage.error.vehicule;

public class InvalidVehiculeException extends VehiculeException {
    public InvalidVehiculeException(String missingField) {
        super(String.format("Le champs '%s' est manquant.", missingField));
    }
}
