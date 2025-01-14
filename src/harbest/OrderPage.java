/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package harbest;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BoxLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.util.List;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

/**
 *
 * @author Seimon
 */
public class OrderPage{
    static Database db = new Database();
    
    private static boolean isDialogVisible = false;
    
    private static final Image logoTwo = Components.resizeImage("/assets/images/logo2.png", 213,69);
    private static final Image cartListSymbol = Components.resizeImage("/assets/images/cart-icon.png", 37, 37);
    private static final Image informationSymbol = Components.resizeImage("/assets/images/information-symbol.png", 40, 40);
    
    private static JButton checkOutButton = Components.makeButton("Checkout", 50);
    private static JButton listPageButton = Components.makeButton(cartListSymbol, 50);
    
    private JPanel salePanel = salePanelLayout();
    private JPanel recommendationPanel = recPanelLayout();
    private JPanel dailyProductPanel = dailyProdPanelLayout();
    
    private static EmptyBorder padding = new EmptyBorder(25, 70, 25, 70);
    private static EmptyBorder paddingSide = new EmptyBorder(0, 70, 0, 70);
    private static Color logoBackground = ColorPalette.GrayOne.getColor();
    private static Color navBackground = ColorPalette.BrownOne.getColor();
    
    private static Dimension subLayoutSize = new Dimension(0, 105);

    private static JMenuBar navPanelLayout;
    private JPanel footerPanelLayout = footerLayout();
    private JPanel setMainLayout;
    private JScrollPane mainPanelLayout = mainLayout();
    
    private ImageIcon image = new ImageIcon(logoTwo);
    
    private JPanel headerPanelLayout = headerLayout();
    
    private static Harbest h = new Harbest();
    public static final ListPage lop = new ListPage();
 
