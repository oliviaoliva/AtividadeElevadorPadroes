package elevador;

import java.util.List;

public class Parado implements EstadoElevador {

    @Override
    public void mover(Elevador elevador, List<Integer> filaRequisicoes) {
        System.out.println("Elevador parado. Aguardando novas requisições.");
    }
}
