# SE201 Projekat – Sistem za rezervaciju sportske opreme 🎾🎽🏓

Ovaj projekat predstavlja studentsku aplikaciju za upravljanje rezervacijom sportske opreme. Razvijen je kao praktični rad za predmet **SE201 – Uvod u softversko inženjerstvo**.

## 🛠 Tehnologije koje su korišćene
- **Java**
- **JavaFX** – grafički interfejs
- **MySQL (phpMyAdmin)** – baza podataka
- **JDBC** – konekcija sa bazom
- **GitHub** – verzionisanje projekta

## 📋 Funkcionalnosti
- Prikaz dostupne sportske opreme
- Dodavanje i brisanje rezervacija
- Ažuriranje datuma vraćanja opreme
- Validacija korisničkog unosa
- Prikaz rezervacija po korisniku
- Statistički grafikon broja rezervacija

## 🧩 Struktura projekta
- `model` – klase kao što su `Korisnik`, `Oprema`, `Rezervacija`
- `controller` – logika za rad sa bazom i korisnički interfejs
- `view` – JavaFX GUI i FXML fajlovi
- `db` – konekcija sa MySQL bazom

## 🧪 Testiranje
Trenutno su funkcionalnosti testirane ručno, a planirani su JUnit testovi za osnovne metode u kontrolerima.

## 📁 Baza podataka
Baza se sastoji od 3 glavne tabele: `korisnik`, `oprema`, `rezervacija`. Napravljena je i održava se pomoću **phpMyAdmin-a**.

## 📈 Planiranje projekta
Korišćen je **Trello board** za planiranje po fazama, kao i **Git** za praćenje razvoja sa više commit-a i razvojnim granama.

## 👨‍💻 Autor
Đuro Popara – student Fakulteta informacionih tehnologija (Metropolitan)

---

