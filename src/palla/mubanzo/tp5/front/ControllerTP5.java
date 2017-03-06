package palla.mubanzo.tp5.front;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import palla.mubanzo.tp5.back.ChargerGrille;
import palla.mubanzo.tp5.back.MotsCroisesTP5;

import javax.xml.soap.Node;

public class ControllerTP5 {

    @FXML
    private GridPane monGridPane;

    MotsCroisesTP5 mc;

    public void initialize(){
        ChargerGrille s = new ChargerGrille();
        this.mc = s.extraireGrille();

        TextField modele = (TextField) monGridPane.getChildren().get(0);
        monGridPane.getChildren().clear();

        for(int i = 1; i <= mc.getHauteur();i++){
            for(int j = 1; j <= mc.getLargeur();j++){

                if(!mc.estCaseNoire(i,j)){
                    TextField nouveau = new TextField();
                    //On click event
                    nouveau.setOnMouseClicked((e)->{this.clicCase(e);});

                    //Binding de la saisie entre javafx et la grille de mots croisés
                    nouveau.textProperty().bindBidirectional(mc.propositionProperty(i,j));

                    //Preferences des textfields
                    nouveau.setPrefWidth(modele.getPrefWidth());
                    nouveau.setPrefHeight(modele.getPrefHeight());
                    for(Object cle : modele.getProperties().keySet())
                    {
                        nouveau.getProperties()
                                .put(cle, modele.getProperties().get(cle));
                    }
                    nouveau.setTooltip(new Tooltip(mc.getDefinition(i,j,true) +"(Horizontal) / "+mc.getDefinition(i,j,false)+"(Vertical)"));
                    //Attention ici c'est la colonne puis la ligne, et l'index est a 0 contrairement au reste
                    monGridPane.add(nouveau, j - 1, i - 1);

                }

            }
        }
        monGridPane.setGridLinesVisible(true);
    }

    //Prend tous les evenemnts au clique partout
    public void clicCase(MouseEvent e){
        if (e.getButton() == MouseButton.MIDDLE) {
            TextField source = (TextField) e.getSource();
            int lig = GridPane.getRowIndex(source);
            int col = GridPane.getColumnIndex(source);
            source.setText(Character.toString(mc.getSolution(lig+1,col+1)));
        }
    }

}
