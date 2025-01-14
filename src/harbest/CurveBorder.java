/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package harbest;
import javax.swing.border.AbstractBorder;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.Insets;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.geom.Area;

/**
 *
 * @author Seimon
 */
public class CurveBorder extends AbstractBorder {
    private int radius;

    public CurveBorder(int radius) {
        this.radius = radius;
        
    }
    
    public int getRadius(){
        return radius;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D design = (Graphics2D) g.create();

        RoundRectangle2D createShape = new RoundRectangle2D.Float(x, y, width, height, radius, radius);
        
        Color bg = c.getParent().getBackground();
        
        Area baseArea = new Area(createShape);
        Rectangle tempRect = new Rectangle(0,0,width, height);
        Area borderRegion = new Area(tempRect);
        borderRegion.subtract(baseArea);
        design.setClip(borderRegion);
        design.setColor(bg);
        design.fillRect(0, 0, width, height);
        design.dispose();
    }
    
    @Override
    public Insets getBorderInsets(Component c){
        int insetSize = Math.min(10, radius); 
        return new Insets(insetSize, insetSize, insetSize, insetSize);
    }
    
    @Override
    public boolean isBorderOpaque(){
        return true;
    }
    
}
