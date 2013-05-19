package core.c;

import Jama.Matrix;
import core.m.ComparisonMatrix;
import core.m.Criterion;
import core.m.Decision;
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
        
        
        Element[] criterions = new Element[3];
        criterions[0] = new Criterion("Nadwozie");
        criterions[1] = new Criterion("Kolor");
        criterions[2] = new Criterion("Segment");
        ComparisonMatrix criterionsMatrix = new ComparisonMatrix(criterions);
        criterionsMatrix.setComparisonValue(criterions[1], criterions[0], 3);
        criterionsMatrix.setComparisonValue(criterions[2], criterions[0], 5);
        criterionsMatrix.setComparisonValue(criterions[2], criterions[1], 3);
        
        Element[] decisions = new Element[3];
        decisions[0] = new Decision("A1");
        decisions[1] = new Decision("A2");
        decisions[2] = new Decision("A3");
        
        //Nadwozie
        ComparisonMatrix criterion1Matrix = new ComparisonMatrix(decisions);
        criterion1Matrix.setComparisonValue(decisions[0], decisions[1], 3);
        criterion1Matrix.setComparisonValue(decisions[0], decisions[2], 5);
        criterion1Matrix.setComparisonValue(decisions[1], decisions[2], 5);
        
        //Kolor
        ComparisonMatrix criterion2Matrix = new ComparisonMatrix(decisions);
        criterion2Matrix.setComparisonValue(decisions[0], decisions[1], 9);
        criterion2Matrix.setComparisonValue(decisions[0], decisions[2], 9);
        criterion2Matrix.setComparisonValue(decisions[1], decisions[2], 1);
        
        //Segment
        ComparisonMatrix criterion3Matrix = new ComparisonMatrix(decisions);
        criterion3Matrix.setComparisonValue(decisions[1], decisions[0], 7);
        criterion3Matrix.setComparisonValue(decisions[1], decisions[2], 3);
        criterion3Matrix.setComparisonValue(decisions[2], decisions[0], 5);
        
        Matrix criterionPreference = AHPLogic.getInstance().calculatePreferenceVector(criterionsMatrix);
        Matrix[] criterionPreferences = new Matrix[3];
        criterionPreferences[0] = AHPLogic.getInstance().calculatePreferenceVector(criterion1Matrix);
        criterionPreferences[1] = AHPLogic.getInstance().calculatePreferenceVector(criterion2Matrix);
        criterionPreferences[2] = AHPLogic.getInstance().calculatePreferenceVector(criterion3Matrix);
        
        Matrix decisionPreference = AHPLogic.getInstance().calculateDecisionVector(criterionPreference, criterionPreferences);
        
        for(int i=0;i<decisionPreference.getRowDimension();i++) {
            for(int j=0;j<decisionPreference.getColumnDimension();j++) {
                System.out.print(decisionPreference.get(i, j)+"\t");
            }
            System.out.print("\n");
        }
    }
}
