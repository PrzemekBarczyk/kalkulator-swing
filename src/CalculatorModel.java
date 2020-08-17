import java.awt.Color;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import javax.swing.JButton;

public class CalculatorModel {

    private String operationLabelText; // zawartość JLabel z zapisem przeprowadzonych operacji
    private String resultLabelText; // zawartość JLabel z aktualnie wprowadzoną wartością/wynikiem

    private double previousNumber; // poprzednio podana wartość
    private double lastNumber; // ostatnio podana wartość
    private String lastOperationSign; // znak ostatniej operacji
    private String stringBuff; // zmienna do przechowywania ciągów znakowych

    private boolean choseNumber; // wpisano liczbę do resultLabelText
    private boolean choseDot; // we wpisanej liczbie użytko przecinka
    private boolean choseBasicOperation; // wybrano jedną z podstawowych operacji
    private boolean chosePowOrSqlt; // wybrano operację potęgowania lub pierwiastkowania
    private boolean chosePercent; // wybrano oprarację liczenia procentu
    private boolean choseFraction; // wybrano operację liczenia ułamka z podanej liczby
    private boolean choseEqualSign; // wybrano operację wyświetlenia wyniku
    private boolean dividedByZero; // doszło do dzielenia przez 0

    private DecimalFormat formatForResultLabelText; // do usunięcia powtarzających się zer z przodu i końca oraz dodania odstępów
    private DecimalFormat formatForOperationLabelText; // do usunięcia powtarzających się zer z przodu i z końca

    private final int MAX_NUMBERS = 13; // maksymalna liczba cyfr jakie może mieć wpisywana liczba

    public CalculatorModel() {

        previousNumber = 0;
        lastNumber = 0;
        operationLabelText = "";
        resultLabelText = "0";
        lastOperationSign = "";

        choseNumber = false;
        choseDot = false;
        choseBasicOperation = false;
        chosePowOrSqlt = false;
        chosePercent = false;
        choseFraction = false;
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

        // zabezpieczenia przed różnymi sytuacjami
        if (!number.equals(".") && dividedByZero) { // dzielenie przez 0 i wybrano cyfrę
            handleClear();
            dividedByZero = false;
        }
        else if (number.equals(".") && dividedByZero) { // dzielenie przez 0 i wybrano przecinek
            return;
        }

        if (!choseNumber && !choseDot && !(choseBasicOperation && choseEqualSign)) { // wybrano pierwszą cyfrę lub przecinek []||[2+]||[2-3+]||[2=]
            resultLabelText = "0";
        }
        else if (!choseNumber && !choseDot) { // użyto equalsButton i wybrano pierwszą cyfrę lub przecinek [2+3=]||[2+3-4=]
            resultLabelText = "0";
            operationLabelText = "";
        }
        else if (resultLabelText.length() > MAX_NUMBERS) { // blokada przed wpisaniem bardzo dużej liczby
            return;
        }

        // modyfikacja resultLabelText i flag
        if (!number.equals(".")) { // cyfra
            resultLabelText = resultLabelText + number;
            resultLabelText = formatWithSpacing(resultLabelText);
            choseNumber = true;
        }
        else if (!choseDot) { // przecinek (wybrany po raz pierwszy)
            resultLabelText = resultLabelText + number;
            choseDot = true;
        }
    }

