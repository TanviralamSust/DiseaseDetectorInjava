/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newtry;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import static newtry.DiseaseCheckController.diseaseSymtomList;
import static newtry.NewTry.window;

/**
 * FXML Controller class
 *
 * @author NIHAL
 */
public class DiseaseSingleController implements Initializable {
    @FXML
    private ListView<String> listView;
    private void addStringItems() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
        for(int i=0;i<DiseaseCheckController.diseaseSymtomList.size();i++){
            {
                listView.getItems().add(DiseaseCheckController.diseaseSymtomList.get(i));
       
            }
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            // TODO
            addStringItems();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(ForthSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void prevbuttonclick(ActionEvent event) throws IOException {
        diseaseSymtomList.clear();
        Parent root = FXMLLoader.load(getClass().getResource("DiseaseCheck.fxml"));
        
        Scene scene = new Scene(root);
        
        window.setScene(scene);
    }

    @FXML
    private void mainbuttonclick(ActionEvent event) throws IOException {
        diseaseSymtomList.clear();
                
        Parent root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        
        Scene scene = new Scene(root);
        
        window.setScene(scene);
    }
    
}
