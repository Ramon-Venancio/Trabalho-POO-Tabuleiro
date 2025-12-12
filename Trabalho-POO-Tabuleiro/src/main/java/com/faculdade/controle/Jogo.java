package com.faculdade.controle;

import com.faculdade.componentes.Baralho;
import com.faculdade.jogador.JogadorFactory; // NOVO: Importa a Factory
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.faculdade.jogador.*;
import com.faculdade.tabuleiro.Tabuleiro;

public abstract class Jogo {

    protected List<Jogador> jogadores;
    protected Tabuleiro tabuleiro;
    protected Baralho baralho = new Baralho(); // Baralho pode ser inicializado aqui
    protected Jogador vencedor = null;

    /*
     * Substitui um jogador existente na lista interna por uma nova instância.
     */
    public void substituirJogador(Jogador jogadorAntigo, Jogador jogadorNovo) {
        if (jogadores.contains(jogadorAntigo)) {
            int index = jogadores.indexOf(jogadorAntigo);
            jogadores.set(index, jogadorNovo);
        }
    }

    // NOVO (FACADE): Método 1 de Configuração (Tabuleiro)
    public void configTabuleiro(int numCasas) {
        // Padrão Singleton: Obtém a única instância do tabuleiro.
        tabuleiro = Tabuleiro.getInstance();

        // Configura o tabuleiro com o número de casas.
        tabuleiro.configurarCasas(numCasas);
    }

    // NOVO (FACADE): Método 2 de Configuração (Jogadores)
    public void config(int quantidade) {

        this.jogadores = new ArrayList<>();

        String[][] nomesCoresJogadores = {
            {"Azul", Main.ANSI_AZUL}, {"Verde", Main.ANSI_VERDE}, {"Vermelho", Main.ANSI_VERMELHO},
            {"Amarelo", Main.ANSI_AMARELO}, {"Roxo", Main.ANSI_ROXO}, {"Ciano", Main.ANSI_CIANO}
        };

        String tipoPrimeiro = "";

        for (int i = 0; i < quantidade; i++) {
            System.out.printf("Informe o nome do jogador %d (Cor: %s%s%s): ", i + 1, nomesCoresJogadores[i][1], nomesCoresJogadores[i][0], Main.ANSI_RESET);

            String nome = Main.scanner.nextLine().trim();

            if (nome.isEmpty()) {
                nome = nomesCoresJogadores[i][0];
            }


            String tipoAleatorio = baralho.sortearTipo();

            if (i == 0) {
                tipoPrimeiro = tipoAleatorio;
            }

            if (i == 1) {
                do {
                    tipoAleatorio = baralho.sortearTipo();
                } while (tipoAleatorio.equals(tipoPrimeiro));
            }

            // Padrão FACTORY: Chama a Factory para criar o jogador, limpando o Jogo.java
            Jogador jogador = JogadorFactory.criarJogador(
                tipoAleatorio,
                i,
                nomesCoresJogadores[i][1], // Cor
                nome                       // Nome
            );

            jogadores.add(jogador);
        }
    }

    public abstract void terminarJogo();

    // NOVOS MÉTODOS ABSTRATOS (PARTE DO FACADE)
    public abstract void printTabuleiro();
    public abstract void start();

    public Baralho getBaralho() { return baralho; }
    public Tabuleiro getTabuleiro() { return tabuleiro; }
    public List<Jogador> getJogadores() { return Collections.unmodifiableList(jogadores); }

    public Jogador getVencedor() {
        return vencedor;
    }

    public void setVencedor(Jogador vencedor) {
        this.vencedor = vencedor;
    }
}