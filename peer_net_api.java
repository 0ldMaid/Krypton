import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;  
import java.lang.Object.*;  
import java.util.EmptyStackException;
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
import java.io.UnsupportedEncodingException;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONValue;

import java.net.HttpURLConnection;
import javax.net.SocketFactory;



public class peer_net_api{


static String client_address_connect = new String("foomfoumyoi37qly.onion");
static int client_port_connect = 55555;
static int api_port = 55556;
static int peer_port = 55544;

long threadId;
static long client_loop_time = (long) 0;
static Long blocktimesx = (long) 0;
static Long difficultyx = (long) 0;
static Long last_block_time = (long) 0;

static int node_timeout = 20000;
static int network_sizex = 0;
static int breakx1 = 0;
static int breakx2 = 0;
static int peer_active_number = 0;
static int no_peers_time = 0;
static int peer_idx = 0;
//static int peersx0 = 0;//active or not
//static int peerid0 = 0;//the id of the peer if we are using a network list
static int stop_mining = 0;
static int mining_status = 0;
static int mining_block_ready = 0;
static int mining_package_ready = 0;
static int tor_in_use = 0;
static int api_in_use = 0;
static int tor_active = 0;
static int connection_active = 0;
static int tor_starting = 0;
static int listing_size = 69;//listing token sections
static int miningx_size = 10;//mining token sections
static int database_in_use = 0;
static int internet_access = 0;
static int network_size = 0;
static int open_network = 0;
static int send_buffer_size = 0;
static int package_block_size = 0;
static int database_unconfirmed_total = 0;
static int restart_attempts = 0;

static boolean updating = false;
static boolean blocks_uptodate = false;

static boolean peerRestart1 = false;
static boolean peerRestart2 = false;
static boolean peerRestart3 = false;
static boolean peerRestart4 = false;

static String[] peer1sendBufferB1 = null;
static String[] peer1sendBufferB2 = null;

static String[] peer1sendUnconfiremdB1 = null;

static String peer1sendPackageB1 = null;

static String new_hash1 = new String("");
static String new_hash2 = new String("");
static String new_hash3 = new String("");
static String new_hash4 = new String("");

static String versionx = new String("1");
static String last_block_mining_idx = new String("");
static String last_remote_mining_idx = new String("");
static String last_block_timestamp = new String("");
static String last_unconfirmed_idx = new String("");
static String programss = new String("");
static String last_block = new String("");
static String last_block_id = new String("");

String statex = new String("0");  





peer_net_api(){//*****************************************************************

	//save time
	client_loop_time = System.currentTimeMillis();

	//the backup system

	Timer xtimerx = new Timer();
	xtimerx.schedule(new RemindTask_backup(), 0);

	//new API
	//Timer xtimer_api = new Timer();
	//xtimer_api.schedule(new RemindTask_api(), 0);//RemindTask_client1

	//new peer
	Timer xtimer_p1 = new Timer();
	xtimer_p1.schedule(new RemindTask_peer1(), 0);//RemindTask_client1


}//*************************************************************************************







	class RemindTask_backup extends TimerTask{
	Runtime rxrunti = Runtime.getRuntime();

	public void run(){//************************************************************************************

		long thisTick = (long) System.currentTimeMillis();

		while(true){


			thisTick = System.currentTimeMillis();

			if(api_in_use == 0){no_peers_time++;}
			System.out.println("no_peers_time ID " + peer_idx + " " + no_peers_time + " active(" + peer_active_number + ")");


			try{

				if(api_in_use == 0){request_status();}

			}catch(Exception e){e.printStackTrace();}

			if(no_peers_time > 25){break;}
			if(peerRestart1){break;}
			if(restart_attempts > 3){System.exit(0);}

			try{Thread.sleep(10000);} catch(InterruptedException e){e.printStackTrace();}
		

		}//while****

		tor_active = 0;
		tor_starting = 0;
		connection_active = 0; 


		//JOptionPane.showMessageDialog(null, "LOOP EXIT!");
		restartApplication();

		System.exit(0);

	}//runx*************************************************************************************************
    }//remindtask








