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
            elevador.mover();  // Move o elevador de acordo com o estado
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
