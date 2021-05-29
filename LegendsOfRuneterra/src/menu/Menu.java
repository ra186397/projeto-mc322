package menu;

import java.lang.System;
import java.util.Scanner;

public class Menu {

    private static Menu menu;

    private Menu() {

    }

    public static Menu getMenu() {
        if (menu == null) {
            menu = new Menu();
        }
        return menu;
    }

    public void abrirMenu() {

        int opcao;
        Scanner sc = new Scanner(System.in);
        System.out.println("Bem vindo a Legends Of Runeterra!");
        System.out.println("Para começar a jogar, você deve escolher o seu deck.");
        System.out.println("Você possui os seguintes decks: Demacia"); // alterar
        System.out.println("Digite o número do deck que você deseja usar, ou digite 0 para criar um novo deck.");

        Game game = Game.getGame();
    }
}
