package game;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

import card.Card;
import card.Effect;
import card.Trigger;

public class Player {

    private ArrayList<Card> hand;
    private int maxMana;
    private int currentMana;
    private int spellMana;
    private int nexusLife;
    private Deck deck;
    private Board board;
    private boolean isHuman;
    private Color color;
    private boolean loser;
    private Scanner scan;

    public Player(Deck deck, boolean isHuman) {
        this.hand = new ArrayList<Card>();
        this.maxMana = 0;
        this.currentMana = 0;
        this.spellMana = 0;
        this.scan = new Scanner(System.in);
        this.nexusLife = 20;
        this.deck = deck;
        this.isHuman = isHuman;
        this.loser = false;
    }

    public void drawStartingHand() {
        deck.shuffle();
        drawCard(4);
        changeStartingCards();
        
    }

    private void changeStartingCards() {

        System.out.println("Digite o número da cartas que deseja trocar, separadas por espaço: ");
        String[] numeros = scan.nextLine().split(" ");
        ArrayList<Card> toChange = new ArrayList<Card>();
        for (String num : numeros) {
            toChange.add(hand.get(Integer.parseInt(num)));
        }
        for (Card card : toChange) {
            changeCard(card);
        }

        scan.reset();
    }

    public void changeCard(Card toReturn) {
        deck.addCard(toReturn);
        hand.remove(toReturn);
        deck.shuffle();
        drawCard(1);
    }

    public void drawCard(int n) {
        
        if (deck.getCards().size() >= n) { // vê se o jogador possui cartas o suficiente para comprar.
            for (int i = 0; i < n; i++) {
                hand.add(deck.getCards().get(0));
                deck.getCards().remove(0);
            }
        }
        else {
            lose();
        }
    }

    public void drawCard(int n, String nameCard) {
        for (int i = 0; i < n; i++) {
            for (Card card : deck.getCards()) {
                if (card.getName() == nameCard){
                    hand.add(card);
                    deck.getCards().remove(card);
                }
            }
        }
    }

    public void takeDamage(int damage) {
        this.nexusLife -= damage;
    }

    public void printHand() {
        System.out.println("Cartas da sua mão: ");
        System.out.println("--------------------------");
        printCards(hand);
    }
    public int getNexusLife() {
        return this.nexusLife;
    }

    public Deck getDeck() {
        return this.deck;
    }

    public ArrayList<Card> getHand() {
        return this.hand;
    }

    public boolean hasCards() {
        if (hand.isEmpty()) {
            return false;
        }
        else {
            return true;
        }
    }

    public boolean isHuman() {
        return isHuman;
    }

    public int getCurrentMana() {
        return currentMana;
    }

    public void setBoard(Board newBoard) {
        this.board = newBoard;
    }

    public void setColor(Color newColor) {
        this.color = newColor;
    }

    public void selectDeck(Deck deck) {
        if (this.deck == null) {    
            this.deck = deck;
        }
    }

    public int selectAction() {
        int action;
        if (isHuman == true){
            System.out.println("Digite 0 para jogar uma carta, 1 para combater ou 2 para passar o turno: ");
            action = scan.nextInt();
            scan.close();
        }
        else {
            action = getRandomResult(3);
        }
        return action;
    }

    public Card selectCard() {
        Card nextCard;
        int numCard = 0;
        if (isHuman) {
            System.out.println("Digite o número da carta que deseja jogar: ");
            for (Card card : hand) {
                System.out.println(numCard + " - " + card.getName() + " " + card.getCost() + " de mana");
                numCard++;
            }
            System.out.println("Sua mana: " + currentMana + " mais " + spellMana + " de mana de feitiço");
            System.out.println();
            while (true) {
                try {
                    numCard = scan.nextInt();
                    nextCard = hand.get(numCard);
                    scan.reset();
                    return nextCard;
                } catch (InputMismatchException | IndexOutOfBoundsException f) {
                    System.out.println("número inválido, tente novamente: ");
                }
            }
        }
        else {
            numCard = getRandomResult(hand.size()+1) - 1;
            nextCard = hand.get(numCard);
            return nextCard;
            
        }
    }

    public void updateMana() {
        spellMana = spellMana + currentMana;
        if (spellMana > 3) {
            spellMana = 3;
        }

        maxMana++;
        currentMana = maxMana;
    }

    public boolean spendMana(int n, boolean isSpell) {
        
        if (isSpell) {
            if (currentMana + spellMana >= n) {
                if (spellMana >= n) {
                    spellMana -= n;
                }
                else {
                    n -= spellMana;
                    currentMana -= n;
                }
                return true;
            }
            else {
                return false;
            }
        }
        else {
            if (currentMana >= n) {
                currentMana -= n;
                return true;
            }
            else {
                return false;
            }
        }

    }

    public Board getBoard() {
        return this.board;
    }

    public int getRandomResult(int max) {
        Random rand = new Random();
        return rand.nextInt(max);
    }

    public void removeCard(Card toRemove){
        hand.remove(toRemove);
    }

    public boolean getLoser(){
        return loser;
    }

    public void lose(){
        loser = true;
    }

    public Color getColor(){
        return color;
    }

    public void closeScan(){
        scan.close();
    }

    public void addMaxMana(int mana){
        maxMana += mana;
    }

    public void healNexus(int health){
        nexusLife += health;
        if (nexusLife >= 20){
            nexusLife = 20;
        }
    }

}