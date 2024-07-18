package tutes.gui2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator {
    private static StringBuilder numberEntered;
    private static String factor1, operation, factor2, result = "";
    private static boolean afterOperation, once;
    private static JLabel resultBox, preBox;


    public static void main(String[] args) {
        JFrame window = new JFrame("Calculator");
        window.setSize(200, 200);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);

        // data
        numberEntered = new StringBuilder();
        JButton[] keys = new JButton[]{
                new JButton("7"),
                new JButton("8"),
                new JButton("9"),
                new JButton("+"),
                new JButton("4"),
                new JButton("5"),
                new JButton("6"),
                new JButton("-"),
                new JButton("1"),
                new JButton("2"),
                new JButton("3"),
                new JButton("*"),
                new JButton("0"),
                new JButton("."),
                new JButton("="),
                new JButton("/"),
        };

        resultBox = new JLabel(" ");
        resultBox.setHorizontalAlignment(JLabel.RIGHT);

        JPanel keyBoard = new JPanel();
        keyBoard.setLayout(new GridLayout(4, 4));
        for (JButton key : keys) {
            key.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // String keyValue = e.getActionCommand();
                    String keyValue = key.getText();
                    String type = detectKey(keyValue);
                    if (type.equals("number")) {
                        numberEntered.append(keyValue);
                        resultBox.setText(numberEntered.toString());
                    } else if (type.equals("operation")) {
                        if (!afterOperation) {
                            factor1 = numberEntered.toString();
                            resultBox.setText(factor1);

                            operation = keyValue;

                            numberEntered = new StringBuilder();
                            once = false;
                            afterOperation = true;
                        } else {
                            factor2 = numberEntered.toString();
                            result = calculate(factor1, operation, factor2);
                            resultBox.setText(result);

                            operation = keyValue;
                            factor1 = result;

                            numberEntered = new StringBuilder();
                            once = false;
                        }
                    } else if (type.equals("decimal")) {
                        if (!once) {
                            numberEntered.append(keyValue);
                            resultBox.setText(numberEntered.toString());

                            once = true;
                        }
                    } else if (type.equals("equal")) {
                        if (afterOperation) {
                            factor2 = numberEntered.toString();
                            result = calculate(factor1, operation, factor2);
                            resultBox.setText(result);

                            numberEntered = new StringBuilder(result);
                            once = false;
                            afterOperation = false;
                        } else {
                            resultBox.setText(numberEntered.toString());
                        }
                    }
                }
            });
            keyBoard.add(key);
        }
        window.add(resultBox, BorderLayout.NORTH);
        window.add(keyBoard, BorderLayout.CENTER);
        window.setVisible(true);
    }

    private static String detectKey(String key) {
        String[] numbers = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        String[] operations = new String[]{"+", "-", "*", "/"};

        for (String number : numbers) {
            if (key.equals(number)) {
                return "number";
            }
        }
        for (String operation : operations) {
            if (key.equals(operation)) {
                return "operation";
            }
        }
        if (key.equals("=")) return "equal";
        if (key.equals(".")) return "decimal";
        return "?";
    }

    private static String calculate(String factor1, String operation, String factor2) {
        double num1 = Double.parseDouble(factor1);
        double num2 = Double.parseDouble(factor2);
        if (num2 == 0 && operation.equals("/")) return "Error: Division to 0";
        if (operation.equals("+")) return String.valueOf(num1 + num2);
        if (operation.equals("-")) return String.valueOf(num1 - num2);
        if (operation.equals("*")) return String.valueOf(num1 * num2);
        if (operation.equals("/")) return String.valueOf(num1 / num2);
        return "?";
    }
}
