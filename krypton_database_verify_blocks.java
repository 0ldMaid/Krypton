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
import java.sql.CallableStatement;

import java.util.ArrayList;
import java.util.Properties;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.Arrays;

import org.spongycastle.util.encoders.Base64;

import java.security.MessageDigest;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.KeyPair;
import java.security.KeyFactory;
import java.security.KeyPairGenerator;
import java.security.Signature;
import java.security.PrivateKey;
import java.security.PublicKey;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.ArrayList;

import org.spongycastle.util.encoders.Base64;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.math.BigInteger;







public class krypton_database_verify_blocks{


    final protected static char[] hexArray = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();

    int ix0 = 0;
    int ix1 = 0;
    int ix2 = 0;

    boolean blocks_verifed = false;


    boolean verify_blocks(){//**************************************************************************
    network.database_in_use = 1;


        try{



            System.out.println("SAVE BLOCKCHAIN...");



            krypton_database_driver.s = krypton_database_driver.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            krypton_database_driver.rs = krypton_database_driver.s.executeQuery("SELECT * FROM backup_db ORDER BY xd ASC");
            krypton_database_driver.rs.last();
            int rowCount4u = krypton_database_driver.rs.getRow();
            int rowCount5u = krypton_database_driver.rs.getInt(1);

            System.out.println("rowCount4u " + rowCount4u);

            rowCount5u = rowCount5u - network.confirm_before_delete;

            System.out.println("rowCount5u " + rowCount5u);

            krypton_database_driver.s.execute("DELETE FROM backup_db WHERE xd < " + rowCount5u );




            boolean testx1 = false;
            boolean testx2 = false;

            System.out.println("TESTING BLOCKCHAIN...");



            //test
            System.out.println("START TEST");
            int test_run = 0;

            int displaypx = 100;
            //if(network.settingsx[7].equals("1")){displaypx = network.hard_token_limit;}



            krypton_database_driver.s = krypton_database_driver.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            if(!network.settingsx[7].equals("1")){krypton_database_driver.s.setMaxRows(100);}

            krypton_database_driver.rs = krypton_database_driver.s.executeQuery("SELECT * FROM mining_db ORDER BY mining_date DESC");
            while(krypton_database_driver.rs.next()){


                    //System.out.println(rs.getString(1));
                    //System.out.println(rs.getString(2));//id
                    System.out.println(krypton_database_driver.rs.getString(3));
                    //System.out.println(rs.getString(4));
                    //System.out.println(rs.getString(5));
                    //System.out.println(rs.getString(6));
                    //System.out.println(rs.getString(7));
                    //System.out.println(rs.getString(8));
                    //System.out.println(rs.getString(9));//hash
                    //System.out.println(rs.getString(10));//sig
                    //System.out.println("");


                        //network.status7.setText("Verify Blockchain (" + Integer.toString((test_run + 1)) + " of " + Integer.toString(displaypx) + ")");


                        krypton_database_driver.s2 = krypton_database_driver.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                        krypton_database_driver.rs2 = krypton_database_driver.s2.executeQuery("SELECT * FROM listings_db WHERE id=" + krypton_database_driver.rs.getString(2));
                        while(krypton_database_driver.rs2.next()){

                            System.out.println("get ID " + krypton_database_driver.rs2.getString("seller_id"));

                            testx1 = false;
                            testx2 = false;

                            //get item
                            String[] move_item = new String[network.listing_size];
                            for(int loop1 = 0; loop1 < network.listing_size; loop1++){//***********

                                //System.out.println(rs2.getString(loop1 + 2));
                                move_item[loop1] = krypton_database_driver.rs2.getString(loop1 + 2);
                                
                            }//********************************************************************

                            String build_hash = new String();
                            build_hash = build_hash + krypton_database_driver.rs2.getString(2);
                            for(int loop1 = 3; loop1 < network.listing_size; loop1++){//***********

                                build_hash = build_hash + krypton_database_driver.rs2.getString(loop1 + 2);

                            }//********************************************************************

                            System.out.println("ID " + move_item[0]);
                            System.out.println("build_hash " + build_hash);



                            //test item
                            try{

                                byte[] sha256_1w = MessageDigest.getInstance("SHA-256").digest(build_hash.getBytes());

                                System.out.println("TESTX " + Base64.toBase64String(sha256_1w));
                                System.out.println("GIVEN " + move_item[1]);
                                System.out.println("MININ " + krypton_database_driver.rs.getString(9));

                                if(Base64.toBase64String(sha256_1w).equals(move_item[1])){testx1 = true;}
                                else{System.out.println("Bad HASH");}


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

                                    //System.out.println(sigpk3.verify(signatureBytesx3));


                                if(sigpk3.verify(signatureBytesx3)){testx2 = true;}
                                else{System.out.println("Bad SIG");}

                            }catch(Exception e){e.printStackTrace();}



                            if(testx1 && testx2){blocks_verifed = true;}
                            else{blocks_verifed = false;}


                        }//**************



                    System.out.println("");
                    System.out.println("");

                    if(!blocks_verifed){break;}

                    test_run++;

            }//**************




            System.out.println("blocks_verifed " + blocks_verifed);
            System.out.println("");



            krypton_database_driver.conn.commit();
            System.out.println("Committed the transaction");



        }catch(Exception e){e.printStackTrace();}


    network.database_in_use = 0;
    return blocks_verifed;
    }//verify



}//class
