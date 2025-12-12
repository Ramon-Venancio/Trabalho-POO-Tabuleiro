package com.faculdade.tabuleiro;

import com.faculdade.controle.Jogo;
import com.faculdade.controle.Main;
import com.faculdade.jogador.*;
import com.faculdade.jogador.JogadorFactory; // Importa a Factory
import java.util.List;
import java.util.logging.Level;

public class CasaSurpresa extends Casa {

    public CasaSurpresa(int numero) {
        super(numero);
    }

    @Override
    public void aplicarRegra(Jogador jogador, List<Jogador> todosJogadores, Jogo jogo) {
        // Lógica para sortear um novo tipo
        String tipoSorteado = jogo.getBaralho().sortearTipo();
        
        // Padrão FACTORY: Usa a Factory para criar a nova instância
        Jogador novo = JogadorFactory.criarJogador(
            tipoSorteado,
            jogador.getIdJogador(), 
            jogador.getCor(), 
            jogador.getNome()
        );

        // Transferência de atributos (mantendo as estatísticas e moedas)
        novo.setPosicao(jogador.getPosicao());
        novo.setJogadas(jogador.getJogadas());
        novo.setPerdeRodada(jogador.getPerdeRodada());
        novo.setMoedas(jogador.getMoedas()); // Transfere as moedas

        // Substitui o jogador na lista principal do jogo
        jogo.substituirJogador(jogador, novo);

        logger.log(Level.INFO, "\n O jogador {0}{1}{2} tirou uma carta na Casa Surpresa ({3}) e agora é do tipo: {4}!",
                new Object[]{jogador.getCor(), jogador.getNome(), Main.ANSI_RESET, this.numero, tipoSorteado});
    }
}