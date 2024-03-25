package edu.java.Animal;


import edu.java.BD.ConnexionBD;

import java.sql.*;


public class DAOAnimal{
    private Connection connexion = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private String msg = "";

    // ****************** PARTIE SINGLETON *********************

    private static DAOAnimal instance = null;

    private DAOAnimal(){}


    public static DAOAnimal getInstance(){
        if(instance == null) {
            instance = new DAOAnimal();
        }
        return instance;
    }
    //************************ FIN PARTIE SINGLETON ********************

    public String Mdl_ajouter(Animal unAnimal) throws Exception {
        try {
            // À compléter
            connexion = ConnexionBD.getInstance().getConnexionMySQL(); //get a singlton connection
            if(connexion != null) {
                //Creat preparedStatement for query with param
                preparedStatement = connexion.prepareStatement("INSERT INTO  animaux VALUES (?, ?, ?, ?, ? )");
                preparedStatement.setString(1,unAnimal.getNom());
                preparedStatement.setString(2,unAnimal.getClasse());
                preparedStatement.setInt(3, unAnimal.getLongevite());
                preparedStatement.setString(4,unAnimal.getHabitat());
                preparedStatement.setString(5,unAnimal.getFait());

                preparedStatement.executeUpdate();
                msg = "Animal " + unAnimal.getNom() + " est ajouté avec succès";
            }else {
                // throw new SQLException("Erreur de connection à la base de donnee");
                msg = "Erreur de connection à la base de donnee";
            }

        } catch (Exception e) {
            msg = "Problème lors de l'exécution de cette requête.";
        }
        fermer();
        return msg;
    }

    public String Mdl_supprimer(String nom) throws Exception {
        try {
            // À compléter
            connexion = ConnexionBD.getInstance().getConnexionMySQL();
            if(connexion != null) {
                preparedStatement = connexion.prepareStatement("DELETE FROM animaux WHERE nom=? ; ");
                preparedStatement.setString(1,nom);
                preparedStatement.executeUpdate();
                msg = "Animal " + nom + "  a été supprimé.";
            }else {
                //  throw new SQLException("Erreur de connection à la base de donnee");
                msg = "Erreur de connection à la base de données.";
            }

        } catch (Exception e) {
            msg = "Problème lors de l'exécution de cette requête.";
        }
        fermer();
        return msg;
    }

    public String Mdl_modifierLongevite(String nom, String longevite) throws Exception {
        try {
            // À compléter //not sure about the select sql
            connexion = ConnexionBD.getInstance().getConnexionMySQL();
            if(connexion != null) {
                preparedStatement = connexion.prepareStatement("UPDATE animaux SET longevite=? WHERE nom=? ; ");
                preparedStatement.setInt(1, Integer.parseInt(longevite));
                preparedStatement.setString(2,nom);

                preparedStatement.executeUpdate();
                //fermer(); //will return null
                msg = "Animal " + nom + "  a été modifié.";
            }else {
                //throw new SQLException("Erreur de connection à la base de donnee");
                msg = "Erreur de connection à la base de données.";
            }

        } catch (Exception e) {
            msg = "Problème lors de l'exécution de cette requête.";
        }
        fermer();
        return msg;
    }

    public ResultSet Mdl_afficherToutesLesAnimaux() throws Exception {
        try {
            // À compléter
            connexion = ConnexionBD.getInstance().getConnexionMySQL();
            if(connexion != null) {
                statement = connexion.createStatement();
                resultSet = statement.executeQuery("SELECT * FROM animaux");
              //  return resultSet;

            }else {
                fermer();
                throw new SQLException("Erreur de connection à la base de donnee");
            }
                
        }catch(Exception e){
            fermer();
            throw new Exception("Problème lors de l'exécution de cette requête.");
        }

/*        //Show the resultSet //the DB connects fine
        System.out.println("\n********* DONNÉES AU DÉPART ************");
        afficherResultat(resultSet);*/
        return resultSet;
    }

    public ResultSet Mdl_rechercherAnimalParNom(String nom) throws Exception {
        try {
            // À compléter
            connexion = ConnexionBD.getInstance().getConnexionMySQL();
            if (connexion != null) {
                preparedStatement = connexion.prepareStatement("SELECT * FROM animaux WHERE nom=?");
                // Les paramétres commencent à 1
                preparedStatement.setString(1, nom);
                resultSet = preparedStatement.executeQuery();
               // return resultSet;
            } else {
                throw new Exception("Erreur de connection à la base de données.");
            }

        }catch(Exception e){
            fermer();
            throw new Exception("Problème lors de l'exécution de cette requête.");
        }
        return resultSet;
    }

    public ResultSet Mdl_afficherAnimauxEntreDeuxAges(int ageMin, int ageMax) throws Exception {
        try {
            // À compléter //not sure about the sql select
            connexion = ConnexionBD.getInstance().getConnexionMySQL();
            if (connexion != null) {
                preparedStatement = connexion.prepareStatement("SELECT * FROM animaux WHERE longevite BETWEEN ? AND ?");
                // Les paramétres commencent à 1
                preparedStatement.setInt(1, ageMin);
                preparedStatement.setInt(2, ageMax);
                resultSet = preparedStatement.executeQuery();
              //  return resultSet;
            } else {
                throw new Exception("Erreur de connection à la base de données.");
            }
        } catch (Exception e) {
            fermer();
            throw new Exception("Problème lors de l'exécution de cette requête.");
        }
        return resultSet;
    }

    // Libérer les ressources
    private void fermer() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (preparedStatement != null) {
                preparedStatement.close();
            }

            if (connexion != null) {
                connexion.close();
            }
        } catch (Exception e) {

        }
    }


}