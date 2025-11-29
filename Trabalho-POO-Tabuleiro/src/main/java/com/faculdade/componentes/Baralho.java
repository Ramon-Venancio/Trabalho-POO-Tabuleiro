package com.faculdade.componentes;
import java.util.Random;

public class Baralho {
    
    // 1. MUDANÇA PRINCIPAL: O campo agora é uma constante estática final (public static final)
    public static final String[] TIPOS_CARTAS = {"Sortudo", "Azarado", "Normal"};
    
    private final Random random = new Random();
    
    public Baralho() {
        // 2. MUDANÇA: A inicialização foi removida, pois a constante já é inicializada acima.
    }
    
    public String sortearTipo() {
        // 3. MUDANÇA: O método usa o novo nome da constante: TIPOS_CARTAS
        int quantidadeDeTipos = TIPOS_CARTAS.length;
        
        int indiceSorteado = random.nextInt(quantidadeDeTipos);
        
        return TIPOS_CARTAS[indiceSorteado];
    }
}