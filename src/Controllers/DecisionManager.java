package Controllers;

import Models.Decision;
import Views.ConsoleManager;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DecisionManager {
    static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    private final ConsoleManager consoleManager;
    List<Decision> decisionList = new ArrayList<>();

    DecisionManager(ConsoleManager consoleManager){
        this.consoleManager = consoleManager;
    }


    boolean addDecision() {
        int importance = -1;
        String person = "", component = "", comment = "", dateString = "", importanceString;
        LocalDate date;

        consoleManager.showPrompts("ASK_DECISION");

        while(!InputValidator.isValidDateFormat(dateString, formatter))
        {
            consoleManager.showPrompts("ASK_DATE");
            dateString = InputProcessor.takeInput();
            if(dateString.equalsIgnoreCase("C")) return false;
        }
        date = LocalDate.parse(dateString, formatter);

        while(component.isBlank() || !InputValidator.isValidLength(component, consoleManager.getComponentFieldSize()))
        {
            consoleManager.showPrompts("ASK_COMPONENT");
            component = InputProcessor.takeInput();
            if(component.equalsIgnoreCase("C")) return false;
        }

        while(person.isBlank() || !InputValidator.isValidLength(person, consoleManager.getPersonFieldSize()))
        {
            consoleManager.showPrompts("ASK_PERSON");
            person = InputProcessor.takeInput();
            if(person.equalsIgnoreCase("C")) return false;
        }

        while (!InputValidator.isNumberFrom1To10(importance))
        {
            consoleManager.showPrompts("ASK_IMPORTANCE");
            importanceString = InputProcessor.takeInput();
            if(dateString.equalsIgnoreCase("C")) return false;
            else if(InputValidator.isPositiveNumber(importanceString)) importance = Integer.parseInt(importanceString);
        }

        while(comment.isBlank() || !InputValidator.isValidLength(comment, consoleManager.getPersonFieldSize()))
        {
            consoleManager.showPrompts("ASK_COMMENT");
            comment = InputProcessor.takeInput();
            if(comment.equalsIgnoreCase("C")) return false;
        }

        System.out.println("\n\n\n");

        decisionList.add(new Decision(decisionList.size(), date, component, person, importance, comment));
        return true;
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
            consoleManager.showError();
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
            consoleManager.showError();
        }
        catch (ClassNotFoundException cnfe)
        {
            //this is normal behaviour. Can't go out of loop in other way
        }
    }

    void deleteDecision(){

        String deletionIdString = "";
        int deletionId;

        while(!InputValidator.isPositiveNumber(deletionIdString))
        {
            consoleManager.showPrompts("ASK_DELETE_ID");
            deletionIdString = InputProcessor.takeInput();
            if(deletionIdString.equalsIgnoreCase("C")) return;
        }
        deletionId = Integer.parseInt(deletionIdString);

        if (!InputValidator.doesDecisionIdExist(deletionId, decisionList))
        {
            consoleManager.showPrompts("NO_ELEMENT_FOUND");
        }
        else
        {
            consoleManager.showPrompts("DELETE_CONFIRM");
            List<Decision> decisionToDelete = new ArrayList<>((FilterLogic.FindAndReturnFoundDecisions(decisionList,
                    (decision -> decision.getId() == deletionId))));
            consoleManager.showDecision(decisionToDelete.get(0));
            while(true)
            {
            String answer = InputProcessor.takeInput();
                if (answer.equalsIgnoreCase("Y"))
                {

                    decisionList.remove(decisionToDelete.get(0));
                    consoleManager.showPrompts("SUCCESSFUL_DELETION");
                    return;
                }
                else if (answer.equalsIgnoreCase("N")) return;
            }
        }
    }

    void findDecision(){
        String answer;
        while(true)
        {
            consoleManager.showFindMenu();
            answer = InputProcessor.takeInput();
            switch (answer)
            {
                case "1":
                    consoleManager.showFoundDecisions(SearchManager.searchById(decisionList));
                    return;
                case "2":
                    consoleManager.showFoundDecisions(SearchManager.searchByDate(decisionList));
                    return;
                case "3":
                    consoleManager.showFoundDecisions(SearchManager.searchByComponent(decisionList));
                    return;
                case "4":
                    consoleManager.showFoundDecisions(SearchManager.searchByPerson(decisionList));
                    return;
                case "5":
                    consoleManager.showFoundDecisions(SearchManager.searchByImportance(decisionList));
                    return;
                case "6":
                    consoleManager.showFoundDecisions(SearchManager.searchByComment(decisionList));
                    return;
                case "0":
                    return;
            }
        }
    }

    public void editDecision() {
        String decisionIdString = "-1";
        int decisionId = -1;
        List<Decision> decisions;
        Decision foundDecision;
        while(!InputValidator.isPositiveNumber(decisionIdString)) {
            ConsoleManager.showEditPrompts("EDIT_ID");
            decisionIdString = InputProcessor.takeInput();
            if(InputValidator.isPositiveNumber(decisionIdString)) decisionId = Integer.parseInt(decisionIdString);
        }
        int finalDecisionId = decisionId;
        decisions = decisionList.stream().filter(dec -> dec.getId() == finalDecisionId).toList();

        if(decisions.isEmpty())
        {
            ConsoleManager.showEditPrompts("EDIT_NO_ELEMENT");
            return;
        }
        foundDecision = decisions.get(0);

        ConsoleManager.showEditPrompts("EDIT_FOUND");
        consoleManager.showDecision(foundDecision);

        ConsoleManager.showEditPrompts("EDIT_NEW_DECISION");
        boolean isNewDecisionAdded = addDecision();
        if(isNewDecisionAdded) decisionList.remove(foundDecision);
        }
    }
