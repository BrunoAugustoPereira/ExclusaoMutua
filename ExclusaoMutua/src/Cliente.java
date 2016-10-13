import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.io.*;

public class Cliente{

    private Cliente() {}
    public String id;

    public static void main(String[] args) {

        String host = (args.length < 1) ? null : args[0];
        try {
            boolean looping=true;
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            Registry registry = LocateRegistry.getRegistry(host, 1099); //host, port
            RecursoComp stub = (RecursoComp) registry.lookup("RecursoComp");

            System.out.println("entre com o Identificador para esse processo");
            String id = br.readLine();

            System.out.println("[1] solicitar recurso [2] ver fila [3] ver status do recurso [x] sair");
            while(looping) {
                String key = br.readLine();
                try {
                    String response = stub.operation(key,id);
                    System.out.print(response);
                } catch (Exception e) {
                System.err. println("Client exception: " + e.toString());
                e.printStackTrace();
            }
        }
        } catch (Exception e) {
            System.err. println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
