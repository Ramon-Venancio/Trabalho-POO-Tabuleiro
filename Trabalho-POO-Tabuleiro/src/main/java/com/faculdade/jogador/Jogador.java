/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.faculdade.jogador;

import com.faculdade.tabuleiro.Casa;

/**
 *
 * @author vinan
 */
public abstract class Jogador {
    protected int idJogador;
    protected String cor;
    protected String nome;
    protected Casa posicao;
    protected int quantidadePassos;
    protected boolean podeJogar;
    
    public Jogador(int idJogador, String cor, String nome) {
        this.idJogador=idJogador;
        this.cor=cor;
        this.nome=nome;
    }
    
    public abstract int jogarDados();
}
