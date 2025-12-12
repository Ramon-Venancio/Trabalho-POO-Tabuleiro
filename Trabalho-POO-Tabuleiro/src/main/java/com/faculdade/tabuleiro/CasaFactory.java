package com.faculdade.tabuleiro;

// O enum TipoCasa não será mais importado aqui!

public class CasaFactory {

    /**
     * Cria uma casa do tipo Strategy correta.
     * @param tipo O nome do tipo de casa (usando as constantes do seu antigo TipoCasa como Strings).
     * @param numero O número da casa no tabuleiro.
     * @return A instância de Casa (Strategy) correta.
     */
    
    public static Casa criarCasa(String tipo, int numero) {
        // Usa o nome do tipo (agora uma String) para decidir qual Strategy criar
        return switch (tipo.toUpperCase()) {
            case "PRISAO" -> new CasaPrisao(numero);
            case "SURPRESA" -> new CasaSurpresa(numero);
            case "SORTE" -> new CasaSorte(numero);
            case "AZAR" -> new CasaAzar(numero);
            case "REVERSA" -> new CasaReversa(numero);
            case "JOGADENOVO" -> new CasaJogaDeNovo(numero);
            default -> new CasaSimples(numero);
        };

    }
}