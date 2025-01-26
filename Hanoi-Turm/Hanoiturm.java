import java.util.Stack;

public class Hanoiturm 
{
    static Stack<Integer> towerA = new Stack<>();
    static Stack<Integer> towerB = new Stack<>();
    static Stack<Integer> towerC = new Stack<>();

    public static void main(String[] args) 
    {
        // Anzahl der Scheiben
        int n = 5;

        // Die Türme als Arrays repräsentieren

        // Initialisierung des Turms A (Startturm)
        for (int i = n; i >= 1; i--) {
            towerA.push(i);
        }

        // Rekursiver Algorithmus
        System.out.println("Startzustand:");
        printTowers(towerA, towerB, towerC);

        solveHanoi(n, towerA, towerC, towerB, "A", "C", "B");

        System.out.println("Endzustand:");
        printTowers(towerA, towerB, towerC);
    }

    // Rekursive Methode zur Lösung des Turms von Hanoi
    public static void solveHanoi(int n, Stack<Integer> from, Stack<Integer> to, Stack<Integer> aux, String fromName, String toName, String auxName) 
    {
        // Basisfall: Wenn nur eine Scheibe zu bewegen ist
        if (n == 1) 
        {
            moveDisk(from, to, aux, fromName, toName);
            return;
        }

        // Rekursiver Schritt:
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
        int disk = from.pop();
        to.push(disk);
        System.out.println("Bewege Scheibe " + disk + " von " + fromName + " nach " + toName);
        printTowers(towerA, towerB, towerC); // Zeige den Zustand aller Türme
    }

    // Methode zur Anzeige der Türme in jedem Schritt
    public static void printTowers(Stack<Integer> TowerA, Stack<Integer> TowerB, Stack<Integer> TowerC) 
    {
        System.out.println("Turm A: " + TowerA);
        System.out.println("Turm B: " + TowerB);
        System.out.println("Turm C: " + TowerC);
        System.out.println();
    }
    
}
