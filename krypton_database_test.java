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




public class krypton_database_test{




    static Long longstamp_hold = (long) 0;

    int ix0 = 0;
    int ix1 = 0;
    int ix2 = 0;




    krypton_database_test(){//**************************************************************************
    //network.database_in_use = 1;
    


        try{



            Long thisTick = System.currentTimeMillis();


            //Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            if(network.new_database_start == 0){

                System.out.println("Loading 1");

                network.my_listings = new ArrayList();
                krypton_database_driver.s = krypton_database_driver.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                //s.setMaxRows(100); 
                krypton_database_driver.rs = krypton_database_driver.s.executeQuery("SELECT id FROM listings_db WHERE seller_id='" + network.base58_id + "'");

                int rowCount4 = 0;
                while(krypton_database_driver.rs.next()){

                   if(krypton_database_driver.rs.getInt(1) >= 0){network.my_listings.add(krypton_database_driver.rs.getString(1)); rowCount4++;}

                }//while


                network.database_listings_owner = rowCount4;
                network.database_listings_for_edit = network.my_listings.size();

                System.out.println("network.database OWNER1 " + network.database_listings_owner);
                System.out.println("network.database OWNER2 " + network.my_listings.size());

            }//if








                System.out.println("Loading 2");


                //s = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                krypton_database_driver.rs = krypton_database_driver.s.executeQuery("SELECT xd FROM send_buffer");

                krypton_database_driver.rs.last();
                int rowCountbu = krypton_database_driver.rs.getRow();
                network.send_buffer_size = rowCountbu;

                System.out.println("network.send_buffer_size " + network.send_buffer_size);






                System.out.println("Loading 3");


                //s = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                krypton_database_driver.rs = krypton_database_driver.s.executeQuery("SELECT hash_id FROM unconfirmed_db ORDER BY xd DESC");

                krypton_database_driver.rs.last();
                int rowCount5u = krypton_database_driver.rs.getRow();
                network.database_unconfirmed_total = rowCount5u;

                System.out.println("<<>>");

                if(rowCount5u > 0){

                    krypton_database_driver.rs.first();
                    network.last_unconfirmed_idx = krypton_database_driver.rs.getString("hash_id");

                }
                else{network.last_unconfirmed_idx = "";}

                System.out.println("network.unconfirmed TOTAL " + network.database_unconfirmed_total);
                System.out.println("network.last_unconfirmed_idx " + network.last_unconfirmed_idx);






                System.out.println("Loading 4");

                network.last_block_ql = new String[network.listing_size];
                longstamp_hold = network.last_block_longstamp1;

                System.out.println("Loading 4 1");

                krypton_database_driver.s = krypton_database_driver.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                //s.setMaxRows(1); 
                krypton_database_driver.rs = krypton_database_driver.s.executeQuery("SELECT link_id,mining_date,mining_new_block,mining_old_block,hash_id FROM mining_db ORDER BY xd DESC FETCH FIRST ROW ONLY");
                
                System.out.println("Loading 4 2");

                krypton_database_driver.rs.first();

                try{network.last_block_longstamp2 = longstamp_hold; network.last_block_longstamp1 = Long.parseLong(krypton_database_driver.rs.getString("mining_date"));} 
                catch(Exception e){System.out.println("Cannot get last block timestamp.");}

                network.last_block_id = krypton_database_driver.rs.getString("link_id");
                network.last_block_mining_idx = krypton_database_driver.rs.getString("mining_new_block");
                network.prev_block_mining_idx = krypton_database_driver.rs.getString("mining_old_block");
                network.last_block_idx = krypton_database_driver.rs.getString("hash_id");

                System.out.println("network.last_block_id " + network.last_block_id);
                System.out.println("network.last_block_idx " + network.last_block_idx);
                System.out.println("network.last_block_mining_idx " + network.last_block_mining_idx);
                System.out.println("last_block_longstamp1 " + network.last_block_longstamp1);
                System.out.println("last_block_longstamp2 " + network.last_block_longstamp2);







                System.out.println("Loading 5");

                //testing
                //load last few blocks to build difficulty
                network.block_difficulty_test = 0;
                network.block_difficulty_listx = new String[network.block_difficulty_reset];
                network.block_date_listx = new String[network.block_difficulty_reset];

                //s = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                krypton_database_driver.s.setMaxRows(network.block_difficulty_reset); 
                krypton_database_driver.rs = krypton_database_driver.s.executeQuery("SELECT mining_difficulty,mining_date FROM mining_db ORDER BY mining_date DESC");

                while(krypton_database_driver.rs.next()){

                    try{

                        network.block_difficulty_listx[network.block_difficulty_test] = new String(krypton_database_driver.rs.getString("mining_difficulty"));
                        network.block_date_listx[network.block_difficulty_test] = new String(krypton_database_driver.rs.getString("mining_date"));

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

                    Long l3 = (long) l1 - l2;
                    Long xlx = (long) 0;

                        for (int loop = 0; loop < network.block_difficulty_reset; loop++){

                            xlx = (long) (xlx + Long.parseLong(network.block_difficulty_listx[loop]));

                        }//***************************************************************

                    System.out.println("xlx " + xlx);

                    xlx = (long) (xlx / network.block_difficulty_reset);

                    System.out.println("xlx2 " + xlx);


                        if(l3 > 0){

                            Long l4 = (long) l3 / network.block_difficulty_reset;

                            network.blocktimesx = l4;

                                System.out.println("network.difficultyx a " + network.difficultyx);
                                System.out.println("network.blocktimesx " + network.blocktimesx);

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

                                System.out.println("network.blocktimesx " + network.blocktimesx);
                                System.out.println("network.target_block_speed " + network.target_block_speed);

                                if(network.blocktimesx < (network.target_block_speed / 2)){System.out.println("DIFFICULTY UP X2"); network.difficultyx = new_difficulty_up_x2;}
                                else if(network.blocktimesx > (network.target_block_speed * 2)){System.out.println("DIFFICULTY DOWN X2"); network.difficultyx = new_difficulty_down_x2;}
                                else if(network.blocktimesx < network.target_block_speed){System.out.println("DIFFICULTY UP"); network.difficultyx = new_difficulty_up;}
                                else if(network.blocktimesx > network.target_block_speed){System.out.println("DIFFICULTY DOWN"); network.difficultyx = new_difficulty_down;}
                                else{System.out.println("DIFFICULTY NO CHANGE");}

                                if(network.difficultyx < 0){network.difficultyx = network.difficultyx_limit;}
                                if(network.difficultyx > network.difficultyx_limit){network.difficultyx = network.difficultyx_limit;}

                                if(network.new_database_start == 1){network.difficultyx = network.difficultyx_limit;}

                                System.out.println("network.difficultyx b " + network.difficultyx);

                        }//********

                }//*********************************************************
                else{}





	           if(network.database_active == 11){network.database_active = 1;}
	           System.out.println("DB LOADED...");




                System.out.println(System.currentTimeMillis() - thisTick);





                krypton_database_driver.conn.commit();
                System.out.println("Committed the transaction");


        }catch(Exception e){e.printStackTrace();}


    }//load




 


//start the program.
    public static void main(String[] args) {

        krypton_database_test black = new krypton_database_test();

    }//main




}//class
