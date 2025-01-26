import java.awt.*;
import java.util.Stack;
import javax.swing.*;

public class HanoiGUI {
    static Stack<Integer> towerA = new Stack<>();
    static Stack<Integer> towerB = new Stack<>();
    static Stack<Integer> towerC = new Stack<>();

    static HanoiPanel hanoiPanel;

    public static void main(String[] args) {
        // Anzahl der Scheiben
        int n = 5;

        // Initialisierung des Turms A (Startturm)
        for (int i = n; i >= 1; i--) {
            towerA.push(i);
        }

        // GUI erstellen
        JFrame frame = new JFrame("Turm von Hanoi");
        hanoiPanel = new HanoiPanel(n);
        frame.add(hanoiPanel);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Algorithmus starten
        solveHanoi(n, towerA, towerC, towerB, "A", "C", "B");
    }

    // Rekursive Methode zur Lösung des Turms von Hanoi
    public static void solveHanoi(int n, Stack<Integer> from, Stack<Integer> to, Stack<Integer> aux, String fromName, String toName, String auxName) {
        if (n == 1) {
            moveDisk(from, to, fromName, toName);
            return;
        }

        solveHanoi(n - 1, from, aux, to, fromName, auxName, toName);
        moveDisk(from, to, fromName, toName);
        solveHanoi(n - 1, aux, to, from, auxName, toName, fromName);
    }

    // Hilfsmethode zum Bewegen einer Scheibe
    public static void moveDisk(Stack<Integer> from, Stack<Integer> to, String fromName, String toName) {
        int disk = from.pop();
        to.push(disk);
        System.out.println("Bewege Scheibe " + disk + " von " + fromName + " nach " + toName);

        // GUI aktualisieren
        hanoiPanel.updateTowers(towerA, towerB, towerC);
        try {
            Thread.sleep(500); // Animation verzögern
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class HanoiPanel extends JPanel {
    private Stack<Integer> towerA;
    private Stack<Integer> towerB;
    private Stack<Integer> towerC;
    private int numDisks;

    public HanoiPanel(int numDisks) {
        this.numDisks = numDisks;
        this.towerA = new Stack<>();
        this.towerB = new Stack<>();
        this.towerC = new Stack<>();
    }

    public void updateTowers(Stack<Integer> towerA, Stack<Integer> towerB, Stack<Integer> towerC) {
        this.towerA = (Stack<Integer>) towerA.clone();
        this.towerB = (Stack<Integer>) towerB.clone();
        this.towerC = (Stack<Integer>) towerC.clone();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        int baseX = 150;
        int baseY = 400;
        int pegWidth = 10;
        int pegHeight = 200;

        // Türme zeichnen
        g2d.setColor(Color.DARK_GRAY);
        g2d.fillRect(baseX, baseY - pegHeight, pegWidth, pegHeight); // Turm A
        g2d.fillRect(baseX + 250, baseY - pegHeight, pegWidth, pegHeight); // Turm B
        g2d.fillRect(baseX + 500, baseY - pegHeight, pegWidth, pegHeight); // Turm C

        // Scheiben zeichnen
        drawDisks(g2d, towerA, baseX, baseY);
        drawDisks(g2d, towerB, baseX + 250, baseY);
        drawDisks(g2d, towerC, baseX + 500, baseY);
    }

    private void drawDisks(Graphics2D g2d, Stack<Integer> tower, int baseX, int baseY) {
        int diskHeight = 20;
        int y = baseY;

        for (int i = tower.size() - 1; i >= 0; i--) {
            int diskWidth = tower.get(i) * 20;
            int x = baseX - diskWidth / 2 + 5;
            y -= diskHeight;

            // Farbe der Scheibe
            g2d.setColor(new Color(100 + tower.get(i) * 30, 50, 150));
            g2d.fillRect(x, y, diskWidth, diskHeight);
            g2d.setColor(Color.BLACK);
            g2d.drawRect(x, y, diskWidth, diskHeight);
        }
    }
}
