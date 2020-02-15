
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author marco
 */
public class DiceGame {

    private Player[] players;
    private int numPlayers;
    private int pot;
    private boolean gameOver;
    Scanner input = new Scanner(System.in);
    private int mode;

    public DiceGame() {
        gameOver = false;

    }

    /**
     * Method that gets the number of the plays
     *
     * @return numPlayers
     */
    public int getNumPlayers() {
        return numPlayers;
    }

    /**
     * Method that gets players
     *
     * @return players
     */
    public Player[] getPlayers() {
        return players;
    }

    /**
     * Method tells if the game is over or not
     *
     * @return gameOver
     */
    public boolean isGameOver() {
        return gameOver;
    }

    public void setUpGame() {
        System.out.println("Enter the mode of game you want to play:"
                + "\n1. Easy mode \n2. Hard mode");
        mode = input.nextInt();
        while ((mode != 1) && (mode != 2)) {
            System.out.println("Incorrect input, Please select 1 or 2");
            System.out.println("1. Easy mode \n2. Hard mode");
            mode = input.nextInt();
        }

        System.out.print("How many people are going to play?");
        numPlayers = input.nextInt();
        players = new Player[numPlayers];
        for (int i = 0; i < players.length; i++) {
            System.out.println("What is your name?");
            String name = input.next();
            players[i] = new Player(name);
        }
        displayRules();
    }

    /**
     * Method that displays the rules of the game
     */
    public void displayRules() {
        switch (mode) {
            //Easy mode rules
            case 1:
                System.out.println("Rules!");
                System.out.println("Each player places a bet and chooses a number between 2 and 12.");
                System.out.println("The total of all the bets forms a \"pot\"");
                System.out.println("Then two dices are rolled.");
                System.out.println("If one of the players bet on the result correctly, she or he wins the entire pot");
                System.out.println("If more than one player bet on that number, the one who bet the most wins the entire pot");
                System.out.println("If there is a tie, they spit the pot");
                System.out.println("If nobody bet on that number, the money remains in the pot for the next round");
                System.out.println("The game is over if one of the players run out of money");
                break;
            //Hard mode rules
            case 2:
                System.out.println("RULES!");
                System.out.println("Each player places a bet and will choose TWO numbers between 1 and 6.");
                System.out.println("The total of all the bets forms a \"pot\".");
                System.out.println("Then two dices are rolled.");
                System.out.println("If one of the players bet on the result correctly, he or she wins the entire pot.");
                System.out.println("If more than one player bet on that number, the one who bet the most wins the entire pot.");
                System.out.println("If there is a tie, they split the pot.");
                System.out.println("If nobody bet on that number, the money remains in the pot for the next round.");
                System.out.println("The game is over if one of the players run out of money.");
                break;
        }
    }

    /**
     * Method that plays the game
     */
    public void playGame() {
        int outcome;
        int outcome1;
        int outcome2;
        for (Player player : players) {
            playTurn(player);
            pot += player.getBetAmount();
        }
        //Generates 2 random numbers between 1 and 6
        int first = (int) (Math.random() * 6 + 1);
        int second = (int) (Math.random() * 6 + 1);
        //checks which mode was selected
        if (mode == 1) {
            outcome = first + second;
            checkWinner(outcome);
            //checks to see3 if balance is zero or less than
            for (Player p : players) {
                if (p.getBalance() <= 0) {
                    gameOver = true;
                    break;
                }
            }
        } else if (mode == 2) {
            outcome1 = first;
            outcome2 = second;
            checkWinner(outcome1, outcome2);
            //checks to see3 if balance is zero or less than
            for (Player p : players) {
                if (p.getBalance() <= 0) {
                    gameOver = true;
                    break;
                }
            }
        }

    }

