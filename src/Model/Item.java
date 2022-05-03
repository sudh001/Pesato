/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author trsud
 */
public class Item {
    private String name;
    private int cost;
    private int quantity;

    public Item(String name, int cost) {
        this.name = name;
        this.cost = cost;
        this.quantity = 1;
    }

    public String getName() {
        return name;
    }

    public void inc_quantity()
    {
        this.quantity++;
    }
    public void dec_quantity() throws Exception
    {
        if(quantity == 0)
            throw new Exception("Item not found");
        this.quantity--;
    }
    
    public int get_quantity()
    {
        return quantity;
    }
    public int getCost() {
        return cost;
    }
    
    public boolean equal(Item rhs)
    {
        return name.contentEquals(rhs.name) && cost == rhs.cost;
            
    }
    
    @Override 
    public String toString()
    {
        return name + "      " + cost + "      " + quantity;
    }
    
}