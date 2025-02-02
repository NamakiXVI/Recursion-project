# Rekursionsprojekt

## Inhaltsverzeichnis
1. [Überblick](#überblick)
2. [Projektstruktur](#projektstruktur)
   - [AchtDamenProblem](#achtdamenproblem)
   - [Hanoi-Turm](#hanoi-turm)
   - [Rekursion-Python](#rekursion-python)
3. [Beschreibung](#beschreibung)
4. [Verwendete Technologien](#verwendete-technologien)
5. [So wird das Projekt ausgeführt](#so-wird-das-projekt-ausgeführt)
   - [Python](#python)
   - [Java](#java)
6. [Mitwirkende](#mitwirkende)
7. [Kontakt](#kontakt)

---

## Überblick
Dieses Projekt wurde von **Philipp Nguyen (NamakiXVI)** und **Morten Böhne (HaiTsung)** im Rahmen eines Schulprojekts unter der Leitung unseres Lehrers **Herrn Wessel** entwickelt. Es beschäftigt sich mit verschiedenen Arten von Rekursionsproblemen, insbesondere dem **Acht-Damen-Problem** und dem **Turm von Hanoi**. Ziel des Projekts war es, zu zeigen, wie man mit Rekursion komplexe Probleme elegant und effizient lösen kann.

Das Projekt ist auf GitHub verfügbar: [Recursion Project Repository](https://github.com/NamakiXVI/Recursion-project).

---

## Projektstruktur

### AchtDamenProblem
- **EightQueens.py**:  
  Diese Datei enthält eine Python-Implementierung des Acht-Damen-Problems. Es wird eine grafische Benutzeroberfläche (GUI) mit `customtkinter` verwendet, um das Schachbrett und die Platzierung der Damen darzustellen. Die Lösung wird rekursiv ermittelt und visuell angezeigt.

- **EightQueens.java**:  
  Diese Datei enthält eine Java-Implementierung des Acht-Damen-Problems. Die Lösung wird in der Konsole ausgegeben, ohne grafische Oberfläche. Es wird gezeigt, wie die Damen auf dem Schachbrett platziert werden, ohne sich gegenseitig zu bedrohen.

---

### Hanoi-Turm
- **HanoiGUI.py**:  
  Diese Datei enthält eine Python-Implementierung des Turms von Hanoi. Es wird eine GUI mit `customtkinter` verwendet, um die Türme und Scheiben darzustellen. Der Benutzer kann die Anzahl der Scheiben und die Verzögerung zwischen den Zügen anpassen, um den Lösungsprozess zu visualisieren.

- **Hanoiturm.java**:  
  Diese Datei enthält eine Java-Implementierung des Turms von Hanoi. Die Lösung wird in der Konsole Schritt für Schritt ausgegeben. Es wird gezeigt, wie die Scheiben von einem Turm zum anderen bewegt werden.

- **HanoiGUI.java**:  
  Diese Datei enthält eine Java-Implementierung des Turms von Hanoi mit einer grafischen Benutzeroberfläche, erstellt mit `Swing`. Die Türme und Scheiben werden visuell dargestellt, und der Lösungsprozess kann in Echtzeit verfolgt werden.

---

### Rekursion-Python
- **Main.py**:  
  Diese Datei ist eine einheitliche Python-Anwendung, die es Benutzern ermöglicht, zwischen dem Acht-Damen-Problem und dem Turm von Hanoi zu wählen. Die Anwendung verfügt über eine benutzerfreundliche Oberfläche, erstellt mit `customtkinter`. Sie bietet eine einfache Möglichkeit, beide Rekursionsprobleme zu visualisieren und zu lösen.

---

## Beschreibung
- **Acht-Damen-Problem**:
  - Lösen des klassischen Problems, bei dem 8 Damen so auf einem Schachbrett platziert werden müssen, dass sie sich nicht gegenseitig bedrohen.
  - Enthält Implementierungen in Python und Java.
  - Die Python-Version verfügt über eine GUI zur Visualisierung der Lösung.

- **Turm von Hanoi**:
  - Lösen des Turm-von-Hanoi-Problems mithilfe von Rekursion.
  - Enthält Python- und Java-Implementierungen mit sowohl konsolenbasierter als auch GUI-basierter Darstellung.
  - Benutzer können die Anzahl der Scheiben und die Verzögerung zwischen den Zügen anpassen, um die Visualisierung zu steuern.

- **Einheitliche Python-Anwendung**:
  - Eine Python-Anwendung (`Main.py`), die es Benutzern ermöglicht, zwischen dem Acht-Damen-Problem und dem Turm von Hanoi zu wählen.
  - Verfügt über eine benutzerfreundliche Oberfläche, erstellt mit `customtkinter`.

---

## Verwendete Bibliotheken
- **Python**:
  - Bibliotheken: `customtkinter`, `PIL` (Python Imaging Library), `threading`.
  - Wurde verwendet, um interaktive GUIs zu erstellen und Rekursionsprobleme zu lösen.

- **Java**:
  - Bibliotheken: `Swing`, `JFrame`, `JPanel`, `JLabel`.
  - Wurde verwendet, um konsolenbasierte und GUI-basierte Lösungen zu implementieren.

---

## So wird das Projekt ausgeführt

### Python
1. Stelle sicher, dass Python installiert ist (Version 3.7 oder höher).
2. Installiere die benötigten Bibliotheken:
   ```bash
   pip install customtkinter
   ```
3. Navigiere zum gewünschten Verzeichnis (`AchtDamenProblem`, `Hanoi-Turm` oder `Rekursion-Python`).
4. Führe das Python-Skript aus:
   ```bash
   python EightQueens.py  # Für das Acht-Damen-Problem
   python HanoiGUI.py     # Für den Turm von Hanoi
   python Main.py         # Für die einheitliche Anwendung
   ```

### Java
1. Stelle sicher, dass Java installiert ist (JDK 11 oder höher).
2. Kompiliere und führe die Java-Dateien mit deiner bevorzugten IDE oder über die Kommandozeile aus:
   ```bash
   javac EightQueens.java
   java EightQueens
   ```
   ```bash
   javac Hanoiturm.java
   java Hanoiturm
   ```
   ```bash
   javac HanoiGUI.java
   java HanoiGUI
   ```

---

## Mitwirkende
- **Philipp Nguyen (NamakiXVI)**: Hat die Python-Versionen des Acht-Damen-Problems und des Turms von Hanoi implementiert und an der einheitlichen Python-Anwendung mitgearbeitet.
- **Morten Böhne (HaiTsung)**: Hat die Java-Versionen des Acht-Damen-Problems und des Turms von Hanoi implementiert und an der einheitlichen Python-Anwendung mitgearbeitet.

---

## Kontakt
Bei Fragen oder so kannst du uns gerne kontaktieren:
- Philipp Nguyen: [GitHub](https://github.com/NamakiXVI)
- Morten Böhne: [GitHub](https://github.com/HaiTsung)

---

Lehrer: Herr Wessel  
Schule: Paulsen Gymnasium, Leistungskurs Informatik Q2
