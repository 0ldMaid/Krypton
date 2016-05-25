import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;  
import java.lang.Object.*;  
import java.net.*;

import java.awt.Toolkit;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Properties;




public class krypton_database_new_task{




    int ix0 = 0;
    int ix1 = 0;
    int ix2 = 0;




    krypton_database_new_task(){//**************************************************************************
    
	
        try{



                System.out.println("Load listings db..." );

                krypton_database_driver.s = krypton_database_driver.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                krypton_database_driver.s.setMaxRows(1); 
                krypton_database_driver.rs = krypton_database_driver.s.executeQuery("SELECT mining_new_block FROM listings_db ORDER BY mining_date DESC");

	            ix0 = 0;
	            while(krypton_database_driver.rs.next()){

	               //System.out.println(rs.getString(1));
	               System.out.println("rs net " + krypton_database_driver.rs.getString(1));

                   mining.old_block_mining_hash = krypton_database_driver.rs.getString(1);

	               ix0++;

	            }//while


                System.out.println("Load unconfirmed db..." );

                krypton_database_driver.s = krypton_database_driver.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                krypton_database_driver.s.setMaxRows(1); 
                krypton_database_driver.rs = krypton_database_driver.s.executeQuery("SELECT id,hash_id FROM unconfirmed_db ORDER BY date_id DESC");

                ix1 = 0;
                ix2 = 0;
                while(krypton_database_driver.rs.next()){

                    //System.out.println(rs.getString(1));
                    System.out.println("rs net " + krypton_database_driver.rs.getString(1));

                    mining.new_block_id = Integer.toString(krypton_database_driver.rs.getInt(1));
                    mining.new_block_hash = krypton_database_driver.rs.getString(2);

                ix1++;

                }//while


                //delete stale blocks

                System.out.println("Load ITEM... And delete" );

                //s = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                //s.setMaxRows(1); 

                krypton_database_driver.rs = krypton_database_driver.s.executeQuery("SELECT id,mining_new_block FROM listings_db ORDER BY mining_date ASC");

                //delete stale blocks
                while(krypton_database_driver.rs.next()){

                    if(krypton_database_driver.rs.getInt(1) == 0){
                        System.out.println("DELETE >>> " + krypton_database_driver.rs.getString(2)); 
                        krypton_database_driver.s2.execute("DELETE FROM listings_db where mining_new_block='" + krypton_database_driver.rs.getString(2) + "'");
                    }//if*****************
                    else{break;}

                }//while********






                //if no mining work... move the chain along...
                if(ix1 < 1){//**********************************************************************

                        System.out.println("MOVE THE CHAIN....");

                        String block_idx = new String("");
                        String build_hash = new String("");
                        String move_item[] = new String[network.listing_size];


    
                        System.out.println("Load ITEM..." );

                        //s = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                        krypton_database_driver.s.setMaxRows(1); 
                        krypton_database_driver.rs = krypton_database_driver.s.executeQuery("SELECT * FROM listings_db ORDER BY mining_date ASC");

                        ix0 = 0;
                        while(krypton_database_driver.rs.next()){


                                move_item[0] = Integer.toString(krypton_database_driver.rs.getInt(1));
                                block_idx = move_item[0];
                                //build_hash = build_hash + Integer.toString(rs.getInt(1));

                                move_item[1] = krypton_database_driver.rs.getString(2);
                                //build_hash = build_hash + rs.getString(2);


                                for (int loop = 2; loop < network.listing_size; loop++){

                                    try{

                                        move_item[loop] = krypton_database_driver.rs.getString((loop + 1));
                                        build_hash = build_hash + krypton_database_driver.rs.getString((loop + 1));
                                        //System.out.println("GET ITEM P " + move_item[loop]);

                                    }catch(Exception e){e.printStackTrace(); System.exit(0);}

                                }//*****************************************************


                        }//while

        



                        if(Integer.parseInt(move_item[0]) != 0){//********************************************************************************************

                        System.out.println("UPDATE");

                        //boolean delete_old = s.execute("DELETE FROM listings_db where id=" + mining.new_block_id);
                        //boolean delete_old = s.execute("UPDATE listings_db set id=0 where id=" + block_idx);
                        //System.out.println("UPDATE id " + delete_old);


                        int numrows = 0;
                        PreparedStatement ps = null;
                        ps = krypton_database_driver.conn.prepareStatement("INSERT INTO unconfirmed_db(id, mining_date, mining_difficulty, mining_noose, mining_old_block, mining_new_block, noose, date_id, owner_id, previous_hash_id, hash_id, sig_id, owner_rating, currency, custom_template, custom_1, custom_2, custom_3, item_errors, item_date_listed, item_date_listed_day, item_date_listed_int, item_hits, item_confirm_code, item_confirmed, item_cost, item_description, item_id, item_price, item_weight, item_listing_id, item_notes, item_package_d, item_package_l, item_package_w, item_part_number, item_title, item_title_url, item_type, item_search_1, item_search_2, item_search_3, item_site_id, item_site_url, item_picture_1, item_total_on_hand, sale_payment_address, sale_payment_type, sale_fees, sale_id, sale_seller_id, sale_status, sale_tax, sale_shipping_company, sale_shipping_in, sale_shipping_out, sale_source_of_sale, sale_total_sale_amount, sale_tracking_number, sale_transaction_id, sale_transaction_info, seller_address_1, seller_address_2, seller_address_city, seller_address_state, seller_address_zip, seller_address_country, seller_id, seller_ip, seller_email, seller_first_name, seller_last_name, seller_notes, seller_phone, seller_logo, seller_url) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

                        ps.setInt(1, Integer.parseInt(block_idx));
                        ps.setString(2, Long.toString(System.currentTimeMillis()));

                            for(int loop1 = 2; loop1 < network.listing_size; loop1++){//**********************************

                                ps.setString((loop1 + 1), move_item[(loop1)]);
                                //System.out.println("PS UPDATE " + move_item[(loop1)]);

                            }//*******************************************************************************************

                        numrows = numrows + ps.executeUpdate();
                        ps.close();

                        }//if*********************************************************************************************************************************

                        System.out.println("DONE");






                        System.out.println("Load listings db..." );

                        krypton_database_driver.s = krypton_database_driver.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                        krypton_database_driver.s.setMaxRows(1); 

                        krypton_database_driver.rs = krypton_database_driver.s.executeQuery("SELECT mining_new_block FROM listings_db ORDER BY mining_date DESC");

                        ix0 = 0;
                        while(krypton_database_driver.rs.next()){

                            //System.out.println(rs.getString(1));
                            System.out.println("rs net " + krypton_database_driver.rs.getString(1));

                            mining.old_block_mining_hash = krypton_database_driver.rs.getString(1);

                            ix0++;

                        }//while


                        System.out.println("Load unconfirmed db..." );

                        krypton_database_driver.s = krypton_database_driver.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                        krypton_database_driver.s.setMaxRows(1); 

                        krypton_database_driver.rs = krypton_database_driver.s.executeQuery("SELECT id,hash_id FROM unconfirmed_db ORDER BY date_id DESC");

                        ix1 = 0;
                        ix2 = 0;
                        while(krypton_database_driver.rs.next()){

                            //System.out.println(rs.getString(1));
                            System.out.println("rs net " + krypton_database_driver.rs.getString(1));

                            mining.new_block_id = Integer.toString(krypton_database_driver.rs.getInt(1));
                            mining.new_block_hash = krypton_database_driver.rs.getString(2);

                        ix1++;

                        }//while






                }//if*******************************************************************************







            krypton_database_driver.conn.commit();
            System.out.println("Committed the transaction");


        }catch(Exception e){e.printStackTrace();}


    }//load




 

}//class
