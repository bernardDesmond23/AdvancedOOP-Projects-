
package GUI;

import data.DBConnection;
import data.DBOperations;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class SimpleGUI extends Application{

    @Override
    public void start(Stage primaryStage) {
         
        //create nodes
        Label bll =new Label("SEARCH");
        bll.setTextFill(Color.RED);
        bll.setFont(new Font("Times New Roman",20));
        TextField tt= new TextField();
        tt.setPromptText("ENTER THE ANIMAL ID");
        Button but =new Button("SEARCH");
        but.setStyle("-fx-background-color:blue; -fx-text-fill:white;");
        Label lb=new Label("UPDATE RHINO");
        lb.setFont(new Font("Times New Roman",20));
        lb.setTextFill(Color.RED);
        TextField tf= new TextField();
        tf.setPromptText("ENTER THE NUMBER OF HORNS");
         TextField ft= new TextField();
           ft.setPromptText("ENTER THE SPECIES");
          TextField tff= new TextField();
            tff.setPromptText("ENTER THE ANIMAL ID");
        Button btn = new Button("UPDATE");
        btn.setStyle("-fx-background-color:blue; -fx-text-fill:white;");
        
        Label bl= new Label("INSERT RHINO");
         bl.setFont(new Font("Times New Roman",20));
         bl.setTextFill(Color.RED);
        TextField fft= new TextField();
        fft.setPromptText("WRITE THE ANIMAL ID HERE ");
         TextField tft = new TextField();
           tft.setPromptText("ENTER THE SPECIES HERE ");
          TextField tfx= new TextField();
            tfx.setPromptText("ENTER THE NAME ");
           TextField txx= new TextField();
             txx.setPromptText("ENTER THE HABITANT ");
           TextField ttx= new TextField();
             ttx.setPromptText("ENTER THE AGE");
            TextField ffx= new TextField();
              ffx.setPromptText("ENTER CONSUMER TYPE ");
             TextField xfx= new TextField();
               xfx.setPromptText("ENTER THE NO OF HORNS");
        Button bn = new Button("INSERT");
         bn.setStyle("-fx-background-color:blue; -fx-text-fill:white;");
        
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               // System.out.println("I am working");
              //int id= Integer.getInteger(tf.getText().toString());// 
              String hn= tf.getText();
              String sp= ft.getText();
              String aniId= tff.getText();
              
                DBConnection dbc= new DBOperations();
                dbc.updateOperation(hn, sp, aniId);
               
    //dbc.updateOperation(id);
            }
        });/*the new EventHandler<ActionEvent>() is a shorter version of creating an instance and 
        it shows creation of inner classes 
        */
        bn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               // System.out.println("I am working");
              // int id= Integer.getInteger(tf.getText().toString());// 
              
              String anId= fft.getText();
              String spe= tft.getText();
              String nam= tfx.getText();
              String hab= txx.getText();
              String age= ttx.getText();
              String ct= ffx.getText();
              String hrn=xfx.getText();
                DBConnection dbc= new DBOperations();
               
                dbc.insertOperation(anId, spe, nam, hab, age, ct, hrn);
    //dbc.updateOperation(id);
            }
        });
         but.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               // System.out.println("I am working");
              // int id= Integer.getInteger(tf.getText().toString());// 
              
              String anId= tt.getText();
              
                DBConnection dbc= new DBOperations();
               
                dbc.selectOperation(anId);
    //dbc.updateOperation(id);
            }
        });
        //CREATE SCENE GRAPH
       /* BorderPane pane = new BorderPane();
        HBox hbox= new HBox();
        pane.setTop(hbox);
        addStackPane(hbox);
       
        pane.setLeft(root);*/
        
         VBox root= new VBox(10);    
        root.setStyle("-fx-background-color:D3D3D3;");
        root.getChildren().addAll(bll,tt,but,lb,tf,ft,tff,btn,bl,fft,tft,tfx,txx,ttx,ffx,xfx,bn);
        root.setAlignment(Pos.CENTER);
        //create a scene
        Scene scene = new Scene(root,700,600);
        primaryStage.setScene(scene);
        primaryStage.show();
       
    }
    
    public static void main(String[] args){
        launch(args);
    }
    
}
