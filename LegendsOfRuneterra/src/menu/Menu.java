package menu;

import java.lang.System;
import java.net.ConnectException;
import java.util.Scanner;

import card.Card;
import card.Effect;
import card.Follower;
import card.Region;
import card.Spell;
import card.Trait;
import card.Trigger;
import card.champion.demacia.*;
import card.champion.freljord.*;
import card.champion.noxus.*;
import game.Deck;
import game.Game;
import game.Player;
import jdk.nashorn.internal.runtime.Undefined;

import java.util.ArrayList;
import java.util.Random;

public class Menu {

    private static Menu menu;
    private ArrayList<Deck> decks;
    private ArrayList<Card> cards;

    private Menu() {
        this.cards = new ArrayList<Card>();
        createCards();
        this.decks = new ArrayList<Deck>();
        Deck demacia = buildBaseDeck();
        decks.add(demacia);
    }

    public static Menu getMenu() {
        if (menu == null) {
            menu = new Menu();
        }
        return menu;
    }

    private Card getCardByName(String name) {
        for (Card card : cards) {
            if (card.getName() == name) {
                return card;
            }
        }
        return null;
    }

    public ArrayList<Player> selectPlayers(TypePlayer type) {
        Player p1;
        Player p2;
        ArrayList<Player> players = new ArrayList<Player>();
        switch (type) {
            case PVP:

                p1 = new Player(null, true);
                p2 = new Player(null, true);
                break;
            case PVIA:
                p1 = new Player(null, true);
                p2 = new Player(getRandomDeck(), false);
                break;
            case IAVIA:
                p1 = new Player(getRandomDeck(), false);
                p2 = new Player(getRandomDeck(), false);
                break;
        }

        players.add(p1);
        players.add(p2);
        return players;

    }

    private Deck getRandomDeck() {
        Random r = new Random();
        int i = r.nextInt(decks.size());
        return decks.get(i);
    }

    /*private void deckSelection(Scanner scanner) {

        int option;
        Deck chosenDeck;
        boolean hasDeck = false;

        while (!hasDeck) {

            System.out.println("Para começar a jogar, você deve escolher o seu deck.");
            System.out.printf("Você possui os seguintes decks: ");

            for (int i = 0; i < this.decks.size(); i++) {
                System.out.printf(" %d - %s. ", i + 1, this.decks.get(i).getName());
            }

            System.out.println("\n");
            System.out.println("Digite o número do deck que você deseja usar, ou digite 0 para criar um novo deck.");
            option = scanner.nextInt();

            if (option == 0) {
                this.decks.add(this.createNewDeck(scanner));
            } else if (option >= 1 && option < decks.size()) {
                System.out.print("Você escolheu o deck " + decks.get(option).getName() + "!");
            }
        }

    }*/

    private Deck createNewDeck(Scanner scanner) {

        Region firstRegion = Region.NONE;
        Region secondRegion = Region.NONE;
        Deck newDeck;
        int option = 0;
        String name;

        System.out.println("A criação de decks possui algumas restrições para melhorar o balanceamento do jogo");
        System.out.println("1 - Um deck não pode possuir mais do que 40 cartas");
        System.out.println("2 - O deck devem possuir cartas de no máximo 2 regiões distintas");
        System.out.println("3 - Digite 0 ou qualquer outra coisa para não selecionar nenhuma região");
        System.out.println("4 - A primeira região deve ser escolhida");

        System.out.printf("\nDigite o nome do seu deck: ");
        name = scanner.next();

        while (firstRegion == Region.NONE) {

            firstRegion = this.regionSelect(scanner);

        }

        while (option != 1 && option != 2) {

            System.out.println("Você deseja uma segunda região no seu deck?");
            System.out.println("1 - Sim. 2 - Não");
            option = scanner.nextInt();

        }

        if (option == 1) {
            System.out.println("Digite a segunda região");

            secondRegion = this.regionSelect(scanner);

            newDeck = new Deck(firstRegion, secondRegion, name);
        } else {

            newDeck = new Deck(firstRegion, name);
        }

        this.cardsSelection(newDeck, scanner);

        return newDeck;

    }

    private void cardsSelection(Deck deck, Scanner scanner) {

    }

    private Region regionSelect(Scanner scanner) {

        int option = 0;

        System.out.println(
                "\nLevando em consideração o que foi dito acima, escolha a primeira região que você quer colocar no seu deck: ");
        System.out.println("     1 - Noxus.\n       2 - Demacia.\n      3 - Feljord.\n");

        option = scanner.nextInt();

        switch (option) {
            case 1:
                return Region.NOXUS;
            case 2:
                return Region.DEMACIA;
            case 3:
                return Region.FRELJORD;
            default:
                return Region.NONE;
        }

    }

