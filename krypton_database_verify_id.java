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
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.Arrays;

import org.spongycastle.util.encoders.Base64;

import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.KeyPair;
import java.security.KeyFactory;
import java.security.KeyPairGenerator;
import java.security.Signature;
import java.security.PrivateKey;
import java.security.PublicKey;





public class krypton_database_verify_id{



    int ix0 = 0;
    int ix1 = 0;
    int ix2 = 0;




    krypton_database_verify_id(String id){//**************************************************************************



        try{




            String move_item[] = new String[network.listing_size];




    
            System.out.println("Load ITEM... VERIFY" );

            //Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            krypton_database_driver.rs = krypton_database_driver.s.executeQuery("SELECT * FROM listings_db WHERE id=" + id);

            ix0 = 0;
            while(krypton_database_driver.rs.next()){


                    move_item[0] = Integer.toString(krypton_database_driver.rs.getInt(2));

                for (int loop = 1; loop < network.listing_size; loop++){

                    try{

                        move_item[loop] = krypton_database_driver.rs.getString((loop + 2));
                        //System.out.println("GET ITEM " + move_item[loop]);

                    }catch(Exception e){

                        e.printStackTrace();

                    }//*****************

                }//*****************************************************


            }//while




            //build the hash without mining info

            String build_hash = new String("");

                build_hash = move_item[0];
                for (int loop = 3; loop < move_item.length; loop++){

                    build_hash = build_hash + move_item[loop];//save everything else

                }//*************************************************




            System.out.println(build_hash);

            try{

            byte[] sha256_1w = MessageDigest.getInstance("SHA-256").digest(build_hash.getBytes());

            System.out.println("TEST HASH " + Base64.toBase64String(sha256_1w));
            System.out.println("DBH HASH " + move_item[1]);




                                    byte[] keyxb3 = Base64.decode(move_item[4]);

                                    X509EncodedKeySpec keySpecx3 = new X509EncodedKeySpec(keyxb3);
                                    KeyFactory factx3 = KeyFactory.getInstance("RSA");
                                    PublicKey pubx3 = factx3.generatePublic(keySpecx3);
                                    Arrays.fill(keyxb3, (byte) 0);

                                    Signature sigpk3 = Signature.getInstance("SHA1WithRSA");//MD5WithRSA
                                    byte[] messagex3 = Base64.toBase64String(sha256_1w).getBytes("UTF8");

                                    byte[] signatureBytesx3 = Base64.decode(move_item[2]);

                                    sigpk3.initVerify(pubx3);
                                    sigpk3.update(messagex3);


                                    boolean text_hash = sigpk3.verify(signatureBytesx3);

                                    System.out.println("text_hash " + text_hash);



                        if(!text_hash){

                            krypton_database_driver.s2.execute("DELETE FROM unconfirmed_db WHERE id=" + move_item[0]);

                        }//if**********


            }catch(Exception e){e.printStackTrace();}





            krypton_database_driver.conn.commit();
            System.out.println("Committed the transaction");


        }catch(Exception e){e.printStackTrace();}



    }//load




 

}//class
