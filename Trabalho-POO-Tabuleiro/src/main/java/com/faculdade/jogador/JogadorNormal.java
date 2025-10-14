/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.faculdade.jogador;

/**
 *
 * @author vinan
 */
public class JogadorNormal extends Jogador {
        public JogadorNormal(int idJogador, String cor, String nome, String tipo) {
        super(idJogador, cor, nome, tipo);
    }
    
    @Override
    public int jogarDados() {
        return 0;
    }
}
