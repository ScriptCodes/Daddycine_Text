package Games;

import org.example.Login;
import org.example.TextUI;
import org.example.User;

import java.util.Random;

public class Roulette {

    TextUI ui = new TextUI();

    //Login login = new Login();
    User user;
    Login login;



    private double balance;

    public Roulette (Login login){
        this.login = login;
    }

    public void runRoulette() {

        user = login.getLoggedInUser();
        balance = user.getBalance();
    }

    public void placeBet(){

        user = login.getLoggedInUser();
        balance = user.getBalance();

        double betAmount = ui.getDoubleInput("Enter your bet amount: $");

        ui.displayMsg("Choose your bet type:" + "\n" + "1.Number bet" + "\n" + "2.Color bet" + "\n" + "3.Thirds bet");

        int betType = ui.getIntInput("Enter your bet type: ");

        switch (betType){
            case 1:
                int chosenNumber = ui.getIntInput("Enter your chosen number (0-36): ");
                processNumberBet(betAmount, chosenNumber);
                break;

            case 2:
                System.out.println("Choose color (Red/Black)");
                String chosenColor = ui.getInput("Choose color (Red/Black): ");
                processColorBet(betAmount, chosenColor);
                break;

            case 3:
                int chosenThird = ui.getIntInput("Choose third (1-12, 13-24, 25-36): ");
                processThirdsBet(betAmount, chosenThird);
                break;

            default:
                ui.displayMsg("Invalid bet type. Please try again.");
        }
    }

    private void processNumberBet(double betAmount, int chosenNumber){

        user = login.getLoggedInUser();
        balance = user.getBalance();

        Random random = new Random();
        int winningNumber = random.nextInt(37);

        ui.displayMsg("The winning number is: " + winningNumber);

        if(chosenNumber == winningNumber){
            double winnings = betAmount * 35;
            user.Deposit(winnings);
            ui.displayMsg("Congratulations! You won $" + winnings);
        } else {
            user.Withdraw(betAmount);
            ui.displayMsg("Sorry, you lost. Better luck next time!");
        }
    }

    private void processColorBet(double betAmount, String chosenColor){

        user = login.getLoggedInUser();
        balance = user.getBalance();

        Random random = new Random();
        int winningNumber = random.nextInt(37);
        String winningColor = (winningNumber == 0 || (winningNumber >= 1 && winningNumber <= 10) || (winningNumber >= 19 && winningNumber <= 28)) ? "Red" : "Black";

        ui.displayMsg("The winning number is: " + winningNumber + " (" + winningColor + ")");

        if (chosenColor.equalsIgnoreCase(winningColor)) {
            double winnings = betAmount * 1.5;
            user.Deposit(winnings);
            ui.displayMsg("Congratulations! You won $" + winnings);
        } else {
            user.Withdraw(betAmount);
            ui.displayMsg("Sorry, you lost. Better luck next time!");
        }
    }

    private void processThirdsBet(double betAmount, int chosenThird) {

        user = login.getLoggedInUser();
        balance = user.getBalance();

        Random random = new Random();
        int winningNumber = random.nextInt(37);

        ui.displayMsg("The winning number is: " + winningNumber);

        int winningThird = (winningNumber - 1) / 12 + 1;
        ui.displayMsg("Winning third: " + winningThird);

        if (chosenThird == winningThird) {
            double winnings = betAmount * 1.5;
            user.Deposit(winnings);
            ui.displayMsg("Congratulations! You won $" + winnings);
        } else {
            user.Withdraw(betAmount);
            ui.displayMsg("Sorry, you lost. Better luck next time!");
        }
    }

    public void spinWheel() {
        ui.displayMsg("Spinning the wheel...");
    }
}
