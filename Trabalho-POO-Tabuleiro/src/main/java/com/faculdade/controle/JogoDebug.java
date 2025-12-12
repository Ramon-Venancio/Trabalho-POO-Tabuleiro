package com.faculdade.controle;

import com.faculdade.jogador.Jogador;
import com.faculdade.tabuleiro.Casa;
import java.util.List;

public class JogoDebug extends Jogo {

    @Override
    public void terminarJogo() {
        // Lógica de finalização, se houver.
    }

    @Override
    public void printTabuleiro() {
        System.out.println("\n----------------------------------------------");
        System.out.println("ESTADO ATUAL DO JOGO");
        System.out.println("----------------------------------------------");

        for (Jogador jogador : jogadores) {
            System.out.printf(
                "%s%s%s | Tipo: %s | Posição: %d | Moedas: %d%n",
                jogador.getCor(),
                jogador.getNome(),
                Main.ANSI_RESET,
                jogador.getClass().getSimpleName().replace("Jogador", ""),
                jogador.getPosicao(),
                jogador.getMoedas()
            );
        }

        System.out.println("----------------------------------------------\n");
    }

    public void start() {
        // Loop principal do jogo:
        
        int indiceJogadorAtual = 0;
        int numJogadores = jogadores.size();
        
        while (vencedor == null) {
            
            Jogador jogadorAtual = jogadores.get(indiceJogadorAtual);
            
            if (jogadorAtual.getPerdeRodada()) {
                System.out.printf("\n%s%s%s perdeu a rodada e está na Casa Prisão. Será liberado agora.%n", 
                                  jogadorAtual.getCor(), jogadorAtual.getNome(), Main.ANSI_RESET);
                jogadorAtual.setPerdeRodada(false);
                indiceJogadorAtual = (indiceJogadorAtual + 1) % numJogadores;
                continue; 
            }
            
            printTabuleiro();
            
            // Lógica do MODO DEBUG: Insere a posição diretamente
            int novaPosicao;
            while (true) {
                System.out.printf("\n%sMODO DEBUG: Qual a NOVA POSIÇÃO de %s%s%s? ", 
                                  Main.EMOJI_DADO, jogadorAtual.getCor(), jogadorAtual.getNome(), Main.ANSI_RESET);
                try {
                    novaPosicao = Integer.parseInt(Main.scanner.nextLine());
                    if (novaPosicao >= 1 && novaPosicao <= tabuleiro.getNumTotalCasas()) break;
                    System.out.printf("Posição inválida. Digite um número entre 1 e %d.%n", tabuleiro.getNumTotalCasas());
                } catch (NumberFormatException e) {
                    System.out.println("Entrada inválida. Digite um número.");
                }
            }
            
            // No modo Debug, o 'passos' no moverJogador é tratado como a Posição Alvo
            tabuleiro.moverJogador(jogadorAtual, novaPosicao, jogadores, this);
            
            if (vencedor != null) {
                break;
            }
            
            // Passa a vez
            indiceJogadorAtual = (indiceJogadorAtual + 1) % numJogadores;
        }
        
        terminarJogo();
    }
}