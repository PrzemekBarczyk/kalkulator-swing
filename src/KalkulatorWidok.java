import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class KalkulatorWidok {

    private int WINDOW_WIDTH = 400;
    private JFrame frame;

    public KalkulatorWidok() {

        frame = new JFrame();
        frame.setTitle("Kalkulator");
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.PAGE_AXIS));
        frame.setMinimumSize(new Dimension(WINDOW_WIDTH, 520)); // Ramka wyższa o 20p od Panelu

        addComponentsToPane();

        frame.pack();
        frame.setVisible(true);
    }

    private void addComponentsToPane() {
        JLabel resultLabel = new JLabel("0");
        resultLabel.setPreferredSize(new Dimension(WINDOW_WIDTH, 100));
        resultLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 40));

        JPanel keyPanel = new JPanel();
        keyPanel.setLayout(new GridLayout(5, 4, 3, 3));
        keyPanel.setPreferredSize(new Dimension(WINDOW_WIDTH, 400));
        keyPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);

        Font numbersFont = new Font("Arial", Font.BOLD, 20);
        Font signFont = new Font("Arial", Font.PLAIN, 30);

        JButton zero = new JButton("0");
        JButton one = new JButton("1");
        JButton two = new JButton("2");
        JButton three = new JButton("3");
        JButton four = new JButton("4");
        JButton five = new JButton("5");
        JButton six = new JButton("6");
        JButton seven = new JButton("7");
        JButton eight = new JButton("8");
        JButton nine = new JButton("9");
        JButton plus = new JButton("+");
        JButton minus = new JButton("-");
        JButton multiplicationSign = new JButton("x");
        JButton divisionSign = new JButton("/");
        JButton secondPower = new JButton("pow");
        JButton squareRoot = new JButton("sqrt");
        JButton equalSign = new JButton("=");
        JButton dot = new JButton(".");
        JButton backspace = new JButton("backspace");
        JButton clearAll = new JButton("clear");

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
        equalSign.setFont(signFont);
        equalSign.setFont(signFont);
        dot.setFont(signFont);

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
        keyPanel.add(clearAll);
        keyPanel.add(zero);
        keyPanel.add(dot);
        keyPanel.add(equalSign);

        frame.getContentPane().add(resultLabel);
        frame.getContentPane().add(keyPanel);
    }
}
