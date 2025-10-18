package com.faculdade.componentes;
import java.util.Random;


public class Baralho {
    public String[] tiposCartas;
    
    private final Random random = new Random();
    
    public Baralho() {
        tiposCartas = new String[] {"Sortudo", "Azarado", "Normal"};
    }
    
    public String sortearTipo() {
        // 1. Determina o número máximo de opções (tamanho do array)
        int quantidadeDeTipos = tiposCartas.length;
        
        // 2. Sorteia um índice aleatório entre 0 (inclusivo) e quantidadeDeTipos (exclusivo)
        int indiceSorteado = random.nextInt(quantidadeDeTipos);
        
        // 3. Retorna a String que está na posição sorteada
        return tiposCartas[indiceSorteado];
    }
}