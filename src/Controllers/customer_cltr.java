/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;
import Model.*;
import javax.swing.*;
import java.util.*;
import java.io.*;
import Views.*;


/**
 *
 * @author trsud
 */
public class customer_cltr {
    public int get_balance()
    {
        try
        {
            return Customer.get_customer().get_wallet();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return 0; // garbage statement
    }
    
    public void add_amount(int amt)
    {
        try
        {
            int wallet = Customer.get_customer().get_wallet();
            Customer.get_customer().set_wallet(wallet + amt);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
    }
    public void rem_amount(int amt) throws Exception
    {
        
        int wallet = Customer.get_customer().get_wallet();
        if(wallet < amt)
            throw new Exception("Insufficient funds");
        Customer.get_customer().set_wallet(wallet - amt);
        
        
    }
    public void make_order()
    {
        choose_resturants chr = new choose_resturants();
        chr.setVisible(true);
    }
}
