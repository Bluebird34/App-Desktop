import java.io.*;
import java.util.Arrays;

public class Film implements Serializable, Comparable<Object>{
    // Attributs statiques  de la classe
    public static int nbFilms = 0;
    String tabCategs[] = {"Horror", "Comedy", "Drama", "Romance", "Historique"};

    // Attributs  d'objet
    private int num;
    private String titre;
    private int categ; //1,2,3,4,5
    private String langue;
    private int etoiles;

    // Constructeur par défaut
    public Film(){
        this.titre = " ";
        nbFilms++;
    }

    // Constructeur paramétré

    public Film(int num, String titre, int categ, String langue, int etoiles) {
        this.num = num;
        this.titre = titre;
        this.categ = categ;
        this.langue = langue;
        this.etoiles = etoiles;
    }

    // Méthodes get, pour obtenir les attributs des objets
    public  int getNum() { return num; }
    public String getTitre() {return titre; }
    public int getCateg() {return categ;}
    public String getCategorieString() {
        return tabCategs[this.categ - 1];
    }

    public String getLangue() {return langue;}

    public int getEtoiles() { return etoiles;}

    // Méthodes set, pour modifier les attributs des objets
    public void  setNum(int num) { this.num=num;}

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setCateg(int categ) {
        this.categ = categ;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

    public void setEtoiles(int etoiles) {
        this.etoiles = etoiles;
    }

    // Méthode toString()
    @Override
    public String toString() {
        return this.num + "\t" + this.titre + "\t" + this.getCategorieString() + "\t" + this.langue + "\t"
                + this.etoiles + "\n" ;
    }


    // Si nécessaire pour trier les clés par String num;
    // Si num était declaré dans la classe comme int num; alors 
    // return this.getNum() -  ((Film)o).getNum();
    // serait suffisant.
    // Dans ce code  on suppose que le num est une chaine de caractères.
    
    @Override
    public int compareTo(Object o) {
        if (o instanceof Film){
            //return this.getNum().compareTo(((Film)o).getNum());
            return this.getNum() -  ((Film)o).getNum();
        }else{  
            throw new ClassCastException("On ne peut pas comparer des onjets de types différents.");
        }
    }

}
