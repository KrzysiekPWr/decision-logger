package Controllers;

import Models.Decision;

import java.util.List;
import java.util.function.Predicate;

public class FilterLogic {
    static List<Decision> FindAndReturnFoundDecisions(List<Decision> decisionList, Predicate<Decision> predicate){
        return decisionList
                .stream()
                .filter(predicate).toList();
    }
}
