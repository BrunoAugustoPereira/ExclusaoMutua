import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
        
public class Server implements RecursoComp {
        
    private boolean flag = false;
    private LinkedList queue = new LinkedList();
    private int IdCurrent;
	
    public Server() {}

	
	//requisicao da regiao critica tendo como parametro o id client
    	public void requestCriticalZone(int id){
		//checar disponibilidade do bollean
		/*
		if(flag== false){
		
		//guardar referencia do processo que esta na regiao critica
		IdCurrent=id;
		// mudar o estado da flag para true
		flag=true;
		
		
		}
		else if(flag == true){
		
		//LINKEDLIST.add(id);
		queue.add(id);
		
		}

		*/
	}
	
	//mostra a fila de processos que estao na espera pela regiao critica
	public void displayQueue(){
	
	//System.out.println(fila_de_requisicoes  AKA LINKEDLIST);
		System.out.println(queue);
	
	}
	public void displayStatus(){
	
		/*
		if(regiaoOcupada== false){
		
		System.out.println(regiao critica esta disponivel);
		
		}
		else if(regiaoOcupada== true){
		
		System.out.println(regiao critica esta em uso+);
		//pode falar qual id do processo que esta utilizando a regiao critica
	
		
		}

		*/
	}
	
	public void operation(int id, String key) throws RemoteException {
		if (key.equals("1")) 
			requestCriticalZone(id); //ver como pegar id
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
