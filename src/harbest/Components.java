/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package harbest;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Seimon
 */
public class Components {
    
    public static void settingFont(String font_file){
        try{
            File getFile = new File(Components.class.getResource(font_file).toURI());
            
            Font custom_font = Font.createFont(Font.TRUETYPE_FONT, getFile);
            
            GraphicsEnvironment register = GraphicsEnvironment.getLocalGraphicsEnvironment();
            register.registerFont(custom_font);
            
            System.out.println("Successfully loaded font: " + font_file);

        }
        catch(IOException | FontFormatException | URISyntaxException e){
            System.err.println("Error loading font: " + e.getMessage());
        }
    }
    
    public static Image resizeImage(String file, int width, int height){
        ImageIcon originalImage = new ImageIcon(Components.class.getResource(file));
        Image newImage = originalImage.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
        
        System.out.println("Successfully cropped image: " + file);
        
        return newImage;
    }
    
    
    public static JButton makeButton(Image image, int radius){
        ImageIcon setIcon = new ImageIcon(image);
        JButton button = new JButton(setIcon);
        button.setBorder(new CurveBorder(radius));
        
        return button;
    }
        
    public static JButton makeButton(String text, int radius){
        JButton button = new JButton(text);
        button.setBorder(new CurveBorder(radius));
        button.setFocusPainted(false);
        
        return button;
    }
    
    public static JPanel margin(int top, int left, int bottom, int right){
        JPanel marginPanel = new JPanel();
        marginPanel.setBorder(new EmptyBorder(top, left, bottom, right));
        
        return marginPanel;
    }
    
}
