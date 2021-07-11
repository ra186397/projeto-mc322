package menu;

import card.Card;
import card.CaseEffects;
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
        this.noxus_spells = new ArrayList<Card>();
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

        Deck noxus = new Deck(Region.NOXUS, Region.NOXUS, "Noxus");
        noxus.addCard(getCardByName("Darius", noxus_units));
        noxus.addCard(getCardByName("Leblanc", noxus_units));
        noxus.addCard(getCardByName("Espia", noxus_units));
        noxus.addCard(getCardByName("Retaguarda da Legiao", noxus_units));
        noxus.addCard(getCardByName("Trifariano", noxus_units));
        noxus.addCard(getCardByName("Homem Bomba", noxus_units));
        noxus.addCard(getCardByName("Quebra Escudos", noxus_units));
        noxus.addCard(getCardByName("Comentarista da Arena", noxus_units));
        noxus.addCard(getCardByName("Capitao Farron", noxus_units));
        noxus.addCard(getCardByName("Determinacao", noxus_spells));
        noxus.addCard(getCardByName("Dizimar", noxus_spells));
        noxus.addCard(getCardByName("Fervor Noxiano", noxus_spells));
        noxus.addCard(getCardByName("Fio de Lamina", noxus_spells));
        noxus.addCard(getCardByName("Machado Duplo", noxus_spells));
        noxus.addCard(getCardByName("Visao", noxus_spells));

        decks.add(demacia);
        decks.add(freljord);
        decks.add(noxus);

        return decks;

    }

    private void createCards() { // Método que instancia todas as cartas implementadas.

        Garen garen = new Garen();
        demacia_units.add(garen);

        Lucian lucian = new Lucian();
        demacia_units.add(lucian);

        Tryndamere tryndamere = new Tryndamere();
        freljord_units.add(tryndamere);

        Anivia anivia = new Anivia();
        freljord_units.add(anivia);

        Leblanc leblanc = new Leblanc();
        noxus_units.add(leblanc);

        Darius darius = new Darius();
        noxus_units.add(darius);


        //INÍCIO SEGUIDORES DE DEMACIA


        Effect[] effectTiana = {new Effect(CaseEffects.ALLY_STRIKE_NEXUS, Trigger.PLAY) };
        Follower tiana = new Follower("Tiana", "Ao ser comprada: uma unidade evocada golpeia o nexus do adversário", 8,
                7, 7, Region.DEMACIA, effectTiana, "/assets/demacia/tiana.png");
        demacia_units.add(tiana);

        Trait[] traitsCavalariaDaVanguarda = {Trait.TOUGH};
        Follower cavalariaDaVanguarda = new Follower("Cavalaria da Vanguarda", "", 5, 5, 5, Region.DEMACIA, traitsCavalariaDaVanguarda, "/assets/demacia/cavalaria-da-vanguarda.png");
        demacia_units.add(cavalariaDaVanguarda);


        Effect[] effectVanguarda = {new Effect(CaseEffects.BOARD_BUFF, 1, 1, Trigger.PLAY) };
        Follower vanguarda = new Follower("Vanguarda", "Dê +1/+1 a todos os seguidores aliados", 4, 3, 3,
                Region.DEMACIA, effectVanguarda, "/assets/demacia/vanguarda.png");
        demacia_units.add(vanguarda);


        Effect[] effectDuelista = {new Effect(CaseEffects.CREATE_CARD, "Poro", Trigger.DESTROY_OPPONENT) };
        Follower duelista = new Follower("Duelista",
                "Se a carta destruir um seguidor do inimigo nesta rodada, uma carta \"Poro\" é colocada em sua mão.", 3,
                3, 2, Region.DEMACIA, effectDuelista, "/assets/demacia/duelista.png");
        demacia_units.add(duelista);


        Trait[] traitDefensor = { Trait.FURY };
        Follower defensor = new Follower("Defensor", "", 2, 2, 2, Region.DEMACIA, traitDefensor, 0, 1, "/assets/demacia/defensor.png");
        demacia_units.add(defensor);


        Follower poro = new Follower("Poro", "", 1, 2, 1, Region.DEMACIA, "/assets/demacia/poro.png");
        demacia_units.add(poro);


        Effect[] effectPoroDefensor = {new Effect(CaseEffects.DRAW_CARD, Trigger.LAST_BREATH) };
        Follower poroDefensor = new Follower("Poro Defensor", "Ao ser destruído, você ganha uma carta.", 1, 1, 2,
                Region.DEMACIA, effectPoroDefensor, "/assets/demacia/poro-defensor.png");
        demacia_units.add(poroDefensor);


        //INÍCIO FEITIÇOS DE DEMACIA


        Effect[] effectBarreiraPrismatica = {new Effect(CaseEffects.PRISMATIC_BARRIER, Trigger.PLAY)};
        Spell barreiraPrismatica = new Spell("Barreira Prismatica", "", 3, effectBarreiraPrismatica, Region.DEMACIA, "/assets/demacia/barreira-prismatica.png");
        demacia_spells.add(barreiraPrismatica);


        Effect[] effectPorDemacia = {new Effect(CaseEffects.AREA_TEMP_BUFF, 3, 3, Trigger.PLAY)};
        Spell porDemacia = new Spell("Por Demacia", "", 6, effectPorDemacia, Region.DEMACIA, "/assets/demacia/por-demacia.png");
        demacia_spells.add(porDemacia);


        Effect[] effectJulgamento = {new Effect(CaseEffects.JUDGEMENT, Trigger.PLAY) };
        Spell julgamento = new Spell("Julgamento", "Um aliado atacante golpeia todos os oponentes defensores", 8,
                effectJulgamento, Region.DEMACIA, "/assets/demacia/julgamento.png");
        demacia_spells.add(julgamento);


        Effect[] effectValorRedobrado = {new Effect(CaseEffects.FULL_HEAL, Trigger.PLAY), new Effect(CaseEffects.DOUBLE_STATS, Trigger.PLAY) };
        Spell valorRedobrado = new Spell("Valor Redobrado",
                "Cure inteiramente um aliado;\nDobre o ataque e defesa deste aliado", 6, effectValorRedobrado,
                Region.DEMACIA, "/assets/demacia/valor-redobrado.png");
        demacia_spells.add(valorRedobrado);


        Effect[] effectGolpeCerteiro = {new Effect(CaseEffects.SINGLE_TEMP_BUFF, 1, 1, Trigger.PLAY) };
        Spell golpeCerteiro = new Spell("Golpe Certeiro", "Dê +1/+1 a um aliado nesta rodada", 1, effectGolpeCerteiro,
                Region.DEMACIA, "/assets/demacia/golpe-certeiro.png");
        demacia_spells.add(golpeCerteiro);


        Effect[] effectCombateUmAUm = {new Effect(CaseEffects.SINGLE_COMBAT, Trigger.PLAY) };
        Spell combateUmAUm = new Spell("Combate um-a-um", "Escolha um aliado e um oponente para um combate imediato", 2,
                effectCombateUmAUm, Region.DEMACIA, "/assets/demacia/combate-um-a-um.png");
        demacia_spells.add(combateUmAUm);


        //INÍCIO SEGUIDORES DE FRELJORD


        Effect[] effectXamaDoGelo = {new Effect(CaseEffects.STRONGEST_ZERO_POWER, Trigger.ROUND_START)};
        Follower xamaDoGelo = new Follower("Xama do Gelo", "", 5, 3, 3, Region.FRELJORD, effectXamaDoGelo, "/assets/freljord/xama-do-gelo.png");
        freljord_units.add(xamaDoGelo);


        Effect[] effectArqueiroDoVale = {new Effect(CaseEffects.ZERO_POWER, Trigger.PLAY)};
        Follower arqueiroDoVale = new Follower("Arqueiro do Vale", "", 2, 3, 1, Region.FRELJORD, effectArqueiroDoVale, "/assets/freljord/arqueiro-do-vale.png");
        freljord_units.add(arqueiroDoVale);


        Effect[] effectLoboFeroz = {new Effect(CaseEffects.SELF_BUFF, Trigger.STRIKE)};
        Follower loboFeroz = new Follower("Lobo Feroz", "", 2, 3, 2, Region.FRELJORD, effectLoboFeroz, "/assets/freljord/lobo-feroz.png");
        freljord_units.add(loboFeroz);


        Effect[] effectVigia = {new Effect(CaseEffects.DRAW_CARD, Trigger.LAST_BREATH)};
        Follower vigia = new Follower("Vigia", "", 2, 2, 3, Region.FRELJORD, effectVigia, "/assets/freljord/vigia.png");
        freljord_units.add(vigia);


        Effect[] effectTaverneiroGentil = {new Effect(CaseEffects.FULL_HEAL, Trigger.PLAY)};
        Follower taverneiroGentil = new Follower("Taverneiro Gentil", "", 3, 3, 3, Region.FRELJORD, effectTaverneiroGentil, "/assets/freljord/taverneiro-gentil.png");
        freljord_units.add(taverneiroGentil);


        Follower elnuk = new Follower("Elnuk", "", 4, 4, 5, Region.FRELJORD, "/assets/freljord/elnuk.png");
        freljord_units.add(elnuk);

        Follower coelho = new Follower("Coelho", "", 1, 2, 1, Region.FRELJORD, "/assets/freljord/coelho.png");
        freljord_units.add(coelho);


        //INÍCIO FEITIÇOS DE FRELJORD


        Effect[] effectElixirDeFerro = {new Effect(CaseEffects.SINGLE_TEMP_BUFF, 0, 2, Trigger.PLAY)};
        Spell elixirDeFerro = new Spell("Elixir de Ferro", "", 0, effectElixirDeFerro, Region.FRELJORD, "/assets/freljord/elixir-de-ferro.png");
        freljord_spells.add(elixirDeFerro);


        Effect[] effectFuriaDoNorte = {new Effect(CaseEffects.SINGLE_TEMP_BUFF, 3, 4, Trigger.PLAY)};
        Spell furiaDoNorte = new Spell("Furia do Norte", "", 3, effectFuriaDoNorte, Region.FRELJORD, "/assets/freljord/furia-do-norte.png");
        freljord_spells.add(furiaDoNorte);


        Effect[] effectCongelar = {new Effect(CaseEffects.ZERO_POWER, Trigger.PLAY)};
        Spell congelar = new Spell("Congelar", "", 1, effectCongelar, Region.FRELJORD, "/assets/freljord/congelar.png");
        freljord_spells.add(congelar);


        Effect[] effectAvalanche = {new Effect(CaseEffects.FULL_AOE, 2, Trigger.PLAY)};
        Spell avalanche = new Spell("Avalanche", "", 3, effectAvalanche, Region.FRELJORD, "/assets/freljord/avalanche.png");
        freljord_spells.add(avalanche);


        Effect[] effectRepartir = {new Effect(CaseEffects.SHATTER, 4, Trigger.PLAY)};
        Spell repartir = new Spell("Repartir", "", 2, effectRepartir, Region.FRELJORD, "/assets/freljord/repartir.png");
        freljord_spells.add(repartir);


        Effect[] effectGemaDeMana = {new Effect(CaseEffects.CATALYST, 3, Trigger.PLAY)};
        Spell gemaDeMana = new Spell("Gema de Mana", "", 5, effectGemaDeMana, Region.FRELJORD, "/assets/freljord/gema-de-mana.png");
        freljord_spells.add(gemaDeMana);


        //INÍCIO SEGUIDORES DE NOXUS


        Follower retaguardaDaLegiao = new Follower("Retaguarda da Legiao", "", 1, 3, 2, Region.NOXUS, "/assets/noxus/retaguarda-da-legiao.png");
        noxus_units.add(retaguardaDaLegiao);


        Trait[] traitsEspia = {Trait.ELUSIVE};
        Follower espia = new Follower("Espia", "", 1, 1, 1, Region.NOXUS, traitsEspia, "/assets/noxus/espia.png");
        noxus_units.add(espia);


        Effect[] effectComentaristaDaArena = {new Effect(CaseEffects.BOARD_BUFF, 1,0, Trigger.PLAY)};
        Follower comentaristaDaArena = new Follower("Comentarista da Arena", "Dê +1/+0 a todos os aliados em jogo.", 2, 2, 2, Region.NOXUS, effectComentaristaDaArena, "/assets/noxus/comentarista-da-arena.png");
        noxus_units.add(comentaristaDaArena);


        Follower trifariano = new Follower("Trifariano", "", 3, 5, 3, Region.NOXUS, "/assets/noxus/trifariano.png");
        noxus_units.add(trifariano);


        Effect[] effectHomemBomba = {new Effect(CaseEffects.DAMAGE_NEXUS, 1, Trigger.LAST_BREATH)};
        Follower homemBomba = new Follower("Homem Bomba", "Quando eu morrer, cause 1 de dano ao nexus inimigo", 2, 3, 2, Region.NOXUS, effectHomemBomba, "/assets/noxus/homem-bomba.png");
        noxus_units.add(homemBomba);


        Follower capitaoFarron = new Follower("Capitao Farron", "", 7, 8, 9, Region.NOXUS, "/assets/noxus/capitao-farron.png");
        noxus_units.add(capitaoFarron);


        Trait[] traitQuebraEscudos = {Trait.DOUBLE_ATTACK};
        Follower quebraEscudos = new Follower("Quebra Escudos", "", 5, 4, 3, Region.NOXUS, traitQuebraEscudos, "/assets/noxus/quebra-escudos.png");
        noxus_units.add(quebraEscudos);


        //INÍCIO FEITIÇOS DE NOXUS


        Effect[] effectDeterminacao = {new Effect(CaseEffects.SINGLE_TEMP_BUFF, 3, 2, Trigger.PLAY)};
        Spell determinacao = new Spell("Determinacao", "Dê +3/+2 a um aliado nessa rodada.", 2, effectDeterminacao, Region.NOXUS, "/assets/noxus/determinacao.png");
        noxus_spells.add(determinacao);


        Effect[] effectDizimar = {new Effect(CaseEffects.DAMAGE_NEXUS, 4, Trigger.PLAY)};
        Spell dizimar = new Spell("Dizimar", "Dê 3 de dano ao nexus inimigo.", 5, effectDizimar, Region.NOXUS, "/assets/noxus/dizimar.png");
        noxus_spells.add(dizimar);


        Effect[] effectFioDeLamina = {new Effect(CaseEffects.DAMAGE_ANYTHING, 1, Trigger.PLAY)};
        Spell fioDeLamina = new Spell("Fio de Lamina", "Dê 1 de dano em qualquer coisa.", 1, effectFioDeLamina, Region.NOXUS, "/assets/noxus/fio-de-lamina.png");
        noxus_spells.add(fioDeLamina);


        Effect[] effectFervorNoxiano = {new Effect(CaseEffects.DAMAGE_ANYTHING, 3, Trigger.PLAY)};
        Spell fervorNoxiano = new Spell("Fervor Noxiano", "Dê 3 de dano em qualquer coisa.", 3, effectFervorNoxiano, Region.NOXUS, "/assets/noxus/fervor-noxiano.png");
        noxus_spells.add(fervorNoxiano);


        Effect[] effectVisao = {new Effect(CaseEffects.BOARD_BUFF, 0, 1, Trigger.PLAY)};
        Spell visao = new Spell("Visao", "Dê +0/+1 a todos os aliados.", 1, effectVisao, Region.NOXUS, "/assets/noxus/visao.png");
        noxus_spells.add(visao);


        Effect[] effectMachadoDuplo = {new Effect(CaseEffects.SINGLE_TEMP_BUFF, 1, 0, Trigger.PLAY)};
        Spell machadoDuplo = new Spell("Machado Duplo", "Dê +1/+0 a um aliado nessa rodada.", 0, effectMachadoDuplo, Region.NOXUS, "/assets/noxus/machado-duplo.png");
        noxus_spells.add(machadoDuplo);



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
