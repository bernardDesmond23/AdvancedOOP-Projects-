
package GUI;

import data.DBConnection;
import data.DBOperations;
import java.rmi.RemoteException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;
import RMI.DatabaseInterface;
import RMI.DatabaseIntImpl;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class SimpleApp extends Application {
   
 Stage window;
  ContextMenu suggestionsMenu = new ContextMenu();
      List<String> previousIds = new ArrayList<>();
      List<String> uphornpreviousIds = new ArrayList<>();
      List<String> upspecpreviousIds = new ArrayList<>();
      List<String> upIDpreviousIds = new ArrayList<>();
      ContextMenu suggestionsdelMenu = new ContextMenu();
      ContextMenu uphornsuggestionsMenu = new ContextMenu();
      ContextMenu upspecsuggestionsMenu = new ContextMenu();
      ContextMenu upIDsuggestionsMenu = new ContextMenu();
      List<Integer> delpreviousIds = new ArrayList<>();
      TextField tx= new TextField();
      TextField tt= new TextField();
      TextField tf= new TextField();
        TextField ft= new TextField();
         TextField tff= new TextField();
            
 // Preferences instance
private static final Preferences prefs = Preferences.userNodeForPackage(SimpleApp.class);
    @Override
    public void start(Stage primaryStage)  {
        /*
        Below we have a global variable window of the type Stage which stores the main frame of the
        GUI. We equate it to the primaryStage to host the main FRAME and the other frames of the GUI
        
        */
        window= primaryStage;
        
         
         Label lb1 = new Label(" WELCOME TO ANIMAL GUI");
         lb1.setFont(new Font("Times New Roman",15));
         lb1.setTextFill(Color.BLACK);
        
        Button btn1  = new Button("OPEN");
      btn1.setStyle("-fx-background-color:#154c79; -fx-text-fill:white;");
      //for the IG screen 
       ImageView icon = new ImageView(new Image(getClass().getResource("/GUI/animal.jpeg").toExternalForm())); // Replace with your image path
        icon.setFitWidth(100);
        icon.setFitHeight(100);

        // Add a label for text under the icon
        Label splashText = new Label("Welcome to Animal GUI");
        splashText.setFont(new Font("Times New Roman", 18));
        splashText.setTextFill(Color.WHITE);

//These fields below will be displayed in  for the search tab 
         Label bll =new Label("SEARCH");
        bll.setTextFill(Color.RED);
        bll.setFont(new Font("Times New Roman",20));
       
       
        tt.setPromptText("ENTER THE ANIMAL ID");
       tt.setMaxWidth(400);
      // Load IDs from preferences when the application starts
loadPreviousIds();

        Button but =new Button("SEARCH");
        but.setStyle("-fx-background-color:blue; -fx-text-fill:white;");
         but.setPrefSize(100, 20);
         
         //The nodes below are for the update tab which are added to the vbox layout
        Label lb=new Label("UPDATE RHINO");
        lb.setFont(new Font("Times New Roman",20));
        lb.setTextFill(Color.RED);
        
        tf.setPromptText("ENTER THE NUMBER OF HORNS");
       tf.setMaxWidth(400);
           ft.setPromptText("ENTER THE SPECIES");
          ft.setMaxWidth(400);
            tff.setPromptText("ENTER THE ANIMAL ID");
            tff.setMaxWidth(400);
             loadhornPreviousIds();
            loadspeciesPreviousIds();
             loadupdateIDPreviousIds();
        Button btn = new Button("UPDATE");
        btn.setStyle("-fx-background-color:blue; -fx-text-fill:white;");
         btn.setPrefSize(100, 20);
         
         //The nodes below are for the insert tab which are added to the vbox layout
        Label bl= new Label("INSERT RHINO");
         bl.setFont(new Font("Times New Roman",20));
         bl.setTextFill(Color.RED);
        TextField fft= new TextField();
        fft.setPromptText("WRITE THE ANIMAL ID HERE ");
        fft.setMaxWidth(400);
         TextField tft = new TextField();
           tft.setPromptText("ENTER THE SPECIES HERE ");
           tft.setMaxWidth(400);
          TextField tfx= new TextField();
            tfx.setPromptText("ENTER THE NAME ");
            tfx.setMaxWidth(400);
           TextField txx= new TextField();
             txx.setPromptText("ENTER THE HABITANT ");
             txx.setMaxWidth(400);
           TextField ttx= new TextField();
             ttx.setPromptText("ENTER THE AGE");
             ttx.setMaxWidth(400);
            TextField ffx= new TextField();
              ffx.setPromptText("ENTER CONSUMER TYPE ");
              ffx.setMaxWidth(400);
             TextField xfx= new TextField();
               xfx.setPromptText("ENTER THE NO OF HORNS");
               xfx.setMaxWidth(400);
        Button bn = new Button("INSERT");
         bn.setStyle("-fx-background-color:blue; -fx-text-fill:white;");
         bn.setPrefSize(100, 20);
         //The nodes below are for the delete tab which are added to the vbox layout
         Label lb2 =new Label("DELETE RHINO");
         lb2.setFont(new Font("Times New Roman",20));
         lb2.setTextFill(Color.RED);
         
         tx.setPromptText("ENTER THE ANIMAL ID");
         tx.setMaxWidth(400);
          loaddelPreviousIds();
         Button bv =new Button("DELETE");
         bv.setStyle("-fx-background-color:blue; -fx-text-fill:white;");
         bv.setPrefSize(100, 20);
         
         /* The section below displays the actions that will happen when the 
         search,update, insert and delete buttons are clicked. this is done using innner classes
         and generational methods. where EvenetHandler<ActionEvent> is an instance of a class
         */
         
         //Action activated upon clicking Delete button 
          bv.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               String input = tx.getText(); // Get the text from the TextField
    
    // Check if the input is not empty
    if (!input.isEmpty()) {
        try {
            int ani = Integer.parseInt(input); // Parse the input to an integer

            // Check if the parsed integer is not zero
            if (ani != 0) {
                // Add the ID to the previous IDs list if not already present
                if (!delpreviousIds.contains(ani)) {
                    delpreviousIds.add(ani);
                    savedelIdToPreferences(ani); // Save the ID to the preferences
                }

                try {
                    // RMI logic to delete the record from the database
                    Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1099);
                    DatabaseInterface di = (DatabaseInterface) registry.lookup("databaseReg");
                    String delresult=di.deleteOperation(ani);
                    deleteData(delresult);

                } catch (RemoteException rex) {
                    rex.getMessage();
                } catch (NotBoundException ex) {
                    ex.getMessage();
                }

                // Clear the text field after the delete operation
                tx.clear();
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid integer ID.");
        }
    } else {
        System.out.println("Please enter an ID.");
    }
            }
        });
           tx.setOnMouseClicked(event -> showdeleteSuggestions());

        //Action activated upon clicking update button   
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
              
              String hn= tf.getText();
              String sp= ft.getText();
              String aniId= tff.getText();
              
              if ( !sp.isEmpty()&& !aniId.isEmpty() && !hn.isEmpty()) {
            // Add the ID to the previous IDs list if not already present
            if (!uphornpreviousIds.contains(hn) && !upspecpreviousIds.contains(sp) && !upIDpreviousIds.contains(aniId)) {
                uphornpreviousIds.add(hn);
                upspecpreviousIds.add(sp);
                upIDpreviousIds.add(sp);
                saveupdatehornToPreferences(hn);
                 saveupdatespecToPreferences(sp);
                 saveupdateIdToPreferences(aniId); // Save the ID to the preferences
                   // Save the ID to the preferences
                   // Save the ID to the preferences
            }
              try{
         Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1099);// since we are fetching a registry we need to store it in a registry 
        DatabaseInterface di= (DatabaseInterface) registry.lookup("databaseReg"); //here we cast the registry.lookup to the databaseinterface class since it returns remote which is of a different datatype to the dbinterface class
       String rresult = di.updateOperation(hn, sp, aniId);
       
       updateData(rresult);
        } catch(RemoteException rem){
            System.out.println(rem.getMessage());
        } catch (NotBoundException ex) {
           System.out.println (ex.getMessage()) ;
        }
               /* DBConnection dbc= new DBOperations();
                dbc.updateOperation(hn, sp, aniId);*/
               updateClear(tf,ft,tff);
    
            }
            }
        });/*the new EventHandler<ActionEvent>() is a shorter version of creating an instance and 
        it shows creation of inner classes 
        */
        tf.setOnMouseClicked(event -> showuphornSuggestions());
        ft.setOnMouseClicked(event -> showupspecSuggestions());
        tff.setOnMouseClicked(event -> showupIdSuggestions());
        //Action activated upon clicking insert button 
        bn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               // System.out.println("I am working");
              //int id= Integer.parseInt(tf.getText().toString());
              
              String anId= fft.getText();
              String spe= tft.getText();
              String nam= tfx.getText();
              String hab= txx.getText();
              String ag= ttx.getText();
              String ct= ffx.getText();
              String hrn=xfx.getText();
              
              try{
         Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1099);// since we are fetching a registry we need to store it in a registry 
        DatabaseInterface di= (DatabaseInterface) registry.lookup("databaseReg"); //here we cast the registry.lookup to the databaseinterface class since it returns remote which is of a different datatype to the dbinterface class
       String rresult = di.insertOperation(anId, spe, nam, hab, ag, ct, hrn);
       
       insertData(rresult);
        } catch(RemoteException rem){
            System.out.println(rem.getMessage());
        } catch (NotBoundException ex) {
           System.out.println (ex.getMessage()) ;
        }
                /*DBConnection dbc= new DBOperations();
               
                dbc.insertOperation(anId, spe, nam, hab, age, ct, hrn);*/
                insertClear(fft,tft,tfx,txx,ttx,ffx,xfx);
   
            }
        });
       
        //Action activated upon clicking search button 
         but.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               // System.out.println("I am working");
              // int id= Integer.getInteger(tf.getText().toString());// 
              //tt.setOnKeyTyped(event -> suggestionsMenu.hide());
              String anId= tt.getText();
             
