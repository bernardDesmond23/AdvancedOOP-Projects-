
package RMI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import data.DBOperations;
import java.util.List;


public class DatabaseIntImpl extends UnicastRemoteObject implements DatabaseInterface {
    
    DBOperations dbo=null;
    public DatabaseIntImpl() throws RemoteException{
        dbo= new DBOperations();
    }

    @Override
    public String insertOperation(String aniId, String species, String name, String habitant, String age, String consumerType, String horns) throws RemoteException {
        return dbo.insertOperation(aniId, species, name, habitant, age, consumerType, horns);
    }

    @Override
    public List<String>  selectOperation(String aniID) throws RemoteException {
        return dbo.selectOperation(aniID);
    }

    @Override
    public String updateOperation(String horns, String species, String aniID) throws RemoteException {
        return dbo.updateOperation(horns, species, aniID);
    }

    @Override
    public String deleteOperation(int aniID) throws RemoteException {
        return dbo.deleteOperation(aniID);
    }
    
}
