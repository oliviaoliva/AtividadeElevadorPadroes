package elevador;

import java.util.Collections;
import java.util.List;

public class Subindo implements EstadoElevador {

    @Override
    public void mover(Elevador elevador, List<Integer> filaRequisicoes) throws InterruptedException {
        // Ordena a fila para garantir que os andares intermediários sejam atendidos na ordem correta
        Collections.sort(filaRequisicoes);

        // Continua subindo enquanto houver andares maiores que o atual na fila
        while (!filaRequisicoes.isEmpty() && filaRequisicoes.get(0) > elevador.getAndarAtual()) {
            int proximoAndar = filaRequisicoes.get(0); // Pega o próximo destino na fila

            // Se o próximo andar for maior que o andar atual, sobe
            while (elevador.getAndarAtual() < proximoAndar) {
                elevador.subirUmAndar();
                Thread.sleep(3000); // Simula o tempo de movimentação entre andares

                // Verifica se há uma nova requisição intermediária enquanto sobe
                Collections.sort(filaRequisicoes); // Reordena a fila dinamicamente
            }

            // Chega ao andar e remove a requisição da fila
            elevador.chegarAoAndar(filaRequisicoes.remove(0)); // Remove o próximo destino atendido
        }
    }
}
