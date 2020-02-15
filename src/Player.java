/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author marco
 */
public class Player {
    private String name;
    private int balance;
    private int betAmount;
    private int guess;
    static int INITIAL_BALANCE =500;
    private int guess1;
    private int guess2;

    /**
     * 
     * @param name 
     */
    public Player(String name) {
        this.name = name;
        this.balance = INITIAL_BALANCE;
    }
    /**
     * 
     * @return name
     */
    public String getName() {
        return name;
    }
    /**
     * 
     * @return balance
     */
    public int getBalance() {
        return balance;
    }
    /**
     * 
     * @return betAmount 
     */
    public int getBetAmount() {
        return betAmount;
    }
    /**
     * 
     * @return guess
     */
    public int getGuess() {
        return guess;
    }
    /**
     * 
     * @return guess1
     */
    public int getGuess1(){
        return guess1;
    }
    /**
     * 
     * @return guess2
     */
    public int getGuess2(){
        return guess2;
    }
    /**
     * 
     * @param balance 
     */
    public void setBalance(int balance) {
        this.balance = balance;
    }
    /**
     * 
     * @param betAmount 
     */
    public void setBetAmount(int betAmount) {
        this.betAmount = betAmount;
    }
    /**
     * 
     * @param guess 
     */
    public void setGuess(int guess) {
        this.guess = guess;
    }
    /**
     * 
     * @param guess 
     */
    public void setGuess1(int guess){
        this.guess1=guess;
    }
    /**
     * 
     * @param guess 
     */
    public void setGuess2(int guess){
        this.guess2=guess;
    }
    

    
    
    
}
