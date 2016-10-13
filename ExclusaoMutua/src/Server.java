import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.RemoteServer;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;

import java.util.LinkedList;
        
public class Server implements RecursoComp {
        
    protected boolean regiaoOcupada = false;
    protected String ocupante;
    protected LinkedList<String> fila_de_requisicoes= new LinkedList<String>();

    public Server() {}

    	public void requestCriticalZone(String id){
			System.out.println("Acesso a Regiao critica solicitado pelo processo: "+ id);
			//if (id.equals(ocupante) || fila_de_requisicoes.contains(id))
			//	System.out.println("Acesso a Regiao critica negado ao processo: "+ id);
			if(regiaoOcupada == false) {
				if (fila_de_requisicoes.size()>0){
					fila_de_requisicoes.add(id);
					ocupante = fila_de_requisicoes.getFirst();
					fila_de_requisicoes.remove(); //deleta o primeiro da fila
					regiaoOcupada = true;
					System.out.println("Acesso a Regiao critica negado ao processo: "+ id+" adicionado a fila de requisicoes");
					System.out.println("Processo na Regiao critica: "+ ocupante);

				}
				else if (fila_de_requisicoes.size()==0){
					ocupante = id;
					regiaoOcupada = true;
					System.out.println("Acesso a Regiao critica concedido ao processo: "+ id);
				}
			}
			else if(regiaoOcupada == true) {
				fila_de_requisicoes.add(id);
				System.out.println("Acesso a Regiao critica negado ao processo: "+ id+" adicionado a fila de requisicoes");
			}
	}
	
	public void displayQueue(){
		for(Object o : fila_de_requisicoes){
    		System.out.println(o);
		}	
	}	
	public String displayStatus(String id) {
		String response = "";
		if(regiaoOcupada == false)
			response = "Liberado \n";
		else if(ocupante.equals(id))
			response = "Obtido \n";
		else if(fila_de_requisicoes.contains(id))
			response = "Espera \n";
		else
			response = "Recurso nao solicitado \n";
		return response;
	}

	public void quit() {
		regiaoOcupada = false;
		System.out.println("Regiao Critica Liberada");
		if (fila_de_requisicoes.size()>0){
			ocupante = fila_de_requisicoes.getFirst();
			fila_de_requisicoes.remove(); 
			regiaoOcupada = true;
			System.out.println("Acesso a Regiao critica concedido ao processo: "+ ocupante);
		}
	}
	
	public String operation(String key, String id) throws RemoteException {
		
		String response = "";
		if (key.equals("1")) {
			try {
	       		requestCriticalZone(id); 
	    	} catch (Exception e) {
	    		System.err.println("Server exception: " + e.toString());
            	e.printStackTrace();
	    	}
		}
		if (key.equals("2")) 
			displayQueue();
		if (key.equals("3"))
			response = displayStatus(id);
		if (key.equals("x")) 
			 quit(); 
		return response;
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
