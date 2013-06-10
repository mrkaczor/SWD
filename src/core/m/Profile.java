package core.m;

import core.c.DecisionsController;
import core.m.i.Element;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author mrkaczor
 */
public class Profile implements Serializable {
    private final String _username;
    private ComparisonMatrix _criterionsPrefereces;
    private Map<Criterion, ComparisonMatrix> _decisionsPreferences;
    private Decision[] _decisions;
    private Criterion[] _criterions;
    private String _decisionsDirectory;
                
    public Profile(String username) {
        _username = username;
        _criterionsPrefereces = null;
        _decisionsPreferences = new LinkedHashMap<>();
        _decisions = null;
        _decisionsDirectory = "images\\";
    }
    
    public String getName() {
        return _username;
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
    
    public void setDecisions(Decision[] decisions)
    {
        _decisions = decisions;
    }
    
    public Decision[] getDecisions()
    {
        return _decisions;
    }
    
    public void setDecisionsFolder(String path)
    {
        _decisionsDirectory = path;
    }
    
    public String getDecisionsFolder()
    {
        return _decisionsDirectory;
    }

    public void setCriterions(Criterion[] criterions) {
        _criterions = criterions;
    }

    public Criterion[] getCriterions() {
        return _criterions;
    }
}
