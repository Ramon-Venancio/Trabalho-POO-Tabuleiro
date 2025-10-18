/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.faculdade.controle;

import com.faculdade.componentes.*;
import com.faculdade.jogador.*;
import com.faculdade.tabuleiro.Tabuleiro;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vinan
 */
public class JogoNormal extends Jogo {

    @Override
    public void iniciarJogo(int quantidade) {
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
            if (tipoAleatorio.equals("Normal")) {
                jogador = new JogadorNormal(i, dadosJogador[1], dadosJogador[0]);
            } else if (tipoAleatorio.equals("Azarado")) {
                jogador = new JogadorAzarado(i, dadosJogador[1], dadosJogador[0]);
            } else if(tipoAleatorio.equals("Sortudo")) {
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
    public ArrayList<Jogador> getJogadores() {
        return (ArrayList<Jogador>) jogadores.clone();
    }
}
