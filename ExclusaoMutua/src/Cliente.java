import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.io.*;

public class Cliente{

    private Cliente() {}

    public static void main(String[] args) {

        String host = (args.length < 1) ? null : args[0];
        try {
            boolean looping=true;
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            Registry registry = LocateRegistry.getRegistry(host, 1099); //host, porta
            RecursoComp stub = (RecursoComp) registry.lookup("RecursoComp");

            System.out.println("Entre com o Identificador para esse processo");
            String id = br.readLine();

            System.out.println("[1] solicitar, [2] ver fila de requisicoes, [3] ver status ou [x] liberar recurso");
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
