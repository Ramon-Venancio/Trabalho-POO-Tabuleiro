package com.faculdade.tabuleiro;
import com.faculdade.controle.Jogo;
import com.faculdade.jogador.Jogador;
import java.util.ArrayList;

public class Casa {

    private final int numero; //Número da casa (Ex: 1 - 40)
    private TipoCasa tipo; //Tipo da casa (NORMAL, SORTE, MÁGICA, ETC.)
    
    /*
    Construtor - cria uma casa normal com um número específico
    */
    
    public Casa(int numero){
        this.numero = numero;
        this.tipo = TipoCasa.NORMAL;
    }
    
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
