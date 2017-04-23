import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;  
import java.lang.Object.*;  
import java.net.*;

import java.awt.Toolkit;

import java.security.MessageDigest;

import java.awt.Toolkit;
import java.util.Timer;
import java.util.TimerTask;
import java.util.*;

import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.KeyPair;
import java.security.KeyFactory;
import java.security.KeyPairGenerator;
import java.security.Signature;
import java.security.PrivateKey;
import java.security.PublicKey;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONValue;

import org.spongycastle.util.encoders.Base64;





public class edit_transfer_item extends JFrame implements ActionListener{//************************************************************************



static String tokenx[] = new String[network.listing_size];

static int showid = 0;

Timer xtimerx;//class loop.
Toolkit toolkit;

JLabel headerb1_l = new JLabel("Transfer Token", JLabel.LEFT);
static JLabel headerb2_l = new JLabel("You have 0 token(s)", JLabel.LEFT);
static JLabel headerb3_l = new JLabel("", JLabel.LEFT);
JLabel headerb4_l = new JLabel("", JLabel.LEFT);

JLabel header1_l = new JLabel("Token ID", JLabel.RIGHT);
JLabel header2_l = new JLabel("To Account ID", JLabel.RIGHT);
JLabel header3_l = new JLabel("Amount", JLabel.RIGHT);
JLabel header4_l = new JLabel("", JLabel.RIGHT);

JLabel headerb5_l = new JLabel("", JLabel.LEFT);
JLabel headerb6_l = new JLabel("", JLabel.LEFT);

String[] tokenx_buffer = new String[network.listing_size];

JButton get_id = new JButton("Get ID");
JButton transferx = new JButton("Transfer");

static JTextField token_id = new JTextField("", 10);
static JTextField account_id = new JTextField("", 20);

JLabel header1_space = new JLabel("", JLabel.LEFT);
JLabel header2_space = new JLabel("", JLabel.LEFT);
JLabel header3_space = new JLabel("", JLabel.LEFT);

static SpinnerModel model;    
static JSpinner spinner;





edit_transfer_item(){//****************************





	//int what_item_save = lm.what_item;

	get_id.setPreferredSize(new Dimension(105, 20));
	get_id.setOpaque(true);
	get_id.setBackground(network.jgray);//darkgray08
	get_id.setForeground(network.blackx);//darkgray08
	get_id.setToolTipText("Get another token");
	get_id.addActionListener(this);



	transferx.setForeground(network.blackx);//darkgray08
	transferx.setBackground(network.jgray);//darkgray08
	transferx.addActionListener(this);


	header1_l.setPreferredSize(new Dimension(100, 20));
	header2_l.setPreferredSize(new Dimension(100, 20));
	header3_l.setPreferredSize(new Dimension(100, 20));
	header4_l.setPreferredSize(new Dimension(168, 20));

	headerb1_l.setPreferredSize(new Dimension(355, 20));
	headerb2_l.setPreferredSize(new Dimension(355, 20));
	headerb3_l.setPreferredSize(new Dimension(355, 20));
	headerb4_l.setPreferredSize(new Dimension(355, 20));
	headerb5_l.setPreferredSize(new Dimension(355, 20));
	headerb6_l.setPreferredSize(new Dimension(255, 20));

	//System.out.println("base58_id " + network.base58_id);

	headerb2_l.setText("You have " + Integer.toString(network.database_listings_owner) + " token(s)");
	headerb2_l.setFont(network.f_01);
	headerb3_l.setText("");
	headerb3_l.setFont(network.f_01);

	token_id.setText("");
	token_id.setBorder(BorderFactory.createLineBorder(network.whitex));

	account_id.setText("");
	account_id.setBorder(BorderFactory.createLineBorder(network.whitex));



	try{

		int testerh = network.database_listings_owner;

		if(testerh < 1){testerh = 17;}

		model = new SpinnerNumberModel(1, 1, testerh, 1);     

	}catch(Exception e){}


	spinner = new JSpinner(model);
	spinner.setPreferredSize(new Dimension(50, 22));
	spinner.setBorder(BorderFactory.createLineBorder(network.whitex));










	JPanel transferp = new JPanel();
	transferp.setLayout(new FlowLayout());
	transferp.setBackground(network.jgray);//darkgray08
	transferp.setPreferredSize(new Dimension(400, 240));
	transferp.add(headerb1_l);
	transferp.add(headerb2_l);
	transferp.add(headerb3_l);
	transferp.add(headerb4_l);
	transferp.add(header1_l);	   transferp.add(token_id);      transferp.add(get_id);
	transferp.add(header2_l);	   transferp.add(account_id);
	transferp.add(header3_l);	   transferp.add(spinner);       transferp.add(header4_l);
	transferp.add(headerb5_l);
	transferp.add(headerb6_l);     transferp.add(transferx);

	



	network.build3.add(transferp);


	showid = 0;
	//show_token();


}//csv_loader_x1********************************************************






