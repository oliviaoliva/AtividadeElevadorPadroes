package elevador;

public class ComandoSubir implements Comando {
    private Elevador elevador;

    public ComandoSubir(Elevador elevador) {
        this.elevador = elevador;
    }

    @Override
    public void executar() {
        elevador.mudarEstado(new Subindo());
        try {
            elevador.mover();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
