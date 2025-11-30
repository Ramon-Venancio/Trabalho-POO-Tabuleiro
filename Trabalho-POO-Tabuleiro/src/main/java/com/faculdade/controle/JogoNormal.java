package com.faculdade.controle;

//Imports herdados da classe jogo

public class JogoNormal extends Jogo {

    // ✅ REMOVIDO: Método iniciarJogo foi movido para Jogo.java
    
    @Override
    public void terminarJogo() {
        // Lógica de finalização, se houver.
    }
    
    // ✅ REMOVIDO: Métodos getBaralho(), getDado(), getTabuleiro() e getJogadores()
    // Agora são herdados do Jogo.java, ou têm implementação padrão lá.
}