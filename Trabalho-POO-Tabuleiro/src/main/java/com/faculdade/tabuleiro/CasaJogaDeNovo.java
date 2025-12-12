package com.faculdade.tabuleiro;

import com.faculdade.controle.Jogo;
import com.faculdade.controle.Main;
import com.faculdade.jogador.Jogador;
import java.util.List;
import java.util.logging.Level;

public class CasaJogaDeNovo extends Casa {

    public CasaJogaDeNovo(int numero) {
        super(numero);
    }

    @Override
    public void aplicarRegra(Jogador jogador, List<Jogador> todosJogadores, Jogo jogo) {

        jogador.setJogaDeNovo(true);

        logger.log(Level.INFO,
            "\n{0}{1}{2} caiu na Casa JogaDeNovo ({3}) e jogará novamente!",
            new Object[]{jogador.getCor(), jogador.getNome(), Main.ANSI_RESET, numero});
    }
}
