import java.awt.Color;
import java.text.DecimalFormat;
import javax.swing.JButton;

public class CalculatorModel {

    private String operationLabelText; // zawartość JLabel z obliczeniami
    private String resultLabelText; // zawartość JLabel z aktualnie wprowadzoną wartością/wynikiem

    private double firstNumber;
    private double secondNumber;
    private String operationSign;
    private boolean choseNumber;
    private boolean choseDot;
    private boolean choseOperationSign; // flaga określająca czy wybrano rodzaj operacji
    private boolean choseEqualSign; // flaga określająca czy zaznaczono =
    private DecimalFormat format; // do usunięcia powtarzających się zer z przodu i końca

    public CalculatorModel() {

        firstNumber = 0;
        secondNumber = 0;
        operationLabelText = "";
        resultLabelText = "0";
        operationSign = "";
        choseNumber = false;
        choseDot = false;
        choseOperationSign = false;
        choseEqualSign = false;
        format = new DecimalFormat("#.###########");
    }

    /**
     * Obsługuje zdarzenie wywołane wyborem dowolnej z cyfr lub przecinka.
     */
    public void handleNumbers(String number) {

        if (resultLabelText.equals("Cannot divide by zero")) { // czyszczenie po dzieleniu przez zero
            removeAllNumbers();
        }

        if (choseOperationSign && choseEqualSign && !choseNumber) { // wyświetlono wynik [2+3=]||[2+3-4=]
            resultLabelText = "0";
            operationLabelText = "";
        }
        else if (!choseNumber) { // wpisywana jest kolejna liczba []||[2+]||[2-3+]||[2=]
            resultLabelText = "0";
        }

        if (number.equals(".") && !choseDot) { // pierwsze pojawienie się '.' (dopisuje kropke do result)
            resultLabelText = resultLabelText + number;
            choseDot = true;
        }
        else if (!number.equals(".")) { // wszystkie cyfry poza '.' (dopisuje nową cyfrę do result)
            resultLabelText = convertToString(convertToDouble(resultLabelText + number));
        }

        choseNumber = true;
    }

    /**
     * Obsługuje zdarzenie wywołane wyborem dowolnego znaku operacji innego niż =.
     */
    public void handleOperationsSigns(String sign) {

        if (resultLabelText.equals("Cannot divide by zero")) { // zablokowanie operacji po dzieleniu przez zero
            return;
        }

        if (choseOperationSign && !choseNumber && !choseEqualSign) { // wybrano kolejny znak pod rząd [2+][2+3-] -> nadpisuje poprzedni znak nowym
            swapSignNumber(sign);
            operationSign = sign;
            return; // nie trzeba ustawiać flag, bo zostały już ustawione dla poprzedniego znaku
        }
        if (choseEqualSign) { // wybrano = [=]||[2=]||[2+3=] (wymagane by poprawnie zadziałała instrukcja poniżej)
            operationLabelText = "";
        }

        operationLabelText = operationLabelText + convertToString(convertToDouble(resultLabelText)) + " " + sign + " ";

        if (choseOperationSign && choseNumber && !choseEqualSign) { // wpisano liczbę, wybrano kolejny znak i nie wybrano = [2+3]->wyznacza wynik
            secondNumber = convertToDouble(resultLabelText);
            executeOperation();
        }

        // wspólne dla wszystkich przypadków
        if (resultLabelText.equals("Cannot divide by zero")) { // sprawdzenie czy nie doszło do dzielenia przez 0
            return;
        }
        firstNumber = convertToDouble(resultLabelText);
        operationSign = sign;
        resultLabelText = convertToString(convertToDouble(resultLabelText));

        choseNumber = false;
        choseDot = false;
        choseOperationSign = true;
        choseEqualSign = false;
    }

    /**
     * Obsługuje zdarzenie wywołane wyborem znaku =.
     */
    public void handleEqualSign() {

        if (resultLabelText.equals("Cannot divide by zero")) { // zabezpieczenie po dzieleniu przez zero
            removeAllNumbers();
            return;
        }

        if (!choseOperationSign) { // wybrano = i nie wybrano żadnego znaku []||[=]||[2]||[2=]
            operationLabelText = resultLabelText + " = ";
            choseNumber = false;
            choseDot = false;
            choseEqualSign = true;
            return;
        }

        // choseOperationSign == True zawsze w tym miejscu
        if (choseEqualSign) { // wybrano znak i = [2+=]||[2+3=]
            firstNumber = convertToDouble(resultLabelText);
            operationLabelText = convertToString(convertToDouble(resultLabelText)) + " " + operationSign + " " + convertToString(secondNumber) + " = ";
        }
        else if (!choseNumber) { // wybrano znak i nie wybrano liczby [+]||[2+]
            secondNumber = convertToDouble(resultLabelText);
            operationLabelText = operationLabelText + convertToString(convertToDouble(resultLabelText)) + " = ";
        }
        else { // wybrano znak i cyfre [2+3]
            secondNumber = convertToDouble(resultLabelText);
            operationLabelText = operationLabelText + convertToString(secondNumber) + " = ";
        }

        executeOperation();

        choseNumber = false;
        choseDot = false;
        choseEqualSign = true;
    }

    public void executeOperation() {
        switch (operationSign) {
            case "+":
                add();
                break;
            case "-":
                subtract();
                break;
            case "×":
                multiply();
                break;
            case "÷":
                divide();
                break;
        }
    }

    public void removeLastNumber() {
        if (resultLabelText.length() == 1) {
            resultLabelText = "0";
        }
        else {
            resultLabelText = resultLabelText.substring(0, resultLabelText.length() - 1);
        }
    }

    public void removeAllNumbers() {
        firstNumber = 0;
        secondNumber = 0;
        operationLabelText = "";
        resultLabelText = "0";
        operationSign = "";
        choseOperationSign = false;
        choseEqualSign = false;
    }

    private void swapSignNumber(String sign) {
        if (operationLabelText.length() > 0) {
            operationLabelText = operationLabelText.substring(0, operationLabelText.length()-2) + sign + " ";
        }
    }

    public double convertToDouble(String number) {
        return Double.parseDouble(number);
    }

    public String convertToString(double number) {
        return format.format(number).replace(",", ".");
    }

    private void add() {
        resultLabelText = convertToString(firstNumber + secondNumber);
    }

    private void subtract() {
        resultLabelText = convertToString(firstNumber - secondNumber);
    }

    private void multiply() {
        resultLabelText = convertToString(firstNumber * secondNumber);
    }

    private void divide() {
        if (secondNumber != 0) { // zabezpieczenie przed dzieleniem przez 0
            resultLabelText = convertToString(firstNumber / secondNumber);
        }
        else {
            resultLabelText = "Cannot divide by zero";
        }
    }

//    private void secondPower() {
//        resultLabelText = convertToString(firstNumber * firstNumber);
//    }
//
//    private void squareRoot() {
//        resultLabelText = convertToString(Math.sqrt(firstNumber));
//    }

    public String getOperationLabelText() {
        return operationLabelText;
    }

    public String getResultLabelText() {
        return resultLabelText;
    }

    public void hightlightButton(JButton button, Color color) {

        Color selectButtonColor = new Color(70,70,70);

        button.setBackground(selectButtonColor);
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        button.setBackground(color);
    }
}
