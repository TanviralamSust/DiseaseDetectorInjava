/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newtry;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
import javafx.scene.input.MouseEvent;
import static newtry.NewTry.window;
import oracle.net.aso.i;

/**
 * FXML Controller class
 *
 * @author NIHAL
 */
public class ThirdSceneController implements Initializable {
    @FXML
    private ListView<String> listView;
    public static ArrayList<String> clicksymtomslist = new ArrayList<>();
    
    private void addStringItems() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
        for(int i=0;i<SecondSceneController.finalMatchedDis.size();i++){
            {
                listView.getItems().add(SecondSceneController.finalMatchedDis.get(i));
                
       
            }
          
        }
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            addStringItems();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(ThirdSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
       ; 
    }    

    @FXML
    private void buttonClicked(ActionEvent event) throws IOException {
       FirstSceneController.unCommonSym.clear();
       SecondSceneController.finalMatchedDis.clear();
        Parent root = FXMLLoader.load(getClass().getResource("FirstScene.fxml"));
        
        Scene scene = new Scene(root);
        
        window.setScene(scene);
    }

    @FXML
    private void onbuttonclick(ActionEvent event) throws IOException {
        //FirstSceneController.unCommonSym.clear();
        ;SecondSceneController.finalMatchedDis.clear();
        Parent root = FXMLLoader.load(getClass().getResource("SecondScene.fxml"));
        
        Scene scene = new Scene(root);
       
        
        window.setScene(scene);
    }

    @FXML
    private void mouseClicked(MouseEvent event) throws IOException, ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {

        String str= (String)((ListView)event.getSource()).getSelectionModel().getSelectedItem();
        System.out.println("  "+str);
        Class.forName("org.sqlite.JDBC").newInstance();
        //String connection_name=String.format("jdbc:sqlite:C:/Users/NIHAL/Desktop/sqlite-tools-win32-x86-3130000/Test.db");
        String connection_name=String.format("jdbc:sqlite:src\\MyDoctor.sqlite");
        Connection connection = DriverManager.getConnection(connection_name);
        Statement statement= connection.createStatement();
        //int i = 0;
        ResultSet result = statement.executeQuery("select distinct symptoms from diseases where disease_name='"+str+"'");
        
        //thirdSceneNum = 0;
        while(result.next())
        {          
            String name = result.getString("symptoms");
            //System.out.println(" third scene name"+name);
            clicksymtomslist.add(name);
            //thirdScene[thirdSceneNum++] = name;
            //System.out.println(i+++"."+name);
            
        }
        for(int k = 0;k<ThirdSceneController.clicksymtomslist.size();k++){
            System.out.println("for symtoms for common disases 3rd ="+clicksymtomslist.get(k));
        //System.out.println("third" +thirdSceneNum);
        System.out.println("third scene database has printed");
    }
           
            

           // System.out.println("ending moment= "+ ThirdSceneController.clicksymtomslist.get(i));
        Parent root = FXMLLoader.load(getClass().getResource("ForthScene.fxml"));
        
        Scene scene = new Scene(root);
        
        window.setScene(scene);
    }
    
}