if (anId != null && !anId.isEmpty()) {
            // Add the ID to the previous IDs list if not already present
            if (!previousIds.contains(anId)) {
                previousIds.add(anId);
                 saveIdToPreferences(anId); // Save the ID to the preferences
            }
               try{
         Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1099);// since we are fetching a registry we need to store it in a registry 
        DatabaseInterface di= (DatabaseInterface) registry.lookup("databaseReg"); //here we cast the registry.lookup to the databaseinterface class since it returns remote which is of a different datatype to the dbinterface class
       List <String> result = di.selectOperation(anId);
        String Result = String.join("\n\n", result);
       selectData(Result);
        } catch(RemoteException rem){
            System.out.println(rem.getMessage());
        } catch (NotBoundException ex) {
           System.out.println (ex.getMessage()) ;
        }
            
               /* DBConnection dbc= new DBOperations();
               
                dbc.selectOperation(anId);*/
                
                tt.clear();
                
     
            }
            }
        });
        
        tt.setOnMouseClicked(event -> showSuggestions());

        //CREATE SCENE GRAPH
        /*different layouts in form of vbox are created for each tab
        vroot for insert tab
        rot for update tab 
        root for the search tab 
        */
        VBox vroot = new VBox(5);
 VBox root= new VBox(10);
 VBox rot =new VBox(10);
 VBox rt= new VBox(10);
 
 /*The layout BorderPane is created so that it acts a main layout where all the vboxs will be 
 hosted
 */
 BorderPane pane = new BorderPane();
        HBox hbox= addHbox(vroot,root,rot,rt,pane);/* This method is used to display the navigation bar where
        the user will use to go to different tabs which are insert update search
        */
        pane.setTop(hbox); //Then the navigation  bar is set at the top position of the BorderPane
       // The statement below add the respective nodes to their layouts 
      rot.getChildren().addAll(lb,tf,ft,tff,btn);
      vroot.getChildren().addAll(bl,fft,tft,tfx,txx,ttx,ffx,xfx,bn);
      root.getChildren().addAll(bll,tt,but);
      rt.getChildren().addAll(lb2,tx,bv);
      // the setstyle() method is used to design each vbox
       rot.setStyle("-fx-background-color:D3D3D3;");
        root.setStyle("-fx-background-color:D3D3D3;");
        vroot.setStyle("-fx-background-color:D3D3D3;");
        rt.setStyle("-fx-background-color:D3D3D3;");
        rot.setAlignment(Pos.CENTER);
        
        vroot.setAlignment(Pos.CENTER);
        
        root.setAlignment(Pos.CENTER);
        
        rt.setAlignment(Pos.CENTER);
        
        /*the setvisible() method is used to make sure that all vboxs are invisble until their respective
        tab is clicked on
        */
        vroot.setVisible(false);
        root.setVisible(false);
        rot.setVisible(false);
        rt.setVisible(false);
       /*
        for the main window a layout vbox vb is created to hold its contents 
        */ 
       
        VBox vb= new VBox(10);
        vb.setAlignment(Pos.CENTER);
        vb.setStyle("-fx-background-color:#148dbe;");
        vb.getChildren().addAll(icon,splashText);
      //    CREATE A SCENE
      /*
      for the main window a different scene is set for it which is scene1 to only host
      the heading of the GUI and a button to urge the user to go to the next tab which is clicked to
      go to the tabs
      scene then hosts the mainlayout which has the vboxs for the tabs 
      
      */
        Scene scene1= new Scene(vb,300,100);
       window.setScene(scene1);
        Scene scene = new Scene(pane,700,600);
        
        
         PauseTransition pause = new PauseTransition(Duration.seconds(8));
        pause.setOnFinished(event -> window.setScene(scene));
        pause.play();
        
          //btn1.setOnAction(e -> window.setScene(scene));
        
        
        window.show();
       
    }
    //This method is responsible for the navigation bar which the user uses to changes the different tabs
    public HBox addHbox(VBox vroot,VBox root,VBox rot,VBox rt, BorderPane pane){
        HBox hbox= new HBox();
        hbox.setPadding(new Insets(15,12,15,12));
        hbox.setSpacing(20);
        hbox.setStyle("-fx-background-color:#336699");
        
        Button bupdate= new Button("INSERT TAB");
        /* when the insert tab button is pressed it makes the other vboxs disappear and only displays the
        the vbox vroot having the fields for inserting data
        */
        bupdate.setOnAction(e ->{
            vroot.setVisible(true);
            root.setVisible(false);
            rot.setVisible(false);
              rt.setVisible(false);
            pane.setCenter(vroot);
                });
        bupdate.setPrefSize(100, 20);
        Button bsearch= new Button("SEARCH TAB");
        /* when the search tab button is pressed it makes the other vboxs disappear and only displays the
        the vbox root having the fields for searching data
        */
        bsearch.setOnAction(e -> {
            root.setVisible(true);
            vroot.setVisible(false);
            rot.setVisible(false);
              rt.setVisible(false);
            pane.setCenter(root);
                });
        bsearch.setPrefSize(100, 20);
        Button bt= new Button("UPDATE TAB");
         bt.setPrefSize(100, 20);
         /* when the update tab button is pressed it makes the other vboxs disappear and only displays the
        the vbox rot having the fields for updating data
        */
        bt.setOnAction(e -> {
            root.setVisible(false);
            vroot.setVisible(false);
            rot.setVisible(true);
            rt.setVisible(false);
            pane.setCenter(rot);
                });
        Button btt= new Button("DELETE TAB");
        btt.setPrefSize(100, 20);
        /* when the delete tab button is pressed it makes the other vboxs disappear and only displays the
        the vbox rt having the fields for deleting  data
        */
        btt.setOnAction(e -> {
            root.setVisible(false);
            vroot.setVisible(false);
            rot.setVisible(false);
            rt.setVisible(true);
            pane.setCenter(rt);
                });
        
        hbox.getChildren().addAll(bupdate,bsearch,bt,btt);
        return hbox;
    }
    //This method clears the text written in the fields after inserting data in the insert tab
    public void insertClear(TextField fft, TextField tft, TextField tfx, TextField txx, TextField ttx, TextField ffx, TextField xfx){
         fft.clear();
               tft.clear();
               tfx.clear();
               txx.clear();
              ttx.clear();
               ffx.clear();
              xfx.clear();
    }
      //This method clears the text written in the fields after inserting data in the update tab
    public void updateClear(TextField rr, TextField tt, TextField td){
        rr.clear();
        tt.clear();
        td.clear();
    }
    private void showSuggestions() {
    suggestionsMenu.getItems().clear(); // Clear old suggestions

    // Add each previous ID as a menu item to the suggestions menu
    for (String id : previousIds) {
        Label searchIcon = new Label("c"); // Unicode for the magnifying glass emoji
        searchIcon.setStyle("-fx-font-size: 14px; -fx-padding: 0 5px 0 0;");

        // Create a Text node for the suggestion text
        Text suggestionText = new Text(id);

        // Create an HBox to hold the icon and the text
        HBox suggestionBox = new HBox(searchIcon, suggestionText);
        suggestionBox.setSpacing(5);
        suggestionBox.setPadding(new Insets(5));

        // Wrap the HBox in a CustomMenuItem
        CustomMenuItem item = new CustomMenuItem(suggestionBox, true);
        
        item.setOnAction(e -> tt.setText(id)); // Set the selected ID to the TextField
        suggestionsMenu.getItems().add(item);
    }

    // Show the suggestions menu below the TextField if there are any previous IDs
    if (!suggestionsMenu.getItems().isEmpty()) {
        suggestionsMenu.show(tt, Side.BOTTOM, 0, 0);
    }
    }
    private void saveIdToPreferences(String id) {
    int size = previousIds.size();
    prefs.put("id_" + size, id); // Store each ID with a unique key like "id_0", "id_1", etc.
    prefs.putInt("id_count", size + 1); // Store the total count of IDs
}

