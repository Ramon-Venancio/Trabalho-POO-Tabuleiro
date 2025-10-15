/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.faculdade.tabuleiro;
import java.util.ArrayList;

/**
 *
 * @author vinan
 */
public class Tabuleiro {
    private ArrayList<Casa> casas;
    
    public Tabuleiro() {
        casas = new ArrayList<>(); 
        
        for (int i = 0; i < 40; i++) {
            if (i==9 || i==24 || i==37) {
                Casa casa = new CasaPulaVez(i);
                casas.add(casa);
            } else if (i==12) {
                Casa casa = new CasaSurpresa(i);
                casas.add(casa);
            } else if (i==4 || i==14 || i==29) {
                Casa casa = new CasaSorte(i);
                casas.add(casa);
            } else if (i==16 || i==26) {
                Casa casa = new CasaRevez(i);
                casas.add(casa);
            } else if (i==19 || i==34) {
                Casa casa = new CasaMagica(i);
                casas.add(casa);
            } else {
                Casa casa = new CasaNormal(i);
                casas.add(casa);
            }
        }
    }
    
    public String atualizarCasa() {
        return "";
    }
    
    public boolean mudarCasa() {
        return true;
    }
}