    private Deck buildBaseDeck() {

        Deck baseDeck = new Deck(Region.DEMACIA, "Demacia");
        baseDeck.addCard(getCardByName("Garen"));
        baseDeck.addCard(getCardByName("Tiana"));
        baseDeck.addCard(getCardByName("Vanguarda"));
        baseDeck.addCard(getCardByName("Duelista"));
        baseDeck.addCard(getCardByName("Defensor"));
        baseDeck.addCard(getCardByName("Poro"));
        baseDeck.addCard(getCardByName("Poro Defensor"));
        baseDeck.addCard(getCardByName("Julgamento"));
        baseDeck.addCard(getCardByName("Valor Redobrado"));
        baseDeck.addCard(getCardByName("Golpe Certeiro"));
        baseDeck.addCard(getCardByName("Combate um-a-um"));

        return baseDeck;

    }

    private void createCards() { // Método que instancia todas as cartas implementadas.
        // Nota: nas especificações está falando que o Garen deve atacar duas vezes ao
        // invés de golpear duas vezes. Mudamos para golpear assumindo que o professor
        // confundiu golpear com atacar.
        Garen garen = new Garen();
        cards.add(garen);

        Fiora fiora = new Fiora();
        cards.add(fiora);

        Effect[] effectTiana = { new Effect(6, Trigger.PLAY) };
        Follower tiana = new Follower("Tiana", "Ao ser comprada: uma unidade evocada golpeia o nexus do adversário", 8,
                7, 7, Region.DEMACIA, effectTiana);
        cards.add(tiana);

        Effect[] effectVanguarda = { new Effect(0, 1, 1, Trigger.PLAY) };
        Follower vanguarda = new Follower("Vanguarda", "Dê +1/+1 a todos os seguidores aliados", 4, 3, 3,
                Region.DEMACIA, effectVanguarda);
        cards.add(vanguarda);

        Effect[] effectDuelista = { new Effect(2, "Poro", Trigger.DESTROY_OPPONENT) };
        Follower duelista = new Follower("Duelista",
                "Se a carta destruir um seguidor do inimigo nesta rodada, uma carta \"Poro\" é colocada em sua mão.", 3,
                3, 2, Region.DEMACIA, effectDuelista);
        cards.add(duelista);

        Trait[] traitDefensor = { Trait.FURY };
        Follower defensor = new Follower("Defensor", "", 2, 2, 2, Region.DEMACIA, traitDefensor, 0, 1);
        cards.add(defensor);

        Follower poro = new Follower("Poro", "", 1, 2, 1, Region.DEMACIA);
        cards.add(poro);
        Effect[] effectPoroDefensor = { new Effect(8, Trigger.LAST_BREATH) };
        Follower poroDefensor = new Follower("Poro Defensor", "Ao ser destruído, você ganha uma carta.", 1, 1, 2,
                Region.DEMACIA, effectPoroDefensor);
        cards.add(poroDefensor);

        Effect[] effectJulgamento = { new Effect(7, Trigger.PLAY) };
        Spell julgamento = new Spell("Julgamento", "Um aliado atacante golpeia todos os oponentes defensores", 8,
                effectJulgamento, Region.DEMACIA);
        cards.add(julgamento);

        Effect[] effectValorRedobrado = { new Effect(3, Trigger.PLAY), new Effect(4, Trigger.PLAY) };
        Spell valorRedobrado = new Spell("Valor Redobrado",
                "Cure inteiramente um aliado;\nDobre o ataque e defesa deste aliado", 6, effectValorRedobrado,
                Region.DEMACIA);
        cards.add(valorRedobrado);

        Effect[] effectGolpeCerteiro = { new Effect(1, 1, 1, Trigger.PLAY) };
        Spell golpeCerteiro = new Spell("Golpe Certeiro", "Dê +1/+1 a um aliado nesta rodada", 1, effectGolpeCerteiro,
                Region.DEMACIA);
        cards.add(golpeCerteiro);

        Effect[] effectCombateUmAUm = { new Effect(5, Trigger.PLAY) };
        Spell combateUmAUm = new Spell("Combate um-a-um", "Escolha um aliado e um oponente para um combate imediato", 2,
                effectCombateUmAUm, Region.DEMACIA);
        cards.add(combateUmAUm);

    }

}
