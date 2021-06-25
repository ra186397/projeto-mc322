package game;

import java.util.ArrayList;
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

    public Player(Deck deck, boolean isHuman) {
        this.hand = new ArrayList<Card>();
        this.maxMana = 0;
        this.currentMana = 0;
        this.spellMana = 0;
        this.nexusLife = 20;
        this.deck = deck;
        this.isHuman = isHuman;
    }

    public void drawStartingHand() {
        deck.shuffle();
        drawCard(4);
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o número da cartas que deseja trocar, separadas por espaço: ");
        String[] numeros = sc.nextLine().split(" ");
        for (String num : numeros) {
            changeCard(Integer.parseInt(num));
        }

        sc.close();
        
    }

    public void changeCard(int index) {
        hand.remove(index);
        drawCard(1);
    }

    public void drawCard(int n) {
        for (int i = 0; i < n; i++) {
            hand.add(deck.getCards().get(0));
            deck.getCards().remove(0);
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

    public int getNexusLife() {
        return this.nexusLife;
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

    public int selectAction() {
        int action;
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite 0 para jogar uma carta, 1 para combater e 2 para passar o turno: ");
        action = sc.nextInt();
        sc.close();
        return action;
    }

    public Card selectCard() {
        Card nextCard;
        boolean validNumber = false;
        int numCard = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o número da carta que deseja jogar: ");
        for (Card card : hand) {
            System.out.println(numCard + " - " + card.getName());
            numCard++;
        }
        System.out.println();
        while (!validNumber) {
            try {
                numCard = sc.nextInt();
                validNumber = true;
            } catch (Exception inputMismatchException) {
                System.out.println("Número inválido, tente denovo: ");
            }
        }
        if (numCard == -1) {
            nextCard = null;
        }
        else {
            nextCard = hand.get(numCard);
        }
        sc.close();

        return nextCard;
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

}
