import java.awt.*; // Importiert alle GUI-Komponenten
import java.util.Stack; // Importiert die Stack-Datenstruktur
import javax.swing.*; // Importiert Swing-GUI-Komponenten

// Hauptklasse für das "Turm von Hanoi"-Programm
public class HanoiGUI 
{

    // Stacks repräsentieren die drei Türme (A, B, C)
    static Stack<Integer> towerA = new Stack<>();
    static Stack<Integer> towerB = new Stack<>();
    static Stack<Integer> towerC = new Stack<>();

    // Referenz auf das GUI-Panel zur Aktualisierung der Darstellung
    static HanoiPanel hanoiPanel;

// Hauptmethode - Einstiegspunkt des Programms
public static void main(String[] args) 
{
    // Home-Bildschirm erstellen
    JFrame homeFrame = new JFrame("Turm von Hanoi - Startbildschirm");
    homeFrame.setSize(1200, 800);
    homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    homeFrame.setLayout(new BorderLayout());

    JLabel title = new JLabel("Turm von Hanoi", JLabel.CENTER);
    title.setFont(new Font("Arial", Font.BOLD, 48));
    homeFrame.add(title, BorderLayout.CENTER);

    // Home-Bildschirm-Layout und Button-Erstellung
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Zentriert die Buttons
    buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 100)); // Setzt Abstand zwischen den Buttons

    JButton startButton = new JButton("Start");
    JButton exitButton = new JButton("Beenden");

    // Setze die feste Größe und mache sie responsiv
    Dimension buttonSize = new Dimension(200, 50);
    startButton.setPreferredSize(buttonSize);
    exitButton.setPreferredSize(buttonSize);
    startButton.setMaximumSize(buttonSize);
    exitButton.setMaximumSize(buttonSize);
    startButton.setMinimumSize(buttonSize);
    exitButton.setMinimumSize(buttonSize);

    buttonPanel.add(startButton);
    buttonPanel.add(exitButton);


    homeFrame.add(buttonPanel, BorderLayout.SOUTH);

    startButton.addActionListener(e -> {
        homeFrame.setVisible(false); // Versteckt das HomeFrame
        showMainFrame(homeFrame);   // Ruft das Hauptfenster auf und übergibt das HomeFrame
    });

    exitButton.addActionListener(e -> System.exit(0));

    homeFrame.setLocationRelativeTo(null);
    homeFrame.setVisible(true);
}

// Zeigt das Hauptfenster an und ermöglicht das Zurückkehren zum Home-Bildschirm
public static void showMainFrame(JFrame homeFrame) 
{
    JFrame frame = new JFrame("Turm von Hanoi");
    frame.setSize(1200, 800);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout(new BorderLayout());

    JPanel controlPanel = new JPanel(new GridLayout(2, 2, 10, 10));

    JLabel diskLabel = new JLabel("Anzahl der Scheiben:", JLabel.RIGHT);
    JTextField diskInput = new JTextField("5");

    JLabel delayLabel = new JLabel("Verzögerung (ms):", JLabel.RIGHT);
    JTextField delayInput = new JTextField("100");

    controlPanel.add(diskLabel);
    controlPanel.add(diskInput);
    controlPanel.add(delayLabel);
    controlPanel.add(delayInput);

    JPanel buttonPanel = new JPanel();
    JButton solveButton = new JButton("Sortierung starten");
    JButton backButton = new JButton("Zurück zum Start");
    buttonPanel.add(solveButton);
    buttonPanel.add(backButton);

    hanoiPanel = new HanoiPanel(towerA, towerB, towerC);
    frame.add(hanoiPanel, BorderLayout.CENTER);
    frame.add(controlPanel, BorderLayout.NORTH);
    frame.add(buttonPanel, BorderLayout.SOUTH);

    solveButton.addActionListener(e -> 
    {
        int n;
        int delay;
        
        try {
            n = Integer.parseInt(diskInput.getText());
            delay = Integer.parseInt(delayInput.getText());
        } catch (NumberFormatException ex) 
        {
            JOptionPane.showMessageDialog(frame, "Bitte gültige Zahlen eingeben!", "Eingabefehler", JOptionPane.ERROR_MESSAGE);
            return;
        }

        towerA.clear();
        towerB.clear();
        towerC.clear();

        for (int i = n; i >= 1; i--) 
        {
            towerA.push(i);
        }

        hanoiPanel.repaint();
        new Thread(() -> solveHanoi(n, towerA, towerC, towerB, delay)).start();
    });

    backButton.addActionListener(e -> 
    {
        frame.setVisible(false);  // Versteckt das Hauptfenster
        homeFrame.setVisible(true); // Zeigt das HomeFrame wieder an
    });

    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
}


    // Rekursiver Algorithmus zur Lösung des Turm-von-Hanoi-Problems
    public static void solveHanoi(int n, Stack<Integer> from, Stack<Integer> to, Stack<Integer> aux, int delay) 
    {
        if (n == 1) {
            moveDisk(from, to, delay);
            return;
        }
        solveHanoi(n - 1, from, aux, to, delay);
        moveDisk(from, to, delay);
        solveHanoi(n - 1, aux, to, from, delay);
    }

    // Bewegt eine Scheibe von einem Turm zu einem anderen und aktualisiert die GUI
    public static void moveDisk(Stack<Integer> from, Stack<Integer> to, int delay) 
    {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) 
        {
            e.printStackTrace();
        }
        to.push(from.pop());
        hanoiPanel.repaint();
    }
}

// GUI-Komponente zur Darstellung der Türme und Scheiben
class HanoiPanel extends JPanel 
{

    private Stack<Integer> towerA;
    private Stack<Integer> towerB;
    private Stack<Integer> towerC;

    public HanoiPanel(Stack<Integer> towerA, Stack<Integer> towerB, Stack<Integer> towerC) 
    {
        this.towerA = towerA;
        this.towerB = towerB;
        this.towerC = towerC;
    }

    @Override
    protected void paintComponent(Graphics g) 
    {
        super.paintComponent(g);

        int panelWidth = getWidth();
        int panelHeight = getHeight();

        int baseY = panelHeight - 50;

        int towerWidth = 10;
        int towerHeight = panelHeight / 2;

        int towerAX = panelWidth / 4 - towerWidth / 2;
        int towerBX = panelWidth / 2 - towerWidth / 2;
        int towerCX = 3 * panelWidth / 4 - towerWidth / 2;

        drawTower(g, towerAX, baseY, towerWidth, towerHeight);
        drawTower(g, towerBX, baseY, towerWidth, towerHeight);
        drawTower(g, towerCX, baseY, towerWidth, towerHeight);

        drawDisks(g, towerA, panelWidth / 4, baseY);
        drawDisks(g, towerB, panelWidth / 2, baseY);
        drawDisks(g, towerC, 3 * panelWidth / 4, baseY);
    }

    private void drawTower(Graphics g, int x, int baseY, int width, int height) 
    {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(x, baseY - height, width, height);
    }

    private void drawDisks(Graphics g, Stack<Integer> tower, int centerX, int baseY) 
    {
        int diskHeight = 20;

        for (int i = 0; i < tower.size(); i++) 
        {
            int disk = tower.get(i);
            int diskWidth = disk * 20;

            int x = centerX - diskWidth / 2;
            int y = baseY - ((i + 1) * diskHeight);

            g.setColor(new Color(100 + disk * 5, 50, 150));
            g.fillRect(x, y, diskWidth, diskHeight);
            g.setColor(Color.BLACK);
            g.drawRect(x, y, diskWidth, diskHeight);
        }
    }
}