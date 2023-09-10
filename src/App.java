import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class App extends JFrame implements ActionListener {

    JTextField textField;
    Double num;
    String ultimoOperador;

    public App() {

        JFrame frame = new JFrame("Calculadora");
        frame.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        JLabel titulo = new JLabel("Calculadora");

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridwidth = 4;
        constraints.weightx = 4.0;
        constraints.insets = new Insets(5, 5, 5, 5);

        frame.add(titulo, constraints);

        textField = new JTextField();

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridwidth = 4;
        constraints.weightx = 4.0;
        constraints.insets = new Insets(5, 5, 5, 5);

        frame.add(textField, constraints);

        // Botones

        int fila = 2;
        int columna = 0;

        String[] buttons = {
                "AC", "CE", "%", "/",
                "7", "8", "9", "*",
                "4", "5", "6", "-",
                "1", "2", "3", "+",
                "0", ".", "="
        };

        for (var textButton : buttons) {
            JButton b = new JButton(textButton);

            if (b.getText().equals("+")) {
                constraints.gridx = columna;
                constraints.gridy = fila;
                constraints.fill = GridBagConstraints.VERTICAL;
                constraints.gridheight = 2;
                constraints.weighty = 2.0;
            } else {
                constraints.gridx = columna;
                constraints.gridy = fila;
                constraints.fill = GridBagConstraints.HORIZONTAL;
                constraints.gridwidth = 1;
            }

            b.addActionListener(this);

            frame.add(b, constraints);

            columna++;

            if (columna > 3) {
                columna = 0;
                fila++;
            }
        }

        frame.pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String expresion = e.getActionCommand();
        Double num2;

        if (expresion.equals("=")) {

            if (textField.getText().equals("")) {
                textField.setText("");
                num2 = null;
            } else {
                num2 = Double.parseDouble(textField.getText());
            }

            if (num == null) {
                if (num2 != null) {
                    textField.setText(Double.toString(num2));
                } else {
                    textField.setText("");
                }
            } else if (Double.toString(num2) == null) {
                textField.setText("");
            } else {
                calcular(num, num2, ultimoOperador);
            }

        } else if (expresion.equals("+") || expresion.equals("-") || expresion.equals("*") || expresion.equals("/")
                || expresion.equals("%")) {
            ultimoOperador = expresion;
            if (!(textField.getText().equals("") || textField.getText().equals(" "))) {
                num = Double.parseDouble(textField.getText());
                textField.setText("");
            }
        } else if (expresion.equals("CE")) {
            if (textField.getText().length() > 0) {
                textField.setText(textField.getText().substring(0, textField.getText().length() - 1));
            }
        } else if (expresion.equals("AC")) {
            num = null;
            num2 = null;
            ultimoOperador = null;
            textField.setText("");
        } else {
            textField.setText(textField.getText() + expresion);
        }
    }

    public void calcular(double n1, double n2, String operador) {
        double resultado;
        switch (operador) {
            case "+":
                resultado = n1 + n2;
                break;
            case "-":
                resultado = n1 - n2;
                break;
            case "*":
                resultado = n1 * n2;
                break;
            case "/":
                resultado = n1 / n2;
                break;
            default:
                resultado = n1 % n2;
        }
        num = resultado;
        textField.setText(resultado + "");
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        new App();
    }

}