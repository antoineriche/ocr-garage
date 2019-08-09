package fr.ar.occ.garage.model.vehicule;

import com.sun.deploy.util.StringUtils;
import fr.ar.occ.garage.error.vehicule.InvalidVehiculeException;
import fr.ar.occ.garage.error.vehicule.option.OptionAlreadySetException;
import fr.ar.occ.garage.model.vehicule.moteur.Moteur;
import fr.ar.occ.garage.model.vehicule.options.Option;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public class Vehicule implements Serializable {

    private Double prix;
    private String nom;
    private List<Option> options;
    private Marque nomMarque;
    private Moteur moteur;

    public Vehicule() {
    }

    public Vehicule(String nom, Marque nomMarque) {
        this.prix = 0d;
        this.nom = nom;
        this.options = new ArrayList<>();
        this.nomMarque = nomMarque;
    }

    public Vehicule(String nom, Marque nomMarque, Moteur moteur) {
        this.nom = nom;
        this.options = new ArrayList<>();
        this.nomMarque = nomMarque;
        this.moteur = moteur;
    }

    public Vehicule(Double prix, String nom, List<Option> options, Marque nomMarque, Moteur moteur) {
        this.prix = prix;
        this.nom = nom;
        this.options = options;
        this.nomMarque = nomMarque;
        this.moteur = moteur;
    }

    public void addOption(Option option) throws OptionAlreadySetException {
        Optional<Option> op = options.stream()
                .filter(o -> o.getClass().equals(option.getClass())).findFirst();

        if(!op.isPresent()){
            options.add(option);
            this.prix += option.getPrix();
        } else {
            throw new OptionAlreadySetException(this.nom, option.getName());
        }
    }

    public Marque getMarque() {
        return nomMarque;
    }

    public List<Option> getOptions() {
        return options;
    }

    public Double getPrix() {
        return prix;
    }

    public void setMoteur(Moteur moteur) {
        if(this.moteur != null){
            if(!(moteur.equals(this.moteur))){
                prix -= this.moteur.getPrix();
                prix += moteur.getPrix();
                this.moteur = moteur;
            }
        } else {
            this.moteur = moteur;
            this.prix += moteur.getPrix();
        }
    }

    public static void validate(Vehicule vehicule) throws InvalidVehiculeException {
        if(vehicule.moteur == null){
            throw new InvalidVehiculeException("Moteur");
        } else if(vehicule.nom == null || vehicule.nom.trim().equals("")){
            throw new InvalidVehiculeException("Nom");
        } else if(vehicule.nomMarque == null){
            throw new InvalidVehiculeException("Marque");
        } else if(vehicule.prix == null || vehicule.prix <= 0){
            throw new InvalidVehiculeException("Prix");
        }
    }

    @Override
    public String toString() {

        String strOptions = StringUtils.join(
                options.stream().map(Option::describe).collect(Collectors.toList()),
                ", ");

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Voiture %s : %s", nomMarque.getName(), nom)).append(" ")
                .append(moteur.toString()).append(" ")
                .append("[").append(strOptions).append("]").append(" ")
                .append(String.format("d'une valeur totale de %.2f €", prix));

        return sb.toString();
    }
}
