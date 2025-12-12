package com.faculdade.controle;

//Imports herdados da classe jogo

public class JogoDebug extends Jogo {
    
    // Não há mais necessidade de sobrescrever iniciarJogo.
    // Ele é herdado e executa a lógica de inicialização do Jogo.java.

    @Override
    public void terminarJogo() {
        // O método é obrigatório por ser abstrato na classe mãe 'Jogo', 
        // mas é mantido vazio nesta classe de debug/teste por não ter lógica final.
    }
    
    // Os métodos getBaralho(), getJogadores(), etc.,
    // foram removidos daqui, pois são herdados da classe Jogo.
}