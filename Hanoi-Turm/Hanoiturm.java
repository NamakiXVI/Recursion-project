import java.util.Stack;

public class Hanoiturm 
{

    public static void main(String[] args) {
        // Anzahl der Scheiben
        int n = 3;

        // Die Türme als Arrays repräsentieren
        Stack<Integer> towerA = new Stack<>();
        Stack<Integer> towerB = new Stack<>();
        Stack<Integer> towerC = new Stack<>();

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
    public static void solveHanoi(int n, Stack<Integer> from, Stack<Integer> to, Stack<Integer> aux, String fromName, String toName, String auxName) {
        // Basisfall: Wenn nur eine Scheibe zu bewegen ist
        if (n == 1) {
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
    public static void moveDisk(Stack<Integer> from, Stack<Integer> to, Stack<Integer> aux, String fromName, String toName) {
        int disk = from.pop();
        to.push(disk);
        System.out.println("Bewege Scheibe " + disk + " von " + fromName + " nach " + toName);
        printTowers(from, to, aux); // Zeige den Zustand aller Türme
    }

    // Methode zur Anzeige der Türme in jedem Schritt
    public static void printTowers(Stack<Integer> towerA, Stack<Integer> towerB, Stack<Integer> towerC) {
        System.out.println("Turm A: " + towerA);
        System.out.println("Turm B: " + towerB);
        System.out.println("Turm C: " + towerC);
        System.out.println();
    }
    
}
