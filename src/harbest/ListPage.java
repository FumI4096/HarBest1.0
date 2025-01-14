/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package harbest;

import static harbest.OrderPage.db;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingUtilities;

/**
 *
 * @author Seimon
 */
public class ListPage {
    static Database db = new Database();
    
    private static boolean isDialogVisible = false;

    private static final Image deleteIcon = Components.resizeImage("/assets/images/delete-trash.png", 40, 40);
    private static final Image logoTwo = Components.resizeImage("/assets/images/logo2.png", 213,69);
    private static final JButton closeButton = Components.makeButton("Back", 10);
    private JPanel headerPanelLayout = headerLayout();
    private JPanel footerPanelLayout = footerLayout();
    public JPanel mainPanel = new JPanel(new BorderLayout());
    private static JLabel totalText = new JLabel("Overall Total: PHP ");
    private JScrollPane mainPanelLayout = mainLayout();
    
    private static JButton checkOutButton = Components.makeButton("Checkout", 50);
    private static JButton editButton = Components.makeButton("Edit", 50);
    private static JButton saveButton = Components.makeButton("Save", 50);
    private static JPanel checkButtonContainer = new JPanel(new BorderLayout(10, 0));
    
    private static EmptyBorder padding = new EmptyBorder(25, 70, 25, 70);
    private static EmptyBorder paddingSide = new EmptyBorder(0, 70, 0, 70);
    private static Color logoBackground = ColorPalette.GrayOne.getColor();
    
    private static Dimension subLayoutSize = new Dimension(0, 105);
    private static Dimension buttonLayoutSize = new Dimension(0, 105);
    private static Dimension totalLayoutSize = new Dimension(0, 60);
    
    static Harbest h = new Harbest();
    static OrderPage op = new OrderPage();
    
