import java.awt.event.*;
import javax.swing.border.*;
import javax.swing.*;
import java.awt.*;

//Extends JLabel, JButton and JTextField class to format these units
class MyLabel extends JLabel {
    MyLabel(String operator) {
        setText(operator);
        Border lineBorder = new LineBorder(Color.BLUE, 1);
        setBorder(lineBorder);
        setHorizontalAlignment(JTextField.CENTER);
    }
}
class MyButton extends JButton {
    MyButton(String operator) {
        setText(operator);
    }
}
class MyTextField extends JTextField {
    MyTextField() {
        setHorizontalAlignment(JTextField.CENTER);
    }
    MyTextField(String num) {
        setText(num);
        setHorizontalAlignment(JTextField.CENTER);
    }
}

public class Calculator extends JFrame {
    private static final int ROW = 2;
    private static final int COLUMN = 5;
    private static final int VERTICAL = 10;
    private static final int HORIZONAL = 10;
    private static final int WIDTH = 450;
    private static final int HIGH = 200;

    private MyTextField firstNum = new MyTextField("12");
    private MyTextField secondNum = new MyTextField("2");

    private MyLabel operator = new MyLabel("");
    private MyLabel equal = new MyLabel("=");
    private MyLabel result = new MyLabel("");

    private MyButton plus = new MyButton("+");
    private MyButton substract = new MyButton("-");
    private MyButton multiple = new MyButton("*");
    private MyButton division = new MyButton("/");

    private JButton jbOK = new JButton("OK");

    public Calculator() {
        add(firstNum);
        add(operator);
        add(secondNum);
        add(equal);
        add(result);
        add(plus);
        add(substract);
        add(multiple);
        add(division);
        add(jbOK);

        setLayout(new GridLayout(ROW, COLUMN, VERTICAL, HORIZONAL));

        plus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                operator.setText("+");
            }
        });

        substract.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                operator.setText("-");
            }
        });

        multiple.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                operator.setText("*");
            }
        });

        division.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                operator.setText("/");
            }
        });
        
        jbOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int number1, number2;
                
                try {
                    number1 = Integer.parseInt(firstNum.getText());
                    number2 = Integer.parseInt(secondNum.getText());
                }
                catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid Input");
                    return;
                }
                compute(number1, number2);
            }
        });
    }

    public static void main(String[] args) {
        Calculator frame = new Calculator();
        frame.setTitle("13331084_Calculator");
        frame.setSize(WIDTH, HIGH);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    void compute(int number1, int number2) {
        int rr = 0;
        String temp = operator.getText();
        if (temp.equals("+")) {
            rr = number1+number2;
        } else if (temp.equals("-")) {
            rr = number1-number2;
        } else if (temp.equals("*")) {
            rr = number1*number2;
        } else if (temp.equals("/") && (number2 != 0)) {
            rr = number1/number2;
        }
        String str = Integer.toString(rr);

        //If the denominator is zero, then the result becomes NaN
        if (temp.equals("/") && (number2 == 0)) {
            result.setText("NaN");
        } else {
            result.setText(str);
        }
    }
}
