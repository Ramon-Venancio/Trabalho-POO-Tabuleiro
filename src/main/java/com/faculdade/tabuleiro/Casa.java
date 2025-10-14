/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.faculdade.tabuleiro;
import com.faculdade.controle.Jogo;
import com.faculdade.jogador.Jogador;
import java.util.ArrayList;

/**
 *
 * @author vinan
 */
public abstract class Casa {
    protected int numero;
    protected ArrayList<Jogador> jogadoresNaCasa;
    
    public Casa(int numero) {
        this.numero=numero;
    }

    // adicionar get e set da pasiçao para ser mudado
    
    public abstract void aplicarEfeito(Jogador jogador, Jogo jogo);
}
