package core.m;

import core.m.i.Element;
import java.io.Serializable;

/**
 *
 * @author mrkaczor
 */
public class Criterion implements Element, Serializable {
    private String _name;
    
    public Criterion(String name) {
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
