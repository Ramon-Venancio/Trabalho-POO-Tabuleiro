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
            System.out.println("Quantas pessoas vão jogar? (O minimo é 2 jogadores)");
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
            System.out.println(jogador);
        }
        System.out.println(jogo.getTabuleiro());
        System.out.println(jogo.getBaralho());
        System.out.println(jogo.getDado());
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
