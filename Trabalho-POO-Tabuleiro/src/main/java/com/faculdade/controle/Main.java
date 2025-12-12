package com.faculdade.controle;

import com.faculdade.jogador.Jogador;
import java.util.List;
import java.util.Scanner;

public class Main {

    // --- CONSTANTES DE FORMATAÇÃO (CORES ANSI) ---
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_AZUL = "\u001B[34m";
    public static final String ANSI_VERDE = "\u001B[32m";
    public static final String ANSI_VERMELHO = "\u001B[31m";
    public static final String ANSI_AMARELO = "\u001B[33m";
    public static final String ANSI_ROXO = "\u001B[35m";
    public static final String ANSI_CIANO = "\u001B[36m";
    public static final String CLEAR_SCREEN = "\u001B[H\u001B[2J";

    // --- EMOJIS ---
    public static final String EMOJI_DADO = "\uD83C\uDFB2";
    public static final String EMOJI_TROFEU = "\uD83C\uDFC6";
    public static final String EMOJI_ALFINETE = "\uD83D\uDCCD";
    public static final String EMOJI_PASSOS = "\uD83D\uDC62";
    public static final String EMOJI_SETA_DIREITA = "\u27A1";

    // Scanner único para o sistema todo
    public static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // 1. Menu Inicial para escolher o modo de jogo
        Jogo jogo = menu();

        System.out.print(CLEAR_SCREEN);

        // 2. Configuração do Tabuleiro (Tamanho)
        int numCasas = lerInteiro("Quantas casas o tabuleiro terá? (Mínimo recomendado: 15)", 10, 100);
        
        // 3. Configuração dos Jogadores (Quantidade)
        int numJogadores = lerInteiro("Quantos jogadores irão participar? (2 a 6)", 2, 6);

        // 4. CHAMADAS AO FACADE (Delega a complexidade para a classe Jogo)
        // O Jogo agora orquestra a criação do Tabuleiro e dos Jogadores (via Factory)
        jogo.configTabuleiro(numCasas); 
        jogo.config(numJogadores);     
        
        System.out.print(CLEAR_SCREEN);
        System.out.println("=== CONFIGURAÇÃO CONCLUÍDA ===");
        jogo.printTabuleiro(); // Mostra o mapa do tabuleiro criado
        
        // 5. INÍCIO DO JOGO
        // O loop principal agora vive dentro de jogo.start()
        jogo.start();

        // 6. RELATÓRIO FINAL
        reporteFinal(jogo.getVencedor(), jogo.getJogadores());
    }

    /**
     * Menu Principal para seleção do tipo de Jogo.
     */
    public static Jogo menu() {
        System.out.println("+" + "-".repeat(18) + "+");
        System.out.println("|  SEJA BEM VINDO  |");
        System.out.println("+" + "-".repeat(18) + "+");
        System.out.println("|    1 - Jogar     |");
        System.out.println("|    2 - Debug     |");
        System.out.println("+" + "-".repeat(18) + "+");

        while (true) {
            int opcao = lerInteiro("Qual opção você deseja?", 1, 2);
            if (opcao == 1) return new JogoNormal();
            if (opcao == 2) return new JogoDebug();
        }
    }

    /**
     * Exibe o placar e estatísticas ao final da partida.
     */
    public static void reporteFinal(Jogador vencedor, List<Jogador> jogadores) {
        if (vencedor != null) {
            System.out.println("\n" + "=".repeat(30));
            System.out.printf("%s %s %s VENCEU O JOGO! %s%n", EMOJI_TROFEU, vencedor.getNome(), EMOJI_TROFEU, ANSI_RESET);
            System.out.println("=".repeat(30));
            
            for (Jogador j : jogadores) {
                System.out.printf("Relatório do Jogador %s%s%s:%n", j.getCor(), j.getNome(), ANSI_RESET);
                System.out.printf("  - %s Jogadas: %d%n", EMOJI_PASSOS, j.getJogadas());
                System.out.printf("  - %s Posição Final: %d%n", EMOJI_ALFINETE, j.getPosicao());
                System.out.printf("  - Moedas acumuladas: %d%n", j.getMoedas());
                System.out.println("-".repeat(20));
            }
        }
    }

    /**
     * Método auxiliar para ler inteiros com validação, evitando erros de buffer.
     */
    private static int lerInteiro(String mensagem, int min, int max) {
        while (true) {
            try {
                System.out.println(mensagem);
                int valor = Integer.parseInt(scanner.nextLine());
                if (valor >= min && valor <= max) return valor;
                System.out.printf("Por favor, digite um valor entre %d e %d.%n", min, max);
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Digite um número inteiro.");
            }
        }
    }
}