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

// Klasse zur Darstellung der Türme und Scheiben im GUI
class HanoiPanel extends JPanel 
{
    private int numDisks; // Anzahl der Scheiben
    private Stack<Integer> towerA; // Referenz auf Turm A
    private Stack<Integer> towerB; // Referenz auf Turm B
    private Stack<Integer> towerC; // Referenz auf Turm C

    // Konstruktor
    public HanoiPanel(int numDisks, Stack<Integer> towerA, Stack<Integer> towerB, Stack<Integer> towerC) 
    {
        this.numDisks = numDisks;
        this.towerA = towerA;
        this.towerB = towerB;
        this.towerC = towerC;
    }

    // Zeichnet das Panel
    @Override
    protected void paintComponent(Graphics g) 
    {
        super.paintComponent(g); // Ruft die Standard-Zeichenlogik auf
        Graphics2D g2d = (Graphics2D) g;

        // Grundpositionen und Größen für Türme und Scheiben
        int baseX = 200; // Abstand vom linken Rand
        int baseY = 500; // Basislinie (unten)
        int towerWidth = 10; // Breite der Türme
        int towerHeight = 300; // Höhe der Türme

        // Zeichnet die drei Türme
        g2d.setColor(Color.DARK_GRAY);
        g2d.fillRect(baseX, baseY - towerHeight, towerWidth, towerHeight); // Turm A
        g2d.fillRect(baseX + 250, baseY - towerHeight, towerWidth, towerHeight); // Turm B
        g2d.fillRect(baseX + 500, baseY - towerHeight, towerWidth, towerHeight); // Turm C

        // Zeichnet die Scheiben
        drawDisks(g2d, towerA, baseX, baseY); // Scheiben von Turm A
        drawDisks(g2d, towerB, baseX + 250, baseY); // Scheiben von Turm B
        drawDisks(g2d, towerC, baseX + 500, baseY); // Scheiben von Turm C
    }

    // Zeichnet die Scheiben eines Turms
    private void drawDisks(Graphics2D g2d, Stack<Integer> tower, int baseX, int baseY) 
    {
        int diskHeight = 20; // Höhe jeder Scheibe
        int y = baseY; // Startpunkt an der Basis

        // Zeichnet die Scheiben von unten nach oben
        for (int i = 0; i < tower.size(); i++) 
        {
            int diskWidth = tower.get(i) * 15; // Breite der Scheibe (abhängig von Größe)
            int x = baseX - diskWidth / 2 + 5; // Zentriert die Scheibe

            // Farbe basierend auf der Größe
            g2d.setColor(new Color(100 + tower.get(i) * 10, 50, 150));
            g2d.fillRect(x, y - diskHeight, diskWidth, diskHeight); // Füllt die Scheibe
            g2d.setColor(Color.BLACK);
            g2d.drawRect(x, y - diskHeight, diskWidth, diskHeight); // Zeichnet einen Rahmen

            y -= diskHeight; // Setzt die nächste Scheibe höher
        }
    }   
}
