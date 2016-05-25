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





public class krypton_update_token{




    int ix0 = 0;
    int ix1 = 0;
    int ix2 = 0;




    krypton_update_token(String[] transfer_id){//**************************************************************************
    network.database_in_use = 1;


        try{

	



                System.out.println("UPDATE TOKEN....");

                String block_idx = new String("");
                String build_hash = new String("");
                String[] move_item = transfer_id;

                network_trim trimx = new network_trim();
                move_item = trimx.trim(move_item);


    
                System.out.println("Update ITEM... " + move_item[0]);

                //test

                boolean test1 = false;
                boolean test2 = false;
                boolean test3 = false;

                //build the hash without mining info


                build_hash = move_item[0];
                for (int loop = 3; loop < move_item.length; loop++){

                    build_hash = build_hash + move_item[loop];//save everything else

                }//*************************************************




                System.out.println(build_hash);

                try{


                    byte[] sha256_1w = MessageDigest.getInstance("SHA-256").digest(build_hash.getBytes());

                    System.out.println("NEW HASH " + Base64.toBase64String(sha256_1w));
                    System.out.println("OLD HASH " + move_item[1]);

                    if(move_item[1].equals(Base64.toBase64String(sha256_1w))){test1 = true;}


                    byte[] keyp = Base64.decode(network.settingsx[4]);
                    byte[] message = Base64.toBase64String(sha256_1w).getBytes("UTF8");

                    PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyp);
                    KeyFactory fact = KeyFactory.getInstance("RSA");
                    PrivateKey prk = fact.generatePrivate(keySpec);
                    Arrays.fill(keyp, (byte) 0);


                    Signature sig = Signature.getInstance("SHA1WithRSA");//MD5WithRSA
                    sig.initSign(prk);
                    sig.update(message);
                    byte[] signatureBytes = sig.sign();

                    String signx = Base64.toBase64String(signatureBytes);

                    System.out.println("");
                    System.out.println("Singature: " + signx);
                    System.out.println("");



                    byte[] keyxb3 = Base64.decode(move_item[4]);

                    X509EncodedKeySpec keySpecx3 = new X509EncodedKeySpec(keyxb3);
                    KeyFactory factx3 = KeyFactory.getInstance("RSA");
                    PublicKey pubx3 = factx3.generatePublic(keySpecx3);
                    Arrays.fill(keyxb3, (byte) 0);

                    Signature sigpk3 = Signature.getInstance("SHA1WithRSA");//MD5WithRSA
                    byte[] messagex3 = Base64.toBase64String(sha256_1w).getBytes("UTF8");

                    byte[] signatureBytesx3 = Base64.decode(signx);

                    sigpk3.initVerify(pubx3);
                    sigpk3.update(messagex3);

                    //System.out.println(sigpk3.verify(signatureBytesx3));
                    if(sigpk3.verify(signatureBytesx3)){test2 = true;}


                }catch(Exception e){e.printStackTrace();}

        

                System.out.println("Test 1 " + test1);
                System.out.println("Test 2 " + test2);

                test1 = true;
                test2 = true;

                //item cannot have null parts

                test3 = true;

                for(int loop = 0; loop < move_item.length; loop++){

                    try{

                        if(move_item[loop].length() < 1){}

                    }catch(Exception e){test3 = false; break;}

                }//************************************************


                System.out.println("Test 3 " + test3);




                        if(test1 && test2){//********************************************************************************************

                            if(test3){


                                System.out.println("ADD TO BUFFER");

                                //boolean delete_old = s.execute("DELETE FROM listings_db where id=" + mining.new_block_id);
                                //boolean delete_old = s.execute("UPDATE listings_db set id=0 where id=" + block_idx);
                                //System.out.println("UPDATE id " + delete_old);

                                krypton_database_driver.s.execute("DELETE FROM send_buffer where id=" + move_item[0]);

                                int numrows = 0;
                                PreparedStatement ps = null;
                                ps = krypton_database_driver.conn.prepareStatement("INSERT INTO send_buffer(id, hash_id, sig_id, date_id, owner_id, owner_rating, currency, custom_template, custom_1, custom_2, custom_3, item_errors, item_date_listed, item_date_listed_day, item_date_listed_int, item_hits, item_confirm_code, item_confirmed, item_cost, item_description, item_id, item_price, item_weight, item_listing_id, item_notes, item_package_d, item_package_l, item_package_w, item_part_number, item_title, item_title_url, item_type, item_search_1, item_search_2, item_search_3, item_site_id, item_site_url, item_picture_1, item_total_on_hand, sale_payment_address, sale_payment_type, sale_fees, sale_id, sale_seller_id, sale_status, sale_tax, sale_shipping_company, sale_shipping_in, sale_shipping_out, sale_source_of_sale, sale_total_sale_amount, sale_tracking_number, sale_transaction_id, sale_transaction_info, seller_address_1, seller_address_2, seller_address_city, seller_address_state, seller_address_zip, seller_address_country, seller_id, seller_ip, seller_email, seller_first_name, seller_last_name, seller_notes, seller_phone, seller_logo, seller_url) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

                                ps.setInt(1, Integer.parseInt(move_item[0]));

                                for(int loop1 = 1; loop1 < network.listing_size; loop1++){//**********************************

                                    ps.setString((loop1 + 1), move_item[(loop1)]);
                                    System.out.println("PS UPDATE " + move_item[(loop1)]);

                                }//*******************************************************************************************

                                numrows = numrows + ps.executeUpdate();
                                ps.close();


                            }//iftest3

                        }//if*********************************************************************************************************************************
                        else{System.out.println("TEST1 TEST2 FAIL...");}






                System.out.println("DONE");




                krypton_database_driver.conn.commit();
                System.out.println("Committed the transaction");




        }catch(Exception e){e.printStackTrace();}


    network.database_in_use = 0;

    }//load




 
}//class
