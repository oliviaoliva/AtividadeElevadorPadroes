package elevador;

import java.util.ArrayList;
import java.util.List;

public class Elevador {
    private static Elevador instanciaUnica;
    private EstadoElevador estadoAtual;
    private int andarAtual;
    private List<Integer> filaRequisicoes;
    private boolean portaAberta;

    private Elevador(int totalPavimentos) {
        this.andarAtual = 0; // Inicializa
        this.estadoAtual = new Parado();
        this.filaRequisicoes = new ArrayList<>();
        this.portaAberta = true;
    }

    public static Elevador getInstancia(int totalPavimentos) {
        if (instanciaUnica == null) {
            instanciaUnica = new Elevador(totalPavimentos);
        }
        return instanciaUnica;
    }

    public int getAndarAtual() {
        return andarAtual;
    }

    public void mudarEstado(EstadoElevador novoEstado) {
        this.estadoAtual = novoEstado;
        System.out.println("Mudança de estado para: " + (novoEstado instanceof Subindo ? "Subindo" : novoEstado instanceof Descendo ? "Descendo" : "Parado"));
    }

    public void subirUmAndar() {
        andarAtual++;
        System.out.println("Subindo... Andar atual: " + andarAtual);
    }

    public void descerUmAndar() {
        andarAtual--;
        System.out.println("Descendo... Andar atual: " + andarAtual);
    }

    public void chegarAoAndar(int andar) {
        this.andarAtual = andar;
        System.out.println("Elevador chegou ao andar " + andar);
        abrirPorta();
        fecharPorta();
    }

    public void abrirPorta() {
        this.portaAberta = true;
        System.out.println("Porta aberta.");
    }

    public void fecharPorta() {
        this.portaAberta = false;
        System.out.println("Porta fechada.");
    }

    public void mover() throws InterruptedException {
        while (!filaRequisicoes.isEmpty()) {
            estadoAtual.mover(this, filaRequisicoes);

            if (!filaRequisicoes.isEmpty()) {
                int proximoDestino = filaRequisicoes.get(0);
                if (proximoDestino > andarAtual) {
                    mudarEstado(new Subindo());
                } else if (proximoDestino < andarAtual) {
                    mudarEstado(new Descendo());
                }
            }
        }

        mudarEstado(new Parado());
    }

    public List<Integer> getFilaRequisicoes() {
        return filaRequisicoes;
    }

    public void adicionarRequisicao(int andar) {
        if (!filaRequisicoes.contains(andar)) {
            filaRequisicoes.add(andar);
            System.out.println("Andar " + andar + " adicionado à fila.");
        } else {
            System.out.println("Andar " + andar + " já está na fila.");
        }
    }
}
