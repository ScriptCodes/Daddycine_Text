package org.example;

import java.util.Random;


public class SlotMachine {

    User user;
    TextUI ui = new TextUI();
    Random generator = new Random();

    double balance;

    int input;

    double currencyRate = 0.5;

    int tokens;

    int Slot1, Slot2, Slot3;

    public SlotMachine(User loggedInUser){
      this.user =loggedInUser;

}

    public void runSlotMachine() {

        do {// Using a do-while loop to repeatedly execute the game
            ui.displayMsg("Tokens:  " + tokens);
            if (tokens <= 5){
                ui.displayMsg("You dont have enough tokens to play");
            }
            ui.displayMsg("1.Go to the Slot Machine!" + "\n" + "2.Exit game" + "\n" + "3.Buy Tokens"+"\n"+"4.Cash out");
            input = ui.getIntInput("");
            switch (input) {

                case 1:
                    playSlotMachine();
                    break;
                case 2:
                    break;
                case 3:
                    buyTokens();
                    break;
                case 4:
                    cashoutTokens();
                    break;
                default:
                    ui.displayMsg("None of the options was chosen, try again.");
                    runSlotMachine();
            }
        }  while (input != 2);
    }

            private void playSlotMachine() {

                double tokensCost = 5;
                if (tokens < 5) {
                    ui.displayMsg("You don't have enough tokens to play. Buy more tokens or cash out.");
                    return;
                } else if (tokens > 5) {
                    tokens -= tokensCost;

                    // Generating random numbers for the three slots
                    Slot1 = generator.nextInt(8) + 1;
                    Slot2 = generator.nextInt(8) + 1;
                    Slot3 = generator.nextInt(8) + 1;

                    System.out.println(Slot1 + "  " + Slot2 + "  " + Slot3 + "  ");

                    // Checking the result and adjusting tokens to the user
                    if (Slot1 == Slot2 && Slot1 == Slot3) {
                        ui.displayMsg("You win 100 tokens!");
                        tokens += 100;
                    } else if (Slot1 == Slot2 || Slot1 == Slot3 || Slot2 == Slot3) {
                        ui.displayMsg("You win 50 tokens!");
                        tokens += 50;
                    } else {
                        ui.displayMsg("You lose 40 tokens!");
                        tokens -= 40;
                    }

                }

            }

        private void buyTokens(){

            ui.displayMsg("Enter the amount you want to spend to buy tokens:");
            double moneySpent = ui.getDoubleInput("");
            moneySpent -= balance;
            int tokensPurchased = (int) (moneySpent / 1.0); //1 token costs 1 unit of money
            tokens += tokensPurchased;
            ui.displayMsg("New balance: "+balance);
            ui.displayMsg("You bought " + tokensPurchased + " tokens. Your current tokens: " + tokens);
        }

        private void cashoutTokens() {


            ui.displayMsg("You have: " + tokens);
            ui.displayMsg("Enter the amount that you want to cashout: ");
            double cashoutAmount = ui.getDoubleInput("");
            if (cashoutAmount > tokens) {
                ui.displayMsg("You dont have that many tokens...");
                cashoutTokens();
            } else
                balance += cashoutAmount * 0.1; // Each token is worth 0.1 units of money
            ui.displayMsg("You now have: " + tokens);
            ui.displayMsg("Your account has been credited. Current account balance: " + balance);
            ui.displayMsg("Returning you to the menu");
            runSlotMachine();

        }
        }


































