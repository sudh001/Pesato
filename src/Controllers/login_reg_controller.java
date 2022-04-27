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
public class login_reg_controller {
    
    public void login(String u_name, String pwd, WHO type) throws Exception
    {
        try
        {
            DBA.load_user(u_name, pwd, type);
            if(type == WHO.CUST)
            {
                customer_home chm = new customer_home();
                chm.setVisible(true);
            }
            else
            {
                new resturant_home().setVisible(true);
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.println("Wassup");
            throw new Exception("Login failed");
        }
        
    }
    public void reg(String u_name, String pwd, WHO type)
    {
        try
        {
            DBA.create_user(u_name, pwd, type);
            if(type == WHO.CUST)
            {
                
            }
            else
            {
                
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
}
