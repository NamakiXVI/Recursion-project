import java.awt.*;
import java.util.Stack;
import javax.swing.*;

public class HanoiturmGUI 
{
    static Stack<Integer> towerA = new Stack<>();
    static Stack<Integer> towerB = new Stack<>();
    static Stack<Integer> towerC = new Stack<>();

    static HanoiPanel hanoiPanel;

    public static void main(String[] args) 
    {
        int n = 5; // Anzahl der Scheiben

        // Türme erstellen
        for (int i = n; i >= 1; i--) 
        {
            towerA.push(i);
        }

        // GUI erstellen
        JFrame frame = new JFrame("Turm von Hanoi");
        hanoiPanel = new HanoiPanel(n, towerA, towerB, towerC);
        frame.add(hanoiPanel);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        System.out.println("Startzustand:");
        printTowers(towerA, towerB, towerC);

        solveHanoi(n, towerA, towerC, towerB, "A", "C", "B");

        System.out.println("Endzustand:");
        printTowers(towerA, towerB, towerC);
    }

    public static void solveHanoi(int n, Stack<Integer> from, Stack<Integer> to, Stack<Integer> aux, String fromName, String toName, String auxName) 
    {
        if (n == 1) 
        {
            moveDisk(from, to, fromName, toName);
            return;
        }

        solveHanoi(n - 1, from, aux, to, fromName, auxName, toName);
        moveDisk(from, to, fromName, toName);
        solveHanoi(n - 1, aux, to, from, auxName, toName, fromName);
    }

    public static void moveDisk(Stack<Integer> from, Stack<Integer> to, String fromName, String toName) 
    {
        int disk = from.pop();
        to.push(disk);
        System.out.println("Bewege Scheibe " + disk + " von " + fromName + " nach " + toName);
        printTowers(towerA,towerB,towerC);
        hanoiPanel.repaint();
        try 
        {
            Thread.sleep(500); // Animation verzögern
        } catch (InterruptedException e) {
        }
    }

    public static void printTowers(Stack<Integer> TowerA, Stack<Integer> TowerB, Stack<Integer> TowerC) 
    {
        System.out.println("Turm A: " + TowerA);
        System.out.println("Turm B: " + TowerB);
        System.out.println("Turm C: " + TowerC);
        System.out.println();
    }
}

class HanoiPanel extends JPanel 
{
    private int numDisks;
    private Stack<Integer> towerA;
    private Stack<Integer> towerB;
    private Stack<Integer> towerC;

    public HanoiPanel(int numDisks, Stack<Integer> towerA, Stack<Integer> towerB, Stack<Integer> towerC) 
    {
        this.numDisks = numDisks;
        this.towerA = towerA;
        this.towerB = towerB;
        this.towerC = towerC;
    }

    @Override
    protected void paintComponent(Graphics g) 
    {
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
        drawDisks(g2d, towerB, baseX + 250, baseY - 1);
        drawDisks(g2d, towerC, baseX + 500, baseY - 1);
    }
    
    private void drawDisks(Graphics2D g2d, Stack<Integer> tower, int baseX, int baseY) 
    {
        int diskHeight = 20; // Höhe jeder Scheibe
        int y = baseY; // Startpunkt an der Basis
        
        System.out.println(tower.size());
        // Zeichnen von unten nach oben
        for (int i = 0; i < tower.size(); i++) 
        {
            int diskWidth = tower.get(i) * 20; // Breite der Scheibe
            int x = baseX - diskWidth / 2 + 5; // Zentrierung der Scheibe
    
            // Farbe der Scheibe basierend auf Größe
            g2d.setColor(new Color(100 + tower.get(i) * 20, 50, 150));
            g2d.fillRect(x, y - diskHeight, diskWidth, diskHeight); // Scheibe zeichnen
            g2d.setColor(Color.BLACK);
            g2d.drawRect(x, y - diskHeight, diskWidth, diskHeight); // Rahmen zeichnen
    
            y -= diskHeight; // Nächste Scheibe höher platzieren
        }
    }   
}