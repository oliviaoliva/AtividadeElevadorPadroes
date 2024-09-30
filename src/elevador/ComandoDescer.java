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
            elevador.mover();  // Move o elevador de acordo com o estado
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
