import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class App {
    static BufferedReader lire;
    static BufferedWriter ecrire;
    static ObjectInputStream lireObj;
    static ObjectOutputStream ecrireObj;
    static JTextArea jtaContenu;

    static List<Radio> listeRadios;


    public static void main(String[] args) throws Exception {
        File fic = new File("src/donnees/object.ser");
        if (fic.exists()) {
            chargerRadiosObjet("src/donnees/object.ser");
        } else {
            chargerRadiosTexte("src/donnees/radios.txt");
        }

        String choix;
        do {
            choix = menu();
            switch (choix) {
                case "1":
                    listerRadios();
                    break;
                case "2":
                    ajouterRadio();
                    break;
                case "3":
                    enleverRadio();
                    break;
                case "4":
                    modifierRadio();
                    break;
                case "5":
                    calculTotal();
                    break;
                case "6":
                    // Solution si  on enregistre les changements à la fin seulement
                    sauvegarderRadiosObjet("src/donnees/object.ser");
                    Utilitaires.afficherMessage("Merci d'avoir utilisé notre application");
                    break;
                default:
                    Utilitaires.afficherMessage("Choix invalide");
                    break;
            }
        } while (!choix.equals("6"));
        System.exit(0);
    }//main

    public static void chargerRadiosObjet(String fichier) throws Exception {
        try {
            lireObj = new ObjectInputStream(new FileInputStream(fichier));
            listeRadios = (ArrayList)lireObj.readObject();
        } catch(FileNotFoundException e){
            Utilitaires.afficherMessage("charger 1:Fichier introuvable. Vérifiez le chemin et nom du fichier.");
        }catch(IOException e){
            Utilitaires.afficherMessage("charger 2:Un probléme est arrivé lors de la manipulation du fichier. Vérifiez vos données.");
        }catch(Exception e){
            Utilitaires.afficherMessage("charger 3:Un probléme est arrivé lors du chargement du fichier. Contactez l'administrateur.");
        } finally {
            if(lireObj != null){
                lireObj.close();
            }
        }
        System.out.println(listeRadios.toString());
    }


    public static void chargerRadiosTexte(String fichier) throws Exception {
        listeRadios = new ArrayList<Radio>();
        lire = new BufferedReader(new FileReader(fichier));
        String elems[] = new String[5];
        String ligne = lire.readLine();
        while (ligne != null) {
            elems = ligne.split(";");
            listeRadios.add(new Radio(Integer.parseInt(elems[0]), elems[1], elems[2], elems[3],Float.parseFloat(elems[4])));
            ligne = lire.readLine();
        }
        lire.close();
    }

    public static String menu(){
        String contenu = "1-Lister\n2-Ajouter\n3-Supprimer\n4-Modifier\n5-Total\n6-Quitter\nEntrez votre choix : ";
        String choix = JOptionPane.showInputDialog(null, contenu, "GESTION DES RADIOS", JOptionPane.PLAIN_MESSAGE);
        return choix;
    }

    //a. Lister tous les radios.
    public static void listerRadios(){
        listeRadios.sort((a,b)->{
            return a.getProduit_no()-b.getProduit_no();
        });

        jtaContenu = new JTextArea(10,0);
        JScrollPane defilement = new JScrollPane(jtaContenu);
        String resultat="\t\tLISTE DES RADIOS\n";
        resultat +="Numéro\t" + Utilitaires.ajouterEspaces(30,"Fabriquant",'F') +
                Utilitaires.ajouterEspaces(30,"Modele",'F') +
                Utilitaires.ajouterEspaces(30,"Options",'F') +
                Utilitaires.ajouterEspaces(10,"Prix",'F') + "\n";

        for (Radio unVol : listeRadios) {
            resultat += unVol.toString();
        }
        resultat += "Nombre de Radios : " + listeRadios.size();

        jtaContenu.setFont(new Font("COURIER", Font.PLAIN, 12));
        jtaContenu.setBackground(Color.lightGray);
        jtaContenu.setForeground(Color.black);
        jtaContenu.setText(resultat);
        JOptionPane.showMessageDialog(null, defilement, "LISTE DES RADIOS", JOptionPane.PLAIN_MESSAGE);
    }

    //function rechercher
    private static int rechercherRadio(int radioNo) {
        int pos = 0;
        for(int i = 0; i < listeRadios.size();i++) {
            if( listeRadios.get(i).getProduit_no() == radioNo ) {
                pos = i;
                break;
            }else {
                pos = -1;
            }
        }
        return pos;
    }


    //b. Ajouter pour avoir un formulaire pour ajouter une nouvelle radio.
    public static void ajouterRadio() throws Exception {
        int radio_No = Integer.parseInt(JOptionPane.showInputDialog(null, "Numéro du Radio:", "AJOUT D'UN RADIO", JOptionPane.PLAIN_MESSAGE));
        int pos = rechercherRadio(radio_No);
        if (pos == -1) {// Introuvable
            String fabriquant = JOptionPane.showInputDialog(null, "Fabriquant:", "AJOUT D'UN RADIO", JOptionPane.PLAIN_MESSAGE);
            String modele = JOptionPane.showInputDialog(null, "modele:", "AJOUT D'UN RADIO", JOptionPane.PLAIN_MESSAGE);
            String options = JOptionPane.showInputDialog(null, "Options:", "AJOUT D'UN RADIO", JOptionPane.PLAIN_MESSAGE);
            int prix = Integer.parseInt(JOptionPane.showInputDialog(null, "Prix:", "AJOUT D'UN RADIO", JOptionPane.PLAIN_MESSAGE));
            listeRadios.add(new Radio(radio_No,fabriquant,modele,options,prix));
            Utilitaires.afficherMessage("Cet Radio est bien ajouter");
        }else{
            Utilitaires.afficherMessage("Le Radio " + radio_No + " est déja existe");
        }
     //   sauvegarderRadiosTexte();
    }


    //c. Supprimer une en particulier.
    public static void enleverRadio() throws Exception {
        int radio_No = Integer.parseInt(JOptionPane.showInputDialog(null, "Numéro du Radio:", "SUPPRIMER D'UN RADIO", JOptionPane.PLAIN_MESSAGE));
        int pos = rechercherRadio(radio_No);
        if (pos == -1) {// Introuvable
            Utilitaires.afficherMessage("Le Radio " + radio_No + " n'est existe !");
        }else{
            listeRadios.remove(pos);
            Utilitaires.afficherMessage("Cet Radio est bien supprimer");
        }
     //   sauvegarderRadiosTexte();
    }

    //d. Modifier les options d’une radio
    public static void modifierRadio() throws Exception {
        int radio_No = Integer.parseInt(JOptionPane.showInputDialog(null, "Numéro du Radio:", "MODIFIER D'UN RADIO", JOptionPane.PLAIN_MESSAGE));
        int pos = rechercherRadio(radio_No);
        if (pos == -1) {// Introuvable
            Utilitaires.afficherMessage("Le Radio " + radio_No + " n'est existe !");
        }else{
            String nouveau_option = JOptionPane.showInputDialog(null, "Nouveau Options:", "MODIFIER D'UN RADIO", JOptionPane.PLAIN_MESSAGE);
            listeRadios.get(pos).setProduit_option(nouveau_option);
        }
      //  sauvegarderRadiosTexte();
    }

    //e. Total pour afficher.
    public static void calculTotal() {
        float sum_prix = 0;
        for (int i = 0; i < listeRadios.size(); i++) {
            sum_prix += listeRadios.get(i).getProduit_prix();
        }
        String msg = "La somme de tous les prix " + "\t" + sum_prix + "$" + "\n" + "Total radios : " + "\t" + listeRadios.size();
        Utilitaires.afficherMessage(msg);
    }

    public static void sauvegarderRadiosObjet(String fichier) throws Exception {
        try {
            ecrireObj = new ObjectOutputStream(new FileOutputStream(fichier));
            ecrireObj.writeObject(listeRadios);
        } catch(FileNotFoundException e){
            Utilitaires.afficherMessage("sauvegarder 1:Fichier introuvable. Vérifiez le chemin et nom du fichier.");
        }catch(IOException e){
            Utilitaires.afficherMessage("sauvegarder 2:Un probléme est arrivé lors de la manipulation du fichier. Vérifiez vos données.");
        }catch(Exception e){
            Utilitaires.afficherMessage("sauvegarder 3:Un probléme est arrivé lors du chargement du fichier. Contactez l'administrateur.");
        } finally {
            if(ecrireObj != null){
                ecrireObj.close();
            }
        }
    }
/*//Why this block of code appears?
    public static void sauvegarderRadiosTexte() throws Exception {
        String ligne;
        ecrire = new BufferedWriter(new FileWriter("src/donnees/radios.txt")); // Recréer le fichier
        for (Radio unRadio : listeRadios) {
            ligne = unRadio.getProduit_no()+";"+unRadio.getProduit_fabriquant()+";"
                    +unRadio.getProduit_modele()+";"+unRadio.getProduit_option()+";"
                    +unRadio.getProduit_prix()+"\n";
            ecrire.write(ligne);
        }
        ecrire.close();
    }*/
}
