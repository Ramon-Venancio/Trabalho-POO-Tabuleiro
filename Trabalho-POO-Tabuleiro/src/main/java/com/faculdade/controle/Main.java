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
    
    public static void main(String[] args) {
        Jogo jogo = menu();
        while(true) {
            System.out.println("Quantas pessoas vão jogar? (No minimo 2 jogadores e no máximo 6)");
            int quantidadeJogadores = Integer.parseInt(scanner.next());
            if (quantidadeJogadores >= 2 && quantidadeJogadores < 7) {
                jogo.iniciarJogo(quantidadeJogadores);
                break;
            } else {
                System.out.println("Quantidade de jogadores incorreto");
            }
        }
        
        ArrayList<Jogador> jogadores = jogo.getJogadores();
        for (Jogador jogador : jogadores) {
            if (jogador instanceof JogadorNormal) {
                String corJogadorAtual = jogador.getCor();
                String nomeJogadorAtual = jogador.getNome();
                System.out.println(corJogadorAtual + nomeJogadorAtual + ANSI_RESET + ": " + "Normal");
            } else if (jogador instanceof JogadorAzarado) {
                String corJogadorAtual = jogador.getCor();
                String nomeJogadorAtual = jogador.getNome();
                System.out.println(corJogadorAtual + nomeJogadorAtual + ANSI_RESET + ": " + "Azarado");
            } else if (jogador instanceof JogadorSortudo) {
                String corJogadorAtual = jogador.getCor();
                String nomeJogadorAtual = jogador.getNome();
                System.out.println(corJogadorAtual + nomeJogadorAtual + ANSI_RESET + ": " + "Sortudo");
            }
        }
        int countRodadas = 1;
        while(true) {
            System.out.println("Rodada " + countRodadas);
            for (Jogador jogador : jogadores) {
                String corJogadorAtual = jogador.getCor();
                String nomeJogadorAtual = jogador.getNome();
                System.out.println("Vez do jogador " + corJogadorAtual + nomeJogadorAtual + ANSI_RESET + "de jogar");
                System.err.println("Role seus dados\n(Pressione enter para rolar)");
                String rodar = scanner.next();
                jogo.executarTurno();
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
        
        
        while(true) {
            System.out.println("Qual opção voçê quer?\n");
            int opcao = Integer.parseInt(scanner.next());
            
            if (opcao == 1) {
                Jogo jogo = new JogoNormal();
                return jogo;
            } else if (opcao == 2) {
                Jogo jogo =  new JogoDebug();
                return jogo;
            } else {
                System.out.println("Valor invalido! Digite novamente.\n");
            }
        }
    }
}
