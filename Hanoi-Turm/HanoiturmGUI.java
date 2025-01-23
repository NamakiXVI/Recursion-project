import javax.swing.*;

import javafx.scene.layout.Border;

import java.awt.*;
import java.util.Stack;

public class HanoiturmGUI {
    static Stack<Integer> towerA = new Stack<>();
    static Stack<Integer> towerB = new Stack<>();
    static Stack<Integer> towerC = new Stack<>();

    static HanoiPanel hanoiPanel;

    public static void main(String[] args) {
        int n = 4; // Anzahl der Scheiben

        // Türme erstellen
        for (int i = n; i >= 1; i--) {
            towerA.push(i);
        }

        // GUI erstellen
        JFrame frame = new JFrame("Turm von Hanoi");
        hanoiPanel = new HanoiPanel(n, towerA, towerB, towerC);
        frame.add(hanoiPanel);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        JButton solveButton = new JButton("Lösen");
        solveButton.addActionListener(e -> solveHanoi(n, towerA, towerC, towerB, "A", "C", "B"));
        JPanel btnPanel = new JPanel(new BorderLayout());
        btnPanel.setBackground(Color.GRAY);

        btnPanel.add(solveButton, BorderLayout.NORTH);
        frame.add(btnPanel, BorderLayout.NORTH);

        // Rekursiver Algorithmus
//        solveHanoi(n, towerA, towerC, towerB, "A", "C", "B");
    }



    public static void solveHanoi(int n, Stack<Integer> from, Stack<Integer> to, Stack<Integer> aux, String fromName, String toName, String auxName) {
        if (n == 1) {
            moveDisk(from, to, fromName, toName);
            return;
        }

        solveHanoi(n - 1, from, aux, to, fromName, auxName, toName);
        moveDisk(from, to, fromName, toName);
        solveHanoi(n - 1, aux, to, from, auxName, toName, fromName);
    }

    public static void moveDisk(Stack<Integer> from, Stack<Integer> to, String fromName, String toName) {
        int disk = from.pop();
        to.push(disk);
        System.out.println("Bewege Scheibe " + disk + " von " + fromName + " nach " + toName);
        printTowers(towerA,towerB,towerC);
        updateGUI();
        try {
            Thread.sleep(500); // Animation verzögern
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void updateGUI() {
        hanoiPanel.repaint();
    }

    public static void printTowers(Stack<Integer> TowerA, Stack<Integer> TowerB, Stack<Integer> TowerC) {
        System.out.println("Turm A: " + TowerA);
        System.out.println("Turm B: " + TowerB);
        System.out.println("Turm C: " + TowerC);
        System.out.println();
    }
}

class HanoiPanel extends JPanel {
    private int numDisks;
    private Stack<Integer> towerA;
    private Stack<Integer> towerB;
    private Stack<Integer> towerC;

    public HanoiPanel(int numDisks, Stack<Integer> towerA, Stack<Integer> towerB, Stack<Integer> towerC) {
        this.numDisks = numDisks;
        this.towerA = towerA;
        this.towerB = towerB;
        this.towerC = towerC;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int baseX = 100; // Startposition des ersten Turms
        int baseY = 400; // Grundlinie für die Türme
        int towerWidth = 20; // Breite eines Turms
        int towerHeight = 300; // Höhe eines Turms

        // Türme zeichnen
        drawTower(g, baseX, baseY, towerWidth, towerHeight, towerA);
        drawTower(g, baseX + 250, baseY, towerWidth, towerHeight, towerB);
        drawTower(g, baseX + 500, baseY, towerWidth, towerHeight, towerC);
    }

    private void drawTower(Graphics g, int x, int y, int towerWidth, int towerHeight, Stack<Integer> tower) {
        // Turm zeichnen
        g.setColor(Color.BLACK);
        g.fillRect(x + towerWidth / 2, y - towerHeight, towerWidth, towerHeight);

        // Scheiben zeichnen
        int offsetY = 0;
        for (int disk : tower) {
            int diskWidth = disk * 40;
            g.setColor(Color.RED);
            g.fillRect(x - diskWidth / 2 + towerWidth / 2 + 10, y - offsetY - 20, diskWidth, 20);
            offsetY += 20;
        }
    }
}