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

Toolkit toolkit;
Timer xtimerx;//class loop.

String client_address_connect = new String("127.0.0.1");
int client_port_connect = 0000;

static long client_loop_time = (long) 0;

int node_timeout = 20000;
static int network_sizex = 0;
static int breakx1 = 0;
static int breakx2 = 0;
static int peer_active_number = 0;

static boolean updating = false;

static boolean peerRestart1 = false;
static boolean peerRestart2 = false;
static boolean peerRestart3 = false;
static boolean peerRestart4 = false;

static String[] peer1sendBufferB1 = null;
static String[] peer1sendBufferB2 = null;

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
	//toolkit = Toolkit.getDefaultToolkit();


	xtimerx = new Timer();
	xtimerx.schedule(new RemindTask_backup(), 0);

	//xtimerx = new Timer();
	//xtimerx.schedule(new RemindTask_peer1(), 0);

	//start the remote peer
	startApplication();

	try{Thread.sleep(60000);} catch(InterruptedException e){e.printStackTrace();}

	//xtimerx = new Timer();
	//xtimerx.schedule(new RemindTask_unconfirmed(), 0);


}//*************************************************************************************










	public void startApplication(){

		try{


  			final String javaBin = System.getProperty("java.home") + File.separator + "bin" + File.separator + "javaw";
  			final File currentJar = new File(network.class.getProtectionDomain().getCodeSource().getLocation().toURI());

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
  			command.add(currentJar.getPath() + "\\Peer.jar");

  			final ProcessBuilder builder = new ProcessBuilder(command);
  			builder.start();

  			//try{Thread.sleep(10000);} catch(InterruptedException e){}


  		}//try
  		catch(Exception e){JOptionPane.showMessageDialog(null, e.getCause());}

	}//******************************












	class RemindTask_backup extends TimerTask{
	Runtime rxrunti = Runtime.getRuntime();

	public void run(){//************************************************************************************

		long thisTick = (long) System.currentTimeMillis();

		//start up the first peer
		//new peer
		peerRestart1 = true;

		while(true){


			thisTick = System.currentTimeMillis();

			//network.no_peers_time++; 
			//System.out.println("network.no_peers_time " + network.no_peers_time);
			//network.status_x1.setText("network.no_peers_time " + network.no_peers_time);


			if(peerRestart1){


				//MyThread mythread = new MyThread();
  				//mythread.start();


				//Thread thread = new Thread(new MyRunnable());
   				//thread.start();


				//on start up thise are not ready
				//try{xtimer_p1.cancel();}catch(Exception e){e.printStackTrace();}
				//try{xtimer_p1.purge();}catch(Exception e){e.printStackTrace();}

				//new peer
				//Timer xtimer_p1 = new Timer();
				//xtimer_p1.schedule(new RemindTask_peer1(), 0);//RemindTask_client1

				peerRestart1 = false;

			}//**************




			try{Thread.sleep(10000);} catch(InterruptedException e){e.printStackTrace();}

			//System.out.println("Loop 32");


		}//while****

		//JOptionPane.showMessageDialog(null, "LOOP EXIT!");

	}//runx*************************************************************************************************
    }//remindtask










    //main update system
	class RemindTask_peer1 extends TimerTask{
	Runtime rxrunti = Runtime.getRuntime();

	public void run(){//************************************************************************************

		//start up the first peer 

		while(true){


			System.out.println("Get status...");



			String jsonText = new String("");


			try{

				JSONObject obj = new JSONObject();
				obj.put("request", "status");
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
				Socket clientSocket = new Socket("localhost", network.peer_port);   
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
	  
	  					String torx = jsonObject2.get("tor_active").toString();
	  					network.tor_active = Integer.parseInt(torx);

	  					String connectionx = jsonObject2.get("connection_active").toString();
	  					network.connection_active = Integer.parseInt(torx);

	  					String no_peers_timex = jsonObject2.get("no_peers_time").toString();
	  					network.no_peers_time = Integer.parseInt(no_peers_timex);

						String blocks_uptodatex = jsonObject2.get("blocks_uptodate").toString();
	  					//if(blocks_uptodatex.equals("true")){krypton_net_client.blocks_uptodate = true;}
	  					//else{krypton_net_client.blocks_uptodate = false;}

	  					System.out.println("torx " + torx);
	  					System.out.println("connectionx " + connectionx);
	  					System.out.println("no_peers_time " + no_peers_timex);


			}catch(Exception e){

				e.printStackTrace(); 
				System.out.println("PEER API SERVER OFFLINE!"); 
				modifiedSentence = "PEER API SERVER OFFLINE!"; 
				network.programst = "PEER API SERVER OFFLINE!";

			}//*****************


			try{Thread.sleep(5000);} catch(InterruptedException e){e.printStackTrace();}

		}//while

	}//runx*************************************************************************************************
    }//remindtask








    public static String send_new_block_update(String[] mining_id, String[] token_id){//*********************************************************

    	System.out.println("SEND NEW BLOCK UPDATE X");

    	network.buffered_mining_block = new String("");
		String jsonText = new String("");

		String successx = new String("1");


		try{

			JSONObject objM = new JSONObject();
			JSONObject objL = new JSONObject();

			String jsonTextM = new String("");
			String jsonTextL = new String("");

    		for (int loop = 0; loop < mining_id.length; loop++){//************

				objM.put(loop, mining_id[loop]);

			}//***************************************************************

			StringWriter outM = new StringWriter();
			objM.writeJSONString(outM);
			jsonTextM = outM.toString();
			network.buffered_mining_block = jsonTextM;


    		for (int loop = 0; loop < token_id.length; loop++){//************

				objL.put(loop, token_id[loop]);

			}//**************************************************************

			StringWriter outL = new StringWriter();
			objL.writeJSONString(outL);
			jsonTextL = outL.toString();
			network.buffered_listing_block = jsonTextL;

			network.mining_block_ready = 1;

		}catch(Exception e){e.printStackTrace(); System.out.println("JSON ERROR"); successx = "0"; network.mining_block_ready = 0;}


		return successx;

    }//*******************************************************************************************************************************************










	class RemindTask_unconfirmed extends TimerTask{
	Runtime rxrunti = Runtime.getRuntime();

	public void run(){//************************************************************************************


		while(true){


			if(network.send_buffer_size > 0){


		    	System.out.println("SEND NEW UNCONFIRMED UPDATE X");

				String jsonText = new String("");
				String jsonTextL = new String("");


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
					jsonTextL = outL.toString();


					JSONObject obj = new JSONObject();
					obj.put("request", "send_new_unconfirmed_block");
					obj.put("password", "1234");
					obj.put("item_array", jsonTextL);

					StringWriter out = new StringWriter();
					obj.writeJSONString(out);
					jsonText = out.toString();
					//System.out.println(jsonText);

				}catch(Exception e){e.printStackTrace(); System.out.println("JSON ERROR");}



				String sentence;   
				String modifiedSentence = new String();   

				try{

					BufferedReader inFromUser = new BufferedReader( new InputStreamReader(System.in) );
					//System.out.println(">>> " + "localhost" + " " + "55556");
					Socket clientSocket = new Socket("localhost", network.peer_port);   
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

					if(message.equals("1")){

						System.out.println("Send unconfirmed update pause...");

						//wait for the peer to send the update and respond back to the api
						//if not then send again to the new peer
						try{Thread.sleep(100000);} catch(InterruptedException e){e.printStackTrace();}

					}//*********************


				}catch(Exception e){

					e.printStackTrace(); 
					System.out.println("API SERVER OFFLINE!"); 
					modifiedSentence = "API SERVER OFFLINE!"; 

				}//*****************


			}//if

			//wait to try again
			try{Thread.sleep(10000);} catch(InterruptedException e){e.printStackTrace();}

		}//while

	}//runx*************************************************************************************************
    }//remindtask









}//last
