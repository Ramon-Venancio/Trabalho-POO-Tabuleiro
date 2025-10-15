
package com.faculdade.jogador;
import com.faculdade.componentes.Dado;

public class JogadorAzarado extends Jogador {
    public JogadorAzarado(int idJogador, String cor, String nome) {
        super(idJogador, cor, nome);
    }
    
    @Override
    public int jogarDados() {
        int soma;
        do{
            soma = Dado.rolarDados() + Dado.rolarDados();
        } while(soma > 6);
        setJogadas(getJogadas() + 1);
        return soma;
    }
}