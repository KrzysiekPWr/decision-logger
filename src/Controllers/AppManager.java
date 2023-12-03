package Controllers;

import Views.ConsoleManager;

public class AppManager {
    static void mainAppLoop(){
        ConsoleManager consoleManager = new ConsoleManager("15", "15", "60");
        DecisionManager decisionManager = new DecisionManager(consoleManager);

        decisionManager.readDecisions();

        String userInput;
        while (true){
            consoleManager.showMainMenu();
            userInput = InputProcessor.takeInput();
            switch (userInput){
                case "1":
                    decisionManager.addDecision();
                    break;
                case "2":
                    decisionManager.deleteDecision();
                    break;
                case "3":
                    decisionManager.editDecision();
                    break;
                case "4":
                    consoleManager.showDecisions(decisionManager.decisionList);
                    break;
                case "5":
                    decisionManager.findDecision();
                    break;
                case "0":
                    decisionManager.saveDecisions();
                    return;
                }
        }
    }
}
