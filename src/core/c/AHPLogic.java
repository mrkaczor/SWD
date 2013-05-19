package core.c;

import Jama.Matrix;
import core.m.ComparisonMatrix;

/**
 *
 * @author mrkaczor
 */
public class AHPLogic {
    // <editor-fold defaultstate="collapsed" desc="Singleton">
    public static AHPLogic getInstance()
    {
      return InstanceHolder.p_instance;
    }

    private static final class InstanceHolder
    {
      private static final AHPLogic p_instance = new AHPLogic();
    }
    // </editor-fold>
  
    private AHPLogic() {
        
    }
    
    public Matrix calculatePreferenceVector(ComparisonMatrix matrix) {
        Matrix m = normalizeMartrix(matrix.getValues());
        Matrix preferenceVector = new Matrix(m.getRowDimension(), 1);
        for(int i=0;i<preferenceVector.getRowDimension();i++) {
            double sum = 0;
            for(int j=0;j<m.getColumnDimension();j++) {
                sum += m.get(i, j);
            }
            preferenceVector.set(i, 0, sum/m.getColumnDimension());
        }
        return preferenceVector.transpose();
    }
    
    public Matrix calculateDecisionVector(Matrix criteria, Matrix[] decisions) {
        Matrix decisionVector = new Matrix(decisions[0].getColumnDimension(),1);
        for(int i=0;i<decisionVector.getRowDimension();i++) {
            double sum = 0;
            for(int j=0;j<criteria.getColumnDimension();j++) {
                sum += criteria.get(0, j)*decisions[j].get(0, i);
            }
            decisionVector.set(i, 0, sum);
        }
        return decisionVector;
    }
    
    private Matrix normalizeMartrix(Matrix m) {
        Matrix normalized = new Matrix(m.getRowDimension(), m.getColumnDimension());
        for(int i=0; i<m.getColumnDimension();i++) {
            double sum = 0.0;
            for (int j=0;j<m.getRowDimension();j++) {
                sum+=m.get(j,i);
            }
            for (int j=0;j<m.getRowDimension();j++) {
                normalized.set(j,i,m.get(j, i)/sum);
            }
        }
        return normalized;
    }
}
