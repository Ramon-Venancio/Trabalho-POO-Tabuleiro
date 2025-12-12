package com.faculdade.tabuleiro;

import com.faculdade.controle.Jogo;
import com.faculdade.controle.Main;
import com.faculdade.jogador.Jogador;
import java.util.List;
import java.util.logging.Level;

public class CasaReversa extends Casa {

    public CasaReversa(int numero) {
        super(numero);
    }

    @Override
    public void aplicarRegra(Jogador jogador, List<Jogador> todosJogadores, Jogo jogo) {

        Jogador maisAtras = jogador;

        for (Jogador j : todosJogadores) {
            if (j.getPosicao() < maisAtras.getPosicao()) {
                maisAtras = j;
            }
        }

        if (maisAtras == jogador) {
            logger.log(Level.INFO,
                "\n{0}{1}{2} caiu na Casa Reversa ({3}), mas já é o último. Nada acontece.",
                new Object[]{jogador.getCor(), jogador.getNome(), Main.ANSI_RESET, numero});
            return;
        }

        int posTemp = jogador.getPosicao();
        jogador.setPosicao(maisAtras.getPosicao());
        maisAtras.setPosicao(posTemp);

        logger.log(Level.INFO,
            "\n{0}{1}{2} caiu na Casa Reversa ({3}) e trocou de lugar com {4}{5}{6}!",
            new Object[]{
                jogador.getCor(), jogador.getNome(), Main.ANSI_RESET,
                numero,
                maisAtras.getCor(), maisAtras.getNome(), Main.ANSI_RESET
            });
    }
}
