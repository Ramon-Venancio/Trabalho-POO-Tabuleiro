package com.faculdade.tabuleiro;

import com.faculdade.controle.Jogo;
import com.faculdade.controle.Main;
import com.faculdade.jogador.Jogador;
import com.faculdade.jogador.JogadorAzarado;
import java.util.List;
import java.util.logging.Level;

public class CasaSorte extends Casa {

    private static final int PASSOS_BONUS = 3;

    public CasaSorte(int numero) {
        super(numero);
    }

    @Override
    public void aplicarRegra(Jogador jogador, List<Jogador> todosJogadores, Jogo jogo) {
        // Regra: Só anda 3 casas se NÃO for Jogador Azarado
        if (jogador instanceof JogadorAzarado) {
            logger.log(Level.INFO, "\n{0}{1}{2} é Azarado e ignora o efeito da Casa Sorte ({3}).", 
                new Object[]{jogador.getCor(), jogador.getNome(), Main.ANSI_RESET, this.numero});
        } else {
            logger.log(Level.INFO, "\n{0}{1}{2} caiu na Casa Sorte ({3}) e andará {4} casas!", 
                new Object[]{jogador.getCor(), jogador.getNome(), Main.ANSI_RESET, this.numero, PASSOS_BONUS});

            // Chama o tabuleiro para mover o jogador novamente
            jogo.getTabuleiro().moverJogador(jogador, PASSOS_BONUS, todosJogadores, jogo);
        }
    }
}