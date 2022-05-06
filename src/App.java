import java.util.ArrayList;

public class App {

    /**
     * Created by André da Rocha Souza on 5/5/2022.
     * 
     * andredarochasouza.12345@gmail.com
     */

    public static void main(String[] args) throws Exception {

        /**
         * 
         * Dataset especifica os niveis de habilidades que Magos e Guerreiros têm,
         * assim realizando a definição da próxima amostra
         * 
         */

        ArrayList<int[]> dataset = new ArrayList<>();

        int a[] = { 1, 1, 3, 4, 5, 1 };
        int b[] = { 2, 1, 4, 5, 5, 1 };
        int c[] = { 1, 2, 5, 4, 3, 1 };
        int d[] = { 1, 2, 5, 5, 5, 1 };
        int e[] = { 5, 4, 2, 1, 1, 0 };
        int f[] = { 4, 5, 1, 1, 2, 0 };
        int g[] = { 5, 5, 1, 1, 1, 0 };

        dataset.add(a);
        dataset.add(b);
        dataset.add(c);
        dataset.add(d);
        dataset.add(e);
        dataset.add(f);
        dataset.add(g);

        int[] amostra = { 3, 3, 2, 1, 1 };

        MetodosKNN.classificadorKNN(3, dataset, amostra);

    }
}
