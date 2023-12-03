package Controllers;

import Models.Decision;
import Views.ConsoleManager;

import java.time.LocalDate;
import java.util.List;
public class SearchManager {
    static List<Decision> searchById(List<Decision> decisionList){
        String answer = "-1";
        while(!InputValidator.isPositiveNumber(answer))
        {
            ConsoleManager.showSearchPrompts("SEARCH_ID");
            answer = InputProcessor.takeInput();
        }

        int intAnswer = Integer.parseInt(answer);

        return FilterLogic.FindAndReturnFoundDecisions(decisionList, (decision -> decision.getId() == intAnswer));
    }

    static List<Decision> searchByDate(List<Decision> decisionList){
        String answer = "";

        while(!InputValidator.isValidDateFormat(answer, DecisionManager.formatter))
        {
            ConsoleManager.showSearchPrompts("SEARCH_DATE");
            answer =  InputProcessor.takeInput();
        }

        LocalDate answerDate = LocalDate.parse(answer, DecisionManager.formatter);
        return FilterLogic.FindAndReturnFoundDecisions(decisionList, (decision -> decision.getDate().isEqual(answerDate)));
    }

    static List<Decision> searchByComponent(List<Decision> decisionList){
        String answer = "";

        while(answer.isBlank())
        {
            ConsoleManager.showSearchPrompts("SEARCH_COMPONENT");
            answer =  InputProcessor.takeInput();
        }
        String finalAnswer = answer;
        return FilterLogic.FindAndReturnFoundDecisions(decisionList, (decision -> decision.getComponent().equalsIgnoreCase(finalAnswer)));
    }

    static List<Decision> searchByComment(List<Decision> decisionList){
        String answer = "";

        while(answer.isBlank())
        {
            ConsoleManager.showSearchPrompts("SEARCH_COMMENT");
            answer = InputProcessor.takeInput();
        }
        String finalAnswer = answer;
        return FilterLogic.FindAndReturnFoundDecisions(decisionList, (decision -> decision.getComment().equalsIgnoreCase(finalAnswer)));
    }

    static List<Decision> searchByPerson(List<Decision> decisionList){
        String answer = "";

        while(answer.isBlank())
        {
            ConsoleManager.showSearchPrompts("SEARCH_PERSON");
            answer = InputProcessor.takeInput();
        }
        String finalAnswer = answer;
        return FilterLogic.FindAndReturnFoundDecisions(decisionList, (decision -> decision.getPerson().equalsIgnoreCase(finalAnswer)));
    }

    static List<Decision> searchByImportance(List<Decision> decisionList){
        String answer = "-1";
        while(!InputValidator.isPositiveNumber(answer) || !InputValidator.isNumberFrom1To10(Integer.parseInt(answer)))
        {
            ConsoleManager.showSearchPrompts("SEARCH_IMPORTANCE");
            answer =  InputProcessor.takeInput();
        }

        int intAnswer = Integer.parseInt(answer);

        return FilterLogic.FindAndReturnFoundDecisions(decisionList, (decision -> decision.getImportance() == intAnswer));
    }
}
