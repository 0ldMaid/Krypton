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





public class krypton_net_client{

static TorClient tor;


Timer xtimerx;//class loop.
Timer xtimerx2;//class loop.
Toolkit toolkit;

String client_address_connect = new String("127.0.0.1");
int client_port_connect = 0000;

static long client_loop_time = (long) 0;

int node_timeout = 20000;
static int network_sizex = 0;
static int breakx1 = 0;
static int breakx2 = 0;

boolean updating = false;
static boolean blocks_uptodate = false;

static String new_hash1 = new String("");
static String new_hash2 = new String("");
static String new_hash3 = new String("");
static String new_hash4 = new String("");


long threadId;



krypton_net_client(){//*****************************************************************


	network_sizex = network.network_size;
	System.out.println("network_sizex " + network_sizex);

	krypton_net_client.client_loop_time = System.currentTimeMillis();

	//the backup system
	toolkit = Toolkit.getDefaultToolkit();
	xtimerx = new Timer();
	xtimerx.schedule(new RemindTask_backup(), 0);

	//main update system
	toolkit = Toolkit.getDefaultToolkit();
	xtimerx = new Timer();
	xtimerx.schedule(new RemindTask_client1(), 0);


}//*************************************************************************************










	class RemindTask_backup extends TimerTask{
	Runtime rxrunti = Runtime.getRuntime();

	public void run(){//************************************************************************************

		long thisTick = (long) System.currentTimeMillis();

		while(true){


			thisTick = System.currentTimeMillis();

			network.no_peers_time++; 
			System.out.println("network.no_peers_time " + network.no_peers_time);

			//network.status_x1.setText("network.no_peers_time " + network.no_peers_time);

			try{Thread.sleep(10000);} catch(InterruptedException e){e.printStackTrace();}

			System.out.println("Loop 32");


		}//while****

		//JOptionPane.showMessageDialog(null, "LOOP EXIT!");

	}//runx*************************************************************************************************
    }//remindtask










    //main update system
	class RemindTask_client1 extends TimerTask{
	Runtime rxrunti = Runtime.getRuntime();

	public void run(){//************************************************************************************


		while(true){


			while(network.client == 1){


				threadId = Thread.currentThread().getId();
				System.out.println("threadId " + threadId);

				client_loop_time = System.currentTimeMillis();

				breakx1 = 0;
				breakx2 = 0;


				System.out.println("TOR START");
				System.gc();


				try{

					//network.no_peers_time = 0;
					network.tor_active = 0;
					initTor();

				}catch(Exception e){e.printStackTrace();}




				int startx = 0;
				while(network.tor_active == 0){

					System.out.println("Waiting for startup... " + startx);
					try{Thread.sleep(1000);} catch (InterruptedException e){e.printStackTrace();}
					startx++;
					if(startx > 150){break;}

				}//****************************



				try{Thread.sleep(5000);}catch(InterruptedException e){}




				for (int loop = 0; loop < 5; loop++){//**************************************************************************************************



				if(network.tor_active == 1){

					try{

						network_sizex = network.network_size;
						System.out.println("network_sizex " + network_sizex);

    					try{Thread.sleep(1000);}catch(InterruptedException e){}

    					if(network.peersx0 == 1){mining.mining3 = true;}
    					else{mining.mining3 = false;}

						run0();

						//sometimes the clent tries to connect to fast.
						try{Thread.sleep(10000);}catch(InterruptedException e){}

						run1();
						//run2();

					}catch(Exception e){e.printStackTrace();}//******************

				}//*************************


				System.out.println("breakid " + loop);

				//take a break
				breakx1 = 0;
				while(network.tor_active == 1){

					//System.out.println("break1 " + breakx1);
					try{Thread.sleep(1000);}catch(InterruptedException e){}
					breakx1++;

					if(breakx1 > 30){System.out.println("break end"); break;}//this over 400 seems to be trouble
					if(!blocks_uptodate){break;}

  				}//****************************


  				if(network.tor_active == 0){break;}

  				}//for***********************************************************************************************************************************




  			

				System.out.println("TOR SHUTDOWN");

				try{

					network.peersx0 = 0;
					network.tor_active = 0;
					mining.mining_stop = 1;
					tor.stop();
		    		System.gc();
		    		//network.status9.setIcon(network.imx0);
		    		//network.status10.setIcon(network.imx0);
		    		//network.status10.setText("Peer(s): (0)");

				}catch(Exception e){e.printStackTrace();}

				System.out.println("TOR EXIT");

			}//while****

			System.out.println("Client internet is off.");
			try{Thread.sleep(10000);} catch(InterruptedException e){e.printStackTrace();}

		}//while****

		//JOptionPane.showMessageDialog(null, "LOOP EXIT!");

	}//runx*************************************************************************************************
    }//remindtask








