import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.io.*;

public class Cliente extends Thread{

    private Cliente() {}
    public void Run(){
    
    
    }
    public static void main(String[] args) {

        String host = (args.length < 1) ? null : args[0];
        try {
        	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            Registry registry = LocateRegistry.getRegistry(host, 1099); //host, port
            RecursoComp stub = (RecursoComp) registry.lookup("RecursoComp");

            
            
            
            System.out.println("[1] solicitar recurso [2] ver fila [3] ver status do recurso [0]sair ");
            int a = Integer.parseInt(br.readLine());
            switch(a){
                 case '1':
                    //chama a solicatacao de recurso
                    // stub.requestCriticalZone(## id do processo##);
            
                case '2':
                    //chama a solicatacao de recurso
                    // stub.displayQueue(); 
                
                case '3':
                    //chama a solicatacao de recurso
                    // stub.displayStatus(); 
            }
               
        } catch (Exception e) {
            System.err.	println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
