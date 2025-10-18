package com.faculdade.tabuleiro;

import com.faculdade.controle.Jogo;
import com.faculdade.controle.Main;
import com.faculdade.jogador.*;
import java.util.ArrayList;
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
    
     public void aplicarEfeito(Jogador jogador, List<Jogador> todosJogadores, Jogo jogo) {
        switch (tipo) {
            case MAGICA -> aplicarEfeitoMagico(jogador, todosJogadores);
            case SORTE -> {
                if (!(jogador instanceof JogadorAzarado)) {
                    jogador.setPosicao(Math.min(jogador.getPosicao() + 3, Tabuleiro.TOTAL_CASAS));
                    System.out.println("\nCasa da Sorte " + Main.EMOJI_TREVO + ": Você pula 3 casas!");
                } else {
                    System.out.println("\nCasa da Sorte " + Main.EMOJI_TREVO + ": Você é um Jogador Azarado! Bônus de pular 3 casas anulado.");
                }
            }
            case VOLTA_AO_INICIO -> {
                System.out.println("\nCasa do Revés " + Main.EMOJI_SETA_RETORNO + " : Escolha um jogador para voltar pro inicio");
                int count = 1;
                ArrayList<Jogador> jogadoresSelecionados = new ArrayList<Jogador>();
                while(true) {
                    for (Jogador j : todosJogadores) {
                        if (!(j.equals(jogador))) {
                            System.out.println(count + " - " + j.getNome());
                            jogadoresSelecionados.add(j);
                            count++;
                        }
                    }
                    int escolhaJogador = Main.scanner.nextInt();
                    
                    Main.scanner.nextLine();
                    
                    if (escolhaJogador > 0 && escolhaJogador < count) {
                        int indexJogadorSelecionado = jogadoresSelecionados.get(escolhaJogador-1).getIdJogador();
                        todosJogadores.get(indexJogadorSelecionado).setPosicao(0);
                        break;
                    } else {
                        System.out.println("Escolha invalida!\nDigite o numero respectivo do jogador para voltar ao inicio.");
                    }
                }
            }
            case PERDE_A_VEZ -> {
                jogador.setPerdeRodada(false);
                System.out.println("\nCasa Pula Vez " + Main.EMOJI_ROSTO_CONGELADO + ": Você não poderá jogador proxima rodada.");
            }
            case SURPRESA -> {
                aplicarEfeitoSurpresa(jogador, todosJogadores, jogo);
                System.out.println("\nCasa Surpresa " + Main.EMOJI_SETAS_HORARIAS + ": Seu tipo foi mudado!");
            }
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
        if (maisAtras == null || jogador.getPosicao() <= maisAtras.getPosicao()) {
            System.out.println("\nCasa Mágica " + Main.EMOJI_BRILHO + ": Você já é o ultimo jogador. Não troca de lugar com nenhum jogador.");
            return;
        }
        int temp = jogador.getPosicao();
        jogador.setPosicao(maisAtras.getPosicao());
        maisAtras.setPosicao(temp);
        System.out.println("\nCasa Mágica " + Main.EMOJI_BRILHO + ": Você troca de lugar com o ultimo jogador " + maisAtras.getCor() + maisAtras.getNome() + Main.ANSI_RESET);
    }
    
    private void aplicarEfeitoSurpresa(Jogador jogador, List<Jogador> todosJogadores, Jogo jogo) {
        String tipoSorteado = jogo.getBaralho().sortearTipo();
        Jogador jogadorNovoTipo = null;
        
        if (tipoSorteado.equals("Normal")) {
            jogadorNovoTipo = new JogadorNormal(jogador.getIdJogador(), jogador.getCor(), jogador.getNome());
        } else if (tipoSorteado.equals("Azarado")) {
            jogadorNovoTipo = new JogadorAzarado(jogador.getIdJogador(), jogador.getCor(), jogador.getNome());
        } else if(tipoSorteado.equals("Sortudo")) {
            jogadorNovoTipo = new JogadorSortudo(jogador.getIdJogador(), jogador.getCor(), jogador.getNome());
        }
        
        jogadorNovoTipo.setPosicao(jogador.getPosicao());
        jogadorNovoTipo.setJogadas(jogador.getJogadas());
        jogadorNovoTipo.setPerdeRodada(jogador.getPerdeRodada());
        todosJogadores.set(todosJogadores.indexOf(jogador), jogadorNovoTipo);
        }
    
    @Override
    public String toString(){
         return String.format("Casa %02d (%s)", numero, tipo);
    }
}
