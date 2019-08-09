package fr.ar.occ.garage.error.vehicule.option;

import fr.ar.occ.garage.error.vehicule.VehiculeException;

public class OptionAlreadySetException extends VehiculeException {
    public OptionAlreadySetException(String vehiculeName, String optionName) {
        super(String.format("%s already has option '%s'", vehiculeName, optionName));
    }
}
