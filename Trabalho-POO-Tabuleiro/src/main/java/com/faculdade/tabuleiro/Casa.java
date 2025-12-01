package com.faculdade.tabuleiro;

import com.faculdade.controle.Jogo;
import com.faculdade.controle.Main;
import com.faculdade.jogador.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

public class Casa {
    
    private static final Logger logger = Logger.getLogger(Casa.class.getName());

    private final int numero;
    private TipoCasa tipo;
    
    public Casa(int numero){
        this.numero = numero;
        this.tipo = TipoCasa.NORMAL;
    }

    public int getNumero(){
        return numero;
    }
    
    public TipoCasa getTipo(){
        return tipo;
    }
    
    public void setTipo(TipoCasa tipo){
        this.tipo = tipo;
    }
    

    // ============================================================
    //  APLICAR EFEITO PRINCIPAL (switch limpo)
    // ============================================================
    public void aplicarEfeito(Jogador jogador, List<Jogador> todosJogadores, Jogo jogo) {

        switch (tipo) {

            case MAGICA ->
                aplicarEfeitoMagico(jogador, todosJogadores);

            case SORTE ->
                aplicarEfeitoSorte(jogador);

            case VOLTA_AO_INICIO ->
                aplicarEfeitoVoltaAoInicio(jogador, todosJogadores);

            case PERDE_A_VEZ -> {
                jogador.setPerdeRodada(true);
                logger.info("\nCasa Pula Vez 🥶: Você não poderá jogar na próxima rodada.");
            }

            case SURPRESA -> {
                aplicarEfeitoSurpresa(jogador, jogo);
                logger.info("\nCasa Surpresa 🔁: Seu tipo foi alterado!");
            }

            default -> {
                // Casa normal — nenhum efeito
            }
        }
    }



    // ============================================================
    //  EFEITO: CASA MÁGICA
    // ============================================================
    private void aplicarEfeitoMagico(Jogador jogador, List<Jogador> todosJogadores) {

        if (todosJogadores.size() <= 1) return;

        Jogador maisAtras = null;

        for (Jogador j : todosJogadores) {
            if (j == jogador) continue;
            if (maisAtras == null || j.getPosicao() < maisAtras.getPosicao())
                maisAtras = j;
        }

        if (maisAtras == null || jogador.getPosicao() <= maisAtras.getPosicao()) {
            logger.info("\nCasa Mágica ✨: Você já é o último jogador. Nada acontece.");
            return;
        }

        int temp = jogador.getPosicao();
        jogador.setPosicao(maisAtras.getPosicao());
        maisAtras.setPosicao(temp);

        logger.info("\nCasa Mágica ✨: Você trocou de lugar com " +
                    maisAtras.getCor() + maisAtras.getNome() + Main.ANSI_RESET);
    }



    // ============================================================
    //  EFEITO: CASA DA SORTE
    // ============================================================
    private void aplicarEfeitoSorte(Jogador jogador) {

        if (!(jogador instanceof JogadorAzarado)) {

            jogador.setPosicao(
                Math.min(jogador.getPosicao() + 3, Tabuleiro.TOTAL_CASAS)
            );

            logger.info("\nCasa da Sorte 🍀: Você pulou 3 casas!");

        } else {

            logger.info("\nCasa da Sorte 🍀: Você é Jogador Azarado! Bônus cancelado.");

        }
    }



    // ============================================================
    //  EFEITO: VOLTA AO INÍCIO
    // ============================================================
    private void aplicarEfeitoVoltaAoInicio(Jogador jogador, List<Jogador> todosJogadores) {

    logger.info("\n========== CASA DO REVÊS ==========");
    logger.info("Escolha um jogador para voltar ao início:");
    logger.info("------------------------------------");

    Jogador escolhido = escolherJogadorParaReset(todosJogadores, jogador);

    if (escolhido != null) {
        escolhido.setPosicao(0);
        logger.info("Jogador " + escolhido.getNome() + " voltou ao início!");
    }

    logger.info("====================================\n");
}

    private Jogador escolherJogadorParaReset(List<Jogador> todos, Jogador atual) {

        List<Jogador> opcoes = new ArrayList<>();
        int count = 1;

        for (Jogador j : todos) {
            if (!j.equals(atual)) {

                logger.log(Level.INFO, "{0} - {1}",
                        new Object[]{count, j.getNome()});

                opcoes.add(j);
                count++;
            }
        }

        while (true) {

            if (!Main.scanner.hasNextInt()) {
                logger.warning("Entrada inválida! Digite o número do jogador.");
                Main.scanner.nextLine();
                continue;
            }

            int escolha = Main.scanner.nextInt();
            Main.scanner.nextLine();

            if (escolha > 0 && escolha <= opcoes.size()) {
                return opcoes.get(escolha - 1);
            }

            logger.warning("Opção fora do intervalo! Tente novamente.");
        }
    }



    // ============================================================
    //  EFEITO: CASA SURPRESA
    // ============================================================
    private void aplicarEfeitoSurpresa(Jogador jogador, Jogo jogo) {

        String tipoSorteado = jogo.getBaralho().sortearTipo();
        Jogador novo = null;

        switch (tipoSorteado) {
            case Jogador.TIPO_NORMAL ->
                novo = new JogadorNormal(jogador.getIdJogador(), jogador.getCor(), jogador.getNome());

            case Jogador.TIPO_AZARADO ->
                novo = new JogadorAzarado(jogador.getIdJogador(), jogador.getCor(), jogador.getNome());

            case Jogador.TIPO_SORTUDO ->
                novo = new JogadorSortudo(jogador.getIdJogador(), jogador.getCor(), jogador.getNome());
            
            default -> {/*Bloco existente para o caso de algo não de acordo com o que se espera*/}
        }

        if (novo == null) return;

        // Transferência de atributos
        novo.setPosicao(jogador.getPosicao());
        novo.setJogadas(jogador.getJogadas());
        novo.setPerdeRodada(jogador.getPerdeRodada());

        jogo.substituirJogador(jogador, novo);

        logger.log(Level.INFO,
                
        "\nO jogador {0}{1}{2} agora é do tipo {3}!",
                
        new Object[]{ jogador.getCor(), jogador.getNome(), Main.ANSI_RESET, tipoSorteado });

    }
}
