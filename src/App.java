import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {

        ArrayList<Personagem> dataset = new ArrayList<>();

        dataset.add(new Personagem(10, 2, TipoPersonagemEnum.GUERREIRO));
        dataset.add(new Personagem(4, 11, TipoPersonagemEnum.MAGO));
        dataset.add(new Personagem(23, 2, TipoPersonagemEnum.GUERREIRO));
        dataset.add(new Personagem(99, 4, TipoPersonagemEnum.GUERREIRO));
        dataset.add(new Personagem(9, 28, TipoPersonagemEnum.MAGO));
        dataset.add(new Personagem(2, 40, TipoPersonagemEnum.MAGO));
        dataset.add(new Personagem(70, 3, TipoPersonagemEnum.GUERREIRO));
        dataset.add(new Personagem(9, 80, TipoPersonagemEnum.MAGO));
        dataset.add(new Personagem(45, 12, TipoPersonagemEnum.GUERREIRO));
        dataset.add(new Personagem(39, 49, TipoPersonagemEnum.MAGO));
        dataset.add(new Personagem(1, 80, TipoPersonagemEnum.MAGO));
        dataset.add(new Personagem(58, 20, TipoPersonagemEnum.GUERREIRO));
        dataset.add(new Personagem(38, 51, TipoPersonagemEnum.MAGO));
        dataset.add(new Personagem(98, 10, TipoPersonagemEnum.GUERREIRO));
        dataset.add(new Personagem(40, 90, TipoPersonagemEnum.MAGO));
        dataset.add(new Personagem(100, 1, TipoPersonagemEnum.GUERREIRO));

        Personagem amostra = new Personagem(23, 34, null);

        amostra = tipoPersonagem(3, dataset, amostra);
        System.out.println("Amostra: " + amostra.getTipoPersonagemEnum());

    }

    static Personagem tipoPersonagem(int k, ArrayList<Personagem> dataset, Personagem amostra) {
        // TRUE - GUERREIRO
        // FALSE - MAGO
        boolean bool = classificadorKNN(k, dataset, amostra);
        if (bool) {
            amostra.tipoPersonagemEnum = TipoPersonagemEnum.GUERREIRO;
            return amostra;
        } else {
            amostra.tipoPersonagemEnum = TipoPersonagemEnum.MAGO;
            return amostra;
        }
    }

    static boolean classificadorKNN(int k, ArrayList<Personagem> dataset, Personagem amostra) {
        double[] amostraPosicao = new double[2];
        amostraPosicao[0] = (double) amostra.forca;
        amostraPosicao[1] = (double) amostra.inteligencia;

        ArrayList<Double> ordenacaoPorDistancia = new ArrayList<>();

        for (int i = 0; i < dataset.size(); i++) {
            double[] treinamentoPosicao = new double[2];
            treinamentoPosicao[0] = (double) dataset.get(i).forca;
            treinamentoPosicao[1] = (double) dataset.get(i).inteligencia;

            System.out.println("Indice -- " + i + " | Valores euclidianos -- "
                    + distanciaEuclidiana(amostraPosicao, treinamentoPosicao));
            ordenacaoPorDistancia.add(distanciaEuclidiana(amostraPosicao, treinamentoPosicao));
        }

        System.out.println("------------------------------");

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
            return true;
        } else {
            return false;
        }
    }

    static double distanciaEuclidiana(double[] p1, double[] p2) {

        BigDecimal bd1 = new BigDecimal(p1[0]);
        BigDecimal bd2 = new BigDecimal(p1[1]);
        BigDecimal bd3 = new BigDecimal(p2[0]);
        BigDecimal bd4 = new BigDecimal(p2[1]);

        BigDecimal potenciaDecimal = new BigDecimal(
                Math.pow(bd1.subtract(bd3).doubleValue(), 2) + Math.pow(bd2.subtract(bd4).doubleValue(), 2));
        return BigDecimal.valueOf(Math.sqrt(potenciaDecimal.doubleValue())).doubleValue();
    }
}
