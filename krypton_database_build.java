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

import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

import org.spongycastle.util.encoders.Base64;

import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;







public class krypton_database_build{


    int ix0 = 0;
    int ix1 = 0;

    String cx0 = new String();
    String cx1 = new String();
    String cx2 = new String();

    int worm_size = 10;
    String base58 = new String("");
    KeyPair keyPair;



    krypton_database_build(){//************************************************************************
    network.database_in_use = 1;


        try{


            System.out.println("KRYPTON DATABASE BUILD");



	        ix0 = 0;
	        ix1 = 20000;



	        try{


                krypton_database_driver.s.execute("create table mining_db(xd integer not null primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), link_id integer, mining_date varchar(60), mining_difficulty varchar(60), mining_noose varchar(60), mining_old_block varchar(100), mining_new_block varchar(100), previous_hash_id varchar(100), hash_id varchar(100), sig_id varchar(1160), package varchar(200)"); 
                System.out.println("Created table mining");

                krypton_database_driver.s.execute("create table listings_db(xd integer not null primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), id integer, hash_id varchar(100), sig_id varchar(1160), date_id varchar(60), owner_id varchar(560), owner_rating varchar(100), currency varchar(160), custom_template varchar(160), custom_1 varchar(160), custom_2 varchar(160), custom_3 varchar(160), item_errors varchar(160), item_date_listed varchar(160), item_date_listed_day varchar(160), item_date_listed_int varchar(160), item_hits varchar(160), item_confirm_code varchar(160), item_confirmed varchar(160), item_cost varchar(160), item_description varchar(3160), item_id varchar(160), item_price varchar(160), item_weight varchar(160), item_listing_id varchar(160), item_notes varchar(160), item_package_d varchar(160), item_package_l varchar(160), item_package_w varchar(160), item_part_number varchar(160), item_title varchar(160), item_title_url varchar(160), item_type varchar(160), item_search_1 varchar(160), item_search_2 varchar(160), item_search_3 varchar(160), item_site_id varchar(160), item_site_url varchar(160), item_picture_1 varchar(160), item_total_on_hand varchar(160), sale_payment_address varchar(160), sale_payment_type varchar(160), sale_fees varchar(160), sale_id varchar(160), sale_seller_id varchar(160), sale_status varchar(160), sale_tax varchar(160), sale_shipping_company varchar(160), sale_shipping_in varchar(160), sale_shipping_out varchar(160), sale_source_of_sale varchar(160), sale_total_sale_amount varchar(160), sale_tracking_number varchar(160), sale_transaction_id varchar(160), sale_transaction_info varchar(160), seller_address_1 varchar(160), seller_address_2 varchar(160), seller_address_city varchar(160), seller_address_state varchar(160), seller_address_zip varchar(160), seller_address_country varchar(160), seller_id varchar(160), seller_ip varchar(160), seller_email varchar(160), seller_first_name varchar(160), seller_last_name varchar(160), seller_notes varchar(160), seller_phone varchar(160), seller_logo varchar(160), seller_url varchar(160))"); 
                System.out.println("Created table listings");

                krypton_database_driver.s.execute("create table test_listings_db(xd integer not null primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), link_id integer, mining_date varchar(60), mining_difficulty varchar(60), mining_noose varchar(60), mining_old_block varchar(100), mining_new_block varchar(100), previous_hash_id varchar(100), hash_id varchar(100), sig_id varchar(1160), id integer, hash_id2 varchar(100), sig_id2 varchar(1160), date_id varchar(60), owner_id varchar(560), owner_rating varchar(100), currency varchar(160), custom_template varchar(160), custom_1 varchar(160), custom_2 varchar(160), custom_3 varchar(160), item_errors varchar(160), item_date_listed varchar(160), item_date_listed_day varchar(160), item_date_listed_int varchar(160), item_hits varchar(160), item_confirm_code varchar(160), item_confirmed varchar(160), item_cost varchar(160), item_description varchar(3160), item_id varchar(160), item_price varchar(160), item_weight varchar(160), item_listing_id varchar(160), item_notes varchar(160), item_package_d varchar(160), item_package_l varchar(160), item_package_w varchar(160), item_part_number varchar(160), item_title varchar(160), item_title_url varchar(160), item_type varchar(160), item_search_1 varchar(160), item_search_2 varchar(160), item_search_3 varchar(160), item_site_id varchar(160), item_site_url varchar(160), item_picture_1 varchar(160), item_total_on_hand varchar(160), sale_payment_address varchar(160), sale_payment_type varchar(160), sale_fees varchar(160), sale_id varchar(160), sale_seller_id varchar(160), sale_status varchar(160), sale_tax varchar(160), sale_shipping_company varchar(160), sale_shipping_in varchar(160), sale_shipping_out varchar(160), sale_source_of_sale varchar(160), sale_total_sale_amount varchar(160), sale_tracking_number varchar(160), sale_transaction_id varchar(160), sale_transaction_info varchar(160), seller_address_1 varchar(160), seller_address_2 varchar(160), seller_address_city varchar(160), seller_address_state varchar(160), seller_address_zip varchar(160), seller_address_country varchar(160), seller_id varchar(160), seller_ip varchar(160), seller_email varchar(160), seller_first_name varchar(160), seller_last_name varchar(160), seller_notes varchar(160), seller_phone varchar(160), seller_logo varchar(160), seller_url varchar(160))"); 
                System.out.println("Created table test_listings");

                krypton_database_driver.s.execute("create table unconfirmed_db(xd integer not null primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), id integer, hash_id varchar(100), sig_id varchar(1160), date_id varchar(60), owner_id varchar(560), owner_rating varchar(100), currency varchar(160), custom_template varchar(160), custom_1 varchar(160), custom_2 varchar(160), custom_3 varchar(160), item_errors varchar(160), item_date_listed varchar(160), item_date_listed_day varchar(160), item_date_listed_int varchar(160), item_hits varchar(160), item_confirm_code varchar(160), item_confirmed varchar(160), item_cost varchar(160), item_description varchar(3160), item_id varchar(160), item_price varchar(160), item_weight varchar(160), item_listing_id varchar(160), item_notes varchar(160), item_package_d varchar(160), item_package_l varchar(160), item_package_w varchar(160), item_part_number varchar(160), item_title varchar(160), item_title_url varchar(160), item_type varchar(160), item_search_1 varchar(160), item_search_2 varchar(160), item_search_3 varchar(160), item_site_id varchar(160), item_site_url varchar(160), item_picture_1 varchar(160), item_total_on_hand varchar(160), sale_payment_address varchar(160), sale_payment_type varchar(160), sale_fees varchar(160), sale_id varchar(160), sale_seller_id varchar(160), sale_status varchar(160), sale_tax varchar(160), sale_shipping_company varchar(160), sale_shipping_in varchar(160), sale_shipping_out varchar(160), sale_source_of_sale varchar(160), sale_total_sale_amount varchar(160), sale_tracking_number varchar(160), sale_transaction_id varchar(160), sale_transaction_info varchar(160), seller_address_1 varchar(160), seller_address_2 varchar(160), seller_address_city varchar(160), seller_address_state varchar(160), seller_address_zip varchar(160), seller_address_country varchar(160), seller_id varchar(160), seller_ip varchar(160), seller_email varchar(160), seller_first_name varchar(160), seller_last_name varchar(160), seller_notes varchar(160), seller_phone varchar(160), seller_logo varchar(160), seller_url varchar(160))"); 
                System.out.println("Created table unconfirmed");

                krypton_database_driver.s.execute("create table send_buffer(xd integer not null primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), id integer, hash_id varchar(100), sig_id varchar(1160), date_id varchar(60), owner_id varchar(560), owner_rating varchar(100), currency varchar(160), custom_template varchar(160), custom_1 varchar(160), custom_2 varchar(160), custom_3 varchar(160), item_errors varchar(160), item_date_listed varchar(160), item_date_listed_day varchar(160), item_date_listed_int varchar(160), item_hits varchar(160), item_confirm_code varchar(160), item_confirmed varchar(160), item_cost varchar(160), item_description varchar(3160), item_id varchar(160), item_price varchar(160), item_weight varchar(160), item_listing_id varchar(160), item_notes varchar(160), item_package_d varchar(160), item_package_l varchar(160), item_package_w varchar(160), item_part_number varchar(160), item_title varchar(160), item_title_url varchar(160), item_type varchar(160), item_search_1 varchar(160), item_search_2 varchar(160), item_search_3 varchar(160), item_site_id varchar(160), item_site_url varchar(160), item_picture_1 varchar(160), item_total_on_hand varchar(160), sale_payment_address varchar(160), sale_payment_type varchar(160), sale_fees varchar(160), sale_id varchar(160), sale_seller_id varchar(160), sale_status varchar(160), sale_tax varchar(160), sale_shipping_company varchar(160), sale_shipping_in varchar(160), sale_shipping_out varchar(160), sale_source_of_sale varchar(160), sale_total_sale_amount varchar(160), sale_tracking_number varchar(160), sale_transaction_id varchar(160), sale_transaction_info varchar(160), seller_address_1 varchar(160), seller_address_2 varchar(160), seller_address_city varchar(160), seller_address_state varchar(160), seller_address_zip varchar(160), seller_address_country varchar(160), seller_id varchar(160), seller_ip varchar(160), seller_email varchar(160), seller_first_name varchar(160), seller_last_name varchar(160), seller_notes varchar(160), seller_phone varchar(160), seller_logo varchar(160), seller_url varchar(160))"); 
                System.out.println("Created table buffer");

                krypton_database_driver.s.execute("create table backup_db(xd integer not null primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), id integer, hash_id varchar(100), sig_id varchar(1160), date_id varchar(60), owner_id varchar(560), owner_rating varchar(100), currency varchar(160), custom_template varchar(160), custom_1 varchar(160), custom_2 varchar(160), custom_3 varchar(160), item_errors varchar(160), item_date_listed varchar(160), item_date_listed_day varchar(160), item_date_listed_int varchar(160), item_hits varchar(160), item_confirm_code varchar(160), item_confirmed varchar(160), item_cost varchar(160), item_description varchar(3160), item_id varchar(160), item_price varchar(160), item_weight varchar(160), item_listing_id varchar(160), item_notes varchar(160), item_package_d varchar(160), item_package_l varchar(160), item_package_w varchar(160), item_part_number varchar(160), item_title varchar(160), item_title_url varchar(160), item_type varchar(160), item_search_1 varchar(160), item_search_2 varchar(160), item_search_3 varchar(160), item_site_id varchar(160), item_site_url varchar(160), item_picture_1 varchar(160), item_total_on_hand varchar(160), sale_payment_address varchar(160), sale_payment_type varchar(160), sale_fees varchar(160), sale_id varchar(160), sale_seller_id varchar(160), sale_status varchar(160), sale_tax varchar(160), sale_shipping_company varchar(160), sale_shipping_in varchar(160), sale_shipping_out varchar(160), sale_source_of_sale varchar(160), sale_total_sale_amount varchar(160), sale_tracking_number varchar(160), sale_transaction_id varchar(160), sale_transaction_info varchar(160), seller_address_1 varchar(160), seller_address_2 varchar(160), seller_address_city varchar(160), seller_address_state varchar(160), seller_address_zip varchar(160), seller_address_country varchar(160), seller_id varchar(160), seller_ip varchar(160), seller_email varchar(160), seller_first_name varchar(160), seller_last_name varchar(160), seller_notes varchar(160), seller_phone varchar(160), seller_logo varchar(160), seller_url varchar(160))"); 
                System.out.println("Created table backup");

	            krypton_database_driver.s.execute("create table network(address varchar(100))"); 
	            System.out.println("Created table network");

	            krypton_database_driver.s.execute("create table settings(id integer, valuex varchar(2000))"); 
	            System.out.println("Created table settings");




	            DateFormat dateFormatx = new SimpleDateFormat("yyyyMMddHHmmss");
	            Date datex = new Date();
	            System.out.println(dateFormatx.format(datex));





                //RSA keys are easy for web developers to use. 
    	        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
                //after non intensive search 2048 seems to be ok for now. 
    	        kpg.initialize(2048);
    	        keyPair = kpg.genKeyPair();

    	        System.out.println("");
    	        System.out.println("privateKey Base 64: " + Base64.toBase64String(keyPair.getPrivate().getEncoded()) );
    	        System.out.println("");
    	        System.out.println("Public Base 64: " + Base64.toBase64String(keyPair.getPublic().getEncoded()) );
    	        System.out.println("");





                byte[] clear = Base64.decode( Base64.toBase64String(keyPair.getPrivate().getEncoded()) );
                PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(clear);
                KeyFactory fact = KeyFactory.getInstance("RSA");
                PrivateKey privateKey = fact.generatePrivate(keySpec);
                Arrays.fill(clear, (byte) 0);

                KeyFactory kf = KeyFactory.getInstance("RSA");
                RSAPrivateKeySpec priv = kf.getKeySpec(privateKey, RSAPrivateKeySpec.class);
                RSAPublicKeySpec keySpecx = new RSAPublicKeySpec(priv.getModulus(), BigInteger.valueOf(65537));
                PublicKey publicKey = kf.generatePublic(keySpecx);

                String base58x = Base64.toBase64String(publicKey.getEncoded());
                System.out.println("base58x " + base58x);






                base58 = Base64.toBase64String(keyPair.getPublic().getEncoded());


                int len = base58.length();
                byte[] data = new byte[len / 2];
                for (int i = 0; i < len; i += 2) {

                    data[i / 2] = (byte) ((Character.digit(base58.charAt(i), 16) << 4) + Character.digit(base58.charAt(i+1), 16));
               
                }//*******************************



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


                PreparedStatement ps = null;
                ps = krypton_database_driver.conn.prepareStatement("insert into settings(id, valuex) values (?,?)");

                int numrows = 0;

                ps.setInt(1, 1);
                ps.setString(2, "Krypton");
                numrows = numrows + ps.executeUpdate();

                ps.setInt(1, 2);
                ps.setString(2, network.versionx);
                numrows = numrows + ps.executeUpdate();

                ps.setInt(1, 3);
                ps.setString(2, "Krypton P2P Market");
                numrows = numrows + ps.executeUpdate();

                ps.setInt(1, 4);
                ps.setString(2, Integer.toString(network.p2p_port));
                numrows = numrows + ps.executeUpdate();

                ps.setInt(1, 5);
                ps.setString(2, Base64.toBase64String(keyPair.getPrivate().getEncoded()) );
                numrows = numrows + ps.executeUpdate();

                ps.setInt(1, 6);
                ps.setString(2, Base64.toBase64String(keyPair.getPublic().getEncoded()) );
                numrows = numrows + ps.executeUpdate();

                ps.setInt(1, 7);
                ps.setString(2, "1");
                numrows = numrows + ps.executeUpdate();//allow all

                ps.setInt(1, 8);
                ps.setString(2, "1");
                numrows = numrows + ps.executeUpdate();//network open

                ps.setInt(1, 9);
                ps.setString(2, "1");
                numrows = numrows + ps.executeUpdate();//system is open or not

                ps.setInt(1, 10);
                ps.setString(2, "foomfoumyoi37qly.onion");
                numrows = numrows + ps.executeUpdate();//favorite node

                ps.setInt(1, 11);
                ps.setString(2, dateFormatx.format(datex).toString());
                numrows = numrows + ps.executeUpdate();


                ps.close();





                network.settingsx[0] = new String("Krypton");
                network.settingsx[1] = new String(network.versionx);
                network.settingsx[2] = new String("Krypton P2P Market");
                network.settingsx[3] = new String(Integer.toString(network.p2p_port));
                network.settingsx[4] = new String(Base64.toBase64String(keyPair.getPrivate().getEncoded()));
                network.settingsx[5] = new String(Base64.toBase64String(keyPair.getPublic().getEncoded()));
                network.settingsx[6] = new String("1");//Network OPEN
                network.settingsx[7] = new String("1");//database all or 100
                network.settingsx[8] = new String("1");//import nodes yes no
                network.settingsx[9] = new String("1");//start minimized
                network.settingsx[10] = new String(dateFormatx.format(datex).toString());




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

                new_item[4] = Base64.toBase64String(keyPair.getPublic().getEncoded());
                new_item[60] = base58;



                //build new item

                try{

                    //and add a few rows...

                    PreparedStatement ps = null;
                    ps = krypton_database_driver.conn.prepareStatement("INSERT INTO unconfirmed_db(id, hash_id, sig_id, date_id, owner_id, owner_rating, currency, custom_template, custom_1, custom_2, custom_3, item_errors, item_date_listed, item_date_listed_day, item_date_listed_int, item_hits, item_confirm_code, item_confirmed, item_cost, item_description, item_id, item_price, item_weight, item_listing_id, item_notes, item_package_d, item_package_l, item_package_w, item_part_number, item_title, item_title_url, item_type, item_search_1, item_search_2, item_search_3, item_site_id, item_site_url, item_picture_1, item_total_on_hand, sale_payment_address, sale_payment_type, sale_fees, sale_id, sale_seller_id, sale_status, sale_tax, sale_shipping_company, sale_shipping_in, sale_shipping_out, sale_source_of_sale, sale_total_sale_amount, sale_tracking_number, sale_transaction_id, sale_transaction_info, seller_address_1, seller_address_2, seller_address_city, seller_address_state, seller_address_zip, seller_address_country, seller_id, seller_ip, seller_email, seller_first_name, seller_last_name, seller_notes, seller_phone, seller_logo, seller_url) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

                    int numrows = 0;


                    for(int loop1 = 0; loop1 < network.hard_token_limit; loop1++){//*************************

                        //System.out.println(loop1);

                            System.out.println("INSERT U 0 " + (loop1));
                            ps.setInt(1, (network.base_int + loop1));

                                for(int loop2 = 1; loop2 < network.listing_size; loop2++){//****************************

                                    //System.out.println("INSERT 0 " + (loop2 + 1));
                                    ps.setString((loop2 + 1), Integer.toString(loop2));

                                }//*************************************************************************************

                            numrows = numrows + ps.executeUpdate();

                    krypton_database_driver.s.execute("UPDATE unconfirmed_db SET owner_id='" + Base64.toBase64String(keyPair.getPublic().getEncoded()) + "' WHERE id=" + (network.base_int + loop1));
                    krypton_database_driver.s.execute("UPDATE unconfirmed_db SET seller_id='" + base58 + "' WHERE id=" + (network.base_int + loop1));

                    }//**************************************************************************************


                    ps.close();



                    //and add a few rows...

                    ps = null;
                    ps = krypton_database_driver.conn.prepareStatement("INSERT INTO listings_db(id, hash_id, sig_id, date_id, owner_id, owner_rating, currency, custom_template, custom_1, custom_2, custom_3, item_errors, item_date_listed, item_date_listed_day, item_date_listed_int, item_hits, item_confirm_code, item_confirmed, item_cost, item_description, item_id, item_price, item_weight, item_listing_id, item_notes, item_package_d, item_package_l, item_package_w, item_part_number, item_title, item_title_url, item_type, item_search_1, item_search_2, item_search_3, item_site_id, item_site_url, item_picture_1, item_total_on_hand, sale_payment_address, sale_payment_type, sale_fees, sale_id, sale_seller_id, sale_status, sale_tax, sale_shipping_company, sale_shipping_in, sale_shipping_out, sale_source_of_sale, sale_total_sale_amount, sale_tracking_number, sale_transaction_id, sale_transaction_info, seller_address_1, seller_address_2, seller_address_city, seller_address_state, seller_address_zip, seller_address_country, seller_id, seller_ip, seller_email, seller_first_name, seller_last_name, seller_notes, seller_phone, seller_logo, seller_url) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

                    numrows = 0;


                    for(int loop1 = 0; loop1 < network.hard_token_limit; loop1++){//*************************

                        //System.out.println(loop1);

                            System.out.println("INSERT L 0 " + (loop1));
                            ps.setInt(1, (network.base_int + loop1));

                                for(int loop2 = 1; loop2 < network.listing_size; loop2++){//****************************

                                    //System.out.println("INSERT 0 " + (loop2 + 1));
                                    ps.setString((loop2 + 1), Integer.toString(loop2));

                                }//*************************************************************************************

                            numrows = numrows + ps.executeUpdate();

                        krypton_database_driver.s.execute("UPDATE listings_db SET owner_id='" + Base64.toBase64String(keyPair.getPublic().getEncoded()) + "' WHERE id=" + (network.base_int + loop1));
                        krypton_database_driver.s.execute("UPDATE listings_db SET seller_id='" + base58 + "' WHERE id=" + (network.base_int + loop1));

                    }//**************************************************************************************


                    ps.close();




                }catch(Exception e){e.printStackTrace(); System.out.println("PRE Mine");}





                System.out.println("START BLOCKS");

                try{




                    for(int loop1 = 0; loop1 < network.hard_token_limit; loop1++){//**********************************

                            
                    //and the first block...

                    PreparedStatement ps = null;
                    ps = krypton_database_driver.conn.prepareStatement("INSERT INTO mining_db(link_id, mining_date, mining_difficulty, mining_noose, mining_old_block, mining_new_block, previous_hash_id, hash_id, sig_id) values(?,?,?,?,?,?,?,?,?)");
                    int numrows = 0;


                            System.out.println(loop1);

                            ps.setInt(1, (network.base_int + loop1));
                            ps.setString(2, Long.toString(System.currentTimeMillis()));
                            ps.setString(3, Long.toString(network.difficultyx_limit));
                            ps.setString(4, "0");


                                String hashw = new String("GENESIS");
                                byte[] sha256_1w = MessageDigest.getInstance("SHA-256").digest(hashw.getBytes());

                                System.out.println(Base64.toBase64String(sha256_1w));

                            ps.setString(5, Base64.toBase64String(sha256_1w));


                                    String build_hash = new String("");

                                    for (int loop2 = 3; loop2 < new_item.length; loop2++){

                                        build_hash = build_hash + new_item[loop2];//save everything else

                                    }//***************************************************

                                    String hashx = new String(Integer.toString((network.base_int + loop1)) + build_hash);
                                    System.out.println("build_hash " + hashx);
                                    byte[] sha256_1x = MessageDigest.getInstance("SHA-256").digest(hashx.getBytes());
                                    System.out.println(Base64.toBase64String(sha256_1x));

                                    krypton_database_driver.s.execute("UPDATE unconfirmed_db SET hash_id='" + Base64.toBase64String(sha256_1x) + "' WHERE id=" + (network.base_int + loop1));
                                    krypton_database_driver.s.execute("UPDATE listings_db SET hash_id='" + Base64.toBase64String(sha256_1x) + "' WHERE id=" + (network.base_int + loop1));


                            ps.setString(6, Base64.toBase64String(sha256_1x));
                            ps.setString(7, Base64.toBase64String(sha256_1x));
                            ps.setString(8, Base64.toBase64String(sha256_1x));


                                byte[] message = Base64.toBase64String(sha256_1x).getBytes("UTF8");

                                Signature sig = Signature.getInstance("SHA1WithRSA");//MD5WithRSA
                                sig.initSign(keyPair.getPrivate());
                                sig.update(message);
                                byte[] signatureBytes = sig.sign();

                                String signx = Base64.toBase64String(signatureBytes);


                                System.out.println("");
                                System.out.println("Singature: " + signx);
                                System.out.println("");

                                //sig.initVerify(keyPair.getPublic());
                                //sig.update(message);

                                //System.out.println(sig.verify(signatureBytes));


                                krypton_database_driver.s.execute("UPDATE unconfirmed_db SET sig_id='" + signx + "' WHERE id=" + (network.base_int + loop1));
                                krypton_database_driver.s.execute("UPDATE listings_db SET sig_id='" + signx + "' WHERE id=" + (network.base_int + loop1));

                            ps.setString(9, signx);

                            numrows = numrows + ps.executeUpdate();

                    ps.close();

                    }//***********************************************************************************************




                }catch(Exception e){e.printStackTrace(); System.out.println("PRE Mine");}



            }//if*****************************************************************************************************************************************







            krypton_database_driver.conn.commit();
            System.out.println("Committed the transaction");


        }catch(Exception e){e.printStackTrace(); System.out.println("Krypton build already in use.");}




    network.database_in_use = 0;
    }//build


}//build






