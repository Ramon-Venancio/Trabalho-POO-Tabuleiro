package com.faculdade.controle;
import com.faculdade.componentes.Baralho;
import com.faculdade.componentes.Dado;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List; // ✅ Novo Import

import com.faculdade.jogador.*;
import com.faculdade.tabuleiro.Tabuleiro;

public abstract class Jogo {
    // ✅ CORREÇÃO: Usando a interface List e inicializando com ArrayList (implícito nas subclasses)
    protected List<Jogador> jogadores; 
    protected Tabuleiro tabuleiro;
    protected Baralho baralho;
    protected Dado dado;
    
    // ✅ NOVA IMPLEMENTAÇÃO: Lógica Comum Centralizada
    public void iniciarJogo(int quantidade) {
        jogadores = new ArrayList<>(); // Inicializa a implementação concreta
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
            // ✅ CORREÇÃO: Usando constantes de Jogador.java
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
    
    public abstract void terminarJogo();
        
    public Baralho getBaralho() { return baralho; } // Implementação Concreta
    public Dado getDado() { return dado; } // Implementação Concreta
    public Tabuleiro getTabuleiro() { return tabuleiro; } // Implementação Concreta
    
    // ✅ CORREÇÃO: Retornando List (interface) e protegendo a lista (unmodifiableList)
    public List<Jogador> getJogadores() {
        return Collections.unmodifiableList(jogadores);
    }
}