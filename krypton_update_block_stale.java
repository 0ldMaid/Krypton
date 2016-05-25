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








public class krypton_update_block_stale{


    final protected static char[] hexArray = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();

    int ix0 = 0;
    int ix1 = 0;
    int ix2 = 0;

    boolean token_updated = false;


    boolean update(String[] transfer_id, String[] mining_id, String[] old_token){//**************************************************************************
    network.database_in_use = 1;




        try{


                token_updated = false;
	


                System.out.println("UPDATE TOKEN TEST FOR STALE BLOCKCHAIN....");

                        String block_idx = new String("");
                        String build_hash = new String("");
                        String[] move_item = transfer_id;//make the new item
                        String[] tokenx = old_token;//get the old token



                        //the new block
                        String ID = mining_id[0];
                        String mining_date = mining_id[1];
                        String mining_difficulty = mining_id[2];
                        String mining_noose = mining_id[3];
                        String mining_old_block = mining_id[4];
                        String mining_new_block = mining_id[5];
                        String old_hash_id = mining_id[6];
                        String hash_id = mining_id[7];
                        String sig_id = mining_id[8];




                        System.out.println("Test Mining... " + move_item[0]);

                        boolean testm0 = false;
                        boolean testm1 = false;
                        boolean testm2 = false;
                        boolean testm3 = false;
                        boolean testm4 = false;

                        String[] token_ssp1 = new String[network.miningx_size];
                        String[] token_ssp2 = new String[network.listing_size];



                        System.out.println("Load server's mining token..." );

                        //Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

                        krypton_database_driver.rs = krypton_database_driver.s.executeQuery("SELECT * FROM mining_db WHERE mining_old_block='" + mining_old_block + "'");

                        ix0 = 0;
                        while(krypton_database_driver.rs.next()){

                            for(int loop1 = 0; loop1 < network.miningx_size; loop1++){//***********

                                token_ssp1[loop1] = new String(krypton_database_driver.rs.getString(loop1 + 1));

                            }//********************************************************************

                        testm1 = true;

                        }//while


                        System.out.println("testm1 " + testm1);


                        System.out.println("token_ssp1[2] " + token_ssp1[2]);
                        System.out.println("mining_date " + mining_date);

                        //if the server's very firt token is the same as one we have on record then their chain is longer.
                        if(token_ssp1[2].equals(mining_date)){testm2 = true;}
                        System.out.println("testm2 " + testm2);







                        //if we have a stale block delete it.
                        if(testm1 && testm2){

                            //move from back up blocks.

                            String hash_delete = new String("");

                            krypton_database_driver.s.setMaxRows(1); 
                            krypton_database_driver.rs = krypton_database_driver.s.executeQuery("SELECT hash_id FROM mining_db ORDER BY xd DESC");

                            while(krypton_database_driver.rs.next()){

                                hash_delete = krypton_database_driver.rs.getString(1);
                                System.out.println("hash_delete " + hash_delete);

                            }//while


                            boolean has_backup = false;
                            String[] token_ssp3 = new String[network.listing_size];

                            krypton_database_driver.rs = krypton_database_driver.s.executeQuery("SELECT * FROM backup_db WHERE hash_id='" + hash_delete + "'");
                            while(krypton_database_driver.rs.next()){

                                for(int loop1 = 0; loop1 < network.listing_size; loop1++){//***********

                                    token_ssp3[loop1] = new String(krypton_database_driver.rs.getString(loop1 + 2));
                                    System.out.println("GETB " + krypton_database_driver.rs.getString(loop1 + 2));

                                }//********************************************************************

                            has_backup = true;

                            }//while



                            System.out.println("has_backup " + has_backup);
                            System.out.println("token_ssp3[0] " + token_ssp3[0]);


                            if(has_backup){

                                    krypton_database_driver.s.execute("DELETE FROM listings_db where id=" + token_ssp3[0]);


                                    int numrows = 0;
                                    PreparedStatement ps = null;
                                    ps = krypton_database_driver.conn.prepareStatement("INSERT INTO listings_db(id, hash_id, sig_id, date_id, owner_id, owner_rating, currency, custom_template, custom_1, custom_2, custom_3, item_errors, item_date_listed, item_date_listed_day, item_date_listed_int, item_hits, item_confirm_code, item_confirmed, item_cost, item_description, item_id, item_price, item_weight, item_listing_id, item_notes, item_package_d, item_package_l, item_package_w, item_part_number, item_title, item_title_url, item_type, item_search_1, item_search_2, item_search_3, item_site_id, item_site_url, item_picture_1, item_total_on_hand, sale_payment_address, sale_payment_type, sale_fees, sale_id, sale_seller_id, sale_status, sale_tax, sale_shipping_company, sale_shipping_in, sale_shipping_out, sale_source_of_sale, sale_total_sale_amount, sale_tracking_number, sale_transaction_id, sale_transaction_info, seller_address_1, seller_address_2, seller_address_city, seller_address_state, seller_address_zip, seller_address_country, seller_id, seller_ip, seller_email, seller_first_name, seller_last_name, seller_notes, seller_phone, seller_logo, seller_url) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

                                        ps.setInt(1, Integer.parseInt(token_ssp3[0]));
                                        for(int loop1 = 1; loop1 < network.listing_size; loop1++){//**********************************

                                            ps.setString((loop1 + 1), token_ssp3[loop1]);
                                            System.out.println("PSV UPDATE " + token_ssp3[loop1]);

                                        }//*******************************************************************************************

                                    numrows = numrows + ps.executeUpdate();
                                    ps.close();

                                    System.out.println("DELETE LAST MINING BLOCK");
                                    krypton_database_driver.s.execute("DELETE FROM mining_db where mining_new_block='" + network.last_block_mining_idx + "'");//delete the last block and try to sync again


                                    System.out.println("UPDATE DONE>");

                            }//************
                            else{System.out.println("Cannot update database server's info is wrong.");}

                        }//******************
                        else{System.out.println("DELETE LAST BLOCK FAILED!");}






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
