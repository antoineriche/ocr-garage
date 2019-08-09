package fr.ar.occ.garage.model.vehicule.options;

import java.io.Serializable;

public interface Option extends Serializable {

    Double getPrix();

    default String describe(){
        return String.format("%s (%.2f€)", getName(), getPrix());
    }

    String getName();
}
