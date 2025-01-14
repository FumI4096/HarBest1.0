/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package harbest;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Seimon
 */
public class Receipt {
    static Harbest h = new Harbest();
    private static Database db = new Database();
    public static Color defaultGray = new Color(232, 232, 232);
    public static Font typewriterFont = new Font("Courier New", Font.PLAIN, 14);
    public static int overallTotal = 0;
    private static JButton continueButton = Components.makeButton("FINISH", 50);
    
    private Object[][] data; 
    private static JPanel receiptPanel;
    private int total = 0;

    // Constructor that accepts data
    public Receipt(Object[][] data, int total) {
        this.data = data; // Store the data
        this.total = total;
        this.receiptPanel = mainPanel(); // Call mainPanel to create the UI
    }
    
    
    public JPanel mainPanel(){

        JPanel mainReceipt = new JPanel(new BorderLayout());

        JPanel paddingPanel = new JPanel();
        paddingPanel.setLayout(new BoxLayout(paddingPanel, BoxLayout.Y_AXIS));
        paddingPanel.setPreferredSize(new Dimension(900, 1080));
        paddingPanel.setBackground(Color.WHITE);
        paddingPanel.setBorder(new EmptyBorder(0, 650, 0, 650));

        JPanel top = new JPanel();
        top.setMaximumSize(new Dimension(900, 120));
        top.setLayout(new BoxLayout(top, BoxLayout.Y_AXIS));

        JLabel itemNumber = new JLabel("001");
        JLabel storeName = new JLabel("HARBEST");
        JLabel storeAddress = new JLabel("55Q8+F5J, Bucal Bypass Rd,");
        JLabel storeAddress2 = new JLabel("Calamba, 4027 Laguna");
        JLabel contact = new JLabel("Contact Us: (049) 545 5453");
        JLabel lineBreak = new JLabel("----------------------------------------");

        
        itemNumber.setFont(typewriterFont);
        storeName.setFont(typewriterFont);
        storeAddress.setFont(typewriterFont);
        storeAddress2.setFont(typewriterFont);
        contact.setFont(typewriterFont);
        lineBreak.setFont(typewriterFont);

        itemNumber.setAlignmentX(Component.CENTER_ALIGNMENT);
        storeName.setAlignmentX(Component.CENTER_ALIGNMENT);
        storeAddress.setAlignmentX(Component.CENTER_ALIGNMENT);
        storeAddress2.setAlignmentX(Component.CENTER_ALIGNMENT);
        contact.setAlignmentX(Component.CENTER_ALIGNMENT);
        lineBreak.setAlignmentX(Component.CENTER_ALIGNMENT);

        top.add(Box.createRigidArea(new Dimension(0, 10)));
        top.add(itemNumber);
        top.add(Box.createRigidArea(new Dimension(0, 10)));
        top.add(storeName);
        top.add(Box.createRigidArea(new Dimension(0, 10)));
        top.add(storeAddress);
        top.add(Box.createRigidArea(new Dimension(0, 10)));
        top.add(storeAddress2);
        top.add(Box.createRigidArea(new Dimension(0, 10)));
        top.add(contact);
        top.add(Box.createRigidArea(new Dimension(0, 20)));
        top.add(lineBreak);
        
        top.setBorder(new EmptyBorder(0, 75, 0, 75));
        top.setBackground(defaultGray);

        JPanel center = new JPanel();
        center.setBorder(new EmptyBorder(0, 20, 0, 20));
        center.setLayout(new BorderLayout());
        center.setBackground(defaultGray);

        String[] columnNames = {"Qty", "Product", "Price", "Sale", "Total"};


        JTable table = new JTable(data, columnNames);
        table.setFont(typewriterFont);
        table.setRowHeight(30);
        table.getTableHeader().setFont(typewriterFont);
        table.setFillsViewportHeight(true);
        table.setShowHorizontalLines(false);
        table.setShowVerticalLines(false);
        table.setShowGrid(false);
        table.getTableHeader().setBorder(null);
        table.setBackground(defaultGray);
        
        table.getColumnModel().getColumn(1).setPreferredWidth(500);
        table.getColumnModel().getColumn(2).setPreferredWidth(170);
        table.getColumnModel().getColumn(4).setPreferredWidth(150);
  
        table.setBorder(null);
        table.getTableHeader().setBackground(defaultGray);
        table.getTableHeader().setBorder(null);
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.GRAY, Color.GRAY));
        center.add(scrollPane, BorderLayout.CENTER);
        
        JLabel allTotal = new JLabel();
        allTotal.setText("Overall Total: ₱" + total + ".00");
        allTotal.setFont(typewriterFont);
        allTotal.setBorder(new EmptyBorder(20, 0, 15, 0));
        
        center.add(allTotal, BorderLayout.SOUTH);

        JPanel bottom = new JPanel();
        bottom.setLayout(new BoxLayout(bottom, BoxLayout.Y_AXIS));

        JLabel lineBreak2 = new JLabel("----------------------------------------");
        JLabel thankyou = new JLabel("Thank you for shopping at HARBEST!");
        JLabel slogan = new JLabel("The Best for Your Harvest");

        lineBreak2.setFont(typewriterFont);
        thankyou.setFont(typewriterFont);
        slogan.setFont(typewriterFont);

        lineBreak2.setAlignmentX(Component.CENTER_ALIGNMENT);
        thankyou.setAlignmentX(Component.CENTER_ALIGNMENT);
        slogan.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        bottom.setBorder(new EmptyBorder(0, 80, 10, 80));
        bottom.setMaximumSize(new Dimension(900, 120));
        
        bottom.add(Box.createRigidArea(new Dimension(0, 10)));
        bottom.add(lineBreak2);
        bottom.add(Box.createRigidArea(new Dimension(0, 10)));
        bottom.add(thankyou);
        bottom.add(Box.createRigidArea(new Dimension(0, 5)));
        bottom.add(slogan);
        
        bottom.setBackground(defaultGray);

        paddingPanel.add(top);
        paddingPanel.add(center);
        paddingPanel.add(bottom);
        
        JScrollPane paperScroll = new JScrollPane(paddingPanel);
        paperScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        paperScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        paperScroll.setBorder(null);
        paperScroll.getViewport().setBackground(Color.WHITE);
        
        JPanel continuePanel = new JPanel(new BorderLayout());
        continueButton.setBackground(ColorPalette.GreenOne.getColor());
        continueButton.setForeground(ColorPalette.BrownOne.getColor());
        continuePanel.add(continueButton, BorderLayout.CENTER);
        continuePanel.setBorder(new EmptyBorder(10, 730, 10, 730));
        continuePanel.setBackground(Color.WHITE);
        
        continueButton.setMaximumSize(new Dimension(100, 100));
        continueButton.setForeground(Color.WHITE);
        continueButton.setFont(new Font("Poppins", Font.BOLD, 20));
 
        
        mainReceipt.add(paperScroll, BorderLayout.CENTER);
        mainReceipt.add(continuePanel, BorderLayout.SOUTH);
        
        return mainReceipt;
    }
    
    
    public static JPanel getReceiptPanel(){
        return receiptPanel;
    }
    
    public static JButton getContinueButton(){
        return continueButton;
    }
    
    
}
