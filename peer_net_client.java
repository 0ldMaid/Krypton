import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;  
import java.lang.Object.*;  
import java.net.*;
import java.util.Random;

import java.awt.Toolkit;
import java.util.Timer;
import java.util.TimerTask;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.imageio.*;
import java.awt.image.*;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONValue;

import java.net.HttpURLConnection;
import javax.net.SocketFactory;

import com.subgraph.orchid.circuits.hs.HSDescriptorCookie;
import com.subgraph.orchid.config.TorConfigBridgeLine;
import com.subgraph.orchid.data.HexDigest;
import com.subgraph.orchid.data.IPv4Address;
import com.subgraph.orchid.encoders.Hex;
import com.subgraph.orchid.*;





public class peer_net_client{

TorClient tor;

String client_address_connect = new String("127.0.0.1");
int client_port_connect = 00000;

long client_loop_time = (long) 0;

int peer_idx = 0;//local version of peer_id
int breakx1 = 0;
int breakx2 = 0;

//boolean updating = false;
//boolean blocks_uptodate = false;

String new_hash1 = new String("");
String new_hash2 = new String("");
String new_hash3 = new String("");
String new_hash4 = new String("");

long threadId;



public String peer_start(int peer_id){//*****************************************************************

	peer_idx = peer_id;

	//set the restart to false 
	peer_net_api.peerRestart1 = false;

	//
	peer_net_api.blocks_uptodate = false;

	//save the number of peers working
	peer_net_api.peer_active_number++;

	client_address_connect = peer_net_api.client_address_connect;
	client_port_connect = peer_net_api.client_port_connect;

	threadId = Thread.currentThread().getId();
	System.out.println("threadId " + threadId);

	client_loop_time = System.currentTimeMillis();

	breakx1 = 0;
	breakx2 = 0;


	//System.out.println("TOR START");
	//System.gc();


	try{

		//no_peers_time = 0;
		peer_net_api.tor_active = 0;
		peer_net_api.connection_active = 0;
		initTor();

	}catch(Exception e){e.printStackTrace();}




	int startx = 0;
	while(peer_net_api.tor_active == 0){

		System.out.println("Waiting for startup... " + startx);
		try{Thread.sleep(1000);} catch (InterruptedException e){e.printStackTrace();}
		startx++;
		if(startx > 150){break;}

	}//****************************



	//try{Thread.sleep(5000);}
	//catch(InterruptedException e){}




	for(int loop = 0; loop < 5; loop++){//**************************************************************************************************



		if(peer_net_api.tor_active == 1){

			try{

				System.out.println("tor loop " + loop);
				peer_net_api.connection_active = 0;

	    		try{Thread.sleep(1000);}
	    		catch(InterruptedException e){}

				run0(loop);

				//sometimes the clent tries to connect to fast.
				//try{Thread.sleep(10000);}
				//catch(InterruptedException e){}

			}catch(Exception e){e.printStackTrace();}//******************

		}//*************************


		System.out.println("breakid " + loop);

		//take a break
		breakx1 = 0;
		while(peer_net_api.tor_active == 1){

			//after the connection is active we wait a while to see if any new information needs to be sent. 
			//System.out.println("break1 " + breakx1);
			try{Thread.sleep(1000);}catch(InterruptedException e){}
			breakx1++;

			//the break will end if longer then 30
			if(breakx1 > 30){System.out.println("break end"); break;}//this over 400 seems to be trouble
			if(!peer_net_api.blocks_uptodate){System.out.println("Need new blocks! break"); break;}

	  	}//****************************


	  	if(peer_net_api.tor_active == 0){System.out.println("TOR not active! break"); break;}

  	}//for***********************************************************************************************************************************



	System.out.println("TOR SHUTDOWN");
	peer_net_api.peerRestart1 = true;

	return "done";

}//*************************************************************************************












	public void run0(int run_number){//************************************************************************************


		System.out.println("Krypton Network Client...0");
		int node_match = 1;


		if(!client_address_connect.contains(".onion")){}//network.status_x1.setText("No network list!");
		else if(peer_net_api.tor_active == 0 && peer_net_api.tor_starting == 1){System.out.println("TOR IS NOT READY..."); peer_net_api.stop_mining = 1;}
		else{


			System.out.println("Other node work...");

		    System.out.println("client_address_connect " + client_address_connect);

		    String status = request_status(0);

		    if(status.equals("active")){


		    	System.out.println("nodelist size 4 " + peer_net_api.network_sizex);
		    	peer_net_api.connection_active = 1;

				//pause
		    	try{Thread.sleep(2000);} 
		    	catch(InterruptedException e){e.printStackTrace();}

				//update the blocks
		    	if(!peer_net_api.blocks_uptodate){

					peer_net_api.updating = true;
					request_blocks_x_update(); 
					peer_net_api.updating = false;

				}//******************

				//send any new blocks if they are found.
				if(peer_net_api.peer1sendBufferB1 != null && peer_net_api.blocks_uptodate){send_new_block_update();}


			}//if**********************
			else{


				peer_net_api.stop_mining = 1;
		    	peer_net_api.tor_active = 0;
		    	peer_net_api.connection_active = 0;
		    	peer_net_api.tor_starting = 0;


			}//else


		    System.out.println("connection_active " + peer_net_api.connection_active);


		}//else		


	}//runx*************************************************************************************************