	public void restartApplication(){

		try{


  			final String javaBin = System.getProperty("java.home") + File.separator + "bin" + File.separator + "javaw";
  			final File currentJar = new File(peer_net_api.class.getProtectionDomain().getCodeSource().getLocation().toURI());

  			System.out.println("javaBin " + javaBin);
  			System.out.println("currentJar " + currentJar);
  			System.out.println("currentJar.getPath() " + currentJar.getPath());

  			/* is it a jar file? */
  			//if(!currentJar.getName().endsWith(".jar")){return;}

  			/* Build command: java -jar application.jar */
  			final ArrayList<String> command = new ArrayList<String>();
  			command.add(javaBin);
  			command.add("-jar");
  			command.add("-Xms256m");
  			command.add("-Xmx512m");
  			command.add(currentJar.getPath());
  			command.add("apiPort:" + api_port);

  			final ProcessBuilder builder = new ProcessBuilder(command);
  			builder.start();

  			//try{Thread.sleep(10000);} catch(InterruptedException e){}


  		}//try
  		catch(Exception e){JOptionPane.showMessageDialog(null, e.getCause());}

	}//******************************










    //main update system
	class RemindTask_peer1 extends TimerTask{
	Runtime rxrunti = Runtime.getRuntime();

	public void run(){//************************************************************************************

		//start up the first peer 

		peer_net_client peer1 = new peer_net_client();
		String peer1_run = peer1.peer_start(1);

		peer1 = null;

		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

		//System.gc();

	}//runx*************************************************************************************************
    }//remindtask











	public void request_status(){//*****************************************************************



		String jsonText = new String("");


		try{

			JSONObject obj = new JSONObject();
			obj.put("request", "status");
			obj.put("password", "1234");
			obj.put("tor_status", tor_active);
			obj.put("connection_active", connection_active);
			obj.put("blocks_uptodate", String.valueOf(blocks_uptodate));

			StringWriter out = new StringWriter();
			obj.writeJSONString(out);
			jsonText = out.toString();
			//System.out.println(jsonText);

		}catch(Exception e){System.out.println("JSON ERROR");}



		String sentence;   
		String modifiedSentence = new String();   

		try{

			BufferedReader inFromUser = new BufferedReader( new InputStreamReader(System.in) );
			//System.out.println(">>> " + "localhost" + " " + "55556");
			Socket clientSocket = new Socket("localhost", api_port);   
			DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
			BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));    
			sentence = jsonText;  
			outToServer.writeBytes(sentence + '\n');   
			modifiedSentence = inFromServer.readLine();   
			//System.out.println("FROM SERVER: " + modifiedSentence);
			clientSocket.close();


				JSONParser parser = new JSONParser();

				Object obj = parser.parse(modifiedSentence);
				JSONObject jsonObject = (JSONObject) obj;
  
				String message = (String) jsonObject.get("message");

				//System.out.println(message);


					Object obj2 = parser.parse(message);
					JSONObject jsonObject2 = (JSONObject) obj2;
  
