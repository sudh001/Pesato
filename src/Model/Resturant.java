/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author trsud
 */
public class Resturant extends User
{
    private ItemList menu;
    private static Resturant rest;
    private boolean is_main_user;
    private Resturant(String u_name, String pwd, boolean is_main_user, ItemList menu)
    {
        super(u_name, pwd);
        this.is_main_user = is_main_user;
        this.menu = menu;
    }
    
    public static void set_resturant(String u_name, String pwd, boolean is_main_user, ItemList menu) throws Exception
    {
        if(rest != null)
        {
            throw new Exception("Resturant already set");
        }
        rest = new Resturant(u_name, pwd, is_main_user, menu);
        
    }
    
    public static Resturant get_resturant() throws Exception
    {
        if(rest == null)
        {
            throw new Exception("Resturant isn't set");
        }
        return rest;
    }
    
    public void add_item(String name, int cost) throws Exception
    {
        if(!is_main_user)
        {
            throw new Exception("add_item operation on resturant denied: not main user");
        }
        else if(menu.item_pos(name) != -1)
        {
            throw new Exception("Item already exists in menu");
        }
        menu.add_item(name, cost);
        DBA.save();
    }
    
    public void rem_item(String name) throws Exception
    {
        if(!is_main_user)
        {
            throw new Exception("add_item operation on resturant denied: not main user");
        }
        menu.rem_item(name);
        DBA.save();
    }
    
    public ItemList get_menu()
    {
        return new ItemList(menu);
        
    }
    
    public boolean item_in_menu(String item_name)
    {
        return menu.item_pos(item_name) != -1;
    }
    public String[] get_menu_strings()
    {
        String []before = menu.toStrings();
        String[] ret = new String[before.length];
        
        for(int i = 0; i < before.length; i++)
            ret[i] = before[i].substring(0, before[i].length() - 1);
        return ret;
    }
}