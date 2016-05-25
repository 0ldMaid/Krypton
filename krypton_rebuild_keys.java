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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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

import org.spongycastle.util.encoders.Base64;

import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;







public class krypton_rebuild_keys{


    int ix0 = 0;
    int ix1 = 0;

    String cx0 = new String();
    String cx1 = new String();
    String cx2 = new String();

    int worm_size = 10;
    String base58 = new String("");
    KeyPair keyPair;



    krypton_rebuild_keys(){//************************************************************************
    network.database_in_use = 1;


        try{




            System.out.println("KRYPTON DATABASE BUILD.");



	        ix0 = 0;
	        ix1 = 20000;


            //delete old pics
            try{
            
                File file = new File(network.base58_id + ".png");
            
                if(file.delete()){System.out.println(file.getName() + " is deleted!");}
                else{System.out.println("Delete operation failed.");}
           
            }catch(Exception e){e.printStackTrace();}





            //build new keys
	        try{


    	            KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
    	            kpg.initialize(2048);
    	            keyPair = kpg.genKeyPair();

    	            System.out.println("");
    	            System.out.println("privateKey Base 64: " + Base64.toBase64String(keyPair.getPrivate().getEncoded()) );
    	            System.out.println("");
    	            System.out.println("Public Base 64: " + Base64.toBase64String(keyPair.getPublic().getEncoded()) );
    	            System.out.println("");

                    base58 = Base64.toBase64String(keyPair.getPublic().getEncoded());


                    int len = base58.length();
                    byte[] data = new byte[len / 2];
                    for (int i = 0; i < len; i += 2) {
                        data[i / 2] = (byte) ((Character.digit(base58.charAt(i), 16) << 4) + Character.digit(base58.charAt(i+1), 16));
                    }


                    byte[] sha256_1 = MessageDigest.getInstance("SHA-256").digest(data);

                    base58 = Base58Encode.encode(sha256_1);

                    System.out.println("base58 " + base58);




                    ByteArrayOutputStream out = QRCode.from(base58).to(ImageType.PNG).withSize(156, 156).stream();
 
                    try {

                        FileOutputStream fout = new FileOutputStream(new File(base58 + ".png"));
 
                        fout.write(out.toByteArray());
 
                        fout.flush();
                        fout.close();
 
                    } catch (FileNotFoundException e) {
                        System.out.println(e.getMessage());
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }






                    System.out.println("");

                    network.base58_id = base58;

                    network.settingsx[4] = Base64.toBase64String(keyPair.getPrivate().getEncoded());
                    network.settingsx[5] = Base64.toBase64String(keyPair.getPublic().getEncoded());


                    krypton_database_driver.s.execute("UPDATE settings set valuex='" + Base64.toBase64String(keyPair.getPrivate().getEncoded()) + "' where id=" + 5);
                    System.out.println("1");
                    krypton_database_driver.s.execute("UPDATE settings set valuex='" + Base64.toBase64String(keyPair.getPublic().getEncoded()) + "' where id=" + 6);
                    System.out.println("2");

	                System.out.println("DBsx");

	        }catch(Exception e){e.printStackTrace(); System.out.println("Krypton build already in use.");}












            //only build blocks if new db is set to 1
            if(network.new_database_start == 1){//********************************************************************************************************


                System.out.println("UNCONFIRMED BLOCKS");


                //build new item

                String new_item[] = new String[network.listing_size];

                for(int loop1 = 0; loop1 < new_item.length; loop1++){//*********************************

                    new_item[loop1] = new String(Integer.toString(loop1));

                }//*************************************************************************************

                new_item[8] = Base64.toBase64String(keyPair.getPublic().getEncoded());
                new_item[67] = base58;



                //build new item

                try{

                    //and add a few rows...

                    PreparedStatement ps = null;

                    //ps = conn.prepareStatement("INSERT INTO unconfirmed_db(id, noose, seller_id, hash_id, sig_id) values(?,?,?,?,?)");
                    ps = krypton_database_driver.conn.prepareStatement("INSERT INTO unconfirmed_db(id, mining_date, mining_difficulty, mining_noose, mining_old_block, mining_new_block, noose, date_id, owner_id, previous_hash_id, hash_id, sig_id, owner_rating, currency, custom_template, custom_1, custom_2, custom_3, item_errors, item_date_listed, item_date_listed_day, item_date_listed_int, item_hits, item_confirm_code, item_confirmed, item_cost, item_description, item_id, item_price, item_weight, item_listing_id, item_notes, item_package_d, item_package_l, item_package_w, item_part_number, item_title, item_title_url, item_type, item_search_1, item_search_2, item_search_3, item_site_id, item_site_url, item_picture_1, item_total_on_hand, sale_payment_address, sale_payment_type, sale_fees, sale_id, sale_seller_id, sale_status, sale_tax, sale_shipping_company, sale_shipping_in, sale_shipping_out, sale_source_of_sale, sale_total_sale_amount, sale_tracking_number, sale_transaction_id, sale_transaction_info, seller_address_1, seller_address_2, seller_address_city, seller_address_state, seller_address_zip, seller_address_country, seller_id, seller_ip, seller_email, seller_first_name, seller_last_name, seller_notes, seller_phone, seller_logo, seller_url) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

                    int numrows = 0;


                    for(int loop1 = 0; loop1 < network.hard_token_limit; loop1++){//*************************

                        System.out.println(loop1);

                            System.out.println("INSERT 0 " + (loop1));
                            ps.setInt(1, (100000 + loop1));

                                for(int loop2 = 1; loop2 < network.listing_size; loop2++){//****************************

                                    //System.out.println("INSERT 0 " + (loop2 + 1));
                                    ps.setString((loop2 + 1), Integer.toString(loop2));

                                }//*************************************************************************************

                            numrows = numrows + ps.executeUpdate();

                    }//**************************************************************************************


                    ps.close();

                }catch(Exception e){e.printStackTrace(); System.out.println("PRE Mine");}




                        //update hash info
                        for(int loop1 = 0; loop1 < network.hard_token_limit; loop1++){//**********************************

                            try{


                                System.out.println("INSERT 0 " + (loop1));
                                krypton_database_driver.s.execute("UPDATE unconfirmed_db SET owner_id='" + Base64.toBase64String(keyPair.getPublic().getEncoded()) + "' where id=" + (100000 + loop1));
                                krypton_database_driver.s.execute("UPDATE unconfirmed_db SET seller_id='" + base58 + "' where id=" + (100000 + loop1));



                                    String build_hash = new String("");

                                    for (int loop2 = 1; loop2 < new_item.length; loop2++){

                                        if(loop2 == 1){System.out.println("MI " + new_item[loop2]);}//don't hash mining info
                                        else if(loop2 == 2){System.out.println("MI " + new_item[loop2]);}//don't hash mining info
                                        else if(loop2 == 3){System.out.println("MI " + new_item[loop2]);}//don't hash mining info
                                        else if(loop2 == 4){System.out.println("MI " + new_item[loop2]);}//don't hash mining info
                                        else if(loop2 == 5){System.out.println("MI " + new_item[loop2]);}//don't hash mining info
                                        else if(loop2 == 10){System.out.println("HI " + new_item[loop2]);}//don't hash the hash
                                        else if(loop2 == 11){System.out.println("HS " + new_item[loop2]);}//don't hash the sig
                                        else{build_hash = build_hash + new_item[loop2];}//save everything else

                                    }//***************************************************


                                    String hashx = new String(Integer.toString((100000 + loop1)) + build_hash);
                                    byte[] sha256_1x = MessageDigest.getInstance("SHA-256").digest(hashx.getBytes());
                                    System.out.println(Base64.toBase64String(sha256_1x));

                                krypton_database_driver.s.execute("UPDATE unconfirmed_db set hash_id='" + Base64.toBase64String(sha256_1x) + "' where id=" + (100000 + loop1));



                                    byte[] message = Base64.toBase64String(sha256_1x).getBytes("UTF8");

                                    Signature sig = Signature.getInstance("SHA1WithRSA");//MD5WithRSA
                                    sig.initSign(keyPair.getPrivate());
                                    sig.update(message);
                                    byte[] signatureBytes = sig.sign();

                                    String signx = Base64.toBase64String(signatureBytes);

                                    System.out.println("");
                                    System.out.println("Singature: " + signx);
                                    System.out.println("");

                                    sig.initVerify(keyPair.getPublic());
                                    sig.update(message);

                                    System.out.println(sig.verify(signatureBytes));




                                    byte[] keyxb3 = Base64.decode(new_item[8]);

                                    X509EncodedKeySpec keySpecx3 = new X509EncodedKeySpec(keyxb3);
                                    KeyFactory factx3 = KeyFactory.getInstance("RSA");
                                    PublicKey pubx3 = factx3.generatePublic(keySpecx3);
                                    Arrays.fill(keyxb3, (byte) 0);

                                    Signature sigpk3 = Signature.getInstance("SHA1WithRSA");//MD5WithRSA
                                    byte[] messagex3 = Base64.toBase64String(sha256_1x).getBytes("UTF8");

                                    byte[] signatureBytesx3 = Base64.decode(signx);

                                    sigpk3.initVerify(pubx3);
                                    sigpk3.update(messagex3);

                                    System.out.println(">>>>" + sigpk3.verify(signatureBytesx3));






                                krypton_database_driver.s.execute("UPDATE unconfirmed_db set sig_id='" + signx + "' where id=" + (100000 + loop1));



                            }catch(Exception e){e.printStackTrace();}

                        }//for********************************************************************************************







                    System.out.println("START BLOCKS");

                    try{

                        //and the first block...

                        PreparedStatement ps = null;

                        ps = krypton_database_driver.conn.prepareStatement("INSERT INTO listings_db(id, noose, seller_id, mining_new_block, hash_id, sig_id, mining_date, mining_difficulty) values(?,?,?,?,?,?,?,?)");

                        int numrows = 0;


                        for(int loop1 = 0; loop1 < network.hard_token_limit; loop1++){//**********************************

                            System.out.println(loop1);

                            ps.setInt(1, (100000 + loop1));
                            ps.setInt(2, 0);
                            ps.setString(3, base58);


                                String hashw = new String("GENESIS");
                                byte[] sha256_1w = MessageDigest.getInstance("SHA-256").digest(hashw.getBytes());

                                System.out.println(Base64.toBase64String(sha256_1w));

                            ps.setString(4, Base64.toBase64String(sha256_1w));


                                    String build_hash = new String("");

                                    for (int loop2 = 1; loop2 < new_item.length; loop2++){

                                        if(loop2 == 1){System.out.println("MI " + new_item[loop2]);}//don't hash mining info
                                        else if(loop2 == 2){System.out.println("MI " + new_item[loop2]);}//don't hash mining info
                                        else if(loop2 == 3){System.out.println("MI " + new_item[loop2]);}//don't hash mining info
                                        else if(loop2 == 4){System.out.println("MI " + new_item[loop2]);}//don't hash mining info
                                        else if(loop2 == 5){System.out.println("MI " + new_item[loop2]);}//don't hash mining info
                                        else if(loop2 == 10){System.out.println("HI " + new_item[loop2]);}//don't hash the hash
                                        else if(loop2 == 11){System.out.println("HS " + new_item[loop2]);}//don't hash the sig
                                        else{build_hash = build_hash + new_item[loop2];}//save everything else

                                    }//***************************************************


                                    String hashx = new String(Integer.toString((100000 + loop1)) + build_hash);
                                    byte[] sha256_1x = MessageDigest.getInstance("SHA-256").digest(hashx.getBytes());
                                    System.out.println(Base64.toBase64String(sha256_1x));

                            ps.setString(5, Base64.toBase64String(sha256_1x));


                                byte[] message = Base64.toBase64String(sha256_1x).getBytes("UTF8");

                                Signature sig = Signature.getInstance("SHA1WithRSA");//MD5WithRSA
                                sig.initSign(keyPair.getPrivate());
                                sig.update(message);
                                byte[] signatureBytes = sig.sign();

                                String signx = Base64.toBase64String(signatureBytes);


                                System.out.println("");
                                System.out.println("Singature: " + signx);
                                System.out.println("");

                                sig.initVerify(keyPair.getPublic());
                                sig.update(message);

                                System.out.println(sig.verify(signatureBytes));

                            ps.setString(6, signx);
                            ps.setString(7, Long.toString(System.currentTimeMillis()));
                            ps.setString(8, Long.toString(network.difficultyx));

                            numrows = numrows + ps.executeUpdate();

                    }//***********************************************************************************************


                ps.close();

                }catch(Exception e){e.printStackTrace(); System.out.println("PRE Mine");}








            }//if*****************************************************************************************************************************************






            krypton_database_driver.conn.commit();
            System.out.println("Committed the transaction");


        }catch(Exception e){e.printStackTrace();}



    network.database_in_use = 0;

    }//rebuild






}//class
