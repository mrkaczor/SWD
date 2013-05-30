package core.m;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author mrkaczor
 */
public class User implements Serializable {
    private final String _username;
    private String _password;
    private ComparisonMatrix _criterionsPrefereces;
    private Map<Criterion, ComparisonMatrix> _decisionsPreferences;
    
    public User(String username, String password) {
        _username = username;
        _password = password;
        _criterionsPrefereces = null;
        _decisionsPreferences = new LinkedHashMap<>();
    }
    
    public String getName() {
        return _username;
    }
    
    public String getPassword() {
        return _password;
    }
    
    public ComparisonMatrix getCriterionsPreferences() {
        return _criterionsPrefereces;
    }
    
    public void setCriterionsPreferences(ComparisonMatrix preferences) {
        _criterionsPrefereces = preferences;
    }
    
    public Map<Criterion, ComparisonMatrix> getDecisionsPreferences() {
        return _decisionsPreferences;
    }
    
    public ComparisonMatrix getDecisionsPreferences(Criterion criterion) {
        return _decisionsPreferences.get(criterion);
    }

    public void setDecisionsPreferences(Criterion criterion, ComparisonMatrix preferences) {
        _decisionsPreferences.put(criterion, preferences);
    }
}
