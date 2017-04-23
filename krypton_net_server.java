import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;  
import java.lang.Object.*;  
import java.net.*;

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

import javax.imageio.ImageIO;
import javax.imageio.*;
import java.awt.image.*;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONValue;







public class krypton_net_server{


Timer xtimerx;//class loop.
Toolkit toolkit;

String user_ip = new String("");
String get_list = new String("");
String post_list = new String("");

String client_address_connect = new String("127.0.0.1");
String clientSentence = new String("");
int client_port_connect = 0000;

boolean test_statex = false;
boolean send_html = false;








krypton_net_server(){//*****************************************************************




	toolkit = Toolkit.getDefaultToolkit();
	xtimerx = new Timer();
	xtimerx.schedule(new RemindTask_server(), 0);



}//*****************************************************************************










	class RemindTask_server extends TimerTask{
	Runtime rxrunti = Runtime.getRuntime();

	public void run(){//************************************************************************************

		while(true){

			while(network.server == 1){

				get_list = "";
				post_list = "";

				String SYSTEM_ERROR_MESSAGE = new String("");
				String jsonText = new String("");
				String statex = new String("");
				String responsex = new String("");          
				String capitalizedSentence;          
				String passwordx = new String("");
				String client_ip = new String("");


				try{          


					InetAddress IP = InetAddress.getLocalHost();
					ServerSocket serverSocket = null;

       				try{

            			serverSocket = new ServerSocket(network.p2p_port); 

            			//network.status8.setText("Port Bindings: (Server: " + Integer.toString(network.p2p_port) + ") (API: " + Integer.toString(network.api_port) + ")");
           				//network.status8.setIcon(network.imx1);

        			}catch(IOException e) {

           				System.err.println("Could not listen on port: " + network.p2p_port);
           				network.programst = "Port blocked (" + Integer.toString(network.p2p_port) + ")";
            			//JOptionPane.showMessageDialog(null, "Could not listen on port: " + network.p2p_port);

           				//network.status8.setText("Could not listen on port: " + network.p2p_port);
           				//network.status8.setIcon(network.imx0);

						try{Thread.sleep(1000);} catch (InterruptedException ex){}

        			}//********************



        				Socket clientSocket = null; 

        				try{

            				clientSocket = serverSocket.accept();
            				if(clientSocket != null){System.out.println("Connected");}

	    					BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            				String inputLine;
            				while (!(inputLine = in.readLine()).equals("")){

               	 				System.out.println(inputLine);
								clientSentence = inputLine;

								if(inputLine.contains("/item/") && !inputLine.contains("Referer")){get_list = inputLine;}
								if(inputLine.contains("/search/") && !inputLine.contains("Referer")){get_list = inputLine;}
								if(inputLine.contains("/item/") && !inputLine.contains("Referer")){get_list = inputLine;}

								if(inputLine.equals("\r") || inputLine.equals("\n")){System.out.println("BREAK>>>"); break;}
								else{System.out.println("xx");}

	    					}//**********************************************
            				//in.close();

	    					System.out.println(">>>");

	    					client_ip = clientSocket.getRemoteSocketAddress().toString();
	    					System.out.println("CLIENT ADDRESS " + clientSocket.getRemoteSocketAddress().toString());

        			}catch (IOException e) {

             			System.err.println("Accept failed.");
             			JOptionPane.showMessageDialog(null, "Accept failed.");

        			}//*********************

        			PrintWriter out = new PrintWriter(clientSocket.getOutputStream());

					JSONParser parser = new JSONParser();

					try {

						send_html = false;
						test_statex = false;
						statex = "0";
						responsex = "e000"; 

						Object obj = parser.parse(clientSentence);
 
						JSONObject jsonObject = (JSONObject) obj;
  
						String request = (String) jsonObject.get("request");
						System.out.println("get " + request);

						//get the token or information
						String objectx = new String("");
						String block_id = new String("");
						String packagex = new String("");
						String unconfirmed_id = new String("");
						try{objectx = (String) jsonObject.get("token").toString();} catch (Exception e) {System.out.println("extra info no token...");}
						try{block_id = (String) jsonObject.get("block_id").toString();} catch (Exception e) {System.out.println("extra info no block_id...");}
						try{packagex = (String) jsonObject.get("package").toString();} catch (Exception e) {System.out.println("extra info no package...");}
						try{unconfirmed_id = (String) jsonObject.get("unconfirmed_id").toString();} catch (Exception e) {System.out.println("extra info no unconfirmed_id...");}


							try{


								     if(request.equals("status")){statex = "1"; responsex = get_status();}

								else if(request.equals("add_new_unconfirmed")){statex = "1"; responsex = add_task(objectx);}

								else if(request.equals("add_new_block")){responsex = add_block(objectx); if(responsex.equals("1")){statex = "1"; responsex = "Block added.";} else{statex = "0"; responsex = "Block could not be added.";} }

								else if(request.equals("add_new_package")){responsex = add_package(packagex); if(responsex.equals("1")){statex = "1"; responsex = "Package added.";} else{statex = "0"; responsex = "Package could not be added.";} }

								else if(request.equals("get_difficulty")){statex = "1"; responsex = Long.toString(network.difficultyx);}

								else if(request.equals("get_network")){statex = "1"; responsex = get_network_list();}

								else if(request.equals("unconfirmed_block_update")){statex = "1"; responsex = get_unconfirmed_id_nx(unconfirmed_id);}

								else if(request.equals("block_update")){responsex = get_block_id_nx(block_id); if(test_statex){statex = "1";} else{statex = "0";}}

								else if(request.equals("blocks_x_update")){responsex = get_blocks_x_id_nx(block_id); if(test_statex){statex = "1";} else{statex = "0";}}

								else if(request.equals("blocks_n_update")){responsex = get_blocks_xn_id_nx2(block_id); if(test_statex){statex = "1";} else{statex = "0";}}

								else if(request.equals("node_info")){}
			
								else if(request.equals("show_id")){}

								else if(request.equals("get_block_list")){}	

								else if(request.equals("get_unconfirmed_list")){}	

								else if(request.equals("get_block")){}

								else if(request.equals("help")){statex = "1";}//responsex = get_help_list();
	
								else{statex = "0"; responsex = "Not a valid request!";}


							}catch (Exception e) {e.printStackTrace(); statex = "0"; responsex = "Server error!";}


					}catch (Exception e) {System.out.println("JSON ERORR SEND HTML"); responsex = build_html_file(); send_html = true;}



					JSONObject obj = new JSONObject();
					obj.put("response", statex);
					try{obj.put("message", responsex);} catch(Exception e){e.printStackTrace();}

					StringWriter outs = new StringWriter();
					obj.writeJSONString(outs);
					jsonText = outs.toString();
					//System.out.println("SEND RESPONSE " + responsex);

           
					//System.out.println("SERVER GETS: " + clientSentence);             
					//capitalizedSentence = clientSentence.toUpperCase() + '\n';             
					if(!send_html){out.print(jsonText + '\n');}
					else{out.print(responsex);}
        			out.flush();
        			out.close();

        			clientSocket.close();
        			serverSocket.close();


				}catch(Exception e){

					e.printStackTrace(); 
					try{Thread.sleep(5000);} catch (InterruptedException ex){}

				}//******************


         
			}//*****while

			System.out.println("Server is off...");
			try{Thread.sleep(10000);} catch (InterruptedException e){}

		}//*****while

	}//runx***************************************************************************************************
    }//remindtask








