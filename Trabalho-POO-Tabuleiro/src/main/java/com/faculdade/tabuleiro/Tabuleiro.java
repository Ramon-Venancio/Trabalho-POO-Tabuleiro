package com.faculdade.tabuleiro;

import com.faculdade.controle.*;
import com.faculdade.jogador.Jogador;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tabuleiro {
    
    private static final Logger logger = Logger.getLogger(Tabuleiro.class.getName());

    // NOVO (SINGLETON): Atributo estático para guardar a única instância.
    private static Tabuleiro instancia;
    
    // Antigo TOTAL_CASAS removido: O tabuleiro é agora configurável.
    private int numTotalCasas; 
    
    // A lista de casas agora será preenchida dinamicamente
    private final List<Casa> casas = new ArrayList<>();

    // NOVO (SINGLETON): Construtor privado para impedir instanciacao externa.
    private Tabuleiro() {
        // Inicializações leves
    }
    
    // NOVO (SINGLETON): Método estático de acesso global.
    public static Tabuleiro getInstance() {
        if (instancia == null) {
            instancia = new Tabuleiro();
        }
        return instancia;
    }

    // NOVO (FACADE): Método para configurar o tamanho do tabuleiro e criar as casas
    public void configurarCasas(int totalCasas) {
    this.numTotalCasas = totalCasas;
    this.casas.clear(); 
    
    // Mapeamento das casas (usando strings que serão lidas pela CasaFactory)
    for (int i = 1; i <= totalCasas; i++) {
        String tipoCasa;
        
        if (i == 10 || i == 25 || i == 38) {
            tipoCasa = "Prisao"; 
        } else if (i == 13) {
            tipoCasa = "Surpresa";
        } else if (i == 5 || i == 15 || i == 30) {
            tipoCasa = "Sorte";
        } else if (i == 17 || i == 27 || i == 20 || i == 35) { 
            // Substitui as regras complexas por Casa Azar
            tipoCasa = "Azar"; 
        } else if (i == 8 || i == 22) {
            tipoCasa = "Reversa";
        }
        else if (i == 12 || i == 28) {
            tipoCasa = "JogaDeNovo";
        }else {
            tipoCasa = "Simples";
        }
        
        // Usa a Factory para criar a estratégia correta (Passamos a string)
        this.casas.add(CasaFactory.criarCasa(tipoCasa, i)); 
    }
}
    
    // O antigo método 'marcarCasas' foi removido.
    
    public Casa getCasa(int posicao) {
        
        if (posicao < 1) posicao = 1;
        // Usa o novo atributo numTotalCasas
        if (posicao > numTotalCasas) posicao = numTotalCasas;

        // O índice da lista é sempre a posição - 1.
        return casas.get(posicao - 1);
    }
    
    public int getNumTotalCasas() {
        return numTotalCasas;
    }

    // O método moverJogador é mantido, mas precisa ser revisado na Etapa 2/3 para usar 
    // Casa.aplicarRegra() (Strategy) em vez do TipoCasa/aplicarEfeito.
    public void moverJogador(Jogador jogador, int passos, List<Jogador> todosJogadores, Jogo jogo) {
        int novaPosicao;
        
        if (jogo instanceof JogoNormal) {
            novaPosicao = jogador.getPosicao() + passos;
        } else {
            // Em JogoDebug, 'passos' na verdade é a posição alvo (o -1 aqui é uma 
            // correção do código original para a posição).
            novaPosicao = passos;
        }
        
        // Verifica se o jogador venceu
        if (novaPosicao >= numTotalCasas) {
            jogador.setPosicao(numTotalCasas);
            jogo.setVencedor(jogador);
            // Mensagem de vitória
            logger.log(Level.INFO, "\n{0}{1}{2} ANDOU {3} CASAS E VENCEU O JOGO!", 
                       new Object[]{jogador.getCor(), jogador.getNome(), Main.ANSI_RESET, passos});
            return; 
        }
        
        jogador.setPosicao(novaPosicao);
        
        Casa casaAtual = getCasa(novaPosicao);
        
        System.out.printf(
            "\n%s%s%s caiu na Casa %s (posição %d).%n",
            jogador.getCor(),
            jogador.getNome(),
            Main.ANSI_RESET,
            casaAtual.toString(),
            novaPosicao
        );
        
        casaAtual.aplicarRegra(jogador, todosJogadores, jogo);
    }
}