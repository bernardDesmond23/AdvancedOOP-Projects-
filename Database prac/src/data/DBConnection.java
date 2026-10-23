
package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;



public abstract class DBConnection {
   public  Connection con =getConnection();//con is a global variable
    //Stpe 1
   public  Connection getConnection(){
       Connection connection= null;
       //Step 1 Load driver
       try{
           Class.forName("com.mysql.cj.jdbc.Driver"); //pass te path of the driver as an argumentr
           System.out.println("Driver loaded successfully");
           
           connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/Animal", "root", "");//
           System.out.println("Connection established succesfully");
       } catch(ClassNotFoundException cnfe){//the exception classnotfound is stored in the variable cnfe in otherwords cnfe is the oject of classnotfound exception 
           System.out.println("could not load driver" + cnfe.getMessage());//incase the class isnt found the message will be printed
       }catch (SQLException sqle) {
           System.out.println("can`t connect to" + sqle.getMessage());
       }
      //Step 2 establish connection
           //DRIVER:dbVendor://Server:portNumber/DBName
       
       
       return connection;
   }
   
   
   public abstract List <String> selectOperation(String aniID);
    public abstract String insertOperation(String aniId,String species,String name,String habitant,String age, String consumerType, String horns);
     public abstract String updateOperation(String horns, String species, String aniID);
      public abstract String deleteOperation(int aniID);
   
}
