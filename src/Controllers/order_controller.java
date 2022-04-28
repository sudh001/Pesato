/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;
import Model.*;
import Views.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

/**
 *
 * @author trsud
 */
public class order_controller {
    private Order order;
    private OrderBuilder obd = new OrderBuilder();
    public String[] get_resturants()
    {
        return DBA.get_resturants();
    }
    public void set_resturant(String rest_u_name) throws Exception
    {
        
        DBA.set_resturant(rest_u_name);
        place_order plc = new place_order();
        plc.setVisible(true);
        
    }
    public ItemList get_menu()
    {
        try
        {
            return Resturant.get_resturant().get_menu();
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return null;
    }
    public ItemList get_cart()
    {
        return obd.peek().get_cart();
    }
    public void add_item(String item_name) throws Exception
    {
        
        if(!Resturant.get_resturant().item_in_menu(item_name))
            throw new Exception("Item not in menu");
        obd.add_item(item_name);
        
    }
    public void rem_item(String item_name) throws Exception
    {
        
        obd.rem_item(item_name);
        
    }
    public void place() throws Exception
    {      
        
        if(order == null)
            order = obd.place();
        else
            throw new Exception("order already placed");
        
        
        
          
    }
    public String get_status()
    {
        try
        {
            if(order != null)
                return order.get_status().toString();
            else
                throw new Exception("order dosent' exist");
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return "";
    }
    public void update_status(WHO who, boolean isPass) throws Exception
    {
        
        if(order != null)
            order.update_status(who, isPass);
        else
            throw new Exception("order dosen't exist");
        JOptionPane.showMessageDialog(null, "Your order status is: " + order.get_status());
        
            
    }
    
    public void order_update_wizard(WHO who, boolean isPass)
    {
       
        if(who == WHO.CUST)
        {
            JOptionPane.showMessageDialog(null, "Your order is being cooked");
            // Sending request to Resturant
            Order_update_rest rst = new Order_update_rest(this);
            rst.setVisible(true);
            
        }
        else if(who == WHO.REST)
        {
            try
            {
                update_status(who, isPass);
                
                order_update_deli deli = new order_update_deli(this);
                deli.setVisible(true);
                
            }
            catch (Exception e)
            {
                JOptionPane.showMessageDialog(null, e.getMessage() + "\n Please re-make your order");
                choose_resturants chr = new choose_resturants();
                chr.setVisible(true);
              
            }
            
        }
        else if(who == WHO.DELI)
        {
            try
            {
                update_status(who, isPass);
            }
            catch (Exception e)
            {
                JOptionPane.showMessageDialog(null, e.getMessage() + "\n Please re-make your order");
                choose_resturants chr = new choose_resturants();
                chr.setVisible(true);
              
            }
        }
    }
   
}
