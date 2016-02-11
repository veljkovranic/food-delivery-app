package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CircleBuilder;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("AmigoDelivery.fxml"));
        primaryStage.setScene(new Scene(root, 800, 500));
        primaryStage.show();
        try {
            connectToAndQueryDatabase("root", "gladijator");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void connectToAndQueryDatabase(String username, String password) throws Exception {

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance ();
        }
        catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        try {
            String url = "'jdbc:mysql://localhost:3306/mysql";
            Connection con = DriverManager.getConnection(url, username, password);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT adress FROM 'orders'");

            while (rs.next()) {
                String s = rs.getString("adress");
                System.out.println(s);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
        launch(args);
    }
}
