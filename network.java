import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;  
import java.lang.Object.*;  
import java.net.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.Toolkit;
import java.util.Timer;
import java.util.TimerTask;

//import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
import java.sql.Statement;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONValue;

import java.util.ArrayList;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.imageio.*;
import java.awt.image.*;

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

import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.spec.RSAPrivateCrtKeySpec;

import com.subgraph.orchid.circuits.hs.HSDescriptorCookie;
import com.subgraph.orchid.config.TorConfigBridgeLine;
import com.subgraph.orchid.data.HexDigest;
import com.subgraph.orchid.data.IPv4Address;
import com.subgraph.orchid.encoders.Hex;
import com.subgraph.orchid.*;

import java.util.List;

import java.awt.datatransfer.*;




public class network{

//static TorClient tor = new TorClient();

Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize();
int xzx = 700;
int xzy = 600;
int cenx = (scrSize.width / 2) - (xzx / 2);
int ceny = (scrSize.height / 2) - (xzy / 2);

Timer xtimerx;//class loop.
Toolkit toolkit;

FlowLayout flow0 = new FlowLayout(0);
static Font f_00 = new Font("Arial", Font.PLAIN, 10);
static Font f_01 = new Font("Arial", Font.PLAIN, 12);
static Font f_02 = new Font("Arial", Font.PLAIN, 18);

static TrayIcon icon;

Process p;
krypton_database_driver systemx;

static boolean startm_yn = true;

static String programst = new String("START");

static List<String> network_list = new ArrayList<String>();//the list of your peers
static List<String> my_listings = new ArrayList<String>();//get my listing ids
static String settingsx[] = new String[23];//settings database
static String item_layout[];//get a few blocks to test
static String block_difficulty_listx[];//get a few blocks to test
static String block_date_listx[];//get a few blocks to test
static String last_block_ql[];//get a few blocks to test
static String html_block_ql[];//quickload for html pages


static String base58_id = new String("");//your base 58 key

static String versionx = new String("1.2.3");
static String idx = new String("");
static String xtypex = new String("user");
static String last_block_timestamp = new String("");
static String last_block_id = new String("");
static String last_block_idx = new String("");
static String last_block_mining_idx = new String("");
static String prev_block_mining_idx = new String("");
static String last_unconfirmed_idx = new String("");
static String ltc_mining_speed = new String("Development Mining");


static Long lastFrame;
static Long thisTick;
static Long seconds;
static Long loaddbx_longstamp = (long) 0;
static Long dbxadd_longstamp = (long) 0;
static Long dbxmine_longstamp = (long) 0;
static Long last_block_longstamp1 = (long) 0;
static Long last_block_longstamp2 = (long) 0;
static Long starttime = (long) 100;
static long difficultyx = (long) 24000000000000000.00;//mining difficulty
static long difficultyx_limit = (long) 44000000000000000.00;//mining difficulty limite
static long blocktimesx = (long) 60;//time between blocks
static long last_block_time = (long) 0;//time since last block
static long time_block_added = (long) 0;//time since last block was added to THIS database


static int new_database_start = 0;//build a new listings database
static int base_int = 100000;//base id
static int open_network = 0;//allow new nodes without confirmation
static int network_chain = 100;//number of nodes in network chain
static int network_size = 0;//number of nodes in network chain
static int network_up_to_date = 0;
static int send_requests = 0;
static int inbox_requests = 0;
static int get_requests = 0;
static int p2p_port = 55555;
static int api_port = 55556;
static int peers = 0;
static int nodex_number = 101;
static int database_active = 0;
static int database_messages = 0;
static int database_listings_owner = 0;
static int database_listings_total = 0;
static int database_listings_for_edit = 0;
static int database_unconfirmed_total = 0;
static int peersx0 = 0;
static int peersx1 = 0;
static int peersx2 = 0;
static int peersx3 = 0;
static int peersx4 = 0;
static int peerid0 = -1;
static int peerid1 = -1;
static int peerid2 = -1;
static int peerid3 = -1;
static int peerid4 = -1;
static int listing_size = 69;//listing token sections
static int miningx_size = 9;//listing token sections
static int tor_active = 0;//is TOR working?
static int tor_starting = 0;//is TOR working?
static int internet_access = 0;//is the standard internet on?
static int client = 1;//is the client on or off
static int server = 1;//is the server on or off
static int xmining = 1;//is mining on or off
static int mining_status = 0;//is the program mining or not
static int mining_speed = 1;//time between hashes
static int blocks_verified = 0;//is mining on or off
static int block_difficulty_reset = 100;//how many blocks before reset
static int block_difficulty_test = 0;//test for enough blocks to build test > difficulty reset...
static int hard_token_limit = 25000;//total number of tokens to allow
static int target_block_speed = 300000;//target speed 300 seconds
static int target_block_adjustment = 1;//percent
static int confirm_before_delete = 25000;//how many confirmations before we delete the old blocks
static int database_in_use = 0;//already working
static int tor_in_use = 0;//already working
static int no_peers_time = 0;//how long since last peer connection
static int package_block_size = 20;//how many blocks to send per package
static int send_buffer_size = 0;//how many blocks are in the send buffer
int ix0 = 0;
int ix1 = 0;



static Color st_gray1 = new Color(0.8f, 0.8f, 0.8f);//light gray for sites
static Color st_gray2 = new Color(0.99f, 0.99f, 0.99f);//darker gray for sites
static Color xstripesc = new Color(0.0f, 0.0f, 0.0f);
static Color jblue = new Color(0.78f, 0.86f, 0.94f);
static Color jgray = new Color(0.92f, 0.92f, 0.92f);
static Color xblue = new Color(0.1f, 0.1f, 0.16f);
static Color bluex1 = new Color(0.0f, 0.0f, 0.3f);
static Color bluex2 = new Color(0.1f, 0.17f, 0.39f);
static Color bluex3 = new Color(0.6f, 0.67f, 0.9f);
static Color bluex4 = new Color(0.3f, 0.5f, 0.6f);
static Color fbluex = new Color(0.69f, 0.90f, 0.99f);
static Color darkgray04 = new Color(0.04f, 0.04f, 0.04f);//dark gray
static Color darkgray08 = new Color(0.08f, 0.08f, 0.08f);//dark gray
static Color darkgray01 = new Color(0.156f, 0.156f, 0.156f);//dark gray
static Color darkgray70 = new Color(0.3f, 0.3f, 0.3f);
static Color gray5 = new Color(0.5f, 0.5f, 0.5f);
static Color gray6 = new Color(0.6f, 0.6f, 0.6f);
static Color gray7 = new Color(0.7f, 0.7f, 0.7f);
static Color gray8 = new Color(0.8f, 0.8f, 0.8f);
static Color gray9 = new Color(0.9f, 0.9f, 0.9f);
static Color blackx = new Color(0.0f, 0.0f, 0.0f);
static Color whitex = new Color(1.0f, 1.0f, 1.0f);
static Color purple = new Color(1.0f, 0.0f, 0.8f);
static Color redx = new Color(1.0f, 0.2f, 0.216f);
static Color yellowx = new Color(1.0f, 0.9f, 0.0f);
static Color yellowx2 = new Color(0.8f, 0.7f, 0.0f);
static Color tab_off = gray8;                               //for the tabs
static Color tab_on = whitex;//bluex2                       //for the tabs
static Color lightgreenx = new Color(0.5f, 0.9f, 0.5f);
static Color darkgreenx = new Color(0.1f, 0.3f, 0.1f);
static Color darkgreebnx = new Color(0.1f, 0.3f, 0.1f);
static Color yellow = bluex4;   //the selected item



static Image ximage;

static Icon imx0;
static Icon imx1;
Icon imx2;
Icon imx3;
Icon imx4;









network(){//**************************************************************************

	programst = "loading";

	starttime = System.currentTimeMillis();

	SysTray();
	System.out.println(System.getProperty("sun.arch.data.model"));



	programst = "start api";

	//start the api server
	krypton_api x5 = new krypton_api();



	programst = "start engine";

	//first back up system. set if the first one fails or if the system does not start correctly. 
	toolkit = Toolkit.getDefaultToolkit();
	xtimerx = new Timer();
	xtimerx.schedule(new RemindTask_engine2(), 0);


	programst = "start database";

	//start the database drivers
	systemx = new krypton_database_driver();



	//test database driver
	try{

		PreparedStatement psInsert = null;
		System.out.println("Test JDB");

	}catch(Exception e){System.out.println("JDB ERROR");}


	//test JSON
 	try{

		JSONObject jsonObject = null;
		System.out.println("Test JSON");

	}catch(Exception e){System.out.println("JSON ERROR");}



	programst = "load chain";
	//load the chain
	krypton_database_load_network xxn = new krypton_database_load_network();
	System.out.println("database_active " + database_active);
	krypton_database_load xxs = new krypton_database_load();

	System.out.println("database_active " + database_active);

	if(database_active == 1){

		idx = settingsx[0];

      	System.out.println("Description: " + settingsx[2]);

		open_network = Integer.parseInt(settingsx[6]);
		if(open_network == 1){System.out.println("Network OPEN");}
		else{System.out.println("Network CLOSED");}

	}//***********************
	else{


		krypton_database_build db = new krypton_database_build();
		krypton_database_load xx2 = new krypton_database_load();

		if(database_active == 1){

			System.out.println("Database Status: (" + database_active + ")");

			idx = settingsx[0];

        	System.out.println("test 6 " + settingsx[6]);

			open_network = Integer.parseInt(settingsx[6]);
			if(open_network == 1){System.out.println("Network OPEN");}
			else{System.out.println("Network CLOSED");}

		}//***********************

	}//else


	System.out.println("network size " + network_list.size());


	programst = "start network";
	try{

	  	InetAddress inet = InetAddress.getLocalHost();
	  	InetAddress[] ips = InetAddress.getAllByName(inet.getCanonicalHostName());
	  	if(ips != null){

	    	for(int i = 0; i < ips.length; i++){System.out.println(ips[i]);}

	  	}//*****************

	}catch(UnknownHostException e){}


	programst = "get api key";
	get_base58_key();



	//worm chain
	programst = "verify";
	//test blocks if system is already built.
	if(new_database_start == 0){

		krypton_database_verify_blocks vb = new krypton_database_verify_blocks();
		boolean verified = vb.verify_blocks();

	}//*************************


	programst = "start network";
	toolkit = Toolkit.getDefaultToolkit();
	xtimerx = new Timer();
	xtimerx.schedule(new RemindTask_engine(), 0);


	programst = "start server";
	//server start
	try{

		krypton_net_server server = new krypton_net_server();

	}catch(Exception e){e.printStackTrace();}

	//client start
	if(new_database_start == 0){

		try{

			krypton_net_client client = new krypton_net_client();

		}catch(Exception e){e.printStackTrace();}

	}//if***********************



	programst = "ready";
	mining x4 = new mining();



}//*****************************************************************************











