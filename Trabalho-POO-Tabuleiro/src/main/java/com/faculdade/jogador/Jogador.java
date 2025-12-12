package com.faculdade.jogador;

import java.util.Objects;

/**
 * Classe Abstrata Jogador
 * Suporta Factory, Strategy e a Casa JogaDeNovo.
 */
public abstract class Jogador {

    // Tipos de jogador (usados pela Factory)
    public static final String TIPO_NORMAL = "NORMAL";
    public static final String TIPO_AZARADO = "AZARADO";
    public static final String TIPO_SORTUDO = "SORTUDO";

    protected int idJogador;
    protected String cor;
    protected String nome;
    protected int posicao;
    protected int quantidadeJogadas;
    protected boolean perdeRodada;
    protected int moedas;
    protected boolean jogaDeNovo;

    protected Jogador(int idJogador, String cor, String nome) {
        this.idJogador = idJogador;
        this.cor = cor;
        this.nome = nome;
        this.posicao = 0;
        this.quantidadeJogadas = 0;
        this.perdeRodada = false;
        this.moedas = 0;
        this.jogaDeNovo = false;
    }

    public abstract int[] jogarDados();

    public int getIdJogador() {
        return idJogador;
    }

    public String getCor() {
        return cor;
    }

    public String getNome() {
        return nome;
    }

    public int getPosicao() {
        return posicao;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

    public int getJogadas() {
        return quantidadeJogadas;
    }

    public void setJogadas(int jogadas) {
        this.quantidadeJogadas = jogadas;
    }

    public boolean getPerdeRodada() {
        return perdeRodada;
    }

    public void setPerdeRodada(boolean perdeRodada) {
        this.perdeRodada = perdeRodada;
    }

    public int getMoedas() {
        return moedas;
    }

    public void setMoedas(int moedas) {
        this.moedas = moedas;
    }

    // ✅ NOVO: controle de jogar novamente
    public boolean isJogaDeNovo() {
        return jogaDeNovo;
    }

    public void setJogaDeNovo(boolean jogaDeNovo) {
        this.jogaDeNovo = jogaDeNovo;
    }

    // --- MÉTODOS AUXILIARES ---
    public void incrementarJogadas() {
        this.quantidadeJogadas++;
    }

    public void adicionarMoedas(int qtd) {
        this.moedas += qtd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Jogador jogador = (Jogador) o;
        return idJogador == jogador.idJogador;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idJogador);
    }

    @Override
    public String toString() {
        return String.format(
            "%s (Posição: %d | Moedas: %d | Jogadas: %d)",
            nome, posicao, moedas, quantidadeJogadas
        );
    }
}
