package palla.mubanzo.tp5;

import java.sql.*;

/**
 * Created by luctu on 02/03/2017.
 */
public class ChargerGrille{
    private Connection maConnection;
    public ChargerGrille(){
        try{
            maConnection = connectionMySQL();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static Connection connectionMySQL() throws SQLException{
        String url = "jdbc:mysql://anteros.istic.univ-rennes1.fr/base_bousse";
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        
        return DriverManager.getConnection(url, "user_123456789", "xxxx");
    }

    public static MotsCroisesTP5 extraireBD(Connection connect, int grille){
        String req = "SELECT * FROM TP5_GRILLE WHERE num_grille = "+grille;
        String req2= "SELECT * FROM TP5_MOT WHERE num_grille ="+grille;

        try{
            //Récupère les données utiles de grille.
            Statement statement = connect.createStatement();
            ResultSet resultatGrille = statement.executeQuery(req);
            resultatGrille.next();
            int hauteur = resultatGrille.getInt("hauteur");
            int largeur = resultatGrille.getInt("largeur");
            MotsCroisesTP5 mc = new MotsCroisesTP5(hauteur, largeur);

            //Récupère les données utiles de mot.
            ResultSet resultatMot = statement.executeQuery(req2);
            Boolean horizontal = resultatMot.getBoolean("horizontal");
            int colonne = resultatMot.getInt("colonne");
            int ligne = resultatMot.getInt("ligne");
            String solution = resultatMot.getString("solution");

            //Insière chaque définition au bon endroit
            while(resultatMot.next()){
                mc.setDefinition(
                        ligne,
                        colonne,
                        horizontal,
                        resultatMot.getString("definition")
                );

                //Place les lettres des solutions
                if(horizontal){
                    for(int i = 0; i<solution.length() ;i++){
                        mc.setSolution(ligne,colonne+i,solution.charAt(i));
                    }
                }
                else{
                    for(int i = 0; i<solution.length() ;i++){
                        mc.setSolution(ligne+i,colonne,solution.charAt(i));
                    }
                }

            }
            return mc;
        }catch(SQLException e){
            e.printStackTrace();
        }
        //Si ca ne fonctionne pas...
        return null;
    }

    public static final int CHOIX_GRILLE = 10;
    public MotsCroisesTP5 extraireGrille(){
        return extraireBD(maConnection, CHOIX_GRILLE);
    }
}