    /**
     * Method that takes the bet amount and guess of the players
     *
     * @param play
     */
    public void playTurn(Player play) {
        System.out.println(play.getName() + " you have $" + play.getBalance() + " left, how much are you going to bet?");
        //stores the bet amount
        int bet = input.nextInt();
        //checks to see if bet ammount exceeds there balance
        while (bet > play.getBalance()) {
            System.out.println("Bet balance exceeds available funds");
            System.out.println("Enter the amount you would like to bet: ");
            bet = input.nextInt();
        }
        //sets bet to setBetAmount
        play.setBetAmount(bet);
        play.setBalance(play.getBalance() - play.getBetAmount());
        //For easy mode
        if (mode == 1) {
            System.out.println("Enter your guess between 2 and 12: ");
            //stores there guess integer
            int guess = input.nextInt();
            //checks if there guess integer is between 2 and 12
            while ((guess < 2) || (guess > 12)) {
                System.out.println("Incorrect guess.");
                System.out.println("Enter your guess between 2 and 12: ");
                guess = input.nextInt();
            }
            play.setGuess(guess);
        } //For hard mode
        else if (mode == 2) {
            System.out.println("Enter your first guess bewteen 1 and 6: ");
            int guess1 = input.nextInt();
            while ((guess1 < 1) || (guess1 > 6)) {
                System.out.println("Incorrect guess.");
                System.out.println("Enter your first guess bewteen 1 and 6: ");
                guess1 = input.nextInt();
            }
            play.setGuess1(guess1);
            System.out.println("Enter your second guess bewteen 1 and 6: ");
            int guess2 = input.nextInt();
            while ((guess2 < 1) || (guess2 > 6)) {
                System.out.println("Incorrect guess.");
                System.out.println("Enter your second guess bewteen 1 and 6: ");
                guess2 = input.nextInt();
            }
            play.setGuess2(guess2);
        }
    }

    /**
     * Method that checks the winner based on the outcome Easy Mode
     *
     * @param outcome
     */
    public void checkWinner(int outcome) {
        //keeps count of how many winners 
        int numWinner = 0;
        //ArrayList to store the winners name
        ArrayList<Player> winner = new ArrayList<Player>();
        //ArrayList to store multiple winner
        ArrayList<Player> topwinners = new ArrayList<Player>();
        //checks to see how many people guessed correctly
        for (Player a : players) {
            if (a.getGuess() == outcome) {
                numWinner++;
                winner.add(a);
            }
        }
        //If there are no winners
        if (numWinner == 0) {
            System.out.println("\nThere are $" + pot + " in the pot for this round!\nThe outcome is " + outcome + " for this round!\n"
                    + "No winner in this round");
            //outputs the amount each player lost     
            for (Player g : players) {
                System.out.println(g.getName() + " lost $" + g.getBetAmount() + " this round");
            }
        } //If there is one winner 
        else if (numWinner == 1) {
            //Adds the pot about to the winners balance
            winner.get(0).setBalance(winner.get(0).getBalance() + pot);
            //outputs the amount in the pot and the outcome
            System.out.println("\nThere is $" + pot + " in the pot for this round!\nThe outcome is " + outcome + " for this round!");
            //outputs how much the winner won
            System.out.println(winner.get(0).getName() + " won $" + pot + " this round");
            //Sets the pot to zero
            pot = 0;
            //outputs how much the loser lost
            for (Player h : players) {
                if (h.getGuess() == outcome) {
                    continue;
                }
                System.out.println(h.getName() + " lost $" + h.getBetAmount() + " this round");
            }
        } //If there are multiple winners
        else {
            int prize = 0;
            int maxBet = 0;
            //sets the max bet
            for (Player b : players) {
                if (maxBet < b.getBetAmount()) {
                    maxBet = b.getBetAmount();
                }
            }
            //checks to see how many players had the same highest bet and adds them to topwinner ArrayList
            for (Player c : players) {
                if (maxBet == c.getBetAmount()) {
                    topwinners.add(c);
                }
            }
            //divides the pot by how many high players won and sets it to prize
            prize = pot / topwinners.size();
            //adds prize to the topwinners balance
            for (Player e : topwinners) {
                e.setBalance(e.getBalance() + prize);
            }
            //outputs how much is in the pot and the outcome
            System.out.println("\nThere is $" + pot + " in the pot for this round!\nThe outcome is " + outcome + " for this round!");
            pot = 0;
            //outputs the topwinners and how much they won
            for (int i = 0; i < topwinners.size(); i++) {
                System.out.println(topwinners.get(i).getName() + " won $" + prize + " this round");
            }
            //Outputs how much the loser lost
            for (Player t : players) {
                for (int c = 0; c < topwinners.size(); c++) {
                    if (t.getName().equals(topwinners.get(c).getName())) {
                        continue;
                    }
                }
                System.out.println(t.getName() + " lost $" + t.getBetAmount() + " this round");
            }
        }
    }

