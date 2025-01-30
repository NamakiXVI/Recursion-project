// Importiert alle notwendigen Java-Bibliotheken für die GUI und Datenstrukturen
import java.awt.*;
import java.util.Stack; 
import javax.swing.*; 

// Hauptklasse für das Turm-von-Hanoi-Spiel
public class HanoiGUI 
{
    // Stacks repräsentieren die drei Türme A B und C
    static Stack<Integer> towerA = new Stack<>(); 
    static Stack<Integer> towerB = new Stack<>(); 
    static Stack<Integer> towerC = new Stack<>(); 

    // Referenz auf das Panel das die Türme und Scheiben zeichnet
    static HanoiPanel hanoiPanel; 

    // Hauptmethode die das Programm startet
    public static void main(String[] args) 
    {
        // Erstellt das Hauptfenster mit dem Titel Turm von Hanoi
        JFrame frame = new JFrame("Turm von Hanoi"); 
        frame.setSize(1200, 800); // Setzt die Größe des Fensters
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Beendet das Programm beim Schließen des Fensters
        frame.setLayout(new BorderLayout()); // Setzt das Layout des Fensters

        // Erstellt ein Panel für die Eingabefelder und Labels
        JPanel controlPanel = new JPanel(new GridLayout(2, 2, 10, 10)); 
        JLabel diskLabel = new JLabel("Anzahl der Scheiben:", JLabel.RIGHT); 
        JTextField diskInput = new JTextField("5"); 
        JLabel delayLabel = new JLabel("Verzögerung (ms):", JLabel.RIGHT); 
        JTextField delayInput = new JTextField("100"); 

        // Fügt die Labels und Eingabefelder zum controlPanel hinzu
        controlPanel.add(diskLabel); 
        controlPanel.add(diskInput); 
        controlPanel.add(delayLabel); 
        controlPanel.add(delayInput); 

        // Erstellt ein Panel für die Buttons
        JPanel buttonPanel = new JPanel(); 
        JButton solveButton = new JButton("Sortierung starten"); 
        buttonPanel.add(solveButton); 

        // Erstellt das HanoiPanel das die Türme und Scheiben zeichnet
        hanoiPanel = new HanoiPanel(towerA, towerB, towerC); 
        frame.add(hanoiPanel, BorderLayout.CENTER); 
        frame.add(controlPanel, BorderLayout.NORTH); 
        frame.add(buttonPanel, BorderLayout.SOUTH); 

        // Fügt einen ActionListener zum Button hinzu der die Sortierung startet
        solveButton.addActionListener(e -> 
        {
            int n; // Anzahl der Scheiben
            int delay; // Verzögerung zwischen den Bewegungen
            
            try {
                // Versucht die Eingaben in Zahlen umzuwandeln
                n = Integer.parseInt(diskInput.getText()); 
                delay = Integer.parseInt(delayInput.getText()); 
            } catch (NumberFormatException ex) 
            {
                // Zeigt eine Fehlermeldung an wenn die Eingaben ungültig sind
                JOptionPane.showMessageDialog(frame, "Bitte gültige Zahlen eingeben!", "Eingabefehler", JOptionPane.ERROR_MESSAGE); 
                return; 
            }

            // Leert die Türme für einen neuen Durchlauf
            towerA.clear(); 
            towerB.clear(); 
            towerC.clear(); 

            // Füllt Turm A mit Scheiben
            for (int i = n; i >= 1; i--) 
            {
                towerA.push(i); 
            }

            hanoiPanel.repaint(); // Aktualisiert die GUI
            new Thread(() -> solveHanoi(n, towerA, towerC, towerB, delay)).start(); // Startet den Lösungsalgorithmus in einem neuen Thread
        });

        // Druckt den Endzustand der Türme in die Konsole
        System.out.println("Endzustand:"); 
        printTowers(towerA, towerB, towerC); 

        frame.setLocationRelativeTo(null); // Zentriert das Fenster auf dem Bildschirm
        frame.setVisible(true); // Macht das Fenster sichtbar
    }

