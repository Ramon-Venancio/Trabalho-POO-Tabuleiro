package com.faculdade.controle;
import com.faculdade.componentes.Baralho;
import com.faculdade.componentes.Dado;
import java.util.ArrayList;
import java.util.List; // Import necessário

import com.faculdade.jogador.*;
import com.faculdade.tabuleiro.Tabuleiro;

public abstract class Jogo {
    // CORRIGIDO: Usa a interface List
    protected List<Jogador> jogadores;
    protected Tabuleiro tabuleiro;
    protected Baralho baralho;
    protected Dado dado;
    
    public abstract void iniciarJogo(int quantidade);
    
    public abstract void terminarJogo();
        
    public abstract Baralho getBaralho();
    
    public abstract Dado getDado();
    
    public abstract Tabuleiro getTabuleiro();
    
    // CORRIGIDO: Retorna a interface List
    public abstract List<Jogador> getJogadores();
}