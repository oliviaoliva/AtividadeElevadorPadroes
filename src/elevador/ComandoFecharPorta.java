package elevador;

public class ComandoFecharPorta implements Comando {
    private Elevador elevador;

    public ComandoFecharPorta(Elevador elevador) {
        this.elevador = elevador;
    }

    @Override
    public void executar() {
        elevador.fecharPorta();
    }
}
