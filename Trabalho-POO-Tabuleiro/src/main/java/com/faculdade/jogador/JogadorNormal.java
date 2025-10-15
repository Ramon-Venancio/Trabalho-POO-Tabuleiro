/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.faculdade.jogador;

import com.faculdade.componentes.Dado;

/**
 *
 * @author vinan
 */
import java.util.Random;
public class JogadorNormal extends Jogador {
        public JogadorNormal(int idJogador, String cor, String nome) {
        super(idJogador, cor, nome);
    }
    
    @Override
    public int jogarDados() {
            int soma;
            soma = Dado.rolarDados() + Dado.rolarDados();
            setJogadas(getJogadas() + 1);
            return soma;
    }
}
