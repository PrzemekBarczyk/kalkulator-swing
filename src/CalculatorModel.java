import java.awt.Color;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
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
    private boolean dividedByZero; // flaga określająca czy doszło do dzielenia przez 0
    private DecimalFormat formatForResultLabelText; // do usunięcia powtarzających się zer z przodu i końca oraz dodania odstępów
    private DecimalFormat formatForOperationLabelText; // do usunięcia powtarzających się zer z przodu i z końca

    private final int MAX_NUMBERS = 13;

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
        dividedByZero = false;

        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator('.');
        symbols.setGroupingSeparator(' ');

        formatForResultLabelText = new DecimalFormat();
        formatForResultLabelText.setDecimalFormatSymbols(symbols);
        formatForResultLabelText.setGroupingUsed(true);
        formatForResultLabelText.setMaximumIntegerDigits(MAX_NUMBERS);
        formatForResultLabelText.setMaximumFractionDigits(MAX_NUMBERS);
        formatForOperationLabelText = new DecimalFormat();
        formatForOperationLabelText.setDecimalFormatSymbols(symbols);
        formatForOperationLabelText.setGroupingUsed(false);
        formatForOperationLabelText.setMaximumIntegerDigits(MAX_NUMBERS);
        formatForOperationLabelText.setMaximumFractionDigits(MAX_NUMBERS);
    }

    /**
     * Obsługuje zdarzenie wywołane wyborem dowolnej z cyfr lub przecinka.
     */
    public void handleNumbers(String number) {

        // przygotowanie
        if (dividedByZero) { // czyszczenie po dzieleniu przez zero
            removeAllNumbers();
            dividedByZero = false;
        }
        else if (choseOperationSign && choseEqualSign && !choseNumber) { // wyświetlono wynik [2+3=]||[2+3-4=]
            resultLabelText = "0";
            operationLabelText = "";
        }
        else if (!choseNumber) { // wpisywana jest kolejna liczba []||[2+]||[2-3+]||[2=]
            resultLabelText = "0";
        }
        else if (resultLabelText.length() > MAX_NUMBERS) { // blokada przed wpisaniem bardzo dużej liczby
            return;
        }

        // działania
        if (number.equals(".") && !choseDot) { // pierwsze pojawienie się '.' (dopisuje kropke do result)
            resultLabelText = resultLabelText + number;
            choseDot = true;
        }
        else if (!number.equals(".")) { // wszystkie cyfry poza '.' (dopisuje nową cyfrę do result i formatuje)
            resultLabelText = resultLabelText + number;
            resultLabelText = formatWithSpacing(resultLabelText);
        }
        choseNumber = true;
    }

    /**
     * Obsługuje zdarzenie wywołane wyborem dowolnego znaku operacji innego niż =.
     */
    public void handleOperationsSigns(String sign) {

        if (dividedByZero) { // zablokowanie operacji po dzieleniu przez zero
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

        operationLabelText = operationLabelText + formatWithoutSpacing(resultLabelText) + " " + sign + " ";

        if (choseOperationSign && choseNumber && !choseEqualSign) { // wpisano liczbę, wybrano kolejny znak i nie wybrano = [2+3]->wyznacza wynik
            secondNumber = convertResultToDouble(resultLabelText);
            executeOperation();
        }

        firstNumber = convertResultToDouble(resultLabelText);
        operationSign = sign;
        resultLabelText = formatWithSpacing(resultLabelText); // nowa wartość

        choseNumber = false;
        choseDot = false;
        choseOperationSign = true;
        choseEqualSign = false;
    }

    /**
     * Obsługuje zdarzenie wywołane wyborem znaku =.
     */
    public void handleEqualSign() {

        if (dividedByZero) { // zabezpieczenie po dzieleniu przez zero
            removeAllNumbers();
            return;
        }

        if (!choseOperationSign) { // wybrano = i nie wybrano żadnego znaku []||[=]||[2]||[2=]
            operationLabelText = formatWithoutSpacing(resultLabelText) + " = ";
            choseNumber = false;
            choseDot = false;
            choseEqualSign = true;
            return;
        }

        // choseOperationSign == True zawsze w tym miejscu
        if (choseEqualSign) { // wybrano znak i = [2+=]||[2+3=]
            firstNumber = convertResultToDouble(resultLabelText);
            operationLabelText = formatWithoutSpacing(resultLabelText) + " " + operationSign + " " + formatWithoutSpacing(convertToString(secondNumber)) + " = ";
        }
        else if (!choseNumber) { // wybrano znak i nie wybrano liczby [+]||[2+]
            secondNumber = convertResultToDouble(resultLabelText);
            operationLabelText = operationLabelText + formatWithoutSpacing(resultLabelText) + " = ";
        }
        else { // wybrano znak i cyfre [2+3]
            secondNumber = convertResultToDouble(resultLabelText);
            operationLabelText = operationLabelText + formatWithoutSpacing(convertToString(secondNumber)) + " = ";
        }

        executeOperation();
        if (!dividedByZero)
            resultLabelText = formatWithSpacing(resultLabelText);

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
        choseNumber = false;
        choseDot = false;
        choseOperationSign = false;
        choseEqualSign = false;
    }

    private void swapSignNumber(String sign) {
        if (operationLabelText.length() > 0) {
            operationLabelText = operationLabelText.substring(0, operationLabelText.length()-2) + sign + " ";
        }
    }

    public String convertToString(double number) {
        return String.valueOf(number);
    }

    public double convertToDouble(String number) {
        return Double.parseDouble(number);
    }

    private double convertResultToDouble(String number) {
        return Double.parseDouble(number.replace(" ", "")); // " " ma nietypowe kodowanie;
    }

    private String formatWithSpacing(String number) {
        return resultLabelText = formatForResultLabelText.format(convertResultToDouble(number));
    }

    private String formatWithoutSpacing(String number) {
        return resultLabelText = formatForOperationLabelText.format(convertResultToDouble(number));
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
            dividedByZero = true;
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

    public void hightlighButton(JButton button, Color color) {

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
