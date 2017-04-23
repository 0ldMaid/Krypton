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

import java.security.MessageDigest;

import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.spec.RSAPrivateCrtKeySpec;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.security.SignatureException;
import java.util.Arrays;
import java.util.Comparator;
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



public class krypton_update_new_block_remote{



    final protected static char[] hexArray = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();

    int ix0 = 0;
    int ix1 = 0;
    int ix2 = 0;

    boolean token_updated = false;


    boolean update(String[] transfer_id, String[] mining_id, String[] old_token){//**************************************************************************
    network.database_in_use = 1;
    Long thisTick = System.currentTimeMillis();


        try{



            token_updated = false;
	


            System.out.println("UPDATE TOKEN....REMOTE NEW BLOCK");

            String block_idx = new String("");
            String[] move_item = transfer_id;//make the new item
            String[] tokenx = old_token;//get the old token



            //the new block
            String ID = mining_id[0];
            String mining_date = mining_id[1];
            String mining_difficulty = mining_id[2];
            String mining_noose = mining_id[3];
            String mining_old_block = mining_id[4];
            String mining_new_block = mining_id[5];
            String prev_hash_id = tokenx[1];//old block hash
            String hash_id = mining_id[7];
            String sig_id = mining_id[8];
            String packagex = mining_id[9];




            System.out.println("Test Mining... " + move_item[0] + " " + ID);

            boolean testm0 = false;
            boolean testm1 = false;
            boolean testm2 = false;
            boolean testm3 = false;
            boolean testm4 = false;
            boolean testm5 = false;
            boolean testm6 = false;
            boolean testm7 = false;
            boolean testm8 = false;






            //decode packages
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

            if(network.last_package_x.length() < 5){last_is_package = false;}
            else{

                last_is_package = true;

                try{

                    JSONParser parserx = new JSONParser();
                    Object objx = parserx.parse(network.last_package_x);
                    jsonObjectx_last = (JSONArray) objx;

                    last_package_sizex = jsonObjectx_last.size();

                }catch(Exception e){last_is_package = false;}


            }//***




            try{

                System.out.println("last package " + network.last_package_x);
                System.out.println("this package " + packagex);

                System.out.println("Last Package " + last_package_sizex);
                System.out.println("This Package " + this_package_sizex);

                //test size
                if(this_package_sizex == 0 && last_package_sizex == 0){testm0 = true;}//normal opperation
                else if(this_package_sizex == 0 && last_package_sizex == 1){testm0 = true;}//package just ended
                else if(this_package_sizex <= network.block_compress_size && last_package_sizex == 0){

                    if(this_is_package){

                        System.out.println("First package block");
                        System.out.println("id1 " + jsonObjectx_this.get(0));
                        System.out.println("id2 " + ID);

                        if(jsonObjectx_this.get(0).equals(ID)){testm0 = true;}
                        else{testm0 = false;}

                    }//*****************
                    else{testm0 = false;}

                }//***********************************************************************************
                else if((this_package_sizex + 1) == last_package_sizex && this_package_sizex <= network.block_compress_size){

                    if(this_is_package){

                        is_in_package = true;

                        System.out.println("Middle package block");
                        System.out.println("id0 " + jsonObjectx_last.get(1));
                        System.out.println("id1 " + jsonObjectx_this.get(0));
                        System.out.println("id2 " + ID);

                        if(jsonObjectx_this.get(0).equals(ID) && jsonObjectx_last.get(1).equals(ID)){testm0 = true;}
                        else{testm0 = false;}

                    }//*****************
                    else{testm0 = false;}

                }//**********************************************************************************************************
                else{testm0 = false;}

            }catch(Exception e){testm0 = false;}

            System.out.println("testm0 " + testm0);





            try{

                if(Integer.parseInt(ID) >= network.base_int && Integer.parseInt(ID) <= (network.base_int + network.hard_token_limit)){testm1 = true;}

            }catch(Exception e){testm1 = false;}

            System.out.println("testm1 " + testm1);





            System.out.println(mining_old_block);
            System.out.println(network.last_block_mining_idx);

            if(mining_old_block.equals(network.last_block_mining_idx)){testm2 = true;}
            System.out.println("testm2 " + testm2);





            //String encode = encode_date + old_block_mining_hash + new_block_hash + Integer.toString(noosex);
            String encode = mining_date + mining_old_block + hash_id + mining_noose + packagex;

            System.out.println("mining_date " + mining_date);
            System.out.println("mining_old_block " + mining_old_block);
            System.out.println("hash_id " + hash_id);
            System.out.println("mining_noose " + mining_noose);

            //test the block mining


                try{

                    byte[] sha256_1 = MessageDigest.getInstance("SHA-256").digest(encode.getBytes());
                    //System.out.println("SHA1 " + bytesToHex(sha256_1));


                    ByteBuffer buffer = ByteBuffer.wrap(sha256_1);
                    buffer.order(ByteOrder.BIG_ENDIAN);  // if you want little-endian
                    long result = buffer.getLong();

                    //System.out.println("value " + value);
                    System.out.println("result " + result);
                    System.out.println("network.difficultyx " + network.difficultyx);
                    System.out.println("SHA1 Mining " + bytesToHex(sha256_1));

                    encode = bytesToHex(sha256_1);

                    long package_difficultyx = (long) 0;

                    //if we are building a package then the other items do not need a hard difficulty. 
                    if(is_in_package){package_difficultyx = network.difficultyx_limit;}
                    else{package_difficultyx = network.difficultyx;}

                    if(result < package_difficultyx && result > 0){testm3 = true;}

                }catch(Exception e){e.printStackTrace();}


                System.out.println("testm3 " + testm3);




                //test mining date

                try{

                    Long block_date = (long) Long.parseLong(mining_date);
                    System.out.println("block_date " + block_date);

                    System.out.println("tokenx[3] " + tokenx[3]);
                    Long old_block_date = (long) Long.parseLong(tokenx[3]);
                    System.out.println("old_block_date " + old_block_date);
                            
                    Long time_now = (long) System.currentTimeMillis();
                    System.out.println("time_now " + time_now);

                    if(block_date > old_block_date && block_date < time_now){testm4 = true;}
                        
                }catch(Exception e){}

                System.out.println("testm4 " + testm4);





    
                System.out.println("Test ITEM... " + move_item[0]);

                //test

                //build the hash without mining info

                String build_hash = new String("");
                build_hash = build_hash + move_item[0];
                for (int loop = 3; loop < move_item.length; loop++){

                    build_hash = build_hash + move_item[loop];//save everything else

                }//*************************************************



                //test signature
                System.out.println(build_hash);

                try{


                    byte[] sha256_1w = MessageDigest.getInstance("SHA-256").digest(build_hash.getBytes());

                    System.out.println("TEST HASH " + Base64.toBase64String(sha256_1w));
                    System.out.println("BASE HASH " + move_item[1]);

                    if(move_item[1].equals(Base64.toBase64String(sha256_1w))){testm5 = true;}
                    System.out.println("testm5 " + testm5);

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

                        if(sigpk3.verify(signatureBytesx3)){testm6 = true;}
                        System.out.println("testm6 " + testm6);

                }catch(Exception e){e.printStackTrace();}




                //get the old key from old token match it to the new public key 

                String old_base58_key = tokenx[60];
                //System.out.println("old_base58_key " + old_base58_key);

                String base58 = new String("x");

                    try{

                        base58 = move_item[4];

                        int len = base58.length();
                        byte[] data = new byte[len / 2];

                            for (int i = 0; i < len; i += 2) {

                                data[i / 2] = (byte) ((Character.digit(base58.charAt(i), 16) << 4) + Character.digit(base58.charAt(i+1), 16));

                            }//*******************************

                        byte[] sha256_1 = MessageDigest.getInstance("SHA-256").digest(data);

                        base58 = Base58Encode.encode(sha256_1);

                    }catch(Exception e){e.printStackTrace();}




                System.out.println("old_base58_key " + old_base58_key);
                System.out.println("base58 " + base58);

                System.out.println("old hash " + tokenx[1]);
                System.out.println("new hash " + move_item[1]);

                //if the hash is the same then don't bother
                if(base58.equals(old_base58_key) || move_item[1].equals(tokenx[1])){testm7 = true;}
                System.out.println("testm7 " + testm7);



                    try{

                        //make sure item is new
                        if(Long.parseLong(tokenx[3]) <= Long.parseLong(move_item[3])){testm8 = true;}                            

                    }catch(Exception e){e.printStackTrace();}


                System.out.println("testm8 " + testm8);

                int last_test = 0;

                if(testm0){last_test++;}
                if(testm1){last_test++;}
                if(testm2){last_test++;}
                if(testm3){last_test++;}
                if(testm4){last_test++;}
                if(testm5){last_test++;}
                if(testm6){last_test++;}
                if(testm7){last_test++;}
                if(testm8){last_test++;}

                System.out.println("last_test " + last_test);


                //the buffered block is used or useless get a new one.
                network.mining_block_ready = 0;

                //JOptionPane.showMessageDialog(null, "Package test");


                    if(last_test == 9){//********************************************************************************************

                        //int set_negitive = (Integer) (Integer.parseInt(move_item[0]) * -1);

                        //boolean delete_old = s.execute("UPDATE listings_db set id=" + set_negitive + " where id=" + Integer.parseInt(move_item[0]) );
                        boolean delete_old = krypton_database_driver.s.execute("DELETE FROM listings_db WHERE id=" + move_item[0] );
                        System.out.println("delete_old " + delete_old);

                        System.out.println("UPDATE");

                        //boolean delete_old = s.execute("DELETE FROM listings_db where id=" + mining.new_block_id);
                        //boolean delete_old = s.execute("UPDATE listings_db set id=0 where id=" + block_idx);
                        //System.out.println("UPDATE id " + delete_old);

                        move_item[1] = hash_id;
                        move_item[2] = sig_id;


                        int numrows = 0;
                        PreparedStatement ps = null;
                        ps = krypton_database_driver.conn.prepareStatement("INSERT INTO listings_db(id, hash_id, sig_id, date_id, owner_id, owner_rating, currency, custom_template, custom_1, custom_2, custom_3, item_errors, item_date_listed, item_date_listed_day, item_date_listed_int, item_hits, item_confirm_code, item_confirmed, item_cost, item_description, item_id, item_price, item_weight, item_listing_id, item_notes, item_package_d, item_package_l, item_package_w, item_part_number, item_title, item_title_url, item_type, item_search_1, item_search_2, item_search_3, item_site_id, item_site_url, item_picture_1, item_total_on_hand, sale_payment_address, sale_payment_type, sale_fees, sale_id, sale_seller_id, sale_status, sale_tax, sale_shipping_company, sale_shipping_in, sale_shipping_out, sale_source_of_sale, sale_total_sale_amount, sale_tracking_number, sale_transaction_id, sale_transaction_info, seller_address_1, seller_address_2, seller_address_city, seller_address_state, seller_address_zip, seller_address_country, seller_id, seller_ip, seller_email, seller_first_name, seller_last_name, seller_notes, seller_phone, seller_logo, seller_url) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                        ps.setInt(1, Integer.parseInt(move_item[0]));

                        for(int loop1 = 1; loop1 < network.listing_size; loop1++){//**********************************

                            ps.setString((loop1 + 1), move_item[(loop1)]);
                            //System.out.println("PS UPDATE " + move_item[(loop1)]);

                        }//*******************************************************************************************

                        numrows = numrows + ps.executeUpdate();
                        ps.close();



                        numrows = 0;
                        ps = null;
                        ps = krypton_database_driver.conn.prepareStatement("INSERT INTO backup_db(id, hash_id, sig_id, date_id, owner_id, owner_rating, currency, custom_template, custom_1, custom_2, custom_3, item_errors, item_date_listed, item_date_listed_day, item_date_listed_int, item_hits, item_confirm_code, item_confirmed, item_cost, item_description, item_id, item_price, item_weight, item_listing_id, item_notes, item_package_d, item_package_l, item_package_w, item_part_number, item_title, item_title_url, item_type, item_search_1, item_search_2, item_search_3, item_site_id, item_site_url, item_picture_1, item_total_on_hand, sale_payment_address, sale_payment_type, sale_fees, sale_id, sale_seller_id, sale_status, sale_tax, sale_shipping_company, sale_shipping_in, sale_shipping_out, sale_source_of_sale, sale_total_sale_amount, sale_tracking_number, sale_transaction_id, sale_transaction_info, seller_address_1, seller_address_2, seller_address_city, seller_address_state, seller_address_zip, seller_address_country, seller_id, seller_ip, seller_email, seller_first_name, seller_last_name, seller_notes, seller_phone, seller_logo, seller_url) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

                        ps.setInt(1, Integer.parseInt(tokenx[0]));

                        for(int loop1 = 1; loop1 < network.listing_size; loop1++){//**********************************

                            ps.setString((loop1 + 1), tokenx[(loop1)]);
                            System.out.println("PS UPDATE BB " + tokenx[(loop1)]);

                        }//*******************************************************************************************

                        numrows = numrows + ps.executeUpdate();
                        ps.close();





                        numrows = 0;
                        ps = null;
                        ps = krypton_database_driver.conn.prepareStatement("INSERT INTO mining_db(link_id, mining_date, mining_difficulty, mining_noose, mining_old_block, mining_new_block, previous_hash_id, hash_id, sig_id, package) values(?,?,?,?,?,?,?,?,?,?)");

                        ps.setInt(1, Integer.parseInt(move_item[0]));
                        ps.setString(2, mining_date);
                        ps.setString(3, mining_difficulty);
                        ps.setString(4, mining_noose);
                        ps.setString(5, mining_old_block);
                        ps.setString(6, mining_new_block);
                        ps.setString(7, prev_hash_id);
                        ps.setString(8, hash_id);
                        ps.setString(9, sig_id);
                        ps.setString(10, packagex);

                        System.out.println("hash_id " + hash_id);
                        System.out.println("sig_id " + sig_id);

                        //try{Thread.sleep(200000);} catch (InterruptedException e){}

                        numrows = numrows + ps.executeUpdate();
                        ps.close();


                        network.time_block_added = System.currentTimeMillis();


                        if(network.new_database_start == 0){mining.mining_stop = 1;}//someone has found the block, go to the next task.


                        krypton_database_driver.s.execute("DELETE FROM unconfirmed_db WHERE id=" + move_item[0]);

                        token_updated = true;



                    }//if************************************************************************************************************
                    else{System.out.println("TEST1 TEST2 FAIL..."); token_updated = false;}



                krypton_database_driver.s = krypton_database_driver.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                krypton_database_driver.rs = krypton_database_driver.s.executeQuery("SELECT id FROM unconfirmed_db");

                krypton_database_driver.rs.last();
                int rowCount5u = krypton_database_driver.rs.getRow();
                network.database_unconfirmed_total = rowCount5u;

                System.out.println("network.unconfirmed TOTAL " + network.database_unconfirmed_total);


                System.out.println("DONE");


                krypton_database_driver.conn.commit();
                System.out.println("Committed the transaction");



        }catch(Exception e){e.printStackTrace(); network.mining_block_ready = 0;}



        network.dbxadd_longstamp = System.currentTimeMillis() - thisTick;
        network.database_in_use = 0;
        return token_updated;

    }//load





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