	public String request_status(int node){//*****************************************************************


		String sentence = new String("");
		String modifiedSentence = new String("");  
		String JsonSentence = new String(""); 

		String jsonText = new String("");

		//SocketFactory factory;
		Socket socket;


		boolean passx = true;
		int tests = 0;
		while(peer_net_api.tor_in_use == 1){

    		System.out.println("TOR in use...rqs " + tests);
			try{Thread.sleep(1000);} catch (InterruptedException e){}
			tests++;
			if(tests > 100){passx = false; new Exception("Cannot get status!"); break;}//*************

    	}//*****************************

		peer_net_api.tor_in_use = 1;

    	if(passx){


			try{

				JSONObject obj = new JSONObject();
				obj.put("request","status");

				StringWriter out = new StringWriter();
				obj.writeJSONString(out);
				jsonText = out.toString();
				System.out.println(jsonText);

			}catch(Exception e){System.out.println("JSON ERROR");}


			try{

				System.out.println("address: " + client_port_connect);
				System.out.println("address: " + client_address_connect);

				System.out.println("socket");
				System.out.println("isConnected " + tor.getCircuitManager().openDirectoryCircuit().isConnected());
				System.out.println("Circuit ID " + tor.getCircuitManager().getCleanInternalCircuit().getCircuitId());
				//tor.getCircuitManager().getCleanInternalCircuit();

				tor.getCircuitManager().getCleanInternalCircuit().destroyCircuit();
   				socket = tor.getSocketFactory().createSocket(client_address_connect, 80);
   				//socket.setKeepAlive(true); 
				//socket.setSoTimeout(20000);

   				//wait for Circuit creation
				try{Thread.sleep(5000);} 
				catch(InterruptedException e){e.printStackTrace();}

				System.out.println("socketg");

    			OutputStream outputStream = socket.getOutputStream();
    			PrintWriter outx = new PrintWriter(outputStream);
    			outx.print(jsonText + "\r\n\r\n");
    			outx.flush();
    			InputStream inputStream = socket.getInputStream();
    			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
    			BufferedReader in = new BufferedReader(inputStreamReader);

				System.out.println("socketw");

    			String line;
    			while ((line = in.readLine()) != null) {

    		 	 	System.out.println(line);
		  	 	 	modifiedSentence = line;

    			}//*************************************

				outputStream.close();
   				outx.close();
    			in.close();
    			socket.close();


				JSONParser parser = new JSONParser();
				Object obj = parser.parse(modifiedSentence);
				JSONObject jsonObject = (JSONObject) obj;
  
				String response = (String) jsonObject.get("response");
				String message = (String) jsonObject.get("message");
				System.out.println("JSON " + response);

				if(response.equals("1")){

					JsonSentence = "active";

					Object obj2 = parser.parse(message);
					JSONObject jsonObject2 = (JSONObject) obj2;
  
					String block = (String) jsonObject2.get("last_block_id");
					String block_timestamp = (String) jsonObject2.get("last_block_timestamp");
					String unconfirmed_block = (String) jsonObject2.get("last_unconfirmed_id");
					String network_size = (String) jsonObject2.get("node_list");

					System.out.println("block " + block);
					System.out.println("unconfirmed_block " + unconfirmed_block);
					System.out.println("network_size " + network_size);


						peer_net_api.no_peers_time = 0;


						if(node == 0){new_hash1 = block;}
						if(node == 1){new_hash2 = block;}
						if(node == 2){new_hash3 = block;}
						if(node == 3){new_hash4 = block;}


						//test the new has to see if we have the most recent.
						if(block.equals(peer_net_api.last_block_mining_idx)){System.out.println("Blocks up to date..."); peer_net_api.stop_mining = 1; peer_net_api.network_up_to_date = 0; peer_net_api.blocks_uptodate = true;}
						else if(Long.parseLong(block_timestamp) < Long.parseLong(peer_net_api.last_block_timestamp)){System.out.println("Blocks are ahead..."); peer_net_api.stop_mining = 1; peer_net_api.network_up_to_date = 0; peer_net_api.blocks_uptodate = true;}
						else{System.out.println("Blocks are not up to date..."); peer_net_api.stop_mining = 1; peer_net_api.network_up_to_date = 1; peer_net_api.blocks_uptodate = false;}

						//test the pending database to see if there are new items.
						if(unconfirmed_block.equals(peer_net_api.last_unconfirmed_idx) || unconfirmed_block.equals("")){System.out.println("Unconfirmed blocks up to date...");}
						else{System.out.println("Unconfirmed blocks are not up to date...");}

						//test to see if there are new peers we may want. 
						if(peer_net_api.network_size >= Integer.parseInt(network_size) || peer_net_api.open_network == 0){System.out.println("Network is up to date...");}
						else{System.out.println("Network is missing nodes...");}

						if(peer_net_api.send_buffer_size > 1){}



						//run
						if(!block.equals(peer_net_api.last_block_mining_idx)){peer_net_api.blocks_uptodate = false;}
						else if(Long.parseLong(block_timestamp) < Long.parseLong(peer_net_api.last_block_timestamp)){peer_net_api.blocks_uptodate = true;}
						else if(!unconfirmed_block.equals(peer_net_api.last_unconfirmed_idx)){request_unconfirmed_block_update();}
						else if(peer_net_api.network_size < Integer.parseInt(network_size) && peer_net_api.open_network == 1){update_network_list();}
						else if(peer_net_api.send_buffer_size > 0 && peer_net_api.peer1sendUnconfiremdB1 != null){send_unconfirmed_update();}
						else{}


				}//***********************
				else{JsonSentence = "error";}


			}catch(Exception e){ 

				e.printStackTrace(); 
				System.out.println("Cannot find node!"); 
				JsonSentence = "not active!"; 
				peer_net_api.connection_active = 0; 
				peer_net_api.tor_active = 0; 
				peer_net_api.updating = false; 
				peer_net_api.tor_in_use = 0; 

			}//******************


		}//ifpassx

		peer_net_api.tor_in_use = 0;
		return JsonSentence;

	}//*******************************************************************************************************













