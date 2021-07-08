package game;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import card.Card;
import card.Effect;
import card.Follower;
import card.Trigger;
import javafx.scene.shape.MoveTo;

public class Game {
    
    private static Game game;
    Player bluePlayer;
    Player redPlayer;
    Board blueBoard;
    Board redBoard;
    boolean gameOver = false;
    boolean endRound = false;
    Color loser;

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
        Player attackingPlayer = null;
        Card nextCard;
        int nextMove;
        boolean validTurn = false;
        boolean passed = false;
        Scanner scan = new Scanner(System.in);
        Board opponentBoard;
        bluePlayer.drawStartingHand();
        redPlayer.drawStartingHand();
        while (!gameOver) {
            startNewRound(attackingPlayer, currentPlayer);

            while (!endRound) {

                if (currentPlayer == bluePlayer) {
                    opponentBoard = redBoard;
                    System.out.println("Jogador azul, é sua vez!");
                }
                else {
                    opponentBoard = blueBoard;
                    System.out.println("Jogador vermelho, é sua vez!");
                }

                while (!validTurn) {

                    nextMove = currentPlayer.selectAction(); 
                    if (nextMove == 0 && currentPlayer.hasCards()) {
                        nextCard = currentPlayer.selectCard();
                        if (!nextCard.playCard(currentPlayer.getBoard(), opponentBoard)) {
                            System.out.println("Você não tem mana o suficiente para jogar essa carta! Selecione outra ou passe a vez.");
                        }
                        else {
                            validTurn = true;
                            currentPlayer.removeCard(nextCard);
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

                if (currentPlayer == bluePlayer){
                    currentPlayer = redPlayer;
                }
                validTurn = false;
                passed = false;

            }

        }

        if (loser == Color.BLUE) {
            System.out.println("Parabéns, jogador azul! Você provou ser um verdadeiro mestre de Legends Of Runeterra!");
        }
        else {
            System.out.println("Parabéns, jogador vermelho! Você se mostrou um jogador muito habilidoso de Legends Of Runeterra!");
        }

        scan.close();

    }


    public void startCombat() {

        ArrayList<Follower> attackers = redBoard.getCombatingFollowers();
        ArrayList<Follower> defenders = blueBoard.getCombatingFollowers();
        Board attackingBoard = redBoard;
        Board defendingBoard = blueBoard;
        Player defender = bluePlayer;
        Scanner scan = new Scanner(System.in);


        if (blueBoard.getCurrentTurn()) {

            attackers = blueBoard.getCombatingFollowers();
            defenders = redBoard.getCombatingFollowers();

            attackingBoard = blueBoard;
            defendingBoard = redBoard;

            defender = redPlayer;

        }

        System.out.println("Escolha as unidades que devem atacar.");
        //print board
        String[] toAttack = scan.nextLine().split(" ");
        for (int i = 0; i < toAttack.length; i++){
            attackingBoard.moveToCombat(i, Integer.parseInt(toAttack[i]));
        }
        System.out.println("Escolha as unidades que devem defender.");
        //print board
        for (int i = 0; i < toAttack.length; i++){
            System.out.println("Você quer defender a unidade" + i + " ? Digite o número da unidade que você deseja usar para defender ou -1 para nao defender.");
            defendingBoard.moveToCombat(i, scan.nextInt());
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
        scan.close();
    }

    private void startNewRound(Player attackingPlayer, Player currentPlayer) {
        redPlayer.updateMana();
        bluePlayer.updateMana();
        if (attackingPlayer == null){
            Random rand = new Random();
            if (rand.nextInt(1) == 0){
                attackingPlayer = bluePlayer;
            }
            else {
                attackingPlayer = redPlayer;
            }
        }
        else if (attackingPlayer == bluePlayer){
            attackingPlayer = redPlayer;
        }
        else {
            attackingPlayer = bluePlayer;
        }

        currentPlayer = attackingPlayer;

        redPlayer.drawCard(1);
        bluePlayer.drawCard(1);

        checkWin(attackingPlayer);
        
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

    private void checkWin(Player attackingPlayer){
        if (bluePlayer.getLoser() == true || redPlayer.getLoser() == true){
            gameOver = true;
            endRound = true;
            if (bluePlayer.getLoser() == true){
                if (redPlayer.getLoser() == true){
                    loser = attackingPlayer.getColor();
                }
                else {
                    loser = Color.BLUE;
                }
            }
            else {
                loser = Color.RED;
            }
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

