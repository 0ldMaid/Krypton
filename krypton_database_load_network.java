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




public class krypton_database_load_network{




    int ix0 = 0;
    int ix1 = 0;
    int ix2 = 0;

    int database_test = 0;


    krypton_database_load_network(){//**************************************************************************
    network.database_in_use = 1;

        try{



                network.network_list = new ArrayList<String>();

                System.out.println("Load Network GX..." );

                krypton_database_driver.rs = krypton_database_driver.s.executeQuery("SELECT * FROM network ORDER BY address");

	            ix0 = 0;
	            while(krypton_database_driver.rs.next()){

	                //System.out.println(rs.getString(1));
	                System.out.println("rs net " + krypton_database_driver.rs.getString(1));

                    network.network_list.add(krypton_database_driver.rs.getString(1));

	                ix0++;

	            }//while

	            network.network_size = ix0;

	            System.out.println("network size " + network.network_size);






                krypton_database_driver.s = krypton_database_driver.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                krypton_database_driver.rs = krypton_database_driver.s.executeQuery("SELECT id FROM listings_db");

                krypton_database_driver.rs.last();
                int rowCount5 = krypton_database_driver.rs.getRow();
                network.database_listings_total = rowCount5;

                System.out.println("database TOTAL " + network.database_listings_total);







	            krypton_database_driver.rs = krypton_database_driver.s.executeQuery("SELECT id FROM settings ORDER BY id");

	            krypton_database_driver.rs.last();
	            int rowCount3 = krypton_database_driver.rs.getRow();

	            database_test = rowCount3;

	            System.out.println("database " + database_test);






                for (int loop = 0; loop < network.settingsx.length; loop++){

                    network.settingsx[loop] = new String("0");

                }//*********************************************************


	            krypton_database_driver.rs = krypton_database_driver.s.executeQuery("SELECT valuex FROM settings ORDER BY id");

	            ix0 = 0;
	            while(krypton_database_driver.rs.next()){

	               System.out.println("rs settings: " + krypton_database_driver.rs.getString(1));

	               network.settingsx[ix0] = new String(krypton_database_driver.rs.getString(1));

	               ix0++;

	            }//while






                System.out.println("Loading 1");

                try{

                    network.base58_id = network.settingsx[5];

                    int len = network.base58_id.length();
                    byte[] data = new byte[len / 2];

                        for (int i = 0; i < len; i += 2) {
                            data[i / 2] = (byte) ((Character.digit(network.base58_id.charAt(i), 16) << 4) + Character.digit(network.base58_id.charAt(i+1), 16));
                        }//*******************************

                    byte[] sha256_1 = MessageDigest.getInstance("SHA-256").digest(data);

                    network.base58_id = Base58Encode.encode(sha256_1);

                }catch(Exception e){e.printStackTrace();}


                System.out.println("base58 " + network.base58_id);





                System.out.println("Load QL Token..." );


                network.html_block_ql = new String[network.listing_size];

                krypton_database_driver.rs = krypton_database_driver.s.executeQuery("SELECT * FROM listings_db WHERE id=100000");

                ix0 = 0;
                while(krypton_database_driver.rs.next()){


                  for(int loop1 = 0; loop1 < network.listing_size; loop1++){//***********

                       network.html_block_ql[loop1] = new String(krypton_database_driver.rs.getString((loop1 + 2)));

                  }//********************************************************************


                }//while

        
                System.out.println("network size " + network.network_list.size());


                if(database_test == 23){network.database_active = 1; System.out.println("DATABASE ACTIVE YES. " + network.database_active);}
                System.out.println("DB LOADED...");


                krypton_database_driver.conn.commit();
                System.out.println("Committed the transaction");




        }catch(Exception e){e.printStackTrace();}

    network.database_in_use = 0;

    }//load




 

}//load network
