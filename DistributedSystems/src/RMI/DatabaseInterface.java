
package RMI;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;


public interface DatabaseInterface extends Remote{
    public String insertOperation(String favoriteToy,String nocturnal,String eyecolor,int microchipNo,String colour,String breed,int age,int weight,String name,String gender) throws RemoteException;
    public List<String> selectOperation(String microchip) throws RemoteException;
    public String updateOperation(int microchipNo, String newfavoriteToy) throws RemoteException;
    public String deleteOperation(int microchipNo) throws RemoteException;
}