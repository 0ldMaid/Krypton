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




public class krypton_database_get_my_tokens{


    String[] token_ssp2;


    int ix0 = 0;
    int ix1 = 0;
    int ix2 = 0;




    public String[] get_tokens(String id){//**************************************************************************
    network.database_in_use = 1;




        try{


                System.out.println("Load id List..." );

                krypton_database_driver.s = krypton_database_driver.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                krypton_database_driver.rs = krypton_database_driver.s.executeQuery("SELECT id FROM listings_db WHERE seller_id='" + id + "' ORDER BY id ASC");

                krypton_database_driver.rs.last();
                int rowCountx = krypton_database_driver.rs.getRow();
                token_ssp2 = new String[rowCountx];

                krypton_database_driver.rs.first();

                if(rowCountx > 0){token_ssp2[0] = new String(krypton_database_driver.rs.getString(1));}

                ix0 = 1;
	            while(krypton_database_driver.rs.next()){

                    token_ssp2[ix0] = new String(krypton_database_driver.rs.getString(1));

                    ix0++;
	            }//while

	    
	            System.out.println("size " + token_ssp2.length);



                krypton_database_driver.conn.commit();
                System.out.println("Committed the transaction");


        }catch(Exception e){e.printStackTrace();}


    network.database_in_use = 0;
    return token_ssp2;

    }//load




}//class