    // Rekursiver Algorithmus zur Lösung des Turm-von-Hanoi-Problems
    public static void solveHanoi(int n, Stack<Integer> from, Stack<Integer> to, Stack<Integer> aux, int delay) 
    {
        if (n == 1) {
            moveDisk(from, to, delay); // Bewegt die letzte Scheibe direkt zum Zielturm
            return; 
        }
        solveHanoi(n - 1, from, aux, to, delay); // Bewegt n-1 Scheiben vom Startturm zum Hilfsturm
        moveDisk(from, to, delay); // Bewegt die verbleibende Scheibe zum Zielturm
        solveHanoi(n - 1, aux, to, from, delay); // Bewegt die n-1 Scheiben vom Hilfsturm zum Zielturm
    }

    // Bewegt eine Scheibe von einem Turm zu einem anderen und aktualisiert die GUI
    public static void moveDisk(Stack<Integer> from, Stack<Integer> to, int delay) 
    {
        try {
            Thread.sleep(delay); // Pausiert den Thread für die angegebene Verzögerung
        } catch (InterruptedException e) 
        {
            e.printStackTrace(); // Druckt den Stacktrace falls der Thread unterbrochen wird
        }
        to.push(from.pop()); // Bewegt die oberste Scheibe vom Startturm zum Zielturm

        // Aktualisiert die GUI
        printTowers(towerA, towerB, towerC); 
        hanoiPanel.repaint(); 
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

// GUI-Komponente zur Darstellung der Türme und Scheiben
class HanoiPanel extends JPanel 
{
    private Stack<Integer> towerA; // Referenz auf Turm A
    private Stack<Integer> towerB; // Referenz auf Turm B
    private Stack<Integer> towerC; // Referenz auf Turm C

    // Konstruktor der die Referenzen auf die Türme übernimmt
    public HanoiPanel(Stack<Integer> towerA, Stack<Integer> towerB, Stack<Integer> towerC) 
    {
        this.towerA = towerA; 
        this.towerB = towerB; 
        this.towerC = towerC; 
    }

    // Überschreibt die paintComponent-Methode um die Türme und Scheiben zu zeichnen
    @Override
    protected void paintComponent(Graphics g) 
    {
        super.paintComponent(g); 

        int panelWidth = getWidth(); // Breite des Panels
        int panelHeight = getHeight(); // Höhe des Panels

        int baseY = panelHeight - 50; // Y-Koordinate der Basis der Türme

        int towerWidth = 10; // Breite der Türme
        int towerHeight = panelHeight / 2; // Höhe der Türme

        // X-Koordinaten der drei Türme
        int towerAX = panelWidth / 4 - towerWidth / 2; 
        int towerBX = panelWidth / 2 - towerWidth / 2; 
        int towerCX = 3 * panelWidth / 4 - towerWidth / 2; 

        // Zeichnet die drei Türme
        drawTower(g, towerAX, baseY, towerWidth, towerHeight); 
        drawTower(g, towerBX, baseY, towerWidth, towerHeight); 
        drawTower(g, towerCX, baseY, towerWidth, towerHeight); 

        // Zeichnet die Scheiben auf den Türmen
        drawDisks(g, towerA, panelWidth / 4, baseY); 
        drawDisks(g, towerB, panelWidth / 2, baseY); 
        drawDisks(g, towerC, 3 * panelWidth / 4, baseY); 
    }

    // Methode zum Zeichnen eines Turms
    private void drawTower(Graphics g, int x, int baseY, int width, int height) 
    {
        g.setColor(Color.DARK_GRAY); 
        g.fillRect(x, baseY - height, width, height); 
    }

    // Methode zum Zeichnen der Scheiben auf einem Turm
    private void drawDisks(Graphics g, Stack<Integer> tower, int centerX, int baseY) 
    {
        int diskHeight = 20; // Höhe jeder Scheibe

        // Iteriert durch alle Scheiben im Turm
        for (int i = 0; i < tower.size(); i++) 
        {
            int disk = tower.get(i); // Größe der aktuellen Scheibe
            int diskWidth = disk * 20; // Breite der Scheibe

            // Berechnet die Position der Scheibe
            int x = centerX - diskWidth / 2; 
            int y = baseY - ((i + 1) * diskHeight); 

            // Setzt die Farbe der Scheibe
            g.setColor(new Color(100 + disk * 5, 50, 150)); 
            g.fillRect(x, y, diskWidth, diskHeight); 
            g.setColor(Color.BLACK); 
            g.drawRect(x, y, diskWidth, diskHeight); 
        }
    }
}