// Method to load previous IDs from the preferences
private void loadPreviousIds() {
    int size = prefs.getInt("id_count", 0); // Retrieve the count of IDs
    for (int i = 0; i < size; i++) {
        String id = prefs.get("id_" + i, null);
        if (id != null) {
            previousIds.add(id);
        }
    }
    
}


private void savedelIdToPreferences(int id) {
    int size = delpreviousIds.size();
    String del = Integer.toString(id);
    prefs.put("delete_id_" + size, del); // Store each ID with a unique key like "id_0", "id_1", etc.
    prefs.putInt("delete_id_count", size + 1); // Store the total count of IDs
}
private void loaddelPreviousIds() {
    
    int size = prefs.getInt("delete_id_count", 0); // Retrieve the count of IDs
    for (int i = 0; i < size; i++) {
          
        String id = prefs.get("delete_id_" + i, null);
        
        if (id != null) {
             int del = Integer.parseInt(id);
            delpreviousIds.add(del);
        }
    }
    
}
private void showdeleteSuggestions() {
    suggestionsdelMenu.getItems().clear(); // Clear old suggestions

    // Add each previous ID as a menu item to the suggestions menu
    for (int id : delpreviousIds) {
        Label searchIcon = new Label("\uD83D\uDD0D"); // Unicode for the magnifying glass emoji
        searchIcon.setStyle("-fx-font-size: 14px; -fx-padding: 0 5px 0 0;");

        // Create a Text node for the suggestion text
        String text =  Integer.toString(id); 
        Text suggestionText = new Text(text);

        // Create an HBox to hold the icon and the text
        HBox suggestionBox = new HBox(searchIcon, suggestionText);
        suggestionBox.setSpacing(5);
        suggestionBox.setPadding(new Insets(5));

        // Wrap the HBox in a CustomMenuItem
        CustomMenuItem item = new CustomMenuItem(suggestionBox, true);
        
        item.setOnAction(e -> tx.setText(text)); // Set the selected ID to the TextField
        suggestionsdelMenu.getItems().add(item);
    }

    // Show the suggestions menu below the TextField if there are any previous IDs
    if (!suggestionsdelMenu.getItems().isEmpty()) {
        suggestionsdelMenu.show(tx, Side.BOTTOM, 0, 0);
    }
    }

