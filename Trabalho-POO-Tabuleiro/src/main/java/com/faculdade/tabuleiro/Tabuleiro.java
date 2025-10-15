package com.faculdade.tabuleiro;

import com.faculdade.jogador.Jogador;
import com.faculdade.jogador.JogadorAzarado;
import com.faculdade.jogador.JogadorNormal;
import com.faculdade.jogador.JogadorSortudo;
import java.util.ArrayList;
import java.util.List;


public class Tabuleiro {
    
    public static final int TOTAL_CASAS = 40;
    private final List<Casa> casas = new ArrayList<>();

    public Tabuleiro() {
        for (int i = 1; i <= TOTAL_CASAS; i++) casas.add(new Casa(i));
        marcarCasas(TipoCasa.PERDE_A_VEZ, 10, 25, 38);
        marcarCasas(TipoCasa.SURPRESA, 13);
        marcarCasas(TipoCasa.SORTE, 5, 15, 30);
        marcarCasas(TipoCasa.VOLTA_AO_INICIO, 17, 27);
        marcarCasas(TipoCasa.MAGICA, 20, 35);
    }

    private void marcarCasas(TipoCasa tipo, int... numeros) {
        for (int numero : numeros) if (numero >= 1 && numero <= casas.size()) casas.get(numero - 1).setTipo(tipo);
    }

    public Casa getCasa(int posicao) {
        if (posicao < 1) posicao = 1;
        if (posicao > casas.size()) posicao = casas.size();
        return casas.get(posicao - 1);
    }

    public void moverJogador(Jogador jogador, int passos, List<Jogador> todosJogadores) {
        int nova = jogador.getPosicao() + passos;
        if(nova < 1) nova = 1;
        if (nova > casas.size()) nova = casas.size();
        jogador.setPosicao(nova);
        Casa atual = getCasa(nova);
        atual.aplicarEfeito(jogador, todosJogadores);
    }

    public int getTotalCasas() { return casas.size(); }
    public List<Casa> getCasas() { return casas; }
}