import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Properties;

import java.io.*;
import java.security.*;

import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.spec.RSAPrivateCrtKeySpec;






public class krypton_database_nodes{


    int ix0 = 0;
    int ix1 = 0;

    String cx0 = new String();
    String cx1 = new String();
    String cx2 = new String();

    int worm_size = 10;


    krypton_database_nodes(String nodex){//************************************************************************
    network.database_in_use = 1;



        try{



                krypton_database_driver.s = krypton_database_driver.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                krypton_database_driver.s.setMaxRows(1); 
                krypton_database_driver.rs = krypton_database_driver.s.executeQuery("SELECT address FROM network WHERE address='" + nodex + "'");

                ix0 = 0;
                while(krypton_database_driver.rs.next()){

                   ix0++;

                }//while




                    if(ix0 == 0){

                        PreparedStatement ps = null;
                        ps = krypton_database_driver.conn.prepareStatement("insert into network(address) values (?)");

                        int numrows = 0;

                            System.out.println(nodex);

                            ps.setString(1, nodex);

                            numrows = numrows + ps.executeUpdate();

                        ps.close();

                    }//if********





                krypton_database_driver.conn.commit();
                System.out.println("Committed the transaction");

        }catch(Exception e){e.printStackTrace();}

    network.database_in_use = 0;
    }





}//class
