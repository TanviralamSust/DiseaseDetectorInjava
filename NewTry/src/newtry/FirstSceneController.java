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
import java.util.Arrays;
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
import javafx.scene.control.RadioButton;
import static newtry.NewTry.unCommon;
import static newtry.NewTry.uncomonNum;
import static newtry.NewTry.window;

/**
 * FXML Controller class
 *
 * @author NIHAL
 */
public class FirstSceneController implements Initializable {

    //public static String[] seletSymtom = new String[100];

    ArrayList<String> seletSymtom = new ArrayList<>();
    ArrayList<String> commonDiseases = new ArrayList<>();
    public static ArrayList<String> unCommonSym = new ArrayList<>();

    @FXML
    private ListView<CheckBox> listView;
    @FXML
    private Label lastLabel;

    private void addCheckBoxItems(int n) {
        for (int i = 0; i < n; i++) {
            listView.getItems().add(new CheckBox(NewTry.string[i]));

        }
    }


    /*public  Connection connection;
     public ListView<CheckBox> listView;
     public ListView<CheckBox> add(ListView<CheckBox> listView, CheckBox name){
     try {
     //launch(args);
     // TODO code application logic here
     Class.forName("org.sqlite.JDBC").newInstance();
     String connection_name=String.format("jdbc:sqlite:C:/Users/NIHAL/Desktop/sqlite-tools-win32-x86-3130000/Test.db");
     connection = DriverManager.getConnection(connection_name);
     Statement statement= connectionk2.createStatement();
     } catch (Exception e) {
     System.out.println("pkgtry.AddInListView.add()"); //System.out.println("TADA "+e);
     }
     while (connection.hasNextLine()) {
     if (name.equals("list")) {

     listView.getItems().add(connection.nextLine());

     } else {
     listView.getItems().add(.next());
     connection.nextLine();
     }

     }
     return null;
    
     }

     /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //System.out.println(NewTry.stringNum);
        addCheckBoxItems(NewTry.stringNum);
    }

    @FXML
    private void buttonClicked(ActionEvent event) throws IOException, SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        if (checkboxitem()) {

            System.err.println("frgjhj");

            Parent root = FXMLLoader.load(getClass().getResource("SecondScene.fxml"));

            Scene scene = new Scene(root);

            window.setScene(scene);
        }
        else lastLabel.setVisible(true);
    }

    private boolean checkboxitem() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        ObservableList<CheckBox> selected;
        boolean retValue = false;
        selected = listView.getItems();
        int i = 0;
        //System.out.println(""+selected);
        for (CheckBox m : selected) {
            //System.out.println("asjsak");
            if (m.isSelected()) {
                retValue = true;
                seletSymtom.add(m.getText());

                //seletSymtom[i++]=m.getText();
                System.out.println("1st control");
                //System.out.println(seletSymtom[i-1]);
            }
        }
        if(retValue==false) return retValue;
        //System.out.println(Arrays.toString(NewTry.seletSymtom));
        Class.forName("org.sqlite.JDBC").newInstance();
        //String connection_name=String.format("jdbc:sqlite:C:/Users/NIHAL/Desktop/sqlite-tools-win32-x86-3130000/Test.db");
        String connection_name = String.format("jdbc:sqlite:src\\MyDoctor.sqlite");
        Connection connection = DriverManager.getConnection(connection_name);
        Statement statement = connection.createStatement();
        String query = "";

        /*for(String item : seletSymtom){
         if(j==0)
                 
         query = "select distinct disease_name from diseases where (symptoms = '"+seletSymtom[j]+"'";
         else
         {
         query+=" or symptoms = '"+seletSymtom[j]+"'";
         }
         }*/
        for (int j = 0; j < seletSymtom.size(); j++) {

            if (j == 0) {
                query = "select distinct disease_name from diseases where (symptoms = '" + seletSymtom.get(j) + "'";
            } else {
                query += " or symptoms = '" + seletSymtom.get(j) + "'";
            }
        }
        if (seletSymtom.size() != 0) {
            query += " and flag=0)";

        }
        ResultSet result = statement.executeQuery(query);
        System.out.println(query);
        System.out.println(" this is the 1st qurey selected common dis thing" + result);
        //2nd work
        System.out.println("Symtoms matched with  followings diseases : ");
        i = 1;
        //uncomonNum = 0; 
        while (result.next()) {
            String name = result.getString("disease_name");
            commonDiseases.add(name);
            //unCommon[uncomonNum++] = name;
            System.out.println(i++ + "." + name);
        }
        for (int k = 0; k < commonDiseases.size(); k++) {
            System.out.println("print common dis =" + commonDiseases.get(k));
        }
        // String query="";
        for (int l = 0; l < commonDiseases.size(); l++) {

            if (l == 0) //query = "select distinct disease_name from diseases where (symptoms = '"+seletSymtom.get(l)+"'";
            {
                query = "select distinct symptoms from diseases where ((disease_name='" + commonDiseases.get(l) + "'";
            } else {
                query += " or disease_name = '" + commonDiseases.get(l) + "'";
            }
        }
        if (commonDiseases.size() != 0) {

            query += ") and flag=1);";

        }
        System.out.println("this is the query  =" + query);
        ResultSet result2 = statement.executeQuery(query);

        System.out.println("this is the 2nd uncoomn  thing" + result2);
        // String[] diseaseName = {"malerria","fever","dengu"};
        //uncommonSymtom staring arra pawa disease name tar size jenne upore moto string a rekhe database a query chalate hobe//
        //result = statement.executeQuery("select distinct symptoms from diseases where (disease_name='"+diseaseName[0]+"' or disease_name='"+diseaseName[1]+"' or disease_name='"+diseaseName[2]+"') and flag=1");
        System.out.println("Uncommon symptoms :");
        i = 1;
        //uncomonNum = 0;
        while (result.next()) {
            String name = result.getString("symptoms");
            unCommonSym.add(name);
            //unCommon[uncomonNum++] = name;
            System.out.println(i++ + "." + name);
        }
        for (int k = 0; k < unCommonSym.size(); k++) {
            System.out.println("print the  uncommon symtom 3rd =" + unCommonSym.get(k));

        }
        System.out.println("JHakashkaaalasl......ashkas  "+retValue);
        return retValue;
    }

    @FXML
    private void menuButtonClicked(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        
        Scene scene = new Scene(root);
        
        window.setScene(scene);
    }

}
