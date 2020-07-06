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
            theModel.handleOperationsSigns("*");
        }
        else if (source == theView.getDivisionSign()) {
            theModel.handleOperationsSigns("/");
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

    }
}
