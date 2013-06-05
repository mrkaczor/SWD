package core.m;

import Jama.Matrix;
import core.m.i.Element;
import java.io.Serializable;

/**
 *
 * @author mrkaczor
 */
public class ComparisonMatrix implements Serializable {
    private Element[] _elements;
    private Matrix _values;
    
    public ComparisonMatrix(Element[] elements) {
        _elements = elements;
        _values = new Matrix(_elements.length, _elements.length);
        for(int i=0;i<_elements.length;i++) {
            _values.set(i, i, 1);
        }
    }
    
    public Element[] getElements() {
        return _elements;
    }
    
    public Matrix getValues() {
        return _values;
    }
    
    public void setComparisonValue(Element e1, Element e2, double value) {
        int e1id = getElementId(e1);
        int e2id = getElementId(e2);
        if(e1id!=-1 && e2id!=-1) {
            _values.set(e1id, e2id, value);
            _values.set(e2id, e1id, 1.0/value);
        }
    }
    
    private int getElementId(Element e) {
        for(int i=0;i<_elements.length;i++) {
            if(_elements[i].getName().equals(e.getName())) {
                return i;
            }
        }
        return -1;
    }
    
    @Override
    public String toString() {
        String matrix = "";
        for(int i=0;i<_values.getRowDimension();i++) {
            for(int j=0;j<_values.getColumnDimension();j++) {
                String value = Double.toString(_values.get(i, j));
                if(value.length()>4) {
                    value = value.substring(0, 4);
                }
                matrix += value+"\t";
            }
            matrix += "\n";
        }
        return matrix;
    }
}