    /**
     * Obsługuje zdarzenie wywołane wyborem dowolnego znaku operacji innego niż =.
     */
    public void handleBasicOperations(String sign) {

        lastOperationSign = sign;

        // zabezpieczenie przed różnymi sytuacjami
        if (dividedByZero) { // zablokowanie operacji po dzieleniu przez zero
            return;
        }

        if (choseBasicOperation && !choseNumber && !choseEqualSign && !chosePowOrSqlt) { // wybrano kolejny znak pod rząd [2+][2+3-]
            swapSignNumber(lastOperationSign); // nadpisuje poprzedni znak nowym
            return; // nie trzeba ustawiać flag, bo zostały już ustawione dla poprzedniego znaku
        }

        if (choseEqualSign && !choseNumber) { // użyto equalsButton i nie podano liczby [=]||[2=]||[2+3=]
            operationLabelText = "";
        }

        // modyfikacja operationLabelText
        if (chosePowOrSqlt || chosePercent || choseFraction) { // wybrano którąś z nietypowych operacji [sqrt(2)][2+sqrt(3)]
            operationLabelText = operationLabelText + " " + lastOperationSign + " ";
        }
        else { // wybrano zwykłą operację
            operationLabelText = operationLabelText + formatWithoutSpacing(resultLabelText) + " " + lastOperationSign + " ";
        }

        // modyfikacja resultLabelText
        if (choseBasicOperation && (choseNumber || chosePowOrSqlt || chosePercent || choseFraction) && !choseEqualSign) { // wybrano operację kolejny raz bez wyboru = [2+3]
            lastNumber = deleteSpacesAndConvertToDouble(resultLabelText);
            executeBasicOperation(); // wyznacza nowy resultLabelText
        }
        if (!dividedByZero)
            resultLabelText = formatWithSpacing(resultLabelText); // nowa wartość

        previousNumber = deleteSpacesAndConvertToDouble(resultLabelText);

        choseNumber = false;
        choseDot = false;
        choseBasicOperation = true;
        chosePowOrSqlt = false;
        chosePercent = false;
        choseFraction = false;
        choseEqualSign = false;
    }

    public void handlePowerAndSqrt(String sign) {

        if (dividedByZero) { // zablokowanie operacji po dzieleniu przez zero
            return;
        }

        if (!chosePowOrSqlt) { // pierwszy pod rząd wybór tej operacji
            stringBuff = sign + "(" + formatWithoutSpacing(resultLabelText) + ")";
            operationLabelText = operationLabelText + stringBuff;
        }
        else { // kolejny
            String reversedBuff = new StringBuilder(stringBuff).reverse().toString();
            reversedBuff = reversedBuff.replace(")", "\\)");
            reversedBuff = reversedBuff.replace("(", "\\(");
            stringBuff = sign + "(" + stringBuff + ")";
            operationLabelText = new StringBuilder(operationLabelText).reverse().toString().replaceFirst(reversedBuff, ")" + reversedBuff + "(" + new StringBuilder(sign).reverse().toString());
            operationLabelText = new StringBuilder(operationLabelText).reverse().toString();
        }

        executeExtraOperation(sign);
        if (!dividedByZero)
            resultLabelText = formatWithSpacing(resultLabelText);

        choseNumber = false;
        choseDot = false;
        chosePowOrSqlt = true;
    }

    /**
     * Obsługuje przycisk liczenia procentu
     */
    public void handlePercent() {

        if (dividedByZero) { // zablokowanie operacji po dzieleniu przez zero
            return;
        }

        if (!chosePercent) { // pierwszy pod rząd wybór tej operacji
            executeExtraOperation("%");
            operationLabelText = operationLabelText + formatWithoutSpacing(resultLabelText);
        }
        else { // kolejny
            String oldResult = formatWithoutSpacing(resultLabelText);
            executeExtraOperation("%");

            String reversedOldResult = new StringBuilder(oldResult).reverse().toString();
            operationLabelText = new StringBuilder(operationLabelText).reverse().toString().replaceFirst(reversedOldResult, new StringBuilder(resultLabelText).reverse().toString());
            operationLabelText = new StringBuilder(operationLabelText).reverse().toString();
        }
        if (!dividedByZero)
            resultLabelText = formatWithSpacing(resultLabelText);

        choseNumber = false;
        choseDot = false;
        chosePercent = true;
    }

