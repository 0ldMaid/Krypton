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

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONValue;





public class krypton_database_verify_blocks{


    final protected static char[] hexArray = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();

    int ix0 = 0;
    int ix1 = 0;
    int ix2 = 0;

    boolean blocks_verified = false;
    String last_package = new String();


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



            boolean testx0 = false;
            boolean testx1 = false;
            boolean testx2 = false;

            System.out.println("TESTING BLOCKCHAIN...");



            //test
            System.out.println("START TEST");
            int test_run = 0;

            int displaypx = network.block_difficulty_reset;
            //if(network.settingsx[7].equals("1")){displaypx = network.hard_token_limit;}



            krypton_database_driver.s = krypton_database_driver.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            if(!network.settingsx[7].equals("1")){krypton_database_driver.s.setMaxRows(network.block_difficulty_reset + 1);}

            krypton_database_driver.rs = krypton_database_driver.s.executeQuery("SELECT * FROM mining_db ORDER BY mining_date DESC");
            krypton_database_driver.rs.last();

            last_package = krypton_database_driver.rs.getString(11);

            krypton_database_driver.rs.previous();

            while(krypton_database_driver.rs.previous()){


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



                //decode packages
                String ID = krypton_database_driver.rs.getString(2);
                String packagex = krypton_database_driver.rs.getString(11);

                testx0 = false;
                boolean is_in_package = false;
                boolean last_is_package = false;
                boolean this_is_package = false;
                JSONArray jsonObjectx_last = null;
                JSONArray jsonObjectx_this = null;
                int last_package_sizex = 0;
                int this_package_sizex = 0;

                if(packagex.length() < 5){this_is_package = false;}
                else{

                    this_is_package = true;

                    try{

                        JSONParser parserx = new JSONParser();
                        Object objx = parserx.parse(packagex);
                        jsonObjectx_this = (JSONArray) objx;

                        this_package_sizex = jsonObjectx_this.size();

                    }catch(Exception e){this_is_package = false;}

                }//***

                if(last_package.length() < 5){last_is_package = false;}
                else{

                    last_is_package = true;

                    try{

                        JSONParser parserx = new JSONParser();
                        Object objx = parserx.parse(last_package);
                        jsonObjectx_last = (JSONArray) objx;

                        last_package_sizex = jsonObjectx_last.size();

                    }catch(Exception e){last_is_package = false;}


                }//***




                try{

                    System.out.println("last package " + last_package);
                    System.out.println("this package " + packagex);

                    System.out.println("Last Package " + last_package_sizex);
                    System.out.println("This Package " + this_package_sizex);

                    //test size
                    if(this_package_sizex == 0 && last_package_sizex == 0){testx0 = true;}//normal opperation
                    else if(this_package_sizex == 0 && last_package_sizex == 1){testx0 = true;}//package just ended
                    else if(this_package_sizex <= network.block_compress_size && last_package_sizex == 0){

                        if(this_is_package){

                            System.out.println("First package block");
                            System.out.println("id1 " + jsonObjectx_this.get(0));
                            System.out.println("id2 " + ID);

                            if(jsonObjectx_this.get(0).equals(ID)){testx0 = true;}
                            else{testx0 = false;}

                        }//*****************
                        else{testx0 = false;}

                    }//***********************************************************************************
                    else if((this_package_sizex + 1) == last_package_sizex && this_package_sizex <= network.block_compress_size){

                        if(this_is_package){

                            is_in_package = true;

                            System.out.println("Middle package block");
                            System.out.println("id0 " + jsonObjectx_last.get(1));
                            System.out.println("id1 " + jsonObjectx_this.get(0));
                            System.out.println("id2 " + ID);

                            if(jsonObjectx_this.get(0).equals(ID) && jsonObjectx_last.get(1).equals(ID)){testx0 = true;}
                            else{testx0 = false;}

                        }//*****************
                        else{testx0 = false;}

                    }//**********************************************************************************************************
                    else{testx0 = false;}

                }catch(Exception e){testx0 = false;}

                System.out.println("testx0 " + testx0);





                network.programst = ("Verify Blockchain (" + Integer.toString((test_run + 1)) + " of " + Integer.toString(displaypx) + ")");


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



                    if(testx1 && testx2){blocks_verified = true;}
                    else{blocks_verified = false;}


                }//**************



                //test mining hash

                String mining_date = krypton_database_driver.rs.getString(3);
                String mining_old_block = krypton_database_driver.rs.getString(6);
                String hash_id = krypton_database_driver.rs.getString(9);
                String mining_noose = krypton_database_driver.rs.getString(5);
                        


                //String encode = encode_date + old_block_mining_hash + new_block_hash + Integer.toString(noosex);
                String encode = mining_date + mining_old_block + hash_id + mining_noose + packagex;

                System.out.println("mining_date " + mining_date);
                System.out.println("mining_old_block " + mining_old_block);
                System.out.println("hash_id " + hash_id);
                System.out.println("mining_noose " + mining_noose);

                //test the block mining

                boolean testm3 = false;
                Long difficultyx = Long.parseLong(krypton_database_driver.rs.getString(4));

                try{

                    byte[] sha256_1 = MessageDigest.getInstance("SHA-256").digest(encode.getBytes());
                    //System.out.println("SHA1 " + bytesToHex(sha256_1));

                    ByteBuffer buffer = ByteBuffer.wrap(sha256_1);
                    buffer.order(ByteOrder.BIG_ENDIAN);  // if you want little-endian
                    long result = buffer.getLong();

                    //System.out.println("value " + value);
                    System.out.println("result " + result);
                    System.out.println("difficultyx " + difficultyx);
                    System.out.println("SHA1 Mining " + bytesToHex(sha256_1));

                    encode = bytesToHex(sha256_1);

                    long package_difficultyx = (long) 0;

                    //if we are building a package then the other items do not need a hard difficulty. 
                    if(is_in_package){package_difficultyx = network.difficultyx_limit;}
                    else{package_difficultyx = difficultyx;}

                    if(result <= package_difficultyx && result > 0){testm3 = true;}
                    else{blocks_verified = false;}

                }catch(Exception e){e.printStackTrace();}


                System.out.println("testm3 " + testm3);



                System.out.println("");
                System.out.println("");

                last_package = packagex;

                if(!blocks_verified){break;}

                test_run++;

            }//**************


            System.out.println("blocks_verified " + blocks_verified);
            System.out.println("");


            krypton_database_driver.conn.commit();
            System.out.println("Committed the transaction");

            if(test_run == network.block_difficulty_reset){blocks_verified = false;}

            if(!blocks_verified){JOptionPane.showMessageDialog(null, "Block verify failed!");}


        }catch(Exception e){e.printStackTrace();}


        network.database_in_use = 0;
        return blocks_verified;

    }//verify









    public String bytesToHex(byte[] bytes) {

        char[] hexChars = new char[bytes.length * 2];

        for ( int j = 0; j < bytes.length; j++ ) {

            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];

        }//***************************************

        return new String(hexChars);

    }//********************************************









}//class
