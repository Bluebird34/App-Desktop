import javax.swing.*;

public class Utilitaires {

    //function afficherMessage
    public static void afficherMessage(String msg){
        JOptionPane.showMessageDialog(null,msg, "Message", JOptionPane.PLAIN_MESSAGE);
    }

    //function ajouterEspaces
    public static String ajouterEspaces(int tailleColonne, String donnee, char posEspaces) {
        String donneeAvecEspaces = "";
        int nbEspaces = tailleColonne - donnee.length();
        for (int i = 0; i < nbEspaces; i++) {
            donneeAvecEspaces += " ";
        }
        if (posEspaces == 'F'){// Espaces à la fin de la donnée
            return donnee+donneeAvecEspaces;
        }else{ // Espaces au début de la donnée
            return donneeAvecEspaces + donnee;
        }
    }
}
