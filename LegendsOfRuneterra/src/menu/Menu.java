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


import java.util.ArrayList;
import java.util.Random;

public class Menu {

    private static Menu menu;
    private ArrayList<Deck> decks;
    private ArrayList<Card> demacia_units;
    private ArrayList<Card> demacia_spells;
    private ArrayList<Card> freljord_units;
    private ArrayList<Card> freljord_spells;
    private ArrayList<Card> noxus_units;
    private ArrayList<Card> noxus_spells;
    private Player p1;
    private Player p2;

    private Menu() {
        this.demacia_units = new ArrayList<Card>();
        this.demacia_spells = new ArrayList<Card>();
        this.freljord_units = new ArrayList<Card>();
        this.freljord_spells = new ArrayList<Card>();
        this.noxus_units = new ArrayList<Card>();
        this.freljord_spells = new ArrayList<Card>();
        createCards();
        this.decks = buildBaseDecks();
        this.p1 = null;
        this.p2 = null;
    }

    public static Menu getMenu() {
        if (menu == null) {
            menu = new Menu();
        }
        return menu;
    }

    private Card getCardByName(String name, ArrayList<Card> lista) {
        for (Card card : lista) {
            if (card.getName() == name) {
                return card;
            }
        }
        return null;
    }

    public void selectPlayers(TypePlayer type) {
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

    }

    private Deck getRandomDeck() {
        Random r = new Random();
        int i = r.nextInt(decks.size());
        return decks.get(i);
    }

    public Player getPlayer1() {
        return this.p1;
    }

    public Player getPlayer2() {
        return this.p2;
    }

    public ArrayList<Deck> getDecks() {
        return this.decks;
    }

    private ArrayList<Deck> buildBaseDecks() {

        ArrayList<Deck> decks = new ArrayList<Deck>();

        Deck demacia = new Deck(Region.DEMACIA, Region.DEMACIA, "Demacia");
        demacia.addCard(getCardByName("Garen", demacia_units));
        demacia.addCard(getCardByName("Tiana", demacia_units));
        demacia.addCard(getCardByName("Vanguarda", demacia_units));
        demacia.addCard(getCardByName("Duelista", demacia_units));
        demacia.addCard(getCardByName("Defensor", demacia_units));
        demacia.addCard(getCardByName("Poro", demacia_units));
        demacia.addCard(getCardByName("Poro Defensor", demacia_units));
        demacia.addCard(getCardByName("Julgamento", demacia_spells));
        demacia.addCard(getCardByName("Valor Redobrado", demacia_spells));
        demacia.addCard(getCardByName("Golpe Certeiro", demacia_spells));
        demacia.addCard(getCardByName("Combate um-a-um", demacia_spells));

        Deck freljord = new Deck(Region.FRELJORD, Region.FRELJORD, "Freljord");
        freljord.addCard(getCardByName("Arqueiro do Vale", freljord_units));
        freljord.addCard(getCardByName("Elnuk", freljord_units));
        freljord.addCard(getCardByName("Taverneiro Gentil", freljord_units));
        freljord.addCard(getCardByName("Xama do Gelo", freljord_units));
        freljord.addCard(getCardByName("Lobo Feroz", freljord_units));
        freljord.addCard(getCardByName("Tryndamere", freljord_units));
        freljord.addCard(getCardByName("Vigia", freljord_units));
        freljord.addCard(getCardByName("Avalanche", freljord_spells));
        freljord.addCard(getCardByName("Congelar", freljord_spells));
        freljord.addCard(getCardByName("Elixir de Ferro", freljord_spells));
        freljord.addCard(getCardByName("Furia do Norte", freljord_spells));

        decks.add(demacia);
        decks.add(freljord);

        return decks;

    }

