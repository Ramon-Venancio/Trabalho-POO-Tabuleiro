package com.faculdade.controle;

//Imports herdados da classe jogo

public class JogoDebug extends Jogo {
    
    // ✅ CORREÇÃO: Não há mais necessidade de sobrescrever iniciarJogo.
    // Ele é herdado e executa a lógica de inicialização do Jogo.java.

    @Override
    public void terminarJogo() {
        /*
        * A implementação continua vazia, específica para o modo debug.
        */
    }
    
    // ✅ CORREÇÃO: Os métodos getBaralho(), getJogadores(), etc.,
    // foram removidos daqui, pois são herdados da classe Jogo.
}