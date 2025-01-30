// Importiert die Stack-Datenstruktur aus dem Java Collections Framework
import java.util.Stack;

// Hauptklasse für das Turm-von-Hanoi-Programm
public class Hanoiturm 
{
    // Stacks repräsentieren die drei Türme A B und C
    static Stack<Integer> towerA = new Stack<>(); 
    static Stack<Integer> towerB = new Stack<>(); 
    static Stack<Integer> towerC = new Stack<>(); 

    // Hauptmethode die das Programm startet
    public static void main(String[] args) 
    {
        // Anzahl der Scheiben
        int n = 5; 

        // Initialisierung des Turms A (Startturm)
        for (int i = n; i >= 1; i--) 
        {
            towerA.push(i); // Füllt Turm A mit Scheiben
        }

        // Zeigt den Startzustand der Türme an
        System.out.println("Startzustand:"); 
        printTowers(towerA, towerB, towerC); 

        // Startet den rekursiven Algorithmus zur Lösung des Problems
        solveHanoi(n, towerA, towerC, towerB, "A", "C", "B"); 

        // Zeigt den Endzustand der Türme an
        System.out.println("Endzustand:"); 
        printTowers(towerA, towerB, towerC); 
    }

    // Rekursive Methode zur Lösung des Turms von Hanoi
    public static void solveHanoi(int n, Stack<Integer> from, Stack<Integer> to, Stack<Integer> aux, String fromName, String toName, String auxName) 
    {
        // Basisfall: Wenn nur eine Scheibe zu bewegen ist
        if (n == 1) 
        {
            moveDisk(from, to, aux, fromName, toName); // Bewegt die Scheibe direkt zum Zielturm
            return; 
        }

        // Rekursive Schritte:
        // 1. Bewege die oberen n-1 Scheiben vom Startturm zum Hilfsturm
        solveHanoi(n - 1, from, aux, to, fromName, auxName, toName); 

        // 2. Bewege die größte Scheibe vom Startturm zum Zielturm
        moveDisk(from, to, aux, fromName, toName); 

        // 3. Bewege die n-1 Scheiben vom Hilfsturm zum Zielturm
        solveHanoi(n - 1, aux, to, from, auxName, toName, fromName); 
    }

    // Hilfsmethode zum Bewegen einer Scheibe von einem Turm zum anderen
    public static void moveDisk(Stack<Integer> from, Stack<Integer> to, Stack<Integer> aux, String fromName, String toName) 
    {
        int disk = from.pop(); // Entfernt die oberste Scheibe vom Startturm
        to.push(disk); // Legt die Scheibe auf den Zielturm
        System.out.println("Bewege Scheibe " + disk + " von " + fromName + " nach " + toName); // Gibt die Bewegung aus
        printTowers(towerA, towerB, towerC); // Zeigt den Zustand aller Türme nach der Bewegung
    }

    // Methode zur Anzeige der Türme in jedem Schritt
    public static void printTowers(Stack<Integer> TowerA, Stack<Integer> TowerB, Stack<Integer> TowerC) 
    {
        System.out.println("Turm A: " + TowerA); // Zeigt den Inhalt von Turm A
        System.out.println("Turm B: " + TowerB); // Zeigt den Inhalt von Turm B
        System.out.println("Turm C: " + TowerC); // Zeigt den Inhalt von Turm C
        System.out.println(); // Leerzeile für bessere Lesbarkeit
    }
}