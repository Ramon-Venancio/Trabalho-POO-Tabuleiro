/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.faculdade.jogador;

/**
 *
 * @author Antônio Carlos
 */
public abstract class Jogador {
    protected int idJogador;
    protected String cor;
    protected String nome;
    protected int posicao;
    protected int quantidadePassos;
    protected boolean podeJogar;
    
    public Jogador(int idJogador, String cor, String nome) {
        this.idJogador=idJogador;
        this.cor=cor;
        this.nome=nome;
    }

    public int getidJogador(){
        return idJogador;
    }

    public String getCor(){
        return cor;
    }

    public void setCor(String cor){
        this.cor = cor;
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public int getPosicao() {
        return posicao;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

    public int getJogadas() {
        return quantidadePassos;
    }

    public void setJogadas(int jogadas) {
        this.quantidadePassos = jogadas;
    }

    public boolean getPerdeRodada() {
        return podeJogar;
    }

    public void setPerdeRodada(boolean podeJogar) {
        this.podeJogar = podeJogar;
    }

    public void incrementarJogadas(){
        this.quantidadePassos++;
    }

    public abstract int jogarDados();
    
}