    private JPanel headerLayout(){
        navPanelLayout = navLayout();
        JPanel headerPanel = new JPanel();
        JLabel iconDisplay = new JLabel(image);
        
        iconDisplay.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                mainPanelLayout.setViewportView(setMainLayout);
            }
            @Override
            public void mouseEntered(MouseEvent e){
                iconDisplay.setCursor((new Cursor(Cursor.HAND_CURSOR)));
            }
            @Override
            public void mouseExited(MouseEvent e){
                iconDisplay.setCursor((new Cursor(Cursor.DEFAULT_CURSOR)));
            }

        });
        
        JPanel logoPanel = new JPanel(new BorderLayout());
        
        logoPanel.add(iconDisplay, BorderLayout.WEST);
        logoPanel.setBackground(logoBackground);
        logoPanel.setBorder(padding);
        logoPanel.setPreferredSize(new Dimension(subLayoutSize));
        
        headerPanel.setLayout(new BorderLayout());
        headerPanel.add(logoPanel, BorderLayout.NORTH);
        headerPanel.add(navPanelLayout, BorderLayout.SOUTH); 

        return headerPanel;
    }
    
    private JMenuBar navLayout(){
        JMenuBar navMenu = new JMenuBar();
        navMenu.setLayout(new GridLayout(1, 5));
        navMenu.setBackground(navBackground);
        navMenu.setPreferredSize(new Dimension(0, 100));
        JMenu seedMenu = menuStyle("SEEDS", () -> mainPanelLayout.setViewportView(seedCategory()));
        JMenu soilMenu = menuStyle("SOILS", () -> mainPanelLayout.setViewportView(soilCategory()));
        JMenu fertMenu = menuStyle("FERTILIZERS", () -> mainPanelLayout.setViewportView(fertCategory()));
        JMenu pestMenu = menuStyle("PESTICIDES", () -> mainPanelLayout.setViewportView(pestCategory()));
        JMenu toolMenu = menuStyle("TOOLS", () -> mainPanelLayout.setViewportView(toolCategory()));
        
        navMenu.add(seedMenu);
        navMenu.add(soilMenu);
        navMenu.add(fertMenu);
        navMenu.add(pestMenu);
        navMenu.add(toolMenu);
        
        return navMenu;
    }
    
    private JMenu menuStyle(String title, Runnable action) {
        String html = String.format(
            "<html><div style='display: flex; justify-content: center; align-items: center; width: 285px'>" +
        "<div style='text-align: center; font-size: 19px; font-family: Poppins;'>%s</div></div></html>",
            title
        );
        JMenu menu = new JMenu(html);
        menu.setBackground(ColorPalette.BrownOne.getColor());
        menu.setForeground(ColorPalette.GreenOne.getColor());
        menu.setOpaque(true); 
 
        menu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                action.run(); 
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                menu.setBackground(ColorPalette.BrownTwo.getColor());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                menu.setBackground(ColorPalette.BrownOne.getColor()); 
            }
        });
        
        return menu;    
    }
    
    
    private JScrollPane mainLayout(){
        JPanel mainPanel = new JPanel(new BorderLayout(0, 25));
        JScrollPane mainScroll = new JScrollPane(mainPanel);  
        
        mainPanel.add(salePanel, BorderLayout.NORTH);
        mainPanel.add(recommendationPanel, BorderLayout.CENTER);
        mainPanel.add(dailyProductPanel, BorderLayout.SOUTH);
        mainPanel.setBorder(padding);
        mainPanel.setBackground(Color.WHITE);
        
        setMainLayout = mainPanel;

        mainScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        mainScroll.setBackground(Color.WHITE);
        mainScroll.getVerticalScrollBar().setUnitIncrement(30);
        
        
        return mainScroll;
    }
    
    private JPanel footerLayout(){
        JPanel footerPanel = new JPanel(new BorderLayout());
        JPanel buttonContainer = new JPanel(new BorderLayout(10, 0));
        
        listPageButton.setPreferredSize(new Dimension(90, 0));

        checkOutButton.setPreferredSize(new Dimension(150, 0));
        checkOutButton.setBackground(Color.WHITE);
        checkOutButton.setForeground(ColorPalette.BrownOne.getColor());
        checkOutButton.setFont(new Font("Poppins", Font.PLAIN, 18));
        
        
        if (db.checkOrder()){
            listPageButton.setEnabled(true);
            checkOutButton.setVisible(true);
            listPageButton.setBackground(ColorPalette.BrownOne.getColor());
        }
        else{
            listPageButton.setEnabled(false);
            checkOutButton.setVisible(false);
            listPageButton.setBackground(Color.GRAY);
            
        }
        
        checkOutButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){

                
                if (!isDialogVisible) {
                    JButton base = (JButton) e.getSource();
                    Container buttonPanel = base.getParent();
                    Container footerPanel = buttonPanel.getParent();
                    Container parent = footerPanel.getParent();

                    Frame parentFrame = (Frame) SwingUtilities.getWindowAncestor(parent);

                    String statement = "Proceed to Checkout?";
                    isDialogVisible = true;
                    // Show the dialog
                    CustomDialog dialog = new CustomDialog(parentFrame, statement, "", "Proceed", "No");
                    dialog.setVisible(true);
                    
                    if (dialog.isConfirmed()) {
                        
                        List<String> quantities = db.getCurrentOrderQuantity();
                        List<String> products = db.getCurrentOrderName();
                        List<String> prices = db.getCurrentOrderCost();
                        List<String> sales = db.getCurrentOrderSale();
                        List<String> totals = db.getCurrentOrderTotal();

                        int rowCount = quantities.size();
                        Object[][] data = new Object[rowCount][5];
                        int overallTotal = 0;
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
        
        buttonContainer.setBorder(new EmptyBorder(25, 0, 25, 0));
        buttonContainer.add(checkOutButton, BorderLayout.WEST);
        buttonContainer.add(listPageButton, BorderLayout.EAST);
        buttonContainer.setBackground(null);
        
        footerPanel.setBorder(paddingSide);
        footerPanel.setBackground(ColorPalette.GreenOne.getColor());
        footerPanel.setPreferredSize(subLayoutSize);
        footerPanel.add(buttonContainer, BorderLayout.EAST);
        
        return footerPanel;
    }
    
    private JPanel salePanelLayout(){
        JPanel subLayout = new JPanel(new BorderLayout());
        JPanel saleProducts = new JPanel(new GridLayout(0, 3, 20, 0));
        
        JLabel categoryText = new JLabel("Sales");
        categoryText.setFont(new Font("Poppins", Font.BOLD, 28));
        categoryText.setForeground(Color.BLACK);
        
        List<String> saleName = db.getSaleName();
        List<String> saleImage = db.getSaleImage();
        List<String> saleCategory = db.getSaleCategory();
        List<String> saleCost = db.getSaleCost();
        List<String> sale = db.getSale();
        
        for(int i = 0; i < saleName.size(); i++){
            String name = saleName.get(i);
            String category = saleCategory.get(i);
            String cost = saleCost.get(i);
            String sales = sale.get(i);
            String images = saleImage.get(i);
            
            saleProducts.add(productDisplay(name, category, cost, sales, images));
        }
        saleProducts.setBorder(null);
        saleProducts.setBackground(Color.WHITE);
        
        subLayout.add(categoryText, BorderLayout.NORTH);
        subLayout.add(saleProducts, BorderLayout.CENTER);
        
        subLayout.setBackground(Color.WHITE);
        
        salePanel = subLayout;
        
        return subLayout;
    }
    
    private JPanel recPanelLayout(){
        JPanel subLayout = new JPanel(new BorderLayout());
        JPanel saleProducts = new JPanel(new GridLayout(0, 2, 20, 0));
        
        JLabel categoryText = new JLabel("Recommendation");
        categoryText.setFont(new Font("Poppins", Font.BOLD, 28));
        categoryText.setForeground(Color.BLACK);
        
        List<String> recommendedName = db.getRecommendationName();
        List<String> recommendedCategory = db.getRecommendationCategory();
        List<String> recommendedCost = db.getRecommendationCost();
        List<String> recommendedImage = db.getRecommendationImage();
        
        for(int i = 0; i < recommendedName.size(); i++){
            String name = recommendedName.get(i);
            String category = recommendedCategory.get(i);
            String cost = recommendedCost.get(i);
            String image = recommendedImage.get(i);
            
            saleProducts.add(productDisplay(name, category, cost, "0", image));
        }

        saleProducts.setBorder(null);
        saleProducts.setBackground(Color.WHITE);
        
        subLayout.add(categoryText, BorderLayout.NORTH);
        subLayout.add(saleProducts, BorderLayout.CENTER);
        
        subLayout.setBackground(Color.WHITE);
        
        recommendationPanel = subLayout;
        
        return subLayout;
    }
        
    private JPanel dailyProdPanelLayout(){
        JPanel subLayout = new JPanel(new BorderLayout());
        JPanel saleProducts = new JPanel(new GridLayout(0, 3, 20, 20));
        
        JLabel categoryText = new JLabel("Daily Products");
        categoryText.setFont(new Font("Poppins", Font.BOLD, 28));
        categoryText.setForeground(Color.BLACK);
        
        List<String> dailyName = db.getDailyName();
        List<String> dailyCategory = db.getDailyCategory();
        List<String> dailyCost = db.getDailyCost();
        List<String> dailyImage = db.getDailyImage();
        
        for(int i = 0; i < dailyName.size(); i++){
            String name = dailyName.get(i);
            String category = dailyCategory.get(i);
            String cost = dailyCost.get(i);
            String image = dailyImage.get(i);
            
            saleProducts.add(productDisplay(name, category, cost, "0", image));
        }
        
   
        saleProducts.setBorder(null);
        saleProducts.setBackground(Color.WHITE);
        
        subLayout.add(categoryText, BorderLayout.NORTH);
        subLayout.add(saleProducts, BorderLayout.CENTER);
        
        subLayout.setBackground(Color.WHITE);
        
        dailyProductPanel = subLayout;
        
        return subLayout;
    }
    
    private JPanel seedCategory(){
        JPanel seedPanel = new JPanel(new GridLayout(0, 3, 30, 30));
        List<String> seedName = db.getSeedName();
        List<String> seedCategory = db.getSeedCategory();
        List<String> seedCost = db.getSeedCost();
        List<String> seedSale = db.getSeedSale();      
        List<String> seedImage = db.getSeedImage();
        
        for(int i = 0; i < seedName.size(); i++){
            String name = seedName.get(i);
            String category = seedCategory.get(i);
            String cost = seedCost.get(i);
            String sale = seedSale.get(i);
            String image = seedImage.get(i);
            
            seedPanel.add(productDisplay(name, category, cost, sale, image));
            
        }
        
        seedPanel.setBorder(padding);
        seedPanel.setBackground(Color.WHITE);
        
        return seedPanel;
    }
    
    private JPanel soilCategory(){
        JPanel soilPanel = new JPanel(new GridLayout(0, 3, 30, 30));
        List<String> soilName = db.getSoilName();
        List<String> soilCategory = db.getSoilCategory();
        List<String> soilCost = db.getSoilCost();
        List<String> soilSale = db.getSoilSale();      
        List<String> soilImage = db.getSoilImage();    
        
        for(int i = 0; i < soilName.size(); i++){
            String name = soilName.get(i);
            String category = soilCategory.get(i);
            String cost = soilCost.get(i);
            String sale = soilSale.get(i);
            String image = soilImage.get(i);
            
            soilPanel.add(productDisplay(name, category, cost, sale, image));
            
        }
        
        soilPanel.setBorder(padding);
        soilPanel.setBackground(Color.WHITE);
        return soilPanel;
    }
        
    private JPanel fertCategory(){
        JPanel fertPanel = new JPanel(new GridLayout(0, 3, 30, 30));
        List<String> fertName = db.getFertName();
        List<String> fertCategory = db.getFertCategory();
        List<String> fertdCost = db.getFertCost();
        List<String> fertSale = db.getFertSale();     
        List<String> fertImage = db.getFertImage();      
        
        for(int i = 0; i < fertName.size(); i++){
            String name = fertName.get(i);
            String category = fertCategory.get(i);
            String cost = fertdCost.get(i);
            String sale = fertSale.get(i);
            String image = fertImage.get(i);
            
            fertPanel.add(productDisplay(name, category, cost, sale, image));
            
        }
        
        fertPanel.setBorder(padding);
        fertPanel.setBackground(Color.WHITE);
        return fertPanel;
    }

    private JPanel pestCategory(){
        JPanel pestPanel = new JPanel(new GridLayout(0, 3, 30, 30));
        List<String> pestName = db.getPestName();
        List<String> pestCategory = db.getPestCategory();
        List<String> pestCost = db.getPestCost();
        List<String> pestSale = db.getPestSale();     
        List<String> pestImage = db.getPestImage();  
        
        for(int i = 0; i < pestName.size(); i++){
            String name = pestName.get(i);
            String category = pestCategory.get(i);
            String cost = pestCost.get(i);
            String sale = pestSale.get(i);
            String image = pestImage.get(i);
            
            pestPanel.add(productDisplay(name, category, cost, sale, image));
            
        }
        
        pestPanel.setBorder(padding);
        pestPanel.setBackground(Color.WHITE);
        return pestPanel;
    }
 
    private JPanel toolCategory(){
        JPanel toolPanel = new JPanel(new GridLayout(0, 3, 30, 30));
        List<String> toolName = db.getToolName();
        List<String> toolCategory = db.getToolCategory();
        List<String> toolCost = db.getToolCost();
        List<String> toolSale = db.getToolSale();   
        List<String> toolImage = db.getToolImage();
        
        for(int i = 0; i < toolName.size(); i++){
            String name = toolName.get(i);
            String category = toolCategory.get(i);
            String cost = toolCost.get(i);
            String sale = toolSale.get(i);
            String image = toolImage.get(i);
            
            toolPanel.add(productDisplay(name, category, cost, sale, image));
            
        }
        
        toolPanel.setBorder(padding);
        toolPanel.setBackground(Color.WHITE);
        return toolPanel;
    }    
    
    private static JPanel productDisplay(String name, String category, String cost, String sale, String croppedImage){
        Image image = Components.resizeImage(croppedImage, 270, 270);
        JPanel product = new JPanel(new BorderLayout());
        JPanel productInfo = new JPanel();
        JPanel productProcess = new JPanel(new BorderLayout());
        JPanel productQuantityPanel = new JPanel();
        JPanel confirmPanel = new JPanel();
        JPanel productQuantityInput = new JPanel(new BorderLayout());
        JPanel productOutput = new JPanel(new BorderLayout());
        JPanel costOutput = new JPanel(new BorderLayout());
        JPanel quantityOutput = new JPanel(new BorderLayout());
        
        JButton addButton = Components.makeButton("+", 12);
        JLabel quantityText = new JLabel("0");
        JLabel showQuantityCost = new JLabel("Order Cost:");
        JLabel showSuccessText = new JLabel("Order Added Successfully!");
        JLabel showUpdateQuantity = new JLabel("Current Order: ");
        showSuccessText.setVisible(false);
        JButton minusButton = Components.makeButton("-", 12);
        JButton confirmButton = Components.makeButton("Add to Cart", 12);
        JButton deleteButton = Components.makeButton("Delete Order", 12);
        
        minusButton.setBackground(ColorPalette.GreenOne.getColor());
        minusButton.setFont(new Font("Poppins", Font.BOLD, 19));
        minusButton.setForeground(Color.WHITE);
        minusButton.setPreferredSize(new Dimension(40, 40));
        
        addButton.setBackground(ColorPalette.GreenOne.getColor());
        addButton.setFont(new Font("Poppins", Font.BOLD, 19));
        addButton.setPreferredSize(new Dimension(40, 40));
        addButton.setForeground(Color.WHITE);
        
        quantityText.setFont(new Font("Poppins", Font.PLAIN, 20));
        
        showQuantityCost.setFont(new Font("Poppins", Font.PLAIN, 20));
        showQuantityCost.setForeground(Color.BLACK);
        
        showUpdateQuantity.setFont(new Font("Poppins", Font.PLAIN, 20));
        showUpdateQuantity.setForeground(Color.BLACK);
        
        showSuccessText.setFont(new Font("Poppins", Font.PLAIN, 20));
        showSuccessText.setForeground(ColorPalette.GreenTwo.getColor());
        
        confirmButton.setBackground(Color.WHITE);
        confirmButton.setPreferredSize(new Dimension(150, 40));
        confirmButton.setFont(new Font("Poppins", Font.PLAIN, 17));
        confirmButton.setForeground(Color.WHITE);        
        confirmButton.setEnabled(false);
        
        deleteButton.setBackground(Color.RED);
        deleteButton.setPreferredSize(new Dimension(150, 40));
        deleteButton.setFont(new Font("Poppins", Font.PLAIN, 17));
        deleteButton.setForeground(Color.WHITE);        
        deleteButton.setVisible(false);
        
        productQuantityPanel.add(minusButton);
        productQuantityPanel.add(Components.margin(0, 5, 0, 5));
        productQuantityPanel.add(quantityText);
        productQuantityPanel.add(Components.margin(0, 5, 0, 5));
        productQuantityPanel.add(addButton);
 
        int[] count = {0};

        confirmPanel.add(confirmButton);
        
        costOutput.add(showQuantityCost, BorderLayout.WEST);
        costOutput.add(deleteButton, BorderLayout.EAST);
        
        quantityOutput.add(showUpdateQuantity, BorderLayout.WEST);
        quantityOutput.add(showSuccessText, BorderLayout.EAST);
        
        productOutput.add(costOutput, BorderLayout.NORTH);
        productOutput.add(quantityOutput, BorderLayout.SOUTH);
        productOutput.setBorder(new EmptyBorder(10, 25, 0, 25));
        productOutput.setBackground(ColorPalette.Hover.getColor());
        costOutput.setBackground(ColorPalette.Hover.getColor());
        costOutput.setPreferredSize(new Dimension(0, 40));
        quantityOutput.setBackground(ColorPalette.Hover.getColor());
        quantityOutput.setPreferredSize(new Dimension(0, 40));
        
        productQuantityInput.add(productQuantityPanel, BorderLayout.WEST);
        productQuantityInput.add(confirmButton, BorderLayout.EAST);
        productQuantityInput.add(productOutput, BorderLayout.NORTH);
        productQuantityInput.setBorder(new EmptyBorder(0, 21, 0, 25));
        productQuantityInput.setBackground(ColorPalette.Hover.getColor());
        
        productQuantityPanel.setBackground(productQuantityPanel.getParent().getBackground());
        confirmPanel.setBackground(productQuantityPanel.getParent().getBackground());
        
        productInfo.setLayout(new BoxLayout(productInfo, BoxLayout.Y_AXIS));
        
        ImageIcon icon = new ImageIcon(image);
        
        JLabel productImage = new JLabel(icon);
        productImage.setBorder(new EmptyBorder(30, 0, 30, 0));
        JLabel productName = new JLabel(name);
        JLabel productCategory = new JLabel("Category: " + category);
        JLabel productCost = new JLabel("PHP " + cost + ".00");
        JLabel productSale = new JLabel();
        if(!(sale.isEmpty() || sale.equals("0"))){
            productSale.setText(sale + "%");
        }
        
        productName.setFont(new Font("Poppins", Font.BOLD, 20));
        productCategory.setFont(new Font("Poppins", Font.PLAIN, 18));
        productCost.setFont(new Font("Poppins", Font.PLAIN, 18));
        productSale.setFont(new Font("Poppins", Font.PLAIN, 20));
        productSale.setForeground(ColorPalette.GreenTwo.getColor());
        
        productInfo.add(productName);
        productInfo.add(productCategory);
        productInfo.add(productCost);
        productInfo.add(productSale);
        productInfo.setBorder(new EmptyBorder(0, 20, 20, 0));
        
        productProcess.add(productQuantityInput, BorderLayout.NORTH);
        productProcess.add(productOutput, BorderLayout.SOUTH);
   
        lop.getCloseButton().addActionListener(e -> {
            if(!(db.checkOrder(name))){
                count[0] = 0;
                quantityText.setText(String.valueOf(0));
                showQuantityCost.setText("Order Cost:");
                showUpdateQuantity.setText("Current Order:");
                confirmButton.setText("Add to Cart");
                confirmButton.setEnabled(false);
                confirmButton.setBackground(Color.WHITE);
                deleteButton.setVisible(false);                
            }

        });  
        
        minusButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
               count[0]--;
               if(count[0] < 0){
                   quantityText.setText(String.valueOf(++count[0]));

               }
               else if(count[0] == 0){
                   confirmButton.setBackground(Color.WHITE);
                   confirmButton.setEnabled(false);
                   quantityText.setText(String.valueOf(count[0]));
                   showQuantityCost.setText("Order Cost: " + String.valueOf("PHP " + 0 + ".00"));
               }
               else{
                   quantityText.setText(String.valueOf(count[0]));
                   int updatedCost = (Integer.parseInt(cost) * count[0]) * (100 - Integer.parseInt(sale)) / 100;
                   showQuantityCost.setText("Order Cost: " + String.valueOf("PHP " + updatedCost + ".00"));
                   
               }
            }
        });
        
        addButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                count[0]++;
                confirmButton.setBackground(ColorPalette.GreenTwo.getColor());
                confirmButton.setEnabled(true);
                int updatedCost = (Integer.parseInt(cost) * count[0]) * (100 - Integer.parseInt(sale)) / 100;
                showQuantityCost.setText("Order Cost: " + String.valueOf("PHP " + updatedCost + ".00"));
                quantityText.setText(String.valueOf(count[0]));

            } 
        });
        
        confirmButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                
                String productNameStr = productName.getText();
                String productCategoryStr = productCategory.getText().replace("Category: ", "");
                String productCostStr = productCost.getText().replace("PHP ", "");
                String productCostStr2 = productCostStr.replace(".00", "");
                String productSaleStr = productSale.getText().isEmpty() ? "0" : productSale.getText().replace("%", "");
                String quantityStr = quantityText.getText();
                
                int cost = Integer.parseInt(productCostStr2);
                int sale = Integer.parseInt(productSaleStr);
                int quantity = Integer.parseInt(quantityStr);
                
                db.insertOrUpdateProduct(productNameStr, productCategoryStr, cost, sale, quantity);
                
                showSuccessText.setVisible(true);
                
                showUpdateQuantity.setText("Current Order: " + count[0]);
                
                deleteButton.setVisible(true);
                
                if(db.isNewProduct()){
                    showSuccessText.setText("Order Added Successfully!");

                }
                else{
                    
                    showSuccessText.setText("Order Updated Successfully!");
                }
                
                confirmButton.setText("Update Order");
                
                Timer timer = new Timer(5000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        showSuccessText.setVisible(false); 
                    }
                });

                timer.setRepeats(false); 
                timer.start(); 
                
                if (db.checkOrder()){
                    listPageButton.setEnabled(true);
                    checkOutButton.setVisible(true);
                    listPageButton.setBackground(ColorPalette.BrownOne.getColor());
                }
                else{
                    listPageButton.setEnabled(false);
                    checkOutButton.setVisible(false);
                    listPageButton.setBackground(Color.GRAY);

                }
                
            }
        });
        
        deleteButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                db.deleteOrder(productName.getText());
                count[0] = 0;
                quantityText.setText(String.valueOf(count[0]));
                showQuantityCost.setText("Order Cost: 0");
                showUpdateQuantity.setText("Current Order: 0");
                confirmButton.setText("Add to Cart");
                confirmButton.setBackground(Color.WHITE);
                confirmButton.setEnabled(false);
                showSuccessText.setText("Order Deleted!");
                
                Timer timer = new Timer(5000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e){
                        showSuccessText.setVisible(false); 
                    }
                });

                timer.setRepeats(false); 
                timer.start(); 
                
                deleteButton.setVisible(false);
                
                if (db.checkOrder()){
                    listPageButton.setEnabled(true);
                    checkOutButton.setVisible(true);
                    listPageButton.setBackground(ColorPalette.BrownOne.getColor());
                }
                else{
                    listPageButton.setEnabled(false);
                    checkOutButton.setVisible(false);
                    listPageButton.setBackground(Color.GRAY);
                }
                
            }            
        });
        
        product.setBackground(ColorPalette.Hover.getColor());
        productInfo.setBackground(ColorPalette.Hover.getColor());
       
        product.add(productImage, BorderLayout.NORTH);
        product.add(productInfo, BorderLayout.CENTER);
        product.add(productProcess, BorderLayout.SOUTH);
        product.setBorder(new EmptyBorder(0, 0, 20, 0));


        return product;
    }
    
    public JButton getListButton(){
        return listPageButton;
    }
    
    public JButton getCheckOutButton(){
        return checkOutButton;
    }
    
    public void reset(){
        setMainLayout.removeAll();
        setMainLayout.repaint();
        setMainLayout.revalidate();
        
        salePanel = salePanelLayout();
        recommendationPanel = recPanelLayout();
        dailyProductPanel = dailyProdPanelLayout();
                    
        setMainLayout.add(salePanel, BorderLayout.NORTH);
        setMainLayout.add(recommendationPanel, BorderLayout.CENTER);
        setMainLayout.add(dailyProductPanel, BorderLayout.SOUTH);
        
        mainPanelLayout.setViewportView(setMainLayout);
        
        setMainLayout.repaint();
        setMainLayout.revalidate();
        
    }
    
    
    public JPanel orderPageLayout(){
        JPanel orderPanel = new JPanel(new BorderLayout());
        
        
        
        orderPanel.add(headerPanelLayout, BorderLayout.NORTH);
        orderPanel.add(mainPanelLayout, BorderLayout.CENTER);
        orderPanel.add(footerPanelLayout, BorderLayout.SOUTH);
        
        return orderPanel;
    }

}
