import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.io.*;

public class Cliente extends Thread{

    private Cliente() {}

    public static void main(String[] args) {

        String host = (args.length < 1) ? null : args[0];
        try {
        	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            Registry registry = LocateRegistry.getRegistry(host, 1099); //host, port
            Calculator stub = (Calculator) registry.lookup("Calculator");

            System.out.println("a ? ");
            int a = Integer.parseInt(br.readLine());
            System.out.println("b ? ");
            int b = Integer.parseInt(br.readLine());
            System.out.println("operator ? ");
            String operator = br.readLine();
            int response = stub.op(a, b, operator);
            System.out.println("response: " + response);
        } catch (Exception e) {
            System.err.	println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}