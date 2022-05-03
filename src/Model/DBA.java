/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author trsud
 */

import com.mongodb.Block;
import com.mongodb.client.*;
//import com.mongodb.client.MongoCollection;
import com.mongodb.MongoClient;
//import com.mongodb.MongoCredential;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.eq;
import java.util.ArrayList;
import org.bson.Document;


public class DBA {
    public static User create_user(String u_name, String pwd, WHO type) throws Exception
    {
        MongoClient mongo = new MongoClient("localhost" , 27017);

        try
        {
            if(type == WHO.CUST)
            {
                MongoDatabase cust = mongo.getDatabase("Customer");
                System.out.println("GET customerdb yes");

                // Retrieving a collection
                MongoCollection<Document> collection = cust.getCollection("customerCollection");
                System.out.println("GET customerCollection OK");

                try
                {
                    Document nameDocList = collection.find(eq("customer Name", u_name)).first();
                    if (nameDocList == null) {
                        Document cust_record = new Document("customer Name", u_name)
                        .append("password", pwd)
                        .append("wallet balance", 0);
                        collection.insertOne(cust_record);
                        System.out.println("CREATE customer OK");
                    } else {
                        throw new Exception("Customer already exists~ Please Sign in.");
                    }
                }
                catch (Exception e)
                {
                    System.out.println(e.getMessage());
                    throw e;
                }
            }
            else
            {
                MongoDatabase cust = mongo.getDatabase("Restaurent");
                System.out.println("GET restdb yes");

                // Retrieving a collection
                MongoCollection<Document> collection = cust.getCollection("restaurentCollection");
                System.out.println("GET restCollection OK");

                try
                {
                    Document nameDocList = collection.find(eq("restaurent Name", u_name)).first();
                    if (nameDocList == null) {
                        ItemList menu = new ItemList();
                        menu.add_item("item 1", 20);
                        menu.add_item("item 2", 21);
                        menu.add_item("item 3", 22);
                        Item[] items = menu.get_items();
                        String itemStr = "";
                        for (Item item : items)
                        {
                            itemStr += item.getName() + "," + item.get_quantity() + ";";
                        }
                        Document rest_record = new Document("restaurent Name", u_name) // u_name is actually r_name
                            .append("password", pwd)
                            .append("items", itemStr);
                        collection.insertOne(rest_record);
                        System.out.println("CREATE rest OK");
                    } else {
                        throw new Exception("Restaurant already exists! Please Sign in.");
                    }
                }
                catch (Exception e)
                {
                    System.out.println(e.getMessage());
                    throw e;
                }
            }
        }
        catch (Exception e)
        {
            throw e;
        }
      return null;
    }

    public static void load_user(String u_name, String pwd, WHO type) throws Exception
    {
        // Expected to create main_user

        MongoClient mongo = new MongoClient("localhost" , 27017);
        try
        {

            if(type == WHO.CUST)
            {
                MongoDatabase database = mongo.getDatabase("Customer");
                MongoCollection<Document> collection = database.getCollection("customerCollection");
                System.out.println("GET customerCollection OK");

                try
                {
                    Document nameDoc = collection.find(eq("customer Name", u_name)).first();
                    //if (nameDoc == null || null == nameDoc.get("uname"))
                    {
                        Customer.set_customer(u_name, pwd, nameDoc.getInteger("wallet balance"));
                    }
                }
                catch (Exception e)
                {
                    System.out.println(e.getMessage());
                    throw new Exception("User does not exist!");
                }

            }
            else
            {
                MongoDatabase database = mongo.getDatabase("Restaurent");
                MongoCollection<Document> collection = database.getCollection("restaurentCollection");

                try
                {
                    Document nameDoc = collection.find(eq("restaurent Name", u_name)).first();
                    if (nameDoc.isEmpty())
                    {
                        throw new Exception("Restaurent does not exist!");
                    }
                        String itemStr = nameDoc.getString("items");
                        ItemList menu = new ItemList();
                        for (String item : itemStr.split(";"))
                        {
                           String[] item12 = item.split(",");
                           menu.add_item(item12[0], Integer.parseUnsignedInt(item12[1]));
                        }
                        Resturant.set_resturant(u_name, pwd, true, menu);
                }
                catch (Exception e)
                {
                    System.out.println(e.getMessage());
                    throw e;
                }
            }

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public static ArrayList<String> get_resturants() // keep ret type as string
    {
        ArrayList<String> ret = new ArrayList<>();

        MongoClient mongo = new MongoClient("localhost" , 27017);
        MongoDatabase database = mongo.getDatabase("Restaurent");
        MongoCollection<Document> collection = database.getCollection("restaurentCollection");

        try
        {
            FindIterable<Document> iterable = collection.find();
            iterable.forEach(new Block<Document>() {//
                @Override
                public void apply(final Document d) {
                    ret.add(d.getString("restaurent Name"));
                }
            });
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return ret;
    }

    public static void set_resturant(String name) throws Exception
    {
        MongoClient mongo = new MongoClient("localhost" , 27017);
        MongoDatabase database = mongo.getDatabase("Restaurent");
        MongoCollection<Document> collection = database.getCollection("restaurentCollection");

        try
        {
            Document nameDoc = collection.find(eq("restaurent Name", name)).first();
            if (nameDoc.isEmpty())
            {
                throw new Exception("Restaurent does not exist!");
            }
                String itemStr = nameDoc.getString("items");
                String pwd = nameDoc.getString("password");

                ItemList menu = new ItemList();
                for (String item : itemStr.split(";"))
                {
                   String[] item12 = item.split(",");
                   menu.add_item(item12[0], Integer.parseUnsignedInt(item12[1]));
                }
                Resturant.set_resturant(name, pwd, true, menu);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            throw e;
        }

    }

    public static void save() throws Exception // TODO
    {
        // save the entire state of the application (customer and restaurent)

        // CUSTOMER - update customer wallet, rest menu
        MongoClient mongo = new MongoClient("localhost" , 27017);

        try
        {
            MongoDatabase cust = mongo.getDatabase("Customer");
            MongoCollection<Document> cust_coll = cust.getCollection("customerCollection");
            String u_name = Customer.get_customer().getU_name();
            Document cust_record = new Document("customer Name", u_name)
            .append("password", Customer.get_customer().getPwd())
            .append("wallet balance", Customer.get_customer().get_wallet());

            cust_coll.updateOne(eq("customer Name", u_name), new Document("$set", cust_record));
            System.out.println("customer details saved successfully!");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }


        try
        {
            MongoDatabase database = mongo.getDatabase("Restaurent");
            MongoCollection<Document> collection = database.getCollection("restaurentCollection");

            ItemList menu = new ItemList();

            Item[] items = Resturant.get_resturant().get_menu().get_items();
            String itemStr = "";
            for (Item item : items)
            {
                itemStr += item.getName() + "," + item.getCost() + ";";
            }
            String u_name = Resturant.get_resturant().getU_name();
            Document rest_record = new Document("restaurent Name", u_name) // u_name is actually r_name
                .append("password", Resturant.get_resturant().getPwd())
                .append("items", itemStr);
            //update collection where restaurent name = u_name
            collection.updateOne(eq("restaurent Name", u_name), new Document("$set", rest_record));

            //Resturant.set_resturant(u_name, pwd, true, menu);

            System.out.println("inserted in save rest");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            //throw e;
        }

        mongo.close();

    }
}