    /**
     * Obsługuje przycisk liczenia ułamka
     */
    public void handleFraction() {

        if (dividedByZero) { // zablokowanie operacji po dzieleniu przez zero
            return;
        }

        if (!choseFraction) { // pierwszy pod rząd wybór tej operacji
            stringBuff = "1/( " + formatWithoutSpacing(resultLabelText) + " )";
            operationLabelText = operationLabelText + stringBuff;
        }
        else { // kolejny
            String reversedBuff = new StringBuilder(stringBuff).reverse().toString();
            reversedBuff = reversedBuff.replace(")", "\\)");
            reversedBuff = reversedBuff.replace("(", "\\(");
            stringBuff = "1/" + "( " + stringBuff + " )";
            operationLabelText = new StringBuilder(operationLabelText).reverse().toString().replaceFirst(reversedBuff, ") " + reversedBuff + " (" + new StringBuilder("1/").reverse().toString());
            operationLabelText = new StringBuilder(operationLabelText).reverse().toString();
        }

        executeExtraOperation("1/");
        if (!dividedByZero)
            resultLabelText = formatWithSpacing(resultLabelText);

        choseNumber = false;
        choseDot = false;
        choseFraction = true;
    }

    /**
     * Obsługuje przycisk zmieniający znak wpisywanej liczby
     * Zmienia znak liczby przechowywanej w resultLabel
     */
    public void handleSignNegation() {

        if (dividedByZero) { // zablokowanie operacji po dzieleniu przez zero
            return;
        }

        if (!resultLabelText.equals("0"))
            resultLabelText = formatWithSpacing(convertToString(-1 * deleteSpacesAndConvertToDouble(resultLabelText)));
    }

    /**
     * Obsługuje zdarzenie wywołane wyborem znaku =.
     */
    public void handleEqualSign() {

        if (dividedByZero) { // zablokowanie operacji po dzieleniu przez zero
            handleClear();
            return;
        }

        if (!choseBasicOperation && !chosePowOrSqlt && !chosePercent && !choseFraction) { // wybrano = i nie wybrano żadnego znaku []||[=]||[2]||[2=]
            operationLabelText = formatWithoutSpacing(resultLabelText) + " = ";
            choseNumber = false;
            choseDot = false;
            choseEqualSign = true;
            return;
        }
        if (!choseBasicOperation) { // wybrano = i którąś z operacji nie typowych
            operationLabelText = operationLabelText + " = ";
            choseNumber = false;
            choseDot = false;
            chosePowOrSqlt = false;
            chosePercent = false;
            choseFraction = false;
            return;
        }

        // choseOperationSign == True zawsze w tym miejscu
        if (choseEqualSign) { // wybrano znak i = [2+=]||[2+3=]
            previousNumber = deleteSpacesAndConvertToDouble(resultLabelText);
            operationLabelText = formatWithoutSpacing(resultLabelText) + " " + lastOperationSign + " " + formatWithoutSpacing(convertToString(lastNumber)) + " = ";
        }
        else if (!choseNumber && !chosePowOrSqlt && !chosePercent && !choseFraction) { // wybrano znak i nie wybrano liczby [+]||[2+]
            lastNumber = deleteSpacesAndConvertToDouble(resultLabelText);
            operationLabelText = operationLabelText + formatWithoutSpacing(resultLabelText) + " = ";
        }
        else if (chosePowOrSqlt || chosePercent || choseFraction) {
            lastNumber = deleteSpacesAndConvertToDouble(resultLabelText);
            operationLabelText = operationLabelText + " = ";
        }
        else { // wybrano znak i cyfre [2+3]
            lastNumber = deleteSpacesAndConvertToDouble(resultLabelText);
            operationLabelText = operationLabelText + formatWithoutSpacing(convertToString(lastNumber)) + " = ";
        }

        executeBasicOperation();
        if (!dividedByZero)
            resultLabelText = formatWithSpacing(resultLabelText);

        choseNumber = false;
        choseDot = false;
        choseEqualSign = true;
        chosePowOrSqlt = false;
        chosePercent = false;
        choseFraction = false;
    }

    /**
     * Wykonuje odpowiednią operację na podstawie ostatnio użytego znaku
     */
    private void executeBasicOperation() {
        switch (lastOperationSign) {
            case "+":
                resultLabelText = convertToString(previousNumber + lastNumber);
                break;
            case "-":
                resultLabelText = convertToString(previousNumber - lastNumber);
                break;
            case "×":
                resultLabelText = convertToString(previousNumber * lastNumber);
                break;
            case "÷":
                if (lastNumber != 0) { // zabezpieczenie przed dzieleniem przez 0
                    resultLabelText = convertToString(previousNumber / lastNumber);
                }
                else {
                    resultLabelText = "Cannot divide by zero";
                    dividedByZero = true;
                }
                break;
            default:
                System.out.println("Nieznana operacja!");
        }
    }

