/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author trsud
 */
public class DBA {
    public static User create_user(String u_name, String pwd, WHO type) throws Exception
    {
        // Replace this dummy code with correct data base access code
        try
        {
            if(type == WHO.CUST)
            {
                Customer.set_customer(u_name, pwd, 200);
                return Customer.get_customer();
            }
            else
            {
                ItemList menu = new ItemList();
                menu.add_item("item 1", 20);
                menu.add_item("item 2", 21);
                menu.add_item("item 3", 22);

                Resturant.set_resturant(u_name, pwd, true, menu);
                return Resturant.get_resturant();
            }
            
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    public static User load_user(String u_name, String pwd, WHO type) throws Exception
    {
        // Replace this dummy code with correct data base access code
        // Expected to create main_user 
        try
        {
            DBA.create_user(u_name, pwd, type);
            if(type == WHO.CUST)
            {
                return Customer.get_customer();
            }
            else
                return Resturant.get_resturant();
            
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    public static String[] get_resturants()
    {
        String[] ret = {"Rest 1", "Rest 2", "Rest 3","Rest 4"};
        return ret;
    }
    
    public static void set_resturant(String name) throws Exception
    {
        ItemList menu = new ItemList();
        String pwd = name + " pwd";
        menu.add_item("item 1", 20);
        menu.add_item("item 2", 21);
        menu.add_item("item 3", 22);

        Resturant.set_resturant(name, pwd, false, menu); // Make sure to put Resturant is_main_user as false
       
    }
    public static void save()
    {
        // Save the entire state of the application (Customer and Resturant)
    }
}
