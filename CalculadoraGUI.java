import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CalculadoraGUI extends JFrame {
    private JTextField txtNumero1, txtNumero2;
    private JLabel lblResultado;
    private Calculadora calc;

    public CalculadoraGUI() {
        setTitle("Calculadora RMI");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 3, 5, 5));

        panel.add(new JLabel("Número 1:"));
        txtNumero1 = new JTextField();
        panel.add(txtNumero1);

        panel.add(new JLabel("Número 2:"));
        txtNumero2 = new JTextField();
        panel.add(txtNumero2);

        String[] operacoes = { "+", "-", "*", "/", "Seno", "Cosseno", "Tangente", "Potência", "Raiz²", "Raiz³", "Log" };
        for (String operacao : operacoes) {
            JButton btn = new JButton(operacao);
            btn.addActionListener(this::calcular);
            panel.add(btn);
        }

        lblResultado = new JLabel("Resultado: ");
        lblResultado.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(lblResultado);

        add(panel);

        try {
            Registry registry = LocateRegistry.getRegistry("localhost");
            calc = (Calculadora) registry.lookup("calculadora");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao conectar ao servidor RMI!", "Erro", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    private void calcular(ActionEvent e) {
        try {
            double num1 = Double.parseDouble(txtNumero1.getText());
            double num2 = txtNumero2.getText().isEmpty() ? 0 : Double.parseDouble(txtNumero2.getText());
            String operacao = ((JButton) e.getSource()).getText();
            Numero resultado = null;

            switch (operacao) {
                case "+":
                    resultado = calc.soma(new NumeroImpl(num1), new NumeroImpl(num2));
                    break;
                case "-":
                    resultado = calc.subtrai(new NumeroImpl(num1), new NumeroImpl(num2));
                    break;
                case "*":
                    resultado = calc.multiplica(new NumeroImpl(num1), new NumeroImpl(num2));
                    break;
                case "/":
                    resultado = calc.divide(new NumeroImpl(num1), new NumeroImpl(num2));
                    break;
                case "Seno":
                    resultado = calc.seno(new NumeroImpl(num1));
                    break;
                case "Cosseno":
                    resultado = calc.cosseno(new NumeroImpl(num1));
                    break;
                case "Tangente":
                    resultado = calc.tangente(new NumeroImpl(num1));
                    break;
                case "Potência":
                    resultado = calc.potenciacao(new NumeroImpl(num1), new NumeroImpl(num2));
                    break;
                case "Raiz²":
                    resultado = calc.raizQuadrada(new NumeroImpl(num1));
                    break;
                case "Raiz³":
                    resultado = calc.raizCubica(new NumeroImpl(num1));
                    break;
                case "Log":
                    resultado = calc.logaritmo(new NumeroImpl(num1));
                    break;
            }
            lblResultado.setText("Resultado: " + resultado.getValor());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, insira números válidos!", "Erro",
                    JOptionPane.ERROR_MESSAGE);
        } catch (DivisaoPorZeroException ex) {
            JOptionPane.showMessageDialog(this, "Erro: Divisão por zero!", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao conectar ao servidor!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CalculadoraGUI().setVisible(true));
    }
}
