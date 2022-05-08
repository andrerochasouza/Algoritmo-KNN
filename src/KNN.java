/**
 * 
 * Criado por André da Rocha Souza em 5/5/2022.
 * 
 * Email - andredarochasouza.12345@gmail.com
 * 
 * 
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KNN {

    /**
     * 
     * Método estático que carrega os dados do arquivo CSV separados com VIRGULA (,)
     * e
     * retorna uma lista de frases do tipo array de strings.
     * 
     * @Author andre.rsouza
     * 
     */

    static ArrayList<String[]> loadDataset(String pathArquive) throws Exception {

        if (pathArquive == null) {
            try {
                BufferedReader arquive = new BufferedReader(new FileReader(pathArquive));
                ArrayList<String[]> arq = new ArrayList<>();

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
            throw new Exception("Caminho do arquivo não está sendo achado ou está nulo");
        }
        return null;

    }

    /**
     * 
     * Método estático que calcula a distância euclidiana entre duas posições
     * de arrays de doubles.
     * 
     * @Author andre.rsouza
     * 
     */

    static Double distanciaEuclidiana(Double[] p1, Double[] p2) {

        double bd = 0.0;

        try {

            for (int i = 0; i < p2.length; i++) {
                bd = Math.pow((p1[i] - p2[i]), 2) + bd;
            }

        } catch (Exception e) {
            System.out.println("Erro durante o calculo euclidiano: " + e.getMessage());
        }

        return Math.sqrt(bd);
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
     */

    static ArrayList<Double> calcularDatasetPelaAmostra(ArrayList<Double[]> dataset, Double[] amostra) {

        ArrayList<Double> calculoDatasetEuclidiano = new ArrayList<>();

        for (int i = 0; i < dataset.size(); i++) {

            Double[] treinamentoPosicao = new Double[amostra.length];
            for (int j = 0; j < amostra.length; j++) {
                treinamentoPosicao[j] = dataset.get(i)[j];
            }

            System.out.println("Indice -- " + i + " | Valores euclidianos -- " + distanciaEuclidiana(amostra, treinamentoPosicao));
            calculoDatasetEuclidiano.add(distanciaEuclidiana(amostra, treinamentoPosicao));

        }

        System.out.println("------------------------------");
        return calculoDatasetEuclidiano;
    }

    /**
     * 
     * Método estático que retorna uma tabela hash, indicando cada valor com um
     * indice.
     * 
     * @Author andre.rsouza
     * 
     */

    static HashMap<Integer, Double> mapaEuclidiano(ArrayList<Double> arrCalculosEuclidianos) {

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

    /**
     * 
     * Método estático que retorna uma tabela hash, indicando cada valor com um
     * indice.
     * 
     * @Author andre.rsouza
     * 
     */

    static void classificadorKNN(int k, ArrayList<Double[]> dataset, Double[] amostra) {

        HashMap<Integer, Double> map = mapaEuclidiano(calcularDatasetPelaAmostra(dataset, amostra));

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

}
