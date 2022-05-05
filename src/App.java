import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class App {

    /**
     * Created by André da Rocha Souza on 5/5/2022.
     * 
     * andredarochasouza.12345@gmail.com
     */

    public static void main(String[] args) throws Exception {

        ArrayList<Personagem> dataset = new ArrayList<>();

        /**
         * 
         * Dataset especifica os niveis de habilidades que Magos e Guerreiros têm,
         * assim realizando a definição da próxima amostra
         * 
         */

        dataset.add(new Personagem(10, 2, 20, 0, 8, TipoPersonagemEnum.GUERREIRO));
        dataset.add(new Personagem(9, 49, 4, 50, 76, TipoPersonagemEnum.MAGO));
        dataset.add(new Personagem(86, 23, 80, 4, 13, TipoPersonagemEnum.GUERREIRO));
        dataset.add(new Personagem(65, 9, 98, 10, 3, TipoPersonagemEnum.GUERREIRO));
        dataset.add(new Personagem(18, 69, 18, 57, 72, TipoPersonagemEnum.MAGO));
        dataset.add(new Personagem(23, 89, 9, 67, 45, TipoPersonagemEnum.MAGO));
        dataset.add(new Personagem(76, 12, 98, 20, 2, TipoPersonagemEnum.GUERREIRO));
        dataset.add(new Personagem(2, 100, 3, 96, 46, TipoPersonagemEnum.MAGO));

        Personagem amostra = new Personagem(67, 23, 86, 20, 29, null);

        classificadorKNN(3, dataset, amostra);

    }

    static void classificadorKNN(int k, ArrayList<Personagem> dataset, Personagem amostra) {

        double[] amostraPosicao = new double[5];
        amostraPosicao[0] = (double) amostra.forca;
        amostraPosicao[1] = (double) amostra.inteligencia;
        amostraPosicao[2] = (double) amostra.velocidade;
        amostraPosicao[3] = (double) amostra.poder;
        amostraPosicao[4] = (double) amostra.tenacidade;

        ArrayList<Double> ordenacaoPorDistancia = new ArrayList<>();

        for (int i = 0; i < dataset.size(); i++) {
            double[] treinamentoPosicao = new double[5];
            treinamentoPosicao[0] = (double) dataset.get(i).forca;
            treinamentoPosicao[1] = (double) dataset.get(i).inteligencia;
            treinamentoPosicao[2] = (double) dataset.get(i).velocidade;
            treinamentoPosicao[3] = (double) dataset.get(i).poder;
            treinamentoPosicao[4] = (double) dataset.get(i).tenacidade;

            System.out.println("Indice -- " + i + " | Valores euclidianos -- "
                    + distanciaEuclidiana(amostraPosicao, treinamentoPosicao));

            ordenacaoPorDistancia.add(distanciaEuclidiana(amostraPosicao, treinamentoPosicao));
        }

        System.out.println("------------------------------");

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

    static double distanciaEuclidiana(double[] p1, double[] p2) {

        double bd = 0.0;

        for (int i = 0; i < p2.length; i++) {
            bd = Math.pow((p1[i] - p2[i]), 2) + bd;
        }

        return Math.sqrt(bd);
    }
}
