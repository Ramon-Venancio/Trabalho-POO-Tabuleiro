package com.faculdade.componentes;

public class Dado {
    
    private static final java.util.Random RANDOM = new java.util.Random(); 

    public static int rolarDados() {
        
        return RANDOM.nextInt(6) + 1; 
    }
}