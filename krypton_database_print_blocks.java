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

import java.awt.datatransfer.*;


public class krypton_database_print_blocks{


    int ix0 = 0;
    int ix1 = 0;
    int ix2 = 0;




    krypton_database_print_blocks(){//**************************************************************************
    network.database_in_use = 1;



        try{




                System.out.println("Loading 4");
 
                krypton_database_driver.s = krypton_database_driver.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                krypton_database_driver.s.setMaxRows(30);
                krypton_database_driver.rs = krypton_database_driver.s.executeQuery("SELECT link_id,mining_date,mining_old_block,mining_new_block FROM mining_db ORDER BY mining_date DESC");
                while(krypton_database_driver.rs.next()){

                    try{

                    //System.out.println(rs.getString("id"));
                    //System.out.println(rs.getString("mining_date"));
                    //System.out.println(rs.getString("mining_old_block"));
                    //System.out.println(rs.getString("mining_new_block"));
                    //System.out.println(rs.getString("seller_id"));  

                    System.out.println("BLOCK: " + krypton_database_driver.rs.getString("link_id") + " " + krypton_database_driver.rs.getString("mining_date") + " " + krypton_database_driver.rs.getString("mining_old_block").substring(0,18) + " " + krypton_database_driver.rs.getString("mining_new_block").substring(0,18));
                    }catch(Exception e){e.printStackTrace();}



                }//**************


                System.out.println("-----------------------------------------------");

                System.out.println("Loading 4");
 
                krypton_database_driver.s = krypton_database_driver.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                krypton_database_driver.s.setMaxRows(20);
                krypton_database_driver.rs = krypton_database_driver.s.executeQuery("SELECT id,hash_id FROM unconfirmed_db ORDER BY xd ASC");
                while(krypton_database_driver.rs.next()){

                    try{

                    System.out.println("BLOCK: " + krypton_database_driver.rs.getString("id") + " " + krypton_database_driver.rs.getString("hash_id").substring(0,18));
                    }catch(Exception e){e.printStackTrace();}

                }//**************







                krypton_database_driver.conn.commit();
                System.out.println("Committed the transaction");



        }catch(Exception e){e.printStackTrace();}



    network.database_in_use = 0;

    }//load




}//class
