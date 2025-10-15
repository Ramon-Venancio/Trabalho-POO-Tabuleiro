package com.faculdade.tabuleiro;

    public enum TipoCasa {
        NORMAL,          // Casa comum, sem efeito especial
        PERDE_A_VEZ,     // O jogador perde a próxima rodada
        SURPRESA,        // O jogador muda seu tipo (ex: vira sortudo ou azarado)
        SORTE,           // O jogador anda 3 casas à frente
        VOLTA_AO_INICIO, // O jogador volta para a casa 1
        MAGICA           // O jogador troca de lugar com outro jogador   
    }