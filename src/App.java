import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class App {

    /**
     * Created by André da Rocha Souza on 5/5/2022.
     * 
     * andredarochasouza.12345@gmail.com
     * 
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

        int[] amostra = { 3, 3, 2, 1, 1};

        classificadorKNN(3, dataset, amostra);

    }



    static ArrayList<Double> calcularDataset(ArrayList<int[]> dataset, int[] amostra) {

        ArrayList<Double> calculoDatasetEuclidiano = new ArrayList<>();

        for (int i = 0; i < dataset.size(); i++) {

            int[] treinamentoPosicao = new int[dataset.get(0)[0] - 1];
            for (int j = 0; j < dataset.get(0)[0] - 1; j++) {
                treinamentoPosicao[j] = dataset.get(i)[j];
            }

            System.out.println("Indice -- " + i + " | Valores euclidianos -- "
                    + distanciaEuclidiana(amostra, treinamentoPosicao));

            calculoDatasetEuclidiano.add(distanciaEuclidiana(amostra, treinamentoPosicao));
        }

        System.out.println("------------------------------");
        return calculoDatasetEuclidiano;
    }




    static HashMap<Integer, Double> mapeamentoHash(ArrayList<Double> listaCalculosEuclidiano) {

        // Acoplando o indice no valores (calculo euclidiano)
        HashMap<Integer, Double> map = new HashMap<>();
        for (int i = 0; i < listaCalculosEuclidiano.size(); i++) {
            map.put(i, listaCalculosEuclidiano.get(i));
        }

        // Printando o mapa de forma ordenada
        map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .forEachOrdered(x -> System.out.println(x.getKey() + " - " + x.getValue()));

        System.out.println("------------------------------");

        return map;

    }

    static void classificadorKNN(int k, ArrayList<int[]> dataset, int[] amostra) {

        // Mapeando os calculos euclidianos pela sequência do dataset
        HashMap<Integer, Double> map = mapeamentoHash(calcularDataset(dataset, amostra));


        // Pegando os indices dos k primeiros e coletando os tipos de personagem
        map = map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .limit(k)
                .collect(HashMap::new, (m, e) -> m.put(e.getKey(), e.getValue()), Map::putAll);

        int sumGuerreiro = 0;
        int sumMago = 0;

        for (Map.Entry<Integer, Double> entry : map.entrySet()) {

            System.out.println("Indice -- " + entry.getKey() + " | Valor -- " + entry.getValue() + " | Personagem -- " + dataset.get(entry.getKey())[5]);

            if (dataset.get(entry.getKey())[5] == 0) {
                sumGuerreiro++;
            } else {
                sumMago++;
            }
        }

        System.out.println("------------------------------");

        if (sumGuerreiro > sumMago) {
            System.out.println("Amostra: Guerreiro");
        } else {
            System.out.println("Amostra: Mago");
        }
    }

    static double distanciaEuclidiana(int[] p1, int[] p2) {

        double bd = 0.0;

        for (int i = 0; i < p2.length; i++) {
            bd = Math.pow((p1[i] - p2[i]), 2) + bd;
        }

        return Math.sqrt(bd);
    }
}
