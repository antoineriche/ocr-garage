package fr.ar.occ.garage.utils;

import fr.ar.occ.garage.model.Garage;
import fr.ar.occ.garage.model.vehicule.Marque;
import fr.ar.occ.garage.model.vehicule.Vehicule;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class GarageInfoBuilder {

    public enum DescriptionType {
        Short, Detail
    }

    private static final char DECORATOR_CHARACTER = '*';

    private static String getHeader(String garageName){
        StringBuilder strB = new StringBuilder(insertWrapperLine(garageName));
        strB.append(insertNewLine());
        strB.append(garageName);
        strB.append(insertNewLine());

        return strB.toString();
    }

    private static String insertWrapperLine(String garageName){
        StringBuilder strB = new StringBuilder();
        for(int i = 0 ; i < garageName.length() + 2 ; i++){
            strB.append(DECORATOR_CHARACTER);
        }
        return strB.toString();
    }

    private static String insertHour(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        return insertNewInfo(sdf.format(new Date()));
    }

    private static String insertCarCount(List<? super Vehicule> vehicules){
        String carCount = String.format(
                (vehicules.size() <= 1) ? "%d voiture" : "%d voitures", vehicules.size());
        return insertNewInfo(carCount);
    }

    private static String insertDetailCars(List<? super Vehicule> vehicules){
        String detailCars = insertNewInfo(String.format("Liste des véhicules (%d)", vehicules.size()));
        StringBuilder strB = new StringBuilder(detailCars);
        for(Object o : vehicules){
            Vehicule v = (Vehicule) o;
            strB.append(insertNewLine()).append("\t").append(v);
        }
        return strB.toString();
    }

    private static String insertTypeCount(List<Marque> marques) {
        String marqueCount = String.format(
                (marques.size() <= 1) ? "%d marque" : "%d marques", marques.size());
        return insertNewInfo(marqueCount);
    }

    private static String insertTotalAmount(double total){
        return insertNewInfo(String.format("Montant total: %.2f €", total));
    }

    private static String insertNewLine(){
        return new StringBuilder("\n").append(DECORATOR_CHARACTER).append(" ").toString();
    }

    private static String insertNewInfo(String info){
        return new StringBuilder(DECORATOR_CHARACTER)
                .append(insertNewLine()).append("=>")
                .append(" ").append(info)
                .toString();
    }

    private static String description(Garage garage,DescriptionType type){

        StringBuilder stringBuilder = new StringBuilder(getHeader(garage.getName()));

        // DATE AND TIME
        stringBuilder.append(insertHour());

        // CAR COUNT
        if (type.equals(DescriptionType.Detail)) {
            stringBuilder.append(insertDetailCars(garage.getVoitures()));
        } else {
            stringBuilder.append(insertCarCount(garage.getVoitures()));
        }

        // TYPE COUNT
        stringBuilder.append(insertTypeCount(garage.getMarques()));

        // AMOUNT COUNT
        stringBuilder.append(insertTotalAmount(garage.getTotalAmount()));

        stringBuilder.append("\n")
                .append(insertWrapperLine(garage.getName()))
                .append("\n");

        return stringBuilder.toString();
    }

    public static String shortDescription(Garage garage){
        return description(garage, DescriptionType.Short);
    }

    public static String detailDescription(Garage garage){
        return description(garage, DescriptionType.Detail);
    }
}
