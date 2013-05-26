/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package core.m;

import core.m.i.Element;
import java.io.Serializable;

/**
 *
 * @author mrkaczor
 */
public class Decision implements Element, Serializable {
    private String _name;
    
    public Decision(String name) {
        _name = name;
    }
    
    @Override
    public String getName() {
        return _name;
    }
    
    public void setName(String name) {
        _name = name;
    }
}