	public String send_unconfirmed_update(){//*****************************************************************


		try{Thread.sleep(2000);} catch (InterruptedException e){}

		String sentence = new String("");
		String modifiedSentence = new String("");  
		String JsonSentence = new String(""); 
		String jsonText = new String("");
		Socket socket;
		String[] token_id = null;
		String token_id_save = new String("");


		int tests = 0;
		boolean testxl = true;

    	if(testxl){


			try{


				JSONObject token_obj = new JSONObject();
				int xxp1 = 0;
	
				for (int loop = 0; loop < peer_net_api.peer1sendUnconfiremdB1.length; loop++){//************

					token_obj.put(Integer.toString(xxp1), peer_net_api.peer1sendUnconfiremdB1[loop]);
					xxp1++;
					
				}//*****************************************************************************************

				token_id_save = peer_net_api.peer1sendUnconfiremdB1[0];

				JSONObject obj = new JSONObject();
				obj.put("request","add_new_unconfirmed");
				obj.put("token", token_obj);

				StringWriter out = new StringWriter();
				obj.writeJSONString(out);
				jsonText = out.toString();
				System.out.println(jsonText);


			}catch(Exception e){System.out.println("JSON ERROR");}


			try{


	    		for (int loop = 0; loop < 1; loop++){//**********************************************************************************************************

					System.out.println("socket");

	   				socket = tor.getSocketFactory().createSocket(client_address_connect, 80);
	   				//socket.setKeepAlive(true); 
					//socket.setSoTimeout(20000);

					System.out.println("socketg");

	    			OutputStream outputStream = socket.getOutputStream();
	    			PrintWriter outx = new PrintWriter(outputStream);
	    			outx.print(jsonText + "\r\n\r\n");
	    			outx.flush();
	    			InputStream inputStream = socket.getInputStream();
	    			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
	    			BufferedReader in = new BufferedReader(inputStreamReader);

					System.out.println("socketw");

	    			String line;
	    			while ((line = in.readLine()) != null) {

	    				System.out.println(line);
			  			modifiedSentence = line;

	    			}//*************************************

					outputStream.close();
	   				outx.close();
	    			in.close();
	    			socket.close();

					JSONParser parser = new JSONParser();
					Object obj = parser.parse(modifiedSentence);
					JSONObject jsonObject = (JSONObject) obj;
	  	
					String response = (String) jsonObject.get("response");
					String message = (String) jsonObject.get("message");
					System.out.println("JSON " + response);

					if(response.equals("1")){

						JsonSentence = "active";

						Object obj2 = parser.parse(message);
						JSONObject jsonObject2 = (JSONObject) obj2;
	  
						String test = (String) jsonObject2.get("add_task");

						System.out.println("test " + test);

						if(test.equals("1") || test.equals("0")){

							System.out.println("token_id[0] " + token_id_save);
							//peer_database_delete_buffer bufferd = new peer_database_delete_buffer();
							//bufferd.delete(token_id[0]);
							peer_net_api.peer1sendUnconfiremdB1 = null;
							break;

						}//******************


					}//***********************
					else{

						JsonSentence = "error";
						System.out.println("token_id[0] " + token_id_save);
						//peer_database_delete_buffer bufferd = new peer_database_delete_buffer();
						//bufferd.delete(token_id[0]);
						peer_net_api.peer1sendUnconfiremdB1 = null;
						break;

					}//

			
				}//for*******************************************************************************************************************************************


			}catch(Exception e){ 

				e.printStackTrace(); 
				System.out.println("Cannot find node!"); 
				JsonSentence = "not active!";
				peer_net_api.connection_active = 0; 
				peer_net_api.tor_active = 0; 

			}//******************


		}//if

		return JsonSentence;

	}//***************************************************************************************************************