private void saveupdatehornToPreferences(String ho) {
    int size = uphornpreviousIds.size();
    
    prefs.put("horn_id_" + size, ho); // Store each ID with a unique key like "id_0", "id_1", etc.
    prefs.putInt("horn_id_count", size + 1); // Store the total count of IDs
}

private void saveupdateIdToPreferences(String up) {
    int size = upIDpreviousIds.size();
    
    prefs.put("updateID_id_" + size, up); // Store each ID with a unique key like "id_0", "id_1", etc.
    prefs.putInt("updateID_id_count", size + 1); // Store the total count of IDs
}

private void saveupdatespecToPreferences(String spe) {
    int size = upspecpreviousIds.size();
  
    prefs.put("species_id_" + size, spe); // Store each ID with a unique key like "id_0", "id_1", etc.
    prefs.putInt("species_id_count", size + 1); // Store the total count of IDs
}

// Method to load previous IDs from the preferences
private void loadhornPreviousIds() {
    int size = prefs.getInt("horn_id_count", 0); // Retrieve the count of IDs
    for (int i = 0; i < size; i++) {
        String horn = prefs.get("horn_id_" + i, null);
        if (horn != null) {
            uphornpreviousIds.add(horn);
        }
    }
    
}

// Method to load previous IDs from the preferences
private void loadspeciesPreviousIds() {
    int size = prefs.getInt("species_id_count", 0); // Retrieve the count of IDs
    for (int i = 0; i < size; i++) {
        String spec = prefs.get("species_id_" + i, null);
        if (spec != null) {
            upspecpreviousIds.add(spec);
        }
    }
    
}

