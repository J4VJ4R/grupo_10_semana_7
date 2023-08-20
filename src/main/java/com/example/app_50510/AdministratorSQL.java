package com.example.app_50510;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;

public class AdministratorSQL {
    private Connection objConnection;
    private String url = "jdbc:mysql://34.67.198.207:3306/base_users";
    private String user = "jara-desarrollo";
    private String passw = "12345";
    private String table = "users";

    public boolean connectSQL(){
        try{
            objConnection = DriverManager.getConnection(url, user, passw);
            return  true;
        }catch (Exception ex){
            Log.e("MyTag", ex.toString());
            return false;
        }
    }
}
