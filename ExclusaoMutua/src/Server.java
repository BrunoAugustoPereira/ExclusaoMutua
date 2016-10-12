import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.RemoteServer;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;
        
public class Server implements RecursoComp {
        
    private boolean flag = false;
    public Server() {}

	
	//requisicao da regiao critica tendo como parametro o id client
    	public void requestCriticalZone(String id){
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
	public void displayQueue(){
	
	//System.out.println(fila_de_requisicoes);
	
	
	}
	public void displayStatus(){
	
		/*
		if(regiaoOcupada== false){
		
		System.out.println(regiao critica esta disponivel);
		
		}
		else if(regiaoOcupada== true){
		
		System.out.println(regiao critica esta em uso);
		
		}

		*/
	}
	
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
			displayStatus();//finaliza processo
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