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




//RPC commands

//lmx_active              program active or not returns error or 1
//lmx_get_info            returns program info

//lmx_get_items_count     returns the items for sale count
//lmx_get_item_x          returns info about a given item by sale ID

//lmx_get_sales_count     returns the sales count
//lmx_get_sale_x          returns info about a given sale by sale ID
//lmx_confirm_sales       confirms payment for items
//lmx_confirm_shipped     confirms item shipped

//lmx_get_purchase_count  returns the purchased items count
//lmx_get_purchase_x      returns a purchase by ID

//lmx_get_balance         gets the balance shown in the program
//lmx_refresh_balance     refreshes the balance from the connected wallet
//lmx_get_store           gets the store name
//lmx_get_wallet_type     gets the wallet type connected to the program blockchain.info or bitcoind

//lmx_add_item            adds a new item from info provided
//lmx_edit_item           edit an item by ID




public class test{


Timer xtimerx;//class loop.
Toolkit toolkit;

String command = new String();



test(String[] argsx){//*****************************************************************


	//command = argsx[0];
	request_status();

	try{Thread.sleep(10000);} catch (InterruptedException e){}

}//**************************************************************************************************














public void request_status(){//*****************************************************************

	String jsonText = new String("");


	try{

		JSONObject obj = new JSONObject();
		obj.put("request", "set_transfer_block");
		obj.put("item_id", "102119");
		obj.put("send_to", "6bpczgx79U6zcWHkEt3GTpGFBbRXNhQpxRHyELYd7qrV");

		StringWriter out = new StringWriter();
		obj.writeJSONString(out);
		jsonText = out.toString();
		System.out.println(jsonText);

	}catch(Exception e){System.out.println("JSON ERROR");}




	try{

		String sentence;   
		String modifiedSentence;   

		BufferedReader inFromUser = new BufferedReader( new InputStreamReader(System.in) );
		System.out.println(">>> " + "localhost" + " " + "55556");
		Socket clientSocket = new Socket("127.0.0.1", 55556);   
		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));    
		sentence = jsonText;  
		outToServer.writeBytes(sentence + '\n');   
		modifiedSentence = inFromServer.readLine();   
		System.out.println("FROM SERVER: " + modifiedSentence);
		clientSocket.close();

	}catch(Exception e){e.printStackTrace(); System.out.println("API SERVER OFFLINE!");}

}//*********************************************************************************************






//start the program.
    public static void main(String[] args) {

		test market = new test(args);

    }//main




}//last
