import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class ClienteCalculadora {

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost");
            Calculadora calc = (Calculadora) registry.lookup("calculadora");

            Scanner input = new Scanner(System.in);
            int choice;

            do {
                System.out.println("\nMenu da Calculadora RMI");
                System.out.println("1. Adição");
                System.out.println("2. Subtração");
                System.out.println("3. Multiplicação");
                System.out.println("4. Divisão");
                System.out.println("5. Seno");
                System.out.println("6. Cosseno");
                System.out.println("7. Tangente");
                System.out.println("8. Potenciação");
                System.out.println("9. Raiz quadrada");
                System.out.println("10. Raiz cúbica");
                System.out.println("11. Logaritmo");
                System.out.println("12. Sair");
                System.out.print("Escolha uma opção: ");
                choice = input.nextInt();

                Numero num1, num2;

                switch (choice) {
                    case 1:
                        System.out.print("Digite o primeiro número: ");
                        num1 = new NumeroImpl(input.nextDouble());
                        System.out.print("Digite o segundo número: ");
                        num2 = new NumeroImpl(input.nextDouble());
                        Numero resultadoSoma = calc.soma(num1, num2);
                        System.out.println("Resultado: " + resultadoSoma.getValor());
                        break;

                    case 2:
                        System.out.print("Digite o primeiro número: ");
                        num1 = new NumeroImpl(input.nextDouble());
                        System.out.print("Digite o segundo número: ");
                        num2 = new NumeroImpl(input.nextDouble());
                        Numero resultadoSubtracao = calc.subtrai(num1, num2);
                        System.out.println("Resultado: " + resultadoSubtracao.getValor());
                        break;

                    case 3:
                        System.out.print("Digite o primeiro número: ");
                        num1 = new NumeroImpl(input.nextDouble());
                        System.out.print("Digite o segundo número: ");
                        num2 = new NumeroImpl(input.nextDouble());
                        Numero resultadoMultiplicacao = calc.multiplica(num1, num2);
                        System.out.println("Resultado: " + resultadoMultiplicacao.getValor());
                        break;

                    case 4:
                        System.out.print("Digite o numerador: ");
                        num1 = new NumeroImpl(input.nextDouble());
                        System.out.print("Digite o denominador: ");
                        num2 = new NumeroImpl(input.nextDouble());
                        try {
                            Numero resultadoDivisao = calc.divide(num1, num2);
                            System.out.println("Resultado: " + resultadoDivisao.getValor());
                        } catch (DivisaoPorZeroException e) {
                            System.out.println("Erro: Divisão por zero!");
                        }
                        break;

                    case 5:
                        System.out.print("Digite o número para calcular o seno: ");
                        num1 = new NumeroImpl(input.nextDouble());
                        Numero resultadoSeno = calc.seno(num1);
                        System.out.println("Seno de " + num1.getValor() + " é: " + resultadoSeno.getValor());
                        break;

                    case 6:
                        System.out.print("Digite o número para calcular o cosseno: ");
                        num1 = new NumeroImpl(input.nextDouble());
                        Numero resultadoCos = calc.cosseno(num1);
                        System.out.println("Cosseno de " + num1.getValor() + " é: " + resultadoCos.getValor());
                        break;

                    case 7:
                        System.out.print("Digite o número para calcular a tangente: ");
                        num1 = new NumeroImpl(input.nextDouble());
                        Numero resultadoTan = calc.tangente(num1);
                        System.out.println("Tangente de " + num1.getValor() + " é: " + resultadoTan.getValor());
                        break;

                    case 8:
                        System.out.print("Digite a base: ");
                        num1 = new NumeroImpl(input.nextDouble());
                        System.out.print("Digite o expoente: ");
                        num2 = new NumeroImpl(input.nextDouble());
                        Numero resultadoPotencia = calc.potenciacao(num1, num2);
                        System.out.println("Resultado: " + resultadoPotencia.getValor());
                        break;

                    case 9:
                        System.out.print("Digite o número para calcular a raiz quadrada: ");
                        num1 = new NumeroImpl(input.nextDouble());
                        Numero resultadoRaizQuad = calc.raizQuadrada(num1);
                        System.out.println(
                                "A raiz quadrada de " + num1.getValor() + " é: " + resultadoRaizQuad.getValor());
                        break;

                    case 10:
                        System.out.print("Digite o número para calcular a raiz cúbica: ");
                        num1 = new NumeroImpl(input.nextDouble());
                        Numero resultadoRaizCub = calc.raizCubica(num1);
                        System.out
                                .println("A raiz cúbica de " + num1.getValor() + " é: " + resultadoRaizCub.getValor());
                        break;

                    case 11:
                        System.out.print("Digite o número para calcular o logaritmo: ");
                        num1 = new NumeroImpl(input.nextDouble());
                        Numero resultadoLog = calc.logaritmo(num1);
                        System.out.println("O logaritmo de " + num1.getValor() + " é: " + resultadoLog.getValor());
                        break;

                    case 12:
                        System.out.println("Saindo...");
                        break;

                    default:
                        System.out.println("Opção inválida! Tente novamente.");
                }
            } while (choice != 12);

        } catch (Exception e) {
            System.err.println("Erro no cliente: " + e.toString());
            e.printStackTrace();
        }
    }
}