    /**
     * Method that checks the winner based on the 2 outcomes Hard Mode
     *
     * @param outcome1
     * @param outcome2
     */
    public void checkWinner(int outcome1, int outcome2) {
        int numWinner = 0;
        ArrayList<Player> winner = new ArrayList<>();
        ArrayList<Player> topwinners = new ArrayList<>();
        //finding winners
        for (Player q : players) {
            if (((q.getGuess1() == outcome1) || (q.getGuess1() == outcome2)) && ((q.getGuess2() == outcome2) || (q.getGuess2() == outcome1))) {
                winner.add(q);
                numWinner++;
            }
        }
        //If there are no winners
        if (numWinner == 0) {
            System.out.println("\nThere are $" + pot + " in the pot for this round!\nThe outcomes are " + outcome1 + " and " + outcome2 + " for this round!\n"
                    + "No winner in this round");
            //outputs the amount each player lost     
            for (Player g : players) {
                System.out.println(g.getName() + " lost $" + g.getBetAmount() + " this round");
                // g.setBalance(g.getBalance() - g.getBetAmount());
            }
        } //If there is one winner
        else if (numWinner == 1) {
            //integer to store the amount in the pot
            // int prize = pot;
            //Adds the pot about to the winners balance
            winner.get(0).setBalance(winner.get(0).getBalance() + pot);
            //outputs the amount in the pot and the outcome
            System.out.println("\nThere are $" + pot + " in the pot for this round!\nThe outcomes are " + outcome1 + " and " + outcome2 + " for this round!");
            //outputs how much the winner won
            System.out.println(winner.get(0).getName() + " won $" + pot + " this round");
            //sets pot to zero
            pot = 0;
            //outputs how much the loser lost
            for (Player h : players) {
                if (h.getName().equals(winner.get(mode))) {
                    if (((h.getGuess1() == outcome1) || (h.getGuess1() == outcome2)) && ((h.getGuess2() == outcome2) || (h.getGuess2() == outcome1))) {
                        continue;
                    }
                }
                System.out.println(h.getName() + " lost $" + h.getBetAmount() + " this round");
            }
        } //If there are multiple winners
        else {
            int maxBet = 0;
            int prize1 = 0;
            //sets the max bet
            for (Player b : players) {
                if (maxBet < b.getBetAmount()) {
                    maxBet = b.getBetAmount();
                }
            }
            //checks to see how many players had the same highest bet and adds them to topwinner ArrayList
            for (Player c : players) {
                if (maxBet == c.getBetAmount()) {
                    topwinners.add(c);
                }
            }
            //divides the pot by how many high players won and sets it to prize
            prize1 = pot / topwinners.size();
            //adds prize to the topwinners balance
            for (Player e : topwinners) {
                e.setBalance(e.getBalance() + prize1);
            }
            pot = 0;
            //outputs how much is in the pot and the outcome
            System.out.println("\nThere are $" + pot + " in the pot for this round!\nThe outcomes are " + outcome1 + " and  " + outcome2 + " for this round!");
            //outputs the topwinners and how much they won
            for (int i = 0; i < topwinners.size(); i++) {
                System.out.println(topwinners.get(i).getName() + " won $" + prize1 + " this round \n");
            }
            //Outputs how much the loser lost
            for (Player t : players) {
                for (int c = 0; c < topwinners.size(); c++) {
                    if (t.getName().equals(topwinners.get(c).getName())) {
                        continue;
                    }
                }
                System.out.println(t.getName() + " lost $" + t.getBetAmount() + " this round");
            }
        }

    }

}
