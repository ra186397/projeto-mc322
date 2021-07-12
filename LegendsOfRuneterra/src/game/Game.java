package game;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;
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
    Random rand;

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

        this.rand = new Random();
    }

    public static Game getGame(Player p1, Player p2) {
        if (game == null) {
            game = new Game(p1, p2);
        }
        return game;
    }
    
    public void startGame() {

        System.out.println("Bem vindx a Legends of Runeterra.");
        
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
            attackingPlayer = startNewRound(attackingPlayer, currentPlayer);
            currentPlayer = attackingPlayer;
            endRound = false;
            hasAttacked = false;
            passed = false;

            while (!endRound) {

                if (currentPlayer == bluePlayer) {
                    opponentBoard = redBoard;
                    System.out.println("Jogador azul, é sua vez!");
                    if (bluePlayer.isHuman()){
                        printHand(bluePlayer);
                    }
                }
                else {
                    opponentBoard = blueBoard;
                    System.out.println("Jogador vermelho, é sua vez!");
                    if (redPlayer.isHuman()){
                        printHand(redPlayer);
                    }
                }
                printBoard();

                while (!validTurn) {

                    printPlayerMana(currentPlayer);
                    nextMove = currentPlayer.selectAction();
                    if (nextMove == 0 && currentPlayer.hasCards()) {
                        nextCard = currentPlayer.selectCard();
                        if (!nextCard.playCard(currentPlayer.getBoard(), opponentBoard)) {
                            if (currentPlayer.isHuman()){
                                System.out.println("Você não tem mana o suficiente para jogar essa carta! Selecione outra ou passe a vez.");
                            }
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
                        startCombat(attackingPlayer);
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
                        if (currentPlayer.isHuman()){
                            System.out.println("Você tentou uma ação inválida");
                        }
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

        System.exit(0);

    }


    public void startCombat(Player attackingPlayer) {

        ArrayList<Follower> attackers = redBoard.getCombatingFollowers();
        ArrayList<Follower> defenders = blueBoard.getCombatingFollowers();
        Board attackingBoard = redBoard;
        Board defendingBoard = blueBoard;
        Player defender = bluePlayer;


        if (attackingPlayer.getColor() == Color.BLUE) {

            attackers = blueBoard.getCombatingFollowers();
            defenders = redBoard.getCombatingFollowers();

            attackingBoard = blueBoard;
            defendingBoard = redBoard;

            defender = redPlayer;

        }
        
        int[] toAttack = {};
        boolean validAttackerList = false;
        if (defender.isHuman()){
            while (validAttackerList == false){
                try {
                    System.out.println("Escolha as unidades que devem atacar.");
                    printPlayerBoard(attackingBoard.getPlayer());
                    toAttack = Arrays.stream(scan.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                    ArrayList<Follower> auxAttack = new ArrayList<Follower>();
                    for (int i = 0; i < toAttack.length; i++){
                        for (Effect effect : attackingBoard.getCards().get(i).getEffects()){
                            effect.checkTrigger(Trigger.ATTACK, attackingBoard, defendingBoard, attackingBoard.getCards().get(i));
                        }
                        auxAttack.add(attackingBoard.getCards().get(toAttack[i]));
                        
                    }
                    for (Follower follower : auxAttack){
                        attackingBoard.moveToCombat(follower);
                    }
                    validAttackerList = true;
                }
                catch (IndexOutOfBoundsException | NullPointerException e){
                    System.out.println("Você escolheu um número inválido");
                }
            }
        }
        else {
            attackingBoard.moveToCombat(attackingBoard.getCards().get(rand.nextInt(attackingBoard.getCards().size())));
        }
        boolean validDefender = false;
        System.out.println("Escolha as unidades que devem defender.");
        printAttackingUnits(attackers);
        printPlayerBoard(defender);
        int defendingUnit;
        try {
            ArrayList<Follower> auxDefense = new ArrayList<Follower>();
            for (int i = 0; i < toAttack.length; i++){
                while (validDefender ==  false){
                    if (defender.isHuman()){
                        System.out.println("Você quer defender a unidade " + i + " ? Digite o número da unidade que você deseja usar para defender ou -1 para nao defender.");
                        defendingUnit = scan.nextInt();
                    }
                    else {
                        defendingUnit = rand.nextInt(defendingBoard.getCards().size()+1)-1;
                    }
                    if (defendingUnit == -1){
                        auxDefense.add(null);
                        validDefender = true;
                    }
                    else {
                        if (attackers.get(toAttack[i]).hasTrait(Trait.ELUSIVE) || !defendingBoard.getCards().get(defendingUnit).hasTrait(Trait.ELUSIVE)){
                            System.out.println("Você só pode bloquer uma unidade elusiva com outra unidade elusiva");
                            validDefender = false;
                        }
                        else {
                            auxDefense.add(defendingBoard.getCards().get(defendingUnit));
                            validDefender = true;
                        }
                    }
                }
                validDefender = false;
            }
            for (Follower follower : auxDefense){
                defendingBoard.moveToCombat(follower);
            }
        }
        catch (IndexOutOfBoundsException | NullPointerException e){
            System.out.println("Você digitou um índice inválido");
        }      

        printCombat(attackers, defenders);

        for (int i = 0; i < attackers.size(); i++) {
            if (defenders.get(i) == null) {
                if (attackers.get(i).hasTrait(Trait.DOUBLE_ATTACK)){
                    attackers.get(i).strike(defender, attackingBoard);
                }
                attackers.get(i).strike(defender, attackingBoard);
                System.out.println("Vida do Nexus " + defender.getColor().toString() + ": " + defender.getNexusLife());
            }
            else {
                if (attackers.get(i).hasTrait(Trait.DOUBLE_ATTACK)){
                    attackers.get(i).strike(defenders.get(i), attackingBoard, defendingBoard);
                }
                if (!((attackers.get(i).hasTrait(Trait.DOUBLE_ATTACK) == true || attackers.get(i).hasTrait(Trait.QUICK_ATTACK) == true) && defenders.get(i).getCurrentHealth() < 0)){
                    defenders.get(i).strike(attackers.get(i), attackingBoard, defendingBoard);
                }
                attackers.get(i).strike(defenders.get(i), attackingBoard, defendingBoard);
                if (attackers.get(i).hasTrait(Trait.FURY) && defenders.get(i).getCurrentHealth() <= 0){
                    attackers.get(i).triggerFury();
                }
            }
        }

        checkDeaths(attackingBoard, defendingBoard);
        checkDeaths(defendingBoard, attackingBoard);

        defenders.clear();

    }

    private Player startNewRound(Player attackingPlayer, Player currentPlayer) {
        redPlayer.updateMana();
        bluePlayer.updateMana();
        System.out.println("Mana máxima: " + bluePlayer.getCurrentMana());
        if (attackingPlayer == null){
            Random rand = new Random();
            if (rand.nextInt(2) == 0){
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

        System.out.println("Jogador " + attackingPlayer.getColor().toString() + ", é a sua rodada de ataque!");
        
        if (bluePlayer.getCurrentMana() == 10){
            updateAllEffects(Trigger.ENLIGHTENED, bluePlayer.getBoard(), redPlayer.getBoard());
        }
        
        redPlayer.drawCard(1);
        bluePlayer.drawCard(1);

        checkWin(attackingPlayer);
        
        updateAllEffects(Trigger.ROUND_START, redBoard, blueBoard);
        updateAllEffects(Trigger.ROUND_START, blueBoard, redBoard);
        return attackingPlayer;
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

    void printHand(Player p) {
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
    private void printPlayerMana(Player p){
        System.out.println("Sua mana: " + p.getCurrentMana() + " mais " + p.getSpellMana() + " de mana de feitiço");
    }

    public Scanner getScanner(){
        return scan;
    }

    private void checkDeaths(Board myBoard, Board opponentBoard){
        ArrayList<Follower> toReturn = new ArrayList<Follower>();
        ArrayList<Follower> toRemove = new ArrayList<Follower>();
        for (Follower follower : myBoard.getCombatingFollowers()) {
            if (follower != null){
                if (follower.getCurrentHealth() > 0) {
                    toReturn.add(follower);
                }
                else {
                    for (Effect effect : follower.getEffects()){
                        effect.checkTrigger(Trigger.LAST_BREATH, myBoard, opponentBoard, follower);
                    }
                    updateAllEffects(Trigger.SEEN_ALLY_DIE, myBoard, opponentBoard);
                    toRemove.add(follower);
                }
            }
        }
        for (Follower backFollower : myBoard.getCards()){
            if (backFollower != null){
                if (backFollower.getCurrentHealth() <= 0) {
                    for (Effect effect : backFollower.getEffects()){
                        effect.checkTrigger(Trigger.LAST_BREATH, myBoard, opponentBoard, backFollower);
                    }
                    updateAllEffects(Trigger.SEEN_ALLY_DIE, myBoard, opponentBoard);
                    toRemove.add(backFollower);
                }
            }
        }
        for (Follower livingFollower : toReturn){
            myBoard.returnFromCombat(livingFollower);
        }
        for (Follower deadFollower : toRemove)
        myBoard.getCombatingFollowers().remove(deadFollower);
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