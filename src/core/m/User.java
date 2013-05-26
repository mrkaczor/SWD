/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package core.m;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import sun.security.util.Password;

/**
 *
 * @author mrkaczor
 */
public class User implements Serializable {
    private final String _username;
    private Password _password;
    private ComparisonMatrix _criterionsPrefereces;
    private Map<Criterion, ComparisonMatrix> _decisionsPreferences;
    
    public User(String username, Password password) {
        _username = username;
        _password = password;
        _criterionsPrefereces = null;
        _decisionsPreferences = new HashMap<>();
    }
    
    public String getName() {
        return _username;
    }
    
    public void setCriterionsPreferences(ComparisonMatrix preferences) {
        _criterionsPrefereces = preferences;
    }
    
    public void setDecisionsPreferences(Criterion criterion, ComparisonMatrix preferences) {
        _decisionsPreferences.put(criterion, preferences);
    }
}
