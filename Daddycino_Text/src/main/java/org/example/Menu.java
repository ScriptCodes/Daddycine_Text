package org.example;

import java.util.ArrayList;

public class Menu {

    private User loggedInUser;

    private ArrayList<User> users = new ArrayList<>();

    private ArrayList<User> newUsersList = new ArrayList<>();

    SlotMachine slotMachine = new SlotMachine(loggedInUser);

    TextUI ui = new TextUI();

    Login login = new Login();

    FileIO io = new FileIO();
    Roulette roulette = new Roulette(login);

    Coinflip coinflip = new Coinflip(login);

    BlackJack blackJack = new BlackJack(login);

    String uInputUsername;

    String uInputPassword;

    public Menu() {
        this.ui = new TextUI();
    }


    public void loginMenu() {
        ui.displayMsg("1. Login\n2. Create new user");
        String loginOptions = ui.getInput("");
        switch (loginOptions) {
            case "1":
                ui.displayMsg("Please type your Username and password:");
                if (login.login()) {
                    loggedInUser = login.getLoggedInUser();
                    displayUserOptions();
                }
                break;
            case "2":
                login.createUser();
                displayUserOptions();
                break;
            default:
                ui.displayMsg("None of the options was chosen, try again");
                loginMenu();
        }
    }

    public void displayUserOptions() {
        ui.displayMsg("Current Balance: $" + loggedInUser.getBalance());
        ui.displayMsg("What do you want to do?: ");
        ui.displayMsg("1.Deposit & Withdraw" + "\n" + "2.Slots Machine" + "\n" + "3.Coinflip" + "\n" + "4.Roulette Table"+"\n"+"5.Blackjack"+"\n"+"6.Exit program");

        String options = ui.getInput("");

        switch (options) {
            case "1":
                //accountMenu();
                break;
            case "2":
                slotMachine.runSlotMachine();
                break;
            case "3":
                coinflip.coinflipOptions();
                break;
            case "4":
                roulette.runRoulette();
                break;
            case "5":
                blackJack.runBlackjack();
                break;
            case "6":
                break;
            default:
                ui.displayMsg("None of the options was selected");

        }
    }
}
