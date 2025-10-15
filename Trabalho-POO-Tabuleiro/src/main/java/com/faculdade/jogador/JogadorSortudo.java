package com.faculdade.jogador;

import com.faculdade.componentes.Dado;

<<<<<<< HEAD
/**
 *
 * @author vinan
 */
public class JogadorSortudo extends Jogador {
        public JogadorSortudo(int idJogador, String cor, String nome, String tipo) {
=======
public class JogadorSortudo extends Jogador {
        public JogadorSortudo(int idJogador, String cor, String nome) {
>>>>>>> ph
        super(idJogador, cor, nome);
    }
    
    @Override
    public int jogarDados() {
        int soma;
        do{
            soma = Dado.rolarDados() + Dado.rolarDados();
        } while (soma < 7);
        setJogadas(getJogadas() + 1);
        return soma;
    }
}