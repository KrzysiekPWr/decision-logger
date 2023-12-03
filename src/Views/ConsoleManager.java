package Views;

import Models.Decision;

import java.util.List;
import java.util.Map;

public class ConsoleManager implements IVisualManager {

    public String getComponentFieldSize() {
        return componentFieldSize;
    }

    public String getPersonFieldSize() {
        return personFieldSize;
    }

//    public String getCommentFieldSize() {
//        return commentFieldSize;
//    }
    private String componentFieldSize = "15";
    private String personFieldSize = "15";
    private String commentFieldSize = "40";

    public ConsoleManager(String componentFieldSize, String personFieldSize, String commentFieldSize){
        this.componentFieldSize = componentFieldSize;
        this.personFieldSize = personFieldSize;
        this.commentFieldSize = commentFieldSize;
    }

    @Override
    public void showFoundDecisions(List<Decision> foundDecisions) {
        if (foundDecisions.isEmpty()) System.out.println("No elements found");
        else
        {
            System.out.println("Found " + foundDecisions.size() + " elements:");
            foundDecisions.forEach((System.out::println));
        }
        System.out.println("\n\n\n");
    }

    @Override
    public void showMainMenu() {
        System.out.println("""
                MAIN MENU
                1. Add decision entry.
                2. Delete decision entry.
                3. Edit decision entry.
                4. Show all decisions.
                5. Search for decision entry.
                0. Save and exit.
                """);
    }

    @Override
    public void showFindMenu(){
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
    }

    @Override
    public void showDecisions(List<Decision> decisionList) {
        if(decisionList.isEmpty())
        {
            System.out.println("\nThere are no decisions to show\n");
            return;
        }

        String maxIdOfDecisionList = String.valueOf(decisionList.getLast().getId());
        int sizeOfIdSpace = maxIdOfDecisionList.length();

        String idFormat = "%" + sizeOfIdSpace + "d";
        String idHeaderFormat = "%" + sizeOfIdSpace + "s";

        System.out.printf("|" + idHeaderFormat + "|%11s | %"+ componentFieldSize +"s | %"+ personFieldSize +"s " +
                        "| %10s | %"+ commentFieldSize +"s|", "Id", "Date", "Component", "Person",
                "Importance", "Comment");
        for (Decision decision: decisionList)
        {
            System.out.printf("\n|"+ idFormat + " |%11s | %"+ componentFieldSize +"s | %"+ personFieldSize +"s " +
                            "| %10d | %"+ commentFieldSize +"s|", decision.getId(), decision.getDate(), decision.getComponent(),
                    decision.getPerson(), decision.getImportance(), decision.getComment());
        }
        System.out.println("\n\n\n");
    }
    @Override
    public void showError() {
        System.out.println("Something went wrong");
    }

    @Override
    public void showPrompts(String promptType){
        final Map<String,String> prompts = Map.of(
                "ASK_DECISION", "Specify the decision entry (C+Enter to cancel): \n",
                "ASK_DATE", "Specify the date (YYYY/MM/DD): ",
                "ASK_COMPONENT", "Specify the component ("+ componentFieldSize + " letters max): ",
                "ASK_PERSON", "Specify the person ("+ personFieldSize + " letters max): ",
                "ASK_IMPORTANCE", "Specify the importance (1-10): ",
                "ASK_COMMENT", "Specify the comment ("+ commentFieldSize + " letters max): ",
                "ASK_DELETE_ID", "Specify the index of the decision you want to delete (C+Enter to cancel): ",
                "DELETE_CONFIRM", "Are you sure you want to delete this element (Y/N): ",
                "SUCCESSFUL_DELETION", "Entry deleted successfully\n\n\n",
                "NO_ELEMENT_FOUND", "No such element found\n\n\n"
        );
        System.out.print(prompts.get(promptType));
    }
    static public void showSearchPrompts(String promptType){
        final Map<String, String> searchPrompts = Map.of(
                "SEARCH_ID", "Specify the id you want to search for: ",
                "SEARCH_DATE", "Specify the date you want to search for (YYYY/MM/DD): ",
                "SEARCH_COMPONENT", "Specify the component you want to search for: ",
                "SEARCH_PERSON", "Specify the person you want to search for: ",
                "SEARCH_IMPORTANCE", "Specify the importance you want to search for: ",
                "SEARCH_COMMENT", "Specify the comment you want to search for: "
        );
        System.out.print(searchPrompts.get(promptType));
    }

    static public void showEditPrompts(String promptType){
        final Map<String, String> searchPrompts = Map.of(
                "EDIT_ID", "Specify id of the decision you want to edit: ",
                "EDIT_FOUND", "\nYour selected decision is: ",
                "EDIT_NEW_DECISION", "\n\nSpecify new decision below\n",
                "EDIT_NO_ELEMENT", "No such element found\n\n\n"
        );
        System.out.print(searchPrompts.get(promptType));
    }

    @Override
    public void showDecision(Decision decision){
        System.out.println(decision.toString());
    }

}