    private JPanel headerLayout(){
        ImageIcon image = new ImageIcon(logoTwo);
        JPanel headerPanel = new JPanel();
        JLabel iconDisplay = new JLabel(image);
        
        closeButton.setPreferredSize(new Dimension(100, 30));
        closeButton.setBackground(ColorPalette.GreenOne.getColor());
        closeButton.setFont(new Font("Poppins", Font.BOLD, 20));
        closeButton.setVerticalAlignment(SwingConstants.CENTER);
        
        closeButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                checkButtonContainer.remove(editButton);
                checkButtonContainer.add(editButton, BorderLayout.CENTER);
                
            }
        });
        JPanel logoPanel = new JPanel(new BorderLayout());
        
        logoPanel.add(iconDisplay, BorderLayout.WEST);
        logoPanel.add(closeButton, BorderLayout.EAST);
        logoPanel.setBackground(logoBackground);
        logoPanel.setBorder(padding);
        logoPanel.setPreferredSize(new Dimension(subLayoutSize));
        
        headerPanel.setLayout(new BorderLayout());
        headerPanel.add(logoPanel, BorderLayout.NORTH);

        return headerPanel;
    }
    
    private JScrollPane mainLayout(){
        JScrollPane mainScroll = new JScrollPane(mainPanel);  
        
        mainPanel.setBorder(new EmptyBorder(20, 300, 20, 300));
        mainPanel.setBackground(null);
        

        mainScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        mainScroll.setBackground(ColorPalette.Hover.getColor());
        mainScroll.getVerticalScrollBar().setUnitIncrement(30);
        
        
        return mainScroll;
    }
    
    public JPanel orderListLayout(List<String> orderName, List<String> orderCategory, List<String> orderQuantity, List<String> orderCost, List<String> orderSale, List<String> orderTotal){
        JPanel orderList = new JPanel(new GridBagLayout()); 
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;                 
        gbc.gridy = GridBagConstraints.RELATIVE; 
        gbc.anchor = GridBagConstraints.NORTH; 
        gbc.fill = GridBagConstraints.HORIZONTAL; 
        gbc.insets = new Insets(20, 0, 20, 0); 


        for (int i = 0; i < orderName.size(); i++){
            String name = orderName.get(i);
            String category = orderCategory.get(i);
            String quantity = orderQuantity.get(i);
            String cost = orderCost.get(i);
            String sale = orderSale.get(i);
            String total = orderTotal.get(i);


            orderList.add(displayOrder(name, category, quantity, cost, sale, total), gbc);
        }
        
        return orderList;
    }
    
    public JPanel displayOrder(String name, String category, String quantity, String cost, String sale, String total){
        ImageIcon deleteOrder = new ImageIcon(deleteIcon);
        JPanel order = new JPanel(new BorderLayout());
        
        JLabel iconDisplay = new JLabel(deleteOrder);
        JLabel orderName = new JLabel(name);
        JLabel orderCost = new JLabel("Cost: PHP " + cost + ".00");
        JLabel orderQuantity = new JLabel("Quantity: " + quantity);
        JLabel orderCategory = new JLabel("Category: " + category);
        JLabel orderSale = new JLabel();
        if(sale.isEmpty() || sale.equals("0")){
            orderSale.setText("");
        }
        else{
            orderSale.setText("Sale: " + sale + "%");
        }
        JLabel orderTotal = new JLabel("Total: PHP " + total + ".00");
        JButton addButton = Components.makeButton("+", 0);
        JButton minusButton = Components.makeButton("-", 0);
        
        orderName.setFont(new Font("Poppins", Font.BOLD, 27));
        orderCost.setFont(new Font("Poppins", Font.BOLD, 19));
        orderQuantity.setFont(new Font("Poppins", Font.PLAIN, 19));
        orderCategory.setFont(new Font("Poppins", Font.PLAIN, 19));
        orderSale.setFont(new Font("Poppins", Font.PLAIN, 19));
        orderSale.setForeground(ColorPalette.GreenOne.getColor());
        orderTotal.setFont(new Font("Poppins", Font.BOLD, 22));
        orderTotal.setForeground(Color.WHITE);

        JPanel orderInfoContainer = new JPanel(new GridLayout(4, 0));
        JPanel orderInfoOne = new JPanel(new BorderLayout());
        JPanel orderInfoTwo = new JPanel(new BorderLayout());
        JPanel orderInfoThree = new JPanel(new BorderLayout());
        JPanel orderInfoFour = new JPanel(new BorderLayout());
        JPanel buttonContainer = new JPanel(new GridLayout(2, 0));
        
        orderName.setBackground(Color.BLACK);
        
        orderInfoContainer.setBackground(Color.WHITE);
        
        orderInfoOne.add(orderName, BorderLayout.WEST);
        orderInfoOne.add(iconDisplay, BorderLayout.EAST);
        orderInfoTwo.add(orderCost, BorderLayout.EAST);
        orderInfoTwo.add(orderCategory, BorderLayout.WEST);
        orderInfoThree.add(orderQuantity, BorderLayout.WEST);
        orderInfoThree.add(orderSale, BorderLayout.EAST);
        orderInfoFour.add(orderTotal, BorderLayout.WEST);
        
        orderInfoContainer.add(orderInfoOne);
        orderInfoContainer.add(orderInfoTwo);
        orderInfoContainer.add(orderInfoThree);
        orderInfoContainer.add(orderInfoFour);
        
        orderInfoOne.setBackground(orderInfoOne.getParent().getBackground());
        orderInfoTwo.setBackground(orderInfoOne.getParent().getBackground());
        orderInfoThree.setBackground(orderInfoOne.getParent().getBackground());
        orderInfoFour.setBackground(ColorPalette.BrownOne.getColor());
        
        orderInfoOne.setBorder(new EmptyBorder(10, 20, 0, 20));
        orderInfoTwo.setBorder(new EmptyBorder(0, 20, 0, 20));
        orderInfoThree.setBorder(new EmptyBorder(0, 20, 0, 20));
        orderInfoFour.setBorder(new EmptyBorder(0, 20, 0, 20));

        buttonContainer.setBackground(Color.WHITE);
        
        buttonContainer.add(addButton, BorderLayout.NORTH);
        buttonContainer.add(minusButton, BorderLayout.SOUTH);
        
        addButton.setPreferredSize(new Dimension(100, 0));
        minusButton.setPreferredSize(new Dimension(100, 0));
        
        addButton.setBackground(Color.GRAY);
        minusButton.setBackground(ColorPalette.GrayTwo.getColor());
        
        addButton.setFont(new Font("Poppins", Font.BOLD, 20));
        minusButton.setFont(new Font("Poppins", Font.BOLD, 20));
        addButton.setForeground(Color.BLACK);
        minusButton.setForeground(Color.BLACK);
        
        addButton.setEnabled(false);
        minusButton.setEnabled(false);
        
        orderInfoContainer.setPreferredSize(new Dimension(1100, 301));
        
        order.add(orderInfoContainer, BorderLayout.WEST);
        order.add(buttonContainer, BorderLayout.EAST);
        
        order.setPreferredSize(new Dimension(1200, 300));
        
        iconDisplay.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                JLabel base = (JLabel) e.getSource();
                Container infoPanel = base.getParent();
                Container wholePanel = infoPanel.getParent();
                Container order = wholePanel.getParent();
                Container parent = order.getParent();
                
                Frame parentFrame = (Frame) SwingUtilities.getWindowAncestor(parent);

                String statement = "Are you sure you want to delete this item?";

                CustomDialog dialog = new CustomDialog(parentFrame, statement, name, "Yes", "No");
                dialog.setVisible(true); 
                
                if (dialog.isConfirmed()) {
                    parent.remove(order);
                    parent.revalidate();
                    parent.repaint();
                    db.deleteOrder(name);
                    totalText.setText("Overall Total: PHP " + db.getOverallOrderTotal() + ".00");
                    
                    if(!(db.checkOrder())){
                        reset();
                    }
                } 
            }

        });
        
        iconDisplay.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e){
                iconDisplay.setCursor((new Cursor(Cursor.HAND_CURSOR)));
            }
            @Override
            public void mouseExited(MouseEvent e){
                iconDisplay.setCursor((new Cursor(Cursor.DEFAULT_CURSOR)));
            }

        });
        
        int[] changeQuantity = {Integer.parseInt(quantity)};
        int[] saveNewQuantity = {0};
        
        minusButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){ 
                changeQuantity[0]--;
                if(changeQuantity[0] < 1){
                    orderQuantity.setText("Quantity: " + String.valueOf(++changeQuantity[0]));
                    int updatedCost = (Integer.parseInt(cost) * changeQuantity[0]) * (100 - Integer.parseInt(sale)) / 100;
                    orderTotal.setText("Total: PHP " + String.valueOf(updatedCost) + ".00");
                }
                else{
                    orderQuantity.setText("Quantity: " + String.valueOf(changeQuantity[0]));
                    int updatedCost = (Integer.parseInt(cost) * changeQuantity[0]) * (100 - Integer.parseInt(sale)) / 100;
                    orderTotal.setText("Total: PHP " + String.valueOf(updatedCost)+ ".00");

                }
                saveNewQuantity[0] = changeQuantity[0];
            }
        });
 
        addButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                changeQuantity[0]++;
                int updatedCost = (Integer.parseInt(cost) * changeQuantity[0]) * (100 - Integer.parseInt(sale)) / 100;
                orderTotal.setText("Total: PHP " + String.valueOf(updatedCost) + ".00");
                orderQuantity.setText("Quantity: " + String.valueOf(changeQuantity[0]));
                
                saveNewQuantity[0] = changeQuantity[0];
            }
        });
       
 
        editButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                addButton.setEnabled(true);
                minusButton.setEnabled(true);
                addButton.setBackground(ColorPalette.GreenOne.getColor());
                minusButton.setBackground(ColorPalette.RedOne.getColor());
                checkButtonContainer.remove(editButton);
                saveButton.setVisible(true);
                closeButton.setVisible(false);
                checkOutButton.setVisible(false);
                checkButtonContainer.add(saveButton, BorderLayout.CENTER);
                checkButtonContainer.repaint();
                checkButtonContainer.revalidate();
            }
        });
        
        saveButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                
                if(saveNewQuantity[0] > 0){
                    System.out.println(name);
                    System.out.println("New Quantity: " + saveNewQuantity[0]);

                    int newTotal = (Integer.parseInt(cost) * saveNewQuantity[0]) * (100 - Integer.parseInt(sale)) / 100;

                    db.updateCurrentProduct(String.valueOf(saveNewQuantity[0]), name);

                    orderTotal.setText("Total: PHP " + newTotal+ ".00");

                }
                
                addButton.setEnabled(false);
                minusButton.setEnabled(false);
                addButton.setBackground(Color.GRAY);
                minusButton.setBackground(ColorPalette.GrayTwo.getColor());
                checkOutButton.setVisible(true);
                closeButton.setVisible(true);
                checkButtonContainer.remove(saveButton);
                checkButtonContainer.add(editButton, BorderLayout.CENTER);
                checkButtonContainer.repaint();
                checkButtonContainer.revalidate();
                
                totalText.setText("Overall Total: PHP " + db.getOverallOrderTotal() + ".00");

            }
        });
        
        return order;

    }
    
    private static JPanel footerLayout(){
        JPanel footerPanel = new JPanel(new BorderLayout());
        JPanel totalPanel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel(new BorderLayout());
        
        totalText.setFont(new Font("Poppins", Font.BOLD, 24));
        totalText.setForeground(Color.WHITE);
        
        checkOutButton.setPreferredSize(new Dimension(150, 0));
        checkOutButton.setBackground(Color.WHITE);
        checkOutButton.setForeground(ColorPalette.BrownOne.getColor());
        checkOutButton.setFont(new Font("Poppins", Font.PLAIN, 18));
        
        editButton.setPreferredSize(new Dimension(100, 0));
        editButton.setBackground(ColorPalette.GrayOne.getColor());
        editButton.setForeground(ColorPalette.GreenOne.getColor());
        editButton.setFont(new Font("Poppins", Font.PLAIN, 18));
        
        saveButton.setPreferredSize(new Dimension(100, 0));
        saveButton.setBackground(Color.WHITE);
        saveButton.setForeground(ColorPalette.GreenTwo.getColor());
        saveButton.setFont(new Font("Poppins", Font.PLAIN, 18));
        
        checkOutButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                
                JButton base = (JButton) e.getSource();
                Container buttonPanel = base.getParent();
                Container footerPanel = buttonPanel.getParent();
                Container parent = footerPanel.getParent();
                
                Frame parentFrame = (Frame) SwingUtilities.getWindowAncestor(parent);
                String statement = "Proceed to Checkout?";
                if (!isDialogVisible) {
                    isDialogVisible = true;

                    CustomDialog dialog = new CustomDialog(parentFrame, statement, "", "Proceed", "No");
                    dialog.setVisible(true);
                    if (dialog.isConfirmed()) {
                        List<String> quantities = db.getCurrentOrderQuantity();
                        List<String> products = db.getCurrentOrderName();
                        List<String> prices = db.getCurrentOrderCost();
                        List<String> sales = db.getCurrentOrderSale();
                        List<String> totals = db.getCurrentOrderTotal();
                        int overallTotal = 0;
                        int rowCount = quantities.size();
                        Object[][] data = new Object[rowCount][5];

                        for (int i = 0; i < rowCount; i++) {
                            data[i][0] = quantities.get(i);
                            data[i][1] = products.get(i);
                            data[i][2] = "₱" + prices.get(i) + ".00";
                            data[i][3] = sales.get(i) + "%";
                            data[i][4] = "₱" + totals.get(i) + ".00";
                            
                            overallTotal += Integer.parseInt(totals.get(i));

                        }
                        Receipt receiptPage = new Receipt(data, overallTotal);
                        h.getMainPanel().removeAll();
                        h.getMainPanel().add(receiptPage.getReceiptPanel(), BorderLayout.CENTER);
                        h.getMainPanel().repaint();
                        h.getMainPanel().revalidate();
                    }
                    else{
                        dialog.addWindowListener(new WindowAdapter() {
                            @Override
                            public void windowClosed(WindowEvent e) {
                                isDialogVisible = false; 
                            }
                        });
                    }
                    dialog.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosed(WindowEvent e) {
                            isDialogVisible = false; 
                        }
                    });
                }
            }
        });
        
        checkButtonContainer.setBorder(new EmptyBorder(25, 0, 25, 0));
        checkButtonContainer.add(checkOutButton, BorderLayout.EAST);
        checkButtonContainer.add(editButton, BorderLayout.CENTER);
        checkButtonContainer.setBackground(null);
        
        buttonPanel.setBorder(paddingSide);
        buttonPanel.setBackground(ColorPalette.GreenOne.getColor());
        buttonPanel.setPreferredSize(buttonLayoutSize);
        buttonPanel.add(checkButtonContainer, BorderLayout.EAST);
        
        totalPanel.setBorder(paddingSide);
        totalPanel.setBackground(ColorPalette.BrownOne.getColor());
        totalPanel.setPreferredSize(totalLayoutSize);
        totalPanel.add(totalText, BorderLayout.WEST);
        
        footerPanel.add(totalPanel, BorderLayout.NORTH);
        footerPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        return footerPanel;
    }
    
    public JButton getCloseButton(){
        return closeButton;
    }
    
    public JButton getEditButton(){
        return editButton;
    }
    
    public JButton getCheckOutButton(){
        return checkOutButton;
    }
    
    public JButton getSaveButton(){
        return saveButton;
    }    
    
    public JLabel getOverallText(){
        return totalText;
    }

    public JPanel listPageLayout(){
        JPanel orderPanel = new JPanel(new BorderLayout());
        
        orderPanel.add(headerPanelLayout, BorderLayout.NORTH);
        orderPanel.add(mainPanelLayout, BorderLayout.CENTER);
        orderPanel.add(footerPanelLayout, BorderLayout.SOUTH);
        
        return orderPanel;
    }
    
    public void reset(){
        JPanel noOrderPanel = new JPanel();
        JLabel noOrderLabel = new JLabel("No Order Specified!");
        noOrderLabel.setFont(new Font("Poppins", Font.BOLD, 30));
        noOrderPanel.add(noOrderLabel);
        mainPanel.setLayout(new BorderLayout());
        mainPanel.removeAll();
        mainPanel.revalidate();
        mainPanel.repaint();
        mainPanel.add(noOrderPanel, BorderLayout.CENTER);
        checkOutButton.setVisible(false);
        editButton.setEnabled(false);
        totalText.setText("");
        op.getListButton().setEnabled(false);
        op.getCheckOutButton().setVisible(false);
        op.getListButton().setBackground(Color.GRAY);
    }
}
