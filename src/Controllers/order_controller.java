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
    public void update_status(WHO who, boolean isPass)
    {
        try
        {
            if(order != null)
                order.update_status(who, isPass);
            else
                throw new Exception("order dosen't exist");
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
   
}