  					versionx = jsonObject2.get("version").toString();
					difficultyx = Long.parseLong(jsonObject2.get("difficulty").toString());
					send_buffer_size = (Integer) Integer.parseInt(jsonObject2.get("buffer_size").toString());
					blocktimesx = Long.parseLong(jsonObject2.get("blocktimesx").toString());
					last_block_time = Long.parseLong(jsonObject2.get("last_block_time").toString());
					last_block = jsonObject2.get("last_block").toString();
					database_unconfirmed_total = Integer.parseInt(jsonObject2.get("database_unconfirmed_total").toString());
					mining_status = (Integer) Integer.parseInt(jsonObject2.get("mining_status").toString());
					programss = jsonObject2.get("program_status").toString();
					last_block_timestamp = jsonObject2.get("last_block_timestamp").toString();
					last_block_mining_idx = jsonObject2.get("last_mining_id").toString();
					last_remote_mining_idx = jsonObject2.get("last_remote_mining_idx").toString();
					last_unconfirmed_idx = jsonObject2.get("last_unconfirmed_idx").toString();
					client_address_connect = jsonObject2.get("peer_number1").toString();
					mining_block_ready = (Integer) Integer.parseInt(jsonObject2.get("mining_block_ready").toString());
					mining_package_ready = (Integer) Integer.parseInt(jsonObject2.get("mining_package_ready").toString());

					if(send_buffer_size > 0 && peer1sendUnconfiremdB1 == null){

						get_buffer_unconfirmed();

					}//********************************************************

					if(mining_block_ready == 1 && peer_net_api.peer1sendBufferB1 == null){

						//JOptionPane.showMessageDialog(null, "mining_block_ready " + mining_block_ready + "\n" + "peer_net_api.peer1sendBufferB1 " + peer_net_api.peer1sendBufferB1);
						get_new_block();

					}//*******************************************************************

					if(mining_package_ready == 1 && peer1sendPackageB1 == null){

						get_new_package();

					}//********************************************************

