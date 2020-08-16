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
        } else if (source == theView.getOne()) {
            theModel.handleNumbers("1");
        } else if (source == theView.getTwo()) {
            theModel.handleNumbers("2");
        } else if (source == theView.getThree()) {
            theModel.handleNumbers("3");
        } else if(source == theView.getFour()) {
            theModel.handleNumbers("4");
        } else if (source == theView.getFive()) {
            theModel.handleNumbers("5");
        } else if (source == theView.getSix()) {
            theModel.handleNumbers("6");
        } else if (source == theView.getSeven()) {
            theModel.handleNumbers("7");
        } else if (source == theView.getEight()) {
            theModel.handleNumbers("8");
        } else if (source == theView.getNine()) {
            theModel.handleNumbers("9");
        } else if (source == theView.getDot()) {
            theModel.handleNumbers(".");
        }

        else if (source == theView.getPlus()) {
            theModel.handleBasicOperations("+");
        } else if (source == theView.getMinus()) {
            theModel.handleBasicOperations("-");
        } else if (source == theView.getMultiplicationSign()) {
            theModel.handleBasicOperations("×");
        } else if (source == theView.getDivisionSign()) {
            theModel.handleBasicOperations("÷");
        }

        else if (source == theView.getSecondPower()) {
            theModel.handlePowerAndSqrt("sqrt");
        } else if (source == theView.getSquareRoot()) {
            theModel.handlePowerAndSqrt("√");
        } else if (source == theView.getPercent()) {
            theModel.handlePercent();
        } else if (source == theView.getFraction()) {
            theModel.handleFraction();
        }

        else if (source == theView.getEqualSign()) {
            theModel.handleEqualSign();
        } else if (source == theView.getBackspace()) {
            theModel.handleBackspace();
        } else if (source == theView.getClear()) {
            theModel.handleClear();
        } else if (source == theView.getClearEntry()) {
            theModel.handleClearEntry();
        } else if (source == theView.getChangeSign()) {
            theModel.handleSignNegation();
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
            new Thread(() -> theModel.hightlighButton(theView.getZero(), numbersColor)).start();
        }
        else if (key == KeyEvent.VK_1 || key == KeyEvent.VK_NUMPAD1) {
            theModel.handleNumbers("1");
            new Thread(() -> theModel.hightlighButton(theView.getOne(), numbersColor)).start();
        }
        else if (key == KeyEvent.VK_2 || key == KeyEvent.VK_NUMPAD2) {
            theModel.handleNumbers("2");
            new Thread(() -> theModel.hightlighButton(theView.getTwo(), numbersColor)).start();
        }
        else if (key == KeyEvent.VK_3 || key == KeyEvent.VK_NUMPAD3) {
            theModel.handleNumbers("3");
            new Thread(() -> theModel.hightlighButton(theView.getThree(), numbersColor)).start();
        }
        else if(key == KeyEvent.VK_4 || key == KeyEvent.VK_NUMPAD4) {
            theModel.handleNumbers("4");
            new Thread(() -> theModel.hightlighButton(theView.getFour(), numbersColor)).start();
        }
        else if (key == KeyEvent.VK_5 || key == KeyEvent.VK_NUMPAD5) {
            theModel.handleNumbers("5");
            new Thread(() -> theModel.hightlighButton(theView.getFive(), numbersColor)).start();
        }
        else if (key == KeyEvent.VK_6 || key == KeyEvent.VK_NUMPAD6) {
            theModel.handleNumbers("6");
            new Thread(() -> theModel.hightlighButton(theView.getSix(), numbersColor)).start();
        }
        else if (key == KeyEvent.VK_7 || key == KeyEvent.VK_NUMPAD7) {
            theModel.handleNumbers("7");
            new Thread(() -> theModel.hightlighButton(theView.getSeven(), numbersColor)).start();
        }
        else if ((key == KeyEvent.VK_8 && !e.isShiftDown()) || key == KeyEvent.VK_NUMPAD8) {
            theModel.handleNumbers("8");
            new Thread(() -> theModel.hightlighButton(theView.getEight(), numbersColor)).start();
        }
        else if (key == KeyEvent.VK_9 || key == KeyEvent.VK_NUMPAD9) {
            theModel.handleNumbers("9");
            new Thread(() -> theModel.hightlighButton(theView.getNine(), numbersColor)).start();
        }
        else if (key == KeyEvent.VK_DECIMAL) {
            theModel.handleNumbers(".");
            new Thread(() -> theModel.hightlighButton(theView.getDot(), operationsColor)).start();
        }

        else if (key == KeyEvent.VK_ADD || (e.isShiftDown() && key == KeyEvent.VK_EQUALS)) {
            theModel.handleBasicOperations("+");
            new Thread(() -> theModel.hightlighButton(theView.getPlus(), operationsColor)).start();
        }
        else if (key == KeyEvent.VK_MINUS || key == KeyEvent.VK_SUBTRACT) {
            theModel.handleBasicOperations("-");
            new Thread(() -> theModel.hightlighButton(theView.getMinus(), operationsColor)).start();
        }
        else if (key == KeyEvent.VK_MULTIPLY || (e.isShiftDown() && key == KeyEvent.VK_8)) {
            theModel.handleBasicOperations("×");
            new Thread(() -> theModel.hightlighButton(theView.getMultiplicationSign(), operationsColor)).start();
        }
        else if (key == KeyEvent.VK_DIVIDE) {
            theModel.handleBasicOperations("÷");
            new Thread(() -> theModel.hightlighButton(theView.getDivisionSign(), operationsColor)).start();
        }

        else if ((key == KeyEvent.VK_EQUALS && !e.isShiftDown()) || key == KeyEvent.VK_ENTER) {
            theModel.handleEqualSign();
            new Thread(() -> theModel.hightlighButton(theView.getEqualSign(), equalsColor)).start();
        }
        else if (key == KeyEvent.VK_BACK_SPACE) {
            theModel.handleBackspace();
            new Thread(() -> theModel.hightlighButton(theView.getBackspace(), operationsColor)).start();
        }
        else if (key == KeyEvent.VK_DELETE) {
            theModel.handleClearEntry();
            new Thread(() -> theModel.hightlighButton(theView.getClearEntry(), operationsColor)).start();
        }

        theView.setOperationLabelText(theModel.getOperationLabelText());
        theView.setResultLabelText(theModel.getResultLabelText());
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    }

    @Override
    public void mouseEntered(MouseEvent e) {

        Object source = e.getSource();
        Color selectButtonColor = new Color(70,70,70);

        if (source == theView.getZero()) {
            theView.getZero().setBackground(selectButtonColor);
        } else if (source == theView.getOne()) {
            theView.getOne().setBackground(selectButtonColor);
        } else if (source == theView.getTwo()) {
            theView.getTwo().setBackground(selectButtonColor);
        } else if (source == theView.getThree()) {
            theView.getThree().setBackground(selectButtonColor);
        } else if(source == theView.getFour()) {
            theView.getFour().setBackground(selectButtonColor);
        } else if (source == theView.getFive()) {
            theView.getFive().setBackground(selectButtonColor);
        } else if (source == theView.getSix()) {
            theView.getSix().setBackground(selectButtonColor);
        } else if (source == theView.getSeven()) {
            theView.getSeven().setBackground(selectButtonColor);
        } else if (source == theView.getEight()) {
            theView.getEight().setBackground(selectButtonColor);
        } else if (source == theView.getNine()) {
            theView.getNine().setBackground(selectButtonColor);
        } else if (source == theView.getDot()) {
            theView.getDot().setBackground(selectButtonColor);
        }

        else if (source == theView.getPlus()) {
            theView.getPlus().setBackground(selectButtonColor);
        } else if (source == theView.getMinus()) {
            theView.getMinus().setBackground(selectButtonColor);
        } else if (source == theView.getMultiplicationSign()) {
            theView.getMultiplicationSign().setBackground(selectButtonColor);
        } else if (source == theView.getDivisionSign()) {
            theView.getDivisionSign().setBackground(selectButtonColor);
        } else if (source == theView.getSecondPower()) {
            theView.getSecondPower().setBackground(selectButtonColor);
        } else if (source == theView.getSquareRoot()) {
            theView.getSquareRoot().setBackground(selectButtonColor);
        } else if (source == theView.getPercent()) {
            theView.getPercent().setBackground(selectButtonColor);
        } else if (source == theView.getFraction()) {
            theView.getFraction().setBackground(selectButtonColor);
        }

        else if (source == theView.getEqualSign()) {
            theView.getEqualSign().setBackground(selectButtonColor);
        } else if (source == theView.getBackspace()) {
            theView.getBackspace().setBackground(selectButtonColor);
        } else if (source == theView.getClear()) {
            theView.getClear().setBackground(selectButtonColor);
        } else if (source == theView.getClearEntry()) {
            theView.getClearEntry().setBackground(selectButtonColor);
        } else if (source == theView.getChangeSign()) {
            theView.getChangeSign().setBackground(selectButtonColor);
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
        } else if (source == theView.getOne()) {
            theView.getOne().setBackground(numbersColor);
        } else if (source == theView.getTwo()) {
            theView.getTwo().setBackground(numbersColor);
        } else if (source == theView.getThree()) {
            theView.getThree().setBackground(numbersColor);
        } else if(source == theView.getFour()) {
            theView.getFour().setBackground(numbersColor);
        } else if (source == theView.getFive()) {
            theView.getFive().setBackground(numbersColor);
        } else if (source == theView.getSix()) {
            theView.getSix().setBackground(numbersColor);
        } else if (source == theView.getSeven()) {
            theView.getSeven().setBackground(numbersColor);
        } else if (source == theView.getEight()) {
            theView.getEight().setBackground(numbersColor);
        } else if (source == theView.getNine()) {
            theView.getNine().setBackground(numbersColor);
        } else if (source == theView.getDot()) {
            theView.getDot().setBackground(numbersColor);
        }

        else if (source == theView.getPlus()) {
            theView.getPlus().setBackground(operationsColor);
        } else if (source == theView.getMinus()) {
            theView.getMinus().setBackground(operationsColor);
        } else if (source == theView.getMultiplicationSign()) {
            theView.getMultiplicationSign().setBackground(operationsColor);
        } else if (source == theView.getDivisionSign()) {
            theView.getDivisionSign().setBackground(operationsColor);
        } else if (source == theView.getSecondPower()) {
            theView.getSecondPower().setBackground(operationsColor);
        } else if (source == theView.getSquareRoot()) {
            theView.getSquareRoot().setBackground(operationsColor);
        } else if (source == theView.getPercent()) {
            theView.getPercent().setBackground(operationsColor);
        } else if (source == theView.getFraction()) {
            theView.getFraction().setBackground(operationsColor);
        }

        else if (source == theView.getEqualSign()) {
            theView.getEqualSign().setBackground(equalsColor);
        } else if (source == theView.getBackspace()) {
            theView.getBackspace().setBackground(operationsColor);
        } else if (source == theView.getClear()) {
            theView.getClear().setBackground(operationsColor);
        } else if (source == theView.getClearEntry()) {
            theView.getClearEntry().setBackground(operationsColor);
        } else if (source == theView.getChangeSign()) {
            theView.getChangeSign().setBackground(numbersColor);
        }
    }
}
