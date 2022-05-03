/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author trsud
 */
public class Customer extends User{
    private static Customer cust;
    int wallet;
    private Customer(String u_name, String pwd, int wallet)
    {
        super(u_name, pwd);
        this.wallet = wallet;
    }
    public static void set_customer(String u_name, String pwd, int wallet) throws Exception
    {
        if(cust != null)
        {
            throw new Exception("Customer already exists");
        }
        cust = new Customer(u_name, pwd, wallet);
       
    }
    
    public static Customer get_customer() throws Exception
    {
        if(cust == null)
        {
            throw new Exception("Customer not set");
        }
        return cust;
    }
    
    public int get_wallet()
    {
        return wallet;
    }
    public void set_wallet(int amt) throws Exception
    {
        wallet = amt;
        DBA.save();
    }
    
}