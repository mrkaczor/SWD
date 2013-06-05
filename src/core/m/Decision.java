/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package core.m;

import core.m.i.Element;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import javax.imageio.ImageIO;

/**
 *
 * @author mrkaczor
 */
public class Decision implements Element, Serializable {
    private String _name;
    private String _image;
    
    public Decision(String name) {
        _name = name;
        _image = null;
    }
    
    public Decision(String name, String imagePath) {
        _name = name;
        _image = imagePath;
    }
    
    @Override
    public String getName() {
        return _name;
    }
    
    public void setName(String name) {
        _name = name;
    }
    
    public BufferedImage getImage() throws IOException
    {
        return ImageIO.read(new File(_image));
    }
    
    public String getImagePath()
    {
        return _image;
    }
}
