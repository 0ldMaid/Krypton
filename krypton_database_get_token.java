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




public class krypton_database_get_token{
    /* the default framework is embedded*/


    String[] token_ssp2 = new String[network.listing_size];


    int ix0 = 0;
    int ix1 = 0;
    int ix2 = 0;




    public String[] get_token(String id){//**************************************************************************
    network.database_in_use = 1;

        try{


            int idx = 0;



            try{

                idx = Integer.parseInt(id);

            }catch(Exception e){}





            for(int loop1 = 0; loop1 < network.listing_size; loop1++){//***********

                token_ssp2[loop1] = new String("error");

            }//********************************************************************





            System.out.println("Load Token..." );

            krypton_database_driver.rs = krypton_database_driver.s.executeQuery("SELECT * FROM listings_db WHERE id=" + idx + " ORDER BY id ASC");

	        ix0 = 0;
	        while(krypton_database_driver.rs.next()){


                for(int loop1 = 0; loop1 < network.listing_size; loop1++){//***********

                    token_ssp2[loop1] = new String(krypton_database_driver.rs.getString((loop1 + 2)));

                }//********************************************************************


	        }//while

	    
	        System.out.println("network size " + network.network_list.size());




            krypton_database_driver.conn.commit();
            System.out.println("Committed the transaction");



        }catch(Exception e){e.printStackTrace();}



        network.database_in_use = 0;
        return token_ssp2;

    }//load






    public String[] get_token2(String id){//**************************************************************************
    //network.database_in_use = 1;

        try{


            int idx = 0;



            try{

                idx = Integer.parseInt(id);

            }catch(Exception e){}





            for(int loop1 = 0; loop1 < network.listing_size; loop1++){//***********

                token_ssp2[loop1] = new String("error");

            }//********************************************************************





            System.out.println("Load Token..." );

            krypton_database_driver.rse = krypton_database_driver.se.executeQuery("SELECT * FROM listings_db WHERE id=" + idx + " ORDER BY id ASC");

            ix0 = 0;
            while(krypton_database_driver.rse.next()){


                for(int loop1 = 0; loop1 < network.listing_size; loop1++){//***********

                    token_ssp2[loop1] = new String(krypton_database_driver.rse.getString((loop1 + 2)));

                }//********************************************************************


            }//while

        
            System.out.println("network size " + network.network_list.size());



            krypton_database_driver.conn.commit();
            System.out.println("Committed the transaction");



        }catch(Exception e){e.printStackTrace();}



        //network.database_in_use = 0;
        return token_ssp2;

    }//load





 
}//get
