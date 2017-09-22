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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import static newtry.NewTry.window;
import static newtry.ThirdSceneController.clicksymtomslist;

/**
 * FXML Controller class
 *
 * @author NIHAL
 */
public class DiseaseCheckController implements Initializable {
    @FXML
    private ListView<String> listView;
    public static ArrayList<String> diseaseSymtomList = new ArrayList<>();
    private void addStringItems(int n){
        for(int i=0;i<n;i++){
            //listView.getItems().add(new Button(NewTry.diseaselist.get(i)));
            listView.getItems().add(NewTry.diseaselist.get(i));
        
        }
    }
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //addButtonItems(NewTry.diseaselist.size());
        addStringItems(NewTry.diseaselist.size());
    }    

    @FXML
    private void mouseClicked(MouseEvent event) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException, IOException {
        String str1= (String)((ListView)event.getSource()).getSelectionModel().getSelectedItem();
        System.out.println("  "+str1);
        Class.forName("org.sqlite.JDBC").newInstance();
        //String connection_name=String.format("jdbc:sqlite:C:/Users/NIHAL/Desktop/sqlite-tools-win32-x86-3130000/Test.db");
        String connection_name=String.format("jdbc:sqlite:src\\MyDoctor.sqlite");
        Connection connection = DriverManager.getConnection(connection_name);
        Statement statement= connection.createStatement();
        //int i = 0;
        ResultSet result = statement.executeQuery("select distinct symptoms from diseases where disease_name='"+str1+"'");
        
        //thirdSceneNum = 0;
        while(result.next())
        {          
            String name = result.getString("symptoms");
            //System.out.println(" third scene name"+name);
            diseaseSymtomList.add(name);
            //thirdScene[thirdSceneNum++] = name;
            //System.out.println(i+++"."+name);
            
        }
        Parent root = FXMLLoader.load(getClass().getResource("DiseaseSingle.fxml"));
        
        Scene scene = new Scene(root);
        
        window.setScene(scene);
    }

    @FXML
    private void buttonClicked(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        
        Scene scene = new Scene(root);
        
        window.setScene(scene);
    }
    
    
}
