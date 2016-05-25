import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Properties;

import java.io.*;
import java.security.*;

import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.spec.RSAPrivateCrtKeySpec;






public class krypton_database_save{



    int ix0 = 0;
    int ix1 = 0;

    String cx0 = new String();
    String cx1 = new String();
    String cx2 = new String();

    int worm_size = 10;


    krypton_database_save(){//************************************************************************


        while(network.database_in_use == 1){

            int test_db = 0;
            System.out.println("Database in use...SAVE");
            try{Thread.sleep(1000);} catch (InterruptedException e){}

        }//*********************************


        network.database_in_use = 1;



        try{



            krypton_database_driver.s.execute("drop table settings");
            System.out.println("DROP settings");


            krypton_database_driver.s.execute("create table settings(id integer, valuex varchar(2000))"); 
            System.out.println("Created table settings");



            PreparedStatement ps = null;
            ps = krypton_database_driver.conn.prepareStatement("insert into settings(id, valuex) values (?,?)");

            int numrows = 0;

            for (int loop = 0; loop < network.settingsx.length; loop++){

                System.out.println("save >>> " + network.settingsx[loop]);
                ps.setInt(1, (loop + 1));
                ps.setString(2, network.settingsx[loop]);
                numrows = numrows + ps.executeUpdate();

            }//*********************************************************

                
            ps.close();



            krypton_database_driver.conn.commit();
            System.out.println("Committed the transaction");


        }catch(Exception e){e.printStackTrace();}


    network.database_in_use = 0;

    }//load







}//class
