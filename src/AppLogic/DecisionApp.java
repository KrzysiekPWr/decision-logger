package AppLogic;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DecisionApp {
    String componentFieldSize = "15";
    String personFieldSize = "15";
    String commentFieldSize = "40";
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    List<Decision> decisionList = new ArrayList<>();

    public DecisionApp(String componentFieldSize, String personFieldSize, String commentFieldSize){
        this.componentFieldSize = componentFieldSize;
        this.personFieldSize = personFieldSize;
        this.commentFieldSize = commentFieldSize;
    }

    void addDecision() {
        int importance = -1;
        String person = "", component = "", comment, dateString = "", importanceString;
        LocalDate date;

        Scanner decisionScanner = new Scanner(System.in);
        System.out.println("Specify the decision entry (C+Enter to cancel): ");

        while(!InputValidator.isValidDateFormat(dateString, formatter))
        {
            System.out.print("Specify the date (YYYY/MM/DD): ");
            dateString = decisionScanner.nextLine();
            if(dateString.equalsIgnoreCase("C")) return;
        }
        date = LocalDate.parse(dateString, formatter);

        while(component.isBlank())
        {
            System.out.print("Specify the component: ");
            component = decisionScanner.nextLine();
            if(component.equalsIgnoreCase("C")) return;
        }

        while(person.isBlank())
        {
            System.out.print("Specify the person: ");
            person = decisionScanner.nextLine();
            if(person.equalsIgnoreCase("C")) return;
        }

        while (!InputValidator.isNumberFrom1To10(importance))
        {
            System.out.print("Specify the importance (1-10): ");
            importanceString = decisionScanner.next();
            if(dateString.equalsIgnoreCase("C")) return;
            else if(InputValidator.isPositiveNumber(importanceString)) importance = Integer.parseInt(importanceString);
        }

        decisionScanner.nextLine(); //to flush the input

        System.out.print("Specify the comment: ");
        comment = decisionScanner.nextLine();
        if(comment.equalsIgnoreCase("C")) return;

        System.out.println("\n\n\n");

        decisionList.add(new Decision(decisionList.size(), date, component, person, importance, comment));
    }

    void showDecisions() {
        if(decisionList.isEmpty())
        {
            System.out.println("\nThere are no decisions to show\n");
            return;
        }

        String maxIdOfDecisionList = String.valueOf(decisionList.getLast().id);
        int sizeOfIdSpace = maxIdOfDecisionList.length();

        String idFormat = "%" + sizeOfIdSpace + "d";
        String idHeaderFormat = "%" + sizeOfIdSpace + "s";

        System.out.printf("|" + idHeaderFormat + "|%11s | %"+ componentFieldSize +"s | %"+ personFieldSize +"s " +
                        "| %10s | %"+ commentFieldSize +"s|", "Id", "Date", "Component", "Person",
                        "Importance", "Comment");
        for (Decision decision: decisionList)
        {
            System.out.printf("\n|"+ idFormat + " |%11s | %"+ componentFieldSize +"s | %"+ personFieldSize +"s " +
                            "| %10d | %"+ commentFieldSize +"s|", decision.id, decision.date, decision.component,
                            decision.Person, decision.importance, decision.comment);
        }
        System.out.println("\n\n\n");
    }

    void saveDecisions(){
        try
        {
            FileOutputStream fileOut = new FileOutputStream("decisions.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(decisionList);
            out.close();
            fileOut.close();
        }
        catch (IOException ioe)
        {
            System.out.println("Something went wrong");
        }
    }

    void readDecisions(){
        File decisionsFile = new File("decisions.ser");
        if (!decisionsFile.exists()) return;

        try
        {
            FileInputStream fileIn = new FileInputStream("decisions.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);

            decisionList = (List<Decision>)in.readObject();

            in.close();
            fileIn.close();
        }
        catch (IOException ioe)
        {
            System.out.println("Something went wrong");
        }
        catch (ClassNotFoundException cnfe)
        {
            //this is normal behaviour. Can't go out of loop in other way
        }
    }

    void deleteDecision(){

        Scanner deletionScanner = new Scanner(System.in);
        String deletionIdString = "";
        int deletionId;

        while(!InputValidator.isPositiveNumber(deletionIdString))
        {
            System.out.print("Specify the index of the decision you want to delete (C+Enter to cancel): ");
            deletionIdString = deletionScanner.nextLine();
            if(deletionIdString.equalsIgnoreCase("C")) return;
        }
        deletionId = Integer.parseInt(deletionIdString);

        if (deletionId >= decisionList.size() || deletionId < 0)
        {
            System.out.println("No such element found\n\n\n");
        }
        else
        {
            System.out.println("Are you sure you want to delete this element (Y/N):");
            System.out.println(decisionList.get(deletionId).toString());
            while(true)
            {
            String answer = deletionScanner.next();
                if (answer.equalsIgnoreCase("Y"))
                {
                    decisionList.remove(deletionId);
                    System.out.println("Entry deleted successfully\n\n\n");
                    return;
                }
                else if (answer.equalsIgnoreCase("N")) return;
            }
        }
    }

    void findDecision(){
        Scanner findScanner = new Scanner(System.in);
        while(true)
        {
            System.out.println("""
                    Specify what element you want to search by:
                    1. Id
                    2. Date
                    3. Component
                    4. Person
                    5. Importance
                    6. Comment
                    0. Cancel
                    """);
            String answer = findScanner.next();
            findScanner.nextLine(); //flush the scanner
            switch (answer)
            {
                case "1":
                    SearchManager.searchById(decisionList, findScanner);
                    return;
                case "2":
                    SearchManager.searchByDate(decisionList, findScanner);
                    return;
                case "3":
                    SearchManager.searchByComponent(decisionList, findScanner, "Specify the component " +
                            "you want to search for: ");
                    return;
                case "4":
                    SearchManager.searchByPerson(decisionList, findScanner, "Specify the person " +
                            "you want to search for: ");
                    return;
                case "5":
                    SearchManager.searchByImportance(decisionList, findScanner);
                    return;
                case "6":
                    SearchManager.searchByComment(decisionList, findScanner, "Specify the comment " +
                            "you want to search for: ");
                    return;
                case "0":
                    return;
            }
        }
    }

}
