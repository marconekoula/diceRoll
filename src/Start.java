/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author marco
 */
public class Start {
   public static void main(String[] args) {
        System.out.println("Welcome to the Dice Game!");
        DiceGame dg = new DiceGame();
       
        
        int round=1;
        dg.setUpGame();
        
       while(!dg.isGameOver()){
            System.out.println("************************");
            System.out.println("         Round " + round);
            System.out.println("************************");
            System.out.println("");
            dg.playGame();
            round++;
        }
        
   }
    
}
