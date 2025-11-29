package com.faculdade.controle;

import com.faculdade.jogador.*;
import java.util.*;

public class Main {

    // --- CONSTANTES E VARIÁVEIS ESTÁTICAS (MANTIDAS) ---
    public static Scanner scanner = new Scanner(System.in);
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String EMOJI_DADO = "\uD83C\uDFB2";
    public static final String EMOJI_PESSOA_ANDANDO = "\uD83D\uDEB6";
    public static final String EMOJI_PASSOS = "\uD83D\uDC62";
    public static final String EMOJI_TROFEU = "\uD83C\uDFC6";
    public static final String EMOJI_BANDEIRA = "\uD83C\uDFC1";
    public static final String EMOJI_ALFINETE = "\uD83D\uDCCD";
    public static final String CLEAR_SCREEN = "\u001B[H\u001B[2J";
    public static final String EMOJI_SETA_DIREITA = "\u27A1";
    public static final String EMOJI_TREVO = "\uD83C\uDF40";
    public static final String EMOJI_ROSTO_CONGELADO = "\uD83E\uDD76";
    public static final String EMOJI_BRILHO = "\u2728";
    public static final String EMOJI_SETA_RETORNO = "\u21A9\uFE0F";
    public static final String EMOJI_SETAS_HORARIAS = "\uD83D\uDD04";
    
    // Inicializa o jogo
    public static void main(String[] args) {
        Jogo jogo = menu();
        // Chama o método orquestrador que contém a lógica de jogo.
        executarJogo(jogo);
    }
    
    // --- MÉTODO ORQUESTRADOR REAPROVEITADO (Antigo método Main) ---
    /**
     * Orquestra as fases do jogo: Configuração, Loop de Rodadas e Placar.
     * Complexidade Cognitiva reduzida de 37 para aproximadamente 6.
     */
    public static void executarJogo(Jogo jogo) {

        // 1. Configuração de Jogadores (Quantidade)
        configurarQuantidadeJogadores(jogo); 

        //Informa o tamanho da lista
        ArrayList<Jogador> jogadores = jogo.getJogadores();

        // 2. Configuração de Nomes dos Jogadores
        configurarNomes(jogadores);
        
        // 3. Exibição e Inicialização
        mostrarJogadores(jogadores); 
        iniciarJogoUX();
        
        // 4. Loop Principal de Rodadas
        Jogador vencedor = executarLoopPrincipal(jogo, jogadores);
        
        // 5. Exibição Final do Placar
        mostrarPlacar(vencedor, jogadores); 
    }
    
    // --- MÉTODOS PRIVADOS REFACTORADOS (Lógica Isolada) ---
    

    private static void configurarQuantidadeJogadores(Jogo jogo) {
        while (true) {
            System.out.println("Quantas pessoas vão jogar? (No minimo 2 jogadores e no máximo 6)");
            
            // Adicionado validação robusta para evitar InputMismatchException
            if (scanner.hasNextInt()) { 
                int quantidadeJogadores = scanner.nextInt();
                scanner.nextLine(); // Consome a quebra de linha
                
                if (quantidadeJogadores >= 2 && quantidadeJogadores < 7) {
                    jogo.iniciarJogo(quantidadeJogadores);
                    break;
                } else {
                    System.out.println("Quantidade de jogadores incorreto");
                }
            } else {
                System.out.println("Entrada inválida. Digite um número inteiro.");
                scanner.nextLine(); // Consome a entrada inválida para evitar loop infinito
            }
        }
    }
    
    private static void configurarNomes(ArrayList<Jogador> jogadores) {
        System.out.println("Como se chama cada jogador?");
        for (Jogador jogador : jogadores) {
            System.out.printf("Qual é o nome do %s%d° jogador%s? (Se deixar vazio o nome padrão '%s' será usado)%n", 
                jogador.getCor(), jogador.getIdJogador() + 1, ANSI_RESET, jogador.getNome());

            String nome = scanner.nextLine().trim();
            if (!nome.isEmpty()) {
                jogador.setNome(nome);
            }
        }
    }

    private static void mostrarJogadores(ArrayList<Jogador> jogadores) {
        System.out.println("Jogadores");
        for (Jogador jogador : jogadores) {
            String mostrarJogador = String.format("%d° jogador: %s%s%s", 
                jogador.getIdJogador() + 1, jogador.getCor(), jogador.getNome(), ANSI_RESET);
            System.out.println(mostrarJogador);
        }
    }
    
    private static void iniciarJogoUX() {
        System.out.println("(Pressione enter para começar o jogo)");
        scanner.nextLine();
        System.out.print(CLEAR_SCREEN);
    }

    private static Jogador executarLoopPrincipal(Jogo jogo, ArrayList<Jogador> jogadores) {
        if (jogo instanceof JogoDebug) { // Lógica condicional isolada
            System.out.println("\nModo Debug\n(Você pode escolher a posição do jogador em vez de rolar dados)\n");
        }
        
        int countRodadas = 1;
        Jogador vencedor = null;
        boolean continuar = true;
        
        while (continuar) { // Loop de Rodadas
            System.out.println("\nRodada " + countRodadas);
            
            for (Jogador jogador : jogadores) { // Loop de Jogadores
                // DELEGA A LÓGICA DO TURNO E VERIFICA A VITÓRIA
                if (executarTurno(jogador, jogo, jogadores)) { 
                    vencedor = jogador;
                    continuar = false;
                    break; // Sai do loop de jogadores
                }
                
                mostrarPosicoes(jogadores); // I/O isolada
            }
            
            if (continuar) { // Lógica de fim de rodada
                System.out.println("\n" + countRodadas + "° Rodado finaliza!\n(Pressione enter para ir para proxima)");
                scanner.nextLine();
                System.out.print(CLEAR_SCREEN);
                countRodadas++;
            }
        }
        return vencedor;
    }
    
