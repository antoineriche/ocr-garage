package fr.ar.occ.garage.utils;

import fr.ar.occ.garage.model.Garage;
import fr.ar.occ.garage.model.vehicule.Vehicule;

import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class GarageUtility {

    private static final String DIRECTORY_PATH = "./garages/";
    private static final File DIRECTORY_GARAGE = new File(DIRECTORY_PATH);
    public static final String EXTENSION_GARAGE_FILE = ".txt";

    public static List<Path> getExistingGarages(){
        List<Path> garageFiles = new ArrayList<>();

        if(!DIRECTORY_GARAGE.exists()){
            DIRECTORY_GARAGE.mkdirs();
        }

        Path src = Paths.get(DIRECTORY_PATH);
        String filterExtension = String.format("*%s", EXTENSION_GARAGE_FILE);
        try(DirectoryStream<Path> listing = Files.newDirectoryStream(src, filterExtension)){
            for(Path p : listing){
                garageFiles.add(p);
            }
        } catch (IOException ignored){ }

        return garageFiles;
    }

    public static void persistGarage(Garage garage){
        ObjectOutputStream oos;
        FileOutputStream fon;

        try {
            fon = new FileOutputStream(getOutputFile(garage.getName()));
            oos = new ObjectOutputStream(
                    new BufferedOutputStream(fon));

            for (Object v : garage.getVoitures()) {
                oos.writeObject(v);
            }

            oos.close();

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static List<? super Vehicule> extractVehiculesFromFile(String garageName){
        List<? super Vehicule> voitures = new ArrayList<>();

        ObjectInputStream ois;
        FileInputStream fin;

        File persistedGarage = getOutputFile(garageName);

        if (persistedGarage.exists()) {

            try {
                fin = new FileInputStream(persistedGarage);
                ois = new ObjectInputStream(
                        new BufferedInputStream(fin));

                Vehicule v = (Vehicule) ois.readObject();
                while (v != null) {
                    voitures.add(v);
                    try {
                        v = (Vehicule) ois.readObject();
                    } catch (EOFException e) {
                        break;
                    }
                }

                ois.close();
                fin.close();

            } catch (IOException | ClassNotFoundException e) {
                System.err.println(e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.err.println("Aucune voiture sauvegardée !");
        }

        return voitures;
    }

    public static void deleteGarage(String garageName){
        File file = getOutputFile(garageName);
        if(file.exists()){
            file.delete();
            System.out.println("Garage has been deleted.");
        } else {
            System.err.println("Garage does not exist.");
        }
    }

    private static String getFileName(String garageName){
        return String.format("%s%s", garageName, EXTENSION_GARAGE_FILE);
    }

    private static File getOutputFile(String garageName){
        return new File(DIRECTORY_GARAGE, getFileName(garageName));
    }

}
