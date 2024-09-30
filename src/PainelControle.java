import elevador.*;

import java.util.ArrayList;
import java.util.List;

public class PainelControle {
    private List<Comando> filaComandos = new ArrayList<>();

    public void adicionarComando(Comando comando) {
        filaComandos.add(comando);
    }

    public void executarComandos() {
        for (Comando comando : filaComandos) {
            comando.executar();
        }
        filaComandos.clear(); // Limpa os comandos após a execução
    }
}
