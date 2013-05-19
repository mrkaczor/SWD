/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package core.m;

import core.m.i.Element;

/**
 *
 * @author mrkaczor
 */
public class Decision implements Element {
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
