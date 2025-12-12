package com.faculdade.jogador;

public abstract class Jogador {

    // NOVO: Constantes para os tipos de jogador (centralização de strings).
    public static final String TIPO_NORMAL = "Normal";
    public static final String TIPO_AZARADO = "Azarado";
    public static final String TIPO_SORTUDO = "Sortudo";
    
    protected int idJogador;
    protected String cor;
    protected String nome;
    protected int posicao;
    protected int quantidadePassos;
    protected boolean podeJogar;
    
    protected Jogador(int idJogador, String cor, String nome) {
        this.idJogador=idJogador;
        this.cor=cor;
        this.nome=nome;
        this.podeJogar = true;
        
        this.posicao = 0;
    }

    public int getIdJogador(){
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

    public abstract int[] jogarDados();
    
    @Override
    public boolean equals(Object outro) {
        // 1. Verifica se são o mesmo objeto na memória (otimização)
        if (this == outro) {
            return true;
        }

        // 2. Verifica se o objeto não é nulo e é do tipo Jogador
        if (outro == null || getClass() != outro.getClass()) {
            return false;
        }

        // 3. Converte para o tipo Jogador
        Jogador outroJogador = (Jogador) outro;

        // 4. Compara os atributos de VALOR (o que define a igualdade)
        return idJogador == outroJogador.idJogador; 
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + this.idJogador;
        return hash;
    }

}

