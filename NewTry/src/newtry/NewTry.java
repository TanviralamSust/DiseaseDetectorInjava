/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newtry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 *
 * @author NIHAL
 */
public class NewTry extends Application {
    
    public static Stage window;
    public static int stringNum,uncomonNum,thirdSceneNum;
    public static String[] unCommon = new String[100];
    public static String[] string = new String[100]; 
    public static String[] thirdScene = new String[100];
   // public static String[] seletSymtom = new String[100];
    public static String[] uncommonSymtom = new String[100];
    public static ArrayList<String> diseaselist = new ArrayList<>();

    @Override
    public void start(Stage stage) throws Exception {
        window=stage;
        Parent root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        
        Scene scene = new Scene(root);
        
        window.setScene(scene);
        window.show();
    }
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        
        
        // TODO code application logic here
        Class.forName("org.sqlite.JDBC").newInstance();
        //String connection_name=String.format("jdbc:sqlite:C:/Users/NIHAL/Desktop/sqlite-tools-win32-x86-3130000/Test.db");
        String connection_name=String.format("jdbc:sqlite:src\\MyDoctor.sqlite");
        
        Connection connection = DriverManager.getConnection(connection_name);
        Statement statement= connection.createStatement();
        //statement.executeUpdate("insert into student values(2012233,'aaaaa')");
        //statement.executeUpdate("insert into students values('myname,23)");
        int i=1;
        System.out.println("Common symptoms :");
        ResultSet result = statement.executeQuery("select distinct symptoms from Diseases where flag=0");
        stringNum = 0;
        while(result.next())
        {          
            String name = result.getString("symptoms");
            string[stringNum++] = name;
            System.out.println(i+++"."+name);
        }
        result = statement.executeQuery("select distinct disease_name from Diseases");
        while(result.next())
        {          
            String name = result.getString("disease_name");
            diseaselist.add(name);
        }
        for(int k = 0;k<diseaselist.size();k++){
            System.out.println("mydiseases list puka ="+diseaselist.get(k));
        
    }
        
        //String[] commonSymptoms = {"headache","vomiting"};
        
        //i had to use the loop to make query jate amr common cheked symtom theke deases pete pari//
        /*String query="";
             for(int j=0;j<seletSymtom.length;j++)
             {
                 
                 if(j==0)
                 
                 query = "select distinct disease_name from diseases where (symptoms = '"+seletSymtom[j]+"'";
                 else
                 {
                     query+=" or symptoms = '"+seletSymtom[j]+"'";
                 }
             }
             if(seletSymtom.length!=0)
             {
                 query+= " and flag=0)";
                 
             }
             result = statement.executeQuery(query);
            System.out.println(query);
        */
             
            //result= statement.executeQuery("select distinct disease_name from diseases where (symptoms = '"+commonSymptoms[0]+"' or symptoms = '"+commonSymptoms[1]+"') and flag=0");
          // result = statement.executeQuery("select distinct disease_name from diseases where (symptoms = '"+commonSymptoms[0]+"' or symptoms = '"+commonSymptoms[1]+"') and flag=0");
        //result = statement.executeQuery("select distinct disease_name from diseases where (symptoms = '"+commonSymptoms[0]+"' or symptoms = '"+commonSymptoms[1]+"') and flag=0");
        //2nd edit//
       /* System.out.println("Symtoms matched with  followings diseases : ");
        i=1;
       //uncomonNum = 0; 
        while(result.next())
        {          
            String name = result.getString("disease_name");
            //unCommon[uncomonNum++] = name;
            System.out.println(i+++"."+name);
        }
        String[] diseaseName = {"malerria","fever","dengu"};
        //uncommonSymtom staring arra pawa disease name tar size jenne upore moto string a rekhe database a query chalate hobe//
        result = statement.executeQuery("select distinct symptoms from diseases where (disease_name='"+diseaseName[0]+"' or disease_name='"+diseaseName[1]+"' or disease_name='"+diseaseName[2]+"') and flag=1");
        System.out.println("Uncommon symptoms :");
        i=1;
       uncomonNum = 0;
        while(result.next())
        {          
            String name = result.getString("symptoms");
            unCommon[uncomonNum++] = name;
            System.out.println(i+++"."+name);
        }*/
        /*String[] uncommonSymptoms = {"back pain","cokhbetha"};
        //aikhan a je uncoomon symtoms gulate check dibe tar upor depend kore ki disease hoise ta user ke bole dibe//
        result = statement.executeQuery("select distinct disease_name from diseases where (symptoms = '"+uncommonSymptoms[0]+"'or symptoms = '"+uncommonSymptoms[1]+"') and flag=1");
        System.out.println("Symtoms matched with followings : ");
        i=1;
        thirdSceneNum = 0;
        while(result.next())
        {          
            String name = result.getString("disease_name");
            thirdScene[thirdSceneNum++] = name;
            System.out.println(i+++"."+name);
            
        }
        System.out.println("third" +thirdSceneNum);
        System.out.println("database has printed");
        */
            //
            //connection = DriverManager.getConnection(connection_name, Tools.Config.DB_USER, Tools.Config.DB_PASSWORD);
             launch(args);
    }
    //INSERT INTO Diseases VALUES('cokhbetha',1,'highfever');

    /**
     * @param args the command line arguments
     */
    
}