					restart_attempts = 0;

		}catch(Exception e){

			e.printStackTrace(); 
			System.out.println("API SERVER OFFLINE!"); 
			modifiedSentence = "API SERVER OFFLINE!";
			//JOptionPane.showMessageDialog(null, "Nothing to connect to: (" + api_port + ")");
			restart_attempts++;
			System.exit(0);

		}//*****************

	}//*********************************************************************************************










	public void get_buffer_unconfirmed(){


		String jsonText = new String("");


		try{

			JSONObject obj = new JSONObject();
			obj.put("request", "get_buffer_unconfirmed");
			obj.put("password", "1234");

			StringWriter out = new StringWriter();
			obj.writeJSONString(out);
			jsonText = out.toString();
			//System.out.println(jsonText);

		}catch(Exception e){System.out.println("JSON ERROR");}



		String sentence;   
		String modifiedSentence = new String();   

		try{

			BufferedReader inFromUser = new BufferedReader( new InputStreamReader(System.in) );
			//System.out.println(">>> " + "localhost" + " " + "55556");
			Socket clientSocket = new Socket("localhost", api_port);   
			DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
			BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));    
			sentence = jsonText;  
			outToServer.writeBytes(sentence + '\n');   
			modifiedSentence = inFromServer.readLine();   
			//System.out.println("FROM SERVER: " + modifiedSentence);
			clientSocket.close();


				JSONParser parser = new JSONParser();

				Object obj = parser.parse(modifiedSentence);
				JSONObject jsonObject = (JSONObject) obj;
  
				String message = (String) jsonObject.get("message");

				//System.out.println(message);

    			send_new_unconfirmed_block(message);


		}catch(Exception e){

			e.printStackTrace(); 
			System.out.println("API SERVER OFFLINE!"); 
			modifiedSentence = "API SERVER OFFLINE!"; 

		}//*****************


	}//**********************************










	public void get_new_block(){

		String jsonText = new String("");


		try{

			JSONObject obj = new JSONObject();
			obj.put("request", "get_buffered_block");
			obj.put("password", "1234");

			StringWriter out = new StringWriter();
			obj.writeJSONString(out);
			jsonText = out.toString();
			//System.out.println(jsonText);

		}catch(Exception e){System.out.println("JSON ERROR");}



		String sentence;   
		String modifiedSentence = new String();   

		try{

			BufferedReader inFromUser = new BufferedReader( new InputStreamReader(System.in) );
			//System.out.println(">>> " + "localhost" + " " + "55556");
			Socket clientSocket = new Socket("localhost", api_port);   
			DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
			BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));    
			sentence = jsonText;  
			outToServer.writeBytes(sentence + '\n');   
			modifiedSentence = inFromServer.readLine();   
			//System.out.println("FROM SERVER: " + modifiedSentence);
			clientSocket.close();


				JSONParser parser = new JSONParser();

				Object obj = parser.parse(modifiedSentence);
				JSONObject jsonObject = (JSONObject) obj;
  
				String message = (String) jsonObject.get("message");

				//System.out.println(message);


					Object obj2 = parser.parse(message);
					JSONObject jsonObject2 = (JSONObject) obj2;

  					String miningx = jsonObject2.get("new_miningx_id").toString();
    				String listing = jsonObject2.get("new_listing_id").toString();

    				send_new_block(miningx,listing);


		}catch(Exception e){

			e.printStackTrace(); 
			System.out.println("API SERVER OFFLINE!"); 
			modifiedSentence = "API SERVER OFFLINE!"; 

		}//*****************




	}//get block









	public void get_new_package(){

		String jsonText = new String("");


		try{

			JSONObject obj = new JSONObject();
			obj.put("request", "get_buffered_package");
			obj.put("password", "1234");

			StringWriter out = new StringWriter();
			obj.writeJSONString(out);
			jsonText = out.toString();
			//System.out.println(jsonText);

		}catch(Exception e){System.out.println("JSON ERROR");}



		String sentence;   
		String modifiedSentence = new String();   

		try{

			BufferedReader inFromUser = new BufferedReader( new InputStreamReader(System.in) );
			//System.out.println(">>> " + "localhost" + " " + "55556");
			Socket clientSocket = new Socket("localhost", api_port);   
			DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
			BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));    
			sentence = jsonText;  
			outToServer.writeBytes(sentence + '\n');   
			modifiedSentence = inFromServer.readLine();   
			//System.out.println("FROM SERVER: " + modifiedSentence);
			clientSocket.close();


				JSONParser parser = new JSONParser();

				Object obj = parser.parse(modifiedSentence);
				JSONObject jsonObject = (JSONObject) obj;
  
				String message = (String) jsonObject.get("message");

				peer1sendPackageB1 = message;
				//System.out.println(message);

				//JOptionPane.showMessageDialog(null, peer1sendPackageB1.length());


		}catch(Exception e){

			e.printStackTrace(); 
			System.out.println("API SERVER OFFLINE!"); 
			modifiedSentence = "API SERVER OFFLINE!"; 

		}//*****************




	}//get block








    public String send_new_block(String mining_array, String item_array){

		String jsontext = new String("1");

		try{

			String[] update_tokenM = new String[miningx_size];
			String[] update_tokenL = new String[listing_size];

	    	try{


				JSONParser parserx = new JSONParser();
				Object objx = parserx.parse(mining_array);
				JSONObject jsonObjectx = (JSONObject) objx;
	  

	    		for (int loop = 0; loop < miningx_size; loop++){//************

					update_tokenM[loop] = (String) jsonObjectx.get(Integer.toString(loop));
					System.out.println("import M " + update_tokenM[loop]);

				}//**********************************************************


			}catch(Exception e){e.printStackTrace(); statex = "0"; jsontext = "error";}


	    	try{


				JSONParser parserx = new JSONParser();
				Object objx = parserx.parse(item_array);
				JSONObject jsonObjectx = (JSONObject) objx;
	  

	    		for (int loop = 0; loop < listing_size; loop++){//************

					update_tokenL[loop] = (String) jsonObjectx.get(Integer.toString(loop));
					System.out.println("import L " + update_tokenL[loop]);

				}//***********************************************************


			}catch(Exception e){e.printStackTrace(); statex = "0"; jsontext = "error";}


			peer1sendBufferB1 = update_tokenM;
			peer1sendBufferB2 = update_tokenL;

		}catch(Exception e){jsontext = "error";}

		return jsontext;

    }//***************************************











    //main update system
	class RemindTask_api extends TimerTask{
	Runtime rxrunti = Runtime.getRuntime();

	public void run(){//************************************************************************************


		System.out.println("Start API");


		String jsonText2 = new String("");

		JSONObject obj_out = new JSONObject();

		ServerSocket welcomeSocket;
		JSONObject jsonObject;

		String jsonText = new String("");
		String responsex = new String("");
		String clientSentence;          
		String capitalizedSentence;   
 

		while(true){   


			try{//*********************************************************     

				welcomeSocket = new ServerSocket(api_port, 0, InetAddress.getByName("localhost"));
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
					String mining_array = new String("");
					String old_key = new String("");
					String node = new String("");
					String send_to = new String("");
					try{item_id = (String) jsonObject.get("item_id").toString();} catch(Exception e){System.out.println("extra info no item_id...");}
					try{item_array = (String) jsonObject.get("item_array").toString();} catch(Exception e){System.out.println("extra info no item_array...");}
					try{mining_array = (String) jsonObject.get("mining_array").toString();} catch(Exception e){System.out.println("extra info no mining_array...");}
					try{send_to = (String) jsonObject.get("send_to").toString();} catch(Exception e){System.out.println("extra info no send to address...");}


						if(request.equals("status")){statex = "1"; responsex = get_status();}//***************************

						else if(request.equals("send_new_unconfirmed_block")){statex = "1"; responsex = send_new_unconfirmed_block(item_array);}//updating something

						else if(request.equals("send_new_block")){statex = "1"; responsex = send_new_block(mining_array,item_array);}//found new block from mining

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

			}catch(Exception e){e.printStackTrace(); System.out.println("Server ERROR x3");}





	         
		}//**********while





	}//runx*************************************************************************************************
    }//remindtask










    public String get_status(){

		String jsontext = new String("");

    	try{


			JSONObject obj = new JSONObject();

			obj.put("version", versionx);
			obj.put("status", "active");
			obj.put("tor_active", Integer.toString(tor_active));
			obj.put("no_peers_time", Integer.toString(no_peers_time));
			obj.put("connection_active", Integer.toString(connection_active));
			obj.put("blocks_uptodate", String.valueOf(blocks_uptodate));

			StringWriter out = new StringWriter();
			obj.writeJSONString(out);
			jsontext = out.toString();
			//System.out.println(jsonText);

		}catch(Exception e){e.printStackTrace(); statex = "0"; jsontext = "error";}


		return jsontext;

    }//***************************************















    public String send_new_unconfirmed_block(String item_array){


    	System.out.println("Decode unconfirmed block...");

		String jsontext = new String("1");

		try{

			String[] update_tokenL = new String[listing_size];

	    	try{


				JSONParser parserx = new JSONParser();
				Object objx = parserx.parse(item_array);
				JSONObject jsonObjectx = (JSONObject) objx;
	  

	    		for (int loop = 0; loop < listing_size; loop++){//************

					update_tokenL[loop] = (String) jsonObjectx.get(Integer.toString(loop));
					System.out.println("import L " + update_tokenL[loop]);

				}//***********************************************************


			}catch(Exception e){e.printStackTrace(); statex = "0"; jsontext = "error";}


			peer1sendUnconfiremdB1 = update_tokenL;

		}catch(Exception e){e.printStackTrace(); jsontext = "error";}

		return jsontext;

    }//*********************************************************









//start the program.
    public static void main(String[] args) {

    	//get server port
		for (int loop = 0; loop < args.length; loop++){//************

			try{

				if(args[loop].contains("apiPort:")){api_port = Integer.parseInt(args[loop].substring(8,args[loop].length()));}

			}catch(Exception e){e.printStackTrace();}

		}//**********************************************************
		System.out.println("api_port: " + api_port);

		peer_net_api apix = new peer_net_api();

    }//main



}//last
