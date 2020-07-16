import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CalculatorController implements ActionListener, KeyListener, MouseListener {

    private CalculatorView theView;
    private CalculatorModel theModel;

    public CalculatorController(CalculatorView theView, CalculatorModel theModel) {

        this.theView = theView;
        this.theModel = theModel;

        theView.addListeners(this, this, this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == theView.getZero()) {
            theModel.handleNumbers("0");
        }
        else if (source == theView.getOne()) {
            theModel.handleNumbers("1");
        }
        else if (source == theView.getTwo()) {
            theModel.handleNumbers("2");
        }
        else if (source == theView.getThree()) {
            theModel.handleNumbers("3");
        }
        else if(source == theView.getFour()) {
            theModel.handleNumbers("4");
        }
        else if (source == theView.getFive()) {
            theModel.handleNumbers("5");
        }
        else if (source == theView.getSix()) {
            theModel.handleNumbers("6");
        }
        else if (source == theView.getSeven()) {
            theModel.handleNumbers("7");
        }
        else if (source == theView.getEight()) {
            theModel.handleNumbers("8");
        }
        else if (source == theView.getNine()) {
            theModel.handleNumbers("9");
        }

        else if (source == theView.getPlus()) {
            theModel.handleOperationsSigns("+");
        }
        else if (source == theView.getMinus()) {
            theModel.handleOperationsSigns("-");
        }
        else if (source == theView.getMultiplicationSign()) {
            theModel.handleOperationsSigns("×");
        }
        else if (source == theView.getDivisionSign()) {
            theModel.handleOperationsSigns("÷");
        }
        else if (source == theView.getSecondPower()) {
            theModel.handleOperationsSigns("pow");
        }
        else if (source == theView.getSquareRoot()) {
            theModel.handleOperationsSigns("sqrt");
        }

        else if (source == theView.getEqualSign()) {
            theModel.handleEqualSign();
        }
        else if (source == theView.getDot()) {
            theModel.handleNumbers(".");
        }
        else if (source == theView.getBackspace()) {
            theModel.removeLastNumber();
        }
        else if (source == theView.getClear()) {
            theModel.removeAllNumbers();
        }

        theView.setOperationLabelText(theModel.getOperationLabelText());
        theView.setResultLabelText(theModel.getResultLabelText());
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        Color numbersColor = new Color(6,6,6);
        Color operationsColor = new Color(19,19,19);
        Color equalsColor = new Color(19,67,105);

        if (key == KeyEvent.VK_0 || key == KeyEvent.VK_NUMPAD0) {
            theModel.handleNumbers("0");
            new Thread(() -> theModel.hightlightButton(theView.getZero(), numbersColor)).start();
        }
        else if (key == KeyEvent.VK_1 || key == KeyEvent.VK_NUMPAD1) {
            theModel.handleNumbers("1");
            new Thread(() -> theModel.hightlightButton(theView.getOne(), numbersColor)).start();
        }
        else if (key == KeyEvent.VK_2 || key == KeyEvent.VK_NUMPAD2) {
            theModel.handleNumbers("2");
            new Thread(() -> theModel.hightlightButton(theView.getTwo(), numbersColor)).start();
        }
        else if (key == KeyEvent.VK_3 || key == KeyEvent.VK_NUMPAD3) {
            theModel.handleNumbers("3");
            new Thread(() -> theModel.hightlightButton(theView.getThree(), numbersColor)).start();
        }
        else if(key == KeyEvent.VK_4 || key == KeyEvent.VK_NUMPAD4) {
            theModel.handleNumbers("4");
            new Thread(() -> theModel.hightlightButton(theView.getFour(), numbersColor)).start();
        }
        else if (key == KeyEvent.VK_5 || key == KeyEvent.VK_NUMPAD5) {
            theModel.handleNumbers("5");
            new Thread(() -> theModel.hightlightButton(theView.getFive(), numbersColor)).start();
        }
        else if (key == KeyEvent.VK_6 || key == KeyEvent.VK_NUMPAD6) {
            theModel.handleNumbers("6");
            new Thread(() -> theModel.hightlightButton(theView.getSix(), numbersColor)).start();
        }
        else if (key == KeyEvent.VK_7 || key == KeyEvent.VK_NUMPAD7) {
            theModel.handleNumbers("7");
            new Thread(() -> theModel.hightlightButton(theView.getSeven(), numbersColor)).start();
        }
        else if ((key == KeyEvent.VK_8 && !e.isShiftDown()) || key == KeyEvent.VK_NUMPAD8) {
            theModel.handleNumbers("8");
            new Thread(() -> theModel.hightlightButton(theView.getEight(), numbersColor)).start();
        }
        else if (key == KeyEvent.VK_9 || key == KeyEvent.VK_NUMPAD9) {
            theModel.handleNumbers("9");
            new Thread(() -> theModel.hightlightButton(theView.getNine(), numbersColor)).start();
        }

        else if (key == KeyEvent.VK_ADD || (e.isShiftDown() && key == KeyEvent.VK_EQUALS)) {
            theModel.handleOperationsSigns("+");
            new Thread(() -> theModel.hightlightButton(theView.getPlus(), operationsColor)).start();
        }
        else if (key == KeyEvent.VK_MINUS || key == KeyEvent.VK_SUBTRACT) {
            theModel.handleOperationsSigns("-");
            new Thread(() -> theModel.hightlightButton(theView.getMinus(), operationsColor)).start();
        }
        else if (key == KeyEvent.VK_MULTIPLY || (e.isShiftDown() && key == KeyEvent.VK_8)) {
            theModel.handleOperationsSigns("×");
            new Thread(() -> theModel.hightlightButton(theView.getMultiplicationSign(), operationsColor)).start();
        }
        else if (key == KeyEvent.VK_DIVIDE) {
            theModel.handleOperationsSigns("÷");
            new Thread(() -> theModel.hightlightButton(theView.getDivisionSign(), operationsColor)).start();
        }

        else if ((key == KeyEvent.VK_EQUALS && !e.isShiftDown()) || key == KeyEvent.VK_ENTER) {
            theModel.handleEqualSign();
            new Thread(() -> theModel.hightlightButton(theView.getEqualSign(), equalsColor)).start();
        }
        else if (key == KeyEvent.VK_DECIMAL) {
            theModel.handleNumbers(".");
            new Thread(() -> theModel.hightlightButton(theView.getDot(), operationsColor)).start();
        }
        else if (key == KeyEvent.VK_BACK_SPACE) {
            theModel.removeLastNumber();
            new Thread(() -> theModel.hightlightButton(theView.getBackspace(), operationsColor)).start();
        }
        else if (key == KeyEvent.VK_DELETE) {
            theModel.removeAllNumbers();
            new Thread(() -> theModel.hightlightButton(theView.getClear(), operationsColor)).start();
        }

        theView.setOperationLabelText(theModel.getOperationLabelText());
        theView.setResultLabelText(theModel.getResultLabelText());
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        Object source = e.getSource();
        Color selectButtonColor = new Color(70,70,70);

        if (source == theView.getZero()) {
            theView.getZero().setBackground(selectButtonColor);
        }
        else if (source == theView.getOne()) {
            theView.getOne().setBackground(selectButtonColor);
        }
        else if (source == theView.getTwo()) {
            theView.getTwo().setBackground(selectButtonColor);
        }
        else if (source == theView.getThree()) {
            theView.getThree().setBackground(selectButtonColor);
        }
        else if(source == theView.getFour()) {
            theView.getFour().setBackground(selectButtonColor);
        }
        else if (source == theView.getFive()) {
            theView.getFive().setBackground(selectButtonColor);
        }
        else if (source == theView.getSix()) {
            theView.getSix().setBackground(selectButtonColor);
        }
        else if (source == theView.getSeven()) {
            theView.getSeven().setBackground(selectButtonColor);
        }
        else if (source == theView.getEight()) {
            theView.getEight().setBackground(selectButtonColor);
        }
        else if (source == theView.getNine()) {
            theView.getNine().setBackground(selectButtonColor);
        }

        else if (source == theView.getPlus()) {
            theView.getPlus().setBackground(selectButtonColor);
        }
        else if (source == theView.getMinus()) {
            theView.getMinus().setBackground(selectButtonColor);
        }
        else if (source == theView.getMultiplicationSign()) {
            theView.getMultiplicationSign().setBackground(selectButtonColor);
        }
        else if (source == theView.getDivisionSign()) {
            theView.getDivisionSign().setBackground(selectButtonColor);
        }
        else if (source == theView.getSecondPower()) {
            theView.getSecondPower().setBackground(selectButtonColor);
        }
        else if (source == theView.getSquareRoot()) {
            theView.getSquareRoot().setBackground(selectButtonColor);
        }

        else if (source == theView.getEqualSign()) {
            theView.getEqualSign().setBackground(selectButtonColor);
        }
        else if (source == theView.getDot()) {
            theView.getDot().setBackground(selectButtonColor);
        }
        else if (source == theView.getBackspace()) {
            theView.getBackspace().setBackground(selectButtonColor);
        }
        else if (source == theView.getClear()) {
            theView.getClear().setBackground(selectButtonColor);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {

        Object source = e.getSource();
        Color numbersColor = new Color(6,6,6);
        Color operationsColor = new Color(19,19,19);
        Color equalsColor = new Color(19,67,105);

        if (source == theView.getZero()) {
            theView.getZero().setBackground(numbersColor);
        }
        else if (source == theView.getOne()) {
            theView.getOne().setBackground(numbersColor);
        }
        else if (source == theView.getTwo()) {
            theView.getTwo().setBackground(numbersColor);
        }
        else if (source == theView.getThree()) {
            theView.getThree().setBackground(numbersColor);
        }
        else if(source == theView.getFour()) {
            theView.getFour().setBackground(numbersColor);
        }
        else if (source == theView.getFive()) {
            theView.getFive().setBackground(numbersColor);
        }
        else if (source == theView.getSix()) {
            theView.getSix().setBackground(numbersColor);
        }
        else if (source == theView.getSeven()) {
            theView.getSeven().setBackground(numbersColor);
        }
        else if (source == theView.getEight()) {
            theView.getEight().setBackground(numbersColor);
        }
        else if (source == theView.getNine()) {
            theView.getNine().setBackground(numbersColor);
        }

        else if (source == theView.getPlus()) {
            theView.getPlus().setBackground(operationsColor);
        }
        else if (source == theView.getMinus()) {
            theView.getMinus().setBackground(operationsColor);
        }
        else if (source == theView.getMultiplicationSign()) {
            theView.getMultiplicationSign().setBackground(operationsColor);
        }
        else if (source == theView.getDivisionSign()) {
            theView.getDivisionSign().setBackground(operationsColor);
        }
        else if (source == theView.getSecondPower()) {
            theView.getSecondPower().setBackground(operationsColor);
        }
        else if (source == theView.getSquareRoot()) {
            theView.getSquareRoot().setBackground(operationsColor);
        }

        else if (source == theView.getEqualSign()) {
            theView.getEqualSign().setBackground(equalsColor);
        }
        else if (source == theView.getDot()) {
            theView.getDot().setBackground(numbersColor);
        }
        else if (source == theView.getBackspace()) {
            theView.getBackspace().setBackground(operationsColor);
        }
        else if (source == theView.getClear()) {
            theView.getClear().setBackground(numbersColor);
        }
    }
}
