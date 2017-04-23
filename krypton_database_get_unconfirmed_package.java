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




public class krypton_database_get_unconfirmed_package{


    String[][] token_ssp2;

    int ix0 = 0;
    int ix1 = 0;
    int ix2 = 0;




    public String[][] get_tokens(int size){//**************************************************************************
    network.database_in_use = 1;


         

        try{




            System.out.println("Load unconfirmed_db..." );

            krypton_database_driver.s = krypton_database_driver.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            krypton_database_driver.s.setMaxRows(size);
            krypton_database_driver.rs = krypton_database_driver.s.executeQuery("SELECT * FROM unconfirmed_db ORDER BY id ASC");

            krypton_database_driver.rs.last();

	        ix0 = krypton_database_driver.rs.getRow();

            token_ssp2 = new String[network.listing_size][ix0];

            krypton_database_driver.rs.beforeFirst();

            ix1 = 0;
	        while(krypton_database_driver.rs.next()){


                for(int loop1 = 0; loop1 < network.listing_size; loop1++){//***********

                    token_ssp2[loop1][ix1] = new String(krypton_database_driver.rs.getString((loop1 + 2)));

                }//********************************************************************

                ix1++;

	        }//while

	    


            krypton_database_driver.conn.commit();
            System.out.println("Committed the transaction");


        }catch(Exception e){e.printStackTrace();}


        network.database_in_use = 0;
        return token_ssp2;

    }//load




}//class
