import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class ServidorCalculadora {
  public static void main(String args[]) {
    try {
      // Criar o objeto servidor
      CalculadoraImpl calc = new CalculadoraImpl();
      // Exportar o objeto remoto
      Calculadora stub = (Calculadora) UnicastRemoteObject.exportObject(calc, 0);

      // Criar o registro RMI na porta 1099, caso ainda não exista
      Registry registry;
      try {
        registry = LocateRegistry.createRegistry(1099);
        System.out.println("Registro RMI criado na porta 1099.");
      } catch (Exception e) {
        registry = LocateRegistry.getRegistry();
        System.out.println("Registro RMI já existente.");
      }

      // Registra o objeto remoto
      registry.rebind("calculadora", stub);
      System.out.println("Servidor iniciado e calculadora registrada.");

    } catch (Exception e) {
      System.err.println("Erro no servidor: " + e.toString());
      e.printStackTrace();
    }
  }
}
