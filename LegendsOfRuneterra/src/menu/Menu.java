package menu;

import java.lang.System;
import java.util.Scanner;

import game.Deck;

import java.util.ArrayList;

public class Menu {

    private static Menu menu;
    private ArrayList<Deck> decks;

    private Menu() {
        this.decks = new ArrayList<Deck>();
    }

    public static Menu getMenu() {
        if (menu == null) {
            menu = new Menu();
        }
        return menu;
    }

    public void abrirMenu() {

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
            System.out.print
        }

        
    
        Game game = Game.getGame();
    }
}
