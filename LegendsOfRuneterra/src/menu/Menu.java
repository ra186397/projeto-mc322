package menu;

import java.lang.System;
import java.util.Scanner;

import card.Card;
import card.Region;
import card.champion.demacia.Garen;
import game.Deck;

import java.util.ArrayList;

public class Menu {

    private static Menu menu;
    private static ArrayList<Deck> decks;
    private static ArrayList<Card> cards;

    private Menu() {
        this.decks = new ArrayList<Deck>();
        Deck demacia = buildBaseDeck();
    }

    public static Menu getMenu() {
        if (menu == null) {
            menu = new Menu();
        }
        return menu;
    }

    public void openMenu() {

        int option;
        boolean hasDeck = false;
        Deck chosenDeck;
        Scanner sc = new Scanner(System.in);
        System.out.println("Bem vindo a Legends Of Runeterra!");
        System.out.println("Para começar a jogar, você deve escolher o seu deck.");
        System.out.println("Você possui os seguintes decks: Demacia"); // alterar
        System.out.println("Digite o número do deck que você deseja usar, ou digite 0 para criar um novo deck.");
        option = sc.nextInt();
        if (option == 0) {
            System.out.println("Depois nois cria o deck, só escolhe o de demacia ai"); //alterar
        }
        else if(option >= 1 && option < decks.size()) {
            System.out.print("Você escolheu o deck " + decks.get(option) + "!");
        }

        
    
        Game game = Game.getGame();
    }

    private Deck buildBaseDeck() {

        Deck baseDeck = new Deck(Region.DEMACIA);


    }

    private void createCards() {
        // Nota: nas especificações está falando que o Garen deve atacar duas vezes ao invés de golpear duas vezes. Mudamos para golpear assumindo que o professor confundiu golpear com atacar.
        Garen garen = new Garen("Garen", "Eu me curo totalmente no final de cada rodada.\nSubo de nível se golpear duas vezes.", 5, 5, 5, Region.DEMACIA, newEffects, traits)
    }
}
