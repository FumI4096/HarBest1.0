/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package harbest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Seimon
 */
public class Database {
    private Connection con;
    private boolean flag;
    private boolean checkOrder;
    
    public Database() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/harbest", "root", "08_02_2004");
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Harbest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Harbest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Connection getConnection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/harbest", "root", "08_02_2004");
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            System.out.println("couldn't connect!");
            throw new RuntimeException(ex);
        }
    }
    
    public List<String> getRecommendationName(){
        String query = "SELECT P_Name FROM Products WHERE P_Recommendation = TRUE;";
        List<String> recommendedProducts = new ArrayList<>();
        
        try (PreparedStatement stmt = con.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()){
             
            while (rs.next()) {
                String productName = rs.getString("P_Name");
                recommendedProducts.add(productName);
            }
            
        } 
        catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return recommendedProducts;
    }
    
    public List<String> getRecommendationCategory(){
        String query = "SELECT C_Name FROM Products LEFT JOIN category on products.P_Category = category.C_ID WHERE P_Recommendation = TRUE;";
        List<String> recommendedCategory = new ArrayList<>();
        
        try (PreparedStatement stmt = con.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()){
             
            while (rs.next()){
                String productCategory = rs.getString("C_Name");
                recommendedCategory.add(productCategory);
            }
            
        } 
        catch (SQLException ex){
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return recommendedCategory;
    }
        
    public List<String> getRecommendationCost(){
        String query = "SELECT P_Cost FROM Products WHERE P_Recommendation = TRUE;";
        List<String> recommendedCosts = new ArrayList<>();
        
        try (PreparedStatement stmt = con.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()){
             
            while (rs.next()){
                String productCost = rs.getString("P_Cost");
                recommendedCosts.add(productCost);
            }
            
        } 
        catch (SQLException ex){
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return recommendedCosts;
    }
    
        public List<String> getRecommendationImage(){
        String query = "SELECT P_Image FROM Products WHERE P_Recommendation = TRUE;";
        List<String> recommendedImages = new ArrayList<>();
        
        try (PreparedStatement stmt = con.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()){
             
            while (rs.next()){
                String productImage = rs.getString("P_Image");
                recommendedImages.add(productImage);
            }
            
        } 
        catch (SQLException ex){
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return recommendedImages;
    }
    
    public List<String> getSaleName(){
        String query = "SELECT P_Name FROM Products WHERE P_Sale > 0;";
        List<String> saleNames = new ArrayList<>();
        
        try (PreparedStatement stmt = con.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()){
             
            while (rs.next()){
                String productName = rs.getString("P_Name");
                saleNames.add(productName);
            }
            
        } 
        catch (SQLException ex){
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return saleNames;
    }
        
    public List<String> getSaleCost(){
        String query = "SELECT P_Cost FROM Products WHERE P_Sale > 0;";
        List<String> saleCosts = new ArrayList<>();
        
        try (PreparedStatement stmt = con.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()){
             
            while (rs.next()){
                String productCost = rs.getString("P_Cost");
                saleCosts.add(productCost);
            }
            
        } 
        catch (SQLException ex){
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return saleCosts;
    }
    
    public List<String> getSaleCategory(){
        String query = "SELECT C_Name FROM Products LEFT JOIN category on products.P_Category = category.C_ID WHERE P_Sale > 0;";
        List<String> saleCategory = new ArrayList<>();
        
        try (PreparedStatement stmt = con.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()){
             
            while (rs.next()){
                String productCategory = rs.getString("C_Name");
                saleCategory.add(productCategory);
            }
            
        } 
        catch (SQLException ex){
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return saleCategory;
    }
    
    public List<String> getSale(){
        String query = "SELECT P_Sale FROM Products WHERE P_Sale > 0;";
        List<String> saleCategory = new ArrayList<>();
        
        try (PreparedStatement stmt = con.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()){
             
            while (rs.next()){
                String productCategory = rs.getString("P_Sale");
                saleCategory.add(productCategory);
            }
            
        } 
        catch (SQLException ex){
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return saleCategory;
    }
    
    public List<String> getSaleImage(){
        String query = "SELECT P_Image FROM Products WHERE P_Sale > 0;";
        List<String> saleImages = new ArrayList<>();
        
        try (PreparedStatement stmt = con.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()){
             
            while (rs.next()){
                String productImage = rs.getString("P_Image");
                saleImages.add(productImage);
            }
            
        } 
        catch (SQLException ex){
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return saleImages;
    }
    
    
    public List<String> getDailyName(){
        String query = "SELECT P_Name FROM Products ORDER BY P_ID DESC LIMIT 12";
        List<String> dailyNames = new ArrayList<>();
        
        try (PreparedStatement stmt = con.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()){
             
            while (rs.next()){
                String productName = rs.getString("P_Name");
                dailyNames.add(productName);
            }
            
        } 
        catch (SQLException ex){
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return dailyNames;
    }

    public List<String> getDailyCategory(){
        String query = "SELECT C_Name FROM Products LEFT JOIN category on products.P_Category = category.C_ID ORDER BY P_ID DESC LIMIT 12;";
        List<String> dailyCategory = new ArrayList<>();
        
        try (PreparedStatement stmt = con.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()){
             
            while (rs.next()){
                String productCategory = rs.getString("C_Name");
                dailyCategory.add(productCategory);
            }
            
        } 
        catch (SQLException ex){
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return dailyCategory;
    }    
    
    public List<String> getDailyCost(){
        String query = "SELECT P_Cost FROM Products ORDER BY P_ID DESC LIMIT 12;";
        List<String> dailyCosts = new ArrayList<>();
        
        try (PreparedStatement stmt = con.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()){
             
            while (rs.next()){
                String productCost = rs.getString("P_Cost");
                dailyCosts.add(productCost);
            }
            
        } 
        catch (SQLException ex){
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return dailyCosts;
    }
    
    public List<String> getDailyImage(){
        String query = "SELECT P_Image FROM Products ORDER BY P_ID DESC LIMIT 12;";
        List<String> dailyImages = new ArrayList<>();
        
        try (PreparedStatement stmt = con.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()){
             
            while (rs.next()){
                String productImage = rs.getString("P_Image");
                dailyImages.add(productImage);
            }
            
        } 
        catch (SQLException ex){
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return dailyImages;
    }
    
    public List<String> getSeedName(){
        String query = "SELECT P_Name FROM Products WHERE P_Category = 1 ORDER BY P_Sale DESC;";
        List<String> seedName = new ArrayList<>();
        
        try (PreparedStatement stmt = con.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()){
             
            while (rs.next()){
                String productName = rs.getString("P_Name");
                seedName.add(productName);
            }
            
        } 
        catch (SQLException ex){
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return seedName;
    }
    
    public List<String> getSeedCategory(){
        String query = "SELECT C_Name FROM Products LEFT JOIN category on products.P_Category = category.C_ID WHERE P_Category = 1 ORDER BY P_Sale DESC;";
        List<String> seedCategory = new ArrayList<>();
        
        try (PreparedStatement stmt = con.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()){
             
            while (rs.next()){
                String productCategory = rs.getString("C_Name");
                seedCategory.add(productCategory);
            }
            
        } 
        catch (SQLException ex){
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return seedCategory;
    }
        
    public List<String> getSeedCost(){
        String query = "SELECT P_Cost FROM Products WHERE P_Category = 1 ORDER BY P_Sale DESC;";
        List<String> seedCost = new ArrayList<>();
        
        try (PreparedStatement stmt = con.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()){
             
            while (rs.next()){
                String productCost = rs.getString("P_Cost");
                seedCost.add(productCost);
            }
            
        } 
        catch (SQLException ex){
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return seedCost;
    }
        
    public List<String> getSeedSale(){
        String query = "SELECT P_Sale FROM Products WHERE P_Category = 1 ORDER BY P_Sale DESC;";
        List<String> seedSale = new ArrayList<>();
        
        try (PreparedStatement stmt = con.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()){
             
            while (rs.next()){
                String productSale = rs.getString("P_Sale");
                seedSale.add(productSale);
            }
            
        } 
        catch (SQLException ex){
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return seedSale;
    }
    
    public List<String> getSeedImage(){
        String query = "SELECT P_Image FROM Products WHERE P_Category = 1 ORDER BY P_Sale DESC;";
        List<String> seedImages = new ArrayList<>();
        
        try (PreparedStatement stmt = con.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()){
             
            while (rs.next()){
                String productImage = rs.getString("P_Image");
                seedImages.add(productImage);
            }
            
        } 
        catch (SQLException ex){
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return seedImages;
    }
    
    public List<String> getSoilName(){
        String query = "SELECT P_Name FROM Products WHERE P_Category = 2 ORDER BY P_Sale DESC;";
        List<String> soilName = new ArrayList<>();
        
        try (PreparedStatement stmt = con.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()){
             
            while (rs.next()){
                String productName = rs.getString("P_Name");
                soilName.add(productName);
            }
            
        } 
        catch (SQLException ex){
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return soilName;
    }
    
    public List<String> getSoilCategory(){
        String query = "SELECT C_Name FROM Products LEFT JOIN category on products.P_Category = category.C_ID WHERE P_Category = 2 ORDER BY P_Sale DESC;";
        List<String> soilCategory = new ArrayList<>();
        
        try (PreparedStatement stmt = con.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()){
             
            while (rs.next()){
                String productCategory = rs.getString("C_Name");
                soilCategory.add(productCategory);
            }
            
        } 
        catch (SQLException ex){
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return soilCategory;
    }
        
    public List<String> getSoilCost(){
        String query = "SELECT P_Cost FROM Products WHERE P_Category = 2 ORDER BY P_Sale DESC;";
        List<String> soilCost = new ArrayList<>();
        
        try (PreparedStatement stmt = con.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()){
             
            while (rs.next()){
                String productCost = rs.getString("P_Cost");
                soilCost.add(productCost);
            }
            
        } 
        catch (SQLException ex){
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return soilCost;
    }
        
        public List<String> getSoilSale(){
        String query = "SELECT P_Sale FROM Products WHERE P_Category = 2 ORDER BY P_Sale DESC;";
        List<String> soilSale = new ArrayList<>();
        
        try (PreparedStatement stmt = con.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()){
             
            while (rs.next()){
                String productSale = rs.getString("P_Sale");
                soilSale.add(productSale);
            }
            
        } 
        catch (SQLException ex){
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return soilSale;
    }
        
    public List<String> getSoilImage(){
        String query = "SELECT P_Image FROM Products WHERE P_Category = 2 ORDER BY P_Sale DESC;";
        List<String> soilImages = new ArrayList<>();
        
        try (PreparedStatement stmt = con.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()){
             
            while (rs.next()){
                String productImage = rs.getString("P_Image");
                soilImages.add(productImage);
            }
            
        } 
        catch (SQLException ex){
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return soilImages;
    }
        
    public List<String> getFertName(){
        String query = "SELECT P_Name FROM Products WHERE P_Category = 3 ORDER BY P_Sale DESC;";
        List<String> fertName = new ArrayList<>();
        
        try (PreparedStatement stmt = con.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()){
             
            while (rs.next()){
                String productName = rs.getString("P_Name");
                fertName.add(productName);
            }
            
        } 
        catch (SQLException ex){
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return fertName;
    }
    
    public List<String> getFertCategory(){
        String query = "SELECT C_Name FROM Products LEFT JOIN category on products.P_Category = category.C_ID WHERE P_Category = 3 ORDER BY P_Sale DESC;";
        List<String> fertCategory = new ArrayList<>();
        
        try (PreparedStatement stmt = con.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()){
             
            while (rs.next()){
                String productCategory = rs.getString("C_Name");
                fertCategory.add(productCategory);
            }
            
        } 
        catch (SQLException ex){
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return fertCategory;
    }
        
    public List<String> getFertCost(){
        String query = "SELECT P_Cost FROM Products WHERE P_Category = 3 ORDER BY P_Sale DESC;";
        List<String> fertCost = new ArrayList<>();
        
        try (PreparedStatement stmt = con.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()){
             
            while (rs.next()){
                String productCost = rs.getString("P_Cost");
                fertCost.add(productCost);
            }
            
        } 
        catch (SQLException ex){
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return fertCost;
    }
        
    public List<String> getFertSale(){
        String query = "SELECT P_Sale FROM Products WHERE P_Category = 3 ORDER BY P_Sale DESC;";
        List<String> fertSale = new ArrayList<>();
        
        try (PreparedStatement stmt = con.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()){
             
            while (rs.next()){
                String productSale = rs.getString("P_Sale");
                fertSale.add(productSale);
            }
            
        } 
        catch (SQLException ex){
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return fertSale;
    }
    
    public List<String> getFertImage(){
        String query = "SELECT P_Image FROM Products WHERE P_Category = 3 ORDER BY P_Sale DESC;";
        List<String> fertImages = new ArrayList<>();
        
        try (PreparedStatement stmt = con.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()){
             
            while (rs.next()){
                String productImage = rs.getString("P_Image");
                fertImages.add(productImage);
            }
            
        } 
        catch (SQLException ex){
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return fertImages;
    }
    
    public List<String> getPestName(){
        String query = "SELECT P_Name FROM Products WHERE P_Category = 4 ORDER BY P_Sale DESC;";
        List<String> pestName = new ArrayList<>();
        
        try (PreparedStatement stmt = con.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()){
             
            while (rs.next()){
                String productName = rs.getString("P_Name");
                pestName.add(productName);
            }
            
        } 
        catch (SQLException ex){
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return pestName;
    }
    
    public List<String> getPestCategory(){
        String query = "SELECT C_Name FROM Products LEFT JOIN category on products.P_Category = category.C_ID WHERE P_Category = 4 ORDER BY P_Sale DESC;";
        List<String> pestCategory = new ArrayList<>();
        
        try (PreparedStatement stmt = con.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()){
             
            while (rs.next()){
                String productCategory = rs.getString("C_Name");
                pestCategory.add(productCategory);
            }
            
        } 
        catch (SQLException ex){
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return pestCategory;
    }
        
    public List<String> getPestCost(){
        String query = "SELECT P_Cost FROM Products WHERE P_Category = 4 ORDER BY P_Sale DESC;";
        List<String> pestCost = new ArrayList<>();
        
        try (PreparedStatement stmt = con.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()){
             
            while (rs.next()){
                String productCost = rs.getString("P_Cost");
                pestCost.add(productCost);
            }
            
        } 
        catch (SQLException ex){
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return pestCost;
    }
        
    public List<String> getPestSale(){
        String query = "SELECT P_Sale FROM Products WHERE P_Category = 4 ORDER BY P_Sale DESC;";
        List<String> pestSale = new ArrayList<>();
        
        try (PreparedStatement stmt = con.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()){
             
            while (rs.next()){
                String productSale = rs.getString("P_Sale");
                pestSale.add(productSale);
            }
            
        } 
        catch (SQLException ex){
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return pestSale;
    }   
    
    public List<String> getPestImage(){
        String query = "SELECT P_Image FROM Products WHERE P_Category = 4 ORDER BY P_Sale DESC;";
        List<String> pestImages = new ArrayList<>();
        
        try (PreparedStatement stmt = con.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()){
             
            while (rs.next()){
                String productImage = rs.getString("P_Image");
                pestImages.add(productImage);
            }
            
        } 
        catch (SQLException ex){
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return pestImages;
    }

    public List<String> getToolName(){
        String query = "SELECT P_Name FROM Products WHERE P_Category = 5 ORDER BY P_Sale DESC;";
        List<String> toolName = new ArrayList<>();
        
        try (PreparedStatement stmt = con.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()){
             
            while (rs.next()){
                String productName = rs.getString("P_Name");
                toolName.add(productName);
            }
            
        } 
        catch (SQLException ex){
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return toolName;
    }
    
    public List<String> getToolCategory(){
        String query = "SELECT C_Name FROM Products LEFT JOIN category on products.P_Category = category.C_ID WHERE P_Category = 5 ORDER BY P_Sale DESC;";
        List<String> toolCategory = new ArrayList<>();
        
        try (PreparedStatement stmt = con.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()){
             
            while (rs.next()){
                String productCategory = rs.getString("C_Name");
                toolCategory.add(productCategory);
            }
            
        } 
        catch (SQLException ex){
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return toolCategory;
    }
        
    public List<String> getToolCost(){
        String query = "SELECT P_Cost FROM Products WHERE P_Category = 5 ORDER BY P_Sale DESC;";
        List<String> toolCost = new ArrayList<>();
        
        try (PreparedStatement stmt = con.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()){
             
            while (rs.next()){
                String productCost = rs.getString("P_Cost");
                toolCost.add(productCost);
            }
            
        } 
        catch (SQLException ex){
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return toolCost;
    }
        
    public List<String> getToolSale(){
        String query = "SELECT P_Sale FROM Products WHERE P_Category = 5 ORDER BY P_Sale DESC;";
        List<String> toolSale = new ArrayList<>();
        
        try (PreparedStatement stmt = con.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()){
             
            while (rs.next()){
                String productSale = rs.getString("P_Sale");
                toolSale.add(productSale);
            }
            
        } 
        catch (SQLException ex){
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return toolSale;
    }
    
    public List<String> getToolImage(){
        String query = "SELECT P_Image FROM Products WHERE P_Category = 5 ORDER BY P_Sale DESC;";
        List<String> toolImages = new ArrayList<>();
        
        try (PreparedStatement stmt = con.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()){
             
            while (rs.next()){
                String productImage = rs.getString("P_Image");
                toolImages.add(productImage);
            }
            
        } 
        catch (SQLException ex){
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return toolImages;
    }
    
    public void insertOrUpdateProduct(String name, String category, int cost, int sale, int quantity) {
        String checkQuery = "SELECT COUNT(*) FROM current_order WHERE CO_Product_Name = ? AND CO_Category = ?";
        
        String updateQuery = "UPDATE current_order SET CO_Product_Quantity = ? WHERE CO_Product_Name = ? AND CO_Category = ?";

        String insertQuery = "INSERT INTO current_order (CO_Product_Name, CO_Category, CO_Product_Cost, CO_Product_Sale, CO_Product_Quantity) VALUES (?, ?, ?, ?, ?)";
        
        Connection conn = getConnection();
        try (PreparedStatement checkStmt = conn.prepareStatement(checkQuery)) {
            checkStmt.setString(1, name);
            checkStmt.setString(2, category);
            ResultSet rs = checkStmt.executeQuery();
            rs.next();
            int count = rs.getInt(1);

            if(count > 0){
                checkOrder = true;
                try (PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {
                    updateStmt.setInt(1, quantity);  
                    updateStmt.setString(2, name);
                    updateStmt.setString(3, category);

                    int rowsUpdated = updateStmt.executeUpdate();
                    if (rowsUpdated > 0) {
                        flag = false;
                    }
                }
            } 
            else{
                checkOrder = false;
                try (PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
                    insertStmt.setString(1, name);
                    insertStmt.setString(2, category);
                    insertStmt.setDouble(3, cost);
                    insertStmt.setDouble(4, sale);
                    insertStmt.setInt(5, quantity);

                    int rowsInserted = insertStmt.executeUpdate();
                    if (rowsInserted > 0) {
                        System.out.println("Product inserted successfully!");
                        flag = true;
                    }
                }
            }

        } 
        catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void deleteOrder(String name){        
        String deleteQuery = "DELETE FROM current_order WHERE CO_Product_Name = ?";
        Connection conn = getConnection();
        try (PreparedStatement deleteStmt = conn.prepareStatement(deleteQuery)) {
            deleteStmt.setString(1, name);
            int rowDeleted = deleteStmt.executeUpdate();
            System.out.println(rowDeleted + "This order deleted successfully");
        } 
        catch (SQLException ex) {
            ex.printStackTrace();
        }        
    }
    
    public void deleteOrder(){        
        String deleteQuery = "DELETE FROM current_order";
        Connection conn = getConnection();
        try (PreparedStatement deleteStmt = conn.prepareStatement(deleteQuery)) {
            deleteStmt.executeUpdate();
        } 
        catch (SQLException ex) {
            ex.printStackTrace();
        }        
    }
    
    public boolean checkOrder(){
        String checkQuery = "SELECT COUNT(*) FROM current_order";
        
        Connection conn = getConnection();
        try (PreparedStatement checkStmt = conn.prepareStatement(checkQuery)) {
            ResultSet rs = checkStmt.executeQuery();
            rs.next();
            int count = rs.getInt(1);

            if(count > 0){
                checkOrder = true;
            } 
            else{
                checkOrder = false;
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return checkOrder;
    }
    
    public boolean checkOrder(String name){
        boolean order = false;
        String checkQuery = "SELECT COUNT(*) FROM current_order WHERE CO_Product_Name = ?";
        
        Connection conn = getConnection();
        try (PreparedStatement checkStmt = conn.prepareStatement(checkQuery)) {
            checkStmt.setString(1, name);
            ResultSet rs = checkStmt.executeQuery();
            rs.next();
            int count = rs.getInt(1);

            if(count > 0){
                order = true;
            } 
            else{
                order = false;
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return order;
    }
    
    public List<String> getCurrentOrderName(){
        String query = "SELECT CO_Product_Name FROM current_order";
        List<String> coName = new ArrayList<>();
        
        try (PreparedStatement stmt = con.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()){
             
            while (rs.next()){
                String orderName = rs.getString("CO_Product_Name");
                coName.add(orderName);
            }
            
        } 
        catch (SQLException ex){
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return coName;
    }
    
    public List<String> getCurrentOrderCost(){
        String query = "SELECT CO_Product_Cost FROM current_order";
        List<String> coCost = new ArrayList<>();
        
        try (PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery()){
            while (rs.next()){
                String orderCost = rs.getString("CO_Product_Cost");
                coCost.add(orderCost);
            }
            
        } 
        catch (SQLException ex){
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return coCost;
    }
    
    public List<String> getCurrentOrderCategory(){
        String query = "SELECT CO_Category FROM current_order ORDER BY CO_Category DESC";
        List<String> coCategory = new ArrayList<>();
        
        try (PreparedStatement stmt = con.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()){
             
            while (rs.next()){
                String orderCategory = rs.getString("CO_Category");
                coCategory.add(orderCategory);
            }
            
        } 
        catch (SQLException ex){
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return coCategory;
    }
    
    public List<String> getCurrentOrderSale(){
        String query = "SELECT CO_Product_Sale FROM current_order";
        List<String> coSale = new ArrayList<>();
        
        try (PreparedStatement stmt = con.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()){
             
            while (rs.next()){
                String orderSale = rs.getString("CO_Product_Sale");
                coSale.add(orderSale);
            }
            
        } 
        catch (SQLException ex){
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return coSale;
    }
    
    public List<String> getCurrentOrderQuantity(){
        String query = "SELECT CO_Product_Quantity FROM current_order";
        List<String> coQuantity = new ArrayList<>();
        
        try (PreparedStatement stmt = con.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()){
             
            while (rs.next()){
                String orderQuantity = rs.getString("CO_Product_Quantity");
                coQuantity.add(orderQuantity);
            }
            
        } 
        catch (SQLException ex){
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return coQuantity;
    }
    
    public List<String> getCurrentOrderTotal(){
        String query = "SELECT ROUND(CO_Product_Cost * CO_Product_Quantity * ((100 - CO_Product_Sale) / 100)) AS Product_Total FROM current_order;  ";
        List<String> coTotal = new ArrayList<>();
        
        try (PreparedStatement stmt = con.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()){
             
            while (rs.next()){
                String orderTotal = rs.getString("Product_Total");
                coTotal.add(orderTotal);
            }
            
        } 
        catch (SQLException ex){
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return coTotal;
    }
    
    public String getOverallOrderTotal(){
        String query = "SELECT SUM(ROUND(CO_Product_Cost * CO_Product_Quantity * ((100 - CO_Product_Sale) / 100))) AS Overall_Total FROM current_order;";
        String orderOverallTotal = null;
        
        try (PreparedStatement stmt = con.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()){
             
            if(rs.next()){
                orderOverallTotal = rs.getString("Overall_Total");
            }
            
        } 
        catch (SQLException ex){
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return orderOverallTotal;
    }
    
    public void updateCurrentProduct(String quantity, String name){
        String updateQuery = "UPDATE current_order SET CO_Product_Quantity = ? WHERE CO_Product_Name = ?";
        int toIntQuantity = Integer.parseInt(quantity);
        
        Connection conn = getConnection();
        try (PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {
            updateStmt.setInt(1, toIntQuantity);  
            updateStmt.setString(2, name);

            updateStmt.executeUpdate();
        }
        catch (SQLException ex){
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String getNewCurrentOrderTotal(String name){
        String query = "SELECT ROUND(CO_Product_Cost * CO_Product_Quantity * ((100 - CO_Product_Sale) / 100)) AS Product_Total FROM current_order WHERE CO_Product_Name = ?;";
        String newOrderTotal = null;
        
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, name);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    newOrderTotal = rs.getString("Product_Total");
                }
            }
            
        } 
        catch (SQLException ex){
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return newOrderTotal;
    }
    
    public boolean isNewProduct(){
        return flag;
        
    }
    
}
