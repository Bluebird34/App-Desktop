package edu.java.BD;
import edu.java.Env.Env;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class ConnexionBD {
     private Connection connexion = null;
    // **************************** SINGLETON ***********************************************
    private static ConnexionBD instance = null;

    private ConnexionBD(){}

    public static ConnexionBD getInstance(){
        if(instance == null){
            instance = new ConnexionBD();
        }
        return instance;
    }
    // **************************************************************************************

    // Code métier de notre classe Connexion
    public Connection getConnexionMySQL() throws SQLException{
        try {
            // Exemple dans notre cas : "jdbc:mysql://localhost/umbdfilms?user=root&password="
            connexion = DriverManager.getConnection("jdbc:mysql://"+Env.SERVEUR_BD+"/"+Env.BD+"?user="+Env.USER_BD+"&password="+Env.PASS_BD);
        } catch (SQLException e){
            System.out.println("Erreur SQL lors de la connection au serveur");
            connexion = null;
        }
        return connexion;
    }
}
