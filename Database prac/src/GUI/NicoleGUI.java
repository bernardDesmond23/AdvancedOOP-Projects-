 package GUI;
  // import Database.DBConnection;
//import Database.DBOperations;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class NicoleGUI extends Application {
 


     Stage window;
     Scene scene1, scene2;
     
    
     
    @Override
    public void start(Stage primaryStage) {
        
        window = primaryStage;
        
        
        Label lb1 = new Label(" WELCOME TO TETU PASTRYS!!");
        lb1.setFont(new Font("Times New Romans",30));
        lb1.setTextFill(Color.BLACK);
        ImageView icon = new ImageView(new Image(getClass().getResource("/GUI/catt.jpg").toExternalForm())); // Replace with your image path
        icon.setFitWidth(100);
        icon.setFitHeight(100);
       
  // Load the background image
  
        Image backgroundImage = new Image(getClass().getResource("/GUI/catt.jpg").toExternalForm());
        BackgroundImage bImg = new BackgroundImage(
            backgroundImage,
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.CENTER,
            new BackgroundSize(100, 100, true, true, false, true)
        );
        Background background = new Background(bImg);

        // Set the background of the layout
        
        Button btn1  = new Button("NEXT");
        btn1.setOnAction(e -> window.setScene(scene2));
        
        
        //Lb1
        VBox layout1 = new VBox(50);
        layout1.getChildren().addAll(lb1, btn1);
        
        scene1 = new Scene (layout1, 600,  400);
        //layout1.setStyle("-fx-background-color:beige;");
        layout1.setBackground(background);
        // Create tabs
        TabPane tabPane = new TabPane();
       tabPane.setStyle("-fx-background-color:beige;");

        // Insert Page
        Tab insertTab = new Tab("INSERT");
        VBox insertBox = new VBox();
        insertBox.setAlignment(Pos.CENTER);
        insertTab.setStyle("-fx-background-color:pink;");
        
        Label lbinsertpid = new Label(" New Pastryid");
        TextField insertpid = new TextField();
        
        
        Label lbinsertptype = new Label(" New Pastrytype");
        TextField insertptype = new TextField();
        
        Label lbinsertpflavour = new Label(" New Pastryflavour");
        TextField insertpflavour = new TextField();
        
        Label lbinsertpquantity = new Label(" New Pastryquantity");
        TextField insertpquantity = new TextField ();
        
        Button insertButton = new Button("INSERT");
        
        
        insertButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    int pid = Integer.parseInt(insertpid.getText());
                    String ptype = insertptype.getText();
                    String pflavour = insertpflavour.getText();
                    int pquantity = Integer.parseInt(insertpquantity.getText());

                  //  DBOperations dbc = new DBOperations();
                    //dbc.insertOperation(pid, ptype, pflavour, pquantity);

                    // Clear input fields
                    insertpid.clear();
                    insertptype.clear();
                    insertpflavour.clear();
                    insertpquantity.clear();
                } catch (NumberFormatException e) {
                    showAlert(Alert.AlertType.ERROR, "Invalid Input", "Please enter a valid number for quantity.");
                }
            }
        });
        insertBox.getChildren().addAll( lbinsertpid, insertpid,lbinsertptype, insertptype, lbinsertpflavour, insertpflavour,
                lbinsertpquantity, insertpquantity, insertButton);
        
        insertTab.setContent(insertBox);

        
        // Update Page
        Tab updateTab = new Tab("UPDATE");
        VBox updateBox = new VBox();
        updateBox.setAlignment(Pos.CENTER);
        updateTab.setStyle("-fx-background-color:pink;");
        
        
        Label lbupdatepid = new Label("Update Pastryid");
        TextField updatepid = new TextField();
        
        Label lbupdateptype = new Label("Update Pastrytype");
        TextField updateptype = new TextField();
        
        Label lbupdatepflavour = new Label("Update Pastryflavour");
        TextField updatepflavour = new TextField();
        
        Label lbupdatepquantity = new Label("Update Pastryquantity");
        TextField updatepquantity = new TextField();
        
        Button updateButton = new Button("UPDATE");
        
        updateButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    int pid = Integer.parseInt(updatepid.getText());
                    String ptype = updateptype.getText();
                    String pflavour = updatepflavour.getText();
                    int pquantity = Integer.parseInt(updatepquantity.getText());

                   // DBOperations dbc = new DBOperations();
                   // dbc.updateOperation(pid,ptype, pflavour, pquantity);

                    // Clear input fields
                    updatepid.clear();
                    updateptype.clear();
                    updatepflavour.clear();
                    updatepquantity.clear();
                } catch (NumberFormatException e) {
                    showAlert(Alert.AlertType.ERROR, "Invalid Input", "Please enter valid numbers for ID and quantity.");
                }
            }
        });
        
        updateBox.getChildren().addAll(lbupdatepid, updatepid, lbupdateptype, updateptype, lbupdatepflavour, updatepflavour,
                lbupdatepquantity, updatepquantity, updateButton);
        updateTab.setContent(updateBox);

        // Search Page
        Tab searchTab = new Tab("SEARCH ");
        VBox searchBox = new VBox();
        searchBox.setAlignment(Pos.CENTER);
        searchTab.setStyle("-fx-background-color:pink;");
        
        
        Label searchLabel = new Label("Pastry ID");
        TextField searchField = new TextField();
        Button searchButton = new Button("SEARCH");

        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    int id = Integer.parseInt(searchField.getText());
                    //DBConnection dbc = new DBOperations();
                   // dbc.selectOperation(id);
                } catch (NumberFormatException e) {
                    System.err.println("Invalid input. Please enter a valid integer.");
                }
            }
        });

        searchBox.getChildren().addAll(searchLabel, searchField, searchButton);
        searchTab.setContent(searchBox);
        
        
        // Delete Page
        Tab deleteTab = new Tab("DELETE");
        VBox deleteBox = new VBox(10);
        deleteBox.setAlignment(Pos.CENTER);
        deleteTab.setStyle("-fx-background-color:pink;");

        Label lbdeleteid = new Label("Pastry ID to Delete");
        TextField deleteId = new TextField();

        Button deleteButton = new Button("DELETE");
        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    int id = Integer.parseInt(deleteId.getText());

                  //  DBOperations dbc = new DBOperations();
                 //   dbc.deleteOperation(id);

                    // Clear input field
                    deleteId.clear();
                } catch (NumberFormatException e) {
                    showAlert(Alert.AlertType.ERROR, "Invalid Input", "Please enter a valid number for ID.");
                }
            }
        });

        deleteBox.getChildren().addAll(lbdeleteid, deleteId, deleteButton);
        deleteTab.setContent(deleteBox);
        // Add tabs to tabPane
        tabPane.getTabs().addAll(insertTab, updateTab, searchTab, deleteTab);

        // Create a scene
        /*Scene scene = new Scene(tabPane, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("PASTRY HUB");
        primaryStage.show();
         */
        
         scene2 = new Scene(tabPane, 600, 400);
        window.setTitle(" PASTRY HUB ");
        window.setScene(scene1);
        window.show(); 
    }
    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
 public static void main(String[] args) {
        launch(args);
    }
    
} 
 /*integrate this other code into the buttons where relevant so that the operations can work
package PlantGui;

import RMI.databaseint;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.RemoteException;
import java.rmi.NotBoundException;

public class Test {
     public static void main(String[] args){
        
        try{
      Registry registry  =LocateRegistry.getRegistry("192.168.56.1", 1099);//ip address,port
      
      databaseint dii = (databaseint) registry.lookup("DatabaseReg");
      
      String[] result = dii.selectOperations(6);
      
      for(int i=0; i< result.length; i++ ){
          System.out.println(result[i]);
      }
      
    } catch  (RemoteException ex)
    {
        System.out.println("Server not found");
    } 
        
    catch  (NotBoundException ex){
    System.out.println(ex.getMessage());
   }
    
}
}*/