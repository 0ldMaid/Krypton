import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;  
import java.lang.Object.*;  
import java.net.*;
import java.util.Random;
import java.util.*;

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

static String package_listings[][] = null;
static String package_miningxs[][] = null;

long threadId;



krypton_net_client(){//*****************************************************************


	network_sizex = network.network_size;
	System.out.println("network_sizex " + network_sizex);

	krypton_net_client.client_loop_time = System.currentTimeMillis();


	xtimerx = new Timer();
	xtimerx.schedule(new RemindTask_backup(), 0);

	//start the remote peer
	startApplication();



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
  			command.add(System.getProperty("user.dir") + File.separator + "Peer.jar");
  			command.add("apiPort:" + network.api_port);

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

			network.no_peers_time++; 
			System.out.println("network.no_peers_time " + network.no_peers_time);
			//network.status_x1.setText("network.no_peers_time " + network.no_peers_time);

			if(network.no_peers_time > 20){network.programst = "Peer may be offline!";}

			try{Thread.sleep(10000);} catch(InterruptedException e){e.printStackTrace();}

			//System.out.println("Loop 32");


		}//while****

		//JOptionPane.showMessageDialog(null, "LOOP EXIT!");

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










    public static String send_new_block_package(String[][] mining_id, String[][] token_id){//*********************************************************

    	System.out.println("SEND NEW BLOCK PACKAGE UPDATE X");

    	network.buffered_mining_block = new String("");

		LinkedList<String> list = new LinkedList<String>();
		String successx = new String("1");

		try{


			for(int loop0 = 0; loop0 < mining_id[0].length; loop0++){//*******************************************************************

				String jsonText = new String("");
				int xxp1 = 0;
				int xxp2 = 0;

				
				JSONObject obj = new JSONObject();

	    		for(int loop = 0; loop < mining_id.length; loop++){//*************

					obj.put("m" + Integer.toString(xxp1), mining_id[loop][loop0]);
					xxp1++;

				}//***************************************************************

	    		for(int loop = 0; loop < token_id.length; loop++){//*************

					obj.put("l" + Integer.toString(xxp2), token_id[loop][loop0]);
					xxp2++;

				}//**************************************************************

				StringWriter out = new StringWriter();
				obj.writeJSONString(out);
				jsonText = out.toString();
				
				list.add(jsonText); 


			}//****************************************************************************************************************************

			System.out.println(JSONValue.toJSONString(list));
			network.buffered_package_block = JSONValue.toJSONString(list);
			network.mining_package_ready = 1;

			//JOptionPane.showMessageDialog(null, "Package DONE!");

		}catch(Exception e){e.printStackTrace(); System.out.println("JSON ERROR"); successx = "0"; network.mining_package_ready = 0;}


		return successx;

    }//*******************************************************************************************************************************************










}//last
