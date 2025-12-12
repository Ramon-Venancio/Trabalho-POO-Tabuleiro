package com.faculdade.tabuleiro;

import com.faculdade.controle.Jogo;
import com.faculdade.controle.Main;
import com.faculdade.jogador.Jogador;
import com.faculdade.jogador.JogadorSortudo;
import java.util.List;
import java.util.logging.Level;

public class CasaAzar extends Casa {

    private static final int PASSOS_PERDIDOS = -3;

    public CasaAzar(int numero) {
        super(numero);
    }

    @Override
    public void aplicarRegra(Jogador jogador, List<Jogador> todosJogadores, Jogo jogo) {

        if (jogador instanceof JogadorSortudo) {
            logger.log(Level.INFO,
                "\n{0}{1}{2} é Sortudo e ignora o efeito da Casa Azar ({3}).",
                new Object[]{jogador.getCor(), jogador.getNome(), Main.ANSI_RESET, numero});
            return;
        }

        logger.log(Level.INFO,
            "\n{0}{1}{2} caiu na Casa Azar ({3}) e voltará 3 casas!",
            new Object[]{jogador.getCor(), jogador.getNome(), Main.ANSI_RESET, numero});

        jogo.getTabuleiro().moverJogador(jogador, PASSOS_PERDIDOS, todosJogadores, jogo);
    }
}
