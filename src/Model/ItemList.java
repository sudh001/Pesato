/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author trsud
 */
public class ItemList 
{
    private Item[] items = new Item[100];
    private int len = 0;
    
    
    public ItemList() {}
    public ItemList(ItemList rhs)
    {
        this.items = rhs.items;
        this.len = rhs.len;
    }
    
    public void add_item(String name, int cost)
    {
        int pos = item_pos(name);
        if(pos == -1)
        {
            items[len] = new Item(name, cost);
            
            len++;
        }
        else
            items[pos].inc_quantity();
    }
    
    int item_pos(String name)
    {
        for(int i = 0; i < len; i++)
        {
            if (items[i].getName().contentEquals(name))
            {
                return i;
            }
        }
        return -1;
    }
    public void rem_item(String name) throws Exception
    {
        if(len == 0)
        {
            throw new Exception("Item list is empty");
        }
        
        int pos = item_pos(name);
        if(pos == -1)
            throw new Exception("Item not found");  
        else if(items[pos].get_quantity() == 1)
        {    
            items[pos] = items[len - 1];
            items[len - 1] = null;
            
            
            len--;
        }
        else
            items[pos].dec_quantity();
        
    }
    
    public Item[] get_items()
    {
        Item[] ret = new Item[len];
        for(int i = 0; i < len; i++)
            ret[i] = items[i];
        return ret;
    }
    
    @Override
    public String toString()
    {
        String ret = "";
        for(Item item: items)
        {
            if(item == null)
                break;
            ret += item.toString() + "\n";
        }
        return ret;
    }
    
    
    public int getItemCost(String name) throws Exception
    {
        for(Item item: items)
        {
            if(item == null)
                break;
            if(item.getName().contentEquals(name))
                return item.getCost();
        }
        throw new Exception("Item not found");
    }
    
    public String[] toStrings()
    {
        String[] ret = new String[len];
        for(int i = 0; i < len; i++)
            ret[i] = items[i].toString();
        return ret;
    }
    
    
}