/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author trsud
 */


public class Order {
    private oStatus status;
    private ItemList cart;
    private int cost;
    
    public Order()
    {
        status = oStatus.PLACING;
        cart = new ItemList();
        cost = 0;        
    }
    public Order(Order order)
    {
       status = order.status;
       cart = order.cart;
       cost = order.cost;
    }
    
    public void add_item(String name) throws Exception
    {
        int cost = Resturant.get_resturant().get_menu().getItemCost(name);
        cart.add_item(name, cost);
        this.cost += cost;
         
    }
    public void rem_item(String name) throws Exception
    {
        int cost = cart.getItemCost(name);
        cart.rem_item(name);
        this.cost -= cost;
    }
    
    public oStatus get_status()
    {
        return status;        
    } 
    public int get_cost()
    {
        return cost;
    }
    
    public ItemList get_cart()
    {
        return new ItemList(cart);
    }
    
    public void update_status(WHO who, boolean isPass) throws Exception
    {
        if(who == WHO.DELI)
        {
            if(!isPass)
            {
                refund_cust();
                throw new Exception("Delivary guy failed");
                
            }
            else if(status == oStatus.PLACING)
            {
                refund_cust();
                throw new Exception("Hypothetical situation: Delivary guy cooked the food!!");
            }
            else if(status == oStatus.DELIVERED)
            {
                refund_cust();
                throw new Exception("Hypothetical situation: Delivary guy delivered twice!!");
            }
            else if(status == oStatus.COOKING)
                status = oStatus.PICKED;
            
            else if(status == oStatus.PICKED)
                status = oStatus.DELIVERED;
        }
        else 
        {
            if(!isPass)
            {
                refund_cust();
                throw new Exception("Resturant failed");
            }
            else if(status == oStatus.PLACING)
                status = oStatus.COOKING;
            else if(status == oStatus.DELIVERED)
            {
                refund_cust();
                throw new Exception("Hypothetical situation: Resturant delivaring food again!!");
            }
            else if(status == oStatus.COOKING)
            {
                refund_cust();
                throw new Exception("Hypothetical situation: Resturant picking the food!!");
            }            
            else if(status == oStatus.PICKED)
            {
                refund_cust();
                throw new Exception("Hypothetical situation: Resturant delivaring food!!");
            }
        }
    }
    
    private void refund_cust() throws Exception
    {
        
        int wallet = Customer.get_customer().get_wallet();
        Customer.get_customer().set_wallet(wallet + cost);
    }
    
    
}
