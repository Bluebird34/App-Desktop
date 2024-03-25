package edu.java.Animal;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class VueAnimal {

    // **************************** SINGLETON ***********************************************

    private static VueAnimal instance = null;

    private VueAnimal(){}

    public static VueAnimal getInstance(){
        // À completer
        if(instance == null){
            instance = new VueAnimal();
        }
        return instance;
    }
    // **************************** SINGLETON ***********************************************

    public void Vue_Message(String msg){  
        //System.out.println("Message reçue : " + msg);
        JOptionPane.showMessageDialog(null,msg, "Message", JOptionPane.PLAIN_MESSAGE);
    }

    public void Vue_ListerAnimaux(ResultSet resultSet) throws SQLException{
        int count = 0;
        // Afficher les animaux dans un JTable de votre interface
        String sortie = "\t\tLISTE DES ANIMALS\n";
        while(resultSet.next()) {
            String nom = resultSet.getString("nom");
            String classe = resultSet.getString("classe");
            int longevite = resultSet.getInt("longevite");
            String habitat = resultSet.getString("habitat");
            String fait = resultSet.getString("fait");
            Animal unAnimal = new Animal(nom,classe,longevite,habitat,fait);
          //  sortie += unAnimal.afficher(); 
            sortie += unAnimal.toString();         
            count++;

           /*  System.out.println("Nom de Animal : " + nom);
            System.out.println("Classe : " + classe);
            System.out.println("Longevite : " + longevite);
            System.out.println("Habitat : " + habitat);
            System.out.println("Fait : " + fait);            
            System.out.println("---------------------------"); */
        }
        /*  System.out.println("============");
        System.out.println("Total : " + count); */
        sortie += "Total Nombre : "+ count;
        JOptionPane.showMessageDialog(null, sortie, null, JOptionPane.PLAIN_MESSAGE); 
    }
} // class

