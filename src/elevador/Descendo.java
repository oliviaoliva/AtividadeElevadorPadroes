package elevador;

import java.util.Collections;
import java.util.List;

public class Descendo implements EstadoElevador {

    @Override
    public void mover(Elevador elevador, List<Integer> filaRequisicoes) throws InterruptedException {
        Collections.sort(filaRequisicoes, Collections.reverseOrder());

        while (!filaRequisicoes.isEmpty()) {
            int proximoAndar = filaRequisicoes.get(0);

            while (elevador.getAndarAtual() > proximoAndar) {
                elevador.descerUmAndar();
                Thread.sleep(3000);

                if (!filaRequisicoes.isEmpty() && filaRequisicoes.get(0) < elevador.getAndarAtual()) {
                    Collections.sort(filaRequisicoes, Collections.reverseOrder());
                    proximoAndar = filaRequisicoes.get(0);
                }
            }

            elevador.chegarAoAndar(filaRequisicoes.remove(0));
        }

        elevador.mudarEstado(new Parado());
    }
}
