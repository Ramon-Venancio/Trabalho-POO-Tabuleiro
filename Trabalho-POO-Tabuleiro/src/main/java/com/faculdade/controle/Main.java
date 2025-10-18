/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.faculdade.controle;

import com.faculdade.jogador.*;
import java.util.*;

/**
 *
 * @author vinan
 */
public class Main {

    public static Scanner scanner = new Scanner(System.in);
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String EMOJI_DADO = "\uD83C\uDFB2";
    public static final String EMOJI_PESSOA_ANDANDO = "\uD83D\uDEB6";
    public static final String EMOJI_PASSOS = "\uD83D\uDC62";
    public static final String EMOJI_SETA_DIREITA = "\u27A1";
    public static final String EMOJI_TREVO = "\uD83C\uDF40";
    public static final String EMOJI_ROSTO_CONGELADO = "\uD83E\uDD76";
    public static final String EMOJI_BRILHO = "\u2728";
    public static final String EMOJI_SETA_RETORNO = "\u21A9\uFE0F";
    public static final String EMOJI_TROFEU = "\uD83C\uDFC6";
    public static final String EMOJI_SETAS_HORARIAS = "\uD83D\uDD04";
    public static final String EMOJI_BANDEIRA = "\uD83C\uDFC1";
    public static final String EMOJI_ALFINETE = "\uD83D\uDCCD";
    public static final String CLEAR_SCREEN = "\u001B[H\u001B[2J";

    public static void main(String[] args) {
        Jogo jogo = menu();
        while (true) {
            System.out.println("Quantas pessoas vão jogar? (No minimo 2 jogadores e no máximo 6)");
            int quantidadeJogadores = scanner.nextInt();
            scanner.nextLine();

            if (quantidadeJogadores >= 2 && quantidadeJogadores < 7) {
                jogo.iniciarJogo(quantidadeJogadores);
                break;
            } else {
                System.out.println("Quantidade de jogadores incorreto");
            }
        }

        ArrayList<Jogador> jogadores = jogo.getJogadores();
        System.out.println("Como se chama cada jogagor?");

        for (Jogador jogador : jogadores) {
            System.out.printf("Qual é o nome do %s%d° jogador%s? (Se deixar vazio o nome padrão '%s' será usado)%n", jogador.getCor(), jogador.getIdJogador() + 1, ANSI_RESET, jogador.getNome());
            String nome = scanner.nextLine().trim();

            if (!nome.isEmpty()) {
                jogador.setNome(nome);
            }
        }

        System.out.println("Jogadores");
        for (Jogador jogador : jogadores) {
            int numeroJogador = jogador.getIdJogador();
            String corJogadorAtual = jogador.getCor();
            String nomeJogadorAtual = jogador.getNome();
            String mostrarJogador = String.format("%d° jogador: %s%s%s", numeroJogador + 1, corJogadorAtual, nomeJogadorAtual, ANSI_RESET);
            System.out.println(mostrarJogador);
        }

        System.out.println("(Pressione enter para começar o jogo)");
        scanner.nextLine();
        System.out.print(CLEAR_SCREEN);

        int countRodadas = 1;
        boolean continuar = true;
        Jogador vencedor = null;
        
        if (jogo instanceof JogoDebug) {
            System.out.println("\nModo Debug\n(Você pode escolher a posição do jogador em vez de rolar dados)\n");
        }
        
        while (continuar) {
            System.out.println("\nRodada " + countRodadas);
            for (Jogador jogador : jogadores) {
                String corJogadorAtual = jogador.getCor();
                String nomeJogadorAtual = jogador.getNome();
                System.out.println("\nVez do jogador " + corJogadorAtual + nomeJogadorAtual + ANSI_RESET + " de jogar");
                
                if (jogo instanceof JogoNormal) {
                    System.out.println("Role seus dados " + EMOJI_DADO + EMOJI_DADO + "\n(Pressione enter para rolar)");
                    scanner.nextLine();
                }

                if (jogador.getPerdeRodada()) {
                    if (jogo instanceof JogoNormal) {
                        int[] dados = jogador.jogarDados();
                        int passos = dados[0] + dados[1];
                        System.out.printf("%s Dado1: %d %n%s Dado2: %d %n%s Passos: %d", EMOJI_DADO, dados[0], EMOJI_DADO, dados[1], EMOJI_PESSOA_ANDANDO, passos);
                        jogo.getTabuleiro().moverJogador(jogador, passos, jogadores, jogo);
                    } else {
                        System.out.println("Digite uma posição para o jogador ir");
                        int posicao =  scanner.nextInt();
                        scanner.nextLine();
                        jogo.getTabuleiro().moverJogador(jogador, posicao, jogadores, jogo);
                    }
                } else {
                    System.out.println("Jogador " + jogador.getCor() + jogador.getNome() + ANSI_RESET + " não pode jogar nessa rodada.");
                    jogador.setPerdeRodada(true);
                }
                
                if ((jogador.getPosicao()+1) >= 40) {
                    System.out.printf("%nJogador %s%s%s Venceu %s", jogador.getCor(), jogador.getNome(), ANSI_RESET, EMOJI_BANDEIRA);
                    continuar = false;
                    vencedor = jogador;
                    break;
                }
                
                System.out.println("\n\nPosição de cada jogador");
                for (Jogador jogadorMostrado : jogadores) {
                    System.out.printf("%s%s%s: %d%n", jogadorMostrado.getCor(), jogadorMostrado.getNome(), ANSI_RESET, jogadorMostrado.getPosicao()+1);
                }
            }
            
            if (continuar) {
                System.out.println("\n" + countRodadas + "° Rodado finaliza!\n(Pressione enter para ir para proxima)");
                scanner.nextLine();
                System.out.print(CLEAR_SCREEN);
                countRodadas++;
            }
        }
        System.out.println("\n\nPlacar");
        if (vencedor.getPosicao() >= 40) {
            vencedor.setPosicao(39);
        }
        System.out.printf("Jogador Vencedor %s%s%s %s: %s Passos %d %s Posição: %d%n", vencedor.getCor(), vencedor.getNome(), ANSI_RESET, EMOJI_TROFEU, EMOJI_PASSOS,vencedor.getJogadas(), EMOJI_ALFINETE, vencedor.getPosicao()+1);
        for (Jogador jogador : jogadores) {
            if (!(jogador.equals(vencedor))) {
                System.out.printf("Jogador %s%s%s: %s Passos %d %s Posição %d", jogador.getCor(), jogador.getNome(), ANSI_RESET, EMOJI_PASSOS, jogador.getJogadas(), EMOJI_ALFINETE, jogador.getPosicao()+1);
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