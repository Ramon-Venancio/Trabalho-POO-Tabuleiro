
package com.faculdade.tabuleiro;

import com.faculdade.controle.Jogo;
import com.faculdade.jogador.Jogador;
import com.faculdade.jogador.JogadorNormal;
import com.faculdade.jogador.JogadorSortudo;
import com.faculdade.jogador.JogadorAzarado;
import java.util.List;
import java.util.Random;

public class Casa {

    private final int numero;
    private TipoCasa tipo;
    
    /*
    Construtor - cria uma casa normal com um número específico
    */
    
    public Casa(int numero){
        this.numero = numero;
        this.tipo = TipoCasa.NORMAL;
    }

    // adicionar get e set da posiçao para ser mudado
    
    //Retorna o número da casa
    public int getNumero(){
        return numero;
    }
    
    //Retorna o tipo da casa
    public TipoCasa getTipo(){
        return tipo;
    }
    
    //Define o tipo da casa
    public void setTipo(TipoCasa tipo){
        this.tipo = tipo;
    }
    
     public void aplicarEfeito(Jogador jogador, List<Jogador> todosJogadores) {
        switch (tipo) {
            case MAGICA -> aplicarEfeitoMagico(jogador, todosJogadores);
            case SORTE -> jogador.setPosicao(Math.min(jogador.getPosicao() + 3,Tabuleiro.TOTAL_CASAS));
            case VOLTA_AO_INICIO -> jogador.setPosicao(1);
            case PERDE_A_VEZ -> jogador.setPerdeRodada(true);
            case SURPRESA -> aplicarEfeitoSurpresa(jogador,todosJogadores);
            default -> {}
        }
    }

    private void aplicarEfeitoMagico(Jogador jogador, List<Jogador> todosJogadores) {
        if (todosJogadores.size() <= 1) return;
        Jogador maisAtras = null;
        for (Jogador j : todosJogadores) {
            if (j == jogador) continue;
            if (maisAtras == null || j.getPosicao() < maisAtras.getPosicao()) maisAtras = j;
        }
        if (maisAtras == null || jogador.getPosicao() <= maisAtras.getPosicao()) return;
        int temp = jogador.getPosicao();
        jogador.setPosicao(maisAtras.getPosicao());
        maisAtras.setPosicao(temp);
    }
    
    private void aplicarEfeitoSurpresa(Jogador jogador, List<Jogador> todosJogadores) {
        int tipoSorteado = new Random().nextInt(3);
        Jogador novoTipo = switch (tipoSorteado) {
            case 0 -> new JogadorNormal(jogador.getIdJogador(), jogador.getCor(), jogador.getNome());
            case 1 -> new JogadorSortudo(jogador.getIdJogador(), jogador.getCor(), jogador.getNome());
            default -> new JogadorAzarado(jogador.getIdJogador(), jogador.getCor(), jogador.getNome());
        };
        novoTipo.setPosicao(jogador.getPosicao());
        novoTipo.setJogadas(jogador.getJogadas());
        novoTipo.setPerdeRodada(jogador.isPerdeRodada());
        todosJogadores.set(todosJogadores.indexOf(jogador), novoTipo);
        }
    
    @Override
    public String toString(){
         return String.format("Casa %02d (%s)", numero, tipo);
    }
}
