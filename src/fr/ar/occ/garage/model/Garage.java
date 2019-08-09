package fr.ar.occ.garage.model;

import fr.ar.occ.garage.error.InvalidNameGarageException;
import fr.ar.occ.garage.error.vehicule.InvalidVehiculeException;
import fr.ar.occ.garage.model.vehicule.Marque;
import fr.ar.occ.garage.model.vehicule.Vehicule;
import fr.ar.occ.garage.utils.GarageInfoBuilder;
import fr.ar.occ.garage.utils.GarageUtility;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Garage {

    private List<? super Vehicule> voitures;
    private final String name;

    public Garage(String garageName) throws InvalidNameGarageException {
        if(garageName.length() <= 2){
            throw new InvalidNameGarageException("Garage name must be at least 3-characters-length.");
        } else {
            this.name = garageName.toUpperCase();
            this.voitures = GarageUtility.extractVehiculesFromFile(this.name);
        }
    }

    public void save(){
        GarageUtility.persistGarage(this);
    }

    public void add(Vehicule vehicule) {

        try {
            Vehicule.validate(vehicule);
            voitures.add(vehicule);
            System.out.println(String.format("* Nouveau véhicule\n*\t%s\n", vehicule.toString()));
        } catch (InvalidVehiculeException e){
            System.err.println("La voiture n'a pas été ajoutée car :\n" + e.getMessage());
        }
    }

    public void resetGarage(){
        GarageUtility.deleteGarage(this.name);
    }

    public List<Marque> getMarques(){
        List<Marque> marques = new ArrayList<>();
        for (Object voiture : voitures) {
            Vehicule v = (Vehicule) voiture;
            marques.add(v.getMarque());
        }

        return marques.stream().distinct().collect(Collectors.toList());
    }

    public Double getTotalAmount(){
        double total = 0;
        for (Object voiture : voitures) {
            Vehicule v = (Vehicule) voiture;
            total += v.getPrix();
        }
        return total;
    }

    public String getName() {
        return name;
    }

    public List<? super Vehicule> getVoitures() {
        return voitures;
    }

    public String shortDescription(){
        return GarageInfoBuilder.shortDescription(this);
    }

    @Override
    public String toString() {
        return GarageInfoBuilder.detailDescription(this);
    }


}
