package AppLogic;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class SearchManager {

    static void searchById(List<Decision> decisionList, Scanner findScanner){

        String answer = "-1";
        while(!InputValidator.isPositiveNumber(answer))
        {
            System.out.print("Specify the Id you want to search for: ");
            answer = findScanner.next();
        }
        System.out.println();

        int intAnswer = Integer.parseInt(answer);

        FilterLogic.filterTest(decisionList, (decision -> decision.id == intAnswer));

    }

    static void searchByDate(List<Decision> decisionList, Scanner findScanner){
        String answer = "";

        while(!InputValidator.isValidDateFormat(answer, DecisionApp.formatter))
        {
            System.out.print("Specify the date you want to search for (YYYY/MM/DD): ");
            answer = findScanner.nextLine();
        }
        System.out.println();

        LocalDate answerDate = LocalDate.parse(answer, DecisionApp.formatter);
        FilterLogic.filterTest(decisionList, (decision -> decision.date.isEqual(answerDate)));
    }
    static void searchByComponent(List<Decision> decisionList, Scanner findScanner, String printText){
        String answer = "";

        while(answer.isBlank())
        {
            System.out.print(printText);
            answer = findScanner.nextLine();
        }
        String finalAnswer = answer;
        FilterLogic.filterTest(decisionList, (decision -> decision.component.equalsIgnoreCase(finalAnswer)));
    }

    static void searchByComment(List<Decision> decisionList, Scanner findScanner, String printText){
        String answer = "";

        while(answer.isBlank())
        {
            System.out.print(printText);
            answer = findScanner.nextLine();
        }
        String finalAnswer = answer;
        FilterLogic.filterTest(decisionList, (decision -> decision.comment.equalsIgnoreCase(finalAnswer)));
    }

    static void searchByPerson(List<Decision> decisionList, Scanner findScanner, String printText){
        String answer = "";

        while(answer.isBlank())
        {
            System.out.print(printText);
            answer = findScanner.nextLine();
        }
        String finalAnswer = answer;
        FilterLogic.filterTest(decisionList, (decision -> decision.Person.equalsIgnoreCase(finalAnswer)));
    }

    static void searchByImportance(List<Decision> decisionList, Scanner findScanner){
        String answer = "-1";
        while(!InputValidator.isPositiveNumber(answer) || !InputValidator.isNumberFrom1To10(Integer.parseInt(answer)))
        {
            System.out.print("Specify the importance you want to search for: ");
            answer = findScanner.next();
        }
        System.out.println();

        int intAnswer = Integer.parseInt(answer);

        FilterLogic.filterTest(decisionList, (decision -> decision.importance == intAnswer));
    }
}