	public void run0(){//************************************************************************************


		System.out.println("Krypton Network Client...0");
		int node_match = 1;


		System.out.println("nodelist size 0 " + network_sizex);



		if(network_sizex == 0){}//network.status_x1.setText("No network list!");
		else if(network.tor_active == 0 && network.tor_starting == 1){System.out.println("TOR IS NOT READY..."); mining.mining_stop = 1;}
		else{


			System.out.println("Other node work...");

			//look for an active node
				if(network.peerid0 == -1){

					Random rand = new Random();
					network.peerid0 = rand.nextInt(network_sizex) + 0;

					System.out.println("peerid0 " + network.peerid0);

				}//**********************
				else{System.out.println("Already has ID 0 " + network.peerid0);}


		    	client_port_connect = Integer.parseInt("55555");

		    	try{
		    		client_address_connect = network.network_list.get(network.peerid0).toString();
		    	}catch(Exception e){network.peerid0 = -1;}

		    	String status = request_status(0);
		    	System.out.println("client_address_connect " + client_address_connect);

		    	if(status.equals("active")){

		    		System.out.println("nodelist size 4 " + network_sizex);
		    		network.peersx0 = 1;
		    		//network.status10.setIcon(network.imx1);
		    		//network.status10.setText("Peer(s): (" + Integer.toString(network.peersx0 + network.peersx1 + network.peersx2) + ")");
		    		try{Thread.sleep(node_timeout);} catch (InterruptedException e){e.printStackTrace();}


		    		if(!blocks_uptodate){

						updating = true;
						request_blocks_x_update(); 
						updating = false;

					}//******************

				}//if**********************
				else{

					if(network_sizex > 1){

						//krypton_database_delete_node pxd = new krypton_database_delete_node();
            			//pxd.delete(client_address_connect);
            			
            		}//*******************

            		network.peerid0 = -1;
		    		network.peersx0 = 0;

		    		int testg = network.peersx0 + network.peersx1 + network.peersx2;

		    		if(testg == 0){

		    			//network.status10.setIcon(network.imx0);
						mining.mining_stop = 1;
		    			network.tor_active = 0;
		    			network.tor_starting = 0;

					}//************

				}//else



		    System.out.println("nodelist size 5 " + network_sizex);
		    //network.status10.setText("Peer(s): (" + Integer.toString(network.peersx0 + network.peersx1 + network.peersx2) + ")");


		}//else		


	}//runx*************************************************************************************************









	public void run1(){//************************************************************************************



		System.out.println("Krypton Network Client...1");
		int node_match = 1;

 


		//System.out.println("network size " + network_sizex);

		if(network_sizex == 0){}
		else if(network.tor_active == 0 && network.tor_starting == 1){System.out.println("TOR IS NOT READY...");}
		else{


			System.out.println("Other node work...");

			//look for an active node
				if(network.peerid1 == -1){

					Random rand = new Random();
					network.peerid1 = rand.nextInt(network_sizex) + 0;

					System.out.println("peerid1 " + network.peerid1);

				}//**********************
				else{System.out.println("Already has ID 1 " + network.peerid1); node_match = 0;}


				//test
					 if(network.peerid1 == network.peerid0){node_match = 1; network.peerid1 = -1;}
				else if(network.peerid1 == network.peerid2){node_match = 1; network.peerid1 = -1;}
				else{node_match = 0;}


			//test connection
			if(node_match == 0){

		    	client_port_connect = Integer.parseInt("55555");

		    	try{
		    		client_address_connect = network.network_list.get(network.peerid1).toString();
		    	}catch(Exception e){network.peerid1 = -1;}

		    	String status = request_status_ss(1);

		    	System.out.println("client_address_connect " + client_address_connect);

		    	if(status.equals("active")){

		    		network.peersx1 = 1;
		    		//network.status10.setIcon(network.imx1);
		    		//network.status10.setText("Peer(s): (" + Integer.toString(network.peersx0 + network.peersx1 + network.peersx2) + ")");
		    		try{Thread.sleep(node_timeout);} catch (InterruptedException e){e.printStackTrace();}

				}//if**********************
				else{

					if(network_sizex > 1){

						//krypton_database_delete_node pxd = new krypton_database_delete_node();
            			//pxd.delete(client_address_connect);
            			
            		}//*******************

            		network.peerid1 = -1;
		    		network.peersx1 = 0;

		    		int testg = network.peersx0 + network.peersx1 + network.peersx2;

		    		//if(testg == 0){network.status10.setIcon(network.imx0);}

				}//else


			}//if***************
			else{System.out.println("NODE MATCH 1!"); network.peerid1 = -1; network.peersx1 = 0;}
			//test connection

		    //network.status10.setText("Peer(s): (" + Integer.toString(network.peersx0 + network.peersx1 + network.peersx2) + ")");


		}//else		





	}//runx*************************************************************************************************









