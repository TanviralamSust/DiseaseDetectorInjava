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
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import static newtry.NewTry.thirdScene;
import static newtry.NewTry.thirdSceneNum;
import static newtry.NewTry.window;

/**
 * FXML Controller class
 *
 * @author NIHAL
 */
public class SecondSceneController implements Initializable {
    ArrayList<String> seletedUncommon = new ArrayList<>();
    public static ArrayList<String> finalMatchedDis = new ArrayList<>();
    @FXML
    private ListView<CheckBox> listView;
    @FXML
    private Label secondlastLabel;
    private void addCheckBoxItems(int n){
        for(int i=0;i<n;i++){
            listView.getItems().add(new CheckBox(FirstSceneController.unCommonSym.get(i)));
        }
    }
 
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //System.out.println(NewTry.stringNum);
        addCheckBoxItems(FirstSceneController.unCommonSym.size());
    } 

    @FXML
    private void buttonClicked(ActionEvent event) throws Exception {
       if(checkboxitem2()){
        checkboxitem2();
        
        Parent root = FXMLLoader.load(getClass().getResource("ThirdScene.fxml"));
        
        Scene scene = new Scene(root);
       
        
        window.setScene(scene);
       }
        else secondlastLabel.setVisible(true);
    }
    private boolean checkboxitem2() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
                ObservableList<CheckBox> selected;
                boolean retValue1 = false;
        selected = listView.getItems();
        int i=0;
        //System.out.println(""+selected);
        for(CheckBox m: selected)
        {
            //System.out.println("asjsak");
            if(m.isSelected())
            {
                retValue1 = true;
                //NewTry.uncommonSymtom[i++]=m.getText();
                seletedUncommon.add(m.getText());
               // System.out.println("2nd control");
            }
        }
        if(retValue1==false) return retValue1;
        for(int j = 0;j<seletedUncommon.size();j++){
            System.out.println("selected uncommon symtom in 3rd conroler ="+seletedUncommon.get(j));
        }
       // akhn porjnto shob thik select kora uncommon symtom gulo amr selecteduncommon namer arrayList te ashhe gechhe//
        Class.forName("org.sqlite.JDBC").newInstance();
        //String connection_name=String.format("jdbc:sqlite:C:/Users/NIHAL/Desktop/sqlite-tools-win32-x86-3130000/Test.db");
        String connection_name=String.format("jdbc:sqlite:src\\MyDoctor.sqlite");
        Connection connection = DriverManager.getConnection(connection_name);
        Statement statement= connection.createStatement();
        String query="";
        //String[] uncommonSymptoms = {"back pain","cokhbetha"};
        //aikhan a je uncoomon symtoms gulate check dibe tar upor depend kore ki disease hoise ta user ke bole dibe//
        for(int j=0;j<seletedUncommon.size();j++)
             {
                 
                 if(j==0)
                 
                 query = "select distinct disease_name from diseases where (symptoms = '"+seletedUncommon.get(j)+"'";
                 else
                 {
                     query+=" or symptoms = '"+seletedUncommon.get(j)+"'";
                 }
             }
             if(seletedUncommon.size()!=0)
             {
                 query+= " and flag=1)";
                 
             }
             System.out.println("this is the query  ="+query);
            ResultSet result = statement.executeQuery(query);
            
            System.out.println("this is the 2nd uncoomn  thing"+result);
        
        //ResultSet result = statement.executeQuery("select distinct disease_name from diseases where (symptoms = '"+uncommonSymptoms[0]+"'or symptoms = '"+uncommonSymptoms[1]+"') and flag=1");
        System.out.println("Symtoms matched with followings : ");
        i=1;
        //thirdSceneNum = 0;
        while(result.next())
        {          
            String name = result.getString("disease_name");
            finalMatchedDis.add(name);
            //thirdScene[thirdSceneNum++] = name;
            System.out.println(i+++"."+name);
            
        }
        for(int k = 0;k<finalMatchedDis.size();k++){
            System.out.println("now printing the diseases that you are suffering from ="+finalMatchedDis.get(k));
        System.out.println("third" +thirdSceneNum);
        System.out.println("database has printed");
        
    }
        return retValue1;
}

    @FXML
    private void onbuttonclick(ActionEvent event) throws IOException {
        FirstSceneController.unCommonSym.clear();
        Parent root = FXMLLoader.load(getClass().getResource("FirstScene.fxml"));
        
        Scene scene = new Scene(root);
       
        
        window.setScene(scene);
    }
}
