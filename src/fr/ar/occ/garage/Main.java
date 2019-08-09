package fr.ar.occ.garage;

import fr.ar.occ.garage.error.InvalidNameGarageException;
import fr.ar.occ.garage.error.vehicule.PriceException;
import fr.ar.occ.garage.error.vehicule.moteur.CylindreInconnueException;
import fr.ar.occ.garage.error.vehicule.option.OptionAlreadySetException;
import fr.ar.occ.garage.model.Garage;
import fr.ar.occ.garage.model.vehicule.*;
import fr.ar.occ.garage.model.vehicule.moteur.MoteurDiesel;
import fr.ar.occ.garage.model.vehicule.moteur.MoteurElectrique;
import fr.ar.occ.garage.model.vehicule.moteur.MoteurEssence;
import fr.ar.occ.garage.model.vehicule.moteur.MoteurHybride;
import fr.ar.occ.garage.model.vehicule.options.*;
import fr.ar.occ.garage.utils.GarageUtility;

import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InvalidNameGarageException {
        String garageName = instructions();
        Garage garage = new Garage(garageName);

        System.out.println(garage.shortDescription());

        Vehicule lag = new Lagouna();
        try {
            lag.setMoteur(new MoteurDiesel("180 chevaux", 5000d));
            lag.addOption(new Climatisation());
            lag.addOption(new GPS());
        } catch (CylindreInconnueException | PriceException | OptionAlreadySetException e){
            System.err.println(e.getMessage());
        }

        garage.add(lag);

        Vehicule ds = new A300B();
        try {
            ds.setMoteur(new MoteurHybride("250 chevaux", 50000d));
            ds.addOption(new Climatisation());
            ds.addOption(new GPS());
            ds.addOption(new BarreDeToit());
            ds.addOption(new SiegeChauffant());
            ds.addOption(new VitreElectrique());
        } catch (CylindreInconnueException | PriceException | OptionAlreadySetException e){
            System.err.println(e.getMessage());
        }

        garage.add(ds);

        ds = new D4();
        try {
            ds.setMoteur(new MoteurEssence("350 chevaux", 24000d));
            ds.addOption(new Climatisation());
            ds.addOption(new GPS());
            ds.addOption(new SiegeChauffant());
            ds.addOption(new VitreElectrique());
        } catch (CylindreInconnueException | PriceException | OptionAlreadySetException e){
            System.err.println(e.getMessage());
        }

        garage.add(ds);

        Vehicule reno = new Vehicule("R5", Marque.RENO);
        try {
            reno.setMoteur(new MoteurElectrique("v12", 250_000_000d));
            reno.addOption(new Climatisation());
            reno.addOption(new GPS());
            // Next line to test the OptionAlreadySetException
            try { reno.addOption(new GPS()); } catch (OptionAlreadySetException e) { System.err.println(e.getMessage()); }
            reno.addOption(new BarreDeToit());
            reno.addOption(new SiegeChauffant());
            reno.addOption(new VitreElectrique());
        } catch (CylindreInconnueException | PriceException | OptionAlreadySetException e){
            System.err.println(e.getMessage());
        }

        garage.add(reno);

        System.out.println(garage);
        garage.save();
        garage.resetGarage();       // COMMENT THIS LINE IF YOU WANT TO PERSIST DATA
    }

    private static String instructions(){
        Scanner scanner = new Scanner(System.in);

        List<Path> garageFiles = GarageUtility.getExistingGarages();

        if(garageFiles.isEmpty()){
            System.out.println("Veuillez saisir un nouveau nom pour votre garage:");
        } else {
            System.out.println("Veuillez choisir un garage existant ou saisir un nouveau nom pour votre garage:");
        }

        int i = 0;
        for(Path garage : garageFiles){
            System.out.println(String.format("%d) %s", i, garage.getFileName()));
            i++;
        }

        String choice = scanner.nextLine();
        String name;

        try {
            int c = Integer.parseInt(choice);
            if(c >= 0 && c < garageFiles.size()){
                name = garageFiles.get(c).getFileName().toString()
                        .replace(GarageUtility.EXTENSION_GARAGE_FILE, "");
            } else {
                name = String.valueOf(c);
            }
        } catch (NumberFormatException e){
            name = choice;
        }

        return name;
    }
}
