package com.faculdade.controle;

import com.faculdade.jogador.Jogador;
import com.faculdade.tabuleiro.Casa;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JogoNormal extends Jogo {
    
    private static final Logger logger = Logger.getLogger(JogoNormal.class.getName());

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
                // Se o jogador perdeu a rodada (regra da Prisão/Perde a Vez)
                String tipoJogador = jogadorAtual
                        .getClass()
                        .getSimpleName()
                        .replace("Jogador", "");

                System.out.printf(
                    "\n%sÉ a vez de %s%s%s (Tipo: %s)! Pressione ENTER para jogar os dados.%s",
                    Main.EMOJI_DADO,
                    jogadorAtual.getCor(),
                    jogadorAtual.getNome(),
                    Main.ANSI_RESET,
                    tipoJogador,
                    Main.EMOJI_DADO
                );
                jogadorAtual.setPerdeRodada(false); // Libera o jogador para a próxima
                indiceJogadorAtual = (indiceJogadorAtual + 1) % numJogadores;
                continue; 
            }
            
            printTabuleiro();
            
            System.out.printf("\n%sÉ a vez de %s%s%s! Pressione ENTER para jogar os dados.%s", 
                              Main.EMOJI_DADO, jogadorAtual.getCor(), jogadorAtual.getNome(), Main.ANSI_RESET, Main.EMOJI_DADO);
            Main.scanner.nextLine();
            
            int[] dados = jogadorAtual.jogarDados();
            int soma = dados[0] + dados[1];
            
            System.out.printf("Resultado dos dados: %d e %d (Soma: %d).%n", dados[0], dados[1], soma);
            
            tabuleiro.moverJogador(jogadorAtual, soma, jogadores, this);
            
            // Verifica a condição de jogar novamente (dados iguais)
            boolean jogaDeNovo = (dados[0] == dados[1]);
            
            if (vencedor != null) {
                break;
            }
            
            // Se não jogar novamente, passa a vez
            if (!jogaDeNovo) {
                indiceJogadorAtual = (indiceJogadorAtual + 1) % numJogadores;
            } else {
                System.out.printf("\n%s%s%s tirou dados iguais e joga novamente!%n", 
                                  jogadorAtual.getCor(), jogadorAtual.getNome(), Main.ANSI_RESET);
            }
        }
        
        terminarJogo(); // Chama o método de finalização se necessário
    }
}