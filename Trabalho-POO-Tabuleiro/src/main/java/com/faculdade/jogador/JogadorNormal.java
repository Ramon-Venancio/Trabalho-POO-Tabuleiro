package com.faculdade.jogador;

import com.faculdade.componentes.Dado;

import java.util.Random;
public class JogadorNormal extends Jogador {
        public JogadorNormal(int idJogador, String cor, String nome) {
        super(idJogador, cor, nome);
    }
    
    @Override
    public int[] jogarDados() {
        int[] dados = new int[2];
        dados[0] = Dado.rolarDados();
        dados[1] = Dado.rolarDados();
        incrementarJogadas();
        return dados;
    }
}