	public void run2(){//************************************************************************************



		System.out.println("Krypton Network Client...2");
		int node_match = 1;

   


		//System.out.println("network size " + network_sizex);

		if(network_sizex == 0){}
		else if(network.tor_active == 0 && network.tor_starting == 1){System.out.println("TOR IS NOT READY...");}
		else{


			System.out.println("Other node work...");

			//look for an active node
				if(network.peerid2 == -1){

					Random rand = new Random();
					network.peerid2 = rand.nextInt(network_sizex) + 0;

					System.out.println("peerid2 " + network.peerid2);

				}//**********************
				else{System.out.println("Already has ID 2 = " + network.peerid2); node_match = 0;}


				//test
					 if(network.peerid2 == network.peerid0){node_match = 1; network.peerid2 = -1;}
				else if(network.peerid2 == network.peerid1){node_match = 1; network.peerid2 = -1;}
				else{node_match = 0;}	


			//test connection
			if(node_match == 0){

		    	client_port_connect = Integer.parseInt("55555");
		    	client_address_connect = network.network_list.get(network.peerid2).toString(); 
		    	String status = request_status_ss(2);

		    	System.out.println("client_address_connect " + client_address_connect);

		    	if(status.equals("active")){

		    		network.peersx2 = 1;
		    		//network.status10.setIcon(network.imx1);
		    		//network.status10.setText("Peer(s): (" + Integer.toString(network.peersx0 + network.peersx1 + network.peersx2) + ")");
		    		try{Thread.sleep(node_timeout);} catch (InterruptedException e){e.printStackTrace();}

				}//if**********************
				else{

					if(network_sizex > 1){

						//krypton_database_delete_node pxd = new krypton_database_delete_node();
            			//pxd.delete(client_address_connect);

            		}//*******************

            		network.peerid2 = -1;
		    		network.peersx2 = 0;

		    		int testg = network.peersx0 + network.peersx1 + network.peersx2;

		    		//if(testg == 0){network.status10.setIcon(network.imx0);}

				}//else

			}//if***************
			else{System.out.println("NODE MATCH 2!"); network.peerid2 = -1; network.peersx2 = 0;}
			//test connection

		    //network.status10.setText("Peer(s): (" + Integer.toString(network.peersx0 + network.peersx1 + network.peersx2) + ")");


		}//else		





	}//runx*************************************************************************************************















