# kalkulator-swing
Aplikacja ma odwzorować działanie kalkulatora z Windows 10

### Opis projektu:

Aplikacja ma za zadanie symulować działanie kalkualtora z systemu Windows 10. Każda akcja podjęta w aplikacji przez użytkownika ma dawać takie same rezultaty jak w systemowym kalkulatorze.

### Cechy projektu:

* Wykorzystane wzorce architektoniczne: **MVC**
* Wykorzystane wzorce projektowe: **brak**
* Rodzaj hierarchii pakietów: **brak**

### Więcej szczegółów:

#### GUI

Interfejs aplikacj kalkulatora składa się z trzech głównych sekcji: *okna ostatnich operacji*, *okna wpisanej wartości* oraz *panelu z klawiszami*.
*Okno ostatnich operacji* wyświetla wszystkie ostatnie operacje jakie wystąpiły do momentu wybrania znaku równości włącznie z nim. W tej sytuacji wybór każdego z klawiszy innego niż znak równości spowoduje reset wartości wyświetlanej w tym oknie. Kolejne pod rząd wybranie klawisza równości spowoduje natomiast że do aktualnego wyniku zostanie dodana ostatnio podana wartość.
*Okno wpisanej wartości* wyświetla ostanią wartość podaną przez użytkownika lub wynik gdy naciśnięto przycisk równości lub  naciśnięto po raz kolejny przed wybraniem przycisku równości przycisk któreś z *operacji podstawowych* (dodawanie, odejmowanie, mnożenie lub dzielenie).
*Panel z klawiszami* pozwala na wprowadzanie wartości do kalkulatora, wybór operacji oraz modyfikacje podanych danych.

#### Działanie Modelu

W Modelu utworzono wiele flag mających za zadanie określanie dalszej ścieżki przebiegu programu. W zależności od ich wartości są podejmowane odpowiednie operacje na danych.

#### Inne

Tworzenie nowego menu polega na stworzeniu nowego obiektu klasy odpowiadającego mu *Kontrolera* np. dla *MainMenu* tworzymy obiekt klasy *MainMenuController*. W argumentach konstruktora *Kontrolera* tworzone są również obiekty *Widoku* i *Modelu*. W przyszłości spróbuję zrezygnować z konieczności tworzenia obiektów tych klas w ten sposób na rzecz ich automatycznego tworzenia wewnątrz konstruktora *Kontrolera*.
