package com.faculdade.jogador;

import com.faculdade.componentes.Dado;

public class JogadorSortudo extends Jogador {
        public JogadorSortudo(int idJogador, String cor, String nome) {

        super(idJogador, cor, nome);
    }
    
    @Override
    public int[] jogarDados() {
        int[] dados = new int[2];
        int soma;
        do{
            dados[0] = Dado.rolarDados();
            dados[1] = Dado.rolarDados();
            soma = dados[0] + dados[1];
        } while (soma < 7);
        incrementarJogadas();
        return dados;
    }
}
