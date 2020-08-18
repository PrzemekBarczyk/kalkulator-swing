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

    private int PANEL_WIDTH = 400;
    private JFrame frame;

    private JLabel operationLabel;
    private JLabel resultLabel;

    private JButton zeroButton;
    private JButton oneButton;
    private JButton twoButton;
    private JButton threeButton;
    private JButton fourButton;
    private JButton fiveButton;
    private JButton sixButton;
    private JButton sevenButton;
    private JButton eightButton;
    private JButton nineButton;
    private JButton dotButton;
    private JButton plusButton;
    private JButton minusButton;
    private JButton multiplicationButton;
    private JButton divisionButton;
    private JButton secondPowerButton;
    private JButton squareRootButton;
    private JButton equalsButton;
    private JButton backspaceButton;
    private JButton clearButton;
    private JButton clearEntryButton;
    private JButton percentButton;
    private JButton fractionButton;
    private JButton signNegationButton;

    /**
     * Ustawia Ramkę i wywołuję metodę która dodaje do niej komponenty
     */
    public CalculatorView() {

        frame = new JFrame();
        frame.setTitle("Kalkulator");
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setMinimumSize(new Dimension(PANEL_WIDTH+20, 540)); // Ramka większa o 40p od Panelu
        frame.setPreferredSize(new Dimension(PANEL_WIDTH+20, 540));

        addComponentsToPane();

        frame.setVisible(true);
    }

    /**
     * Dodaje komponenty do głównego Panelu
     */
    private void addComponentsToPane() {

        Color backgroundColor = new Color(31,31,31);

        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new BorderLayout());
        resultPanel.setPreferredSize(new Dimension(PANEL_WIDTH, 100));
        resultPanel.setBackground(backgroundColor);

        operationLabel = new JLabel("", SwingConstants.RIGHT);
        operationLabel.setPreferredSize(new Dimension(PANEL_WIDTH, 30));
        operationLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        operationLabel.setForeground(Color.lightGray);

        resultLabel = new JLabel("0", SwingConstants.RIGHT);
        resultLabel.setPreferredSize(new Dimension(PANEL_WIDTH, 70));
        resultLabel.setFont(new Font("Arial", Font.PLAIN, 50));
        resultLabel.setForeground(Color.white);

        resultPanel.add(operationLabel, BorderLayout.NORTH);
        resultPanel.add(resultLabel, BorderLayout.SOUTH);

        JPanel keyPanel = new JPanel();
        keyPanel.setPreferredSize(new Dimension(PANEL_WIDTH, 400));
        keyPanel.setLayout(new GridLayout(6, 4, 3, 3));
        keyPanel.setBackground(backgroundColor);

        createButtons();
        setButtonsFonts();
        setButtonsForegrounds();
        setButtonsBackgrounds();
        setButtonsBorderPainted();
        setButtonsFocusPainted();
        addButtonsToPanel(keyPanel);

        frame.getContentPane().add(resultPanel, BorderLayout.NORTH);
        frame.getContentPane().add(keyPanel, BorderLayout.CENTER);
    }

    /**
     * Tworzy obiekty przycisków
     */
    private void createButtons() {

        zeroButton = new JButton("0");
        oneButton = new JButton("1");
        twoButton = new JButton("2");
        threeButton = new JButton("3");
        fourButton = new JButton("4");
        fiveButton = new JButton("5");
        sixButton = new JButton("6");
        sevenButton = new JButton("7");
        eightButton = new JButton("8");
        nineButton = new JButton("9");
        dotButton = new JButton(".");
        plusButton = new JButton("+");
        minusButton = new JButton("−");
        multiplicationButton = new JButton("×");
        divisionButton = new JButton("÷");
        secondPowerButton = new JButton("x²");
        squareRootButton = new JButton("√x");
        percentButton = new JButton("%");
        fractionButton = new JButton("¹⁄ₓ");
        equalsButton = new JButton("=");
        backspaceButton = new JButton("⌫");
        clearButton = new JButton("C");
        clearEntryButton = new JButton("CE");
        signNegationButton = new JButton("⁺⁄₋");
    }

    /**
     * Zmienia kolor czcionek na przyciskach
     */
    private void setButtonsForegrounds() {

        zeroButton.setForeground(Color.white);
        oneButton.setForeground(Color.white);
        twoButton.setForeground(Color.white);
        threeButton.setForeground(Color.white);
        fourButton.setForeground(Color.white);
        fiveButton.setForeground(Color.white);
        sixButton.setForeground(Color.white);
        sevenButton.setForeground(Color.white);
        eightButton.setForeground(Color.white);
        nineButton.setForeground(Color.white);
        dotButton.setForeground(Color.white);
        plusButton.setForeground(Color.white);
        minusButton.setForeground(Color.white);
        multiplicationButton.setForeground(Color.white);
        divisionButton.setForeground(Color.white);
        secondPowerButton.setForeground(Color.white);
        squareRootButton.setForeground(Color.white);
        percentButton.setForeground(Color.white);
        fractionButton.setForeground(Color.white);
        equalsButton.setForeground(Color.white);
        backspaceButton.setForeground(Color.white);
        clearButton.setForeground(Color.white);
        clearEntryButton.setForeground(Color.white);
        signNegationButton.setForeground(Color.white);
    }

    /**
     * Ustawia odpowiednie tła przyciskom
     */
    private void setButtonsBackgrounds() {

        Color numbersColor = new Color(6,6,6);
        Color operationsColor = new Color(19,19,19);
        Color equalsColor = new Color(19,67,105);

        zeroButton.setBackground(numbersColor);
        oneButton.setBackground(numbersColor);
        twoButton.setBackground(numbersColor);
        threeButton.setBackground(numbersColor);
        fourButton.setBackground(numbersColor);
        fiveButton.setBackground(numbersColor);
        sixButton.setBackground(numbersColor);
        sevenButton.setBackground(numbersColor);
        eightButton.setBackground(numbersColor);
        nineButton.setBackground(numbersColor);
        dotButton.setBackground(numbersColor);
        plusButton.setBackground(operationsColor);
        minusButton.setBackground(operationsColor);
        multiplicationButton.setBackground(operationsColor);
        divisionButton.setBackground(operationsColor);
        secondPowerButton.setBackground(operationsColor);
        squareRootButton.setBackground(operationsColor);
        percentButton.setBackground(operationsColor);
        fractionButton.setBackground(operationsColor);
        equalsButton.setBackground(equalsColor);
        backspaceButton.setBackground(operationsColor);
        clearButton.setBackground(operationsColor);
        clearEntryButton.setBackground(operationsColor);
        signNegationButton.setBackground(numbersColor);
    }

    /**
     * Ustawia odpowiednie czcionki przyciskom
     */
    private void setButtonsFonts() {

        Font numbersFont = new Font("Arial", Font.BOLD, 18);
        Font signFont = new Font("Times New Roman", Font.PLAIN, 28);
        Font remainSignFont = new Font("Times New Roman", Font.ITALIC, 18);

        zeroButton.setFont(numbersFont);
        oneButton.setFont(numbersFont);
        twoButton.setFont(numbersFont);
        threeButton.setFont(numbersFont);
        fourButton.setFont(numbersFont);
        fiveButton.setFont(numbersFont);
        sixButton.setFont(numbersFont);
        sevenButton.setFont(numbersFont);
        eightButton.setFont(numbersFont);
        nineButton.setFont(numbersFont);
        dotButton.setFont(signFont);
        plusButton.setFont(signFont);
        minusButton.setFont(signFont);
        multiplicationButton.setFont(signFont);
        divisionButton.setFont(signFont);
        secondPowerButton.setFont(remainSignFont);
        squareRootButton.setFont(remainSignFont);
        percentButton.setFont(new Font("Arial", Font.PLAIN, 18));
        fractionButton.setFont(new Font("Times New Roman", Font.ITALIC, 25));
        equalsButton.setFont(new Font("Times New Roman", Font.BOLD, 28));
        backspaceButton.setFont(new Font("Default", Font.PLAIN, 15));
        clearButton.setFont(new Font("Arial", Font.PLAIN, 15));
        clearEntryButton.setFont(new Font("Arial", Font.PLAIN, 15));
        signNegationButton.setFont(new Font("Times New Roman", Font.PLAIN, 22));
    }

    /**
     * Wyłącza rysowanie ramek wokół przycisków
     */
    private void setButtonsBorderPainted() {

        zeroButton.setBorderPainted(false);
        oneButton.setBorderPainted(false);
        twoButton.setBorderPainted(false);
        threeButton.setBorderPainted(false);
        fourButton.setBorderPainted(false);
        fiveButton.setBorderPainted(false);
        sixButton.setBorderPainted(false);
        sevenButton.setBorderPainted(false);
        eightButton.setBorderPainted(false);
        nineButton.setBorderPainted(false);
        dotButton.setBorderPainted(false);
        plusButton.setBorderPainted(false);
        minusButton.setBorderPainted(false);
        multiplicationButton.setBorderPainted(false);
        divisionButton.setBorderPainted(false);
        secondPowerButton.setBorderPainted(false);
        squareRootButton.setBorderPainted(false);
        percentButton.setBorderPainted(false);
        fractionButton.setBorderPainted(false);
        equalsButton.setBorderPainted(false);
        backspaceButton.setBorderPainted(false);
        clearButton.setBorderPainted(false);
        clearEntryButton.setBorderPainted(false);
        signNegationButton.setBorderPainted(false);
    }

    /**
     * Wyłącza zaznaczenie przycisków po otrzymaniu focusa
     */
    private void setButtonsFocusPainted() {

        zeroButton.setFocusPainted(false);
        oneButton.setFocusPainted(false);
        twoButton.setFocusPainted(false);
        threeButton.setFocusPainted(false);
        fourButton.setFocusPainted(false);
        fiveButton.setFocusPainted(false);
        sixButton.setFocusPainted(false);
        sevenButton.setFocusPainted(false);
        eightButton.setFocusPainted(false);
        nineButton.setFocusPainted(false);
        dotButton.setFocusPainted(false);
        plusButton.setFocusPainted(false);
        minusButton.setFocusPainted(false);
        multiplicationButton.setFocusPainted(false);
        divisionButton.setFocusPainted(false);
        secondPowerButton.setFocusPainted(false);
        squareRootButton.setFocusPainted(false);
        percentButton.setFocusPainted(false);
        fractionButton.setFocusPainted(false);
        equalsButton.setFocusPainted(false);
        backspaceButton.setFocusPainted(false);
        clearButton.setFocusPainted(false);
        clearEntryButton.setFocusPainted(false);
        signNegationButton.setFocusPainted(false);
    }

    /**
     * Dodaje komponenty przycisków do otrzymanego Panelu
     */
    private void addButtonsToPanel(JPanel keyPanel) {

        keyPanel.add(percentButton);
        keyPanel.add(clearEntryButton);
        keyPanel.add(clearButton);
        keyPanel.add(backspaceButton);
        keyPanel.add(fractionButton);
        keyPanel.add(secondPowerButton);
        keyPanel.add(squareRootButton);
        keyPanel.add(divisionButton);
        keyPanel.add(sevenButton);
        keyPanel.add(eightButton);
        keyPanel.add(nineButton);
        keyPanel.add(multiplicationButton);
        keyPanel.add(fourButton);
        keyPanel.add(fiveButton);
        keyPanel.add(sixButton);
        keyPanel.add(minusButton);
        keyPanel.add(oneButton);
        keyPanel.add(twoButton);
        keyPanel.add(threeButton);
        keyPanel.add(plusButton);
        keyPanel.add(signNegationButton);
        keyPanel.add(zeroButton);
        keyPanel.add(dotButton);
        keyPanel.add(equalsButton);
    }

    /**
     * Dodaje listenery na komponenty przycisków
     */
    public void addListeners(ActionListener actionListener, KeyListener keyListener, MouseListener mouseListener) {

        zeroButton.addActionListener(actionListener);
        oneButton.addActionListener(actionListener);
        twoButton.addActionListener(actionListener);
        threeButton.addActionListener(actionListener);
        fourButton.addActionListener(actionListener);
        fiveButton.addActionListener(actionListener);
        sixButton.addActionListener(actionListener);
        sevenButton.addActionListener(actionListener);
        eightButton.addActionListener(actionListener);
        nineButton.addActionListener(actionListener);
        dotButton.addActionListener(actionListener);
        plusButton.addActionListener(actionListener);
        minusButton.addActionListener(actionListener);
        multiplicationButton.addActionListener(actionListener);
        divisionButton.addActionListener(actionListener);
        secondPowerButton.addActionListener(actionListener);
        squareRootButton.addActionListener(actionListener);
        percentButton.addActionListener(actionListener);
        fractionButton.addActionListener(actionListener);
        equalsButton.addActionListener(actionListener);
        backspaceButton.addActionListener(actionListener);
        clearButton.addActionListener(actionListener);
        clearEntryButton.addActionListener(actionListener);
        signNegationButton.addActionListener(actionListener);

        zeroButton.addKeyListener(keyListener);
        oneButton.addKeyListener(keyListener);
        twoButton.addKeyListener(keyListener);
        threeButton.addKeyListener(keyListener);
        fourButton.addKeyListener(keyListener);
        fiveButton.addKeyListener(keyListener);
        sixButton.addKeyListener(keyListener);
        sevenButton.addKeyListener(keyListener);
        eightButton.addKeyListener(keyListener);
        nineButton.addKeyListener(keyListener);
        dotButton.addKeyListener(keyListener);
        plusButton.addKeyListener(keyListener);
        minusButton.addKeyListener(keyListener);
        multiplicationButton.addKeyListener(keyListener);
        divisionButton.addKeyListener(keyListener);
        equalsButton.addKeyListener(keyListener);
        backspaceButton.addKeyListener(keyListener);
        clearEntryButton.addKeyListener(keyListener);

        zeroButton.addMouseListener(mouseListener);
        oneButton.addMouseListener(mouseListener);
        twoButton.addMouseListener(mouseListener);
        threeButton.addMouseListener(mouseListener);
        fourButton.addMouseListener(mouseListener);
        fiveButton.addMouseListener(mouseListener);
        sixButton.addMouseListener(mouseListener);
        sevenButton.addMouseListener(mouseListener);
        eightButton.addMouseListener(mouseListener);
        nineButton.addMouseListener(mouseListener);
        dotButton.addMouseListener(mouseListener);
        plusButton.addMouseListener(mouseListener);
        minusButton.addMouseListener(mouseListener);
        multiplicationButton.addMouseListener(mouseListener);
        divisionButton.addMouseListener(mouseListener);
        secondPowerButton.addMouseListener(mouseListener);
        squareRootButton.addMouseListener(mouseListener);
        percentButton.addMouseListener(mouseListener);
        fractionButton.addMouseListener(mouseListener);
        equalsButton.addMouseListener(mouseListener);
        backspaceButton.addMouseListener(mouseListener);
        clearButton.addMouseListener(mouseListener);
        clearEntryButton.addMouseListener(mouseListener);
        signNegationButton.addMouseListener(mouseListener);
    }

    public void setOperationLabelText(String text) {
        operationLabel.setText(text);
    }

    public void setResultLabelText(String text) {
        resultLabel.setText(text);
    }

    public JButton getZero() {
        return zeroButton;
    }

    public JButton getOne() {
        return oneButton;
    }

    public JButton getTwo() {
        return twoButton;
    }

    public JButton getThree() {
        return threeButton;
    }

    public JButton getFour() {
        return fourButton;
    }

    public JButton getFive() {
        return fiveButton;
    }

    public JButton getSix() {
        return sixButton;
    }

    public JButton getSeven() {
        return sevenButton;
    }

    public JButton getEight() {
        return eightButton;
    }

    public JButton getNine() {
        return nineButton;
    }

    public JButton getDot() {
        return dotButton;
    }

    public JButton getPlus() {
        return plusButton;
    }

    public JButton getMinus() {
        return minusButton;
    }

    public JButton getMultiplicationSign() {
        return multiplicationButton;
    }

    public JButton getDivisionSign() {
        return divisionButton;
    }

    public JButton getSecondPower() {
        return secondPowerButton;
    }

    public JButton getSquareRoot() {
        return squareRootButton;
    }

    public JButton getPercent() {
        return percentButton;
    }

    public JButton getFraction() {
        return fractionButton;
    }

    public JButton getEqualSign() {
        return equalsButton;
    }

    public JButton getBackspace() {
        return backspaceButton;
    }

    public JButton getClear() {
        return clearButton;
    }

    public JButton getClearEntry() {
        return clearEntryButton;
    }

    public JButton getChangeSign() {
        return signNegationButton;
    }
}
