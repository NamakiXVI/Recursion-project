public class Hanoiturm 
{
    private static int Turm;
    String scheibe;

    public static void main(String[] args) 
    {
        Hanoiturm ht = new Hanoiturm();
        ht.createTurm(Turm);
    }

    public void createTurm(int hoehe)
    {
        for(int i = 0; i < hoehe; i++)
        {
            for(int j = 0; j<hoehe; j++)
            {
                scheibe += "o";
                System.out.println(scheibe);
            }
        }
    }
}