	public void SysTray() {

	try{


	    ximage = ImageIO.read(getClass().getResourceAsStream("images/tray.png"));

      	icon = new TrayIcon(ximage, "Krypton API");
     	icon.addActionListener(new ActionListener() {
       		public void actionPerformed(ActionEvent e) {

	    		startm_yn = true;
	    		settingsx[9] = "1";
	    		savex();
	    		System.out.println("Window Opening");

        	}
     	});

     	SystemTray.getSystemTray().add(icon);


	}catch(Exception e){}


	}//last8********************************************













	class RemindTask_miner_proxy extends TimerTask{
	Runtime rxrunti = Runtime.getRuntime();

	public void run(){//**************************************************************************************


		System.out.println("start miner proxy");



		try{

      		String line;
      		p = Runtime.getRuntime().exec("mining_proxy.exe");


			System.out.println("printing");

      		BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
			BufferedReader error = new BufferedReader(new InputStreamReader(p.getErrorStream()));

      		while ((line = error.readLine()) != null) {
        		System.out.println("line " + line);
      		}//****************************************

      		while ((line = input.readLine()) != null) {
        		System.out.println("line " + line);
      		}//****************************************

      		input.close();


    	}catch (Exception err) {err.printStackTrace();}




	}//runx***************************************************************************************************
    }//remindtask















