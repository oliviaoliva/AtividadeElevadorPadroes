package elevador;

public class ComandoDescer implements Comando {
    private Elevador elevador;

    public ComandoDescer(Elevador elevador) {
        this.elevador = elevador;
    }

    @Override
    public void executar() {
        elevador.mudarEstado(new Descendo());
        try {
            elevador.mover();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
