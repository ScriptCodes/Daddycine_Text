package org.example;

import java.util.Random;

public class Coinflip {
    TextUI ui = new TextUI();
    private double balance;
    Random randomNum = new Random();
    String input;
    Login login = new Login();
    Menu menu;

    public Coinflip(Login login) {

        this.login = login;
    }



    public void coinflipOptions(){
    ui.displayMsg("1.Do you want to play coinflip?"+"\n"+"2.Do you want to exit to main menu?");
        String coinflipoptions = ui.getInput("");
        switch (coinflipoptions){
            case "1":
                runCoinflip();
                break;
            case "2":
                menu.displayUserOptions();
                break;
        }
    }


    public void runCoinflip(){
        User user = login.getLoggedInUser();

        balance = user.getBalance();
        int coin = randomNum.nextInt(2);
        ui.displayMsg("You current balance: " + balance);
        while (balance > 0){
            ui.displayMsg("Please enter your desired betting amount");
           double bet = ui.getDoubleInput("");
           ui.displayMsg("Now decide between heads or tails" +"\n" + "0.Heads" + "\n" + "1.Tails" + "\n" + "2.Exit to Menu");
           int guess = ui.getIntInput("");
           if(guess == coin){
               user.Deposit(bet);
           }else {
               user.Withdraw(bet);
           }
           if (balance <=0){
               ui.displayMsg("U do not have enough money");
           } else if (guess == 2) {
               menu.displayUserOptions();

           }
            ui.displayMsg("\n"+"Your choice: " + guess);
           ui.displayMsg("It landed on: " +coin +"\n");
           ui.displayMsg("You currently have: " + user.getBalance()+ " Kr,- "+ " left on your bank account ");


        }
    }
}