    private void createCards() { // Método que instancia todas as cartas implementadas.

        Garen garen = new Garen();
        demacia_units.add(garen);

        Tryndamere tryndamere = new Tryndamere();
        freljord_units.add(tryndamere);


        Effect[] effectTiana = { new Effect(6, Trigger.PLAY) };
        Follower tiana = new Follower("Tiana", "Ao ser comprada: uma unidade evocada golpeia o nexus do adversário", 8,
                7, 7, Region.DEMACIA, effectTiana, "/assets/demacia/tiana.png");
        demacia_units.add(tiana);


        Effect[] effectVanguarda = { new Effect(0, 1, 1, Trigger.PLAY) };
        Follower vanguarda = new Follower("Vanguarda", "Dê +1/+1 a todos os seguidores aliados", 4, 3, 3,
                Region.DEMACIA, effectVanguarda, "/assets/demacia/vanguarda.png");
        demacia_units.add(vanguarda);


        Effect[] effectDuelista = { new Effect(2, "Poro", Trigger.DESTROY_OPPONENT) };
        Follower duelista = new Follower("Duelista",
                "Se a carta destruir um seguidor do inimigo nesta rodada, uma carta \"Poro\" é colocada em sua mão.", 3,
                3, 2, Region.DEMACIA, effectDuelista, "/assets/demacia/duelista.png");
        demacia_units.add(duelista);


        Trait[] traitDefensor = { Trait.FURY };
        Follower defensor = new Follower("Defensor", "", 2, 2, 2, Region.DEMACIA, traitDefensor, 0, 1, "/assets/demacia/defensor.png");
        demacia_units.add(defensor);


        Follower poro = new Follower("Poro", "", 1, 2, 1, Region.DEMACIA, "/assets/demacia/poro.png");
        demacia_units.add(poro);


        Effect[] effectPoroDefensor = { new Effect(8, Trigger.LAST_BREATH) };
        Follower poroDefensor = new Follower("Poro Defensor", "Ao ser destruído, você ganha uma carta.", 1, 1, 2,
                Region.DEMACIA, effectPoroDefensor, "/assets/demacia/poro-defensor.png");
        demacia_units.add(poroDefensor);


        Effect[] effectBarreiraPrismatica = { new Effect(10, Trigger.PLAY)};
        Spell barreiraPrismatica = new Spell("Barreira Prismatica", "", 3, effectBarreiraPrismatica, Region.DEMACIA, "/assets/demacia/barreira-prismatica.png");
        demacia_spells.add(barreiraPrismatica);


        Effect[] effectPorDemacia = { new Effect(17, 3, 3, Trigger.PLAY)};
        Spell porDemacia = new Spell("Por Demacia", "", 6, effectPorDemacia, Region.DEMACIA, "/assets/demacia/por-demacia.png");
        demacia_spells.add(porDemacia);


        Effect[] effectJulgamento = { new Effect(7, Trigger.PLAY) };
        Spell julgamento = new Spell("Julgamento", "Um aliado atacante golpeia todos os oponentes defensores", 8,
                effectJulgamento, Region.DEMACIA, "/assets/demacia/julgamento.png");
        demacia_spells.add(julgamento);


        Effect[] effectValorRedobrado = { new Effect(3, Trigger.PLAY), new Effect(4, Trigger.PLAY) };
        Spell valorRedobrado = new Spell("Valor Redobrado",
                "Cure inteiramente um aliado;\nDobre o ataque e defesa deste aliado", 6, effectValorRedobrado,
                Region.DEMACIA, "/assets/demacia/valor-redobrado.png");
        demacia_spells.add(valorRedobrado);


        Effect[] effectGolpeCerteiro = { new Effect(1, 1, 1, Trigger.PLAY) };
        Spell golpeCerteiro = new Spell("Golpe Certeiro", "Dê +1/+1 a um aliado nesta rodada", 1, effectGolpeCerteiro,
                Region.DEMACIA, "/assets/demacia/golpe-certeiro.png");
        demacia_spells.add(golpeCerteiro);


        Effect[] effectCombateUmAUm = { new Effect(5, Trigger.PLAY) };
        Spell combateUmAUm = new Spell("Combate um-a-um", "Escolha um aliado e um oponente para um combate imediato", 2,
                effectCombateUmAUm, Region.DEMACIA, "/assets/demacia/combate-um-a-um.png");
        demacia_spells.add(combateUmAUm);


        Effect[] effectXamaDoGelo = {new Effect(14, Trigger.ROUND_START)};
        Follower xamaDoGelo = new Follower("Xama do Gelo", "", 5, 3, 3, Region.FRELJORD, effectXamaDoGelo, "/assets/freljord/xama-do-gelo.png");
        freljord_units.add(xamaDoGelo);


        Effect[] effectArqueiroDoVale = {new Effect(9, Trigger.PLAY)};
        Follower arqueiroDoVale = new Follower("Arqueiro do Vale", "", 2, 3, 1, Region.FRELJORD, effectArqueiroDoVale, "/assets/freljord/arqueiro-do-vale.png");
        freljord_units.add(arqueiroDoVale);


        Effect[] effectLoboFeroz = {new Effect(16, Trigger.STRIKE)};
        Follower loboFeroz = new Follower("Lobo Feroz", "", 2, 3, 2, Region.FRELJORD, effectLoboFeroz, "/assets/freljord/lobo-feroz.png");
        freljord_units.add(loboFeroz);


        Effect[] effectVigia = {new Effect(8, Trigger.LAST_BREATH)};
        Follower vigia = new Follower("Vigia", "", 2, 2, 3, Region.FRELJORD, effectVigia, "/assets/freljord/vigia.png");
        freljord_units.add(vigia);


        Effect[] effectTaverneiroGentil = {new Effect(3, Trigger.PLAY)};
        Follower taverneiroGentil = new Follower("Taverneiro Gentil", "", 3, 3, 3, Region.FRELJORD, effectTaverneiroGentil, "/assets/freljord/taverneiro-gentil.png");
        freljord_units.add(taverneiroGentil);


        Follower elnuk = new Follower("Elnuk", "", 4, 4, 5, Region.FRELJORD, "/assets/freljord/elnuk.png");
        freljord_units.add(elnuk);


        Effect[] effectElixirDeFerro = {new Effect(1, 0, 2, Trigger.PLAY)};
        Spell elixirDeFerro = new Spell("Elixir de Ferro", "", 0, effectElixirDeFerro, Region.FRELJORD, "/assets/freljord/elixir-de-ferro.png");
        freljord_spells.add(elixirDeFerro);

        Effect[] effectFuriaDoNorte = {new Effect(1, 3, 4, Trigger.PLAY)};
        Spell furiaDoNorte = new Spell("Furia do Norte", "", 3, effectFuriaDoNorte, Region.FRELJORD, "/assets/freljord/furia-do-norte.png");
        freljord_spells.add(furiaDoNorte);


        Effect[] effectCongelar = { new Effect(9, Trigger.PLAY)};
        Spell congelar = new Spell("Congelar", "", 1, effectCongelar, Region.FRELJORD, "/assets/freljord/congelar.png");
        freljord_spells.add(congelar);


        Effect[] effectAvalanche = {new Effect(15, 2, Trigger.PLAY)};
        Spell avalanche = new Spell("Avalanche", "", 3, effectAvalanche, Region.FRELJORD, "/assets/freljord/avalanche.png");
        freljord_spells.add(avalanche);

    }

    public ArrayList<Card> getUnitList(Region region){
        switch(region) {
            case DEMACIA:
                return demacia_units;
            case FRELJORD:
                return freljord_units;
            case NOXUS:
                return noxus_units;
            default:
            return null;
        }
    }

    public ArrayList<Card> getSpellList(Region region){
        switch(region) {
            case DEMACIA:
                return demacia_spells;
            case FRELJORD:
                return freljord_spells;
            case NOXUS:
                return noxus_spells;
            default:
            return null;
        }
    }

}
