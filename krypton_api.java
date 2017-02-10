import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;  
import java.lang.Object.*;  
import java.net.*;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.ServerSocket;

import java.awt.Toolkit;
import java.util.Timer;
import java.util.TimerTask;
import java.util.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Properties;

import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.KeyPair;
import java.security.KeyFactory;
import java.security.KeyPairGenerator;
import java.security.Signature;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.MessageDigest;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.ArrayList;

import org.spongycastle.util.encoders.Base64;

import javax.imageio.ImageIO;
import javax.imageio.*;
import java.awt.image.*;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONValue;




//import javax.servlet.http.HttpServletResponse;



public class krypton_api{


JSONObject jsonObject;

Timer xtimerx;//class loop.
Toolkit toolkit;

String jsonText = new String("");
String responsex = new String("");
String clientSentence;          
String capitalizedSentence;          

String[] tokenx_buffer = new String[network.listing_size];

String passwordx = new String("");

String statex = new String("");
String update_state = new String("");

int server_requests = 0;



krypton_api(){//*****************************************************************

	System.out.println("Start Server");

	toolkit = Toolkit.getDefaultToolkit();
	xtimerx = new Timer();
	xtimerx.schedule(new RemindTask_server(), 0);

}//*********************************************************************************














	class RemindTask_server extends TimerTask{
	Runtime rxrunti = Runtime.getRuntime();

	public void run(){//************************************************************************************

		String jsonText2 = new String("");

		JSONObject obj_out = new JSONObject();

		ServerSocket welcomeSocket;

		while(true){   


			try{//*********************************************************     

				welcomeSocket = new ServerSocket(network.api_port, 0, InetAddress.getByName("localhost"));
				Socket connectionSocket = welcomeSocket.accept(); 
				BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream())); 
				DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());             
				clientSentence = inFromClient.readLine();



				JSONParser parser = new JSONParser();

				try{//*********************************************************
	 

					statex = "0";
					responsex = "e00";
					jsonText = "";

					if(!clientSentence.contains("")){throw new EmptyStackException();}
					Object obj = parser.parse(clientSentence);
	 
					jsonObject = (JSONObject) obj;
	  
					String request = (String) jsonObject.get("request");

					String item_id = new String("");
					String item_array = new String("");
					String old_key = new String("");
					String node = new String("");
					String send_to = new String("");
					String tor_status = new String("");
					String connection_active = new String("");
					String network_up_to_date = new String("");
					String blocks_uptodate = new String("");
					try{item_id = (String) jsonObject.get("item_id").toString(); System.out.println("extra info item_id...");} catch(Exception e){}
					try{item_array = (String) jsonObject.get("item_array").toString(); System.out.println("extra info item_array...");} catch(Exception e){}
					try{old_key = (String) jsonObject.get("key").toString(); System.out.println("extra info key...");} catch(Exception e){}
					try{node = (String) jsonObject.get("node").toString(); System.out.println("extra info node...");} catch(Exception e){}
					try{send_to = (String) jsonObject.get("send_to").toString(); System.out.println("extra info send to address...");} catch(Exception e){}
					try{tor_status = (String) jsonObject.get("tor_status").toString(); network.tor_active = Integer.parseInt(tor_status); System.out.println("extra info tor_status " + tor_status);} catch(Exception e){}
					try{connection_active = (String) jsonObject.get("connection_active").toString(); network.connection_active = Integer.parseInt(connection_active); System.out.println("extra info connection_active " + connection_active);} catch(Exception e){}
					try{network_up_to_date = (String) jsonObject.get("network_up_to_date").toString(); network.network_up_to_date = Integer.parseInt(network_up_to_date); System.out.println("extra info network_up_to_date " + network_up_to_date);} catch(Exception e){}
					try{blocks_uptodate = (String) jsonObject.get("blocks_uptodate").toString(); if(blocks_uptodate.equals("true")){network.blocks_uptodate = true;} else{network.blocks_uptodate = false;}; System.out.println("extra info blocks_uptodate " + blocks_uptodate);} catch(Exception e){}



	    				while(network.database_in_use == 1 && !request.equals("status")){

	    					System.out.println("Database in use...");
							try{Thread.sleep(1000);} catch (InterruptedException e){}

	    				}//**************************************************************



						if(request.equals("status")){statex = "1"; responsex = get_status();}//***************************

						else if(request.equals("get_version")){statex = "1"; responsex = network.versionx;}//

						else if(request.equals("get_tor_active")){statex = "1"; responsex = Integer.toString(network.tor_active);}//

						else if(request.equals("get_last_block")){statex = "1"; responsex = network.last_block_id;}//

						else if(request.equals("get_last_block_timestamp")){statex = "1"; responsex = network.last_block_timestamp;}//

						else if(request.equals("get_last_block_hash")){statex = "1"; responsex = network.last_block_idx;}//

						else if(request.equals("get_difficulty")){statex = "1"; responsex = Long.toString(network.difficultyx);}//

						else if(request.equals("get_last_mining_id")){statex = "1"; responsex = network.last_block_mining_idx;}//

						else if(request.equals("get_prev_mining_id")){statex = "1"; responsex = network.prev_block_mining_idx;}//

						else if(request.equals("get_buffer_unconfirmed")){statex = "1"; responsex = get_buffer_unconfirmed();}//

						else if(request.equals("get_my_token_total")){statex = "1"; responsex = Integer.toString(network.database_listings_owner);}//

						else if(request.equals("get_my_id_list")){statex = "1"; responsex = get_item_ids();}//my id list 

						else if(request.equals("get_my_ids_limit")){statex = "1"; responsex = get_item_ids();}//

						else if(request.equals("get_token")){statex = "1"; responsex = get_item_array(item_id);}//

						else if(request.equals("get_settings")){statex = "1"; responsex = get_settings_array();}//

						else if(request.equals("get_mining_info")){statex = "1"; responsex = get_item_array(item_id);}//

						else if(request.equals("get_new_keys")){statex = "1"; responsex = build_keysx();}//

						else if(request.equals("get_buffered_block")){statex = "1"; responsex = build_buffered_block();}//get the waiting new block

						else if(request.equals("delete_node")){statex = "1"; responsex = delete_node(node);}//

						else if(request.equals("delete_all_nodes")){statex = "1"; responsex = delete_all_nodes();}//

						else if(request.equals("delete_bufferx_id")){statex = "1"; responsex = delete_bufferx_id(item_id);}//

						else if(request.equals("set_new_node")){statex = "1"; responsex = set_new_node(node);}//

						else if(request.equals("set_old_key")){statex = "1"; responsex = set_old_key(old_key);}//

						else if(request.equals("set_new_block")){statex = "1"; responsex = set_new_block(item_array);}//

						else if(request.equals("set_edit_block")){statex = "1"; update_state = "set_edit_block"; responsex = update_token(item_array);}//

						else if(request.equals("set_transfer_block")){statex = "1"; update_state = "set_transfer_block"; responsex = transfer_token(item_id,send_to); }//

						else if(request.equals("system_restart")){statex = "1"; responsex = "restarting"; toolkit = Toolkit.getDefaultToolkit(); xtimerx = new Timer(); xtimerx.schedule(new RemindTask_restart(), 0);}//

						else if(request.equals("system_exit")){statex = "1"; System.exit(0);}//

						else{statex = "0"; responsex = "e01 UnknownRequestException";}


				}//try
				catch(ParseException e){e.printStackTrace(); statex = "0"; responsex = "e02 ParseException";}
				catch(Exception e){e.printStackTrace(); statex = "0"; responsex = "e03 Exception";}




