public class AchtDamenProblem {

    boolean[][] feld = new boolean[8][8];


    public static void main(String[] args) {
        AchtDamenProblem adp = new AchtDamenProblem();
        adp.calculateField();
    }

    public void calculateField(){
        for (int x = 0; x < feld.length; x++) {
            for(int y = 0; y < feld.length - 1; y++){
                if (!otherFigureInReach(x, y)) {
                    feld[x][y] = true;
                    break;
                }

                this.printField();
            }
        }
    }


    public boolean otherFigureInReach(int x, int y){
        int min = ;
        int max = 0;

        

        if () {
            
        }
    }



    public void printField(){
        for (int x = 0; x < feld.length; x++) {
            for(int y = 0; y < feld.length - 1; y++){
                if (feld[x][y]) {
                    System.out.print("x ");
                }
                else{
                    System.out.print("o ");
                }
            }

            if (feld[x][feld.length-1]) {
                System.out.println("x");
            }
            else{
                System.out.println("o");
            }
            
        }
    }
}
