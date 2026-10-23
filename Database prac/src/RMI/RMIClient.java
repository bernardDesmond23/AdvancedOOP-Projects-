
package RMI;


import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class RMIClient {
    public static void main(String [] args){
        try{
         Registry registry = LocateRegistry.getRegistry("localhost", 1099);// since we are fetching a registry we need to store it in a registry 
        DatabaseInterface di= (DatabaseInterface) registry.lookup("databaseReg"); //here we cast the registry.lookup to the databaseinterface class since it returns remote which is of a different datatype to the dbinterface class
      di.selectOperation("1");
        } catch(RemoteException rem){
            rem.getMessage();
        } catch (NotBoundException ex) {
            ex.getMessage();
        } 
        
    }
    
}