// Method to load previous IDs from the preferences
private void loadupdateIDPreviousIds() {
    int size = prefs.getInt("updateID_id_count", 0); // Retrieve the count of IDs
    for (int i = 0; i < size; i++) {
        String id = prefs.get("updateID_id_" + i, null);
        if (id != null) {
            upIDpreviousIds.add(id);
        }
    }
    
}

private void showupIdSuggestions() {
    upIDsuggestionsMenu.getItems().clear(); // Clear old suggestions

    // Add each previous ID as a menu item to the suggestions menu
    for (String upId : upIDpreviousIds) {
        Label searchIcon = new Label("\uD83D\uDD0D"); // Unicode for the magnifying glass emoji
        searchIcon.setStyle("-fx-font-size: 14px; -fx-padding: 0 5px 0 0;");

        // Create a Text node for the suggestion text
        Text suggestionText = new Text(upId);

        // Create an HBox to hold the icon and the text
        HBox suggestionBox = new HBox(searchIcon, suggestionText);
        suggestionBox.setSpacing(5);
        suggestionBox.setPadding(new Insets(5));

        // Wrap the HBox in a CustomMenuItem
        CustomMenuItem item = new CustomMenuItem(suggestionBox, true);
        
        item.setOnAction(e -> tt.setText(upId)); // Set the selected ID to the TextField
        upIDsuggestionsMenu.getItems().add(item);
    }

    // Show the suggestions menu below the TextField if there are any previous IDs
    if (!upIDsuggestionsMenu.getItems().isEmpty()) {
        upIDsuggestionsMenu.show(tff, Side.BOTTOM, 0, 0);
    }
    }

