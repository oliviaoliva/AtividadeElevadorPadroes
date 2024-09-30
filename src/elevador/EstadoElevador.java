package elevador;

import java.util.List;

public interface EstadoElevador {
    void mover(Elevador elevador, List<Integer> filaRequisicoes) throws InterruptedException;
}
