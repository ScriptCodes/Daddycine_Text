package org.example;

import DataReader.FileIO;
import Games.BlackJack;
import Games.Coinflip;
import Games.Roulette;
import Games.SlotMachine;

import java.util.ArrayList;

public class Menu {

    private User loggedInUser;

    private ArrayList<User> users = new ArrayList<>();

    private ArrayList<User> newUsersList = new ArrayList<>();


    TextUI ui = new TextUI();

    Login login = new Login();

    FileIO io = new FileIO();

    User user;
    Roulette roulette = new Roulette(login);

    Coinflip coinflip = new Coinflip(login);

    BlackJack blackJack = new BlackJack(login);

    SlotMachine slotMachine = new SlotMachine(login);




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
                loggedInUser = login.getLoggedInUser();
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
        ui.displayMsg("1.Deposit & Withdraw" + "\n" + "2.Slots Machine" + "\n" + "3.Coinflip" + "\n" + "4.Roulette Table" + "\n" + "5.Blackjack" + "\n" + "6.Exit program");

        String options = ui.getInput("");

        switch (options) {
            case "1":
                accountMenu();
                break;
            case "2":
                slotMachineMenu();
                break;
            case "3":
                coinflipMenu();
                break;
            case "4":
                rouletteMenu();
                break;
            case "5":
                blackjackMenu();
                break;
            case "6":
                users.add(loggedInUser);
                io.saveUserData(users);
                break;
            default:
                ui.displayMsg("None of the options was selected");

        }
    }

    public void accountMenu(){
        ui.displayMsg("Current Balance: $" + loggedInUser.getBalance());
        ui.displayMsg("What do you want to do?: ");
        ui.displayMsg("1.Deposit Money" + "\n" + "2.Withdraw Money" + "\n" + "3.Exit to main menu");

        String options = ui.getInput("");

        switch (options){
            case "1":
                double depositAmount = ui.getDoubleInput("Please enter your desired deposit amount: $");

                user = login.getLoggedInUser();
                user.Deposit(depositAmount);

                ui.displayMsg("You have sucessfuly deposited $" + depositAmount + " into your account!");

                accountMenu();
                break;

            case "2":
                double withdrawAmount = ui.getDoubleInput("Please enter your desired withdraw amount: $");

                user = login.getLoggedInUser();
                user.Withdraw(withdrawAmount);

                ui.displayMsg("You have sucessfuly withdrawn $" + withdrawAmount + " from your account!");

                accountMenu();
                break;

            case "3":
                ui.displayMsg("Exiting to menu...");
                displayUserOptions();
                break;

            default:
                ui.displayMsg("None of the options was selected");
        }
    }


    public void coinflipMenu(){
        ui.displayMsg("Welcome to the Coinflip game!");
        ui.displayMsg("Balance: $" + loggedInUser.getBalance());
        ui.displayMsg("What would you like to do?" + "\n" + "1.Play Coinflip?" + "\n" + "2.Exit to menu?");

        String input = ui.getInput("");
        switch (input) {
            case "1":
                coinflip.runCoinflip();
                coinflipMenu();
                break;
            case "2":
                ui.displayMsg("Exiting to menu...");
                displayUserOptions();
                break;
            default:
                ui.displayMsg("Returning to menu...");
                coinflip.runCoinflip();

        }
    }
    public void blackjackMenu() {
        ui.displayMsg("Welcome to the Blackjack game!");
        ui.displayMsg("Balance: $" + loggedInUser.getBalance());
        ui.displayMsg("What would you like to do?" + "\n" + "1.Play blackjack?" + "\n" + "2.Exit to menu?");

        String input = ui.getInput("");

        switch (input) {
            case "1":
                blackJack.runBlackjack();
                blackjackMenu();
                break;
            case "2":
                ui.displayMsg("Exiting to menu...");
                displayUserOptions();
                break;
            default:
                ui.displayMsg("Returning to menu...");
                blackJack.runBlackjack();

        }
    }

    public void rouletteMenu() {

        ui.displayMsg("Welcome to the Roulette Game!");
        ui.displayMsg("Balance: $" + loggedInUser.getBalance());
        ui.displayMsg("1.Place Bet" + "\n" + "2.Spin the wheel" + "\n" + "3.Exit to main menu");

        int choice = ui.getIntInput("Enter your choice: ");

        switch (choice) {
            case 1:
                roulette.placeBet();
                break;

            case 2:
                roulette.spinWheel();
                break;

            case 3:
                ui.displayMsg("Thanks for playing. Exiting...");
                displayUserOptions();
                break;
            default:
                ui.displayMsg("Invalid choice. Please try again!");
        }


        int replayChoice = ui.getIntInput("Do you want to play again?" + "\n" + "1.Yes" + "\n" + "2.No");

        switch (replayChoice){
            case 1:
                rouletteMenu();
                break;
            case 2:
                displayUserOptions();
                break;
            default:
                ui.displayMsg("Invalid choice. Please try again!");
        }
        if (replayChoice != 1) {
            ui.displayMsg("Thanks for playing. Exiting...");
            displayUserOptions();
        }
    }


    public void slotMachineMenu() {

        int input;
        do {// Using a do-while loop to repeatedly execute the game
            ui.displayMsg("Tokens:  " + slotMachine.getTokens());
            if (slotMachine.getTokens() <= 5) {
                ui.displayMsg("You dont have enough tokens to play, please buy more to play");
            }
            ui.displayMsg("1.Pull the lever! It costs 5 tokens per pull!" + "\n" + "2.Buy Tokens" + "\n" + "3.Cash out" + "\n" + "4.Return to menu");
            input = ui.getIntInput("");
            switch (input) {
                case 1:
                    slotMachine.playSlotMachine();
                    break;
                case 2:
                    slotMachine.buyTokens();
                    break;
                case 3:
                    slotMachine.cashoutTokens();
                    break;
                case 4:
                    displayUserOptions();
                    break;
                default:
                    ui.displayMsg("None of the options was chosen, try again.");
                    slotMachineMenu();
            }
        } while (input != 4);

    }

}