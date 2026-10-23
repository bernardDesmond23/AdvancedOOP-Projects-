
package RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface DatabaseInterface extends Remote{
    public String insertOperation(String aniId, String species, String name, String habitant, String age,String consumerType, String horns) throws RemoteException;
     public List<String> selectOperation(String aniID) throws RemoteException;
      public String updateOperation( String horns, String species, String aniID) throws RemoteException;
       public String deleteOperation(int aniID) throws RemoteException;
    
}
