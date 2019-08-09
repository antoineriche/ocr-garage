package fr.ar.occ.garage.model.vehicule.moteur;

import fr.ar.occ.garage.error.vehicule.moteur.CylindreInconnueException;
import fr.ar.occ.garage.error.vehicule.PriceException;

import java.io.Serializable;
import java.util.Objects;

public abstract class Moteur implements Serializable {

    private String cylindre;
    private Double prix;
    protected TypeMoteur type;

    public Moteur(String cylindre, Double prix) throws CylindreInconnueException, PriceException {
        if(cylindre.length() < 2){
            throw new CylindreInconnueException(String.format("La cylindré '%s' est inconnue" +
                    "\nUne cylindré doit compter au moins 2 caractères.", cylindre));
        }

        if(prix < 0){
            throw new PriceException(String.format("Le prix d'un moteur ne peut pas etre négatif (%.2f €)", prix));
        } else if (prix == 0){
            throw new PriceException("Aucun moteur n'est gratuit.");
        }
        this.cylindre = cylindre;
        this.prix = prix;
    }

    public Double getPrix() {
        return prix;
    }

    public String getCylindre() {
        return cylindre;
    }

    public TypeMoteur getType() {
        return type;
    }

    @Override
    public String toString() {
        return String.format("Moteur %s %s (%.2f€)",
                this.type, this.cylindre, this.prix);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Moteur moteur = (Moteur) o;
        return Objects.equals(cylindre, moteur.cylindre) &&
                Objects.equals(prix, moteur.prix) &&
                type == moteur.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cylindre, prix, type);
    }
}