	static public void resetspx(){

		System.out.println("Reset Spinner");

		int value = (Integer) spinner.getValue();

		try{

			System.out.println("network.database_listings_owner " + network.database_listings_owner);
			//JOptionPane.showMessageDialog(null, Integer.toString(network.database_listings_owner));

			model = new SpinnerNumberModel(value, 1, network.database_listings_owner, 1);     

			spinner.setModel(model);

		}catch(Exception e){e.printStackTrace();}


	}//***************************





	static public void show_token(){

		System.out.println("Next");

		try{

			if(showid > -1){

				String req_id = network.my_listings.get(showid).toString();
				tokenx = get_token(req_id);
				token_id.setText(req_id);

			}//if
			else{JOptionPane.showMessageDialog(null, "You have no tokens.");}

		}catch(Exception e){e.printStackTrace(); JOptionPane.showMessageDialog(null, "System is not ready!");}

	}//*****************









	public static String[] get_token(String x){

		String[] token1 = new String[network.listing_size];

		String jsonText = new String("");

		while(network.database_in_use == 1){

			int test_db = 0;
    		System.out.println("Database in use... get_token transfer");
			try{Thread.sleep(1000);} catch (InterruptedException e){}
			test_db++;
			if(test_db > network.database_time_out){break;}

    	}//*********************************

		krypton_database_get_token tokenx = new krypton_database_get_token();
		token1 = tokenx.get_token(x);

		return token1;

	}//****************************************











	public void update_yes(){

		//get the value first
		int value = (Integer) spinner.getValue();


		//reset the spinner if the key has changed
		try{

			model = new SpinnerNumberModel(value, 1, network.database_listings_owner, 1);  
			//JOptionPane.showMessageDialog(null, Integer.toString(network.database_listings_owner));
   
			spinner = new JSpinner(model);

		}catch(Exception e){e.printStackTrace();}


	

		//continue
		String[] token_array = get_token(token_id.getText());

		System.out.println("token_array[60] " + token_array[60]);
		System.out.println("base58_id " + network.base58_id);


		if(token_array[60].equals(network.base58_id)){


			if(account_id.getText().length() < 43 || account_id.getText().length() > 50){

				JOptionPane.showMessageDialog(null, "Account ID is not valid!");

			}//************************************
			else{

				int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to transfer " + Integer.toString(value) + " token(s) to another account?");
 				if(response == 0){

 					if(value == 1){

 					tokenx = token_array;
					updatet();

					account_id.setText("");

					}//************
					else if(value > 1){

						//we don't have access to the mining class
						//mining.mining3 = false;
						System.out.println("transfer many");

						for (int loop = 0; loop < value; loop++){

							//krypton_database_get_token tokenx4b = new krypton_database_get_token();
							String[] token_array2 = get_token( network.my_listings.get(loop).toString() );

							System.out.println("token_array[60] " + token_array[60]);
							System.out.println("base58_id " + network.base58_id);

 							tokenx = token_array2;
							updatet();

						}//for***********************************

						//krypton_database_load reload = new krypton_database_load();
						//mining.mining3 = true;
						account_id.setText("");

					}//else if

				}//***************

			}//else


		}//
		else{JOptionPane.showMessageDialog(null, "This is not your token!");}


	}//**********************








	public void updatet(){

		System.out.println("Update");


		tokenx[3] = Long.toString( System.currentTimeMillis() );

		tokenx[4] = network.settingsx[5];

		//tokenx[9] = tokenx[9];

		tokenx[60] = account_id.getText();


		//sign

		try{

            	String build_hash = new String("");

				build_hash = build_hash + tokenx[0];
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

                System.out.println(sigpk3.verify(signatureBytesx3));


                //send the update
	            tokenx_buffer = tokenx;
				toolkit = Toolkit.getDefaultToolkit();
				xtimerx = new Timer();
				xtimerx.schedule(new RemindTask_send_update(), 0);
	            //send the update

				network.icon.displayMessage("Krypton", "Token transfer ID (" + tokenx[0] + ")", TrayIcon.MessageType.INFO);


		}catch(Exception e){e.printStackTrace();}



	}//*****************









	class RemindTask_send_update extends TimerTask{
	Runtime rxrunti = Runtime.getRuntime();

	public void run(){//************************************************************************************

		krypton_update_token update = new krypton_update_token(tokenx_buffer);
        //krypton_net_client.send_unconfirmed_update(tokenx_buffer);
		krypton_database_load load = new krypton_database_load();

	}//runx***************************************************************************************************
    }//remindtask




    public void show_id_test(){

    	if(token_id.getText().toString().length() == 0){showid = 0;}
    	else{showid++;}

    	if(network.my_listings.size() == 0){showid = -1;}

    	show_token();


    }//





//handel all the button clicks. 
public void actionPerformed(ActionEvent event){

	if(event.getSource() == get_id)      {show_id_test();}
	if(event.getSource() == transferx)   {update_yes();}

}//********************************************







}//last****************************************