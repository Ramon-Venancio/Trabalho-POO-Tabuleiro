/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.faculdade.componentes;
import java.util.Random;

public class Dado {
    private static Random random = new Random();
    public static int rolarDados() {
        return random.nextInt(6) + 1;
    }
}
