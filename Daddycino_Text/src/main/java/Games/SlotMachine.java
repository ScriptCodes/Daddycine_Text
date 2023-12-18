package Games;

import org.example.Login;
import org.example.TextUI;
import org.example.User;


import java.util.Random;


public class SlotMachine {

    Login login;
    TextUI ui = new TextUI();
    Random generator = new Random();

    User user;
    private double balance;

    int tokens;

    int Slot1, Slot2, Slot3;

    public SlotMachine(Login login){
        this.login = login;

    }

    public void runSlotMachine() {

        user = login.getLoggedInUser();

        balance = user.getBalance();

    }

    public void playSlotMachine() {

        double tokensCost = 5;
        if (tokens < 5) {
            ui.displayMsg("You don't have enough tokens to play. Buy more tokens or cash out.");
        } else if (tokens >= 5) {
            tokens -= tokensCost;

            // Generating random numbers for the three slots
            Slot1 = generator.nextInt(9) + 1;
            Slot2 = generator.nextInt(9) + 1;
            Slot3 = generator.nextInt(9) + 1;

            System.out.println(Slot1 + "  " + Slot2 + "  " + Slot3 + "  ");

            // Checking the result and adjusting tokens to the user
            if (Slot1 == Slot2 && Slot1 == Slot3) {
                ui.displayMsg("You win 50 tokens!");
                ui.displayMsg("--------------------");
                tokens += 50;
            } else if (Slot1 == Slot2 || Slot1 == Slot3 || Slot2 == Slot3) {
                ui.displayMsg("You win 10 tokens!");
                ui.displayMsg("--------------------");
                tokens += 10;
            } else {
                ui.displayMsg("You lose 5 tokens!");
                ui.displayMsg("--------------------");
                tokens -= 5;
            }

        }

    }

    public void buyTokens() {

        user = login.getLoggedInUser();
        balance = user.getBalance();

        ui.displayMsg("Balance: "+ balance);
        ui.displayMsg("Enter the amount you want to spend to buy tokens:");
        double moneySpent = ui.getDoubleInput("");
        if (balance < moneySpent) {
            ui.displayMsg("You dont have enough on your balance");
            runSlotMachine();
        } else if (balance > moneySpent) {
            balance -= moneySpent;
            int tokensPurchased = (int) (moneySpent / 1.0); //1 token costs 1 unit of money
            tokens += tokensPurchased;
            user.Withdraw(moneySpent);
            ui.displayMsg("New balance: " + balance);
            ui.displayMsg("You bought " + tokensPurchased + " tokens. Your current tokens: " + tokens);
            runSlotMachine();
        }
    }
    public void cashoutTokens() {

        ui.displayMsg("You have: " + tokens);
        ui.displayMsg("Enter the amount that you want to cashout: ");
        double cashoutAmount = ui.getDoubleInput("");
        if (cashoutAmount > tokens) {
            ui.displayMsg("You dont have that many tokens...");
            cashoutTokens();
        } else if (cashoutAmount <= tokens)
            balance += cashoutAmount;
        ui.displayMsg("You now have: " + tokens);
        user.Deposit(cashoutAmount);
        ui.displayMsg("Your account has been credited. You cashed out: " + tokens+" Tokens"+ "\n" +"Current account balance: " + balance);
        ui.displayMsg("Returning you to the menu");
        runSlotMachine();

    }

    public int getTokens() {
        return tokens;
    }
}


































