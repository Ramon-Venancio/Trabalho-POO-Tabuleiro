package com.faculdade.tabuleiro;

import com.faculdade.controle.Jogo;
import com.faculdade.controle.Main;
import com.faculdade.jogador.Jogador;
import java.util.List;
import java.util.logging.Level;

public class CasaSimples extends Casa {

    public CasaSimples(int numero) {
        super(numero);
    }

    @Override
    public void aplicarRegra(Jogador jogador, List<Jogador> todosJogadores, Jogo jogo) {
        // Requisito do 3º Trabalho: Casa Simples dá 10 moedas
        jogador.adicionarMoedas(10); 
        logger.log(Level.INFO, "\n{0}{1}{2} caiu na Casa Simples ({3}) e ganhou 10 moedas! Total: {4}", 
            new Object[]{jogador.getCor(), jogador.getNome(), Main.ANSI_RESET, this.numero, jogador.getMoedas()});
    }
}