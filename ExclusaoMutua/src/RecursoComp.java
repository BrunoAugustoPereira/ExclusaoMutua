import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RecursoComp extends Remote {
  
  public void operation(int id, String key) throws RemoteException;

}
