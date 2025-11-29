package com.faculdade.controle;

import com.faculdade.componentes.*;
import com.faculdade.jogador.*;
import com.faculdade.tabuleiro.Tabuleiro;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections; // Import para Collections.unmodifiableList

/**
 *
 * @author vinan
 */
public class JogoNormal extends Jogo {

    @Override
    public void iniciarJogo(int quantidade) {
        // CORRIGIDO: O campo é do tipo List, inicializado como ArrayList (implementação)
        jogadores = new ArrayList<Jogador>();

        String[][] nomesCoresJogadores = {
            {"A", "\u001B[34m"}, {"B", "\u001B[31m"}, {"C", "\u001B[32m"}, 
            {"D", "\u001B[33m"}, {"E", "\u001B[38;5;208m"}, {"F", "\u001B[38;2;153;50;204m"}
        };
        
        baralho = new Baralho();
        
        String tipoPrimeiro = "";
        
        for (int i = 0; i < quantidade; i++) {
            String tipoAleatorio = baralho.sortearTipo();
            if (i == 0) {
                tipoPrimeiro = tipoAleatorio;
            }
            
            if (i == 1) {
                do {
                    tipoAleatorio = baralho.sortearTipo();
                } while (tipoAleatorio.equals(tipoPrimeiro));
            }
            
            String[] dadosJogador = {nomesCoresJogadores[i][0], nomesCoresJogadores[i][1]};

            Jogador jogador = null;
            // CORRIGIDO: Usa as constantes de Jogador.java
            if (tipoAleatorio.equals(Jogador.TIPO_NORMAL)) {
                jogador = new JogadorNormal(i, dadosJogador[1], dadosJogador[0]);
            } else if (tipoAleatorio.equals(Jogador.TIPO_AZARADO)) {
                jogador = new JogadorAzarado(i, dadosJogador[1], dadosJogador[0]);
            } else if(tipoAleatorio.equals(Jogador.TIPO_SORTUDO)) {
                jogador = new JogadorSortudo(i, dadosJogador[1], dadosJogador[0]);
            }

            if (jogador != null) {
                jogadores.add(jogador);
            }
        }
        
        tabuleiro = new Tabuleiro();
        dado = new Dado();
    }

    @Override
    public void terminarJogo() {

    }
    
    @Override
    public Baralho getBaralho() {
        return baralho;
    }
    
    @Override
    public Dado getDado() {
        return dado;
    }

    @Override
    public Tabuleiro getTabuleiro() {
        return tabuleiro;
    }
    
    @Override
    // CORRIGIDO: Retorna List<Jogador> (interface) e usa Collections.unmodifiableList
    public List<Jogador> getJogadores() {
        // Retornar uma lista não modificável é uma boa prática para evitar alterações externas.
        return Collections.unmodifiableList(jogadores);
    }
}