	public void get_base58_key(){

		try{

        	String base58 = settingsx[5];

        	int len = base58.length();
        	byte[] data = new byte[len / 2];

        	for (int i = 0; i < len; i += 2) {
            	data[i / 2] = (byte) ((Character.digit(base58.charAt(i), 16) << 4) + Character.digit(base58.charAt(i+1), 16));
        	}//*******************************

        	byte[] sha256_1 = MessageDigest.getInstance("SHA-256").digest(data);

        	base58 = Base58Encode.encode(sha256_1);

        	base58_id = base58;

    	}catch(Exception e){e.printStackTrace();}

	}//**************************













	class RemindTask_engine extends TimerTask{
	Runtime rxrunti = Runtime.getRuntime();

	public void run(){//************************************************************************************


		while(true){


			try{



				// Calculate the time since the last frame
				thisTick = System.currentTimeMillis();
				seconds = (thisTick - starttime) / 1000;


				int requests = send_requests + inbox_requests + get_requests;
				long last_block_tl = (thisTick - last_block_longstamp2) / 1000;


				//node number
				test_node_number();

				last_block_time = last_block_longstamp2;






				if(no_peers_time > 100){

					restartApplication();

				}//*********************

				if(seconds > 1000 && last_block_tl > 3000){

					System.out.println("time_block_added Test: " + (System.currentTimeMillis() - time_block_added));

					if((System.currentTimeMillis() - time_block_added) > 1000){restartApplication();}

				}//****************************************



				try{Thread.sleep(1000);} catch(InterruptedException e){}


			}catch(Exception e){e.printStackTrace();}


		}//*********


	}//runx***************************************************************************************************
    }//remindtask