	public String request_status_ss(int node){//*****************************************************************


		boolean passx = true;


		String sentence = new String("");
		String modifiedSentence = new String("");  
		String JsonSentence = new String(""); 



    	if(passx){


			String jsonText = new String("");

			SocketFactory factory;
			Socket socket;


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

				factory = tor.getSocketFactory();
   				socket = factory.createSocket(client_address_connect, 80);
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
  
					String block = (String) jsonObject2.get("last_block_id");
					String block_timestamp = (String) jsonObject2.get("last_block_timestamp");
					String unconfirmed_block = (String) jsonObject2.get("last_unconfirmed_id");
					String network_size = (String) jsonObject2.get("node_list");

					System.out.println("block " + block);
					System.out.println("unconfirmed_block " + unconfirmed_block);
					System.out.println("network_size " + network_size);


						network.no_peers_time = 0;


						if(node == 0){new_hash1 = block;}
						if(node == 1){new_hash2 = block;}
						if(node == 2){new_hash3 = block;}
						if(node == 3){new_hash4 = block;}


				}//***********************
				else{JsonSentence = "error";}


			}catch(Exception e){ System.out.println("Cannot find node!"); JsonSentence = "not active!"; network.tor_active = 0; updating = false; }


		}//ifpassx

		return JsonSentence;

	}//**********************************************************************************************************






















	public String request_status(int node){//*****************************************************************


		boolean passx = true;

		int tests = 0;
		while(network.tor_in_use == 1 || network.tor_active == 0){

    		System.out.println("TOR in use...rqs " + tests);
			try{Thread.sleep(1000);} catch (InterruptedException e){}
			tests++;
			if(tests > 100){

				passx = false;
				break;

		    }//*************

    	}//*******************************************************



			String sentence = new String("");
			String modifiedSentence = new String("");  
			String JsonSentence = new String(""); 



    	if(passx){


			network.tor_in_use = 1;

			String jsonText = new String("");

			SocketFactory factory;
			Socket socket;


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

				factory = tor.getSocketFactory();
   				socket = factory.createSocket(client_address_connect, 80);
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
  
					String block = (String) jsonObject2.get("last_block_id");
					String block_timestamp = (String) jsonObject2.get("last_block_timestamp");
					String unconfirmed_block = (String) jsonObject2.get("last_unconfirmed_id");
					String network_size = (String) jsonObject2.get("node_list");

					System.out.println("block " + block);
					System.out.println("unconfirmed_block " + unconfirmed_block);
					System.out.println("network_size " + network_size);


						network.no_peers_time = 0;


						if(node == 0){new_hash1 = block;}
						if(node == 1){new_hash2 = block;}
						if(node == 2){new_hash3 = block;}
						if(node == 3){new_hash4 = block;}



						if(block.equals(network.last_block_mining_idx)){System.out.println("Blocks up to date..."); mining.mining3 = true; network.network_up_to_date = 0; node_timeout = 10000; blocks_uptodate = true;}
						else if(Long.parseLong(block_timestamp) < Long.parseLong(network.last_block_timestamp)){System.out.println("Blocks are ahead..."); mining.mining3 = false; network.network_up_to_date = 0; node_timeout = 10000; blocks_uptodate = true;}
						else{System.out.println("Blocks are not up to date..."); mining.mining3 = false; network.network_up_to_date = 1; node_timeout = 4000; blocks_uptodate = false;}

						if(unconfirmed_block.equals(network.last_unconfirmed_idx) || unconfirmed_block.equals("")){System.out.println("Unconfirmed blocks up to date...");}
						else{System.out.println("Unconfirmed blocks are not up to date..."); node_timeout = 4000;}

						if(network.network_size >= Integer.parseInt(network_size) || network.open_network == 0){System.out.println("Network is up to date...");}
						else{System.out.println("Network is missing nodes...");}

						if(network.send_buffer_size > 1){node_timeout = 4000;}



						//run
						if(!block.equals(network.last_block_mining_idx)){blocks_uptodate = false;}
						else if(Long.parseLong(block_timestamp) < Long.parseLong(network.last_block_timestamp)){blocks_uptodate = true;}
						else if(!unconfirmed_block.equals(network.last_unconfirmed_idx)){request_unconfirmed_block_update();}
						else if(network.network_size < Integer.parseInt(network_size) && network.open_network == 1){update_network_list();}
						else if(network.send_buffer_size > 0){send_unconfirmed_update();}
						else{}


				}//***********************
				else{JsonSentence = "error";}


			}catch(Exception e){ System.out.println("Cannot find node!"); JsonSentence = "not active!"; network.tor_active = 0; updating = false; network.tor_in_use = 0; }


		}//ifpassx

		network.tor_in_use = 0;
		return JsonSentence;

	}//*******************************************************************************************************













	public static String send_unconfirmed_update(){//*****************************************************************


		String jsonText = new String("");

		SocketFactory factory;
		Socket socket;

		String[] token_id = null;



		while(network.database_in_use == 1){

    		System.out.println("Database in use...unu");
			try{Thread.sleep(1000);} catch (InterruptedException e){}

  		}//*********************************


		try{


    		krypton_database_get_buffer bufferx = new krypton_database_get_buffer();
			token_id = bufferx.getToken();

			JSONObject token_obj = new JSONObject();
   				for (int loop = 0; loop < token_id.length; loop++){//*************
				token_obj.put(Integer.toString(loop), token_id[loop]);
			}//***************************************************************


			JSONObject obj = new JSONObject();
			obj.put("request","add_new_unconfirmed");
			obj.put("token", token_obj);

			StringWriter out = new StringWriter();
			obj.writeJSONString(out);
			jsonText = out.toString();
			System.out.println(jsonText);


		}catch(Exception e){System.out.println("JSON ERROR");}


		String sentence = new String("");
		String modifiedSentence = new String("");  
		String JsonSentence = new String(""); 


		try{


    		for (int loop = 0; loop < 4; loop++){//**********************************************************************************************************

				System.out.println("socket");

				String address_connectx = new String("");

				if(loop == 0){address_connectx = network.network_list.get(network.peerid0).toString();}
				if(loop == 1){address_connectx = network.network_list.get(network.peerid1).toString();}
				if(loop == 2){address_connectx = network.network_list.get(network.peerid2).toString();}
				if(loop == 3){address_connectx = network.network_list.get(network.peerid4).toString();}
		
				if(address_connectx.length() < 10){break;}

				factory = tor.getSocketFactory();
   				socket = factory.createSocket(address_connectx, 80);
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

						System.out.println("token_id[0] " + token_id[0]);
						krypton_database_delete_buffer bufferd = new krypton_database_delete_buffer();
						bufferd.delete(token_id[0]);
						break;

					}//******************


				}//***********************
				else{JsonSentence = "error";}

		
			}//for*******************************************************************************************************************************************


		}catch(Exception e){ e.printStackTrace(); System.out.println("Cannot find node!"); JsonSentence = "not active!"; network.tor_active = 0; }


		return JsonSentence;

	}//***************************************************************************************************************











	public static String send_new_block_update(String[] mining_id, String[] token_id){//*****************************************************************

		int tests = 0;
		boolean testxl = true;
		while(network.tor_in_use == 1 || network.tor_active == 0){

    		System.out.println("TOR in use...sbu " + tests);
			try{Thread.sleep(1000);} catch (InterruptedException e){}
			tests++;
			if(tests > 100){new Exception("Cannot send block update!"); testxl = false; break;}

    	}//*******************************************************

       	int test_db = 0;
		while(network.database_in_use == 1){

    		System.out.println("Database in use...sbu");
			try{Thread.sleep(1000);} catch (InterruptedException e){}
			test_db++;
			if(test_db > 50){break;}

    	}//**********************************


		network.tor_in_use = 1;

		String jsonText = new String("");

		SocketFactory factory;
		Socket socket;

		String sentence = new String("");
		String modifiedSentence = new String("");  
		String JsonSentence = new String("");


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


   				for (int loop = 0; loop < 4; loop++){//**********************************************************************************************************

					System.out.println("socket");

					String address_connectx = new String("");

					try{if(loop == 0){address_connectx = network.network_list.get(network.peerid0).toString();}}catch(Exception e){break;}
					try{if(loop == 1){address_connectx = network.network_list.get(network.peerid1).toString();}}catch(Exception e){break;}
					try{if(loop == 2){address_connectx = network.network_list.get(network.peerid2).toString();}}catch(Exception e){break;}
					try{if(loop == 3){address_connectx = network.network_list.get(network.peerid4).toString();}}catch(Exception e){break;}
		
					if(address_connectx.length() < 10){break;}

					factory = tor.getSocketFactory();
   					socket = factory.createSocket(address_connectx, 80);
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


			}catch(Exception e){e.printStackTrace(); System.out.println("Cannot find node!"); JsonSentence = "not active!"; network.tor_active = 0; network.tor_in_use = 0; }


		}//if

		network.tor_in_use = 0;
		return JsonSentence;

	}//***********************************************************************************************




















	public String request_block_update(){//*****************************************************************


		String jsonText = new String("");

		SocketFactory factory;
		Socket socket;


		try{

			JSONObject obj = new JSONObject();
			obj.put("request","block_update");
			obj.put("block_id", network.last_block_mining_idx);

			StringWriter out = new StringWriter();
			obj.writeJSONString(out);
			jsonText = out.toString();
			System.out.println(jsonText);

		}catch(Exception e){System.out.println("JSON ERROR");}


		String sentence = new String("");
		String modifiedSentence = new String("");  
		String JsonSentence = new String(""); 


		try{

			System.out.println("address: " + client_port_connect);
			System.out.println("address: " + client_address_connect);

			System.out.println("socket");

			factory = tor.getSocketFactory();
   			socket = factory.createSocket(client_address_connect, 80);
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
  

						String update_token[] = new String[network.listing_size];
						String mining_token[] = new String[network.miningx_size];

    					for (int loop = 0; loop < network.listing_size; loop++){//************

							update_token[loop] = (String) jsonObject2.get("l" + Integer.toString(loop));
							System.out.println("convert " + update_token[loop]);

						}//*******************************************************************

    					for (int loop = 0; loop < mining_token.length; loop++){//************

							mining_token[loop] = (String) jsonObject2.get("m" + Integer.toString(loop));
							System.out.println("convert " + mining_token[loop]);

						}//******************************************************************



					int test_db = 0;
					while(network.database_in_use == 1){

    					System.out.println("Database in use...04");
						try{Thread.sleep(1000);} catch (InterruptedException e){}
						test_db++;
						if(test_db > 20){break;}

    				}//*********************************

        			//get the last token
        			krypton_database_get_token getxt = new krypton_database_get_token();
        			String req_id = update_token[0];
        			String old_token[] = new String[network.listing_size];
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

		


		}catch(Exception e){e.printStackTrace(); System.out.println("Cannot find node!"); JsonSentence = "not active!"; network.tor_active = 0; }


		return JsonSentence;

	}//***********************************************************************************************















	public String request_blocks_x_update(){//*****************************************************************



		String jsonText = new String("");

		SocketFactory factory;
		Socket socket;



		try{

			JSONObject obj = new JSONObject();
			obj.put("request","blocks_x_update");
			obj.put("block_id", network.last_block_mining_idx);

			StringWriter out = new StringWriter();
			obj.writeJSONString(out);
			jsonText = out.toString();
			System.out.println(jsonText);

		}catch(Exception e){System.out.println("JSON ERROR");}


		String sentence = new String("");
		String modifiedSentence = new String("");  
		String JsonSentence = new String(""); 


		try{

			System.out.println("address: " + client_port_connect);
			System.out.println("address: " + client_address_connect);

			System.out.println("socket");

			factory = tor.getSocketFactory();
   			socket = factory.createSocket(client_address_connect, 80);
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



    		for (int loop = 0; loop < network.package_block_size; loop++){//***********************************************************************************

				String bufferp = (String) jsonObjectxl.get(Integer.toString(loop));
				System.out.println("break up " + bufferp);
				System.out.println("loop " + loop);
			
				if(response.equals("1") && bufferp != null){

					JsonSentence = "active";

					Object obj2 = parser.parse(bufferp);
					JSONObject jsonObject2 = (JSONObject) obj2;
  


					String mining_token[] = new String[network.miningx_size];
					String update_token[] = new String[network.listing_size];

    					for (int loopx = 0; loopx < network.listing_size; loopx++){//************

							update_token[loopx] = (String) jsonObject2.get("l" + Integer.toString(loopx));
							System.out.println("convert " + update_token[loopx]);

						}//*******************************************************************

    					for (int loopx = 0; loopx < mining_token.length; loopx++){//************

							mining_token[loopx] = (String) jsonObject2.get("m" + Integer.toString(loopx));
							System.out.println("convert " + mining_token[loopx]);

						}//******************************************************************



					int test_db = 0;
					while(network.database_in_use == 1){

    					System.out.println("Database in use...cx");
						try{Thread.sleep(1000);} catch (InterruptedException e){}
						test_db++;
						if(test_db > 20){break;}

    				}//*********************************

        			//get the last token
        			krypton_database_get_token getxt = new krypton_database_get_token();
        			String req_id = update_token[0];
        			String old_token[] = new String[network.listing_size];
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


		}catch(Exception e){ e.printStackTrace(); System.out.println("Cannot find node!"); JsonSentence = "not active!"; network.tor_active = 0; }


		return JsonSentence;

	}//***********************************************************************************************

















	public String request_unconfirmed_block_update(){//*****************************************************************


		//called from request_status cannot test
		//network.tor_in_use = 1;

		String jsonText = new String("");

		SocketFactory factory;
		Socket socket;



		try{

			JSONObject obj = new JSONObject();
			obj.put("request","unconfirmed_block_update");
			obj.put("unconfirmed_id", network.last_unconfirmed_idx);

			StringWriter out = new StringWriter();
			obj.writeJSONString(out);
			jsonText = out.toString();
			System.out.println(jsonText);

		}catch(Exception e){System.out.println("JSON ERROR");}


		String sentence = new String("");
		String modifiedSentence = new String("");  
		String JsonSentence = new String(""); 


		try{

			System.out.println("address: " + client_port_connect);
			System.out.println("address: " + client_address_connect);

			System.out.println("socket");

			factory = tor.getSocketFactory();
   			socket = factory.createSocket(client_address_connect, 80);
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
  
						String update_token[] = new String[network.listing_size];

    					for (int loop = 0; loop < network.listing_size; loop++){//************

							update_token[loop] = (String) jsonObject2.get(Integer.toString(loop));
							System.out.println("convert " + update_token[loop]);

						}//*******************************************************************


            				int test_db = 0;
							while(network.database_in_use == 1){

    							System.out.println("Database in use...rruu");
								try{Thread.sleep(1000);} catch (InterruptedException e){}
								test_db++;
								if(test_db > 20){break;}

    						}//*********************************


        				//get the last token
        				krypton_database_get_token getxt = new krypton_database_get_token();
        				String req_id = update_token[0];
        				String old_token[] = new String[network.listing_size];
        				old_token = getxt.get_token(req_id);

        				krypton_update_token_remote remotexu2 = new krypton_update_token_remote();
						boolean test2 = remotexu2.update(update_token, old_token);

						if(test2){System.out.println("ADDED!"); krypton_database_load reloadx = new krypton_database_load();}

			}//***********************
			else{JsonSentence = "error";}

		


		}catch(Exception e){e.printStackTrace(); System.out.println("Cannot find node!"); JsonSentence = "not active!"; network.tor_active = 0; }

		return JsonSentence;

	}//***********************************************************************************************













	public String update_network_list(){

		//called from request_status cannot test
		//network.tor_in_use = 1;


		String jsonText = new String("");

		SocketFactory factory;
		Socket socket;



		try{

			JSONObject obj = new JSONObject();
			obj.put("request","get_network");

			StringWriter out = new StringWriter();
			obj.writeJSONString(out);
			jsonText = out.toString();
			System.out.println(jsonText);

		}catch(Exception e){System.out.println("JSON ERROR");}


		String sentence = new String("");
		String modifiedSentence = new String("");  
		String JsonSentence = new String(""); 


		try{

			System.out.println("address: " + client_port_connect);
			System.out.println("address: " + client_address_connect);

			System.out.println("socket");

			factory = tor.getSocketFactory();
   			socket = factory.createSocket(client_address_connect, 80);
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

					while(network.database_in_use == 1){

						int test_db = 0;
    					System.out.println("Database in use...03");
						try{Thread.sleep(1000);} catch (InterruptedException e){}
						test_db++;
						if(test_db > 20){break;}

    				}//*********************************

					krypton_database_load_network nodesx = new krypton_database_load_network();

			}//***********************
			else{JsonSentence = "error";}

		


		}catch(Exception e){e.printStackTrace(); System.out.println("Cannot find node!"); JsonSentence = "not active!"; network.tor_active = 0; }

		
		return JsonSentence;

	}//*******************************


















	public void test_for_internet(){


		System.out.println("Testing");


		network.internet_access = 0;

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

				System.out.println(cx0i);
				if(cx0i.length() > 0){network.internet_access = 1;}
				else{network.internet_access = 0;}//network.status9.setText("No internet test!"); network.status9.setIcon(network.imx0);


    		}//************************************
    		wr.close();
   			rd.close();
    		//conn.close();

		}catch(Exception e){

			e.printStackTrace();
			network.internet_access = 0; 
			System.out.println("URL falure."); 
			//network.status9.setText("No internet!"); 
			//network.status9.setIcon(network.imx0);

		}//*****************




	}//updates******************




























	public void initTor() throws Exception {


		network.tor_in_use = 1;
		network.tor_active = 0;
		network.tor_starting = 1;

		test_for_internet();

		while(network.internet_access == 0){

			//network.no_peers_time = 0;

			try{Thread.sleep(10000);} catch (InterruptedException e){}

			test_for_internet();

		}//while


		System.gc();

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

				network.tor_starting = 0;
				network.tor_active = 1;

				network.tor_in_use = 0;
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
