# kalkulator-swing
Aplikacja ma odwzorować działanie kalkulatora z Windows 10

### Opis projektu:
Aplikacja ma za zadanie symulować działanie kalkualtora z systemu Windows 10. Każda akcja podjęta w aplikacji przez użytkownika ma dawać takie same rezultaty jak w systemowym kalkulatorze.

### Cechy projektu:

* Wykorzystane wzorce architektoniczne: **MVC**
* Wykorzystane wzorce projektowe: **brak**
* Rodzaj hierarchii pakietów: **brak**

### Więcej szczegółów:

Tworzenie nowego menu polega na stworzeniu nowego obiektu klasy odpowiadającego mu *Kontrolera* np. dla *MainMenu* tworzymy obiekt klasy *MainMenuController*. W argumentach konstruktora *Kontrolera* tworzone są również obiekty *Widoku* i *Modelu*. W przyszłości spróbuję zrezygnować z konieczności tworzenia obiektów tych klas w ten sposób na rzecz ich automatycznego tworzenia wewnątrz konstruktora *Kontrolera*.
