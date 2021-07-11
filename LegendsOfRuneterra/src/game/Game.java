package game;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import card.Card;
import card.Effect;
import card.Follower;
import card.Trait;
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
    Scanner scan;

    private Game(Player p1, Player p2) {
        this.bluePlayer = p1;
        this.redPlayer = p2;

        blueBoard = new Board(bluePlayer);
        redBoard = new Board(redPlayer);

        p1.setBoard(blueBoard);
        p1.setColor(Color.BLUE);
        p2.setBoard(redBoard);
        p2.setColor(Color.RED);
        scan = new Scanner(System.in);
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
        boolean hasAttacked = false;
        Board opponentBoard;
        bluePlayer.drawStartingHand();
        redPlayer.drawStartingHand();
        while (!gameOver) {
            startNewRound(attackingPlayer, currentPlayer);
            hasAttacked = false;
            passed = false;

            while (!endRound) {

                if (currentPlayer == bluePlayer) {
                    opponentBoard = redBoard;
                    System.out.println("Jogador azul, é sua vez!");
                    printHand(bluePlayer);
                }
                else {
                    opponentBoard = blueBoard;
                    System.out.println("Jogador vermelho, é sua vez!");
                    printHand(redPlayer);
                }
                printBoard();

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
                            checkDeaths(currentPlayer.getBoard(), opponentBoard);
                            checkDeaths(opponentBoard, currentPlayer.getBoard());
                            passed = false;
                        }
                    }
                    else if (nextMove == 1 && !currentPlayer.getBoard().getCards().isEmpty() && currentPlayer == attackingPlayer && hasAttacked == false){
                        startCombat();
                        validTurn = true;
                        passed = false;
                        hasAttacked = true;
                    }
                    else if (nextMove == 2){
                        validTurn = true;
                        if (passed) {
                            endRound = true;
                        }
                        passed = true;
                    }
                    else {
                        System.out.println("Você tentou uma ação inválida");
                    }
                
                }

                if (currentPlayer == bluePlayer){
                    currentPlayer = redPlayer;
                }
                else {
                    currentPlayer = bluePlayer;
                }

                validTurn = false;

            }

            for (Follower follower : blueBoard.getCards()){
                if (follower.hasTrait(Trait.REGENERATION)){
                    follower.heal(0, true);
                }
            }
            updateAllEffects(Trigger.ROUND_END, blueBoard, redBoard);
            updateAllEffects(Trigger.ROUND_END, redBoard, blueBoard);
        }

        if (loser == Color.BLUE) {
            System.out.println("Parabéns, jogador azul! Você provou ser um verdadeiro mestre de Legends Of Runeterra!");
        }
        else {
            System.out.println("Parabéns, jogador vermelho! Você se mostrou um jogador muito habilidoso de Legends Of Runeterra!");
        }

        scan.close();
        redBoard.closeScan();
        blueBoard.closeScan();
        redPlayer.closeScan();
        bluePlayer.closeScan();

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

        System.out.println("Escolha as unidades que devem atacar.");
        printPlayerBoard(attackingBoard.getPlayer());
        int[] toAttack = Arrays.stream(scan.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int position = 0;
        boolean validDefender = false;
        ArrayList<Follower> aux = new ArrayList<Follower>();
        for (int i = 0; i < toAttack.length; i++){
            for (Effect effect : attackingBoard.getCards().get(i).getEffects()){
                effect.checkTrigger(Trigger.ATTACK, attackingBoard, defendingBoard, attackingBoard.getCards().get(i));
            }
            aux.add(attackingBoard.getCards().get(toAttack[i]));
            
        }
        for (Follower follower : aux){
            attackingBoard.moveToCombat(position, follower);
            position++;
        }
        System.out.println("Escolha as unidades que devem defender.");
        printAttackingUnits(attackers);
        printPlayerBoard(defender);
        aux.clear();
        position = 0;
        for (int i = 0; i < toAttack.length; i++){
            while (validDefender ==  false){
                System.out.println("Você quer defender a unidade" + i + " ? Digite o número da unidade que você deseja usar para defender ou -1 para nao defender.");
                int defendingUnit = scan.nextInt();
                if (defendingUnit == -1){
                    aux.add(null);
                    validDefender = true;
                }
                else {
                    if (attackers.get(toAttack[i]).hasTrait(Trait.ELUSIVE) || !defendingBoard.getCards().get(defendingUnit).hasTrait(Trait.ELUSIVE)){
                        System.out.println("Você só pode bloquer uma unidade elusiva com outra unidade elusiva");
                        validDefender = false;
                    }
                    else {
                        aux.add(defendingBoard.getCards().get(defendingUnit));
                        validDefender = true;
                    }
                }
            }
            validDefender = false;
        }
        for (Follower follower : aux){
            defendingBoard.moveToCombat(position, follower);
            position++;
        }

        printCombat(attackers, defenders);

        for (int i = 0; i < attackers.size(); i++) {
            if (defenders.get(i) == null) {
                if (attackers.get(i).hasTrait(Trait.DOUBLE_ATTACK)){
                    attackers.get(i).strike(defender, attackingBoard);
                }
                attackers.get(i).strike(defender, attackingBoard);
            }
            else {
                if (attackers.get(i).hasTrait(Trait.DOUBLE_ATTACK)){
                    attackers.get(i).strike(defenders.get(i), attackingBoard, defendingBoard);
                }
                attackers.get(i).strike(defenders.get(i), attackingBoard, defendingBoard);
                if (attackers.get(i).hasTrait(Trait.DOUBLE_ATTACK) == false && attackers.get(i).hasTrait(Trait.QUICK_ATTACK) == false && defenders.get(i).getCurrentHealth() > 0){
                    defenders.get(i).strike(attackers.get(i), attackingBoard, defendingBoard);
                }
            }
            if (attackers.get(i).hasTrait(Trait.FURY) && defenders.get(i).getCurrentHealth() <= 0){
                attackers.get(i).triggerFury();
            }
        }

        checkDeaths(attackingBoard, defendingBoard);
        checkDeaths(defendingBoard, attackingBoard);

        printCombat(attackers, defenders);
    }

    private void startNewRound(Player attackingPlayer, Player currentPlayer) {
        redPlayer.updateMana();
        bluePlayer.updateMana();
        System.out.println("Mana máxima: " + bluePlayer.getCurrentMana());
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

        System.out.println("Jogador" + attackingPlayer.getColor().toString() + ", é a sua rodada de ataque!");
        
        if (bluePlayer.getCurrentMana() == 10){
            updateAllEffects(Trigger.ENLIGHTENED, bluePlayer.getBoard(), redPlayer.getBoard());
        }
        
        redPlayer.drawCard(1);
        bluePlayer.drawCard(1);

        checkWin(attackingPlayer);
        
        updateAllEffects(Trigger.ROUND_START, redBoard, blueBoard);
        updateAllEffects(Trigger.ROUND_START, blueBoard, redBoard);
    }

    private void printAttackingUnits(ArrayList<Follower> attackers) {
        System.out.println("Unidades atacando: ");
        System.out.println("--------------------------");
        printCards(attackers);
    }

    private void printCombat(ArrayList<Follower> attackers, ArrayList<Follower> defenders){
        printAttackingUnits(attackers);
        System.out.println("Unidades defendendo");
        System.out.println("--------------------------");
        printCards(defenders);

    }

    public void printPlayerBoard(Player p) {
        ArrayList<Follower> unitList;
        System.out.println("Unidades do jogador " + p.getColor().toString() + ": ");
        if (p.getColor() == Color.BLUE) {
            unitList = blueBoard.getCards();
        }
        else {
            unitList = redBoard.getCards();
        }

        System.out.println("--------------------------");
        printCards(unitList);
    }

    private void printHand(Player p) {
        ArrayList<Card> hand = p.getHand();
        System.out.println("Cartas da sua mão: ");
        System.out.println("--------------------------");
        printCards(hand);
    }

    private void printCards(ArrayList<? extends Card> cards) {
        int i = 0;
        for (Card card : cards) {
            System.out.println(i + ") " + card);
            System.out.println("--------------------------");
            i++;
        }
    }

    private void printBoard() {

       printPlayerBoard(bluePlayer);
       System.out.println("\n");
       printPlayerBoard(redPlayer);
    }

    private void checkDeaths(Board myBoard, Board opponentBoard){
        for (Follower follower : myBoard.getCombatingFollowers()) {
            if (follower != null){
                if (follower.getCurrentHealth() > 0) {
                    myBoard.returnFromCombat(follower);
                }
                else {
                    for (Effect effect : follower.getEffects()){
                        effect.checkTrigger(Trigger.LAST_BREATH, myBoard, opponentBoard, follower);
                    }
                    updateAllEffects(Trigger.SEEN_ALLY_DIE, myBoard, opponentBoard);
                    myBoard.getCombatingFollowers().remove(follower);
                }
            }
        }
    }


    private void updateAllEffects(Trigger trigger, Board myBoard, Board opponentBoard){
        for (Follower follower : myBoard.getCards()) {
            for (Effect effect : follower.getEffects()) {
                effect.checkTrigger(trigger, myBoard, opponentBoard, follower);
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

