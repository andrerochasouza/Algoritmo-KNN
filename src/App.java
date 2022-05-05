import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

        int a[] = { 1, 1, 3, 4, 5, 1 };
        int b[] = { 2, 1, 4, 5, 5, 1 };
        int c[] = { 1, 2, 5, 4, 3, 1 };
        int d[] = { 1, 2, 5, 5, 5, 1 };
        int e[] = { 5, 4, 2, 1, 1, 0 };
        int f[] = { 4, 5, 1, 1, 2, 0 };
        int g[] = { 5, 5, 1, 1, 1, 0 };

        ArrayList<int[]> dataset = new ArrayList<>();

        int[] amostra = { 3, 3, 2, 1, 1 };

        classificadorKNN(3, dataset, amostra);

    }

    static ArrayList<Double> calcularDataset(ArrayList<int[]> dataset, int[] amostra) {

        ArrayList<Double> calculoDatasetEuclidiano = new ArrayList<>();

        for (int i = 0; i < dataset.size(); i++) {

            int[] treinamentoPosicao = new int[dataset.size() - 1];
            for (int j = 0; j < (dataset.size() - 1); j++) {
                treinamentoPosicao[j] = dataset.get(i)[j];
            }

            System.out.println("Indice -- " + i + " | Valores euclidianos -- "
                    + distanciaEuclidiana(amostra, treinamentoPosicao));

            calculoDatasetEuclidiano.add(distanciaEuclidiana(amostra, treinamentoPosicao));
        }

        System.out.println("------------------------------");
        return calculoDatasetEuclidiano;
    }

    static void classificadorKNN(int k, ArrayList<int[]> dataset, int[] amostra) {

        // Acoplando o indice no calculo euclidiano
        LinkedHashMap<Integer, Double> map = new LinkedHashMap<>();
        for (int i = 0; i < ordenacaoPorDistancia.size(); i++) {
            map.put(i, ordenacaoPorDistancia.get(i));
        }

        // Printando o mapa de forma ordenada
        map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .forEachOrdered(x -> System.out.println(x.getKey() + " - " + x.getValue()));

        System.out.println("------------------------------");

        // Pegando os indices dos k primeiros e coletando os tipos de personagem
        map = map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .limit(k)
                .collect(LinkedHashMap::new, (m, e) -> m.put(e.getKey(), e.getValue()), Map::putAll);

        int sumGuerreiro = 0;
        int sumMago = 0;

        for (Map.Entry<Integer, Double> entry : map.entrySet()) {

            System.out.println("Indice -- " + entry.getKey() + " | Valor -- " + entry.getValue() + " | Personagem -- "
                    + dataset.get(entry.getKey()).getTipoPersonagemEnum());

            if (dataset.get(entry.getKey()).tipoPersonagemEnum == TipoPersonagemEnum.GUERREIRO) {
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
