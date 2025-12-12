package com.faculdade.tabuleiro;

import com.faculdade.controle.Jogo;
import com.faculdade.controle.Main;
import com.faculdade.jogador.Jogador;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

// A classe Casa agora é abstrata.
public abstract class Casa { 
    
    // O logger pode ser herdado, mas o ideal é que cada subclasse tenha o seu
    protected static final Logger logger = Logger.getLogger(Casa.class.getName());

    protected final int numero;
    // REMOVA: private TipoCasa tipo;
    
    // Construtor é chamado pelas subclasses
    public Casa(int numero){
        this.numero = numero;
    }

    public int getNumero(){
        return numero;
    }

    // Este é o contrato do Strategy Pattern.
    public abstract void aplicarRegra(Jogador jogador, List<Jogador> todosJogadores, Jogo jogo);

    // Sobrescreve toString() para mostrar o tipo de casa no tabuleiro
    @Override
    public String toString() {
        // Retorna o nome da classe (ex: CasaSimples) sem o prefixo "Casa"
        String nome = getClass().getSimpleName();
        if (nome.startsWith("Casa")) {
            return nome.substring(4);
        }
        return nome;
    }

    // REMOVE TODAS AS LÓGICAS ANTIGAS: aplicarEfeito(), aplicarEfeitoMagico(), aplicarEfeitoSorte(), etc.
    // Toda a lógica antiga de switch/case e métodos privados foram removidas
}