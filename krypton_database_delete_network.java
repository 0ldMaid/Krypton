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




public class krypton_database_delete_network{
    /* the default framework is embedded*/


    boolean deleted = false;

    int ix0 = 0;
    int ix1 = 0;
    int ix2 = 0;




    krypton_database_delete_network(){//**************************************************************************
    network.database_in_use = 1;

        try{



                System.out.println("Delete Nodes..." );


                krypton_database_driver.rs = krypton_database_driver.s.executeQuery("SELECT * FROM network");

                while(krypton_database_driver.rs.next()){

                    krypton_database_driver.s2.execute("DELETE FROM network where address='" + krypton_database_driver.rs.getString("address") + "'");
                    System.out.println("delete: " + krypton_database_driver.rs.getString("address"));

                }//**************************************













                System.out.println("Reload Network..." );

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





                krypton_database_driver.conn.commit();
                System.out.println("Committed the transaction");



        }catch(Exception e){e.printStackTrace();}



    network.database_in_use = 0;
    }//load




 
}//get