				JSONObject obj = new JSONObject();
				obj.put("response", statex);
				try{obj.put("message", responsex);} catch(Exception e){e.printStackTrace();}

				StringWriter outs = new StringWriter();
				obj.writeJSONString(outs);
				jsonText = outs.toString();
				System.out.println("SEND RESPONSE " + responsex);

				outToClient.writeBytes(jsonText + '\n');
				welcomeSocket.close();

			}catch(Exception e){

				e.printStackTrace(); 
				System.out.println("Server ERROR x3");
				network.programst = "API port blocked " + Integer.toString(network.api_port);

			}//*****************





	         
		}//**********while




	}//runx***************************************************************************************************
    }//remindtask
















	class RemindTask_restart extends TimerTask{
	Runtime rxrunti = Runtime.getRuntime();

	public void run(){//**************************************************************************************

		try{

			restartApplication();

		}catch(Exception e){e.printStackTrace();}

	}//runx***************************************************************************************************
    }//remindtask







	public void restartApplication(){

		try{


  			final String javaBin = System.getProperty("java.home") + File.separator + "bin" + File.separator + "javaw";
  			final File currentJar = new File(network.class.getProtectionDomain().getCodeSource().getLocation().toURI());

  			System.out.println("javaBin " + javaBin);
  			System.out.println("currentJar " + currentJar);
  			System.out.println("currentJar.getPath() " + currentJar.getPath());

  			/* is it a jar file? */
  			//if(!currentJar.getName().endsWith(".jar")){return;}

  			try{

				//xmining = 0;
  				//systemx.shutdown();

  			}catch(Exception e){e.printStackTrace();}

  			/* Build command: java -jar application.jar */
  			final ArrayList<String> command = new ArrayList<String>();
  			command.add(javaBin);
  			command.add("-jar");
  			command.add("-Xms256m");
  			command.add("-Xmx1024m");
  			command.add(currentJar.getPath());

  			final ProcessBuilder builder = new ProcessBuilder(command);
  			builder.start();

  			//try{Thread.sleep(10000);} catch (InterruptedException e){}


  			//close and exit
  			SystemTray.getSystemTray().remove(network.icon);
  			System.exit(0);

  		}//try
  		catch(Exception e){JOptionPane.showMessageDialog(null, e.getCause());}

	}//******************************











	public String get_buffer_unconfirmed(){

		System.out.println("Get unconfirmed from buffer.");

		String jsonText = new String("");


		try{

			String[] token_id = new String[network.listing_size];

			krypton_database_get_buffer bufferx = new krypton_database_get_buffer();
			token_id = bufferx.getToken();

			JSONObject token_obj = new JSONObject();
			for (int loop = 0; loop < token_id.length; loop++){//*************

				token_obj.put(Integer.toString(loop), token_id[loop]);

			}//***************************************************************


			StringWriter outL = new StringWriter();
			token_obj.writeJSONString(outL);
			jsonText = outL.toString();


		}catch(Exception e){e.printStackTrace(); System.out.println("JSON ERROR");}

		return jsonText;

	}//**********************












	public String build_buffered_block(){

		System.out.println("Get item from mining task.");

		String jsontext = new String("");

    	try{

			JSONObject obj = new JSONObject();
			obj.put("new_miningx_id", network.buffered_mining_block);
			obj.put("new_listing_id", network.buffered_listing_block);

			StringWriter out = new StringWriter();
			obj.writeJSONString(out);
			jsontext = out.toString();
			System.out.println(jsonText);

		}catch(Exception e){e.printStackTrace(); statex = "0"; jsontext = "error";}


		return jsontext;

	}//**********************










	public String delete_bufferx_id(String id){

		System.out.println("Delete ID from bufferx");

		int test_db = 0;
		while(network.database_in_use == 1){

    		System.out.println("Database in use... delete ID bufferx");
			try{Thread.sleep(1000);} catch (InterruptedException e){}
			test_db++;

    	}//*********************************


		krypton_database_delete_buffer bufferd = new krypton_database_delete_buffer();
		bufferd.delete(id);

        return "deleted";

	}//**********************














	public String delete_all_nodes(){

		System.out.println("Delete all nodes");

		int test_db = 0;
		while(network.database_in_use == 1){

    		System.out.println("Database in use... delete all nodes");
			try{Thread.sleep(1000);} catch (InterruptedException e){}
			test_db++;

    	}//*********************************


        mining.mining3 = false;


		krypton_database_delete_network pxd = new krypton_database_delete_network();

		krypton_database_load_network nodesx = new krypton_database_load_network();

		krypton_database_load loadx = new krypton_database_load();


        mining.mining3 = true;


        return "deleted";

	}//**********************









	public String delete_node(String nodex){

		System.out.println("Delete node");

		int test_db = 0;
		while(network.database_in_use == 1){

    		System.out.println("Database in use... delete node");
			try{Thread.sleep(1000);} catch (InterruptedException e){}
			test_db++;

    	}//*********************************


        mining.mining3 = false;


		krypton_database_delete_node pxd = new krypton_database_delete_node();
        pxd.delete(nodex);

		krypton_database_load_network nodesx = new krypton_database_load_network();

		krypton_database_load loadx = new krypton_database_load();


        mining.mining3 = true;


 		return Integer.toString(network.network_list.size());

	}//**********************









	public String set_new_node(String nodex){

		System.out.println("Add new node");

		int test_db = 0;
		while(network.database_in_use == 1){

    		System.out.println("Database in use... import node");
			try{Thread.sleep(1000);} catch (InterruptedException e){}
			test_db++;

    	}//*********************************


        mining.mining3 = false;


		krypton_database_nodes add_node = new krypton_database_nodes(nodex);

		krypton_database_load_network nodesx = new krypton_database_load_network();

		krypton_database_load loadx = new krypton_database_load();


        mining.mining3 = true;


 		return Integer.toString(network.network_list.size());

	}//**********************










	public String set_old_key(String keyx){

		System.out.println("Build old keys");

		int test_db = 0;
		while(network.database_in_use == 1){

    		System.out.println("Database in use... import key");
			try{Thread.sleep(1000);} catch (InterruptedException e){}
			test_db++;

    	}//*********************************


        mining.mining3 = false;

		krypton_database_import_keys import_kx = new krypton_database_import_keys(keyx);

		krypton_database_load_network xxn = new krypton_database_load_network();
		krypton_database_load xxs = new krypton_database_load();

        mining.mining3 = true;


 		return network.settingsx[5];

	}//**********************











	public String build_keysx(){

		System.out.println("Build new keys");

		int test_db = 0;
		while(network.database_in_use == 1){

    		System.out.println("Database in use...keys");
			try{Thread.sleep(1000);} catch (InterruptedException e){}
			test_db++;

    	}//*********************************


 		krypton_rebuild_keys keysx = new krypton_rebuild_keys();
 		krypton_database_load loadxs = new krypton_database_load();

 		return network.settingsx[5];

	}//**********************









    public String get_settings_array(){


		String jsontext = new String("");

    	try{

			JSONObject obj = new JSONObject();

			for (int loop = 0; loop < network.settingsx.length; loop++){//******

				obj.put(loop, network.settingsx[loop]);

			}//*****************************************************************

			StringWriter out = new StringWriter();
			obj.writeJSONString(out);
			jsontext = out.toString();
			System.out.println(jsonText);

		}catch(Exception e){e.printStackTrace(); statex = "0"; jsontext = "error";}


		return jsontext;


    }//******************************










    public String get_status(){

		String jsontext = new String("");

    	try{

			JSONObject obj_peer_list = new JSONObject();

			for (int loop = 0; loop < network.network_size; loop++){//************

				obj_peer_list.put(Integer.toString(loop), network.network_list.get(loop).toString());
					
			}//*******************************************************************

			StringWriter out2 = new StringWriter();
			obj_peer_list.writeJSONString(out2);
			String jsonpeer = out2.toString();



			JSONObject obj = new JSONObject();

			obj.put("version", network.versionx);
			obj.put("status", "active");
			obj.put("program_status", network.programst);
			obj.put("listing_size", network.listing_size);
			obj.put("buffer_size", network.send_buffer_size);
			obj.put("mining_status", network.mining_status);
			obj.put("key", network.base58_id);
			obj.put("tor_active", Integer.toString(network.tor_active));
			obj.put("last_block", network.last_block_id);
			obj.put("last_block_timestamp", network.last_block_timestamp);
			obj.put("last_block_hash", network.last_block_idx);
			obj.put("difficulty", Long.toString(network.difficultyx));
			obj.put("last_mining_id", network.last_block_mining_idx);
			obj.put("prev_mining_id", network.prev_block_mining_idx);
			obj.put("blocktimesx", network.blocktimesx);
			obj.put("my_token_total", Integer.toString(network.database_listings_owner));
			obj.put("last_block_time", network.last_block_time);
			obj.put("database_listings_total", network.database_listings_total);
			obj.put("database_unconfirmed_total", network.database_unconfirmed_total);
			obj.put("website_hits", network.website_hits);
			obj.put("website_searches", network.website_searches);
			obj.put("last_unconfirmed_idx", network.last_unconfirmed_idx);
			obj.put("peer_number1", network.settingsx[10]);
			obj.put("peer_list", jsonpeer);
			obj.put("mining_block_ready", network.mining_block_ready);


			StringWriter out = new StringWriter();
			obj.writeJSONString(out);
			jsontext = out.toString();
			//System.out.println(jsonText);

		}catch(Exception e){e.printStackTrace(); statex = "0"; jsontext = "error";}


		return jsontext;

    }//***************************************










    public String set_new_block(String array){

		String jsontext = new String("");

		String update_token[] = new String[network.listing_size];

		try{

			JSONParser parserx = new JSONParser();
			Object objx = parserx.parse(array);
			JSONObject jsonObjectx = (JSONObject) objx;
  

    		for (int loop = 0; loop < network.listing_size; loop++){//************

				update_token[loop] = (String) jsonObjectx.get(Integer.toString(loop));
				System.out.println("import " + update_token[loop]);

			}//*******************************************************************

		}catch(Exception e){e.printStackTrace();}



				String testerx = new String("error");

				for (int loop = 0; loop < network.listing_size; loop++){//************

					try{ if(update_token[loop].equals("0")){} }
					catch(Exception e){statex = "0"; testerx = "1"; jsontext = "e09 NullException";}
					
				}//*******************************************************************


				while(!testerx.equals("1")){

					System.out.println("Mining send new block update >>>>");

					//String testg = krypton_net_client.send_new_block_update(update_token);
					//System.out.println("testg " + testg);

					//if(testg.equals("1") || testg.equals("0")){System.out.println("BREAK"); break;}

					try{Thread.sleep(10000);} catch (InterruptedException e){}

				}//while




	return jsontext;

    }//***************************************









    public String get_item_ids(){

	String jsonarry;


    	try{

        	krypton_database_get_my_tokens getxt = new krypton_database_get_my_tokens();

        	String token_array[] = new String[network.listing_size];
        	token_array = getxt.get_tokens(network.base58_id);

			
			LinkedList<String> list = new LinkedList<String>();
    		for (int loop = 0; loop < token_array.length; loop++){//************

				if(!token_array[loop].contains("-")){list.add(token_array[loop]);} 

			}//*****************************************************************

			jsonarry = JSONValue.toJSONString(list);

		}catch(Exception e){

			statex = "0";
			jsonarry = "Error";

		}//*****************

    	return jsonarry;

    }//*************************************









    public String get_item_array(String id){

	String jsonarry;


    	try{

        	krypton_database_get_token getxt = new krypton_database_get_token();

        	String token_array[] = new String[network.listing_size];
        	token_array = getxt.get_token(id);

			JSONObject obj = new JSONObject();

			obj.put("id", token_array[0]);
			obj.put("hash_id", token_array[1]);
			obj.put("sig_id", token_array[2]);
			obj.put("date_id", token_array[3]);
			obj.put("owner_id", token_array[4]);
			obj.put("owner_rating", token_array[5]);
			obj.put("currency", token_array[6]);
			obj.put("custom_template", token_array[7]);
			obj.put("custom_1", token_array[8]);
			obj.put("custom_2", token_array[9]);
			obj.put("custom_3", token_array[10]);
			obj.put("item_errors", token_array[11]);
			obj.put("item_date_listed", token_array[12]);
			obj.put("item_date_listed_day", token_array[13]);
			obj.put("item_date_listed_int", token_array[14]);
			obj.put("item_hits", token_array[15]);
			obj.put("item_confirm_code", token_array[16]);
			obj.put("item_confirmed", token_array[17]);
			obj.put("item_cost", token_array[18]);
			obj.put("item_description", token_array[19]);
			obj.put("item_id", token_array[20]);
			obj.put("item_price", token_array[21]);
			obj.put("item_weight", token_array[22]);
			obj.put("item_listing_id", token_array[23]);
			obj.put("item_notes", token_array[24]);
			obj.put("item_package_d", token_array[25]);
			obj.put("item_package_l", token_array[26]);
			obj.put("item_package_w", token_array[27]);
			obj.put("item_part_number", token_array[28]);
			obj.put("item_title", token_array[29]);
			obj.put("item_title_url", token_array[30]);
			obj.put("item_type", token_array[31]);
			obj.put("item_search_1", token_array[32]);
			obj.put("item_search_2", token_array[33]);
			obj.put("item_search_3", token_array[34]);
			obj.put("item_site_id", token_array[35]);
			obj.put("item_site_url", token_array[36]);
			obj.put("item_picture_1", token_array[37]);
			obj.put("item_total_on_hand", token_array[38]);
			obj.put("sale_payment_address", token_array[39]);
			obj.put("sale_payment_type", token_array[40]);
			obj.put("sale_fees", token_array[41]);
			obj.put("sale_id", token_array[42]);
			obj.put("sale_seller_id", token_array[43]);
			obj.put("sale_status", token_array[44]);
			obj.put("sale_tax", token_array[45]);
			obj.put("sale_shipping_company", token_array[46]);
			obj.put("sale_shipping_in", token_array[47]);
			obj.put("sale_shipping_out", token_array[48]);
			obj.put("sale_source_of_sale", token_array[49]);
			obj.put("sale_total_sale_amount", token_array[50]);
			obj.put("sale_tracking_number", token_array[51]);
			obj.put("sale_transaction_id", token_array[52]);
			obj.put("sale_transaction_info", token_array[53]);
			obj.put("seller_address_1", token_array[54]);
			obj.put("seller_address_2", token_array[55]);
			obj.put("seller_address_city", token_array[56]);
			obj.put("seller_address_state", token_array[57]);
			obj.put("seller_address_zip", token_array[58]);
			obj.put("seller_address_country", token_array[59]);
			obj.put("seller_id", token_array[60]);
			obj.put("seller_ip", token_array[61]);
			obj.put("seller_email", token_array[62]);
			obj.put("seller_first_name", token_array[63]);
			obj.put("seller_last_name", token_array[64]);
			obj.put("seller_notes", token_array[65]);
			obj.put("seller_phone", token_array[66]);
			obj.put("seller_logo", token_array[67]);
			obj.put("seller_url", token_array[68]);

			StringWriter out = new StringWriter();
			obj.writeJSONString(out);
			String jsonText = out.toString();
			System.out.println(jsonText);

			jsonarry = JSONValue.toJSONString(obj);

		}catch(Exception e){

			e.printStackTrace();
			statex = "0";
			jsonarry = "Error";

		}//*****************

    	return jsonarry;

    }//*************************************










	public String update_token(String req_array){

		System.out.println("Update");

		String jsonarry = new String("");

		try{


			JSONParser parser = new JSONParser();
			Object obj = parser.parse(req_array);
			JSONObject jsonObject = (JSONObject) obj;
	  

			String id = (String) jsonObject.get("id");
			String hash_id = (String) jsonObject.get("hash_id");
			String sig_id = (String) jsonObject.get("sig_id");
			String date_id = (String) jsonObject.get("date_id");
			String owner_id = (String) jsonObject.get("owner_id");
			String owner_rating = (String) jsonObject.get("owner_rating");
			String currency = (String) jsonObject.get("currency");
			String custom_template = (String) jsonObject.get("custom_template");
			String custom_1 = (String) jsonObject.get("custom_1");
			String custom_2 = (String) jsonObject.get("custom_2");
			String custom_3 = (String) jsonObject.get("custom_3");
			String item_errors = (String) jsonObject.get("item_errors");
			String item_date_listed = (String) jsonObject.get("item_date_listed");
			String item_date_listed_day = (String) jsonObject.get("item_date_listed_da");
			String item_date_listed_int = (String) jsonObject.get("item_date_listed_int");
			String item_hits = (String) jsonObject.get("item_hits");
			String item_confirm_code = (String) jsonObject.get("item_confirm_code");
			String item_confirmed = (String) jsonObject.get("item_confirmed");
			String item_cost = (String) jsonObject.get("item_cost");
			String item_description = (String) jsonObject.get("item_description");
			String item_id = (String) jsonObject.get("item_id");
			String item_price = (String) jsonObject.get("item_price");
			String item_weight = (String) jsonObject.get("item_weight");
			String item_listing_id = (String) jsonObject.get("item_listing_id");
			String item_notes = (String) jsonObject.get("item_notes");
			String item_package_d = (String) jsonObject.get("item_package_d");
			String item_package_l = (String) jsonObject.get("item_package_l");
			String item_package_w = (String) jsonObject.get("item_package_w");
			String item_part_number = (String) jsonObject.get("item_part_number");
			String item_title = (String) jsonObject.get("item_title");
			String item_title_url = (String) jsonObject.get("item_title");
			String item_type = (String) jsonObject.get("item_type");
			String item_search_1 = (String) jsonObject.get("item_search_1");
			String item_search_2 = (String) jsonObject.get("item_search_2");
			String item_search_3 = (String) jsonObject.get("item_search_3");
			String item_site_id = (String) jsonObject.get("item_site_id");
			String item_site_url = (String) jsonObject.get("item_site_url");
			String item_picture_1 = (String) jsonObject.get("item_picture_1");
			String item_total_on_hand = (String) jsonObject.get("item_total_on_hand");
			String sale_payment_address = (String) jsonObject.get("sale_payment_address");
			String sale_payment_type = (String) jsonObject.get("sale_payment_type");
			String sale_fees = (String) jsonObject.get("sale_fees");
			String sale_id = (String) jsonObject.get("sale_id");
			String sale_seller_id = (String) jsonObject.get("sale_seller_id");
			String sale_status = (String) jsonObject.get("sale_status");
			String sale_tax = (String) jsonObject.get("sale_tax");
			String sale_shipping_company = (String) jsonObject.get("sale_shipping_company");
			String sale_shipping_in = (String) jsonObject.get("sale_shipping_in");
			String sale_shipping_out = (String) jsonObject.get("sale_shipping_out");
			String sale_source_of_sale = (String) jsonObject.get("sale_source_of_sale");
			String sale_total_sale_amount = (String) jsonObject.get("sale_total_sale_amount");
			String sale_tracking_number = (String) jsonObject.get("sale_tracking_number");
			String sale_transaction_id = (String) jsonObject.get("sale_transaction_id");
			String sale_transaction_info = (String) jsonObject.get("sale_transaction_info");
			String seller_address_1 = (String) jsonObject.get("seller_address_1");
			String seller_address_2 = (String) jsonObject.get("seller_address_2");
			String seller_address_city = (String) jsonObject.get("seller_address_city");
			String seller_address_state = (String) jsonObject.get("seller_address_state");
			String seller_address_zip = (String) jsonObject.get("seller_address_zip");
			String seller_address_country = (String) jsonObject.get("seller_address_country");
			String seller_id = (String) jsonObject.get("seller_id");
			String seller_ip = (String) jsonObject.get("seller_ip");
			String seller_email = (String) jsonObject.get("seller_email");
			String seller_first_name = (String) jsonObject.get("seller_first_name");
			String seller_last_name = (String) jsonObject.get("seller_last_name");
			String seller_notes = (String) jsonObject.get("seller_notes");
			String seller_phone = (String) jsonObject.get("seller_phone");
			String seller_logo = (String) jsonObject.get("seller_logo");
			String seller_url = (String) jsonObject.get("seller_url");



			try{if(currency.length() < 1){}}              catch(Exception e){currency = new String("1");}
			try{if(custom_template.length() < 1){}}       catch(Exception e){custom_template = new String("2");}
			try{if(custom_1.length() < 1){}}              catch(Exception e){custom_1 = new String("3");}
			try{if(custom_2.length() < 1){}}              catch(Exception e){custom_2 = new String("4");}
			try{if(custom_3.length() < 1){}}              catch(Exception e){custom_3 = new String("5");}
			try{if(item_errors.length() < 1){}}           catch(Exception e){item_errors = new String("6");}
			try{if(item_date_listed.length() < 1){}}      catch(Exception e){item_date_listed = new String("7");}
			try{if(item_date_listed_day.length() < 1){}}  catch(Exception e){item_date_listed_day = new String("8");}
			try{if(item_date_listed_int.length() < 1){}}  catch(Exception e){item_date_listed_int = new String("9");}
			try{if(item_hits.length() < 1){}}             catch(Exception e){item_hits = new String("10");}
			try{if(item_confirm_code.length() < 1){}}     catch(Exception e){item_confirm_code = new String("11");}
			try{if(item_confirmed.length() < 1){}}        catch(Exception e){item_confirmed = new String("12");}
			try{if(item_cost.length() < 1){}}             catch(Exception e){item_cost = new String("13");}
			try{if(item_description.length() < 1){}}      catch(Exception e){item_description = new String("14");}
			try{if(item_id.length() < 1){}}               catch(Exception e){item_id = new String("15");}
			try{if(item_price.length() < 1){}}            catch(Exception e){item_price = new String("16");}
			try{if(item_weight.length() < 1){}}           catch(Exception e){item_weight = new String("17");}
			try{if(item_notes.length() < 1){}}            catch(Exception e){item_notes = new String("18");}
			try{if(item_package_d.length() < 1){}}        catch(Exception e){item_package_d = new String("19");}
			try{if(item_package_l.length() < 1){}}        catch(Exception e){item_package_l = new String("20");}
			try{if(item_package_w.length() < 1){}}        catch(Exception e){item_package_w = new String("21");}
			try{if(item_part_number.length() < 1){}}      catch(Exception e){item_part_number = new String("22");}
			try{if(item_title.length() < 1){}}            catch(Exception e){item_title = new String("23");}
			try{if(item_title_url.length() < 1){}}        catch(Exception e){item_title_url = new String("24");}
			try{if(item_type.length() < 1){}}             catch(Exception e){item_type = new String("25");}
			try{if(item_search_1.length() < 1){}}         catch(Exception e){item_search_1 = new String("26");}
			try{if(item_search_2.length() < 1){}}         catch(Exception e){item_search_2 = new String("27");}
			try{if(item_search_3.length() < 1){}}         catch(Exception e){item_search_3 = new String("28");}
			try{if(item_site_url.length() < 1){}}         catch(Exception e){item_site_url = new String("29");}
			try{if(item_picture_1.length() < 1){}}        catch(Exception e){item_picture_1 = new String("30");}
			try{if(item_total_on_hand.length() < 1){}}    catch(Exception e){item_total_on_hand = new String("31");}



			String tokenx[] = new String[network.listing_size];


			if(Integer.parseInt(id) >= network.base_int && Integer.parseInt(id) <= (network.hard_token_limit + network.base_int)){

				krypton_database_get_token2 getxt = new krypton_database_get_token2();
				tokenx = getxt.get_token(id);

	        	//update the noose
				tokenx[3] = Long.toString( System.currentTimeMillis() );

				tokenx[4] = network.settingsx[5];

				tokenx[6] = currency;
				tokenx[7] = custom_template;
				tokenx[8] = custom_1;
				tokenx[9] = custom_2;
				tokenx[10] = custom_3;
				tokenx[11] = item_errors;
				tokenx[12] = item_date_listed;
				tokenx[13] = item_date_listed_day;
				tokenx[14] = item_date_listed_int;
				tokenx[15] = item_hits;
				tokenx[16] = item_confirm_code;
				tokenx[17] = item_confirmed;
				tokenx[18] = item_cost;
				tokenx[19] = item_description;
				tokenx[20] = item_id;
				tokenx[21] = item_price;
				tokenx[22] = item_weight;

				tokenx[24] = item_notes;
				tokenx[25] = item_package_d;
				tokenx[26] = item_package_l;
				tokenx[27] = item_package_w;
				tokenx[28] = item_part_number;
				tokenx[29] = item_title;
				tokenx[30] = item_title_url;
				tokenx[31] = item_type;
				tokenx[32] = item_search_1;
				tokenx[33] = item_search_2;
				tokenx[34] = item_search_3;

				tokenx[36] = item_site_url;
				tokenx[37] = item_picture_1;
				tokenx[38] = item_total_on_hand;



				//to help with search
				tokenx[30] = tokenx[29].toLowerCase();

				//base 58
				if(update_state.equals("set_edit_block")){tokenx[60] = network.base58_id;}
				//else if(update_state.equals("set_transfer_block")){tokenx[60] = seller_id;}


				//seller info
				tokenx[63] = network.settingsx[11];//name
				tokenx[64] = network.settingsx[12];//last
				tokenx[54] = network.settingsx[13];//address
				tokenx[55] = network.settingsx[14];//address2
				tokenx[56] = network.settingsx[15];//city
				tokenx[57] = network.settingsx[16];//state
				tokenx[58] = network.settingsx[17];//zip
				tokenx[59] = network.settingsx[18];//country

				tokenx[39] = network.settingsx[19];//btc
				tokenx[62] = network.settingsx[20];//email
				tokenx[66] = network.settingsx[21];//phone
				tokenx[68] = network.settingsx[22];//website
			

				//sign

				try{

	            	String build_hash = new String("");

					build_hash = tokenx[0];
	                for (int loop = 3; loop < tokenx.length; loop++){

	                    build_hash = build_hash + tokenx[loop];//save everything else

	                }//*************************************************


	                String hashx = new String(build_hash);
	                byte[] sha256_1x = MessageDigest.getInstance("SHA-256").digest(hashx.getBytes());
	                System.out.println(Base64.toBase64String(sha256_1x));

					tokenx[1] = Base64.toBase64String(sha256_1x);



	                byte[] message = Base64.toBase64String(sha256_1x).getBytes("UTF8");

		    		byte[] clear = Base64.decode(network.settingsx[4]);
	    			PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(clear);
	    			KeyFactory fact = KeyFactory.getInstance("RSA");
	    			PrivateKey priv = fact.generatePrivate(keySpec);
	    			Arrays.fill(clear, (byte) 0);

	    			Signature sigx = Signature.getInstance("SHA1WithRSA");//MD5WithRSA
	    			sigx.initSign(priv);
	    			sigx.update(message);
	    			byte[] signatureBytesx = sigx.sign();
	    			//System.out.println("Public: " + Base64.toBase64String(pub.getEncoded()));
	    			System.out.println("Singature: " + Base64.toBase64String(signatureBytesx));

	    			String signxx = Base64.toBase64String(signatureBytesx);


					tokenx[2] = signxx;




	                byte[] keyxb3 = Base64.decode(tokenx[4]);

	                X509EncodedKeySpec keySpecx3 = new X509EncodedKeySpec(keyxb3);
	                KeyFactory factx3 = KeyFactory.getInstance("RSA");
	                PublicKey pubx3 = factx3.generatePublic(keySpecx3);
	                Arrays.fill(keyxb3, (byte) 0);

	                Signature sigpk3 = Signature.getInstance("SHA1WithRSA");//MD5WithRSA
	                byte[] messagex3 = Base64.toBase64String(sha256_1x).getBytes("UTF8");

	                byte[] signatureBytesx3 = Base64.decode(signxx);

	                sigpk3.initVerify(pubx3);
	                sigpk3.update(messagex3);

	                boolean testsx = sigpk3.verify(signatureBytesx3);

	                System.out.println("testsx " + testsx);


	                if(testsx){

						statex = "1";
						jsonarry = "Updated";

	                }//********
	                else{

						statex = "0";
						jsonarry = "Update error. Information did not pass signature test.";

	                }//**

	              	if(update_state.equals("set_edit_block")){network.icon.displayMessage("Krypton", "Token updated ID (" + tokenx[0] + ")", TrayIcon.MessageType.INFO);}
	              	//else if(update_state.equals("set_transfer_block")){network.icon.displayMessage("Krypton", "Token transfer ID (" + tokenx[0] + ")", TrayIcon.MessageType.INFO);}


	                //send the update
	                tokenx_buffer = tokenx;
					toolkit = Toolkit.getDefaultToolkit();
					xtimerx = new Timer();
					xtimerx.schedule(new RemindTask_send_update(), 0);
	                //send the update




				}catch(Exception e){e.printStackTrace();}


			}//if
			else{System.out.println("Update item error cannot find item."); statex = "0"; jsonarry = "Update item error cannot find item.";}

		}catch(Exception e){e.printStackTrace(); statex = "0"; jsonarry = "Error";}//*****************



		return jsonarry;

	}//*****************






	public String transfer_token(String item_id, String send_to){

		System.out.println("Transfer");

		String jsonarry = new String("");

		try{


			String tokenx[] = new String[network.listing_size];

			//test address
			if(send_to.length() > 43 && send_to.length() < 50){


				if(Integer.parseInt(item_id) >= network.base_int && Integer.parseInt(item_id) <= (network.hard_token_limit + network.base_int)){

					krypton_database_get_token2 getxt = new krypton_database_get_token2();
					tokenx = getxt.get_token(item_id);


					if(!network.base58_id.equals(send_to)){


			        	//update the noose
						tokenx[3] = Long.toString( System.currentTimeMillis() );

						//key
						tokenx[4] = network.settingsx[5];

						//to help with search
						tokenx[30] = tokenx[29].toLowerCase();

						//base 58
						if(update_state.equals("set_transfer_block")){tokenx[60] = send_to;}


						//seller info
						tokenx[63] = network.settingsx[11];//name
						tokenx[64] = network.settingsx[12];//last
						tokenx[54] = network.settingsx[13];//address
						tokenx[55] = network.settingsx[14];//address2
						tokenx[56] = network.settingsx[15];//city
						tokenx[57] = network.settingsx[16];//state
						tokenx[58] = network.settingsx[17];//zip
						tokenx[59] = network.settingsx[18];//country

						tokenx[39] = network.settingsx[19];//btc
						tokenx[62] = network.settingsx[20];//email
						tokenx[66] = network.settingsx[21];//phone
						tokenx[68] = network.settingsx[22];//website
					

						//sign

						try{

			            	String build_hash = new String("");

							build_hash = tokenx[0];
			                for (int loop = 3; loop < tokenx.length; loop++){

			                    build_hash = build_hash + tokenx[loop];//save everything else

			                }//*************************************************


			                String hashx = new String(build_hash);
			                byte[] sha256_1x = MessageDigest.getInstance("SHA-256").digest(hashx.getBytes());
			                System.out.println(Base64.toBase64String(sha256_1x));

							tokenx[1] = Base64.toBase64String(sha256_1x);



			                byte[] message = Base64.toBase64String(sha256_1x).getBytes("UTF8");

				    		byte[] clear = Base64.decode(network.settingsx[4]);
			    			PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(clear);
			    			KeyFactory fact = KeyFactory.getInstance("RSA");
			    			PrivateKey priv = fact.generatePrivate(keySpec);
			    			Arrays.fill(clear, (byte) 0);

			    			Signature sigx = Signature.getInstance("SHA1WithRSA");//MD5WithRSA
			    			sigx.initSign(priv);
			    			sigx.update(message);
			    			byte[] signatureBytesx = sigx.sign();
			    			//System.out.println("Public: " + Base64.toBase64String(pub.getEncoded()));
			    			System.out.println("Singature: " + Base64.toBase64String(signatureBytesx));

			    			String signxx = Base64.toBase64String(signatureBytesx);


							tokenx[2] = signxx;




			                byte[] keyxb3 = Base64.decode(tokenx[4]);

			                X509EncodedKeySpec keySpecx3 = new X509EncodedKeySpec(keyxb3);
			                KeyFactory factx3 = KeyFactory.getInstance("RSA");
			                PublicKey pubx3 = factx3.generatePublic(keySpecx3);
			                Arrays.fill(keyxb3, (byte) 0);

			                Signature sigpk3 = Signature.getInstance("SHA1WithRSA");//MD5WithRSA
			                byte[] messagex3 = Base64.toBase64String(sha256_1x).getBytes("UTF8");

			                byte[] signatureBytesx3 = Base64.decode(signxx);

			                sigpk3.initVerify(pubx3);
			                sigpk3.update(messagex3);

			                boolean testsx = sigpk3.verify(signatureBytesx3);

			                System.out.println("testsx " + testsx);


			                if(testsx){

								statex = "1";
								jsonarry = "Updated";

			                }//********
			                else{

								statex = "0";
								jsonarry = "Update error. Information did not pass signature test.";

			                }//**


			              	if(update_state.equals("set_transfer_block")){network.icon.displayMessage("Krypton", "Token transfer ID (" + tokenx[0] + ")", TrayIcon.MessageType.INFO);}


			                //send the update
			                tokenx_buffer = tokenx;
							toolkit = Toolkit.getDefaultToolkit();
							xtimerx = new Timer();
							xtimerx.schedule(new RemindTask_send_update(), 0);
			                //send the update




						}catch(Exception e){e.printStackTrace();}


					}//if
					else{System.out.println("Send item error not your item."); statex = "0"; jsonarry = "Send item error not your item.";}

				}//if
				else{System.out.println("Send item error cannot find item."); statex = "0"; jsonarry = "Send item error cannot find item.";}

			}//if
			else{System.out.println("Send item error address error."); statex = "0"; jsonarry = "Send item error address error.";}


		}catch(Exception e){e.printStackTrace(); statex = "0"; jsonarry = "Error";}//*****************



		return jsonarry;

	}//*****************










	class RemindTask_send_update extends TimerTask{
	Runtime rxrunti = Runtime.getRuntime();

	public void run(){//************************************************************************************

		krypton_update_token update = new krypton_update_token(tokenx_buffer);
        //krypton_net_client.send_unconfirmed_update(tokenx_buffer);
		krypton_database_load load = new krypton_database_load();

	}//runx***************************************************************************************************
    }//remindtask
















}//last
