package dao;

import connectWithDatabase.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import model.Product;
import model.User;

public class Dao {
    
    static public Map<String, User> emp = new HashMap();
    
    public void addtoStockDatabase(Product p) {
        DBConnect db = new DBConnect();
        Connection con = null;
        PreparedStatement pstmnt = null;
        ResultSet rs = null;
        try {
            con = db.getConnection();
            pstmnt = con.prepareStatement("insert into product values(?,?,?,?,?)");
            pstmnt.setString(1, p.getProductId());
            pstmnt.setString(2, p.getProductName());
            pstmnt.setDouble(3, p.getCostPrice());
            pstmnt.setDouble(4, p.getSellPrice());
            pstmnt.setInt(5, p.getQuantity());
            int noOfRowAffected = pstmnt.executeUpdate();
            System.out.println("no of row affected :" + noOfRowAffected);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    public void updateAfterSale(String id, int n) {
        DBConnect db = new DBConnect();
        Connection con = null;
        PreparedStatement pstmnt = null;
        ResultSet rs = null;
        int remain = (getProductQuantity(id) - n);
        if (remain <= 1) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            con = db.getConnection();
            pstmnt = con.prepareStatement("UPDATE Product SET Quantity = Quantity-?  WHERE ProductId =?");
            pstmnt.setInt(1, n);
            pstmnt.setString(2, id);
            int noOfRowAffected = pstmnt.executeUpdate();
            System.out.println("no of row affected :" + noOfRowAffected);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

//    public ResultSet getProduct() {
//        try {
//            con = db.getConnection();
//            pstmnt = con.prepareStatement("select * from product");
//            rs = pstmnt.executeQuery();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return rs;
//    }
    public Product getProductById(String id) {
        Product product = new Product();
        DBConnect db = new DBConnect();
        Connection con = null;
        PreparedStatement pstmnt = null;
        ResultSet rs = null;
        try {
            con = db.getConnection();
            pstmnt = con.prepareStatement("select * from product where ProductId=?");
            pstmnt.setString(1, id);
            rs = pstmnt.executeQuery();
            
            while (rs.next()) {
                product.setProductId(rs.getString(1));
                product.setProductName(rs.getString(2));
                product.setCostPrice(rs.getDouble(3));
                product.setSellPrice(rs.getDouble(4));
                product.setQuantity(rs.getInt(5));
                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return product;
    }
    
    public Product getProductByName(String name) {
        Product product = new Product();
        DBConnect db = new DBConnect();
        Connection con = null;
        PreparedStatement pstmnt = null;
        ResultSet rs = null;
        try {
            con = db.getConnection();
            pstmnt = con.prepareStatement("select * from product where ProductName=?");
            pstmnt.setString(1, name);
            rs = pstmnt.executeQuery();
            while (rs.next()) {
                product.setProductId(rs.getString(1));
                product.setProductName(rs.getString(2));
                product.setCostPrice(rs.getDouble(3));
                product.setSellPrice(rs.getDouble(4));
                product.setQuantity(rs.getInt(5));
                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return product;
    }
    
    public double getFromDatabase(DefaultTableModel model) {
        String[] row = new String[5];
        DBConnect dc = new DBConnect();
        Connection con = null;
        PreparedStatement prestmnt = null;
        ResultSet rs = null;
        double tcost=0;
        
        try {
            con = dc.getConnection();
            prestmnt = con.prepareStatement("select * from product");
            rs = prestmnt.executeQuery();
            while (rs.next()) {
                row[0] = rs.getString(1);
                row[1] = rs.getString(2);
                row[2] = Double.toString(rs.getDouble(3));
                tcost+=(rs.getDouble(3)*rs.getInt(5));
                row[3] = Double.toString(rs.getDouble(4));
                row[4] = Integer.toString(rs.getInt(5));
                model.addRow(row);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dc.close(con, prestmnt, rs);
        }
        return tcost;
    }
    
    public boolean loginchk(String k, String u) {
        boolean flag = false;
//For database
//        try {
//            if (emp.get(k).getPass().equals(u)) {
//                flag = true;
//            }
//        } catch (Exception e) {
//            System.out.println("Invalid user name or password ");
//        }
//For static login
        try {
            if (k.equals("admin") && u.equals("admin")) {
                flag = true;
            }
        } catch (Exception e) {
            
        }

        
//        return true;
        return flag;
    }
    
    public double getPrice(String id) {
        DBConnect dc = new DBConnect();
        Connection con = null;
        PreparedStatement prestmnt = null;
        ResultSet rs = null;
        double price = 0;
        try {
            con = dc.getConnection();
            prestmnt = con.prepareStatement(" SELECT SellingPrice FROM Product WHERE ProductId = ?");
            prestmnt.setString(1, id);
            rs = prestmnt.executeQuery();
            while (rs.next()) {
                price = Double.parseDouble(rs.getString(1));
            }
            
        } catch (Exception e) {
            return price = 0.00;
        } finally {
            dc.close(con, prestmnt, rs);
            return price;
        }
        
    }
    public double getCost(String id) {
        DBConnect dc = new DBConnect();
        Connection con = null;
        PreparedStatement prestmnt = null;
        ResultSet rs = null;
        double cost = 0;
        try {
            con = dc.getConnection();
            prestmnt = con.prepareStatement(" SELECT CostPrice FROM Product WHERE ProductId = ?");
            prestmnt.setString(1, id);
            rs = prestmnt.executeQuery();
            while (rs.next()) {
                cost = Double.parseDouble(rs.getString(1));
            }
            
        } catch (Exception e) {
            return cost = 0.00;
        } finally {
            dc.close(con, prestmnt, rs);
            return cost;
        }
        
    }
    
    public String getProductName(String id) {
        DBConnect dc = new DBConnect();
        Connection con = null;
        PreparedStatement prestmnt = null;
        ResultSet rs = null;
        String name = "";
        try {
            con = dc.getConnection();
            prestmnt = con.prepareStatement(" SELECT ProductName FROM Product WHERE ProductId = ?");
            prestmnt.setString(1, id);
            rs = prestmnt.executeQuery();
            while (rs.next()) {
                name = rs.getString(1);
            }
        } catch (Exception e) {
            return name = "";
        }
        dc.close(con, prestmnt, rs);
        return name;
    }
    
    public int getProductQuantity(String id) {
        DBConnect dc = new DBConnect();
        Connection con = null;
        PreparedStatement prestmnt = null;
        ResultSet rs = null;
        int quantity = 0;
        try {
            con = dc.getConnection();
            prestmnt = con.prepareStatement(" SELECT  Quantity FROM Product WHERE ProductId = ?");
            prestmnt.setString(1, id);
            rs = prestmnt.executeQuery();
            while (rs.next()) {
                quantity = rs.getInt(1);
            }
        } catch (Exception e) {
            return quantity = 0;
        }
        dc.close(con, prestmnt, rs);
        return quantity;
    }
    
    public void delete(String type, String id) {
       
        
        String column = new String();
        if (type.equals("Product Id")) {
            column = "ProductId";
        }
        if (type.equals("Product Name")) {
            column = "ProductName";
        }
         DBConnect db = new DBConnect();
        Connection con = null;
        PreparedStatement pstmnt = null;
        System.out.println(type + " " + id + " " + column);
        try {
            con = db.getConnection();
            pstmnt = con.prepareStatement(" DELETE FROM Product WHERE "+column+" = ? ");
            pstmnt.setString(1, id);
            int noOfRowAffected = pstmnt.executeUpdate();
            System.out.println("no of row affected :" + noOfRowAffected);
        } catch (Exception e) {
            e.printStackTrace();
        }
        db.close(con, pstmnt, null);
        
    }
    
    public void update(Product p) {
        DBConnect db = new DBConnect();
        Connection con = null;
        PreparedStatement pstmnt = null;
        
        try {
            con = db.getConnection();
            pstmnt = con.prepareStatement("UPDATE product SET  CostPrice = ? , SellingPrice = ? , Quantity =? WHERE ProductId =?");

            pstmnt.setDouble(1, p.getCostPrice());
            pstmnt.setDouble(2, p.getSellPrice());
            pstmnt.setInt(3, p.getQuantity());
            pstmnt.setString(4, p.getProductId());
            int noOfRowAffected = pstmnt.executeUpdate();
            System.out.println("no of row affected :" + noOfRowAffected);
        } catch (Exception e) {
            e.printStackTrace();
        }
        db.close(con, pstmnt, null);
    }
    
}