    public void update_buffer_list(String unconfirmed_id){//*********************************************************

    	while(true){

	    	System.out.println("SEND ID BACK");
			JOptionPane.showMessageDialog(null, "SEND ID BACK " + unconfirmed_id);

			String jsonText = new String("");


			try{

				JSONObject obj = new JSONObject();
				obj.put("request", "delete_bufferx_id");
				obj.put("password", "1234");
				obj.put("item_id", unconfirmed_id);

				StringWriter out = new StringWriter();
				obj.writeJSONString(out);
				jsonText = out.toString();
				System.out.println(jsonText);

			}catch(Exception e){e.printStackTrace(); System.out.println("JSON ERROR"); JOptionPane.showMessageDialog(null, e.getMessage());}


			String sentence;   
			String modifiedSentence = new String();   

			try{

				BufferedReader inFromUser = new BufferedReader( new InputStreamReader(System.in) );
				//System.out.println(">>> " + "localhost" + " " + "55556");
				Socket clientSocket = new Socket("localhost", network.api_port);   
				DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
				BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));    
				sentence = jsonText;  
				outToServer.writeBytes(sentence + '\n');   
				modifiedSentence = inFromServer.readLine();   
				System.out.println("FROM SERVER: " + modifiedSentence);
				clientSocket.close();


				JSONParser parser = new JSONParser();

				Object obj = parser.parse(modifiedSentence);
				JSONObject jsonObject = (JSONObject) obj;
		  
				String message = (String) jsonObject.get("message");

				System.out.println("message " + message);

				//if(message.equals("1")){break;}

				JOptionPane.showMessageDialog(null, "message " + message);


			}catch(Exception e){

				e.printStackTrace(); 
				System.out.println("API SERVER OFFLINE!"); 
				modifiedSentence = "API SERVER OFFLINE!"; 
				JOptionPane.showMessageDialog(null, e.getMessage());

			}//*****************

