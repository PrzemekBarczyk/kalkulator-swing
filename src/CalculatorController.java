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

        theView.addListeners(this, this);
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
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_0 || key == KeyEvent.VK_NUMPAD0) {
            theModel.handleNumbers("0");
        }
        else if (key == KeyEvent.VK_1 || key == KeyEvent.VK_NUMPAD1) {
            theModel.handleNumbers("1");
        }
        else if (key == KeyEvent.VK_2 || key == KeyEvent.VK_NUMPAD2) {
            theModel.handleNumbers("2");
        }
        else if (key == KeyEvent.VK_3 || key == KeyEvent.VK_NUMPAD3) {
            theModel.handleNumbers("3");
        }
        else if(key == KeyEvent.VK_4 || key == KeyEvent.VK_NUMPAD4) {
            theModel.handleNumbers("4");
        }
        else if (key == KeyEvent.VK_5 || key == KeyEvent.VK_NUMPAD5) {
            theModel.handleNumbers("5");
        }
        else if (key == KeyEvent.VK_6 || key == KeyEvent.VK_NUMPAD6) {
            theModel.handleNumbers("6");
        }
        else if (key == KeyEvent.VK_7 || key == KeyEvent.VK_NUMPAD7) {
            theModel.handleNumbers("7");
        }
        else if ((key == KeyEvent.VK_8 && !e.isShiftDown()) || key == KeyEvent.VK_NUMPAD8) {
            theModel.handleNumbers("8");
        }
        else if (key == KeyEvent.VK_9 || key == KeyEvent.VK_NUMPAD9) {
            theModel.handleNumbers("9");
        }

        else if (key == KeyEvent.VK_ADD || (e.isShiftDown() && key == KeyEvent.VK_EQUALS)) {
            theModel.handleOperationsSigns("+");
        }
        else if (key == KeyEvent.VK_MINUS || key == KeyEvent.VK_SUBTRACT) {
            theModel.handleOperationsSigns("-");
        }
        else if (key == KeyEvent.VK_MULTIPLY || (e.isShiftDown() && key == KeyEvent.VK_8)) {
            theModel.handleOperationsSigns("*");
        }
        else if (key == KeyEvent.VK_DIVIDE) {
            theModel.handleOperationsSigns("/");
        }

        else if ((key == KeyEvent.VK_EQUALS && !e.isShiftDown()) || key == KeyEvent.VK_ENTER) {
            theModel.handleEqualSign();
        }
        else if (key == KeyEvent.VK_DECIMAL) {
            theModel.handleNumbers(".");
        }
        else if (key == KeyEvent.VK_BACK_SPACE) {
            theModel.removeLastNumber();
        }
        else if (key == KeyEvent.VK_DELETE) {
            theModel.removeAllNumbers();
        }

        theView.setOperationLabelText(theModel.getOperationLabelText());
        theView.setResultLabelText(theModel.getResultLabelText());
    }
}
