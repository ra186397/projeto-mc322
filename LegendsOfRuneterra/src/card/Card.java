package card;

import java.util.ArrayList;


public abstract class Card {
    
    protected String name;
    protected String description;
    protected int cost;
    protected Region regiao;
    protected ArrayList<Effect> efeitos;

    public Card(String name, String description, int cost, Region regiao, Effect[] efeitosNovos) {
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.regiao = regiao;
        this.efeitos = new ArrayList<Effect>();
        for (int i = 0; i < efeitosNovos.length; i++) {
            this.efeitos.add(efeitosNovos[i]);
        }
    }


}