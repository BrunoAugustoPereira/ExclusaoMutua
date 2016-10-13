import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RecursoComp extends Remote {
	public String operation(String key, String ID) throws RemoteException;
}
