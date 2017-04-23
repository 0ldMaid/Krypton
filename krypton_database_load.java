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




public class krypton_database_load{



    static Long longstamp_hold = (long) 0;

    int ix0 = 0;
    int ix1 = 0;
    int ix2 = 0;

    int database_test = 0;


    krypton_database_load(){//**************************************************************************
    network.database_in_use = 1;

        Long thisTick = System.currentTimeMillis();


        try{



            //Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            if(network.new_database_start == 0){

                System.out.println("Loading 1");
                System.out.println("base58_id " + network.base58_id);


                network.my_listings = new ArrayList<String>();
                krypton_database_driver.s = krypton_database_driver.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                //s.setMaxRows(100); 
                krypton_database_driver.rs = krypton_database_driver.s.executeQuery("SELECT id FROM listings_db WHERE seller_id='" + network.base58_id + "' ORDER BY id ASC");

                int rowCount4 = 0;
                while(krypton_database_driver.rs.next()){

                    network.my_listings.add(krypton_database_driver.rs.getString(1)); 
                    rowCount4++;

                }//while


                network.database_listings_owner = rowCount4;
                network.database_listings_for_edit = network.my_listings.size();

                System.out.println("database OWNER1 " + network.database_listings_owner);
                System.out.println("database OWNER2 " + network.my_listings.size());

            }//if





            System.out.println("Loading 2");


            krypton_database_driver.s = krypton_database_driver.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            krypton_database_driver.rs = krypton_database_driver.s.executeQuery("SELECT xd FROM send_buffer");

            krypton_database_driver.rs.last();
            int rowCountbu = krypton_database_driver.rs.getRow();
            network.send_buffer_size = rowCountbu;

            System.out.println("network.send_buffer_size " + network.send_buffer_size);






            System.out.println("Loading 3");


            krypton_database_driver.s = krypton_database_driver.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            krypton_database_driver.rs = krypton_database_driver.s.executeQuery("SELECT xd,id,hash_id FROM unconfirmed_db ORDER BY xd DESC");

            krypton_database_driver.rs.last();
            int rowCount5u = krypton_database_driver.rs.getRow();
            network.database_unconfirmed_total = rowCount5u;

            System.out.println("<<>>");

            if(rowCount5u > 0){

                krypton_database_driver.rs.first();
                network.last_unconfirmed_idx = krypton_database_driver.rs.getString("hash_id");

            }
            else{network.last_unconfirmed_idx = "";}

            System.out.println("unconfirmed TOTAL " + network.database_unconfirmed_total);
            System.out.println("last_unconfirmed_idx " + network.last_unconfirmed_idx);






            System.out.println("Loading 4");

            network.last_block_ql = new String[network.listing_size];
            longstamp_hold = network.last_block_longstamp1;

            krypton_database_driver.s = krypton_database_driver.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            krypton_database_driver.s.setMaxRows(1); 
            krypton_database_driver.rs = krypton_database_driver.s.executeQuery("SELECT link_id,mining_date,mining_new_block,mining_old_block,hash_id,package FROM mining_db ORDER BY mining_date DESC");
            while(krypton_database_driver.rs.next()){

                try{network.last_block_longstamp2 = longstamp_hold; network.last_block_longstamp1 = Long.parseLong(krypton_database_driver.rs.getString("mining_date"));} 
                catch(Exception e){System.out.println("Cannot get last block timestamp.");}

                network.last_block_id = krypton_database_driver.rs.getString("link_id");
                network.last_block_mining_idx = krypton_database_driver.rs.getString("mining_new_block");
                network.prev_block_mining_idx = krypton_database_driver.rs.getString("mining_old_block");
                network.last_block_idx = krypton_database_driver.rs.getString("hash_id");
                network.last_block_timestamp = krypton_database_driver.rs.getString("mining_date");
                network.last_package_x = krypton_database_driver.rs.getString("package");

            }//**************************************

            //System.out.println("last_block_ql[0] " + last_block_ql[0]);
            System.out.println("last_block_id " + network.last_block_id);
            System.out.println("last_block_idx " + network.last_block_idx);
            System.out.println("last_block_mining_idx " + network.last_block_mining_idx);
            System.out.println("last_block_longstamp1 " + network.last_block_longstamp1);
            System.out.println("last_block_longstamp2 " + network.last_block_longstamp2);







            System.out.println("Loading 5");

            //testing
            //load last few blocks to build difficulty
            network.block_difficulty_test = 0;
            network.block_difficulty_listx = new String[network.block_difficulty_reset];
            network.block_date_listx = new String[network.block_difficulty_reset];

            krypton_database_driver.s = krypton_database_driver.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            krypton_database_driver.s.setMaxRows(network.block_difficulty_reset + 1); 
            krypton_database_driver.rs = krypton_database_driver.s.executeQuery("SELECT link_id,mining_difficulty,mining_date FROM mining_db WHERE mining_difficulty!='" + network.difficultyx_limit + "' ORDER BY mining_date DESC");

            krypton_database_driver.rs.last();

            while(krypton_database_driver.rs.previous()){

                try{

                    //show debug info for db build only....
                    if(network.new_database_start == 1 && 1 == 2){

                        System.out.println("network.block_difficulty_test " + network.block_difficulty_test);

                        System.out.println("id:                " + krypton_database_driver.rs.getInt("id"));
                        System.out.println("mining_date:       " + krypton_database_driver.rs.getString("mining_date"));
                        System.out.println("mining_difficulty: " + krypton_database_driver.rs.getString("mining_difficulty"));
                        System.out.println("mining_noose:      " + krypton_database_driver.rs.getString("mining_noose"));
                        System.out.println("mining_new_block:  " + krypton_database_driver.rs.getString("mining_new_block"));
                        System.out.println("mining_old_block:  " + krypton_database_driver.rs.getString("mining_old_block"));
                        System.out.println("hash_id:           " + krypton_database_driver.rs.getString("hash_id"));
                        System.out.println("");

                    }//*********************************

                    network.block_difficulty_listx[network.block_difficulty_test] = new String(krypton_database_driver.rs.getString("mining_difficulty"));
                    network.block_date_listx[network.block_difficulty_test] = new String(krypton_database_driver.rs.getString("mining_date"));

                    //System.out.println("<" + krypton_database_driver.rs.getInt("link_id") + " " + krypton_database_driver.rs.getString("mining_difficulty"));
                    //if(krypton_database_driver.rs.getString("mining_difficulty").equals(network.difficultyx_limit)){JOptionPane.showMessageDialog(null, "440000");}

                    network.block_difficulty_test++;
                    
                }catch(Exception e){}

            }//while


            System.out.println("Loading 6");

            //set difficulty
            if(network.block_difficulty_test == network.block_difficulty_reset){

                Long l1 = (long) Long.parseLong( network.block_date_listx[0] );
                Long l2 = (long) Long.parseLong( network.block_date_listx[(network.block_difficulty_reset - 1)] );

                System.out.println("l1 " + l1);
                System.out.println("l2 " + l2);

                Long l3 = (long) l2 - l1;
                Long xlx = (long) 0;

                    for (int loop = 0; loop < network.block_difficulty_reset; loop++){

                        xlx = (long) (xlx + Long.parseLong(network.block_difficulty_listx[loop]));

                    }//***************************************************************

                System.out.println("xlx1 " + xlx);

                xlx = (long) (xlx / network.block_difficulty_reset);

                System.out.println("xlx2 " + xlx);

                System.out.println("l3 " + l3);

                    if(l3 > 0){

                        Long l4 = (long) l3 / network.block_difficulty_reset;

                        network.blocktimesx = l4;

                        System.out.println("difficultyx a " + network.difficultyx);
                        System.out.println("blocktimesx " + network.blocktimesx);

                        Float percentx = (float) network.target_block_adjustment / 100;
                        System.out.println("percentx " + percentx);

                        long change = (long) (xlx * percentx);
                        System.out.println("change " + change);

                        long new_difficulty_up = (long) (xlx - change);
                        long new_difficulty_down = (long) (xlx + change);
                        long new_difficulty_up_x2 = (long) (xlx - (change * 5));
                        long new_difficulty_down_x2 = (long) (xlx + (change * 5));
                        System.out.println("new_difficulty_up " + new_difficulty_up);
                        System.out.println("new_difficulty_down " + new_difficulty_down);

                        System.out.println("blocktimesx " + network.blocktimesx);
                        System.out.println("target_block_speed " + network.target_block_speed);

                        if(network.blocktimesx < (network.target_block_speed / 2)){System.out.println("DIFFICULTY UP X2"); network.difficultyx = new_difficulty_up_x2;}
                        else if(network.blocktimesx > (network.target_block_speed * 2)){System.out.println("DIFFICULTY DOWN X2"); network.difficultyx = new_difficulty_down_x2;}
                        else if(network.blocktimesx < network.target_block_speed){System.out.println("DIFFICULTY UP"); network.difficultyx = new_difficulty_up;}
                        else if(network.blocktimesx > network.target_block_speed){System.out.println("DIFFICULTY DOWN"); network.difficultyx = new_difficulty_down;}
                        else{System.out.println("DIFFICULTY NO CHANGE");}

                        if(network.difficultyx < 0){network.difficultyx = network.difficultyx_limit;}
                        if(network.difficultyx > network.difficultyx_limit){network.difficultyx = network.difficultyx_limit;}

                        if(network.new_database_start == 1){network.difficultyx = network.difficultyx_limit;}

                        System.out.println("difficultyx b " + network.difficultyx);

                    }//********

            }//*********************************************************
            else{}


	        System.out.println("DB LOADED...");


            krypton_database_driver.conn.commit();
            System.out.println("Committed the transaction");




        }catch(Exception e){e.printStackTrace();}



        network.loaddbx_longstamp = System.currentTimeMillis() - thisTick;
        network.database_in_use = 0;

    }//load




 

}//load
