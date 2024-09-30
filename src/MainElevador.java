import elevador.*;

public class MainElevador {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Simulação Elevador\n");

        Elevador elevador = Elevador.getInstancia(10);
        PainelControle painel = new PainelControle();

        System.out.println("Instanciando o elevador novamente para demonstrar o Singleton:");
        Elevador outroElevador = Elevador.getInstancia(10);
        if (elevador == outroElevador) {
            System.out.println("Confirmado: O elevador é um Singleton, mesma instância usada.\n");
        }

        System.out.println("Usuário dentro do elevador solicita o andar 10.");
        elevador.adicionarRequisicao(10);
        System.out.println("Fila de requisições: " + elevador.getFilaRequisicoes());

        System.out.println("Usuário no andar 3 solicita o elevador para subir.");
        elevador.adicionarRequisicao(3);
        System.out.println("Fila de requisições: " + elevador.getFilaRequisicoes());

        Thread requisicaoSubidaIntermediaria = new Thread(() -> {
            try {
                Thread.sleep(3000);
                System.out.println("\nNovo usuário no andar 5 solicita o elevador.");
                elevador.adicionarRequisicao(5);
                System.out.println("Fila de requisições: " + elevador.getFilaRequisicoes());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        requisicaoSubidaIntermediaria.start();

        System.out.println("\nAdicionando comandos ao painel de controle:\n");
        painel.adicionarComando(new ComandoSubir(elevador));
        painel.adicionarComando(new ComandoFecharPorta(elevador));
        painel.adicionarComando(new ComandoAbrirPorta(elevador));


        System.out.println("\nO elevador começará a se mover (subindo)\n");
        painel.executarComandos();


        System.out.println("\nUsuário dentro do elevador solicita o andar 0.");
        elevador.adicionarRequisicao(0);
        System.out.println("Fila de requisições: " + elevador.getFilaRequisicoes());

        Thread requisicaoDescidaIntermediaria = new Thread(() -> {
            try {
                Thread.sleep(1000);
                System.out.println("\nNovo usuário no andar 3 solicita o elevador.");
                elevador.adicionarRequisicao(3);
                System.out.println("Fila de requisições: " + elevador.getFilaRequisicoes());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        requisicaoDescidaIntermediaria.start();

        painel.adicionarComando(new ComandoDescer(elevador));
        painel.adicionarComando(new ComandoFecharPorta(elevador));
        painel.adicionarComando(new ComandoAbrirPorta(elevador));

        System.out.println("\nO elevador começará a se mover (descendo)\n");
        painel.executarComandos();

        System.out.println("\nFim da Simulação");
    }
}
