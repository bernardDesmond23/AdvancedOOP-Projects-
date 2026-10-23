package PETS;
//import Database.DBConnection;
//import Database.DBOperation;
import RMI.DatabaseInterface;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class PetsClientGUI extends Application {

    private Scene homeScene, searchScene, insertScene, updateScene, deleteScene,LaunchScene;

    @Override
    public void start(Stage primaryStage) {
       // Scene launchScene = createLaunchScene(primaryStage);
        homeScene = createHomeScene(primaryStage);
        searchScene = createSearchScene(primaryStage);
        insertScene = createInsertScene(primaryStage);
        updateScene = createUpdateScene(primaryStage);
        deleteScene = createDeleteScene(primaryStage);
LaunchScene= createLaunchScene(primaryStage);
       primaryStage.setScene(LaunchScene);
        primaryStage.setTitle("Pet Store");
        primaryStage.show();
    }

     // Create the Home Scene
    private Scene createLaunchScene(Stage primaryStage) {
        Label label = new Label("Pet Store");
       label.setStyle("-fx-text-fill: white;");
        label.setFont(Font.font("garamond", 24));

        Button btnBack = new Button("Pet Store");
        btnBack.setStyle("-fx-background-color: FF9EAA; -fx-text-fill: white;");
        btnBack.setOnAction(e -> primaryStage.setScene(createHomeScene(primaryStage))); // Switch to regular home scene
ImageView icon = new ImageView(new Image(getClass().getResource("/PETS/catt.jpg").toExternalForm())); // Replace with your image path
        icon.setFitWidth(100);
        icon.setFitHeight(100);
        VBox layout = new VBox(20);
        layout.getChildren().addAll(label, btnBack);
        layout.setAlignment(Pos.CENTER);
  // Load the background image
  
        Image backgroundImage = new Image(getClass().getResource("/PETS/catt.jpg").toExternalForm());
        BackgroundImage bImg = new BackgroundImage(
            backgroundImage,
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.CENTER,
            new BackgroundSize(100, 100, true, true, false, true)
        );
        Background background = new Background(bImg);

        // Set the background of the layout
        layout.setBackground(background);

        return new Scene(layout, 800, 600);
    }


    // Create the Home Scene
    private Scene createHomeScene(Stage primaryStage) {
        Label label = new Label("Tiny's Pet Store");
        label.setFont(Font.font("Times New Roman", 26));

        Button btnSearch = new Button("Search for your pet");
        Button btnInsert = new Button("Insert a new pet");
        Button btnUpdate = new Button("Update current pets");
        Button btnDelete = new Button("Delete existing pets");
        

        btnSearch.setStyle("-fx-background-color: #FF9EAA; -fx-text-fill: white;");
        btnInsert.setStyle("-fx-background-color: #FF9EAA; -fx-text-fill: white;");
        btnUpdate.setStyle("-fx-background-color: #FF9EAA; -fx-text-fill: white;");
        btnDelete.setStyle("-fx-background-color: #FF9EAA; -fx-text-fill: white;");
        

        
        btnSearch.setOnAction(e -> primaryStage.setScene(searchScene));
        btnInsert.setOnAction(e -> primaryStage.setScene(insertScene));
        btnUpdate.setOnAction(e -> primaryStage.setScene(updateScene));
        btnDelete.setOnAction(e -> primaryStage.setScene(deleteScene));
        
        //Scene launchScene = null;
        //btnCancel.setOnAction(e -> primaryStage.setScene(launchScene));
        
        VBox layout = new VBox(20);
        layout.getChildren().addAll(label, btnSearch, btnInsert, btnUpdate, btnDelete);
        layout.setAlignment(Pos.CENTER);
        layout.setBackground(new Background(new BackgroundFill(Color.web("#FFD0D0"), CornerRadii.EMPTY, Insets.EMPTY)));

        layout.setPadding(new Insets(20));

        return new Scene(layout, 800, 600);
    }

    // Create Search Scene
    private Scene createSearchScene(Stage primaryStage) {
        Label lb = new Label("Search for current pets");
        lb.setFont(Font.font("Times New Roman", 24));
        Label lbName = new Label("Enter microchip number: ");
        TextField tfName = new TextField();
        Button btnSearch = new Button("SEARCH");
        Button btnBack = new Button("Back to Home page");

        btnSearch.setStyle("-fx-background-color: FF9EAA; -fx-text-fill: white;");

        btnSearch.setOnAction((ActionEvent event) -> {
            String microchip = tfName.getText();
            try{
         Registry registry = LocateRegistry.getRegistry("10.51.49.24", 1099);// since we are fetching a registry we need to store it in a registry 
        DatabaseInterface di= (DatabaseInterface) registry.lookup("databaseReg"); //here we cast the registry.lookup to the databaseinterface class since it returns remote which is of a different datatype to the dbinterface class
         List <String> result = di.selectOperation(microchip);
        String Result = String.join("\n\n", result);
       selectData(Result);
        } catch(RemoteException rem){
            System.out.println(rem.getMessage());
        } catch (NotBoundException ex) {
           System.out.println (ex.getMessage()) ;
        } 
        
            /*DBConnection dbc = new DBOperation();
           dbc.selectOperation( microchip);*/
        });
        
        btnBack.setStyle("-fx-background-color: FF9EAA; -fx-text-fill: white;");
        btnBack.setOnAction(e -> primaryStage.setScene(homeScene));

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20));
        gridPane.add(lbName, 0, 0);
        gridPane.add(tfName, 1, 0);
        gridPane.add(btnSearch, 1, 1);

        VBox layout = new VBox(20, lb, gridPane, btnBack);
        layout.setAlignment(Pos.CENTER);
         layout.setBackground(new Background(new BackgroundFill(Color.web("FFD0D0"), CornerRadii.EMPTY, Insets.EMPTY)));

        return new Scene(layout, 800, 600); 
    }

    // Create Insert Scene
    private Scene createInsertScene(Stage primaryStage) {
        Label lb = new Label("Enter details for a new pet");
        lb.setFont(Font.font("Times New Roman", 24));
        Label lbtt = new Label("Enter microchip number: ");
        Label lbta = new Label("Enter the cat's breed ");
        Label lbto = new Label("Enter the cat's eye color ");
        Label lbtz = new Label("Enter the cat's name ");
        Label lbtu = new Label("Is the cat nocturnal? ");
        Label lbte = new Label("What is the cat's weight ");
        Label lbtl = new Label("Enter the cat's gender ");
        Label lbtn = new Label("What is the cat's favorite toy ");
        Label lbtv = new Label("What is the cat's clolor ");
        Label lbti = new Label("What is the cat's age ");
        
        TextField tt = new TextField();
        TextField ta = new TextField();
        TextField to = new TextField();
        TextField tz= new TextField();
        TextField tu = new TextField();
        TextField te = new TextField();
        TextField tl = new TextField();
        TextField tn = new TextField();
        TextField tv = new TextField();
        TextField ti = new TextField();

        Button btnInsert = new Button("INSERT");
        Button btnBack = new Button("Back to Home page");

         btnInsert.setStyle("-fx-background-color: FF9EAA; -fx-text-fill: white;");

        btnInsert.setOnAction((ActionEvent event) -> {
            int  microchip = Integer.parseInt(tt.getText());
              String bre= ta.getText();
              String eye = to.getText();
              String nam= tz.getText();
              String noc= tu.getText();
              int wei= Integer.parseInt(te.getText());
              String gen= tl.getText();
              String toy=tn.getText();
              String col= tv.getText();
              int ag = Integer.parseInt(ti.getText());
try{
         Registry registry = LocateRegistry.getRegistry("10.51.49.24", 1099);// since we are fetching a registry we need to store it in a registry 
        DatabaseInterface di= (DatabaseInterface) registry.lookup("databaseReg"); //here we cast the registry.lookup to the databaseinterface class since it returns remote which is of a different datatype to the dbinterface class
         String Result = di.insertOperation(toy, noc, col, microchip, col, bre, ag, wei, nam, gen);
       insertData(Result);
        } catch(RemoteException rem){
            System.out.println(rem.getMessage());
        } catch (NotBoundException ex) {
           System.out.println (ex.getMessage()) ;
        } 
           /* DBConnection dbc = new DBOperation();
            dbc.insertOperation(toy,noc,eye,microchip,col,bre,ag,wei,nam,gen);*/
        });
         btnBack.setStyle("-fx-background-color: FF9EAA; -fx-text-fill: white;");

        btnBack.setOnAction(e -> primaryStage.setScene(homeScene));

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20));
        gridPane.add(lbtt, 0, 0);
        gridPane.add(tt, 1, 0);
        gridPane.add(lbta, 0, 1);
        gridPane.add(ta, 1, 1);
        gridPane.add(lbto, 0, 2);
        gridPane.add(to, 1, 2);
        gridPane.add(lbtz, 0, 3);
        gridPane.add(tz, 1, 3);
        gridPane.add(lbtu, 0, 4);
        gridPane.add(tu, 1, 4);
        gridPane.add(lbte, 0, 5);
        gridPane.add(te, 1, 5);
        gridPane.add(lbtl, 0, 6);
        gridPane.add(tl, 1, 6);
        gridPane.add(lbtn, 0, 7);
        gridPane.add(tn, 1, 7);
        gridPane.add(lbtv, 0, 8);
        gridPane.add(tv, 1, 8);
        gridPane.add(lbti, 0, 9);
        gridPane.add(ti, 1, 9);
        gridPane.add(btnInsert, 1, 10);

        VBox layout = new VBox(20, lb, gridPane, btnBack);
        layout.setAlignment(Pos.CENTER);
   layout.setBackground(new Background(new BackgroundFill(Color.web("FFD0D0"), CornerRadii.EMPTY, Insets.EMPTY)));
        return new Scene(layout, 800, 600);
    }

    // Create Update Scene
    private Scene createUpdateScene(Stage primaryStage) {
        Label lb = new Label("Update current pets");
        lb.setFont(Font.font("Times New Roman", 24));
        Label lbtt = new Label("Enter the microchip number ");
        TextField tt = new TextField();
        Label lbth = new Label("Enter the cat's favorite toy ");
        TextField th = new TextField();
        Button btnUpdate = new Button("UPDATE");
        Button btnBack = new Button("Back to Home");

        btnUpdate.setStyle("-fx-background-color: FF9EAA; -fx-text-fill: white;");

        btnUpdate.setOnAction((ActionEvent event) -> {
            int microchip = Integer.parseInt(tt.getText());
            String toy = th.getText();
            try{
         Registry registry = LocateRegistry.getRegistry("10.51.49.24", 1099);// since we are fetching a registry we need to store it in a registry 
        DatabaseInterface di= (DatabaseInterface) registry.lookup("databaseReg"); //here we cast the registry.lookup to the databaseinterface class since it returns remote which is of a different datatype to the dbinterface class
        
        String Result = di.updateOperation(microchip, toy);
       updateData(Result);
        } catch(RemoteException rem){
            System.out.println(rem.getMessage());
        } catch (NotBoundException ex) {
           System.out.println (ex.getMessage()) ;
        } 
           /* DBConnection dbc = new DBOperation();
            dbc.updateOperation(microchip,toy);  Ideally, update logic should use inputs*/
        });
        
        btnBack.setStyle("-fx-background-color: FF9EAA; -fx-text-fill: white;");
        btnBack.setOnAction(e -> primaryStage.setScene(homeScene));

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20));
        gridPane.add(lbtt, 0, 0);
        gridPane.add(tt, 1, 0);
          gridPane.add(lbth, 0, 1);
        gridPane.add(th, 1, 1);
        gridPane.add(btnUpdate, 2, 2);

        VBox layout = new VBox(20, lb, gridPane, btnBack);
        layout.setAlignment(Pos.CENTER);
   layout.setBackground(new Background(new BackgroundFill(Color.web("FFD0D0"), CornerRadii.EMPTY, Insets.EMPTY)));
        return new Scene(layout, 800, 600);
    }

    // Create Delete Scene
    private Scene createDeleteScene(Stage primaryStage) {
        Label lb = new Label("Delete a pet");
        lb.setFont(Font.font("Times New Roman", 24));
        Label lbtt = new Label("Enter microchip number: ");
        TextField tt = new TextField();
        Button btnDelete = new Button("DELETE");
        Button btnBack = new Button("Back to Home");

         btnDelete.setStyle("-fx-background-color:FF9EAA; -fx-text-fill: white;");

        btnDelete.setOnAction((ActionEvent event) -> {
            int microchip = Integer.parseInt(tt.getText());
            try{
         Registry registry = LocateRegistry.getRegistry("10.51.49.24", 1099);// since we are fetching a registry we need to store it in a registry 
        DatabaseInterface di= (DatabaseInterface) registry.lookup("databaseReg"); //here we cast the registry.lookup to the databaseinterface class since it returns remote which is of a different datatype to the dbinterface class
        
        String Result = di.deleteOperation(microchip);
       deleteData(Result);
        } catch(RemoteException rem){
            System.out.println(rem.getMessage());
        } catch (NotBoundException ex) {
           System.out.println (ex.getMessage()) ;
        } 
            /*DBConnection dbc = new DBOperation();
            dbc.deleteOperation(microchip);*/
        });
        
         btnBack.setStyle("-fx-background-color: FF9EAA; -fx-text-fill: white;");
        btnBack.setOnAction(e -> primaryStage.setScene(homeScene));

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20));
        gridPane.add(lbtt, 0, 0);
        gridPane.add(tt, 1, 0);
        gridPane.add(btnDelete, 1, 1);

        VBox layout = new VBox(20, lb, gridPane, btnBack);
        layout.setAlignment(Pos.CENTER);
   layout.setBackground(new Background(new BackgroundFill(Color.web("FFD0D0"), CornerRadii.EMPTY, Insets.EMPTY)));
        return new Scene(layout, 800, 600);
    }
    public void selectData(String data){
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("NOTIFICATION");
    alert.setHeaderText("Fetched Data from Server");
    //alert.setContentText(data);  // Set the data as the content of the dialog
 TextArea textArea = new TextArea(data);
    textArea.setEditable(false);  // Make sure the data can't be edited
    textArea.setWrapText(true);   // Wrap lines if necessary

    // Add the TextArea to the alert dialog
    alert.getDialogPane().setContent(textArea);
 
    // Display the alert dialog and wait for user to close it
    alert.showAndWait();
    }
    
     public void insertData(String dat){
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("NOTIFICATION");
    alert.setHeaderText(dat);
    //alert.setContentText(data);  // Set the data as the content of the dialog
alert.setWidth(300);
    // Display the alert dialog and wait for user to close it
    alert.showAndWait();
    }
    
      public void updateData(String daa){
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("NOTIFICATION");
    alert.setHeaderText(daa);
    //alert.setContentText(data);  // Set the data as the content of the dialog
alert.setWidth(300);
    // Display the alert dialog and wait for user to close it
    alert.showAndWait();
    }
       public void deleteData(String da){
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("NOTIFICATION");
    alert.setHeaderText(da);
    //alert.setContentText(data);  // Set the data as the content of the dialog

    // Display the alert dialog and wait for user to close it
    alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}