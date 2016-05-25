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




public class krypton_database_delete_buffer{


    String[] token_ssp2 = new String[network.listing_size];


    int ix0 = 0;
    int ix1 = 0;
    int ix2 = 0;




    public boolean delete(String id){//**************************************************************************
    network.database_in_use = 1;



        try{



                krypton_database_driver.s.execute("DELETE FROM send_buffer where id=" + id);



                System.out.println("Loading 2");


                krypton_database_driver.s = krypton_database_driver.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                krypton_database_driver.rs = krypton_database_driver.s.executeQuery("SELECT xd FROM send_buffer");

                krypton_database_driver.rs.last();
                int rowCountbu = krypton_database_driver.rs.getRow();
                network.send_buffer_size = rowCountbu;

                System.out.println("send_buffer_size " + network.send_buffer_size);



                krypton_database_driver.conn.commit();
                System.out.println("Committed the transaction");


        }catch(Exception e){e.printStackTrace();}

    network.database_in_use = 0;
    return true;

    }//load



}//class
