package com.faculdade.tabuleiro;

import com.faculdade.jogador.Jogador;
import com.faculdade.jogador.JogadorAzarado;
import com.faculdade.jogador.JogadorNormal;
import com.faculdade.jogador.JogadorSortudo;
import java.util.List;

/*
 * Representa o tabuleiro completo do jogo (40 casas).
 * É responsável por inicializar as casas, definir as especiais e aplicar efeitos.
 */

public class Tabuleiro {

    private final Casa[] casas; //Vetor com 40 casas
    public static final int Total_Casas = 40;  //Total de casas
 
    /*
        Construtor: cria e inicializa todas as casas do tabuleiro
    */
    
    public Tabuleiro(){
        casas = new Casa[Total_Casas];
        inicializarCasas();
    }
    
    /*
        Cria as casas e define quais são as especiais
    */
 
    private void inicializarCasas(){
        //Criar casas
        for(int i = 0; i < Total_Casas; i++){
            casas[i] = new Casa(i+1);
        }
        
        //Marcar casas especificas
        marcarCasas(TipoCasa.PERDE_A_VEZ, 10, 25, 38);
        marcarCasas(TipoCasa.SURPRESA, 13);
        marcarCasas(TipoCasa.SORTE, 5, 15, 30);
        marcarCasas(TipoCasa.VOLTA_AO_INICIO, 17, 27);
        marcarCasas(TipoCasa.MAGICA, 20, 35);    
    }
    
    //Marca varias casas de um mesmo tipo de uma vez
    
   private void marcarCasas(TipoCasa tipo, int... numeros) {
        for (int n : numeros) {
            if (n >= 1 && n <= Total_Casas) {  // verifica se a casa existe
                casas[n].setTipo(tipo);    // define o tipo dessa casa
            }
        }
    }
   
    //Retorna a casa da posição informada 
   
        public Casa getCasa(int posicao) {
              if (posicao < 1) return casas[0];
              if (posicao > Total_Casas) return casas[Total_Casas - 1];
        return casas[posicao - 1];
    }

    //Move o jogador sem sair do limite de casas
       
        public int moverJogador(int posicaoAtual, int passos){
           int novaPosicao = posicaoAtual + passos;
           if(novaPosicao > Total_Casas){
              novaPosicao = Total_Casas;
           }
           return novaPosicao;
       }
       
       //Retorna o tipo da casa de uma posição específica.
       
        public TipoCasa getTipoCasa(int posicao) {
            return getCasa(posicao).getTipo();
        }
      
        //Aplica o efeito da casa onde o jogador caiu.
        
        public void aplicarEfeito(Jogador jogador, List<Jogador> todosJogadores){
            Casa casaAtual = getCasa(jogador.getPosicao());
            TipoCasa tipo = casaAtual.getTipo();
            
            System.out.printf("%s caiu na casa %d (%s)", jogador.getNome(), casaAtual.getNumero(), tipo);
            
            switch(tipo){
                case PERDE_A_VEZ:
                    jogador.setPerdeRodada(true);
                    System.out.print("O jogador vai perder a próxima rodada!");
                    break;
                    
                case SURPRESA:
                    int tipoSorteado = new java.util.Random().nextInt(3);
                    Jogador novoJogador;

                switch (tipoSorteado) {
                    case 0:
                        novoJogador = new JogadorNormal(jogador.getidJogador(), jogador.getCor(), jogador.getNome()) ;
                        break;
                    case 1:
                        novoJogador = new JogadorSortudo(jogador.getidJogador(), jogador.getCor(), jogador.getNome()) ;
                        break;
                    default:
                        novoJogador = new JogadorAzarado(jogador.getidJogador(), jogador.getCor(), jogador.getNome()) ;
                        break;
                }

                    novoJogador.setPosicao(jogador.getPosicao()); //Mantém os dados da posição
                    novoJogador.setJogadas(jogador.getJogadas()); //Mantém o histórico de jogadas
                    novoJogador.setPerdeRodada(jogador.isPerdeRodada()); //Recebe as penalidades
                    todosJogadores.set(todosJogadores.indexOf(jogador), novoJogador); //Substitui o indice do jogador antigo pelo novo
                    
                break;


            case SORTE:
                int novaPosicao = moverJogador(jogador.getPosicao(), 3);
                jogador.setPosicao(novaPosicao);
                System.out.println("SORTE! Anda 3 casas à frente.");
                break;

            case VOLTA_AO_INICIO:
                jogador.setPosicao(1);
                System.out.println("VOLTA AO INÍCIO!");
                break;

                
                //Não entendi, perguntar ao chat
                
            case MAGICA:
                if (todosJogadores.size() > 1) {
                    Jogador outro = escolherOutroJogador(jogador, todosJogadores);
                    if (outro != null) {
                        int posTemp = jogador.getPosicao(); //
                        jogador.setPosicao(outro.getPosicao()); //
                        outro.setPosicao(posTemp); //
                        System.out.printf("TROCA MÁGICA! %s trocou de lugar com %s\n",
                                jogador.getCor(), outro.getCor()); //
                    }
                }
                break;
     
            case NORMAL:
            default:
                System.out.println("➡️ Casa normal, nada acontece.");
                break;
        }
    }
        //Escolhe outro jogador para a troca mágica
        
        private Jogador escolherOutroJogador(Jogador atual, List<Jogador> jogadores){
            for(Jogador j : jogadores){
                if(!j.equals(atual)){
                    return j;
                }
                
            }
            return null;
        }
            
        //Exibe o tabuleiro no console
        
        public void exibirTabuleiro() {
        System.out.println("\n=== TABULEIRO ===");
        for (Casa casa : casas) {
            System.out.println(casa);
        }
        System.out.println("=================\n");
    }
        
            
 }
