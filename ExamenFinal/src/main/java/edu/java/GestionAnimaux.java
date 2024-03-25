package edu.java;

import javax.swing.JOptionPane;

//import edu.java.Animal.ControleurAnimal;
import edu.java.Animal.Animal;
import edu.java.Animal.ControleurAnimal;
import edu.java.Animal.DAOAnimal;
import edu.java.Animal.VueAnimal;
//import edu.java.Animal.VueAnimal;

import java.sql.ResultSet;

public final class GestionAnimaux {

    public static String menu(){
        String choix;
        String menu =   "A- Ajouter\n" +
                "B-Supprimer\n" +
                "C-Modifier longévité\n" +
                "D-Rechercher par nom\n" +
                "E-Lister\n" +
                "F-Lister entre deux âges\n" +
                "X-Quitter\n\n" +
                "Entrez votre choix : ";
        choix = JOptionPane.showInputDialog(null, menu, "MENU DE GESTION DU Animal", JOptionPane.PLAIN_MESSAGE);
        return choix;
    }

    public static Animal creerAnimal() {
        String nom = JOptionPane.showInputDialog(null, "Entrez le nom d'animal :",
                "SAISIE DES DONNÉES", JOptionPane.PLAIN_MESSAGE);
        String classe = JOptionPane.showInputDialog(null, "Entrez la classe :",
                "SAISIE DES DONNÉES", JOptionPane.PLAIN_MESSAGE);
        int longevite = Integer.parseInt(JOptionPane.showInputDialog(null, "Entrez le longevite :",
                "SAISIE DES DONNÉES", JOptionPane.PLAIN_MESSAGE));
        String habit = JOptionPane.showInputDialog(null, "Entrez le habit:",
                "SAISIE DES DONNÉES", JOptionPane.PLAIN_MESSAGE);
        String fait = JOptionPane.showInputDialog(null, "Entrez le fait:",
                "SAISIE DES DONNÉES", JOptionPane.PLAIN_MESSAGE);

        Animal unAnimal = new Animal(nom,classe,longevite,habit,fait);
        return unAnimal;
    }



    public static void main(String[] args) throws Exception {
        String choix;
        String msg;
        String nom;
        String longevite;
        int minAge,maxAge;
        Animal unAnimal;
        ResultSet listeAnimals, response, responseAnimal;
        VueAnimal vueAnimal = VueAnimal.getInstance();
        ControleurAnimal controleurAnimal = ControleurAnimal.getInstance();
        boolean continuer = true;

        do{
            choix = menu();
            switch (choix) {
                case "A":
                    do {
                        // ajouter(); ok
                        unAnimal = creerAnimal();
                        msg = controleurAnimal.Ctr_ajouter(unAnimal);
                        vueAnimal.Vue_Message(msg);
                        choix = String.valueOf(JOptionPane.showConfirmDialog(null, "Voulez-vous continuer ?", "CONFIRMATION",
                                JOptionPane.YES_NO_OPTION));
                    } while(choix.equals(JOptionPane.YES_OPTION));
                    break;

                case "B":
                    // supprimer(); ok
                    nom = JOptionPane.showInputDialog(null, "Entrez le nom d'animal :",
                            "SAISIE DES DONNÉES", JOptionPane.PLAIN_MESSAGE);
                    msg = controleurAnimal.Ctr_supprimer(nom);
                    vueAnimal.Vue_Message(msg);
                    break;

                case "C":
                    //  modifier(); ??
                    nom = JOptionPane.showInputDialog(null, "Entrez le nom d'animal :",
                            "SAISIE DES DONNÉES", JOptionPane.PLAIN_MESSAGE);
                    responseAnimal = controleurAnimal.Ctr_rechercherAnimalParNom(nom);
                    if(responseAnimal.next()){
                        longevite = JOptionPane.showInputDialog(null, "Nouveau longevite: ", "MODIFICATION",
                                JOptionPane.PLAIN_MESSAGE);
                        controleurAnimal.Ctr_modifierLongevite(nom,longevite);
                    }
                    break;

                case "D":
                    // chercher par nom(); ??
                    nom = JOptionPane.showInputDialog(null, "Entrez le nom d'animal :",
                            "SAISIE DES DONNÉES", JOptionPane.PLAIN_MESSAGE);
                    responseAnimal = controleurAnimal.Ctr_rechercherAnimalParNom(nom);
                    vueAnimal.Vue_ListerAnimaux(responseAnimal);
                    break;

                case "E":
                    // lister(); ok
                    listeAnimals = (ResultSet) controleurAnimal.Ctr_afficherToutesLesAnimaux();
                    vueAnimal.Vue_ListerAnimaux(listeAnimals);
                    break;

                case "F":
                    // lister enre deux âge();
                    minAge = Integer.parseInt(JOptionPane.showInputDialog(null, "Entrer min age: ", "MODIFICATION",
                            JOptionPane.PLAIN_MESSAGE));
                    maxAge = Integer.parseInt(JOptionPane.showInputDialog(null, "Entrer max age: ", "MODIFICATION",
                            JOptionPane.PLAIN_MESSAGE));
                    listeAnimals = controleurAnimal.Ctr_afficherAnimauxEntreDeuxAges(minAge,maxAge);
                    vueAnimal.Vue_ListerAnimaux(listeAnimals);
                    break;

                case "X":
                    JOptionPane.showMessageDialog(null, "Merci d'avoir utilisé notre logiciel", "FIN DU PROGRAMME",
                            JOptionPane.PLAIN_MESSAGE);
                    continuer = false;
            }
        }while (continuer);
        
        System.exit(0);
    }
}
        