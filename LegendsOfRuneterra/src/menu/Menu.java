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
import card.champion.demacia.*;
import card.champion.freljord.*;
import card.champion.noxus.*;
import game.Deck;
import game.Game;
import game.Player;

import java.util.ArrayList;

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

    private Card getCard(String name) {
        for (Card card : cards) {
            if (card.getName() == name) {
                return card;
            }
        }
        return null;
    }

    public void openMenu() {

        int option;
        Player p1 = null;
        Player p2 = null;
        boolean hasDeck = false;
        Deck chosenDeck;
        Scanner sc = new Scanner(System.in);
        System.out.println("Bem vindo a Legends Of Runeterra!");
        System.out.println("Para começar a jogar, você deve escolher o seu deck.");
        System.out.println("Você possui os seguintes decks: Demacia"); //alterar

        while (!hasDeck) {

            System.out.println("Digite o índice do deck que você deseja usar, ou digite -1 para criar um novo deck.");
            option = sc.nextInt();
            if (option == -1) {
                System.out.println("Depois nois cria o deck, só escolhe o de demacia ai"); //alterar
            }
            else if(option >= 0 && option < decks.size()) {
                System.out.println("Você escolheu o deck " + decks.get(option).getName() + "!");
                p1 = new Player(decks.get(option));
                hasDeck = true;
            }
        }
        // Fazer o jogador escolher com quem ele quer jogar.
        p2 = new Player(decks.get(0));

        
    
        Game game = Game.getGame(p1, p2);
        game.startGame();
    }

    private Deck buildBaseDeck() {

        Deck baseDeck = new Deck(Region.DEMACIA, "Demacia");
        baseDeck.addCard(getCard("Garen"));
        baseDeck.addCard(getCard("Tiana"));
        baseDeck.addCard(getCard("Vanguarda"));
        baseDeck.addCard(getCard("Duelista"));
        baseDeck.addCard(getCard("Defensor"));
        baseDeck.addCard(getCard("Poro"));
        baseDeck.addCard(getCard("Poro Defensor"));
        baseDeck.addCard(getCard("Julgamento"));
        baseDeck.addCard(getCard("Valor Redobrado"));
        baseDeck.addCard(getCard("Golpe Certeiro"));
        baseDeck.addCard(getCard("Combate um-a-um"));

        return baseDeck;


    }

    private void createCards() { //Método que instancia todas as cartas implementadas.
        // Nota: nas especificações está falando que o Garen deve atacar duas vezes ao invés de golpear duas vezes. Mudamos para golpear assumindo que o professor confundiu golpear com atacar.
        Garen garen = new Garen();
        cards.add(garen);

        Fiora fiora = new Fiora();
        cards.add(fiora);

        Effect[] effectTiana = {new Effect(6)};
        Follower tiana = new Follower("Tiana", "Ao ser comprada: uma unidade evocada golpeia o nexus do adversário", 8, 7, 7, Region.DEMACIA, effectTiana);
        cards.add(tiana);

        Effect[] effectVanguarda = {new Effect(0, 1, 1)};
        Follower vanguarda = new Follower("Vanguarda", "Dê +1/+1 a todos os seguidores aliados", 4, 3, 3, Region.DEMACIA, effectVanguarda);
        cards.add(vanguarda);

        Effect[] effectDuelista = {new Effect(2, "Poro")};
        Follower duelista = new Follower("Duelista", "Se a carta destruir um seguidor do inimigo nesta rodada, uma carta \"Poro\" é colocada em sua mão.", 3, 3, 2, Region.DEMACIA, effectDuelista);
        cards.add(duelista);

        Trait[] traitDefensor = {Trait.FURY};
        Follower defensor = new Follower("Defensor", "", 2, 2, 2, Region.DEMACIA, traitDefensor, 0, 1);
        cards.add(defensor);

        Follower poro = new Follower("Poro","", 1, 2, 1, Region.DEMACIA);
        cards.add(poro)
        ;
        Effect[] effectPoroDefensor = {new Effect(8)};
        Follower poroDefensor = new Follower("Poro Defensor", "Ao ser destruído, você ganha uma carta.", 1, 1, 2, Region.DEMACIA, effectPoroDefensor);
        cards.add(poroDefensor);

        Effect[] effectJulgamento = {new Effect(7)};
        Spell julgamento = new Spell("Julgamento", "Um aliado atacante golpeia todos os oponentes defensores", 8, effectJulgamento, Region.DEMACIA);
        cards.add(julgamento);

        Effect[] effectValorRedobrado = {new Effect(3), new Effect(4)};
        Spell valorRedobrado = new Spell("Valor Redobrado", "Cure inteiramente um aliado;\nDobre o ataque e defesa deste aliado", 6, effectValorRedobrado, Region.DEMACIA);
        cards.add(valorRedobrado);

        Effect[] effectGolpeCerteiro = {new Effect(1, 1, 1)};
        Spell golpeCerteiro = new Spell("Golpe Certeiro", "Dê +1/+1 a um aliado nesta rodada", 1, effectGolpeCerteiro, Region.DEMACIA);
        cards.add(golpeCerteiro);

        Effect[] effectCombateUmAUm = {new Effect(5)};
        Spell combateUmAUm = new Spell("Combate um-a-um", "Escolha um aliado e um oponente para um combate imediato", 2, effectCombateUmAUm, Region.DEMACIA);
        cards.add(combateUmAUm);

    }

}
