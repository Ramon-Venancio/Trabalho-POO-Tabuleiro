/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.faculdade.jogador;

/**
 *
 * @author Antônio Carlos
 */
public class Jogador {
    protected int idJogador;
    protected String cor;
    protected String nome;
    protected int posicao = 0;
    protected int jogadas = 0;
    protected  boolean perdeRodada = false;
    
    public Jogador(int idJogador, String cor, String nome) {
        this.idJogador=idJogador;
        this.cor=cor;
        this.nome=nome;
        this.posicao = 0;
        this.jogadas = 0;
        this.perdeRodada = false;
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
        if (this.posicao > 40) this.posicao = 40;
        if (this.posicao < 0) this.posicao = 0;
    }

    public int getJogadas() {
        return jogadas;
    }

    public void setJogadas(int jogadas) {
        this.jogadas = jogadas;
    }

    public boolean isPerdeRodada() {
        return perdeRodada;
    }

    public void setPerdeRodada(boolean perdeRodada) {
        this.perdeRodada = perdeRodada;
    }

    public void incrementarJogadas(){
        this.jogadas++;
    }

    public abstract int jogarDados();
    
}
