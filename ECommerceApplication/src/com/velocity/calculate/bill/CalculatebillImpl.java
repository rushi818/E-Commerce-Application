package com.velocity.calculate.bill;
//8
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CalculatebillImpl implements Calculatebill {

    @Override
    public void calculate() throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        double totalBill = 0.0;

        try {
            Class.forName("com.mysql.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce", "root", "root");

            String query = "select productquantity, price from product";
            ps = con.prepareStatement(query);

            rs = ps.executeQuery();
            while (rs.next()) {
                String quantityStr = rs.getString("productquantity").trim();
                int quantity = Integer.parseInt(quantityStr);
                double price = rs.getDouble("price");
                double itemTotal = quantity * price;
                totalBill += itemTotal;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
                rs.close();
                ps.close();
                con.close();
        }

        System.out.println("Admin will calculate the bill>>"+totalBill);
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        CalculatebillImpl cl = new CalculatebillImpl();
        cl.calculate();
    }
}
