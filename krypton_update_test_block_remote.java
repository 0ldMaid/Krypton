import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;  
import java.lang.Object.*;  
import java.net.*;
import java.math.*;

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



public class krypton_update_test_block_remote{



    final protected static char[] hexArray = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();

    int ix0 = 0;
    int ix1 = 0;
    int ix2 = 0;

    boolean token_updated = false;


    boolean update(String[] transfer_id, String[] mining_id){//**************************************************************************
    network.database_in_use = 1;
    Long thisTick = System.currentTimeMillis();


        try{



            token_updated = false;
	


            System.out.println("UPDATE TOKEN....REMOTE TEST BLOCK");

            String block_idx = new String("");
            String[] move_item = transfer_id;//make the new item
            //String[] tokenx = null;//get the old token



            //the new block
            String ID = mining_id[0];
            String mining_date = mining_id[1];
            String mining_difficulty = mining_id[2];
            String mining_noose = mining_id[3];
            String mining_old_block = mining_id[4];
            String mining_new_block = mining_id[5];
            String prev_hash_id = mining_id[6];//old block hash
            String hash_id = mining_id[7];
            String sig_id = mining_id[8];
            String packagex = mining_id[9];
            

            boolean testm0 = false;
            boolean testm1 = false;
            boolean testm2 = false;
            boolean testm3 = false;
            boolean testm4 = false;
            boolean testm5 = false;

            System.out.println("Test Mining... " + move_item[0] + " " + ID);
            if(move_item[0].equals(ID)){testm0 = true;}



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

                    if(result < network.difficultyx_limit && result > 0){testm1 = true;}

                }catch(Exception e){e.printStackTrace();}


            System.out.println("testm1 " + testm1);






    
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

                    if(move_item[1].equals(Base64.toBase64String(sha256_1w))){testm2 = true;}
                    System.out.println("testm2 " + testm2);

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

                        if(sigpk3.verify(signatureBytesx3)){testm3 = true;}
                        System.out.println("testm3 " + testm3);

                }catch(Exception e){e.printStackTrace();}




                System.out.println("last_remote_mining_prev_idx " + network.last_remote_mining_prev_idx);
                System.out.println("mining_new_block " + mining_new_block);


                if(network.last_remote_mining_prev_idx.equals(mining_new_block)){testm4 = true;}//working in order
                else if(network.last_remote_mining_prev_idx.equals("")){testm4 = true;}//start
                System.out.println("testm4 " + testm4);





                //if we find this item that means we have what we need.
                testm5 = true;

                krypton_database_driver.s = krypton_database_driver.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                krypton_database_driver.s.setMaxRows(1);
                krypton_database_driver.rs = krypton_database_driver.s.executeQuery("SELECT * FROM mining_db WHERE mining_new_block='" + mining_new_block + "' ORDER BY xd ASC");

                ix0 = 0;
                int fork_block = 0;
                while(krypton_database_driver.rs.next()){

                    System.out.println("FOUND " + mining_new_block);
                    fork_block = krypton_database_driver.rs.getInt(1);
                    testm5 = false;

                }//while

                System.out.println("testm5 " + testm5);

