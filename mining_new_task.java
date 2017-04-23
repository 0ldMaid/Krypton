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

import org.spongycastle.util.encoders.Base64;




public class mining_new_task{




int ix0 = 0;
int ix1 = 0;
int ix2 = 0;

String move_item[] = new String[network.listing_size];




    String[] new_task(){//**************************************************************************
    network.database_in_use = 1;


        try{


            System.out.println("Mining new task...");



            //make sure old mining hash is OK
            System.out.println("Loading OLD mining hash ");

            krypton_database_driver.s = krypton_database_driver.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            krypton_database_driver.s.setMaxRows(1); 
            krypton_database_driver.rs = krypton_database_driver.s.executeQuery("SELECT mining_new_block,hash_id FROM mining_db ORDER BY mining_date DESC");
            while(krypton_database_driver.rs.next()){

                network.last_block_mining_idx = krypton_database_driver.rs.getString("mining_new_block");
                network.last_block_idx = krypton_database_driver.rs.getString("hash_id");

            }//**************

            System.out.println("network.last_block_idx " + network.last_block_idx);
            System.out.println("network.last_block_mining_idx " + network.last_block_mining_idx);







            mining.new_block_id = "";
            mining.new_block_hash = "";


            System.out.println("Load unconfirmed db..." );

            krypton_database_driver.s = krypton_database_driver.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            krypton_database_driver.s.setMaxRows(1); 
            krypton_database_driver.rs = krypton_database_driver.s.executeQuery("SELECT id,hash_id FROM unconfirmed_db ORDER BY xd ASC");

            ix1 = 0;
            ix2 = 0;
            while(krypton_database_driver.rs.next()){

                //System.out.println(rs.getString(1));
                System.out.println("rs net " + krypton_database_driver.rs.getString(1));
                mining.new_block_id = Integer.toString(krypton_database_driver.rs.getInt(1));
                mining.new_block_hash = krypton_database_driver.rs.getString(2);

                ix1++;

            }//while






            if(mining.new_block_id.length() != 0){


                move_item = new String[network.listing_size];

        
                System.out.println("MINING NEW BLOCK Load UNCONFIRMED ITEM..." );

                krypton_database_driver.s = krypton_database_driver.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                krypton_database_driver.rs = krypton_database_driver.s.executeQuery("SELECT * FROM unconfirmed_db WHERE id=" + mining.new_block_id);

                ix0 = 0;
                while(krypton_database_driver.rs.next()){


                    move_item[0] = Integer.toString(krypton_database_driver.rs.getInt(2));

                    for (int loop = 1; loop < network.listing_size; loop++){

                        try{

                            move_item[loop] = krypton_database_driver.rs.getString((loop + 2));

                        }catch(Exception e){e.printStackTrace();}//*****************

                    }//*****************************************************


                }//while



                String build_hash = new String("");
                build_hash = build_hash + move_item[0];
                for (int loop = 3; loop < move_item.length; loop++){

                    build_hash = build_hash + move_item[loop];//save everything else

                }//*************************************************

                try{

                    byte[] sha256_1w = MessageDigest.getInstance("SHA-256").digest(build_hash.getBytes());
                    mining.new_block_hash = Base64.toBase64String(sha256_1w);

                }catch(Exception e){e.printStackTrace();} 

            }//if*********************************




            krypton_database_driver.conn.commit();
            System.out.println("Committed the transaction");


        }catch(Exception e){e.printStackTrace();}

        network.database_in_use = 0;
        return move_item;

    }//load





    String[] new_task(String id){//**************************************************************************
    network.database_in_use = 1;


        try{


            System.out.println("Mining new task...");



            //make sure old mining hash is OK
            System.out.println("Loading OLD mining hash ");

            krypton_database_driver.s = krypton_database_driver.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            krypton_database_driver.s.setMaxRows(1); 
            krypton_database_driver.rs = krypton_database_driver.s.executeQuery("SELECT mining_new_block,hash_id FROM mining_db ORDER BY mining_date DESC");
            while(krypton_database_driver.rs.next()){

                network.last_block_mining_idx = krypton_database_driver.rs.getString("mining_new_block");
                network.last_block_idx = krypton_database_driver.rs.getString("hash_id");

            }//**************

            System.out.println("network.last_block_idx " + network.last_block_idx);
            System.out.println("network.last_block_mining_idx " + network.last_block_mining_idx);







            mining.new_block_id = "";
            mining.new_block_hash = "";


            System.out.println("Load unconfirmed db..." );

            krypton_database_driver.s = krypton_database_driver.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            krypton_database_driver.s.setMaxRows(1); 
            krypton_database_driver.rs = krypton_database_driver.s.executeQuery("SELECT id,hash_id FROM unconfirmed_db WHERE id=" + id);

            ix1 = 0;
            ix2 = 0;
            while(krypton_database_driver.rs.next()){

                //System.out.println(rs.getString(1));
                System.out.println("rs net " + krypton_database_driver.rs.getString(1));
                mining.new_block_id = Integer.toString(krypton_database_driver.rs.getInt(1));
                mining.new_block_hash = krypton_database_driver.rs.getString(2);

                ix1++;

            }//while






            if(mining.new_block_id.length() != 0){


                move_item = new String[network.listing_size];

        
                System.out.println("MINING NEW BLOCK Load UNCONFIRMED ITEM..." );

                krypton_database_driver.s = krypton_database_driver.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                krypton_database_driver.rs = krypton_database_driver.s.executeQuery("SELECT * FROM unconfirmed_db WHERE id=" + mining.new_block_id);

                ix0 = 0;
                while(krypton_database_driver.rs.next()){


                    move_item[0] = Integer.toString(krypton_database_driver.rs.getInt(2));

                    for (int loop = 1; loop < network.listing_size; loop++){

                        try{

                            move_item[loop] = krypton_database_driver.rs.getString((loop + 2));

                        }catch(Exception e){e.printStackTrace();}//*****************

                    }//*****************************************************


                }//while



                String build_hash = new String("");
                build_hash = build_hash + move_item[0];
                for (int loop = 3; loop < move_item.length; loop++){

                    build_hash = build_hash + move_item[loop];//save everything else

                }//*************************************************

                try{

                    byte[] sha256_1w = MessageDigest.getInstance("SHA-256").digest(build_hash.getBytes());
                    mining.new_block_hash = Base64.toBase64String(sha256_1w);

                }catch(Exception e){e.printStackTrace();} 

            }//if*********************************




            krypton_database_driver.conn.commit();
            System.out.println("Committed the transaction");


        }catch(Exception e){e.printStackTrace();}

        network.database_in_use = 0;
        return move_item;

    }//load





 
}//class
