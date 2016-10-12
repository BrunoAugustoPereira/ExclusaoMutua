import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
        
public class Server implements Calculator {
        
    public Server() {}
	
	//requisicao da regiao critica tendo como parametro o id client
    	public void requestCriticalZone(int id) throws RemoteException {
		//checar disponibilidade do bollean
		/*
		if(regiaoOcupada== false){
		
		&&concede o recurso
		
		}
		else if(regiaoOcupada== true){
		
		&&coloca na fila de requisicao
		
		}

		*/
	}
	
	//mostra a fila de processos que estao na espera pela regiao critica
	public void displayQueue() throws RemoteException{
	
	//System.out.println(fila_de_requisicoes);
	
	
	}
	public void displayStatus() throws RemoteException{
	
		/*
		if(regiaoOcupada== false){
		
		System.out.println(regiao critica esta disponivel);
		
		}
		else if(regiaoOcupada== true){
		
		System.out.println(regiao critica esta em uso);
		
		}

		*/
	
	
	
	}
	
	public int sub(int a, int b) throws RemoteException {
		return a-b;
	}
	public int mul(int a, int b) throws RemoteException {
		return a*b;
	}
	public int div(int a, int b) throws RemoteException {
		if(b != 0)
			return a/b;
		else
			return 0;
	}
	public int op(int a, int b, String operator) throws RemoteException {
		if (operator.equals("+")) 
			return add(a, b);
		if (operator.equals("-")) 
			return sub(a, b);
		if (operator.equals("*")) 
			return mul(a, b);
		if (operator.equals("/")) 
			return div(a, b);
		return 0;
		
	}
        
    public static void main(String args[]) {
        
        try {
            Server obj = new Server();
            Calculator stub = (Calculator) UnicastRemoteObject.exportObject(obj, 0);
            
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("Calculator", stub);

            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