private void showupspecSuggestions() {
    upspecsuggestionsMenu.getItems().clear(); // Clear old suggestions

    // Add each previous ID as a menu item to the suggestions menu
    for (String spc : upspecpreviousIds) {
        Label searchIcon = new Label("\uD83D\uDD0D"); // Unicode for the magnifying glass emoji
        searchIcon.setStyle("-fx-font-size: 14px; -fx-padding: 0 5px 0 0;");

        // Create a Text node for the suggestion text
        Text suggestionText = new Text(spc);

        // Create an HBox to hold the icon and the text
        HBox suggestionBox = new HBox(searchIcon, suggestionText);
        suggestionBox.setSpacing(5);
        suggestionBox.setPadding(new Insets(5));

        // Wrap the HBox in a CustomMenuItem
        CustomMenuItem item = new CustomMenuItem(suggestionBox, true);
        
        item.setOnAction(e -> tt.setText(spc)); // Set the selected ID to the TextField
        upspecsuggestionsMenu.getItems().add(item);
    }

    // Show the suggestions menu below the TextField if there are any previous IDs
    if (!upspecsuggestionsMenu.getItems().isEmpty()) {
        upspecsuggestionsMenu.show(ft, Side.RIGHT, 0, 0);
    }
    }

private void showuphornSuggestions() {
    uphornsuggestionsMenu.getItems().clear(); // Clear old suggestions

    // Add each previous ID as a menu item to the suggestions menu
    for (String hr : uphornpreviousIds) {
        Label searchIcon = new Label("\uD83D\uDD0D"); // Unicode for the magnifying glass emoji
        searchIcon.setStyle("-fx-font-size: 14px; -fx-padding: 0 5px 0 0;");

        // Create a Text node for the suggestion text
        Text suggestionText = new Text(hr);

        // Create an HBox to hold the icon and the text
        HBox suggestionBox = new HBox(searchIcon, suggestionText);
        suggestionBox.setSpacing(5);
        suggestionBox.setPadding(new Insets(5));

        // Wrap the HBox in a CustomMenuItem
        CustomMenuItem item = new CustomMenuItem(suggestionBox, true);
        
        item.setOnAction(e -> tt.setText(hr)); // Set the selected ID to the TextField
        uphornsuggestionsMenu.getItems().add(item);
    }

    // Show the suggestions menu below the TextField if there are any previous IDs
    if (!uphornsuggestionsMenu.getItems().isEmpty()) {
        uphornsuggestionsMenu.show(tf, Side.TOP, 0, 0);
    }
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
/* String hn= tf.getText();
              String sp= ft.getText();
              String aniId= tff.getText();*/
    public static void main(String[] args){
        launch(args);
    }
    }
    

