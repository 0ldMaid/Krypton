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


public class mining_new_task_c{



    int ix0 = 0;
    int ix1 = 0;
    int ix2 = 0;

    String move_item[] = new String[network.listing_size];


    String[] new_task_c(){//**************************************************************************
    network.database_in_use = 1;



        try{

	


                System.out.println("Mining new task c...");





                        krypton_database_driver.s = krypton_database_driver.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                        krypton_database_driver.s.setMaxRows(1); 
                        krypton_database_driver.rs = krypton_database_driver.s.executeQuery("SELECT mining_new_block,hash_id FROM mining_db ORDER BY mining_date DESC");
                        while(krypton_database_driver.rs.next()){

                                network.last_block_mining_idx = krypton_database_driver.rs.getString("mining_new_block");
                                network.last_block_idx = krypton_database_driver.rs.getString("hash_id");

                        }//**************

                        System.out.println("network.last_block_idx " + network.last_block_idx);
                        System.out.println("network.last_block_mining_idx " + network.last_block_mining_idx);








                        int numberx = 0;

                        //get the number of blocks to save
                        krypton_database_driver.s = krypton_database_driver.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                        krypton_database_driver.s.setMaxRows(1); 
                        krypton_database_driver.rs = krypton_database_driver.s.executeQuery("SELECT xd FROM mining_db ORDER BY mining_date DESC");
                        while(krypton_database_driver.rs.next()){

                            numberx = krypton_database_driver.rs.getInt("xd");

                        }//**************




                        System.out.println("Last Block ID " + numberx);

                        numberx = numberx - (network.hard_token_limit + network.confirm_before_delete);

                        System.out.println("Delete ID " + numberx);

                        //delete old blocks
                        System.out.println("Load ITEM... And delete" );

                        if(numberx > 0){

                            krypton_database_driver.s2.execute("DELETE FROM mining_db where xd < " + numberx);

                        }//if***********





                        System.out.println("MOVE THE CHAIN....");

                        mining.new_block_id = "";
                        mining.new_block_hash = "";

                        String block_idx = new String("");
                        move_item = new String[network.listing_size];


    
                        System.out.println("Load ITEM TASK..." );

                        //s = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                        krypton_database_driver.s.setMaxRows(1); 
                        krypton_database_driver.rs = krypton_database_driver.s.executeQuery("SELECT * FROM listings_db ORDER BY xd ASC");

                        ix0 = 0;
                        while(krypton_database_driver.rs.next()){


                                mining.new_block_id = krypton_database_driver.rs.getString("id");
                                mining.new_block_hash = krypton_database_driver.rs.getString("hash_id");

                                move_item[0] = Integer.toString(krypton_database_driver.rs.getInt(2));
                                block_idx = move_item[0];
                                //build_hash = build_hash + Integer.toString(rs.getInt(1));

                                for (int loop = 1; loop < network.listing_size; loop++){

                                    try{

                                        //System.out.println("GET ITEM P " + move_item[loop]);
                                        move_item[loop] = krypton_database_driver.rs.getString((loop + 2));
                                        
                                    }catch(Exception e){e.printStackTrace(); System.exit(0);}

                                }//*****************************************************



                                String build_hash = new String("");
                                build_hash = build_hash + move_item[0];
                                for (int loop = 3; loop < move_item.length; loop++){

                                    build_hash = build_hash + move_item[loop];//save everything else

                                }//*************************************************

                                try{

                                    byte[] sha256_1w = MessageDigest.getInstance("SHA-256").digest(build_hash.getBytes());
                                    mining.new_block_hash = Base64.toBase64String(sha256_1w);

                                }catch(Exception e){e.printStackTrace();} 



                        }//while

        


                mining.new_block_id = move_item[0];
                //mining.new_block_hash = move_item[1];






                krypton_database_driver.conn.commit();
                System.out.println("Committed the transaction");


        }catch(Exception e){e.printStackTrace();}


    network.database_in_use = 0;

    return move_item;

    }//load




 

}//load
