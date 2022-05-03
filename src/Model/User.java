/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author trsud
 */
public class User {
    private String u_name;
    private String pwd;

    public User(String u_name, String pwd) {
        this.u_name = u_name;
        this.pwd = pwd;
    }

    public String getU_name() {
        return u_name;
    }

    public String getPwd() {
        return pwd;
    }
    
    
}