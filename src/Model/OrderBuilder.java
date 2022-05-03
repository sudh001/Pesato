/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author trsud
 */
public class OrderBuilder {
    private Order order;
    private boolean isPlaced;
    public void add_item(String name) throws Exception
    {
        if(isPlaced)
            throw new Exception("Trying to edit a placed order");
        if (order == null)
            order = new Order();
        order.add_item(name);
    }
    public void rem_item(String name) throws Exception
    {
        if(isPlaced)
            throw new Exception("Trying to edit a placed order");
        if(order != null)
        {
            order.rem_item(name);
        }
        else
            throw new Exception("No order exists");
        
    }
    public Order place() throws Exception
    {
        int cust_wallet = Customer.get_customer().get_wallet();
        if(order == null)
            throw new Exception("No order to place");
        else if(cust_wallet < order.get_cost())
            throw new Exception("Insufficient balance");
        else
        {
            Customer.get_customer().set_wallet(cust_wallet - order.get_cost());
            isPlaced = true;
            return order;
        }
    }
    public Order peek()
    {
        return new Order(order);
    }
}