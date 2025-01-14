/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package harbest;

import java.awt.Color;

/**
 *
 * @author Seimon
 */
public enum ColorPalette {
    GreenOne(new Color(126, 217, 87)),
    GreenTwo(new Color(118, 165, 86)),
    BrownOne(new Color(75, 47, 0)),
    BrownTwo(new Color(71, 34, 11)),
    GrayOne(new Color(48, 48, 48)),
    GrayTwo(new Color(176, 176, 176)),
    RedOne(new Color(201, 11, 11)),
    Hover(new Color( 243, 243, 243 ));
    private Color color;
    
    ColorPalette(Color color){
        this.color = color;
    }
    
    public Color getColor(){
        return color;
    }
}
