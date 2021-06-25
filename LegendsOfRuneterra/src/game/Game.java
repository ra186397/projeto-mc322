package game;

import java.util.ArrayList;
import java.util.Scanner;

import card.Card;
import card.Effect;
import card.Follower;
import card.Trigger;

public class Game {

    private static Game game;
    boolean gameOver;
    boolean endRound;
    Player bluePlayer;
    Player redPlayer;
    Board blueBoard;
    Board redBoard;

    private Game(Player p1, Player p2) {
        this.bluePlayer = p1;
        this.redPlayer = p2;
        this.gameOver = false;
        this.endRound = false;

        blueBoard = new Board(bluePlayer);
        redBoard = new Board(redPlayer);

        p1.setBoard(blueBoard);
        p2.setBoard(redBoard);
    }

    public static Game getGame(Player p1, Player p2) {
        if (game == null) {
            game = new Game(p1, p2);
        }
        return game;
    }

    public void startGame() {
        
        Player currentPlayer;
        Card nextCard;
        int nextMove;
        boolean validTurn= false;
        Scanner scan = new Scanner(System.in);
        bluePlayer.drawStartingHand();
        redPlayer.drawStartingHand();
        while (!gameOver) {
            startNewRound();

            while (!endRound) {

                if (blueBoard.getCurrentTurn()) {
                    System.out.println("Jogador azul, é sua vez!");
                    currentPlayer = bluePlayer;
                }
                else {
                    System.out.println("Jogador vermelho, é sua vez!");
                    currentPlayer = redPlayer;
                }

                while (!validTurn) {

                    nextMove = currentPlayer.selectAction(); 
                    if (nextMove == 0) {
                        nextCard = currentPlayer.selectCard();
                        if (!nextCard.playCard(currentPlayer.getBoard())) {
                            System.out.println("Você não tem mana o suficiente para jogar essa carta! Selecione outra ou passe a vez.");
                        }
                        else {
                            validTurn = true;
                        }
                    }
                    else {
                        validTurn = true;
                    }
                }
            }
        }
    }


    public void startCombat() {

        ArrayList<Follower> attackers = redBoard.getCombatingFollowers();
        ArrayList<Follower> defenders = blueBoard.getCombatingFollowers();
        Board attackingBoard = redBoard;
        Board defendingBoard = blueBoard;
        Player defender = bluePlayer;

        if (blueBoard.getCurrentTurn()) {

            attackers = blueBoard.getCombatingFollowers();
            defenders = redBoard.getCombatingFollowers();

            attackingBoard = blueBoard;
            defendingBoard = redBoard;

            defender = redPlayer;

        }

        for (int i = 0; i < attackers.size(); i++) {
            if (defenders.get(i) == null) {
                attackers.get(i).strike(defender);
            } else {
                attackers.get(i).strike(defenders.get(i));
                defenders.get(i).strike(attackers.get(i));
            }
        }

        for (int i = 0; i < attackers.size(); i++) {
            if (attackers.get(i).getCurrentHealth() > 0 && attackers.get(i) != null) {
                attackingBoard.returnFromCombat(i);
            }
            if (defenders.get(i).getCurrentHealth() > 0 && attackers.get(i) != null) {
                defendingBoard.returnFromCombat(i);
            }
        }

    }

}

private void startNewRound() {
    redPlayer.updateMana();
    bluePlayer.updateMana();
    redPlayer.drawCard(1);
    bluePlayer.drawCard(1);
    
    updateAllEffects(Trigger.ROUND_START);
}


private void updateAllEffects(Trigger trigger) {
    for (Follower follower : redBoard.getCards()) {
        for (Effect effect : follower.getEffects()) {
            effect.checkTrigger(trigger, redBoard, blueBoard);
        }
    }
    for (Follower follower : blueBoard.getCards()) {
        for (Effect effect : follower.getEffects()) {
            effect.checkTrigger(trigger, blueBoard, redBoard);
        }
    }
}