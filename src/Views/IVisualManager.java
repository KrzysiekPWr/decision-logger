package Views;

import Models.Decision;

import java.util.List;
import java.util.Optional;

public interface IVisualManager {
    void showFoundDecisions(List<Decision> foundDecisions);
    void showMainMenu();
    void showDecisions(List<Decision> decisionsList);
    void showPrompts(String promptType);
    void showError();
    void showFindMenu();
    void showDecision(Decision decision);
}
