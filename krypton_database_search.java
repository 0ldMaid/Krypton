import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;  
import java.lang.Object.*;  
import java.net.*;

import java.awt.Toolkit;

import java.security.MessageDigest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Properties;




public class krypton_database_search{



    String[][] tokens_ssp2;


    int ix0 = 0;
    int ix1 = 0;
    int ix2 = 0;




    public String[][] search(String id){//**************************************************************************
    network.database_in_use = 1;



        try{



                id = id.toLowerCase();


                boolean found_items = false;
                ix0 = 0;

                System.out.println("Search: " + id);

                System.out.println("Load listings_db... get search items" );

                krypton_database_driver.s = krypton_database_driver.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                krypton_database_driver.rs = krypton_database_driver.s.executeQuery("SELECT id,item_title,item_price,currency,seller_address_zip FROM listings_db WHERE item_title_url LIKE '%" + id + "%' ORDER BY id ASC");

                krypton_database_driver.rs.last();
                int rowCountx = krypton_database_driver.rs.getRow();
                tokens_ssp2 = new String[5][rowCountx];

                System.out.println("rowCountx " + rowCountx);

	            krypton_database_driver.rs.first();

                if(rowCountx > 0){

                    tokens_ssp2[0][ix0] = new String(krypton_database_driver.rs.getString("id"));
                    tokens_ssp2[1][ix0] = new String(krypton_database_driver.rs.getString("item_title"));
                    tokens_ssp2[2][ix0] = new String(krypton_database_driver.rs.getString("item_price"));
                    tokens_ssp2[3][ix0] = new String(krypton_database_driver.rs.getString("currency"));
                    tokens_ssp2[4][ix0] = new String(krypton_database_driver.rs.getString("seller_address_zip"));

                    ix0++;
                    found_items = true;

                }//***************

	            while(krypton_database_driver.rs.next()){

                    tokens_ssp2[0][ix0] = new String(krypton_database_driver.rs.getString("id"));
                    tokens_ssp2[1][ix0] = new String(krypton_database_driver.rs.getString("item_title"));
                    tokens_ssp2[2][ix0] = new String(krypton_database_driver.rs.getString("item_price"));
                    tokens_ssp2[3][ix0] = new String(krypton_database_driver.rs.getString("currency"));
                    tokens_ssp2[4][ix0] = new String(krypton_database_driver.rs.getString("seller_address_zip"));

                    ix0++;
                    found_items = true;

	            }//while

	    
	            System.out.println("found_items " + found_items);




                krypton_database_driver.conn.commit();
                System.out.println("Committed the transaction");


        }catch(Exception e){e.printStackTrace();}


    network.database_in_use = 0;
    return tokens_ssp2;

    }//load




 


}//class
