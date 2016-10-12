import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RecursoComp extends Remote {
  
  public void requestCriticalZone(int id) throws RemoteException;
  public void displayQueue() throws RemoteException;
  public void displayStatus() throws RemoteException;

}
