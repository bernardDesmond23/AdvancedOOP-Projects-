
package data;
 import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBOperations extends DBConnection {


    @Override
    public List <String>  selectOperation(String aniID) {
         List <String> result = new ArrayList<>();
        //step 3 create statemnet 
        try{
           
           String query="SELECT * FROM rhino WHERE aniID=?";
PreparedStatement pst =null;
pst= con.prepareStatement(query); 
pst.setString(1, aniID);
        //step 4 execute statement
       
ResultSet rs= null;/* An object of a resultSet is created because the select command returns data in the table 
which can be expressed as a resultset

*/
rs= pst.executeQuery();/* we use the method executeQuery() because it returns a resultset

*/ 
//Step 5 process statement
 while(rs.next()){
     String data = "Animal ID: " + rs.getInt(1) + "\n" + 
             "Species: "+rs.getString(2)+ "\n" +
             "Name: "+rs.getString(3)+ "\n" + 
             "Habitant: "+rs.getString(4)+ "\n" + 
             "Age: "+rs.getString(5)+ "\n" +
             "ConsumerType: "+ rs.getString(6)+ "\n" + 
             "Horns: "+rs.getString(7);
     result.add(data);
 }/* A loop is used to iterate and produce whatever is in the columns of the table. the next() method is used to refer to
 the next set of data in the table
 */
   //step 6 close connection
   
   
        } catch(SQLException sqle){
            System.out.println("Error has occurred" + sqle.getMessage());
        }
return result;
    }

    @Override
    public String insertOperation(String aniId, String species, String name, String habitant, String age,String consumerType, String horns ) {
        String data = null;
        try{
            //Step 3 Create statement
            String query="INSERT INTO rhino(aniID, species, name, habitant, age, consumerType, horns) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement pst=con.prepareStatement(query);/*
            we use the global con to first establish a connection to the localhost so to connect to the database
            Thereafter the method prepareStatement()is used with an argument of the variable query
            This method returns a preparedStatement hence being an instance of the object pst of the type PreapredStatement
            */
            pst.setString(1, aniId);  
            pst.setString(2, species);
            pst.setString(3, name);
            pst.setString(4, habitant);
            pst.setString(5, age);
            pst.setString(6, consumerType);
            pst.setString(7, horns);
            /*
            The numbers from 1 to 7 represent the number of question marks in the variable of query. 
            The question marks represent the use of preparedStaement interface
            */
            
            
            //step 4 Execute statement
            pst.executeUpdate();/* we use this method executeUpadte() because no resultSet will be returned when the preparedStatement
            is executed ie when the sql command is executed since it will just enter data in the table
            */
            data = "Insertion successfull";
            //step 5 Process ResultSet
            //Step 6 Close connection
           
        } catch(SQLException sqle){
            System.out.println("Inserting of dat had failed" + sqle.getMessage());
        }
        return data;
    }

    @Override
    public String  updateOperation( String horns, String species, String aniID) {
        String update = null;
try{       
//Step 3 Create statement
        String query="UPDATE rhino SET  horns=?, species=? WHERE aniID=?";
       PreparedStatement pst= con.prepareStatement(query);
       pst.setString(1,horns);
        pst.setString(2,species);
         pst.setString(3,aniID);
         //Step 4 execute statement
       pst.executeUpdate();/* we use this method because the SQL command represeented by the query variable is just to change 
       the data in the table it doesnt return any form of data or resultset
       */
         //Step 5 Process Statement
         // step 6 close connection
         
       
       update="UPDATED SUCCESSFULLY";
       
       
    } catch(SQLException sqle){
        System.out.println("Update opeartion failed" + sqle.getMessage());
    }
return update;
    }

    @Override
    public String deleteOperation(int aniID) {
        String delete= null;
        try{
        //Step 3 Create statement
 String query="DELETE FROM rhino WHERE aniID=?";
 PreparedStatement pst= con.prepareStatement(query);
 pst.setInt(1, aniID);
 //sTEP 4 Execute Staement 
 pst.executeUpdate();
 delete="DELETED SUCCESSFULLY";
 //Step 5 Process ResultSet
 //Step6 close connection
 
    } catch(SQLException sqle){
        System.out.println("Delete operstion has failed" + sqle.getMessage());
    }
        return delete;
    }
    
}
