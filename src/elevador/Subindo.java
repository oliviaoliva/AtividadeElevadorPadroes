package elevador;

import java.util.List;

public class Subindo implements EstadoElevador {

    @Override
    public void mover(Elevador elevador, List<Integer> filaRequisicoes) throws InterruptedException {
        if (!filaRequisicoes.isEmpty()) {
            while (!filaRequisicoes.isEmpty() && filaRequisicoes.get(0) > elevador.getAndarAtual()) {
                elevador.subirUmAndar();
                Thread.sleep(3000);
            }
            elevador.chegarAoAndar(filaRequisicoes.remove(0));
        } else {
            System.out.println("Nenhuma requisição pendente.");
        }
    }
}
