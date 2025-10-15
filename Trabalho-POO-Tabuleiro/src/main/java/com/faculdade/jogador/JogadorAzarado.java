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
