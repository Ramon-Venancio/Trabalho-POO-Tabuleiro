/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.faculdade.controle;
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
    
    public abstract void iniciarJogo(int quantidade);
    
    public abstract void terminarJogo();
    
    public abstract void executarTurno();
}