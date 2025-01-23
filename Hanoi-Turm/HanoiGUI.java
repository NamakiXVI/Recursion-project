import javax.swing.*;
import java.awt.*;
import java.util.Stack;

public class HanoiGUI extends JFrame {
    private static final int DISC_WIDTH_STEP = 20;
    private static final int DISC_HEIGHT = 20;
    private static final int BASE_WIDTH = 200;
    private static final int BASE_HEIGHT = 10;
    private static final int NUM_DISCS = 5;

    private JPanel panel;
    private Stack<Integer>[] towers;
    private JLabel[][] discLabels;

    public HanoiGUI() {
        setTitle("Turm von Hanoi");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 400);
        setResizable(false);

        // Initialize GUI panel
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawTowers(g);
                drawDiscs(g);
            }
        };
        panel.setBackground(Color.WHITE);
        add(panel);

        initializeGame();
        new Thread(() -> solveHanoi(NUM_DISCS, 0, 2, 1)).start();
    }

    private void initializeGame() {
        // Initialize towers
        towers = new Stack[3];
        for (int i = 0; i < 3; i++) {
            towers[i] = new Stack<>();
        }

        // Initialize tower A with discs
        for (int i = NUM_DISCS; i >= 1; i--) {
            towers[0].push(i);
        }
    }

    private void drawTowers(Graphics g) {
        int width = getWidth();
        int height = getHeight();
        int towerWidth = width / 3;

        // Draw bases
        g.setColor(Color.BLACK);
        for (int i = 0; i < 3; i++) {
            int baseX = i * towerWidth + (towerWidth - BASE_WIDTH) / 2;
            int baseY = height - BASE_HEIGHT;
            g.fillRect(baseX, baseY, BASE_WIDTH, BASE_HEIGHT);
        }
    }

    private void drawDiscs(Graphics g) {
        int width = getWidth();
        int height = getHeight();
        int towerWidth = width / 3;

        for (int t = 0; t < 3; t++) {
            int towerBaseX = t * towerWidth + (towerWidth - BASE_WIDTH) / 2;
            int towerBaseY = height - BASE_HEIGHT;
            int discCount = towers[t].size();

            for (int i = 0; i < discCount; i++) {
                int disc = towers[t].get(i);
                int discWidth = BASE_WIDTH - (NUM_DISCS - disc) * DISC_WIDTH_STEP;
                int discX = towerBaseX + (BASE_WIDTH - discWidth) / 2;
                int discY = towerBaseY - DISC_HEIGHT * (i + 1);
                g.setColor(Color.BLUE);
                g.fillRect(discX, discY, discWidth, DISC_HEIGHT);
                g.setColor(Color.BLACK);
                g.drawRect(discX, discY, discWidth, DISC_HEIGHT);
            }
        }
    }

    private void solveHanoi(int n, int from, int to, int aux) {
        if (n == 1) {
            moveDisc(from, to);
            return;
        }
        solveHanoi(n - 1, from, aux, to);
        moveDisc(from, to);
        solveHanoi(n - 1, aux, to, from);
    }

    private void moveDisc(int from, int to) {
        int disc = towers[from].pop();
        towers[to].push(disc);
        repaint();

        try {
            Thread.sleep(500); // Pause for animation
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            HanoiGUI frame = new HanoiGUI();
            frame.setVisible(true);
        });
    }
}