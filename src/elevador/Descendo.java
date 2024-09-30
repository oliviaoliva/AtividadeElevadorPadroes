package elevador;

import java.util.List;

public class Descendo implements EstadoElevador {

    @Override
    public void mover(Elevador elevador, List<Integer> filaRequisicoes) throws InterruptedException {
        if (!filaRequisicoes.isEmpty()) {
            while (!filaRequisicoes.isEmpty() && filaRequisicoes.get(0) < elevador.getAndarAtual()) {
                elevador.descerUmAndar();
                Thread.sleep(3000); // Simula o tempo de movimentação entre andares
            }
            elevador.chegarAoAndar(filaRequisicoes.remove(0)); // Remove o andar atual da fila
        } else {
            System.out.println("Nenhuma requisição pendente.");
        }
    }
}
