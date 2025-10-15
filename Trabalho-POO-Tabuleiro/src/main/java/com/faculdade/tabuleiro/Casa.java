<<<<<<< HEAD
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
=======
>>>>>>> ph
package com.faculdade.tabuleiro;

import com.faculdade.controle.Jogo;
import com.faculdade.jogador.Jogador;

<<<<<<< HEAD
/**
 *
 * @author vinan
 */
public abstract class Casa {
    protected int numero;
    protected ArrayList<Jogador> jogadoresNaCasa;
=======
public class Casa {

    private final int numero; //Número da casa (Ex: 1 - 40)
    private TipoCasa tipo; //Tipo da casa (NORMAL, SORTE, MÁGICA, ETC.)
>>>>>>> ph
    
    /*
    Construtor - cria uma casa normal com um número específico
    */
    
    public Casa(int numero){
        this.numero = numero;
        this.tipo = TipoCasa.NORMAL;
    }

    // adicionar get e set da posiçao para ser mudado
    
    //Retorna o número da casa
    public int getNumero(){
        return numero;
    }
    
    //Retorna o tipo da casa
    public TipoCasa getTipo(){
        return tipo;
    }
    
    //Define o tipo da casa
    public void setTipo(TipoCasa tipo){
        this.tipo = tipo;
    }
    
    @Override
    public String toString(){
         return String.format("Casa %02d (%s)", numero, tipo);
         /*
           %02d - tem que ter dois zeros a esquerda - 
            Ex: ("%02d", 0) - saída: 00 
                ("%02d", 3) - saída: 03 
         */
    }
}
