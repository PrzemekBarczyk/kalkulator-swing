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
    private boolean choseDyadicOperation; // wybrano jedną z operacji dwuargumentowych
    private boolean chosePowOrSqlt; // wybrano operację potęgowania lub pierwiastkowania
    private boolean chosePercent; // wybrano oprarację liczenia procentu
    private boolean choseFraction; // wybrano operację liczenia ułamka z podanej liczby
    private boolean choseEqualSign; // wybrano operację wyświetlenia wyniku
    private boolean dividedByZero; // doszło do dzielenia przez 0

    private DecimalFormat formatForResultLabelText; // do usunięcia powtarzających się zer z przodu i końca oraz dodania odstępów
    private DecimalFormat formatForOperationLabelText; // do usunięcia powtarzających się zer z przodu i z końca

    private final int MAX_NUMBERS = 13; // maksymalna liczba cyfr jakie może mieć wpisywana liczba

    /**
     * Ustawia zmienne
     */
    public CalculatorModel() {

        previousNumber = 0;
        lastNumber = 0;
        operationLabelText = "";
        resultLabelText = "0";
        lastOperationSign = "";

        choseNumber = false;
        choseDot = false;
        choseDyadicOperation = false;
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
     * Obsługuje zdarzenie wywołane wyborem dowolnej z cyfr lub przecinka
     *
     * Zmienia wartość resultLabelText w przypadku podania cyfry lub przecinka. Może również wyczyścić
     * operationLabelText w przypadku gdy użyto equalsButton.
     *
     * Metoda posiada zabezpieczenia przed sytuacjami takimi jak gdy nastąpiło dzielenie przez 0, użycie przycisku
     * equalsButton, podanie pierwszej cyfry oraz podanie za dużej ilości cyfr.
     *
     * Następnie w zależności czy wybrano dowolną z cyfr czy przecinek wykonywane są instrukcję modyfikujące
     * resultLabelText.
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

        if (!choseNumber && !choseDot && !choseDyadicOperation && !choseEqualSign) { // wybrano pierwszą cyfrę lub przecinek []||[2+]||[2-3+]||[2=]
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
     * Obsługuje zdarzenie wywołane wyborem operacji dwuargumentowej
     *
     * Zapisuje podaną wartość wraz z wybranym znakiem operacji w operationLabelText. W przypadku wybrania drugiej i
     * każdej kolejnej operacji bez wybrania equalsButton zapisuje wynik w resultLabelText.
     *
     * Metoda posiada zabezpieczenia przed sytuacjami takimi jak gdy nastąpiło dzielenie przez 0, wybór znaku operacji
     * kilka razy pod rząd, użycie przycisku equalsButton i nie podanie nowej wartości przed wyborem znaku. użycie
     * nie typowej operacji lub zwykłej.
     *
     * Następnie w zależności czy wybrano operację jednoargumentową czy dwuargumentową modyfikowane jest
     * operationLabelText. Ponadto, jeśli wybrana operacja jest drugą lub kolejną wybraną bez wybrania equalsButton
     * wyznaczany jest nowy wynik.
     */
    public void handleDyadicOperation(String sign) {

        lastOperationSign = sign;

        // zabezpieczenie przed różnymi sytuacjami
        if (dividedByZero) { // zablokowanie operacji po dzieleniu przez zero
            return;
        }

        if (choseDyadicOperation && !choseNumber && !choseEqualSign && !chosePowOrSqlt) { // wybrano kolejny znak pod rząd [2+][2+3-]
            swapSignNumber(lastOperationSign); // nadpisuje poprzedni znak nowym
            return; // nie trzeba ustawiać flag, bo zostały już ustawione dla poprzedniego znaku
        }

        if (choseEqualSign && !choseNumber) { // użyto equalsButton i nie podano liczby [=]||[2=]||[2+3=]
            operationLabelText = "";
        }

        // modyfikacja operationLabelText
        if (chosePowOrSqlt || chosePercent || choseFraction) { // wybrano operację jednoargumentową [sqrt(2)][2+sqrt(3)]
            operationLabelText = operationLabelText + " " + lastOperationSign + " ";
        }
        else { // wybrano operację dwuargumentową
            operationLabelText = operationLabelText + formatWithoutSpacing(resultLabelText) + " " + lastOperationSign + " ";
        }

        // modyfikacja resultLabelText
        if (choseDyadicOperation && (choseNumber || chosePowOrSqlt || chosePercent || choseFraction) && !choseEqualSign) { // wybrano operację dwuargumentową kolejny raz bez wyboru = [2+3]
            lastNumber = deleteSpacesAndConvertToDouble(resultLabelText);
            executeDyadicOperation(); // wyznacza nowy resultLabelText
        }
        if (!dividedByZero)
            resultLabelText = formatWithSpacing(resultLabelText); // nowa wartość

        previousNumber = deleteSpacesAndConvertToDouble(resultLabelText);

        choseNumber = false;
        choseDot = false;
        choseDyadicOperation = true;
        chosePowOrSqlt = false;
        chosePercent = false;
        choseFraction = false;
        choseEqualSign = false;
    }

    /**
     * Wykonuje operację dwuargumentową na podstawie ostatnio użytego znaku
     *
     * Modyfikuję wartość resultLabelText przy użyciu zmiennych previousNumber i lastNumber.
     */
    private void executeDyadicOperation() {
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
     * Obsługuje zdarzenie wywołane wyborem operacji potęgowania lub pierwiastkowania
     *
     * Modyfikuję operationLabelText w różny sposób w zależności od tego czy operacja była wywołana pierwszy czy kolejny
     * raz pod rząd.
     *
     * Metoda posiada zabezpieczenie przed sytuacją gdy nastąpiło dzielenie przez 0.
     */
    public void handlePowerAndSqrt(String sign) {

        if (dividedByZero) { // zablokowanie operacji po dzieleniu przez zero
            return;
        }

        // modyfikacja operationLabelText
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

        // modyfikacja resultLabelText
        executeUnaryOperation(sign);
        if (!dividedByZero)
            resultLabelText = formatWithSpacing(resultLabelText);

        choseNumber = false;
        choseDot = false;
        chosePowOrSqlt = true;
    }

    /**
     * Obsługuje zdarzenie wywołane wyborem operacji wyznaczania procentu
     *
     * Modyfikuję operationLabelText w różny sposób w zależności od tego czy operacja była wywołana pierwszy czy kolejny
     * raz pod rząd.
     *
     * Metoda posiada zabezpieczenie przed sytuacją gdy nastąpiło dzielenie przez 0.
     */
    public void handlePercent() {

        if (dividedByZero) { // zablokowanie operacji po dzieleniu przez zero
            return;
        }

        // modyfikacja operationLabelText i resultLabelText
        if (!chosePercent) { // pierwszy pod rząd wybór tej operacji
            executeUnaryOperation("%");
            operationLabelText = operationLabelText + formatWithoutSpacing(resultLabelText);
        }
        else { // kolejny
            String oldResult = formatWithoutSpacing(resultLabelText);
            executeUnaryOperation("%");

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
     * Obsługuje zdarzenie wywołane wyborem operacji wyznaczania ułamka
     *
     * Modyfikuję operationLabelText w różny sposób w zależności od tego czy operacja była wywołana pierwszy czy kolejny
     * raz pod rząd.
     *
     * Metoda posiada zabezpieczenie przed sytuacją gdy nastąpiło dzielenie przez 0.
     */
    public void handleFraction() {

        if (dividedByZero) { // zablokowanie operacji po dzieleniu przez zero
            return;
        }

        // modyfikacja operationLabelText
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

        // modyfikacja resultLabelText
        executeUnaryOperation("1/");
        if (!dividedByZero)
            resultLabelText = formatWithSpacing(resultLabelText);

        choseNumber = false;
        choseDot = false;
        choseFraction = true;
    }

    /**
     * Wykonuje operację jednoargumentową na podstawie otrzymanego znaku
     *
     * Modyfikuję wartość resultLabelText przy użyciu odpowiedniego działania na resultLabelText.
     */
    private void executeUnaryOperation(String sign) {

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
     * Obsługuje zdarzenie wywołane wyborem znaku =
     *
     * Modyfikuje operationLabelText oraz wyznacza nową wartość resultLabelText przy użyciu wartości zmiennych
     * previousNumber i lastNumber.
     *
     * Metoda posiada zabezpieczenie przed sytuacją gdy nastąpiło dzielenie przez 0.
     *
     * Następnie podejmowane są kroki w przypadku gdy metoda została wywołana bez wyboru operacji dwuargumentowej.
     * Polegają one na modyfikacji operationLabelText i zakończeniu wykonywania metody ponieważ resultLabelText nie
     * może być w takiej sytuacji modyfikowany. W przypadku gdy metoda została wywołana z wybraną operacją
     * dwuargumentową modyfikowany jest operationLabelText, wykonywana jest wybrana operacja oraz wyświetlana jest
     * nowa wartość resultLabelText.
     */
    public void handleEqualSign() {

        // zabezpieczenie przed różnymi sytuacjami
        if (dividedByZero) { // zablokowanie operacji po dzieleniu przez zero
            handleClear();
            return;
        }

        // modyfikacja operationLabelText przy braku wyboru operacji dwuargumentowej
        if (!choseDyadicOperation && !chosePowOrSqlt && !chosePercent && !choseFraction) { // nie wybrano znaku i operacji jednoargumentowej []||[=]||[2]||[2=]
            operationLabelText = formatWithoutSpacing(resultLabelText) + " = ";
            choseNumber = false;
            choseDot = false;
            choseEqualSign = true;
            return;
        }
        if (!choseDyadicOperation) { // nie wybrano znaku i wybrano operację jednoargumentową
            operationLabelText = operationLabelText + " = ";
            choseNumber = false;
            choseDot = false;
            choseEqualSign = true;
            chosePowOrSqlt = false;
            chosePercent = false;
            choseFraction = false;
            return;
        }

        // modyfikacja operationLabelText po wyborze operacji dwuargumentowej
        // choseOperationSign == True zawsze w tym miejscu
        if (choseEqualSign) { // wybrano znak i = kolejny raz pod rząd [2+=]||[2+3=]||[2+3==]
            previousNumber = deleteSpacesAndConvertToDouble(resultLabelText);
            operationLabelText = formatWithoutSpacing(resultLabelText) + " " + lastOperationSign + " " + formatWithoutSpacing(convertToString(lastNumber)) + " = ";
        }
        else if (!choseNumber && !chosePowOrSqlt && !chosePercent && !choseFraction) { // wybrano znak i nie wybrano drugiej liczby [+]||[2+]
            lastNumber = deleteSpacesAndConvertToDouble(resultLabelText);
            operationLabelText = operationLabelText + formatWithoutSpacing(resultLabelText) + " = ";
        }
        else if (chosePowOrSqlt || chosePercent || choseFraction) { // wybrano znak i operację jednoargumentową [2+sqrt(3)]
            lastNumber = deleteSpacesAndConvertToDouble(resultLabelText);
            operationLabelText = operationLabelText + " = ";
        }
        else { // wybrano znak i cyfre [2+3]
            lastNumber = deleteSpacesAndConvertToDouble(resultLabelText);
            operationLabelText = operationLabelText + formatWithoutSpacing(convertToString(lastNumber)) + " = ";
        }

        // modyfikacja resultLabelText
        executeDyadicOperation();
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
     * Usuwa ostatni znak z resultLabelText
     *
     * Posiada trzy możliwe przebiegi w zależności czy ostatni znak to przecinek, cyfra oraz ostatnia cyfra.
     *
     * Metoda posiada zabezpieczenie przed sytuacją gdy nastąpiło dzielenie przez 0.
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
     * Usuwa zawartość resultLabelText
     *
     * W przypadku użycia znaku "=" działa tak samo jak clear().
     *
     * Metoda posiada zabezpieczenie przed sytuacją gdy nastąpiło dzielenie przez 0.
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
     * Usuwa wszystkie wprowadzone dane i przywraca je do wartości początkowych
     */
    public void handleClear() {

        previousNumber = 0;
        lastNumber = 0;
        operationLabelText = "";
        resultLabelText = "0";
        lastOperationSign = "";
        choseNumber = false;
        choseDot = false;
        choseDyadicOperation = false;
        chosePowOrSqlt = false;
        chosePercent = false;
        choseFraction = false;
        choseEqualSign = false;
    }

    /**
     * Obsługuje zdarzenie wywołane wyborem przycisku zmiany znaku
     *
     * Zmienia znak liczby przechowywanej w resultLabel na przeciwny mnożąc jej wartość przez -1.
     *
     * Metoda posiada zabezpieczenie przed sytuacją gdy nastąpiło dzielenie przez 0.
     */
    public void handleSignNegation() {

        if (dividedByZero) { // zablokowanie operacji po dzieleniu przez zero
            return;
        }

        if (!resultLabelText.equals("0"))
            resultLabelText = formatWithSpacing(convertToString(-1 * deleteSpacesAndConvertToDouble(resultLabelText)));
    }


    /**
     * Usuwa ostatni znak z operationLabelText i zamienia go na otrzymany w argumencie
     */
    private void swapSignNumber(String sign) {
        if (operationLabelText.length() > 0) {
            operationLabelText = operationLabelText.substring(0, operationLabelText.length()-2) + sign + " ";
        }
    }

    /**
     * Konwertuje otrzymanego doubla do Stringa
     */
    private String convertToString(double number) {
        return String.valueOf(number);
    }

    /**
     * Konwertuje otrzymanego Stringa do doubla
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

    /**
     * Formatuje otrzymanego Stringa dodając spację co 3 cyfry
     *
     * Używa w tym celu klasy DecimalFormat
     */
    private String formatWithSpacing(String number) {
        return resultLabelText = formatForResultLabelText.format(deleteSpacesAndConvertToDouble(number));
    }

    /**
     * Formatuje otrzymanego Stringa bez dodawania spacji
     *
     * Używa w tym celu klasy DecimalFormat
     */
    private String formatWithoutSpacing(String number) {
        return resultLabelText = formatForOperationLabelText.format(deleteSpacesAndConvertToDouble(number));
    }

    /**
     * Zwraca zawartość zmiennej operationLabelText
     */
    public String getOperationLabelText() {
        return operationLabelText;
    }

    /**
     * Zwraca zawartość zmiennej resultLabelText
     */
    public String getResultLabelText() {
        return resultLabelText;
    }

    /**
     * Zmienia na chwilę kolor tła przesłanego przycisku
     */
    public void highlightButton(JButton button, Color color) {

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
