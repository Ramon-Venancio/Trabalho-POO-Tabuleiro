package com.faculdade.tabuleiro;

import com.faculdade.controle.*;
import com.faculdade.jogador.Jogador;
import java.util.ArrayList;
import java.util.List;


public class Tabuleiro {
    
    public static final int TOTAL_CASAS = 40;
    private final List<Casa> casas = new ArrayList<>();

    public Tabuleiro() {
        // Cria casas de 1 até 40 (índices 0 a 40)
        for (int i = 0; i <= TOTAL_CASAS; i++) casas.add(new Casa(i));

        marcarCasas(TipoCasa.PERDE_A_VEZ, 10, 25, 38);
        marcarCasas(TipoCasa.SURPRESA, 13);
        marcarCasas(TipoCasa.SORTE, 5, 15, 30);
        marcarCasas(TipoCasa.VOLTA_AO_INICIO, 17, 27);
        marcarCasas(TipoCasa.MAGICA, 20, 35);
    }

    private void marcarCasas(TipoCasa tipo, int... numeros) {
        for (int numero : numeros)
            if (numero >= 1 && numero <= casas.size())
                casas.get(numero).setTipo(tipo);  
    }

    public Casa getCasa(int posicao) {
        
        if (posicao < 1) posicao = 1;
        if (posicao > TOTAL_CASAS) posicao = TOTAL_CASAS;

        return casas.get(posicao);
    }

    public void moverJogador(Jogador jogador, int passos, List<Jogador> todosJogadores, Jogo jogo) {
        int novaPosicao;
        
        if (jogo instanceof JogoNormal) {
            novaPosicao = jogador.getPosicao() + passos;
        } else {
            novaPosicao = passos - 1;
        }
        
        // Correção: impedir posição 41 (índice inválido)
        if (novaPosicao < 1) novaPosicao = 1;
        if (novaPosicao > TOTAL_CASAS) novaPosicao = TOTAL_CASAS;

        jogador.setPosicao(novaPosicao);

        Casa casaAtual = getCasa(novaPosicao);
        casaAtual.aplicarEfeito(jogador, todosJogadores, jogo);
    }

    public int getTotalCasas() { return casas.size(); }
    public List<Casa> getCasas() { return casas; }
}