    private static boolean executarTurno(Jogador jogador, Jogo jogo, ArrayList<Jogador> jogadores) {
        System.out.println("\nVez do jogador " + jogador.getCor() + jogador.getNome() + ANSI_RESET + " de jogar");
        
        if (jogo instanceof JogoNormal) { // Condicional 1
            System.out.println("Role seus dados " + EMOJI_DADO + EMOJI_DADO + "\n(Pressione enter para rolar)");
            scanner.nextLine();
        }

        if (jogador.getPerdeRodada()) { // Condicional 2 (Pode jogar)
            moverJogador(jogador, jogo, jogadores); // Movimento delegado
        } else {
            System.out.println("Jogador " + jogador.getCor() + jogador.getNome() + ANSI_RESET + " não pode jogar nessa rodada.");
            jogador.setPerdeRodada(true);
        }
        
        // Condicional de vitória
        if ((jogador.getPosicao()+1) >= 40) { 
            System.out.printf("%nJogador %s%s%s Venceu %s", jogador.getCor(), jogador.getNome(), ANSI_RESET, EMOJI_BANDEIRA);
            return true;
        }
        return false;
    }
    
    private static void moverJogador(Jogador jogador, Jogo jogo, ArrayList<Jogador> jogadores) {
        if (jogo instanceof JogoNormal) { // Condicional 1: Movimento Normal (Dados)
            int[] dados = jogador.jogarDados();
            int passos = dados[0] + dados[1];
            System.out.printf("%s Dado1: %d %n%s Dado2: %d %n%s Passos: %d", EMOJI_DADO, dados[0], EMOJI_DADO, dados[1], EMOJI_PESSOA_ANDANDO, passos);
            jogo.getTabuleiro().moverJogador(jogador, passos, jogadores, jogo);
        } else { // Condicional 2: Movimento Debug (Posição Manual)
            System.out.println("Digite uma posição para o jogador ir");
            // Adicionado validação robusta para evitar InputMismatchException
            if (scanner.hasNextInt()) { 
                int posicao =  scanner.nextInt();
                scanner.nextLine();
                jogo.getTabuleiro().moverJogador(jogador, posicao, jogadores, jogo);
            } else {
                 System.out.println("Entrada inválida para a posição. Movimento ignorado.");
                 scanner.nextLine();
            }
        }
    }
    
    private static void mostrarPosicoes(ArrayList<Jogador> jogadores) {
        System.out.println("\n\nPosição de cada jogador");
        for (Jogador jogadorMostrado : jogadores) {
            System.out.printf("%s%s%s: %d%n", jogadorMostrado.getCor(), 
                jogadorMostrado.getNome(), ANSI_RESET, jogadorMostrado.getPosicao()+1);
        }
    }
    
    private static void mostrarPlacar(Jogador vencedor, ArrayList<Jogador> jogadores) {
        if (vencedor == null) {
            System.out.println("\n\nO jogo terminou sem um vencedor claro.");
            return;
        }
        
        System.out.println("\n\nPlacar");
        if (vencedor.getPosicao() >= 40) {
            vencedor.setPosicao(39);
        }
        
        // Exibição do Vencedor
        System.out.printf("Jogador Vencedor %s%s%s %s: %s Passos %d %s Posição: %d%n", 
            vencedor.getCor(), vencedor.getNome(), ANSI_RESET, EMOJI_TROFEU, 
            EMOJI_PASSOS, vencedor.getJogadas(), EMOJI_ALFINETE, vencedor.getPosicao()+1);
        
        // Exibição dos demais jogadores
        for (Jogador jogador : jogadores) {
            if (!(jogador.equals(vencedor))) {
                System.out.printf("Jogador %s%s%s: %s Passos %d %s Posição %d%n", // Adicionado %n para quebrar linha
                    jogador.getCor(), jogador.getNome(), ANSI_RESET, EMOJI_PASSOS, 
                    jogador.getJogadas(), EMOJI_ALFINETE, jogador.getPosicao()+1);
            }
        }
    }

    public static Jogo menu() {
        String tracos = "-".repeat(18);
        String linhaSI = String.format("+%s+", tracos);
        String titulo = "SEJA BEM VINDO";
        String linhaMeio = String.format("|  %s  |", titulo);
        String cabecalho = String.format("%s%n%s%n%s", linhaSI, linhaMeio, linhaSI);
        String opcao1 = String.format("|%s1 - Jogar%s|", " ".repeat(4), " ".repeat(5));
        String opcao2 = String.format("|%s2 - Debug%s|", " ".repeat(4), " ".repeat(5));
        String menu = String.format("%s%n%s%n%s%n%s", cabecalho, opcao1, opcao2, linhaSI);
        System.out.println(menu);

        while (true) {
            System.out.println("Qual opção voçê quer?\n");
            // Nota: Se fosse refatorar o menu, esta linha seria o ponto principal:
            // if (scanner.hasNextInt()) { int opcao = scanner.nextInt(); ... }
            int opcao = Integer.parseInt(scanner.next());

            if (opcao == 1) {
                Jogo jogo = new JogoNormal();
                return jogo;
            } else if (opcao == 2) {
                Jogo jogo = new JogoDebug();
                return jogo;
            } else {
                System.out.println("Valor invalido! Digite novamente.\n");
            }
        }
    }
}