package com.faculdade.jogador;

import com.faculdade.componentes.Dado;

import java.util.Random;
public class JogadorNormal extends Jogador {
        public JogadorNormal(int idJogador, String cor, String nome) {
        super(idJogador, cor, nome);
    }
    
    @Override
    public int jogarDados() {
            int soma;
            soma = Dado.rolarDados() + Dado.rolarDados();
            setJogadas(getJogadas() + 1);
            return soma;
    }
}