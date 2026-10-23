
package PETS;

import RMI.DatabaseInterface;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class Test {
    
    public static void main(String [] args){
        try{
         Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1099);// since we are fetching a registry we need to store it in a registry 
        DatabaseInterface di= (DatabaseInterface) registry.lookup("databaseReg"); //here we cast the registry.lookup to the databaseinterface class since it returns remote which is of a different datatype to the dbinterface class
        di.selectOperation("1");
        } catch(RemoteException rem){
            System.out.println(rem.getMessage());
        } catch (NotBoundException ex) {
           System.out.println (ex.getMessage()) ;
        } 
        
    }
    


}
