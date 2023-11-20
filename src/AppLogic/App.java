package AppLogic;

import java.util.Scanner;

public class App {
    static void mainAppLoop(){
        DecisionApp decisionApp = new DecisionApp("15", "15", "60");

        Scanner scanner = new Scanner(System.in);
        //CLEAR CONSOLE - NEED TO CHECK IF IT WORKS
//        try {
//            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
//        }
//        catch (InterruptedException ie){
//            System.out.println("Thread has been interrupted");
//            return;
//        }
//        catch (IOException ioe){
//            System.out.println("Couldn't start operating system process");
//            return;
//        }

        decisionApp.readDecisions();

        while (true){
        System.out.println("""
                MAIN MENU
                1. Add decision entry.
                2. Delete decision entry.
                3. Show all decisions.
                4. Search for decision entry.
                0. Save and exit.
                """);


        switch (scanner.next()){
            case "1":
                decisionApp.addDecision();
                break;
            case "2":
                decisionApp.deleteDecision();
                break;
            case "3":
                decisionApp.showDecisions();
                break;
            case "4":
                decisionApp.findDecision();
                break;
            case "0":
                decisionApp.saveDecisions();
                return;
            }
        }
    }
}