			try{Thread.sleep(2000);} catch (InterruptedException e){}

		}//while

    }//*******************************************************************************************************************************************























	public String send_new_block_update(){//*****************************************************************

		String[] mining_id = peer_net_api.peer1sendBufferB1;
		String[] token_id = peer_net_api.peer1sendBufferB2;



		String jsonText = new String("");

		Socket socket;

		String sentence = new String("");
		String modifiedSentence = new String("");  
		String JsonSentence = new String("");


		int tests = 0;
		boolean testxl = true;
		while(peer_net_api.tor_in_use == 1 && peer_net_api.database_in_use == 1){

    		System.out.println("TOR in use...sbu " + tests);
			try{Thread.sleep(1000);} catch (InterruptedException e){}
			tests++;
			if(tests > 100){testxl = false; new Exception("Cannot send block update!"); break;}

    	}//*****************************

		peer_net_api.tor_in_use = 1;

    	if(testxl){


			try{


				JSONObject token_obj = new JSONObject();
				int xxp1 = 0;
				int xxp2 = 0;
	
				for (int loop = 0; loop < mining_id.length; loop++){//************

					token_obj.put("m" + Integer.toString(xxp1), mining_id[loop]);
					xxp1++;
					
				}//***************************************************************

   				for (int loop = 0; loop < token_id.length; loop++){//*************

					token_obj.put("l" + Integer.toString(xxp2), token_id[loop]);
					xxp2++;

				}//***************************************************************

				JSONObject obj = new JSONObject();
				obj.put("request","add_new_block");
				obj.put("token", token_obj);

				StringWriter out = new StringWriter();
				obj.writeJSONString(out);
				jsonText = out.toString();
				System.out.println(jsonText);


			}catch(Exception e){System.out.println("JSON ERROR");}


 


			try{


   				for (int loop = 0; loop < 1; loop++){//**********************************************************************************************************

					System.out.println("socket");

   					socket = tor.getSocketFactory().createSocket(client_address_connect, 80);
   					//socket.setKeepAlive(true); 
					//socket.setSoTimeout(20000);

					System.out.println("socketg");

    				OutputStream outputStream = socket.getOutputStream();
    				PrintWriter outx = new PrintWriter(outputStream);
    				outx.print(jsonText + "\r\n\r\n");
    				outx.flush();
    				InputStream inputStream = socket.getInputStream();
    				InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
    				BufferedReader in = new BufferedReader(inputStreamReader);

					System.out.println("socketw");

    				String line;
    				while ((line = in.readLine()) != null) {

    					System.out.println(line);
		  				modifiedSentence = line;

    				}//*************************************

					outputStream.close();
   					outx.close();
    				in.close();
    				socket.close();


					JSONParser parser = new JSONParser();
					Object obj = parser.parse(modifiedSentence);
					JSONObject jsonObject = (JSONObject) obj;
  
					String response = (String) jsonObject.get("response");
					String message = (String) jsonObject.get("message");
					System.out.println("JSON " + response);

					if(response.equals("1")){

						JsonSentence = "active";

						Object obj2 = parser.parse(message);
						JSONObject jsonObject2 = (JSONObject) obj2;
  
						String block = (String) jsonObject2.get("add_block");

						JsonSentence = block;

						System.out.println("add_block " + block);

					}//***********************
					else{JsonSentence = "error";}

		
				}//for*******************************************************************************************************************************************


			}catch(Exception e){

				e.printStackTrace(); 
				System.out.println("Cannot find node!"); 
				JsonSentence = "not active!";
				peer_net_api.connection_active = 0;  
				peer_net_api.tor_active = 0; 
				peer_net_api.tor_in_use = 0; 

			}


		}//if


		peer_net_api.peer1sendBufferB1 = null;
		peer_net_api.peer1sendBufferB2 = null;


		peer_net_api.tor_in_use = 0;
		return JsonSentence;

	}//***********************************************************************************************




















	public String request_block_update(){//*****************************************************************


		String jsonText = new String("");
		String sentence = new String("");
		String modifiedSentence = new String("");  
		String JsonSentence = new String(""); 

		Socket socket;


		try{

			JSONObject obj = new JSONObject();
			obj.put("request","block_update");
			obj.put("block_id", peer_net_api.last_block_mining_idx);

			StringWriter out = new StringWriter();
			obj.writeJSONString(out);
			jsonText = out.toString();
			System.out.println(jsonText);

		}catch(Exception e){System.out.println("JSON ERROR");}


		try{

			System.out.println("address: " + client_port_connect);
			System.out.println("address: " + client_address_connect);

			System.out.println("socket");

   			socket = tor.getSocketFactory().createSocket(client_address_connect, 80);
   			//socket.setKeepAlive(true); 
			//socket.setSoTimeout(20000);

			System.out.println("socketg");

    		OutputStream outputStream = socket.getOutputStream();
    		PrintWriter outx = new PrintWriter(outputStream);
    		outx.print(jsonText + "\r\n\r\n");
    		outx.flush();
    		InputStream inputStream = socket.getInputStream();
    		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
    		BufferedReader in = new BufferedReader(inputStreamReader);

			System.out.println("socketw");

    		String line;
    		while ((line = in.readLine()) != null) {

    		  System.out.println(line);
		  	  modifiedSentence = line;

    		}//*************************************

			outputStream.close();
   			outx.close();
    		in.close();
    		socket.close();


			JSONParser parser = new JSONParser();
			Object obj = parser.parse(modifiedSentence);
			JSONObject jsonObject = (JSONObject) obj;
  
			String response = (String) jsonObject.get("response");
			String message = (String) jsonObject.get("message");
			System.out.println("JSON " + response);

			if(response.equals("1")){

				JsonSentence = "active";

				Object obj2 = parser.parse(message);
				JSONObject jsonObject2 = (JSONObject) obj2;
  

				String update_token[] = new String[peer_net_api.listing_size];
				String mining_token[] = new String[peer_net_api.miningx_size];

    			for (int loop = 0; loop < peer_net_api.listing_size; loop++){//************

					update_token[loop] = (String) jsonObject2.get("l" + Integer.toString(loop));
					System.out.println("convert " + update_token[loop]);

				}//*******************************************************************

    			for (int loop = 0; loop < mining_token.length; loop++){//************

					mining_token[loop] = (String) jsonObject2.get("m" + Integer.toString(loop));
					System.out.println("convert " + mining_token[loop]);

				}//******************************************************************



				int test_db = 0;
				while(peer_net_api.database_in_use == 1){

    				System.out.println("Database in use...04");
					try{Thread.sleep(1000);} catch (InterruptedException e){}
					test_db++;
					if(test_db > 20){break;}

    			}//*********************************

        		//get the last token
        		krypton_database_get_token getxt = new krypton_database_get_token();
        		String req_id = update_token[0];
        		String old_token[] = new String[peer_net_api.listing_size];
        		old_token = getxt.get_token(req_id);

        		boolean gott = false;
        		try{System.out.println("GOT TOKENX: " + Integer.parseInt(old_token[0])); gott = true;}
        		catch(Exception e){gott = false;}

        		System.out.println("gott " + gott);

        		boolean test = false;

        		if(gott){

        			//try to add the new token
        			krypton_update_new_block_remote remotex = new krypton_update_new_block_remote();
					test = remotex.update(update_token, mining_token, old_token);

				}//******
				else{

        			//too dangerous
        			//krypton_update_new_block_remote_nt remotex = new krypton_update_new_block_remote_nt();
					//test = remotex.update(update_token, old_token);

				}//**


				if(!test){

        			krypton_update_block_stale remotex3 = new krypton_update_block_stale();
					boolean test3 = remotex3.update(update_token, mining_token, old_token);

					if(test3){System.out.println("Our block was changed to the server's block.");}

				}//*******
				else{System.out.println("....NO NEED TO BREAK THE CHAIN....");}

				krypton_database_load loadx = new krypton_database_load();

			}//***********************
			else{JsonSentence = "error";}

		


		}catch(Exception e){

			e.printStackTrace(); 
			System.out.println("Cannot find node!"); 
			JsonSentence = "not active!";
			peer_net_api.connection_active = 0; 
			peer_net_api.tor_active = 0; 

		}


		return JsonSentence;

	}//***********************************************************************************************















	public String request_blocks_x_update(){//*****************************************************************



		String jsonText = new String("");
		String sentence = new String("");
		String modifiedSentence = new String("");  
		String JsonSentence = new String(""); 
		Socket socket;



		try{

			JSONObject obj = new JSONObject();
			obj.put("request","blocks_x_update");
			obj.put("block_id", peer_net_api.last_block_mining_idx);

			StringWriter out = new StringWriter();
			obj.writeJSONString(out);
			jsonText = out.toString();
			System.out.println(jsonText);

		}catch(Exception e){System.out.println("JSON ERROR");}


		try{

			System.out.println("address: " + client_port_connect);
			System.out.println("address: " + client_address_connect);

			System.out.println("socket");

   			socket = tor.getSocketFactory().createSocket(client_address_connect, 80);
   			//socket.setKeepAlive(true); 
			//socket.setSoTimeout(20000);

			System.out.println("socketg");

    		OutputStream outputStream = socket.getOutputStream();
    		PrintWriter outx = new PrintWriter(outputStream);
    		outx.print(jsonText + "\r\n\r\n");
    		outx.flush();
    		InputStream inputStream = socket.getInputStream();
    		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
    		BufferedReader in = new BufferedReader(inputStreamReader);

			System.out.println("socketw");

    		String line;
    		while ((line = in.readLine()) != null) {

    		  System.out.println(line);
		  	  modifiedSentence = line;

    		}//*************************************

			outputStream.close();
   			outx.close();
    		in.close();
    		socket.close();


			JSONParser parser = new JSONParser();
			Object obj = parser.parse(modifiedSentence);
			JSONObject jsonObject = (JSONObject) obj;
  
			String response = (String) jsonObject.get("response");
			String message = (String) jsonObject.get("message");
			System.out.println("JSON " + response);



			Object objxl = parser.parse(message);
			JSONObject jsonObjectxl = (JSONObject) objxl;



    		for (int loop = 0; loop < peer_net_api.package_block_size; loop++){//***********************************************************************************

				String bufferp = (String) jsonObjectxl.get(Integer.toString(loop));
				System.out.println("break up " + bufferp);
				System.out.println("loop " + loop);
			
				if(response.equals("1") && bufferp != null){

					JsonSentence = "active";

					Object obj2 = parser.parse(bufferp);
					JSONObject jsonObject2 = (JSONObject) obj2;
  


					String mining_token[] = new String[peer_net_api.miningx_size];
					String update_token[] = new String[peer_net_api.listing_size];

    				for (int loopx = 0; loopx < peer_net_api.listing_size; loopx++){//************

						update_token[loopx] = (String) jsonObject2.get("l" + Integer.toString(loopx));
						System.out.println("convert " + update_token[loopx]);

					}//*******************************************************************

    				for (int loopx = 0; loopx < mining_token.length; loopx++){//************

						mining_token[loopx] = (String) jsonObject2.get("m" + Integer.toString(loopx));
						System.out.println("convert " + mining_token[loopx]);

					}//******************************************************************



					int test_db = 0;
					while(peer_net_api.database_in_use == 1){

    					System.out.println("Database in use...cx");
						try{Thread.sleep(1000);} catch (InterruptedException e){}
						test_db++;
						if(test_db > 20){break;}

    				}//*********************************

        			//get the last token
        			krypton_database_get_token getxt = new krypton_database_get_token();
        			String req_id = update_token[0];
        			String old_token[] = new String[peer_net_api.listing_size];
        			old_token = getxt.get_token(req_id);

        			boolean gott = false;
        			try{System.out.println("GOT TOKENX: " + Integer.parseInt(old_token[0])); gott = true;}
        			catch(Exception e){gott = false;}

        			System.out.println("gott " + gott);

        			boolean test = false;

        			if(gott && !update_token[0].equals("error")){

        				//try to add the new token
        				krypton_update_new_block_remote remotex = new krypton_update_new_block_remote();
						test = remotex.update(update_token, mining_token, old_token);
						
					}//******
					else{
        				//try to add the new token but we don't have the old token
        				//too dangerous
        				//krypton_update_new_block_remote_nt remotex = new krypton_update_new_block_remote_nt();
						//test = remotex.update(update_token, old_token);
					}//**



					if(!test){

        				krypton_update_block_stale remotex3 = new krypton_update_block_stale();
						boolean test3 = remotex3.update(update_token, mining_token, old_token);

						if(test3){System.out.println("Our block was changed to the server's block.");}
						else{System.out.println("EXIT"); try{Thread.sleep(440000);} catch (InterruptedException e){} break;}

					}//*******
					else{System.out.println("....NO NEED TO BREAK THE CHAIN....");}

					krypton_database_load loadx = new krypton_database_load();
					try{Thread.sleep(1000);} catch (InterruptedException e){}



				}//***********************
				else{JsonSentence = "error"; break;}

		

			}//for***************************************************************************************************************************************


		}catch(Exception e){ 

			e.printStackTrace(); 
			System.out.println("Cannot find node!"); 
			JsonSentence = "not active!"; 
			peer_net_api.connection_active = 0; 
			peer_net_api.tor_active = 0; 

		}


		return JsonSentence;

	}//***********************************************************************************************

















	public String request_unconfirmed_block_update(){//*****************************************************************


		//called from request_status cannot test
		//network.tor_in_use = 1;

		String jsonText = new String("");
		String sentence = new String("");
		String modifiedSentence = new String("");  
		String JsonSentence = new String(""); 
		Socket socket;



		try{

			JSONObject obj = new JSONObject();
			obj.put("request","unconfirmed_block_update");
			obj.put("unconfirmed_id", peer_net_api.last_unconfirmed_idx);

			StringWriter out = new StringWriter();
			obj.writeJSONString(out);
			jsonText = out.toString();
			System.out.println(jsonText);

		}catch(Exception e){System.out.println("JSON ERROR");}


		try{

			System.out.println("address: " + client_port_connect);
			System.out.println("address: " + client_address_connect);

			System.out.println("socket");

   			socket = tor.getSocketFactory().createSocket(client_address_connect, 80);
   			//socket.setKeepAlive(true); 
			//socket.setSoTimeout(20000);

			System.out.println("socketg");

    		OutputStream outputStream = socket.getOutputStream();
    		PrintWriter outx = new PrintWriter(outputStream);
    		outx.print(jsonText + "\r\n\r\n");
    		outx.flush();
    		InputStream inputStream = socket.getInputStream();
    		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
    		BufferedReader in = new BufferedReader(inputStreamReader);

			System.out.println("socketw");

    		String line;
    		while ((line = in.readLine()) != null) {

    		  System.out.println(line);
		  	  modifiedSentence = line;

    		}//*************************************

			outputStream.close();
   			outx.close();
    		in.close();
    		socket.close();


			JSONParser parser = new JSONParser();
			Object obj = parser.parse(modifiedSentence);
			JSONObject jsonObject = (JSONObject) obj;
  
			String response = (String) jsonObject.get("response");
			String message = (String) jsonObject.get("message");
			System.out.println("JSON " + response);

			if(response.equals("1")){

				JsonSentence = "active";

				Object obj2 = parser.parse(message);
				JSONObject jsonObject2 = (JSONObject) obj2;
  
				String update_token[] = new String[peer_net_api.listing_size];

    			for (int loop = 0; loop < peer_net_api.listing_size; loop++){//************

					update_token[loop] = (String) jsonObject2.get(Integer.toString(loop));
					System.out.println("convert " + update_token[loop]);

				}//*******************************************************************


            	int test_db = 0;
				while(peer_net_api.database_in_use == 1){

    				System.out.println("Database in use...rruu");
					try{Thread.sleep(1000);} catch (InterruptedException e){}
					test_db++;
					if(test_db > 20){break;}

    			}//*********************************


        		//get the last token
        		krypton_database_get_token getxt = new krypton_database_get_token();
        		String req_id = update_token[0];
        		String old_token[] = new String[peer_net_api.listing_size];
        		old_token = getxt.get_token(req_id);

        		krypton_update_token_remote remotexu2 = new krypton_update_token_remote();
				boolean test2 = remotexu2.update(update_token, old_token);

				if(test2){System.out.println("ADDED!"); krypton_database_load reloadx = new krypton_database_load();}

			}//***********************
			else{JsonSentence = "error";}

		


		}catch(Exception e){

			e.printStackTrace(); 
			System.out.println("Cannot find node!"); 
			JsonSentence = "not active!"; 
			peer_net_api.connection_active = 0; 
			peer_net_api.tor_active = 0; 

		}

		return JsonSentence;

	}//***********************************************************************************************













	public String update_network_list(){

		//called from request_status cannot test
		//network.tor_in_use = 1;


		String jsonText = new String("");
		String sentence = new String("");
		String modifiedSentence = new String("");  
		String JsonSentence = new String(""); 
		Socket socket;



		try{

			JSONObject obj = new JSONObject();
			obj.put("request","get_network");

			StringWriter out = new StringWriter();
			obj.writeJSONString(out);
			jsonText = out.toString();
			System.out.println(jsonText);

		}catch(Exception e){System.out.println("JSON ERROR");}


		try{

			System.out.println("address: " + client_port_connect);
			System.out.println("address: " + client_address_connect);

			System.out.println("socket");

   			socket = tor.getSocketFactory().createSocket(client_address_connect, 80);
   			//socket.setKeepAlive(true); 
			//socket.setSoTimeout(20000);

			System.out.println("socketg");

    		OutputStream outputStream = socket.getOutputStream();
    		PrintWriter outx = new PrintWriter(outputStream);
    		outx.print(jsonText + "\r\n\r\n");
    		outx.flush();
    		InputStream inputStream = socket.getInputStream();
    		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
    		BufferedReader in = new BufferedReader(inputStreamReader);

			System.out.println("socketw");

    		String line;
    		while ((line = in.readLine()) != null) {

    		  System.out.println(line);
		  	  modifiedSentence = line;

    		}//*************************************

			outputStream.close();
   			outx.close();
    		in.close();
    		socket.close();


			JSONParser parser = new JSONParser();
			Object obj = parser.parse(modifiedSentence);
			JSONObject jsonObject = (JSONObject) obj;
  
			String response = (String) jsonObject.get("response");
			String message = (String) jsonObject.get("message");
			System.out.println("JSON " + response);

			if(response.equals("1")){

				JsonSentence = "active";

				Object obj2 = parser.parse(message);
				//JSONObject jsonObject2 = (JSONObject) obj2;
				JSONArray array = (JSONArray) obj2;

  				System.out.println(array.size());


				for(int loop = 0; loop < array.size(); loop++){

        			System.out.println("new nodes " + array.get(loop));
        			krypton_database_nodes add_node = new krypton_database_nodes(array.get(loop).toString());

				}//********************************************

				while(peer_net_api.database_in_use == 1){

					int test_db = 0;
    				System.out.println("Database in use...03");
					try{Thread.sleep(1000);} catch (InterruptedException e){}
					test_db++;
					if(test_db > 20){break;}

    			}//*********************************

				krypton_database_load_network nodesx = new krypton_database_load_network();

			}//***********************
			else{JsonSentence = "error";}

		


		}catch(Exception e){

			e.printStackTrace(); 
			System.out.println("Cannot find node!"); 
			JsonSentence = "not active!"; 
			peer_net_api.connection_active = 0; 
			peer_net_api.tor_active = 0; 

		}

		
		return JsonSentence;

	}//*******************************


















	public void test_for_internet(){


		System.out.println("Testing");


		peer_net_api.internet_access = 0;

		String cx0i = new String("");

		// Construct data
		try {


    		//String data = URLEncoder.encode("item_id", "UTF-8") + "=" + URLEncoder.encode(nm.carbon[0][part_number_xx][ix1], "UTF-8");

    		// Send data
			//URL url = new URL("http://www.yahoo.com");
    		//URL url = new URL("http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22nome%2C%20ak%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys");
    		URL url = new URL("http://www.bing.com/");
   			URLConnection conn = url.openConnection();
   			conn.setDoOutput(true);
    		OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
    		//wr.write(data);
    		wr.flush();

    		// Get the response
    		BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
    		String line;
    		while ((line = rd.readLine()) != null){

       			cx0i = line;

				//System.out.println(cx0i);
				if(cx0i.length() > 0){peer_net_api.internet_access = 1;}
				else{peer_net_api.internet_access = 0;}//network.status9.setText("No internet test!"); network.status9.setIcon(network.imx0);


    		}//************************************
    		wr.close();
   			rd.close();
    		//conn.close();

		}catch(Exception e){

			e.printStackTrace();
			peer_net_api.internet_access = 0; 
			System.out.println("URL falure."); 
			//network.status9.setText("No internet!"); 
			//network.status9.setIcon(network.imx0);

		}//*****************




	}//updates******************




























	public void initTor() throws Exception {


		peer_net_api.tor_in_use = 1;
		peer_net_api.tor_active = 0;
		peer_net_api.tor_starting = 1;

		test_for_internet();

		while(peer_net_api.internet_access == 0){

			//no_peers_time = 0;

			try{Thread.sleep(10000);} catch (InterruptedException e){}

			test_for_internet();

		}//while


		//System.gc();

		System.out.println("Start TOR...");

        // Oracle actually got permission to enable AES256 everywhere years ago anyway, they just didn't get around to
        // actually doing so yet!

        tor = new TorClient();
        tor.addInitializationListener(new TorInitializationListener() {
            @Override
            public void initializationProgress(String message, int percent) {

                System.out.println(">>> [ " + percent + "% ]: " + message);
				//network.status9.setText("LOAD TOR >>> [ " + percent + "% ]: " + message);

            }//**************************************************************

            @Override
            public void initializationCompleted() {

          		System.out.println("Tor is ready to go!");
            	//network.status9.setText("Tor is ready to go!");
				//network.status9.setIcon(network.imx1);

				peer_net_api.tor_starting = 0;
				peer_net_api.tor_active = 1;

				peer_net_api.tor_in_use = 0;
				//System.gc();

            }//************************************
        });

		//url_connect.setEnabled(false);
		//lm.searchb.setEnabled(false);
		//lm.searchx.setEnabled(false);
		//lm.searchf.setEnabled(false);

        tor.start();

    }//****************************************







}//last
