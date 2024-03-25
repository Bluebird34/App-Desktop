package edu.java.Animal;

// Mêmes attributs que la table animaux de la bd
public class Animal {
    String nom;
    String classe;
    int longevite;
    String habitat;
    String fait;
   
    public static int nbAnimaux = 0;

    //constructor par defaut
    public Animal(){
        ++nbAnimaux;
    }

    //constructor avec param
    public Animal(String nom,String classe,int longevite,String habitat,String fait){
        this.nom = nom;
        this.classe = classe;
        this. longevite = longevite;
        this. habitat = habitat;
        this.fait = fait;
        ++nbAnimaux;
    }

    //getter
    public String getNom() {
        return nom;
    }

    public String getClasse() {
        return classe;
    }

    public int getLongevite() {
        return longevite;
    }

    public String getHabitat() {
        return habitat;
    }

    public String getFait() {
        return fait;
    }

    //setter
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public void setLongevite(int longevite) {
        this.longevite = longevite;
    }

    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }

    public void setFait(String fait) {
        this.fait = fait;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "nom='" + nom + '\'' +
                ", classe='" + classe + '\'' +
                ", longevite=" + longevite +
                ", habitat='" + habitat + '\'' +
                ", fait='" + fait + '\'' +
                '}'+ "\n";
    }

    public String afficher() {
      //  String rep = this.nom + "\t\\t\\t" + this.classe + "\t" + this.longevite + "\t" + this.habitat + "\t" + this.fait + "\n";
        String rep = this.nom + "\t\t\t" + this.classe + "\t" + this.longevite + "\t" + "\n";
        return rep;
    }
}
