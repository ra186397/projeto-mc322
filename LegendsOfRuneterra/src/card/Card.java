package card;

import java.util.ArrayList;


public abstract class Card {
    
    protected String name;
    protected String description;
    protected int cost;
    protected Region regiao;
    protected ArrayList efeitos; // i0 = carta ser jogada, i1 = carta morrer, i2 = carta ataca, i3 = efeito de atualização
    

    public Card(String name, String description, int cost, Region regiao, int[] efeitos) {
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.regiao = regiao;
        this.efeitos = new int[4];
        for (int i = 0; i < efeitos.length; i++) {
            this.efeitos[i] = efeitos[i];
        }
    }


}