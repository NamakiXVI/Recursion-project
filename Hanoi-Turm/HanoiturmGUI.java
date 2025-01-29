import java.awt.*; // Importiert Klassen für GUI-Komponenten und Grafik
import java.util.Stack; // Importiert die Stack-Datenstruktur
import javax.swing.*; // Importiert Swing für die GUI

// Hauptklasse für das Programm
public class HanoiturmGUI 
{
    // Stacks repräsentieren die drei Türme (A, B, C)
    static Stack<Integer> towerA = new Stack<>();
    static Stack<Integer> towerB = new Stack<>();
    static Stack<Integer> towerC = new Stack<>();

    // Referenz auf das GUI-Panel zur Aktualisierung der Darstellung
    static HanoiPanel hanoiPanel;

    // Hauptmethode
    public static void main(String[] args) 
    {
        int n = 15; // Anzahl der Scheiben (kann angepasst werden)

        // Initialisiert den ersten Turm (Turm A) mit Scheiben
        for (int i = n; i >= 1; i--) 
        {
            towerA.push(i); // Größte Scheibe (n) unten, kleinste oben
        }

        // Erstellen des GUI-Fensters
        JFrame frame = new JFrame("Turm von Hanoi"); // Fenster mit Titel
        hanoiPanel = new HanoiPanel(n, towerA, towerB, towerC); // Initialisiert das Panel
        frame.add(hanoiPanel); // Fügt das Panel zum Fenster hinzu
        frame.setSize(1200, 800); // Setzt die Fenstergröße
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Beendet Programm bei Fenster-Schließen
        frame.setVisible(true); // Macht das Fenster sichtbar

        // Druckt den Startzustand der Türme in die Konsole
        System.out.println("Startzustand:");
        printTowers(towerA, towerB, towerC);

        // Löst das "Turm von Hanoi"-Problem
        solveHanoi(n, towerA, towerC, towerB, "A", "C", "B");

        // Druckt den Endzustand der Türme in die Konsole
        System.out.println("Endzustand:");
        printTowers(towerA, towerB, towerC);
    }

    // Rekursive Methode zur Lösung des Problems
    public static void solveHanoi(int n, Stack<Integer> from, Stack<Integer> to, Stack<Integer> aux, String fromName, String toName, String auxName) 
    {
        if (n == 1) // Basisfall: Nur eine Scheibe
        {
            moveDisk(from, to, fromName, toName); // Bewege die Scheibe direkt
            return;
        }

        // Rekursive Aufteilung des Problems
        solveHanoi(n - 1, from, aux, to, fromName, auxName, toName); // Bewege (n-1) Scheiben zum Hilfsturm
        moveDisk(from, to, fromName, toName); // Bewege die größte Scheibe zum Zielturm
        solveHanoi(n - 1, aux, to, from, auxName, toName, fromName); // Bewege die (n-1) Scheiben vom Hilfsturm zum Zielturm
    }

    // Verschiebt eine Scheibe von einem Turm zum anderen
    public static void moveDisk(Stack<Integer> from, Stack<Integer> to, String fromName, String toName) 
    {
        try 
        {
            Thread.sleep(10); // Verzögert die Animation um 500 Millisekunden
        } 
        catch (InterruptedException e) 
        {
            // Ausnahmebehandlung (falls der Thread unterbrochen wird)
        }

        int disk = from.pop(); // Entfernt die oberste Scheibe vom Quell-Turm
        to.push(disk); // Legt die Scheibe auf den Ziel-Turm

        // Ausgabe der Bewegung in der Konsole
        System.out.println("Bewege Scheibe " + disk + " von " + fromName + " nach " + toName);

        // Aktualisiert die GUI
        printTowers(towerA, towerB, towerC); // Druckt den aktuellen Zustand der Türme
        hanoiPanel.repaint(); // Zeichnet das Panel neu

    }

    // Druckt den Zustand der Türme in die Konsole
    public static void printTowers(Stack<Integer> TowerA, Stack<Integer> TowerB, Stack<Integer> TowerC) 
    {
        System.out.println("Turm A: " + TowerA);
        System.out.println("Turm B: " + TowerB);
        System.out.println("Turm C: " + TowerC);
        System.out.println();
    }
}

class HanoiPanel extends JPanel {

    // Referenzen auf die Türme
    private Stack<Integer> towerA;
    private Stack<Integer> towerB;
    private Stack<Integer> towerC;

    // Konstruktor - Initialisiert das Panel mit den Türmen
    public HanoiPanel(int numDisks, Stack<Integer> towerA, Stack<Integer> towerB, Stack<Integer> towerC) {
        this.towerA = towerA; // Turm A
        this.towerB = towerB; // Turm B
        this.towerC = towerC; // Turm C
    }

    // Zeichnet das Panel, einschließlich der Türme und Scheiben
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Standard-Zeichenlogik aufrufen

        // Grundpositionen für die Türme
        int baseX = 200; // X-Position des ersten Turms
        int baseY = 500; // Y-Position der Basis

        // Türme zeichnen
        drawTower(g, baseX); // Turm A
        drawTower(g, baseX + 400); // Turm B
        drawTower(g, baseX + 800); // Turm C

        // Scheiben zeichnen
        drawDisks(g, towerA, baseX, baseY); // Scheiben von Turm A
        drawDisks(g, towerB, baseX + 400, baseY); // Scheiben von Turm B
        drawDisks(g, towerC, baseX + 800, baseY); // Scheiben von Turm C
    }

    // Zeichnet einen einzelnen Turm
    private void drawTower(Graphics g, int x) {
        g.setColor(Color.DARK_GRAY); // Farbe des Turms
        g.fillRect(x - 5, 200, 10, 300); // Turm als Rechteck zeichnen
    }

    // Zeichnet die Scheiben eines Turms
    private void drawDisks(Graphics g, Stack<Integer> tower, int baseX, int baseY) {
        int diskHeight = 20; // Höhe jeder Scheibe

        // Iteriert durch die Scheiben des Turms
        for (int i = 0; i < tower.size(); i++) {
            int disk = tower.get(i); // Größe der aktuellen Scheibe

            // Bestimmt die Breite einer Scheibe basierend auf ihrer Größe (Größe multipliziert mit 20)
            int diskWidth = disk * 20;
            
            int x = baseX - diskWidth / 2; // Zentriert die Scheibe horizontal auf dem Turm
            int y = baseY - ((i + 1) * diskHeight); // Berechnet die vertikale Position der Scheibe

            g.setColor(new Color(100 + disk * 10, 50, 150)); // Farbe der Scheibe basierend auf ihrer Größe
            g.fillRect(x, y, diskWidth, diskHeight); // Zeichnet die gefüllte Scheibe
            g.setColor(Color.BLACK); // Farbe für den Rand der Scheibe
            g.drawRect(x, y, diskWidth, diskHeight); // Zeichnet den Rand der Scheibe
        }
    }
}