                if(!testm5){

                    //test chains

                    System.out.println("TEST CHAINS FOR THE BEST ONE.");

                    krypton_database_verify_chain_a testxa = new krypton_database_verify_chain_a();
                    String testa = testxa.test_blocks(fork_block);

                    krypton_database_verify_chain_b testxb = new krypton_database_verify_chain_b();
                    String testb = testxb.test_blocks();

                    System.out.println("testa " + testa);
                    System.out.println("testb " + testb);

                    BigDecimal bg1 = new BigDecimal(testa);
                    BigDecimal bg2 = new BigDecimal(testb);

                    int res = bg1.compareTo(bg2);

                    if(res == -1){

                        System.out.println("New chain is better.");

                        krypton_update_block_stale remotex3 = new krypton_update_block_stale();

                        boolean test = true;

                        while(test){

                            test = remotex3.update(network.last_block_id);

                            krypton_database_load loadx = new krypton_database_load();

                            System.out.println("network.last_block_id " + network.last_block_id);
                            System.out.println("mining_new_block " + mining_new_block);

                            if(network.last_block_mining_idx.equals(mining_new_block)){break;}

                            //JOptionPane.showMessageDialog(null, "LOOP ");

                        }//while

                    }//**********
                    else{

                        System.out.println("RESET TEST DB");

                        //JOptionPane.showMessageDialog(null, "RESET ");

                        krypton_database_driver.s2 = krypton_database_driver.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                        krypton_database_driver.s2.execute("DELETE FROM test_listings_db WHERE 1=1");
                        token_updated = false;

                        network.last_remote_mining_idx = "";//this is a copy of the remote peer's mining ID
                        network.last_remote_mining_prev_idx = "";//^^ PREV

                    }//***


                    //JOptionPane.showMessageDialog(null, "STOP ");

                }//*********







            int last_test = 0;

            if(testm0){last_test++;}
            if(testm1){last_test++;}
            if(testm2){last_test++;}
            if(testm3){last_test++;}
            if(testm4){last_test++;}
            if(testm5){last_test++;}


            System.out.println("last_test " + last_test);




                if(last_test == 6){//********************************************************************************************


                    int numrows = 0;
                    PreparedStatement ps = null;
                    ps = krypton_database_driver.conn.prepareStatement("INSERT INTO test_listings_db(link_id, mining_date, mining_difficulty, mining_noose, mining_old_block, mining_new_block, previous_hash_id, hash_id, sig_id, id, hash_id2, sig_id2, date_id, owner_id, owner_rating, currency, custom_template, custom_1, custom_2, custom_3, item_errors, item_date_listed, item_date_listed_day, item_date_listed_int, item_hits, item_confirm_code, item_confirmed, item_cost, item_description, item_id, item_price, item_weight, item_listing_id, item_notes, item_package_d, item_package_l, item_package_w, item_part_number, item_title, item_title_url, item_type, item_search_1, item_search_2, item_search_3, item_site_id, item_site_url, item_picture_1, item_total_on_hand, sale_payment_address, sale_payment_type, sale_fees, sale_id, sale_seller_id, sale_status, sale_tax, sale_shipping_company, sale_shipping_in, sale_shipping_out, sale_source_of_sale, sale_total_sale_amount, sale_tracking_number, sale_transaction_id, sale_transaction_info, seller_address_1, seller_address_2, seller_address_city, seller_address_state, seller_address_zip, seller_address_country, seller_id, seller_ip, seller_email, seller_first_name, seller_last_name, seller_notes, seller_phone, seller_logo, seller_url) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

                    ps.setInt(1, Integer.parseInt(ID));
                    ps.setString(2, mining_date);
                    ps.setString(3, mining_difficulty);
                    ps.setString(4, mining_noose);
                    ps.setString(5, mining_old_block);
                    ps.setString(6, mining_new_block);
                    ps.setString(7, prev_hash_id);
                    ps.setString(8, hash_id);
                    ps.setString(9, sig_id);

                    ps.setInt(10, Integer.parseInt(move_item[0]));

                    int plusx = 11;
                    for(int loop1 = 1; loop1 < network.listing_size; loop1++){//********

                        ps.setString(plusx, move_item[loop1]);
                        System.out.println("TEST UPDATE " + move_item[(loop1)]);
                        plusx++;

                    }//*****************************************************************

                    numrows = numrows + ps.executeUpdate();
                    ps.close();

                    //reset this so the peer knows what ID to go for next.
                    network.last_remote_mining_idx = mining_new_block;
                    network.last_remote_mining_prev_idx = mining_old_block;

                    token_updated = true;


                }//if************************************************************************************************************
                else{System.out.println("TEST1 TEST2 FAIL...");}




            System.out.println("DONE");



            krypton_database_driver.conn.commit();
            System.out.println("Committed the transaction");



        }catch(Exception e){e.printStackTrace();}

        

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
