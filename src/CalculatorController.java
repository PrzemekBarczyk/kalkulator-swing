import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class CalculatorController implements ActionListener, KeyListener {

    private CalculatorView theView;
    private CalculatorModel theModel;

    public CalculatorController(CalculatorView theView, CalculatorModel theModel) {

        this.theView = theView;
        this.theModel = theModel;

        theView.addListeners(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == theView.getZero()) {
            theModel.addNumberToResultLabel("0");
        }
        else if (source == theView.getOne()) {
            theModel.addNumberToResultLabel("1");
        }
        else if (source == theView.getTwo()) {
            theModel.addNumberToResultLabel("2");
        }
        else if (source == theView.getThree()) {
            theModel.addNumberToResultLabel("3");
        }
        else if(source == theView.getFour()) {
            theModel.addNumberToResultLabel("4");
        }
        else if (source == theView.getFive()) {
            theModel.addNumberToResultLabel("5");
        }
        else if (source == theView.getSix()) {
            theModel.addNumberToResultLabel("6");
        }
        else if (source == theView.getSeven()) {
            theModel.addNumberToResultLabel("7");
        }
        else if (source == theView.getEight()) {
            theModel.addNumberToResultLabel("8");
        }
        else if (source == theView.getNine()) {
            theModel.addNumberToResultLabel("9");
        }

        else if (source == theView.getPlus()) {
            theModel.addSignToOperationLabel("+");
        }
        else if (source == theView.getMinus()) {
            theModel.addSignToOperationLabel("-");
        }
        else if (source == theView.getMultiplicationSign()) {
            theModel.addSignToOperationLabel("*");
        }
        else if (source == theView.getDivisionSign()) {
            theModel.addSignToOperationLabel("/");
        }
        else if (source == theView.getSecondPower()) {
            theModel.addSignToOperationLabel("pow");
        }
        else if (source == theView.getSquareRoot()) {
            theModel.addSignToOperationLabel("sqrt");
        }

        else if (source == theView.getEqualSign()) {
            theModel.calculateResult();
        }
        else if (source == theView.getDot()) {
            theModel.addNumberToResultLabel(".");
        }
        else if (source == theView.getBackspace()) {
            theModel.removeOneNumber();
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

    }
}