    public String build_html_file(){

    	network.website_hits++;

		String buffer = new String();
		String html = new String("");

        int test_db = 0;
		while(network.database_in_use == 1){

    		System.out.println("Database in use...html");
			try{Thread.sleep(1000);} catch (InterruptedException e){}
			test_db++;
			if(test_db > 20){break;}

    	}//*********************************


	    try{

			if(get_list.contains("/item/")){

				String search_for = new String("");
				int xp = 0;
				System.out.println("get_list " + get_list);

				xp = get_list.indexOf("/item/");
				search_for = get_list.substring(xp + 6, get_list.length());
				System.out.println("search_for " + search_for);

				xp = search_for.lastIndexOf(" HTTP");
				try{search_for = search_for.substring(0, xp);}catch(Exception e){}
				System.out.println("search_for " + search_for);

				try {

					BufferedReader in = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("html/item.html")));
					//BufferedReader in = new BufferedReader(new FileReader("html/item.html"));
					while ((buffer = in.readLine()) != null){html = html + buffer;}
					in.close();

				}catch(IOException e){e.printStackTrace();}

				String[] load_token = new String[network.listing_size];

				krypton_database_get_token tokenx = new krypton_database_get_token();
				load_token = tokenx.get_token(search_for);


				html = html.replace("<||ID00||>", load_token[0]);
				html = html.replace("<||ID01||>", load_token[1]);
				html = html.replace("<||ID02||>", load_token[2]);
				html = html.replace("<||ID03||>", load_token[3]);
				html = html.replace("<||ID04||>", load_token[4]);
				html = html.replace("<||ID05||>", load_token[5]);
				html = html.replace("<||ID06||>", load_token[6]);
				html = html.replace("<||ID07||>", load_token[7]);
				html = html.replace("<||ID08||>", load_token[8]);
				html = html.replace("<||ID09||>", load_token[9]);		
				html = html.replace("<||ID10||>", load_token[10]);
				html = html.replace("<||ID11||>", load_token[11]);
				html = html.replace("<||ID12||>", load_token[12]);
				html = html.replace("<||ID13||>", load_token[13]);
				html = html.replace("<||ID14||>", load_token[14]);
				html = html.replace("<||ID15||>", load_token[15]);
				html = html.replace("<||ID16||>", load_token[16]);
				html = html.replace("<||ID17||>", load_token[17]);
				html = html.replace("<||ID18||>", load_token[18]);
				html = html.replace("<||ID19||>", load_token[19]);
				html = html.replace("<||ID20||>", load_token[20]);
				html = html.replace("<||ID21||>", load_token[21]);
				html = html.replace("<||ID22||>", load_token[22]);
				html = html.replace("<||ID23||>", load_token[23]);
				html = html.replace("<||ID24||>", load_token[24]);
				html = html.replace("<||ID25||>", load_token[25]);
				html = html.replace("<||ID26||>", load_token[26]);
				html = html.replace("<||ID27||>", load_token[27]);
				html = html.replace("<||ID28||>", load_token[28]);
				html = html.replace("<||ID29||>", load_token[29]);
				html = html.replace("<||ID30||>", load_token[30]);
				html = html.replace("<||ID31||>", load_token[31]);
				html = html.replace("<||ID32||>", load_token[32]);
				html = html.replace("<||ID33||>", load_token[33]);
				html = html.replace("<||ID34||>", load_token[34]);
				html = html.replace("<||ID35||>", load_token[35]);
				html = html.replace("<||ID36||>", load_token[36]);
				html = html.replace("<||ID37||>", load_token[37]);
				html = html.replace("<||ID38||>", load_token[38]);
				html = html.replace("<||ID39||>", load_token[39]);
				html = html.replace("<||ID40||>", load_token[40]);
				html = html.replace("<||ID41||>", load_token[41]);
				html = html.replace("<||ID42||>", load_token[42]);
				html = html.replace("<||ID43||>", load_token[43]);
				html = html.replace("<||ID44||>", load_token[44]);
				html = html.replace("<||ID45||>", load_token[45]);
				html = html.replace("<||ID46||>", load_token[46]);
				html = html.replace("<||ID47||>", load_token[47]);
				html = html.replace("<||ID48||>", load_token[48]);
				html = html.replace("<||ID49||>", load_token[49]);
				html = html.replace("<||ID50||>", load_token[50]);
				html = html.replace("<||ID51||>", load_token[51]);
				html = html.replace("<||ID52||>", load_token[52]);
				html = html.replace("<||ID53||>", load_token[53]);
				html = html.replace("<||ID54||>", load_token[54]);
				html = html.replace("<||ID55||>", load_token[55]);
				html = html.replace("<||ID56||>", load_token[56]);
				html = html.replace("<||ID57||>", load_token[57]);
				html = html.replace("<||ID58||>", load_token[58]);
				html = html.replace("<||ID59||>", load_token[59]);
				html = html.replace("<||ID60||>", load_token[60]);
				html = html.replace("<||ID61||>", load_token[61]);
				html = html.replace("<||ID62||>", load_token[62]);
				html = html.replace("<||ID63||>", load_token[63]);
				html = html.replace("<||ID64||>", load_token[64]);
				html = html.replace("<||ID65||>", load_token[65]);
				html = html.replace("<||ID66||>", load_token[66]);
				html = html.replace("<||ID67||>", load_token[67]);
				html = html.replace("<||ID68||>", load_token[68]);

				for (int loop = 0; loop < network.item_layout.length; loop++){//*******

					html = html.replace("<||" + network.item_layout[loop] + "||>", load_token[loop]);

				}//********************************************************************


				//if template 0 only send description
				if(load_token[7].equals("0")){html = load_token[19];}


			}//if****************************
			else if(get_list.contains("/search/")){

				network.website_searches++;

				String search_for = new String("");
				int xp = 0;
				System.out.println("get_list " + get_list);

				xp = get_list.indexOf("/search/?search=");
				search_for = get_list.substring(xp + 16, get_list.length());
				System.out.println("search_for " + search_for);

				xp = search_for.lastIndexOf(" HTTP");
				try{search_for = search_for.substring(0, xp);}catch(Exception e){}
				search_for = search_for.replace("+"," ");
				System.out.println("search_for " + search_for);

				String[][] search_list;

				krypton_database_search searchx = new krypton_database_search();
				search_list = searchx.search(search_for);

				try {

					BufferedReader in = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("html/search.html")));
					while ((buffer = in.readLine()) != null){html = html + buffer;}
					in.close();

				}catch(IOException e){e.printStackTrace();}

				for (int loop = 0; loop < search_list[0].length; loop++){//************

					if(search_list[0][loop].contains("-")){}
					else{

						html = html + "<br />";
						html = html + "<br />";
						html = html + "<a href='/item/" + search_list[0][loop] + "'>" + search_list[0][loop] + "</a> ";
						html = html + "<a href='/item/" + search_list[0][loop] + "'>" + search_list[1][loop] + "</a>";
						html = html + "<br />";
						html = html + search_list[2][loop] + "";
						html = html + search_list[3][loop] + "";
						html = html + "&nbsp;&nbsp;&nbsp;&nbsp;Location Zip Code: " + search_list[4][loop] + "";
						html = html + "<br />";

					}//else

				}//*****************************************************************

				html = html + "</div>";
				html = html + "</center>";
				html = html + "</div>";
				html = html + "</body>";
				html = html + "</html>";


			}//*******************************
			else{

				try {

					BufferedReader in = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("html/home.html")));
					while ((buffer = in.readLine()) != null){html = html + buffer;}
					in.close();

				}catch(IOException e){e.printStackTrace();}

				html = html.replace("||<0>||", network.html_block_ql[37]);
				html = html.replace("||<1>||", network.html_block_ql[19]);
				html = html.replace("||<2>||", network.html_block_ql[41]);

			}//else

		}catch(Exception e){e.printStackTrace(); html = "error";}

		return html;

    }//status****************










    public String add_block(String token){

		String jsonarry = new String("");
		String yes_no = new String("0");


        int test_db = 0;
		while(network.database_in_use == 1){

    		System.out.println("Database in use...addb");
			try{Thread.sleep(1000);} catch (InterruptedException e){}
			test_db++;
			if(test_db > 20){break;}

    	}//*********************************


		try{

			//get the array

			JSONParser parserx = new JSONParser();
			Object objx = parserx.parse(token);
			JSONObject jsonObjectx = (JSONObject) objx;
	  


			String update_token[] = new String[network.listing_size];
			String mining_token[] = new String[network.miningx_size];

	    	for (int loop = 0; loop < network.listing_size; loop++){//************

				update_token[loop] = (String) jsonObjectx.get("l" + Integer.toString(loop));
				System.out.println("convert " + update_token[loop]);

			}//*******************************************************************

	    	for (int loop = 0; loop < mining_token.length; loop++){//************

				mining_token[loop] = (String) jsonObjectx.get("m" + Integer.toString(loop));
				System.out.println("convert " + update_token[loop]);

			}//******************************************************************



	        //get the last token
	        krypton_database_get_token getxt = new krypton_database_get_token();

	        String req_id = update_token[0];
	        String old_token[] = new String[network.listing_size];
	        old_token = getxt.get_token(req_id);

	        //try to add the new token
	        krypton_update_new_block_remote remotex = new krypton_update_new_block_remote();
			boolean test = remotex.update(update_token, mining_token, old_token);

			krypton_database_load loadx = new krypton_database_load();

		
			if(test){

				yes_no = "1";

				//delete from the pending list
				krypton_database_delete_buffer bufferd = new krypton_database_delete_buffer();
				bufferd.delete(update_token[0]);

				System.out.println("Send my new update to network.");

				toolkit = Toolkit.getDefaultToolkit();
				xtimerx = new Timer();
				xtimerx.schedule(new RemindTask_server_updaten(update_token, mining_token, old_token), 0);

			}//******
			else{yes_no = "0";}

			//JSONObject obj = new JSONObject();
			//obj.put("add_block", yes_no);

			//StringWriter out = new StringWriter();
			//obj.writeJSONString(out);
			//String jsonText = out.toString();
			//System.out.println(jsonText);

			//jsonarry = JSONValue.toJSONString(obj);
			//jsonarry = "Block " + update_token[0] + "";

		}catch(Exception e){e.printStackTrace();}

		return yes_no;

    }//status****************










    public String add_package(String packagex){

		String jsonarry = new String("");
		String yes_no = new String("0");


        int test_db = 0;
		while(network.database_in_use == 1){

    		System.out.println("Database in use...addp");
			try{Thread.sleep(1000);} catch (InterruptedException e){}
			test_db++;
			if(test_db > 20){break;}

    	}//*********************************


		try{

			//get the array

			//JOptionPane.showMessageDialog(null, packagex.length());

			JSONParser parserx = new JSONParser();
			Object objx = parserx.parse(packagex);
			JSONArray jsonObjectx = (JSONArray) objx;

	    	for(int loop = 0; loop < jsonObjectx.size(); loop++){//************

				//System.out.println(jsonObjectx.get(loop));

	    		String update_token[] = new String[network.listing_size];
				String mining_token[] = new String[network.miningx_size];

				Object obj = parserx.parse(jsonObjectx.get(loop).toString());
				JSONObject jsonObject = (JSONObject) obj;

	    		for(int loopx = 0; loopx < network.listing_size; loopx++){//**********

					update_token[loopx] = (String) jsonObject.get("l" + Integer.toString(loopx));
					//System.out.println("convert " + update_token[loopx]);

				}//*******************************************************************

	    		for(int loopx = 0; loopx < mining_token.length; loopx++){//**********

					mining_token[loopx] = (String) jsonObject.get("m" + Integer.toString(loopx));
					//System.out.println("convert " + update_token[loopx]);

				}//******************************************************************

	        	//get the last token
	        	krypton_database_get_token getxt = new krypton_database_get_token();

	        	String req_id = update_token[0];
	        	String old_token[] = new String[network.listing_size];
	        	old_token = getxt.get_token(req_id);

	        	//try to add the new token
	        	krypton_update_new_block_remote remotex = new krypton_update_new_block_remote();
				boolean test = remotex.update(update_token, mining_token, old_token);

				//JOptionPane.showMessageDialog(null, jsonObjectx.size());

				if(test){

					yes_no = "1";

					//delete from the pending list
					krypton_database_delete_buffer bufferd = new krypton_database_delete_buffer();
					bufferd.delete(update_token[0]);

				}//******
				else{yes_no = "0"; break;}

				krypton_database_load loadx = new krypton_database_load();

			}//*****************************************************************

			//package is used or broken get a new one
			network.mining_package_ready = 0;

			if(yes_no == "1"){

				System.out.println("Send my new update to network.");
				//JOptionPane.showMessageDialog(null, "Send my new update to network.");

				//toolkit = Toolkit.getDefaultToolkit();
				//xtimerx = new Timer();
				//xtimerx.schedule(new RemindTask_server_updaten(update_token, mining_token, old_token), 0);

			}

		}catch(Exception e){e.printStackTrace();}

		return yes_no;

    }//status****************











	class RemindTask_server_updaten extends TimerTask{

		Runtime rxrunti = Runtime.getRuntime();

		String[] move_itemt;
		String[] mining_itemt;
		String[] old_tokent;

		RemindTask_server_updaten(String[] move_itemx, String[] mining_itemx, String[] old_tokenx){

			move_itemt = move_itemx;
			mining_itemt = mining_itemx;
			old_tokent = old_tokenx;

		}//****************************************************************************************

		public void run(){//************************************************************************************

			System.out.println("Update all my peers with the new block.");

			//krypton_update_new_block_remote remotexu2 = new krypton_update_new_block_remote();
			//boolean test2 = remotexu2.update(move_itemt, mining_itemt, old_tokent);

			//System.out.println("test2 " + test2);

			System.out.println("Update all my peers with the new block. Done...");

		}//runx*************************************************************************************************

    }//remindtask









    public String add_task(String token){

		String jsonarry = new String("");


        int test_db = 0;
		while(network.database_in_use == 1){

    		System.out.println("Database in use...addt");
			try{Thread.sleep(1000);} catch (InterruptedException e){}
			test_db++;
			if(test_db > 20){break;}

    	}//*********************************


		try{


			//get the array

			JSONParser parserx = new JSONParser();
			Object objx = parserx.parse(token);
			JSONObject jsonObjectx = (JSONObject) objx;
	  


			String update_token[] = new String[network.listing_size];

	    	for (int loop = 0; loop < network.listing_size; loop++){//************

				update_token[loop] = (String) jsonObjectx.get(Integer.toString(loop));
				System.out.println("convert " + update_token[loop]);

			}//*******************************************************************



			//test for this item in the task list alreay
			krypton_database_get_unconfirmed_test test_token = new krypton_database_get_unconfirmed_test();
	        String req_id1 = update_token[0];
	        int int_token = test_token.testx(req_id1);

	        if(int_token == 0){

	        	//get the old token
	        	krypton_database_get_token getxt = new krypton_database_get_token();

	        	String req_id2 = update_token[0];
	        	String old_token[] = new String[network.listing_size];
	        	old_token = getxt.get_token(req_id2);

	       		//try to add the new token
	        	krypton_update_token_remote remotex = new krypton_update_token_remote();
				boolean test = remotex.update(update_token, old_token);

				String yes_no = new String("0");

				if(test){yes_no = "1"; network.last_unconfirmed_idx = update_token[10];}
				else{yes_no = "0";}


				//delete from the pending list
				krypton_database_delete_buffer bufferd = new krypton_database_delete_buffer();
				bufferd.delete(update_token[0]);


				JSONObject obj = new JSONObject();
				obj.put("add_task", yes_no);

				StringWriter out = new StringWriter();
				obj.writeJSONString(out);
				String jsonText = out.toString();
				System.out.println(jsonText);

				jsonarry = JSONValue.toJSONString(obj);

			}//if**************
			else{

				//delete from the pending list
				krypton_database_delete_buffer bufferd = new krypton_database_delete_buffer();
				bufferd.delete(update_token[0]);


				JSONObject obj = new JSONObject();
				obj.put("add_task", "0");

				StringWriter out = new StringWriter();
				obj.writeJSONString(out);
				String jsonText = out.toString();
				System.out.println(jsonText);

				jsonarry = JSONValue.toJSONString(obj);

			}//else

		}catch(Exception e){e.printStackTrace();}

		return jsonarry;

    }//status****************














    public String get_block_id_nx(String idx){

		String jsonarry = new String("");

		try{


			String[] token_array;

			System.out.println("idx " + idx);
			System.out.println("last_block_mining_idx " + network.last_block_mining_idx);



	            int test_db = 0;
				while(network.database_in_use == 1){

	    			System.out.println("Database in use...sl");
					try{Thread.sleep(1000);} catch (InterruptedException e){}
					test_db++;
					if(test_db > 20){break;}

	    		}//*********************************


			System.out.println("SLOW LOAD");
			krypton_database_get_token_fmh fmh = new krypton_database_get_token_fmh();
			token_array = fmh.get_token(idx);



			JSONObject obj = new JSONObject();
			int xxp1 = 0;
			int xxp2 = 0;
		
			for (int loop = 0; loop < network.miningx_size; loop++){//*************
				
				obj.put("m" + Integer.toString(xxp1), token_array[loop]);
				System.out.println("m" + token_array[loop]);
				xxp1++;

			}//********************************************************************

	    	for (int loop = network.miningx_size; loop < token_array.length; loop++){//*************

				obj.put("l" + Integer.toString(xxp2), token_array[loop]);
				System.out.println("l" + token_array[loop]);
				xxp2++;

			}//*************************************************************************************

			//try{Thread.sleep(200000);} catch (InterruptedException e){}


			if(token_array[0].length() > 3){test_statex = true;}

			StringWriter out = new StringWriter();
			obj.writeJSONString(out);
			String jsonText = out.toString();
			System.out.println(jsonText);

			jsonarry = JSONValue.toJSONString(obj);

		}catch(Exception e){e.printStackTrace(); jsonarry = "Cannot find block!";}

		return jsonarry;

    }//status****************












    public String get_blocks_x_id_nx(String idx){

		String jsonarry = new String("");

		try{


			String[][] token_array;

			System.out.println("idx " + idx);
			System.out.println("last_block_mining_idx " + network.last_block_mining_idx);

			//idx = "00002D476806AD5C56DEA0BB487E54AEA27FF60DEB1D2833010788B6C0C2C8F0";


	            int test_db = 0;
				while(network.database_in_use == 1){

	    			System.out.println("Database in use...xx");
					try{Thread.sleep(1000);} catch (InterruptedException e){}
					test_db++;
					if(test_db > 20){break;}

	    		}//*********************************



			System.out.println("SLOW LOAD");
			krypton_database_get_token_fmh_x fmhx = new krypton_database_get_token_fmh_x();
			token_array = fmhx.get_tokens(idx, network.package_block_size);

			System.out.println("token_array[0].length " + token_array[0].length);

			if(token_array[0].length != 0 && !token_array[0][0].equals("error")){

				test_statex = true;

				JSONObject obj1 = new JSONObject();
	    		for (int loop1 = 0; loop1 < token_array[0].length; loop1++){//************

	    		String jxsonarry = new String("");

					JSONObject obj2 = new JSONObject();
					int xxp1 = 0;
					int xxp2 = 0;
		
						for (int loop = 0; loop < network.miningx_size; loop++){//*************

							obj2.put("m" + Integer.toString(xxp1), token_array[loop][loop1]);
							System.out.println("m" + token_array[loop][loop1]);
							xxp1++;

						}//********************************************************************

	    				for (int loop = network.miningx_size; loop < token_array.length; loop++){//*************

							obj2.put("l" + Integer.toString(xxp2), token_array[loop][loop1]);
							System.out.println("l" + token_array[loop][loop1]);
							xxp2++;

						}//*************************************************************************************


					StringWriter out = new StringWriter();
					obj2.writeJSONString(out);
					String jsonTextx = out.toString();
					System.out.println(jsonTextx);

					jxsonarry = JSONValue.toJSONString(obj2);

					obj1.put(Integer.toString(loop1), jxsonarry);

				}//***********************************************************************


				StringWriter out1 = new StringWriter();
				obj1.writeJSONString(out1);
				String jsonText = out1.toString();
				System.out.println(jsonText);

				jsonarry = JSONValue.toJSONString(obj1);


			}//if**********************************
			else{test_statex = false;}


		}catch(Exception e){e.printStackTrace(); jsonarry = "Cannot find block!";}

		return jsonarry;

    }//status****************







    public String get_blocks_xn_id_nx2(String idx){

		String jsonarry = new String("");

		try{


			String[][] token_array;

			System.out.println("idx " + idx);
			System.out.println("last_block_mining_idx " + network.last_block_mining_idx);

			//idx = "00002D476806AD5C56DEA0BB487E54AEA27FF60DEB1D2833010788B6C0C2C8F0";


	            int test_db = 0;
				while(network.database_in_use == 1){

	    			System.out.println("Database in use...xx");
					try{Thread.sleep(1000);} catch (InterruptedException e){}
					test_db++;
					if(test_db > 20){break;}

	    		}//*********************************



			System.out.println("SLOW LOAD");
			krypton_database_get_token_fmh_n fmhn = new krypton_database_get_token_fmh_n();
			token_array = fmhn.get_tokens(idx, network.package_block_size);

			System.out.println("token_array[0].length " + token_array[0].length);

			if(token_array[0].length != 0 && !token_array[0][0].equals("error")){

				test_statex = true;

				JSONObject obj1 = new JSONObject();
	    		for (int loop1 = 0; loop1 < token_array[0].length; loop1++){//************

	    		String jxsonarry = new String("");

					JSONObject obj2 = new JSONObject();
					int xxp1 = 0;
					int xxp2 = 0;
		
						for (int loop = 0; loop < network.miningx_size; loop++){//*************

							obj2.put("m" + Integer.toString(xxp1), token_array[loop][loop1]);
							System.out.println("m" + token_array[loop][loop1]);
							xxp1++;

						}//********************************************************************

	    				for (int loop = network.miningx_size; loop < token_array.length; loop++){//*************

							obj2.put("l" + Integer.toString(xxp2), token_array[loop][loop1]);
							System.out.println("l" + token_array[loop][loop1]);
							xxp2++;

						}//*************************************************************************************


					StringWriter out = new StringWriter();
					obj2.writeJSONString(out);
					String jsonTextx = out.toString();
					System.out.println(jsonTextx);

					jxsonarry = JSONValue.toJSONString(obj2);

					obj1.put(Integer.toString(loop1), jxsonarry);

				}//***********************************************************************


				StringWriter out1 = new StringWriter();
				obj1.writeJSONString(out1);
				String jsonText = out1.toString();
				System.out.println(jsonText);

				jsonarry = JSONValue.toJSONString(obj1);


			}//if**********************************
			else{test_statex = false;}


		}catch(Exception e){e.printStackTrace(); jsonarry = "Cannot find block!";}

		return jsonarry;

    }//status****************














    public String get_unconfirmed_id_nx(String idx){

		String jsonarry = new String("");

        int test_db = 0;
		while(network.database_in_use == 1){

    		System.out.println("Database in use...unx");
			try{Thread.sleep(1000);} catch (InterruptedException e){}
			test_db++;
			if(test_db > 20){break;}

    	}//*********************************


		try{

			krypton_database_get_unconfirmed_fts fts = new krypton_database_get_unconfirmed_fts();
			String[] token_array = fts.get_token(idx);

			JSONObject obj = new JSONObject();
	    	for (int loop = 0; loop < token_array.length; loop++){//************

				obj.put(Integer.toString(loop), token_array[loop]);
				System.out.println("BUILD " + token_array[loop]);

			}//*****************************************************************

			if(token_array[0].length() > 3){test_statex = true;}

			StringWriter out = new StringWriter();
			obj.writeJSONString(out);
			String jsonText = out.toString();
			System.out.println(jsonText);

			jsonarry = JSONValue.toJSONString(obj);

		}catch(Exception e){e.printStackTrace();}

		return jsonarry;

    }//status****************









    public String get_network_list(){

        int test_db = 0;
		while(network.database_in_use == 1){

    		System.out.println("Database in use...update_network_list");
			try{Thread.sleep(1000);} catch (InterruptedException e){}
			test_db++;
			if(test_db > 20){break;}

    	}//*********************************


		String jsonarry = new String("");
		LinkedList<String> list = new LinkedList<String>();

		int array_size = network.network_size;

		if(array_size > 100){array_size = 100;}

		try{

			for(int xloop = 0; xloop < array_size; xloop++){//****

				list.add(network.network_list.get(xloop).toString()); 

			}//for************************************************

			jsonarry = JSONValue.toJSONString(list);

		}catch(Exception e){e.printStackTrace();}

		return jsonarry;

    }//status****************











    public String get_status(){

		String jsonarry = new String("");

		try{

			JSONObject obj = new JSONObject();
			obj.put("active","1");
			obj.put("version", network.versionx);
			obj.put("difficulty", Long.toString(network.difficultyx));
			obj.put("last_block_id", network.last_block_mining_idx);
			obj.put("last_block_timestamp", network.last_block_timestamp);
			obj.put("prev_block_id", network.prev_block_mining_idx);
			obj.put("last_unconfirmed_id", network.last_unconfirmed_idx);
			obj.put("node_list", Integer.toString(network.network_size));


			StringWriter out = new StringWriter();
			obj.writeJSONString(out);
			String jsonText = out.toString();
			System.out.println(jsonText);

			jsonarry = JSONValue.toJSONString(obj);

		}catch(Exception e){e.printStackTrace();}

		return jsonarry;

    }//status****************

















public void update_network_list(){


	krypton_database_save save = new krypton_database_save();


}//*******************************



}//last
