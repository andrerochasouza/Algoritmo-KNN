import java.util.ArrayList;

public class App {

    /**
     * Created by André da Rocha Souza on 5/5/2022.
     * 
     * andredarochasouza.12345@gmail.com
     * 
     * @Author andre.rsouza
     * 
     */

    public static void main(String[] args) throws Exception {

        ArrayList<Double[]> dataset = new ArrayList<>();

        Double a[] = { 1.0, 1.0, 3.0, 4.0, 5.0, 1.0 };
        Double b[] = { 2.0, 1.0, 4.0, 5.0, 5.0, 1.0 };
        Double c[] = { 1.0, 2.0, 5.0, 4.0, 3.0, 1.0 };
        Double d[] = { 1.0, 2.0, 5.0, 5.0, 5.0, 1.0 };
        Double e[] = { 5.0, 4.0, 2.0, 1.0, 1.0, 0.0 };
        Double f[] = { 4.0, 5.0, 1.0, 1.0, 2.0, 0.0 };
        Double g[] = { 5.0, 5.0, 1.0, 1.0, 1.0, 0.0 };

        dataset.add(a);
        dataset.add(b);
        dataset.add(c);
        dataset.add(d);
        dataset.add(e);
        dataset.add(f);
        dataset.add(g);

        Double[] amostra = { 3.0, 3.0, 2.0, 1.0, 1.0 };

        KNN.classificadorKNN(3, dataset, amostra);

    }
}
