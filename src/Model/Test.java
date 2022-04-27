/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author trsud
 */
public class Test {
    public static void main(String[] args) throws Exception
    {
        /*
        // Tests on resturant functionalities
        String u_name = "rest1";
        String pwd = "rest1_pwd";
        ItemList menu = new ItemList();
        menu.add_item("item 1", 20);
        menu.add_item("item 2", 21);
        menu.add_item("item 3", 22);
        
        Resturant.set_resturant(u_name, pwd, true, menu);
        System.out.println("hii");
        Resturant rest = Resturant.get_resturant();
        ItemList menu1 = rest.get_menu();
        System.out.println(menu1);
        
        rest.add_item("item 4", 23);
        menu1 = rest.get_menu();
        System.out.println(menu1);
        
        rest.rem_item("item 1");
        menu1 = rest.get_menu();
        System.out.println(menu1);
        */
        
        // Testing customer and order placing functionalities
        Customer.set_customer("cust 1", "cust_1_pwd", 200);
//        Customer.set_customer("cust 1", "cust_1_pwd", 200);

        Customer cust = Customer.get_customer();
        String u_name = "rest1";
        String pwd = "rest1_pwd";
        ItemList menu = new ItemList();
        menu.add_item("item 1", 20);
        menu.add_item("item 2", 21);
        menu.add_item("item 3", 22);
        
        Resturant.set_resturant(u_name, pwd, true, menu);
        OrderBuilder obd = new OrderBuilder();
        obd.add_item("item 1");
        obd.add_item("item 1");
        obd.add_item("item 1");
        
        Order order = obd.place(); // Decrements wallet amount from customer
        System.out.println(order.get_cart());
        System.out.println(order.get_cost());
        System.out.println(cust.get_wallet());
        
        System.out.println(order.get_status());
        order.update_status(WHO.REST, true);
        System.out.println(order.get_status());
        order.update_status(WHO.REST, true);
        System.out.println(order.get_status());
        
        try
        {
            order.update_status(WHO.DELI, false);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println(cust.get_wallet()); // Refund functionality supported !!
        }
        
        
        
        // Feel free to expierement with 
        
    }
}
