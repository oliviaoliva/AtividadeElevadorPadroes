package elevador;

import java.util.Collections;
import java.util.List;

public class Descendo implements EstadoElevador {

    @Override
    public void mover(Elevador elevador, List<Integer> filaRequisicoes) throws InterruptedException {
        // Ordena a fila em ordem decrescente para atender os andares durante a descida
        Collections.sort(filaRequisicoes, Collections.reverseOrder());

        // Continua descendo enquanto houver andares menores que o atual na fila
        while (!filaRequisicoes.isEmpty()) {
            int proximoAndar = filaRequisicoes.get(0); // Pega o próximo destino na fila

            // Se o próximo andar for menor que o andar atual, desce
            while (elevador.getAndarAtual() > proximoAndar) {
                elevador.descerUmAndar();
                Thread.sleep(3000); // Simula o tempo de movimentação entre andares

                // Verifica se uma nova requisição foi feita para um andar mais baixo durante a descida
                if (!filaRequisicoes.isEmpty() && filaRequisicoes.get(0) < elevador.getAndarAtual()) {
                    Collections.sort(filaRequisicoes, Collections.reverseOrder()); // Reordena a fila dinamicamente
                    proximoAndar = filaRequisicoes.get(0); // Atualiza o próximo destino
                }
            }

            // Chega ao andar e remove a requisição da fila
            elevador.chegarAoAndar(filaRequisicoes.remove(0)); // Remove o próximo destino atendido
        }

        // Se a fila estiver vazia, muda o estado do elevador para parado
        elevador.mudarEstado(new Parado());
    }
}
