/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package harbest;
import static harbest.ListPage.db;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.List;
import javax.swing.*;

/**
 *
 * @author Seimon
 */
public class Harbest {
    //page object creation
    private static final LandingPage lp = new LandingPage();
    private static final ListPage lop = new ListPage();
    private static final OrderPage op = new OrderPage();
    
    //panel page creation
    private static JPanel mainPanel = new JPanel();
    private static JPanel landingPage = lp.landingPageLayout();
    private static JPanel orderPage = op.orderPageLayout();
    private static JPanel listPage = lop.listPageLayout();
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Components.settingFont("/assets/fonts/CaveatBrush-Regular.ttf");
        Components.settingFont("/assets/fonts/Poppins-Regular.ttf");
        
        mainFrame();
    }
    
    static void mainFrame(){
        mainPanel();
        
        JFrame frame = new JFrame();
        frame.add(mainPanel);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setTitle("HarBest");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        frame.setVisible(true);
        
    }
    
    static void mainPanel(){
        mainPanel.setLayout(new BorderLayout());  
        
        mainPanel.add(landingPage, BorderLayout.CENTER);
        
        lp.getLandingButton().addActionListener(e -> {
            mainPanel.remove(landingPage);
            mainPanel.add(orderPage, BorderLayout.CENTER);
            mainPanel.revalidate();
            mainPanel.repaint();
        });
        
        lop.getCloseButton().addActionListener(e -> {
            mainPanel.remove(listPage);
            mainPanel.add(orderPage, BorderLayout.CENTER);
            mainPanel.revalidate();
            mainPanel.repaint();
        });
        
        op.getListButton().addActionListener(e ->{
            
            List<String> orderName = db.getCurrentOrderName();
            List<String> orderCategory = db.getCurrentOrderCategory();
            List<String> orderQuantity = db.getCurrentOrderQuantity();
            List<String> orderCost = db.getCurrentOrderCost();
            List<String> orderSale = db.getCurrentOrderSale();
            List<String> orderTotal = db.getCurrentOrderTotal();
            
            
            lop.orderListLayout(orderName, orderCategory, orderQuantity, orderCost, orderSale, orderTotal);
            if(db.checkOrder()){
                lop.mainPanel.removeAll();  
                lop.mainPanel.add(lop.orderListLayout(orderName, orderCategory, orderQuantity, orderCost, orderSale, orderTotal)); 
                lop.mainPanel.revalidate();
                lop.mainPanel.repaint();
                lop.getOverallText().setText("Overall Total: PHP " + db.getOverallOrderTotal() + ".00");
            }
            
            lop.getEditButton().setEnabled(true);
            lop.getCheckOutButton().setVisible(true);
            lop.getSaveButton().setVisible(false);
            
            mainPanel.remove(orderPage);
            mainPanel.add(listPage, BorderLayout.CENTER);
            mainPanel.revalidate();
            mainPanel.repaint();

        });
        
        Receipt.getContinueButton().addActionListener(e -> {
            db.deleteOrder();
            op.reset();
            lop.reset();
            mainPanel.remove(Receipt.getReceiptPanel());
            mainPanel.add(landingPage, BorderLayout.CENTER);
            mainPanel.revalidate();
            mainPanel.repaint();
        });

        mainPanel.setBackground(Color.WHITE); 
    }
    
    public JPanel getMainPanel(){
        return mainPanel;
    }
    
    public JPanel getLandingPagePanel(){
        return landingPage;
    }
    
    

    
}
