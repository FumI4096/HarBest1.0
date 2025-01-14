/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package harbest;
import javax.swing.JDialog;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Seimon
 */
public class CustomDialog extends JDialog{
    private boolean confirmation = false;
    
    public CustomDialog(Frame parent, String statement, String statement2, String yesStatement, String noStatement){
        super(parent, true);
        
        setUndecorated(true);
        setLayout(new GridBagLayout());
        setSize(1920, 1080);
        setLocationRelativeTo(parent); 
        setBackground(new Color(0, 0, 0, 150));
        
        JPanel dialogPanel = new JPanel(new BorderLayout());
        dialogPanel.setPreferredSize(new Dimension(600, 300));
        dialogPanel.setBackground(Color.WHITE);
        dialogPanel.setBorder(new EmptyBorder(40, 20, 40, 20));
        
        
        String alignText = "<html><div style='text-align: center;'>"
                + statement
                + " "
                + statement2
                + "</div></html>";
        
        JPanel statementPanel = new JPanel(new BorderLayout());
        statementPanel.setBackground(null);
        JLabel statementLabel = new JLabel(alignText);
        statementLabel.setFont(new Font("Poppins", Font.PLAIN, 20));
        statementLabel.setHorizontalAlignment(SwingConstants.CENTER);
        statementLabel.setBorder(new EmptyBorder(0, 10, 0, 10));

        
        statementPanel.add(statementLabel, BorderLayout.CENTER);
        dialogPanel.add(statementPanel, BorderLayout.NORTH);

        JButton yesButton = Components.makeButton(yesStatement, 50);
        JButton noButton = Components.makeButton(noStatement, 50);
        
        yesButton.setFocusPainted(false);
        noButton.setFocusPainted(false);
        
        yesButton.setPreferredSize(new Dimension(200, 50));
        noButton.setPreferredSize(new Dimension(200, 50));
        
        yesButton.setBackground(ColorPalette.GreenOne.getColor());
        noButton.setBackground(ColorPalette.BrownOne.getColor());
        
        yesButton.setForeground(Color.BLACK);
        noButton.setForeground(Color.WHITE);
        
        yesButton.setFont(new Font("Poppins", Font.PLAIN, 17));
        noButton.setFont(new Font("Poppins", Font.PLAIN, 17));

        yesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmation = true; 
                dispose(); 
            }
        });

        noButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmation = false;
                dispose();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setOpaque(false);
        buttonPanel.add(yesButton);
        buttonPanel.add(noButton);
        dialogPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; 
        gbc.gridy = 0; 
        gbc.anchor = GridBagConstraints.CENTER; 
        gbc.insets = new Insets(0, 0, 0, 0); 
        add(dialogPanel, gbc); 

        
        add(dialogPanel, gbc);
    }

    public boolean isConfirmed() {
        return confirmation;
    }
}
