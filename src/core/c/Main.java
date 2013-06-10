package core.c;

import Jama.Matrix;
import core.m.Criterion;
import core.m.Profile;
import core.v.MainWindow;

/**
 *
 * @author mrkaczor
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MainWindow.openWindow();
    }

    public static Matrix calcUserChoice() {
        Profile user = ProfilesService.getInstance().getCurrentProfile();
        if (user != null) {
            Matrix criterionPreference = AHPLogic.getInstance().calculatePreferenceVector(user.getCriterionsPreferences());
            Matrix[] criterionPreferences = new Matrix[user.getDecisionsPreferences().size()];
            int index = 0;
            for (Criterion criterion : user.getDecisionsPreferences().keySet()) {
                criterionPreferences[index++] = AHPLogic.getInstance().calculatePreferenceVector(user.getDecisionsPreferences(criterion));
            }
            Matrix decisionPreference = AHPLogic.getInstance().calculateDecisionVector(criterionPreference, criterionPreferences);

            System.out.println("USER CHOICE PREFERENCES:");
            for (int i = 0; i < decisionPreference.getRowDimension(); i++) {
                for (int j = 0; j < decisionPreference.getColumnDimension(); j++) {
                    System.out.print(decisionPreference.get(i, j) + "\t");
                }
                System.out.print("\n");
            }
            System.out.print("\n");
            
            return decisionPreference;
        }
        
        return null;
    }

    public static void showUser() {
        Profile user = ProfilesService.getInstance().getCurrentProfile();
        if (user != null) {
            System.out.println(user.getName().toUpperCase());
            //CRITERIONS MATRIX
            if (user.getCriterionsPreferences() != null) {
                System.out.println(user.getCriterionsPreferences().toString());
            }
            //DECISIONS PREFERENCES
            if (user.getDecisionsPreferences() != null) {
                for (Criterion criterion : user.getDecisionsPreferences().keySet()) {
                    System.out.println(criterion.getName());
                    System.out.println(user.getDecisionsPreferences(criterion).toString());
                }
            }
        } else {
            System.out.println("BRAK ZALOGOWANEGO UŻYTKOWNIKA");
        }
    }
}
