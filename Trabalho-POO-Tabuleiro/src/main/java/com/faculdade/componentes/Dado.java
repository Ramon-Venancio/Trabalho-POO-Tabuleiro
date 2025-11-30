package com.faculdade.componentes;
import java.util.Random;

public class Dado {
    public static int rolarDados() {
        return new Random().nextInt(6) + 1; // Cria um novo objeto Random a cada chamada
    }
}