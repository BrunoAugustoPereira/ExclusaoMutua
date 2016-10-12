import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.RemoteServer;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;

import java.util.LinkedList;
        
public class Server implements RecursoComp {
        
    private boolean regiaoOcupada = false;
    protected String ocupante;
    protected LinkedList<String> fila_de_requisicoes= new LinkedList<String>();
    public Server() {}

	
	//requisicao da regiao critica tendo como parametro o id client
    	public void requestCriticalZone(String id){
		if(regiaoOcupada == false) {
			ocupante = fila_de_requisicoes.getFirst();
			fila_de_requisicoes.remove(); //deleta o primeiro da fila
		}
		else if(regiaoOcupada == true) {
			fila_de_requisicoes.add(id);
		}
	}
	
	//mostra a fila de processos que estao na espera pela regiao critica
	public void displayQueue(){
		for(Object o : fila_de_requisicoes){
    		System.out.println(o);
		}	
	}
	public void displayStatus(){
	
		if(regiaoOcupada == false)
			System.out.println("Regiao critica esta disponivel ! ");
		else if (regiaoOcupada == true)
			System.out.println("Regiao critica NAO esta disponivel ! ");
		else
			System.out.println("Status desconhecido");
	}

	public void quit() {}
	
	public void operation(String key) throws RemoteException {
		
		if (key.equals("1")) {
			try {
	       		String clientHost = RemoteServer.getClientHost();
	       		requestCriticalZone(clientHost); //ver como pegar id
	       		System.out.println("clientHost : "+clientHost);
	    	} catch (ServerNotActiveException e) {
	    		System.err.println("Server exception: " + e.toString());
            	e.printStackTrace();
	    	}
		}
		if (key.equals("2")) 
			displayQueue();
		if (key.equals("3")) 
			displayStatus();
		if (key.equals("x")) 
			quit(); //finaliza processo
	}
	
	
        
    public static void main(String args[]) {
        
        try {
            Server obj = new Server();
            RecursoComp stub = (RecursoComp) UnicastRemoteObject.exportObject(obj, 0);
            
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("RecursoComp", stub);

            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}