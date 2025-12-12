package com.faculdade.jogador;

public class JogadorFactory {

    /**
     * Centraliza a lógica de instanciacao dos jogadores.
     * @param tipo A string que define o tipo de jogador a ser criado.
     * @param id O ID do jogador.
     * @param cor A cor do jogador.
     * @param nome O nome do jogador.
     * @return A instância do jogador concreto (Normal, Azarado ou Sortudo).
     */
    public static Jogador criarJogador(String tipo, int id, String cor, String nome) {
        
        // Factory Method: Usa o tipo para decidir qual classe concreta criar
        return switch (tipo.toUpperCase()) {
            case Jogador.TIPO_AZARADO -> new JogadorAzarado(id, cor, nome);
            case Jogador.TIPO_SORTUDO -> new JogadorSortudo(id, cor, nome);
            default -> new JogadorNormal(id, cor, nome);
        };
    }
}