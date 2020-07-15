import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CalculatorView {

    private int WINDOW_WIDTH = 400;
    private JFrame frame;

    private JLabel operationLabel;
    private JLabel resultLabel;

    private JButton zero;
    private JButton one;
    private JButton two;
    private JButton three;
    private JButton four;
    private JButton five;
    private JButton six;
    private JButton seven;
    private JButton eight;
    private JButton nine;
    private JButton plus;
    private JButton minus;
    private JButton multiplicationSign;
    private JButton divisionSign;
    private JButton secondPower;
    private JButton squareRoot;
    private JButton equalSign;
    private JButton dot;
    private JButton backspace;
    private JButton clear;

    public CalculatorView() {

        frame = new JFrame();
        frame.setTitle("Kalkulator");
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setMinimumSize(new Dimension(WINDOW_WIDTH+20, 540)); // Ramka większa o 40p od Panelu
        frame.setPreferredSize(new Dimension(WINDOW_WIDTH+20, 540));

        addComponentsToPane();

        frame.setVisible(true);
    }

    private void addComponentsToPane() {

        Color backgroundColor = new Color(31,31,31);

        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new BorderLayout());
        resultPanel.setPreferredSize(new Dimension(WINDOW_WIDTH, 100));
        resultPanel.setBackground(backgroundColor);

        operationLabel = new JLabel("", SwingConstants.RIGHT);
        operationLabel.setPreferredSize(new Dimension(WINDOW_WIDTH, 30));
        operationLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        operationLabel.setForeground(Color.lightGray);

        resultLabel = new JLabel("0", SwingConstants.RIGHT);
        resultLabel.setPreferredSize(new Dimension(WINDOW_WIDTH, 70));
        resultLabel.setFont(new Font("Arial", Font.PLAIN, 50));
        resultLabel.setForeground(Color.white);

        resultPanel.add(operationLabel, BorderLayout.NORTH);
        resultPanel.add(resultLabel, BorderLayout.SOUTH);

        JPanel keyPanel = new JPanel();
        keyPanel.setPreferredSize(new Dimension(WINDOW_WIDTH, 400));
        keyPanel.setLayout(new GridLayout(5, 4, 3, 3));
        keyPanel.setBackground(backgroundColor);

        createButtons();
        setButtonsFonts();
        setButtonsForegroungs();
        setButtonsBackgroungs();
        setButtonsBorderPainted();
        addButtonsToPanel(keyPanel);

        frame.getContentPane().add(resultPanel, BorderLayout.NORTH);
        frame.getContentPane().add(keyPanel, BorderLayout.CENTER);
    }

    private void createButtons() {

        zero = new JButton("0");
        one = new JButton("1");
        two = new JButton("2");
        three = new JButton("3");
        four = new JButton("4");
        five = new JButton("5");
        six = new JButton("6");
        seven = new JButton("7");
        eight = new JButton("8");
        nine = new JButton("9");
        plus = new JButton("+");
        minus = new JButton("-");
        multiplicationSign = new JButton("×");
        divisionSign = new JButton("÷");
        secondPower = new JButton("x²");
        squareRoot = new JButton("√x");
        equalSign = new JButton("=");
        dot = new JButton(".");
        backspace = new JButton("⌫");
        clear = new JButton("C");
    }

    private void setButtonsForegroungs() {

        zero.setForeground(Color.white);
        one.setForeground(Color.white);
        two.setForeground(Color.white);
        three.setForeground(Color.white);
        four.setForeground(Color.white);
        five.setForeground(Color.white);
        six.setForeground(Color.white);
        seven.setForeground(Color.white);
        eight.setForeground(Color.white);
        nine.setForeground(Color.white);
        plus.setForeground(Color.white);
        minus.setForeground(Color.white);
        multiplicationSign.setForeground(Color.white);
        divisionSign.setForeground(Color.white);
        secondPower.setForeground(Color.white);
        squareRoot.setForeground(Color.white);
        equalSign.setForeground(Color.white);
        dot.setForeground(Color.white);
        backspace.setForeground(Color.white);
        clear.setForeground(Color.white);
    }

    private void setButtonsBackgroungs() {

        Color numbersColor = new Color(6,6,6);
        Color operationsColor = new Color(19,19,19);
        Color equalsColor = new Color(19,67,105);

        zero.setBackground(numbersColor);
        one.setBackground(numbersColor);
        two.setBackground(numbersColor);
        three.setBackground(numbersColor);
        four.setBackground(numbersColor);
        five.setBackground(numbersColor);
        six.setBackground(numbersColor);
        seven.setBackground(numbersColor);
        eight.setBackground(numbersColor);
        nine.setBackground(numbersColor);
        plus.setBackground(operationsColor);
        minus.setBackground(operationsColor);
        multiplicationSign.setBackground(operationsColor);
        divisionSign.setBackground(operationsColor);
        secondPower.setBackground(operationsColor);
        squareRoot.setBackground(operationsColor);
        equalSign.setBackground(equalsColor);
        dot.setBackground(numbersColor);
        backspace.setBackground(operationsColor);
        clear.setBackground(numbersColor);
    }

    private void setButtonsFonts() {

        Font numbersFont = new Font("Arial", Font.BOLD, 20);
        Font signFont = new Font("Times New Roman", Font.BOLD, 35);
        Font remainSignFont = new Font("Times New Roman", Font.ITALIC | Font.BOLD, 21);

        zero.setFont(numbersFont);
        one.setFont(numbersFont);
        two.setFont(numbersFont);
        three.setFont(numbersFont);
        four.setFont(numbersFont);
        five.setFont(numbersFont);
        six.setFont(numbersFont);
        seven.setFont(numbersFont);
        eight.setFont(numbersFont);
        nine.setFont(numbersFont);
        plus.setFont(signFont);
        minus.setFont(signFont);
        multiplicationSign.setFont(signFont);
        divisionSign.setFont(signFont);
        secondPower.setFont(remainSignFont);
        squareRoot.setFont(remainSignFont);
        equalSign.setFont(signFont);
        dot.setFont(signFont);
        backspace.setFont(new Font("Default", Font.BOLD, 20));
        clear.setFont(numbersFont);
    }

    private void setButtonsBorderPainted() {

        zero.setBorderPainted(false);
        one.setBorderPainted(false);
        two.setBorderPainted(false);
        three.setBorderPainted(false);
        four.setBorderPainted(false);
        five.setBorderPainted(false);
        six.setBorderPainted(false);
        seven.setBorderPainted(false);
        eight.setBorderPainted(false);
        nine.setBorderPainted(false);
        plus.setBorderPainted(false);
        minus.setBorderPainted(false);
        multiplicationSign.setBorderPainted(false);
        divisionSign.setBorderPainted(false);
        secondPower.setBorderPainted(false);
        squareRoot.setBorderPainted(false);
        equalSign.setBorderPainted(false);
        dot.setBorderPainted(false);
        backspace.setBorderPainted(false);
        clear.setBorderPainted(false);
    }

    private void addButtonsToPanel(JPanel keyPanel) {

        keyPanel.add(backspace);
        keyPanel.add(secondPower);
        keyPanel.add(squareRoot);
        keyPanel.add(divisionSign);
        keyPanel.add(seven);
        keyPanel.add(eight);
        keyPanel.add(nine);
        keyPanel.add(multiplicationSign);
        keyPanel.add(four);
        keyPanel.add(five);
        keyPanel.add(six);
        keyPanel.add(minus);
        keyPanel.add(one);
        keyPanel.add(two);
        keyPanel.add(three);
        keyPanel.add(plus);
        keyPanel.add(clear);
        keyPanel.add(zero);
        keyPanel.add(dot);
        keyPanel.add(equalSign);
    }

    public void addListeners(ActionListener actionListener, KeyListener keyListener, MouseListener mouseListener) {

        zero.addActionListener(actionListener);
        one.addActionListener(actionListener);
        two.addActionListener(actionListener);
        three.addActionListener(actionListener);
        four.addActionListener(actionListener);
        five.addActionListener(actionListener);
        six.addActionListener(actionListener);
        seven.addActionListener(actionListener);
        eight.addActionListener(actionListener);
        nine.addActionListener(actionListener);
        plus.addActionListener(actionListener);
        minus.addActionListener(actionListener);
        multiplicationSign.addActionListener(actionListener);
        divisionSign.addActionListener(actionListener);
        secondPower.addActionListener(actionListener);
        squareRoot.addActionListener(actionListener);
        equalSign.addActionListener(actionListener);
        dot.addActionListener(actionListener);
        backspace.addActionListener(actionListener);
        clear.addActionListener(actionListener);

        zero.addKeyListener(keyListener);
        one.addKeyListener(keyListener);
        two.addKeyListener(keyListener);
        three.addKeyListener(keyListener);
        four.addKeyListener(keyListener);
        five.addKeyListener(keyListener);
        six.addKeyListener(keyListener);
        seven.addKeyListener(keyListener);
        eight.addKeyListener(keyListener);
        nine.addKeyListener(keyListener);
        plus.addKeyListener(keyListener);
        minus.addKeyListener(keyListener);
        multiplicationSign.addKeyListener(keyListener);
        divisionSign.addKeyListener(keyListener);
        equalSign.addKeyListener(keyListener);
        dot.addKeyListener(keyListener);
        backspace.addKeyListener(keyListener);
        clear.addKeyListener(keyListener);

        zero.addMouseListener(mouseListener);
        one.addMouseListener(mouseListener);
        two.addMouseListener(mouseListener);
        three.addMouseListener(mouseListener);
        four.addMouseListener(mouseListener);
        five.addMouseListener(mouseListener);
        six.addMouseListener(mouseListener);
        seven.addMouseListener(mouseListener);
        eight.addMouseListener(mouseListener);
        nine.addMouseListener(mouseListener);
        plus.addMouseListener(mouseListener);
        minus.addMouseListener(mouseListener);
        multiplicationSign.addMouseListener(mouseListener);
        divisionSign.addMouseListener(mouseListener);
        secondPower.addMouseListener(mouseListener);
        squareRoot.addMouseListener(mouseListener);
        equalSign.addMouseListener(mouseListener);
        dot.addMouseListener(mouseListener);
        backspace.addMouseListener(mouseListener);
        clear.addMouseListener(mouseListener);
    }



    public void setOperationLabelText(String text) {
        operationLabel.setText(text);
    }

    public void setResultLabelText(String text) {
        resultLabel.setText(text);
    }

    public JButton getZero() {
        return zero;
    }

    public JButton getOne() {
        return one;
    }

    public JButton getTwo() {
        return two;
    }

    public JButton getThree() {
        return three;
    }

    public JButton getFour() {
        return four;
    }

    public JButton getFive() {
        return five;
    }

    public JButton getSix() {
        return six;
    }

    public JButton getSeven() {
        return seven;
    }

    public JButton getEight() {
        return eight;
    }

    public JButton getNine() {
        return nine;
    }

    public JButton getPlus() {
        return plus;
    }

    public JButton getMinus() {
        return minus;
    }

    public JButton getMultiplicationSign() {
        return multiplicationSign;
    }

    public JButton getDivisionSign() {
        return divisionSign;
    }

    public JButton getSecondPower() {
        return secondPower;
    }

    public JButton getSquareRoot() {
        return squareRoot;
    }

    public JButton getEqualSign() {
        return equalSign;
    }

    public JButton getDot() {
        return dot;
    }

    public JButton getBackspace() {
        return backspace;
    }

    public JButton getClear() {
        return clear;
    }
}
