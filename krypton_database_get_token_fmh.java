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




public class krypton_database_get_token_fmh{


    int modsx = network.listing_size + network.miningx_size;

    String[] token_ssp2 = new String[modsx];


    int ix0 = 0;
    int ix1 = 0;
    int ix2 = 0;




    public String[] get_token(String id){//**************************************************************************
    network.database_in_use = 1;



        try{





                System.out.println("GET TOKEN FROM MINING HASH");



                for(int loop1 = 0; loop1 < network.listing_size; loop1++){//***********

                    token_ssp2[loop1] = new String("error");

                }//********************************************************************






                boolean found_item = false;

                int id_mining = -1;



                krypton_database_driver.s = krypton_database_driver.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                krypton_database_driver.s.setMaxRows(1); 
                krypton_database_driver.rs = krypton_database_driver.s.executeQuery("SELECT * FROM mining_db WHERE mining_old_block='" + id + "' ORDER BY mining_date ASC");

                ix0 = 0;
                while(krypton_database_driver.rs.next()){

                  for(int loop1 = 0; loop1 < network.miningx_size; loop1++){//***********

                    token_ssp2[loop1] = new String(krypton_database_driver.rs.getString((loop1 + 2)));

                  }//********************************************************************

                  id_mining = krypton_database_driver.rs.getInt(2);
                  found_item = true;

                }//while

        
                System.out.println("found_item " + found_item);






                System.out.println("Load listings_db... get token FHM");

                //Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                krypton_database_driver.s = krypton_database_driver.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                krypton_database_driver.s.setMaxRows(1); 
                krypton_database_driver.rs = krypton_database_driver.s.executeQuery("SELECT * FROM listings_db WHERE id=" + id_mining + " ORDER BY id ASC");

	            ix0 = 0;
	            while(krypton_database_driver.rs.next()){


                  for(int loop1 = 0; loop1 < network.listing_size; loop1++){//***********

                    token_ssp2[loop1 + network.miningx_size] = new String(krypton_database_driver.rs.getString((loop1 + 2)));

                  }//********************************************************************

                  found_item = true;

	            }//while

	    
	            System.out.println("found_item " + found_item);












                //if the item is too old send them the first block
                if(!found_item){


                    id_mining = -1;


                    System.out.println("Load mining_db first item..." );

                    krypton_database_driver.s.setMaxRows(1); 
                    krypton_database_driver.rs = krypton_database_driver.s.executeQuery("SELECT * FROM mining_db ORDER BY mining_date ASC");

                    ix0 = 0;
                    while(krypton_database_driver.rs.next()){


                        for(int loop1 = 0; loop1 < network.miningx_size; loop1++){//***********

                            token_ssp2[loop1] = new String(krypton_database_driver.rs.getString((loop1 + 2)));

                        }//********************************************************************

                        id_mining = krypton_database_driver.rs.getInt(2);
                        found_item = true;

                    }//while






                    System.out.println("Load mining_db first item..." );

                    krypton_database_driver.s.setMaxRows(1); 
                    krypton_database_driver.rs = krypton_database_driver.s.executeQuery("SELECT * FROM listings_db WHERE id=" + id_mining);

                    ix0 = 0;
                    while(krypton_database_driver.rs.next()){


                        for(int loop1 = 0; loop1 < network.listing_size; loop1++){//***********

                            token_ssp2[loop1 + network.miningx_size] = new String(krypton_database_driver.rs.getString((loop1 + 2)));

                        }//********************************************************************

                        found_item = true;

                    }//while

        
                    System.out.println("found_item " + found_item);




                }//*************




                krypton_database_driver.conn.commit();
                System.out.println("Committed the transaction");


        }catch(Exception e){e.printStackTrace();}

    network.database_in_use = 0;
    return token_ssp2;

    }//load





}//class
