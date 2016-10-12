import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RecursoComp extends Remote {
  
  public void operation(String key) throws RemoteException;

}
