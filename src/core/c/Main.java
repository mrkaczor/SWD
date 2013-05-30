package core.c;

import Jama.Matrix;
import core.m.ComparisonMatrix;
import core.m.Criterion;
import core.m.Decision;
import core.m.User;
import core.m.exceptions.UserAuthenticationException;
import core.m.exceptions.UserRegistrationException;
import core.m.i.Element;

/**
 *
 * @author mrkaczor
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        Matrix m = new Matrix(2,4);
//        m.set(1, 2, 15);
//        System.out.println("M: "+m.getColumnDimension()+"x"+m.getRowDimension());
//        for(int i=0;i<m.getRowDimension();i++) {
//            for(int j=0;j<m.getColumnDimension();j++) {
//                System.out.print(m.get(i, j)+"\t");
//            }
//            System.out.print("\n");
//        }
        
        //signIn("Majusia", "password");
        logIn("Majusia", "password");
        
        showUser();
        calcUserChoice();
        
        UserService.getInstance().logOut();
        showUser();
    }
    
    public static void signIn(String username, String password) {
        User user = new User(username, password);
        try {
            UserService.getInstance().signIn(user);
        } catch(UserRegistrationException ex) {
            System.err.println(ex.getMessage());
        }
        
        //CRITERIONS MATRIX
        Element[] criterions = new Element[3];
        criterions[0] = new Criterion("Nadwozie");
        criterions[1] = new Criterion("Kolor");
        criterions[2] = new Criterion("Segment");
        ComparisonMatrix criterionsMatrix = new ComparisonMatrix(criterions);
        criterionsMatrix.setComparisonValue(criterions[1], criterions[0], 3);
        criterionsMatrix.setComparisonValue(criterions[2], criterions[0], 5);
        criterionsMatrix.setComparisonValue(criterions[2], criterions[1], 3);
        user.setCriterionsPreferences(criterionsMatrix);
        
        //DECISIONS
        Element[] decisions = new Element[3];
        decisions[0] = new Decision("A1");
        decisions[1] = new Decision("A2");
        decisions[2] = new Decision("A3");
        
        //DECISIONS OVER CRITERIONS PREFERENCES
        //Nadwozie
        ComparisonMatrix criterion1Matrix = new ComparisonMatrix(decisions);
        criterion1Matrix.setComparisonValue(decisions[0], decisions[1], 3);
        criterion1Matrix.setComparisonValue(decisions[0], decisions[2], 5);
        criterion1Matrix.setComparisonValue(decisions[1], decisions[2], 5);
        user.setDecisionsPreferences((Criterion)criterions[0], criterion1Matrix);
        //Kolor
        ComparisonMatrix criterion2Matrix = new ComparisonMatrix(decisions);
        criterion2Matrix.setComparisonValue(decisions[0], decisions[1], 9);
        criterion2Matrix.setComparisonValue(decisions[0], decisions[2], 9);
        criterion2Matrix.setComparisonValue(decisions[1], decisions[2], 1);
        user.setDecisionsPreferences((Criterion)criterions[1], criterion2Matrix);
        //Segment
        ComparisonMatrix criterion3Matrix = new ComparisonMatrix(decisions);
        criterion3Matrix.setComparisonValue(decisions[1], decisions[0], 7);
        criterion3Matrix.setComparisonValue(decisions[1], decisions[2], 3);
        criterion3Matrix.setComparisonValue(decisions[2], decisions[0], 5);
        user.setDecisionsPreferences((Criterion)criterions[2], criterion3Matrix);
    }
    
    public static void logIn(String username, String password) {
        try {
            UserService.getInstance().logIn(new User(username, password));
        } catch (UserAuthenticationException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public static void calcUserChoice() {
        User user = UserService.getInstance().getLoggedUser();
        if(user != null) {
            Matrix criterionPreference = AHPLogic.getInstance().calculatePreferenceVector(user.getCriterionsPreferences());
            Matrix[] criterionPreferences = new Matrix[user.getDecisionsPreferences().size()];
            int index = 0;
            for(Criterion criterion : user.getDecisionsPreferences().keySet()) {
                criterionPreferences[index++] = AHPLogic.getInstance().calculatePreferenceVector(user.getDecisionsPreferences(criterion));
            }
            Matrix decisionPreference = AHPLogic.getInstance().calculateDecisionVector(criterionPreference, criterionPreferences);

            System.out.println("USER CHOICE PREFERENCES:");
            for(int i=0;i<decisionPreference.getRowDimension();i++) {
                for(int j=0;j<decisionPreference.getColumnDimension();j++) {
                    System.out.print(decisionPreference.get(i, j)+"\t");
                }
                System.out.print("\n");
            }
            System.out.print("\n");
        }
    }
    
    public static void showUser() {
        User user = UserService.getInstance().getLoggedUser();
        if(user != null) {
            System.out.println(user.getName().toUpperCase()+" ["+user.getPassword()+"]");
            //CRITERIONS MATRIX
            System.out.println(user.getCriterionsPreferences().toString());
            //DECISIONS PREFERENCES
            for(Criterion criterion : user.getDecisionsPreferences().keySet()) {
                System.out.println(criterion.getName());
                System.out.println(user.getDecisionsPreferences(criterion).toString());
            }
        } else {
            System.out.println("BRAK ZALOGOWANEGO UŻYTKOWNIKA");
        }
    }
}