	class RemindTask_engine2 extends TimerTask{
	Runtime rxrunti = Runtime.getRuntime();

	public void run(){//************************************************************************************


		while(true){

			//System.gc();

			try{Thread.sleep(100000);} catch (InterruptedException e){}

			//status_x1.setText("Clean up memory...");

			try{Integer.parseInt(settingsx[6]);}
			catch(Exception e){restartApplication();}//restartApplication();

		}//*********


	}//runx*************************************************************************************************
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
  			SystemTray.getSystemTray().remove(icon);
  			System.exit(0);

  		}//try
  		catch(Exception e){JOptionPane.showMessageDialog(null, e.getCause());}

	}//******************************









	public void test_node_number(){





	}//****************************









	public void savex(){

		int test_db = 0;
		while(database_in_use == 1){

    			System.out.println("Database in use...save settings");
				try{Thread.sleep(1000);} catch (InterruptedException e){}
				test_db++;
				if(test_db > 20){break;}

    	}//*************************


		krypton_database_save saved = new krypton_database_save();


	}//*****************






	public void start_network(){

		String response2 = JOptionPane.showInputDialog(null, "Enter Node IP:", "IP Address", JOptionPane.QUESTION_MESSAGE);
		if(response2.length() > 0){

			krypton_database_nodes add_node = new krypton_database_nodes(response2);

			krypton_database_load_network nodesx = new krypton_database_load_network();

			krypton_database_load loadx = new krypton_database_load();

			System.out.println(network_list.size());

		}//************************

	}//*************************







	public void build_item_array(){

		item_layout = new String[listing_size];

		item_layout[0] = new String("id");
		item_layout[1] = new String("mining_date");
		item_layout[2] = new String("mining_difficulty");
		item_layout[3] = new String("mining_noose");
		item_layout[4] = new String("mining_old_block");
		item_layout[5] = new String("mining_new_block");
		item_layout[6] = new String("noose");
		item_layout[7] = new String("date_id");
		item_layout[8] = new String("owner_id");
		item_layout[9] = new String("previous_hash_id");
		item_layout[10] = new String("hash_id");
		item_layout[11] = new String("sig_id");
		item_layout[12] = new String("owner_rating");
		item_layout[13] = new String("currency");
		item_layout[14] = new String("custom_template");
		item_layout[15] = new String("custom_1");
		item_layout[16] = new String("custom_2");
		item_layout[17] = new String("custom_3");
		item_layout[18] = new String("item_errors");
		item_layout[19] = new String("item_date_listed");
		item_layout[20] = new String("item_date_listed_day");
		item_layout[21] = new String("item_date_listed_int");
		item_layout[22] = new String("item_hits");
		item_layout[23] = new String("item_confirm_code");
		item_layout[24] = new String("item_confirmed");
		item_layout[25] = new String("item_cost");
		item_layout[26] = new String("item_description");
		item_layout[27] = new String("item_id");
		item_layout[28] = new String("item_price");
		item_layout[29] = new String("item_weight");
		item_layout[30] = new String("item_listing_id");
		item_layout[31] = new String("item_notes");
		item_layout[32] = new String("item_package_d");
		item_layout[33] = new String("item_package_l");
		item_layout[34] = new String("item_package_w");
		item_layout[35] = new String("item_part_number");
		item_layout[36] = new String("item_title");
		item_layout[37] = new String("item_title_url");
		item_layout[38] = new String("item_type");
		item_layout[39] = new String("item_search_1");
		item_layout[40] = new String("item_search_2");
		item_layout[41] = new String("item_search_3");
		item_layout[42] = new String("item_site_id");
		item_layout[43] = new String("item_site_url");
		item_layout[44] = new String("item_picture_1");
		item_layout[45] = new String("item_total_on_hand");
		item_layout[46] = new String("sale_payment_address");
		item_layout[47] = new String("sale_payment_type");
		item_layout[48] = new String("sale_fees");
		item_layout[49] = new String("sale_id");
		item_layout[50] = new String("sale_seller_id");
		item_layout[51] = new String("sale_status");
		item_layout[52] = new String("sale_tax");
		item_layout[53] = new String("sale_shipping_company");
		item_layout[54] = new String("sale_shipping_in");
		item_layout[55] = new String("sale_shipping_out");
		item_layout[56] = new String("sale_source_of_sale");
		item_layout[57] = new String("sale_total_sale_amount");
		item_layout[58] = new String("sale_tracking_number");
		item_layout[59] = new String("sale_transaction_id");
		item_layout[60] = new String("sale_transaction_info");
		item_layout[61] = new String("seller_address_1");
		item_layout[62] = new String("seller_address_2");
		item_layout[63] = new String("seller_address_city");
		item_layout[64] = new String("seller_address_state");
		item_layout[65] = new String("seller_address_zip");
		item_layout[66] = new String("seller_address_country");
		item_layout[67] = new String("seller_id");
		item_layout[68] = new String("seller_ip");
		item_layout[69] = new String("seller_email");
		item_layout[70] = new String("seller_first_name");
		item_layout[71] = new String("seller_last_name");
		item_layout[72] = new String("seller_notes");
		item_layout[73] = new String("seller_phone");
		item_layout[74] = new String("seller_logo");
		item_layout[75] = new String("seller_url");

	}//****************************








	public void run_cmd(){


			String response2 = JOptionPane.showInputDialog(null, "Enter command:", "CMD", JOptionPane.QUESTION_MESSAGE);
            if(response2.length() > 0){



            }//************************



	}//*******************









	public void delete_from_network(){


			String response2 = JOptionPane.showInputDialog(null, "Enter node to delete:", "Delete node", JOptionPane.QUESTION_MESSAGE);
            if(response2.length() > 0){

            	krypton_database_delete_node pxd = new krypton_database_delete_node();
            	pxd.delete(response2);
            	krypton_database_load xxs = new krypton_database_load();

            }//************************



	}//*******************************








	public void delete_network(){


			int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the network list?");
 			if(response == 0){

            	krypton_database_delete_network pxd = new krypton_database_delete_network();
            	krypton_database_load xxs = new krypton_database_load();

            }//***************



	}//*******************************









//***************************************************************************************************************************************
//***************************************************************************************************************************************





//start the program.
    public static void main(String[] args) {

		network black = new network();

    }//main




}//last
