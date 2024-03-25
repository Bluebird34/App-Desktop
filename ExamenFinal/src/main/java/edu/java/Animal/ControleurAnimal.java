package edu.java.Animal;

import java.sql.ResultSet;

public class ControleurAnimal {
    private static ControleurAnimal instance = null;
    private ResultSet listeAnimaux = null;
    private String msg = "";

    private ControleurAnimal(){}

    //******************************Partie Singleton********************************
    public static ControleurAnimal getInstance(){
        // À completer
        if(instance == null) {
            instance = new ControleurAnimal();
        }
        return instance;
    }
    //******************************Partie Singleton********************************

    DAOAnimal daoAnimal = DAOAnimal.getInstance();
    public String Ctr_ajouter(Animal unAnimal) throws Exception {
        // Votre code ici
        msg = daoAnimal.Mdl_ajouter(unAnimal);
        return msg;
    }

    public String Ctr_supprimer(String nom) throws Exception {
        // Votre code ici
        msg = daoAnimal.Mdl_supprimer(nom);
        return msg;
    }

    public String Ctr_modifierLongevite(String nom, String longevite) throws Exception {
        // Votre code ici
        msg = daoAnimal.Mdl_modifierLongevite(nom,longevite);
        return msg;
    }

    public ResultSet Ctr_rechercherAnimalParNom(String nom) throws Exception {
        // Votre code ici
        ResultSet listeAnimaux = daoAnimal.Mdl_rechercherAnimalParNom(nom);
        return listeAnimaux;
    }

    public ResultSet Ctr_afficherToutesLesAnimaux() throws Exception {
        // Votre code ici
        ResultSet listeAnimaux = daoAnimal.Mdl_afficherToutesLesAnimaux();
        return listeAnimaux;
    }

    public ResultSet Ctr_afficherAnimauxEntreDeuxAges(int ageMin, int ageMax) throws Exception {
        // Votre code ici
        ResultSet listeAnimaux = daoAnimal.Mdl_afficherAnimauxEntreDeuxAges(ageMin,ageMax);
        return listeAnimaux;
    }

    // CAS D'UTILISATION 1
    // Reçoit la requête à savoir typeOperation, unAnimal quand c'est pour ajouter
    // nom quand c'est nécessaire sinon sera null, longevite quand c'est nécessaire sinon sera 0.
    // La vue appelle cette méthode pour faire appel à la méthode en lui envoyant en 
    // paramètre les informations.
    // Par exemple, si la vue veut ajouter un animal, elle fera appel à cette méthode
    // en lui envoyant en paramètre "ajouter", unAnimal, null, 0.
    // Si la vue veut supprimer un animal, elle fera appel à cette méthode
    // en lui envoyant en paramètre "supprimer", null, nom, 0 et anisi de suite.
    
    // CAS D'UTILISATION 2
    // Si vous ne voulez pas faire appel à traiterAction alors votre vue fera appel
    // à chacune des méthodes du contrôleur directement.

    // Dispatcher (Répartiteur)
    // public Object traiterAction(String typeOperation, Animal unAnimal,  String nom, int longevite) throws Exception{
    //     VueAnimal viewAnimal =  VueAnimal.getInstance();
    //     Object reponse;
    //     // CRUD
    //     switch(typeAction){
          
    //         case "ajouter":
                
    //         break

    //         case "supprimer":
               
    //          break;
           
    //          case "modifierLongevite":
                
    //          break;
            
    //          case "rechercherParNom":
                
    //          break;
            
    //          case "lister":
                
    //          break;
    //          case "listerEntreDeuxAges":
                
    //          break;
    //     }
    // }
}
