
package com.faculdade.tabuleiro;
import com.faculdade.controle.Jogo;
import com.faculdade.jogador.Jogador;
import java.util.ArrayList;


public abstract class Casa {
    protected int numero;
    protected ArrayList<Jogador> jogadoresNaCasa;
    
    public Casa(int numero) {
        this.numero=numero;
    }
    
    public abstract void aplicarEfeito(Jogador jogador, Jogo jogo);
}

//Abobora com leite sdasdasdasd 

