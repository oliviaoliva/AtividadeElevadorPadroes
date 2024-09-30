import elevador.*;

public class MainElevador {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Simulação Elevador\n");

        // Inicializa o elevador com 10 andares
        Elevador elevador = Elevador.getInstancia(10); // Singleton
        PainelControle painel = new PainelControle();  // Painel de controle que gerencia os comandos

        // Mostrar que o elevador é um Singleton (mesma instância)
        System.out.println("Instanciando o elevador novamente para demonstrar o Singleton:");
        Elevador outroElevador = Elevador.getInstancia(10);
        if (elevador == outroElevador) {
            System.out.println("Confirmado: O elevador é um Singleton, mesma instância usada.\n");
        }

        // Adicionando requisições de andar
        System.out.println("Usuário dentro do elevador solicita o andar 5.");
        elevador.adicionarRequisicao(5);  // Adiciona requisição ao andar 5
        System.out.println("Fila de requisições: " + elevador.getFilaRequisicoes()); // Exibe a fila


        System.out.println("Usuário dentro do elevador solicita o andar 9.");
        elevador.adicionarRequisicao(9);  // Adiciona requisição ao andar 9
        System.out.println("Fila de requisições: " + elevador.getFilaRequisicoes()); // Exibe a fila


        System.out.println("Usuário no andar 2 solicita o elevador para subir.");
        elevador.adicionarRequisicao(2);  // Adiciona requisição ao andar 2
        System.out.println("Fila de requisições: " + elevador.getFilaRequisicoes()); // Exibe a fila


        // Adicionando comandos ao painel de controle
        System.out.println("\nAdicionando comandos ao painel de controle:\n");
        painel.adicionarComando(new ComandoSubir(elevador));   // Comando para iniciar a subida
        painel.adicionarComando(new ComandoFecharPorta(elevador)); // Comando para fechar porta após o movimento
        painel.adicionarComando(new ComandoAbrirPorta(elevador));  // Comando para abrir porta ao chegar

        // Executar comandos
        System.out.println("\n O elevador começará a se mover\n");
        painel.executarComandos();  // Executa os comandos

        // Demonstrar que o elevador muda de estado dinamicamente
        System.out.println("\nAdicionando nova requisição enquanto o elevador está em movimento:");
        elevador.adicionarRequisicao(10);  // Adiciona nova requisição durante a movimentação
        System.out.println("Fila de requisições: " + elevador.getFilaRequisicoes()); // Exibe a fila
        elevador.adicionarRequisicao(5);   // Adiciona nova requisição durante a movimentação
        System.out.println("Fila de requisições: " + elevador.getFilaRequisicoes()); // Exibe a fila

        // Adiciona comandos adicionais ao painel e os executa novamente
        painel.adicionarComando(new ComandoSubir(elevador));  // Subir até o próximo andar
        painel.executarComandos();  // Executa os novos comandos

        System.out.println("\nFim da Simulação");
    }
}
