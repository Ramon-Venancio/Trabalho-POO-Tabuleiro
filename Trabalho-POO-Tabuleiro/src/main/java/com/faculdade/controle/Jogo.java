/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.faculdade.controle;
import com.faculdade.componentes.Baralho;
import com.faculdade.componentes.Dado;
import java.util.ArrayList;

import com.faculdade.jogador.*;
import com.faculdade.tabuleiro.Tabuleiro;

/**
 *
 * @author vinan
 */
public abstract class Jogo {
    protected ArrayList<Jogador> jogadores;
    protected Tabuleiro tabuleiro;
    protected Baralho baralho;
    protected Dado dado;
    
    public abstract void iniciarJogo(int quantidade);
    
    public abstract void terminarJogo();
        
    public abstract Baralho getBaralho();
    
    public abstract Dado getDado();
    
    public abstract Tabuleiro getTabuleiro();
    
    public abstract ArrayList<Jogador> getJogadores();
}