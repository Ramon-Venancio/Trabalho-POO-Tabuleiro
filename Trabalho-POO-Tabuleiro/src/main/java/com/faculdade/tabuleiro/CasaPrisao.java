package com.faculdade.tabuleiro;

import com.faculdade.controle.Jogo;
import com.faculdade.controle.Main;
import com.faculdade.jogador.Jogador;
import java.util.List;
import java.util.logging.Level;

public class CasaPrisao extends Casa {

    public CasaPrisao(int numero) {
        super(numero);
    }

    @Override
    public void aplicarRegra(Jogador jogador, List<Jogador> todosJogadores, Jogo jogo) {
        // Regra: Não joga a próxima rodada
        jogador.setPerdeRodada(true);
        logger.log(Level.INFO, "\n{0}{1}{2} caiu na Casa Prisão ({3}) e perderá a próxima rodada!", 
            new Object[]{jogador.getCor(), jogador.getNome(), Main.ANSI_RESET, this.numero});
    }
}