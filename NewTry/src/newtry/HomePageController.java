/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newtry;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import static newtry.NewTry.window;

/**
 * FXML Controller class
 *
 * @author NIHAL
 */
public class HomePageController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void creditsclick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Credits.fxml"));
        
        Scene scene = new Scene(root);
        
        window.setScene(scene);
    }

    @FXML
    private void healthcheckupclicked(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FirstScene.fxml"));
        
        Scene scene = new Scene(root);
        
        window.setScene(scene);
    }

    @FXML
    private void diseaseQueryclicked(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("DiseaseCheck.fxml"));
        
        Scene scene = new Scene(root);
        
        window.setScene(scene);
    }
    
    
}
