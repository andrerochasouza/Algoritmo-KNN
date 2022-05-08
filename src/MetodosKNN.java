import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * Criado por André da Rocha Souza on 5/5/2022.
 * 
 * Email - andredarochasouza.12345@gmail.com
 * 
 * @Author andre.rsouza
 * 
 */

public class MetodosKNN {

    /**
     * 
     * Método estático que carrega os dados do arquivo CSV separados com VIRGULA (,)
     * e
     * retorna uma lista de frases do tipo array de strings.
     * 
     * @Author andre.rsouza
     * 
     */

    static List<String[]> loadDataset(String pathArquive) throws Exception {

        if (pathArquive == null) {
            try {
                BufferedReader arquive = new BufferedReader(new FileReader(pathArquive));
                List<String[]> arq = new ArrayList<>();

                arq.stream().forEach(line -> {
                    try {
                        arquive.readLine().split(",");
                    } catch (IOException e) {
                        System.out.println("Erro ao ler a linha do arquivo, Erro: " + e.getMessage());
                    }
                });

                arquive.close();
                return arq;

            } catch (Exception e) {
                System.out.println("Erro ao abrir o arquivo no caminho especificado: " + pathArquive);
            }

        } else {
            throw new Exception("Path arquivo não pode ser nulo");
        }
        return null;

    }

    /**
     * 
     * Método estático que retorna uma tabela hash mapeada, com indices dos dados em
     * sequência do dataset, e os valores
     * doubles que foram calculados pela distancia entre a amostra e o dado do
     * dataset.
     * 
     * @Author andre.rsouza
     * 
     * 
     *         ARRUMAR MÉTODO - FOCO NO STRING[] DO DATASET E AMOSTRA
     * 
     */

    static ArrayList<Double> calcularDataset(ArrayList<String[]> dataset, String[] amostra) {

        ArrayList<Double> calculoDatasetEuclidiano = new ArrayList<>();

        for (int i = 0; i < dataset.size(); i++) {

            int[] treinamentoPosicao = new int[amostra.length];
            for (int j = 0; j < amostra.length; j++) {
                treinamentoPosicao[j] = dataset.get(i)[j];
            }

            System.out.println("Indice -- " + i + " | Valores euclidianos -- "
                    + distanciaEuclidiana(amostra, treinamentoPosicao));

            calculoDatasetEuclidiano.add(distanciaEuclidiana(amostra, treinamentoPosicao));
        }

        System.out.println("------------------------------");
        return calculoDatasetEuclidiano;
    }

    static HashMap<Integer, Double> mapeandoIndicesEuclidians(ArrayList<Double> arrCalculosEuclidianos) {

        // Acoplando o indice no calculo euclidiano
        HashMap<Integer, Double> map = new HashMap<>();
        for (int i = 0; i < arrCalculosEuclidianos.size(); i++) {
            map.put(i, arrCalculosEuclidianos.get(i));
        }

        // Printando o mapa de forma ordenada
        map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .forEachOrdered(x -> System.out.println(x.getKey() + " - " + x.getValue()));

        System.out.println("------------------------------");

        return map;
    }

    static void classificadorKNN(int k, ArrayList<int[]> dataset, int[] amostra) {

        HashMap<Integer, Double> map = mapeandoIndicesEuclidians(calcularDataset(dataset, amostra));

        // Pegando os indices dos k primeiros e coletando os tipos de personagem
        map = map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .limit(k)
                .collect(HashMap::new, (m, e) -> m.put(e.getKey(), e.getValue()), Map::putAll);

        int sumGuerreiro = 0;
        int sumMago = 0;

        for (Map.Entry<Integer, Double> entry : map.entrySet()) {

            System.out.println("Indice -- " + entry.getKey() + " | Valor -- " + entry.getValue() + " | Personagem -- "
                    + dataset.get(entry.getKey())[5]);

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

    /**
     * 
     * Método estático que calcula a distância euclidiana entre duas posições
     * de arrays de doubles.
     * 
     * @Author andre.rsouza
     * 
     */

    static double distanciaEuclidiana(int[] p1, int[] p2) {

        double bd = 0.0;

        for (int i = 0; i < p2.length; i++) {
            bd = Math.pow((p1[i] - p2[i]), 2) + bd;
        }

        return Math.sqrt(bd);
    }
}
