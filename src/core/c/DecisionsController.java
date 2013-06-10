/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package core.c;

import core.m.Decision;
import core.m.i.Element;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Mateusz
 */
public class DecisionsController {
    // <editor-fold defaultstate="collapsed" desc="Singleton">
    public static DecisionsController getInstance() {
      return DecisionsController.InstanceHolder.p_instance;
    }

    private static final class InstanceHolder {
      private static final DecisionsController p_instance = new DecisionsController();
    }
    // </editor-fold>
    
    public List<Decision> loadDecisions(String imagesPath)
    {
    List<Decision> decisions = new LinkedList<>();
        for (File file : new File(imagesPath).listFiles()) {
            if(file.isFile()){
                if(file.getName().toLowerCase().endsWith(".jpg")
                        || file.getName().toLowerCase().endsWith(".jpeg")
                        || file.getName().toLowerCase().endsWith(".png")){
                    decisions.add(new Decision(file.getName().substring(0, file.getName().lastIndexOf(".")), file.getPath()));
                }
            }
        }
        return decisions;
    }
}