    /**
     * Wykonuje odpowiednie operację na podstawie otrzymanego znaku
     */
    private void executeExtraOperation(String sign) {

        switch(sign) {
            case "sqrt":
                resultLabelText = convertToString(convertToDouble(resultLabelText) * convertToDouble(resultLabelText));
                break;
            case "√":
                resultLabelText = convertToString(Math.sqrt(convertToDouble(resultLabelText)));
                break;
            case "1/":
                if (deleteSpacesAndConvertToDouble(resultLabelText) != 0) { // zabezpieczenie przed dzieleniem przez 0
                    resultLabelText = convertToString(1 / convertToDouble(resultLabelText));
                }
                else {
                    resultLabelText = "Cannot divide by zero";
                    dividedByZero = true;
                }
                break;
            case "%":
                resultLabelText = convertToString(convertToDouble(resultLabelText) / 100 * previousNumber);
                break;
            default:
                System.out.println("Nieznana operacja!");
        }
    }

    /**
     * Usuwa ostatni znak z resultLabelText
     */
    public void handleBackspace() {

        if (dividedByZero) { // zablokowanie operacji po dzieleniu przez zero
            handleClear();
            return;
        }

        if (resultLabelText.length() == 1) { // ostatnia cyfra
            resultLabelText = "0";
        }
        else if (!resultLabelText.endsWith(".")) { // zwykła cyfra
            resultLabelText = resultLabelText.substring(0, resultLabelText.length() - 1);
        }
        else { // przecinek
            resultLabelText = resultLabelText.substring(0, resultLabelText.length() - 1);
            choseDot = false;
        }
    }

    /**
     * Usuwa ostanio wpisaną wartość. W przypadku użycia znaku "=" działa tak samo jak clear()
     */
    public void handleClearEntry() {

        if (dividedByZero) { // zablokowanie operacji po dzieleniu przez zero
            handleClear();
            return;
        }

        if (!choseEqualSign) {
            resultLabelText = "0";
            choseNumber = false;
            choseDot = false;
            chosePowOrSqlt = false;
            chosePercent = false;
            choseFraction = false;
        }
        else {
            handleClear();
        }
    }

    /**
     * Usuwa wszystkie wprowadzone dane i przywraca je do wartości poczatkowych
     */
    public void handleClear() {

        previousNumber = 0;
        lastNumber = 0;
        operationLabelText = "";
        resultLabelText = "0";
        lastOperationSign = "";
        choseNumber = false;
        choseDot = false;
        choseBasicOperation = false;
        chosePowOrSqlt = false;
        chosePercent = false;
        choseFraction = false;
        choseEqualSign = false;
    }

    private void swapSignNumber(String sign) {
        if (operationLabelText.length() > 0) {
            operationLabelText = operationLabelText.substring(0, operationLabelText.length()-2) + sign + " ";
        }
    }

    /**
     * Konwertuje otrzymanego doubla na Stringa
     */
    private String convertToString(double number) {
        return String.valueOf(number);
    }

    /**
     * Konwertuje otrzymanego Stringa na doubla
     */
    private double convertToDouble(String number) {
        return Double.parseDouble(number);
    }

    /**
     * Usuwa spację z otrzymanego Stringa i konwertuję go do doubla
     */
    private double deleteSpacesAndConvertToDouble(String number) {
        return Double.parseDouble(number.replace(" ", "")); // " " ma nietypowe kodowanie;
    }

    private String formatWithSpacing(String number) {
        return resultLabelText = formatForResultLabelText.format(deleteSpacesAndConvertToDouble(number));
    }

    private String formatWithoutSpacing(String number) {
        return resultLabelText = formatForOperationLabelText.format(deleteSpacesAndConvertToDouble(number));
    }


    private void squareRoot() {
        resultLabelText = convertToString(Math.sqrt(convertToDouble(resultLabelText)));
    }

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
