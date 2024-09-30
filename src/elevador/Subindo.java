package elevador;

import java.util.Collections;
import java.util.List;

public class Subindo implements EstadoElevador {

    @Override
    public void mover(Elevador elevador, List<Integer> filaRequisicoes) throws InterruptedException {
        Collections.sort(filaRequisicoes);

        while (!filaRequisicoes.isEmpty() && filaRequisicoes.get(0) > elevador.getAndarAtual()) {
            int proximoAndar = filaRequisicoes.get(0);

            while (elevador.getAndarAtual() < proximoAndar) {
                elevador.subirUmAndar();
                Thread.sleep(3000);

                Collections.sort(filaRequisicoes);
            }

            elevador.chegarAoAndar(filaRequisicoes.remove(0));
            System.out.println("Fila de requisições: " + elevador.getFilaRequisicoes());
        }
    }
}
