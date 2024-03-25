import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class GestionFilms {

    public static int NOMBRE_ATTRIBUTS = 5;
    public static String  nomFichier = "./src/donnees/films.txt"; // Nom du fichier de données
    public static String nomFichierObj = "./src/donnees/films.obj"; // Nom du fichier d'objets

    public static BufferedReader bufLire;
    public static BufferedWriter bufEcrire;
    public static ObjectInputStream bufLireObj;
    public static ObjectOutputStream bufEcrireObj;
    public static BufferedReader clavier = new BufferedReader(new InputStreamReader(System.in));
    public static Map<Integer, Film> mapFilms = new HashMap<>();
    static int nbFilms;

    Map<String,Film> filmMap = new HashMap<>();
    ArrayList<Integer> filmList = new ArrayList<>();

    static JTextArea jtaContenu;

    public static void main(String[] args) throws Exception{
        int num;
        String titre;
        // Charge les données
        File fic = new File(nomFichierObj);
        if (fic.exists()) {
            chargerFilmsObjet(nomFichierObj);
        } else {
            lireFichier(nomFichier);
        }

        //Ajouter un film
        ajouterFilm();
        afficherToutesLesFilms();

        //Supprimer un film
        System.out.print("Entrez le numéro du film à supprimer : ");
        num = Integer.parseInt(clavier.readLine());
        supprimerFilm(num);
        afficherToutesLesFilms();

        //Modifier film titre.
        System.out.print("Entrez le numéro du film à changer: ");
        num = Integer.parseInt(clavier.readLine());
        System.out.print("Entrez le nouveau titre : ");
        titre = clavier.readLine();
        modifierTitre(num, titre);
        afficherToutesLesFilms();

        // Trier les films en ordre croissant.
        trierEtAfficherFilmsParNumero();
        afficherToutesLesFilms();

        // Rechercher un film selon un titre
        System.out.print("Entrez le titre du film à Chercher : ");
        titre = clavier.readLine();
        rechercherFilmParTitre(titre);

        System.out.print("Afficher tous les films : ");
        // Afficher tous les films
        afficherToutesLesFilms();

        // Sauvegarder films.obj
        sauvegarderMapFichierObj();
    } //main


   // Charger le fichier texte - ok
    public static void lireFichier(String nomFichier) throws IOException {
        String ligne;
        String[] composants = new String[NOMBRE_ATTRIBUTS];
        bufLire = new BufferedReader(new FileReader(nomFichier));
        while ((ligne = bufLire.readLine()) != null) {
            composants = ligne.split(";");
            int num = Integer.parseInt(composants[0]);
            String titre = composants[1];
            int categ = Integer.parseInt(composants[2]);
            String langue = composants[3];
            int etoiles = Integer.parseInt(composants[4]);
            mapFilms.put(num,new Film(num,titre,categ,langue,etoiles));
        }
        bufLire.close();
        System.out.println("ChargeText---can see the print result at 1st launch");
        System.out.println(Arrays.asList(mapFilms)); //can see the print result at 1st launch

    }
    // Charger le fichier obj - ok
    public static void chargerFilmsObjet(String fichier) throws Exception {
        try {
            bufLireObj = new ObjectInputStream(new FileInputStream(fichier));
            mapFilms = (Map<Integer, Film>) bufLireObj.readObject();
        } catch(FileNotFoundException e){
            System.out.println("Fichier introuvable. Vérifiez le chemin et nom du fichier.");
        }catch(IOException e){
            System.out.println("Un probléme est arrivé lors de la manipulation du fichier. Vérifiez vos données.");
        }catch(Exception e){
            System.out.println("Un probléme est arrivé lors du chargement du fichier. Contactez l'administrateur.");
        } finally {// Exécuté si erreur ou pas
            if(bufLireObj != null){
                bufLireObj.close();
            }
        }
    }

    // Ajouter un film --ok
    public static void  ajouterFilm() {
        int num = Integer.parseInt(JOptionPane.showInputDialog(null, "Entrez le numéro du film", "Ajout d'un film", JOptionPane.PLAIN_MESSAGE));
        String titre = JOptionPane.showInputDialog(null, "Entrez le titre du film", "Ajout d'un film", JOptionPane.PLAIN_MESSAGE);
        int category = Integer.parseInt(JOptionPane.showInputDialog(null, "Entrez le code catégorie du film", "Ajout d'un film", JOptionPane.PLAIN_MESSAGE));
        String langue = JOptionPane.showInputDialog(null, "Entrez la langue du film", "Ajout d'un film", JOptionPane.PLAIN_MESSAGE);
        int etoiles = Integer.parseInt(JOptionPane.showInputDialog(null, "Entrez le nombre d'étoiles'film", "Ajout d'un film", JOptionPane.PLAIN_MESSAGE));
        mapFilms.put(num,new Film(num,titre,category,langue,etoiles));
        System.out.print("Le film est bien ajouté ! ");
    }

    // Supprimer un film --ok
    public static void supprimerFilm(int num){
        Film filmExiste;
        do {
            filmExiste = mapFilms.remove(num);
            if (filmExiste == null) {
                System.out.println("Le livre de numéro " + num + " n'existe pas !");
            }
        }while(filmExiste == null && num != -1);
    }

    // Modifier un film avec titre --ok
    public static void modifierTitre(int num, String titre) {
        Film leFilm = mapFilms.get(num);
        if (leFilm == null) {
            System.out.print("Le livre de numéro \" + num + \" n'existe pas !");
        } else{
            leFilm.setTitre(titre);
            System.out.println("Le titre est bien changé !");
        }
    }

    // Trier en ordre croissant des numéros (num) et affiche après.
    public static void trierEtAfficherFilmsParNumero(){
       // mapFilms.put(Map.Entry.comparingByValue());

    }
    
    // Rechercher et afficher le contenu (via appel à toString de la classe Film) --ok
    public static void rechercherFilmParTitre(String titre){
        Film leFilm;
        Boolean trouve = true;
        for (Integer num : mapFilms.keySet()) {
            leFilm = mapFilms.get(num);
            if(leFilm.getTitre().equals(titre)){
                System.out.println(leFilm);
                trouve = true;
                break;
            } else {
                trouve = false;
            }
            if(trouve == false){
                System.out.print("le film n'existe pas : ");
            }
        }
    }

    // Lister les films avec GUI --ok
    public static void afficherToutesLesFilms(){
        nbFilms = 0;
        Film unFilm = null;
        jtaContenu = new JTextArea(10,0);
        JScrollPane defilement = new JScrollPane(jtaContenu);
        String resultat="\t\tLISTE DES FILMS\n";
        resultat +="Numéro\t"+"Titre\t\t"+"\tLangue\tEtoiles\n";
        for (Integer num : mapFilms.keySet()) {
            resultat += mapFilms.get(num).toString();
            nbFilms++;
        }
        resultat += "Nombre de films : "+ nbFilms;

        jtaContenu.setFont(new Font("Comic Sans MS", Font.BOLD + Font.ITALIC, 14));
        jtaContenu.setBackground(Color.pink);
        jtaContenu.setForeground(Color.white);
        jtaContenu.setText(resultat);
        JOptionPane.showMessageDialog(null, defilement, "Liste des films", JOptionPane.PLAIN_MESSAGE);
    }

    // Sauvegarder dans fichier d'objets--ok
    public static void sauvegarderMapFichierObj() throws Exception{
        try {
            bufEcrireObj = new ObjectOutputStream(new FileOutputStream(nomFichierObj));
            bufEcrireObj.writeObject(mapFilms);
        } catch(FileNotFoundException e){
            System.out.println("Fichier introuvable. Vérifiez le chemin et nom du fichier.");
        }catch(IOException e){
            System.out.println("Un probléme est arrivé lors de la manipulation du fichier. Vérifiez vos données.");
        }catch(Exception e){
            System.out.println("Un probléme est arrivé lors du chargement du fichier. Contactez l'administrateur.");
        } finally {// Exécuté si erreur ou pas
            if(bufEcrireObj != null){
                bufEcrireObj.close();
            }
        }
    }

}
