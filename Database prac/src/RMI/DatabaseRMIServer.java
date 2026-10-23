
package RMI;
import java.nio.channels.AlreadyBoundException;
import java.rmi.AccessException;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;



public class DatabaseRMIServer  {
    public static void main(String[] args){
        try{
          DatabaseIntImpl dii= new DatabaseIntImpl();
        Registry registry= LocateRegistry.createRegistry(1099);
        registry.bind("databaseReg", (Remote) dii);  
        System.out.println("RMI Bound successfully");
        } catch(RemoteException ex){
            ex.getMessage();
        } catch (java.rmi.AlreadyBoundException ex) {
           ex.getMessage();
        } 
        
    }
}
