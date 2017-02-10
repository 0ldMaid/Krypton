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

import java.util.ArrayList;
import java.util.Properties;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONValue;





public class startup{

Toolkit toolkit;
Timer xtimerx;
String programss = new String("");


startup(){//**************************************************************************


    request_status();

    if(programss.length() == 0){

        //krypton network
	    toolkit = Toolkit.getDefaultToolkit();
	    xtimerx = new Timer();
        xtimerx.schedule(new RemindTask_network(), 0);

    }//if**********************

	//krypton network
	toolkit = Toolkit.getDefaultToolkit();
	xtimerx = new Timer();
	xtimerx.schedule(new RemindTask_krypton(), 0);


    try{Thread.sleep(5000);} catch (InterruptedException e){}

	System.exit(0);


}//*****************************************************************************









    public void request_status(){//*****************************************************************



        String jsonText = new String("");


        try{

            JSONObject obj = new JSONObject();
            obj.put("request", "status");
            obj.put("password", "1234");

            StringWriter out = new StringWriter();
            obj.writeJSONString(out);
            jsonText = out.toString();
            System.out.println(jsonText);

        }catch(Exception e){System.out.println("JSON ERROR");}



        String sentence;   
        String modifiedSentence = new String();   

        try{


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


            JSONParser parser = new JSONParser();

            Object obj = parser.parse(modifiedSentence);
            JSONObject jsonObject = (JSONObject) obj;
  
            String message = (String) jsonObject.get("message");

            System.out.println(message);


            Object obj2 = parser.parse(message);
            JSONObject jsonObject2 = (JSONObject) obj2;
  
            programss = jsonObject2.get("program_status").toString();


        }catch(Exception e){e.printStackTrace();}//*****************

    }//*********************************************************************************************











	class RemindTask_krypton extends TimerTask{
	Runtime rxrunti = Runtime.getRuntime();

	public void run(){//**************************************************************************************


		try{


  			final String javaBin = System.getProperty("java.home") + File.separator + "bin" + File.separator + "javaw";
  			final String currentJar = System.getProperty("user.dir") + File.separator + "Krypton.jar";

  			System.out.println("javaBin " + javaBin);
  			System.out.println("currentJar " + currentJar);

  			/* Build command: java -jar application.jar */
  			final ArrayList<String> command = new ArrayList<String>();
  			command.add(javaBin);
  			command.add("-jar");
  			command.add("-Xms256m");
  			command.add("-Xmx512m");
  			command.add(currentJar);

  			final ProcessBuilder builder = new ProcessBuilder(command);
  			builder.start();

  			//try{Thread.sleep(10000);} catch (InterruptedException e){}


  		}//try
  		catch(Exception e){e.printStackTrace();}

	}//runx***************************************************************************************************
    }//remindtask





	class RemindTask_network extends TimerTask{
	Runtime rxrunti = Runtime.getRuntime();

	public void run(){//**************************************************************************************


		try{


  			final String javaBin = System.getProperty("java.home") + File.separator + "bin" + File.separator + "javaw";
  			final String currentJar = System.getProperty("user.dir") + File.separator + "Network.jar";

  			System.out.println("javaBin " + javaBin);
  			System.out.println("currentJar " + currentJar);

  			/* Build command: java -jar application.jar */
  			final ArrayList<String> command = new ArrayList<String>();
  			command.add(javaBin);
  			command.add("-jar");
  			command.add("-Xms256m");
  			command.add("-Xmx1024m");
  			command.add(currentJar);

  			final ProcessBuilder builder = new ProcessBuilder(command);
  			builder.start();

  			//try{Thread.sleep(10000);} catch (InterruptedException e){}

  			
  		}//try
  		catch(Exception e){e.printStackTrace();}

	}//runx***************************************************************************************************
    }//remindtask









    //start the program.
    public static void main(String[] args) {

		startup black = new startup();

    }//main




}//last
