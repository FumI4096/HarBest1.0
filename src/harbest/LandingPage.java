/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package harbest;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import javax.swing.border.EmptyBorder;

/**
 *
 * @author Seimon
 */
public class LandingPage {
    private Harbest h = new Harbest();

    private final Image logoOne = Components.resizeImage("/assets/images/harbestLogo.png", 700, 700);
    private final Color buttonColor = ColorPalette.GreenOne.getColor();
    private final JPanel welcomePanel = new JPanel();
    private final JPanel imagePanel = new JPanel(new GridBagLayout());
    private final JPanel buttonPanel = new JPanel();
    private final JLabel imageDisplay = new JLabel();
    
    private JLabel welcomeText = new JLabel("Welcome To");
    private JButton orderButton = new JButton("TAKE YOUR ORDER");
    private ImageIcon image = new ImageIcon(logoOne);
    
    private void configureButton(){
        orderButton.setPreferredSize(new Dimension(400, 70));
        orderButton.setBackground(buttonColor);
        orderButton.setForeground(Color.WHITE);
        orderButton.setFont(new Font("Poppins", Font.BOLD, 20));
        orderButton.setBorder(new CurveBorder(70));
        orderButton.setFocusPainted(false);
        
        
        orderButton.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e){
                orderButton.setCursor((new Cursor(Cursor.HAND_CURSOR)));
            }
            @Override
            public void mouseExited(MouseEvent e){
                orderButton.setCursor((new Cursor(Cursor.DEFAULT_CURSOR)));
            }
        });
        

        buttonPanel.add(this.orderButton);
    }
    
    private void configureImage(){
        GridBagConstraints center = new GridBagConstraints();
        imageDisplay.setIcon(this.image);
        center.gridx = 0;
        center.gridy = 0;
        center.anchor = GridBagConstraints.CENTER;
        
        imagePanel.add(this.imageDisplay, center);
    }
    
    private void configureText(){
        this.welcomeText.setFont(new Font("Caveat Brush", Font.PLAIN, 80));
        
        welcomePanel.add(this.welcomeText);
    }
    
    public JButton getLandingButton(){
        return orderButton;
    }
    
    
    public JPanel landingPageLayout(){
        configureImage();
        configureButton();
        configureText();

        JPanel landingPanel = new JPanel(new BorderLayout());
        
        landingPanel.add(welcomePanel, BorderLayout.NORTH);
        landingPanel.add(imagePanel, BorderLayout.CENTER);
        landingPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        landingPanel.setBackground(Color.WHITE);
        welcomePanel.setBackground(welcomePanel.getParent().getBackground());
        imagePanel.setBackground(imagePanel.getParent().getBackground());
        buttonPanel.setBackground(buttonPanel.getParent().getBackground());
        landingPanel.setBorder(new EmptyBorder(90, 0, 120, 0));
        return landingPanel;
    }
}
