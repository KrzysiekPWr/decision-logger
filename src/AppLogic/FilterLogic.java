package AppLogic;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;

public class FilterLogic {
    static void filterTest(List<Decision> decisionList, Predicate<Decision> predicate){
        List<Decision> foundDecisions = decisionList
                .stream()
                .filter(predicate).toList();
        if (foundDecisions.isEmpty()) System.out.println("No elements found");
        else
        {
            System.out.println("Found " + foundDecisions.size() + " elements:");
            foundDecisions.forEach((System.out::println));
        }
        System.out.println("\n\n\n");
    }
}
