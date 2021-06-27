package game;

import java.util.ArrayList;
import java.util.Scanner;

import card.Card;
import card.Effect;
import card.Follower;
import card.Trigger;

public class Game {

    private static Game game;
    Player bluePlayer;
    Player redPlayer;
    Board blueBoard;
    Board redBoard;

    private Game(Player p1, Player p2) {
        this.bluePlayer = p1;
        this.redPlayer = p2;

        blueBoard = new Board(bluePlayer);
        redBoard = new Board(redPlayer);

        p1.setBoard(blueBoard);
        p1.setColor(Color.BLUE);
        p2.setBoard(redBoard);
        p2.setColor(Color.RED);
    }

    public static Game getGame(Player p1, Player p2) {
        if (game == null) {
            game = new Game(p1, p2);
        }
        return game;
    }

    public void startGame() {
        
        Player currentPlayer = null;
        Color loser;
        Card nextCard;
        int nextMove;
        boolean validTurn = false;
        boolean gameOver = false;
        boolean endRound = false;
        boolean passed = false;
        Scanner sc = new Scanner(System.in);
        bluePlayer.drawStartingHand();
        redPlayer.drawStartingHand();
        while (!gameOver) {
            loser = startNewRound(currentPlayer);

            if (loser != Color.NONE) {
                endRound = true;
                gameOver = true;
            }

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
                    if (nextMove == 0 && currentPlayer.hasCards()) {
                        nextCard = currentPlayer.selectCard();
                        if (!nextCard.playCard(currentPlayer.getBoard())) {
                            System.out.println("Você não tem mana o suficiente para jogar essa carta! Selecione outra ou passe a vez.");
                        }
                        else {
                            validTurn = true;
                            passed = false;
                        }
                    }
                    else if (nextMove == 1 && !currentPlayer.getBoard().getCards().isEmpty()){
                        startCombat();
                        validTurn = true;
                        passed = false;
                    }
                    else {
                        validTurn = true;
                        if (passed) {
                            endRound = true;
                        }
                        passed = true;
                    }
                }
            }

        }

        if (loser == Color.BLUE) {
            System.out.println("Parabéns, jogador azul! Você provou ser um verdadeiro mestre de Legends Of Runeterra!");
        }
        else {
            System.out.println("Parabéns, jogador vermelho! Você se mostrou um jogador muito habilidoso de Legends Of Runeterra!");
        }

        sc.close();

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

private Color startNewRound(Player currentPlayer) {
    Color loser;
    redPlayer.updateMana();
    bluePlayer.updateMana();
    loser = redPlayer.drawCard(1);
    loser = bluePlayer.drawCard(1);
    if ()
    
    updateAllEffects(Trigger.ROUND_START);
    return loser;
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




/*
array de todos os seguidores em jogo (Ax)

foreach seguidor em Ax

seguidor.AtualizarFisico(indice do seguidor em Ax)


public void atualizarFisico() {

    posicao 0:
    se tiver seguidor de indice 0
        colocar seguidor de indice 0

    posicao 1:
        se tiver seguidor de indice 1
        colocar seguidor de indice 1
}
*/

