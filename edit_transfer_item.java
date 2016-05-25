import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;  
import java.lang.Object.*;  
import java.net.*;

import java.awt.Toolkit;

import java.security.MessageDigest;

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

static String tokenx[] = new String[krypton.listing_size];

static int showid = 0;


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


	JButton get_id = new JButton("Get ID");
	JButton transferx = new JButton("Transfer");

	static JTextField token_id = new JTextField("", 10);
	static JTextField account_id = new JTextField("", 20);


	JLabel header1_space = new JLabel("", JLabel.LEFT);
	JLabel header2_space = new JLabel("", JLabel.LEFT);
	JLabel header3_space = new JLabel("", JLabel.LEFT);


	SpinnerModel model;    
	JSpinner spinner;





edit_transfer_item(){//****************************





	//int what_item_save = lm.what_item;

	get_id.setPreferredSize(new Dimension(105, 20));
	get_id.setOpaque(true);
	get_id.setBackground(krypton.jgray);//darkgray08
	get_id.setForeground(krypton.blackx);//darkgray08
	get_id.setToolTipText("Get another token");
	get_id.addActionListener(this);



	transferx.setForeground(krypton.blackx);//darkgray08
	transferx.setBackground(krypton.jgray);//darkgray08
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

	System.out.println("base58_id " + krypton.base58_id);

	headerb2_l.setText("You have " + Integer.toString(krypton.database_listings_owner) + " token(s)");
	headerb2_l.setFont(krypton.f_01);
	headerb3_l.setText("");
	headerb3_l.setFont(krypton.f_01);

	token_id.setText("");
	token_id.setBorder(BorderFactory.createLineBorder(krypton.whitex));

	account_id.setText("");
	account_id.setBorder(BorderFactory.createLineBorder(krypton.whitex));



	try{

		model = new SpinnerNumberModel(1, 1, krypton.database_listings_owner, 1);     

	}catch(Exception e){model = new SpinnerNumberModel(1, 1, 1, 1);  }


	spinner = new JSpinner(model);
	spinner.setPreferredSize(new Dimension(50, 22));
	spinner.setBorder(BorderFactory.createLineBorder(krypton.whitex));










	JPanel transferp = new JPanel();
	transferp.setLayout(new FlowLayout());
	transferp.setBackground(krypton.jgray);//darkgray08
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

	



	krypton.build3.add(transferp);


	showid = 0;
	show_token();


}//csv_loader_x1********************************************************












	static public void show_token(){

		System.out.println("Next");

		try{

			if(showid > -1){

				String req_id = krypton.my_listings.get(showid).toString();
				tokenx = get_token(req_id);
				token_id.setText(req_id);

			}//if

		}catch(Exception e){}

	}//*****************



	public static String[] get_token(String x){


		String[] token1 = new String[krypton.listing_size];

		String jsonText = new String("");


		try{

			JSONObject obj = new JSONObject();
			obj.put("request", "get_token");
			obj.put("item_id", x);
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


			JSONParser parserx = new JSONParser();
			Object objx = parserx.parse(modifiedSentence);
			JSONObject jsonObjectx = (JSONObject) objx;

				String item_xf = (String) jsonObjectx.get("message");

				Object objx2 = parserx.parse(item_xf);
				JSONObject jsonObjectx2 = (JSONObject) objx2;

				for(int loop = 0; loop < krypton.listing_size; loop++){//************

					System.out.println("GET " + krypton.item_layout[loop]);
					token1[loop] = (String) jsonObjectx2.get(krypton.item_layout[loop]);

				}//******************************************************************


		}catch(Exception e){e.printStackTrace(); System.out.println("API SERVER OFFLINE!"); modifiedSentence = "API SERVER OFFLINE!";}


		return token1;

	}//****************************************







	public void update_yes(){

		int value = (Integer) spinner.getValue();

		String[] token_array = get_token(token_id.getText());

		System.out.println("token_array[60] " + token_array[60]);
		System.out.println("base58_id " + krypton.base58_id);


		if(token_array[60].equals(krypton.base58_id)){


			if(account_id.getText().length() < 43){

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

						mining.mining3 = false;
						System.out.println("transfer many");

						for (int loop = 0; loop < value; loop++){

							krypton_database_get_token tokenx4b = new krypton_database_get_token();
							String[] token_array2 = tokenx4b.get_token( krypton.my_listings.get(loop).toString() );

							System.out.println("token_array[60] " + token_array[60]);
							System.out.println("base58_id " + krypton.base58_id);

 							tokenx = token_array2;
							updatet();

						}//for***********************************

						krypton_database_load reload = new krypton_database_load();
						mining.mining3 = true;
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

		tokenx[4] = krypton.settingsx[5];

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

	    		byte[] clear = Base64.decode(krypton.settingsx[4]);
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


                update_send(tokenx);


		}catch(Exception e){e.printStackTrace();}



	}//*****************






	public void update_send(String[] token){


		String[] token1 = new String[krypton.listing_size];

		String jsonText = new String("");


		try{

			JSONObject obj2 = new JSONObject();

			for (int loop = 0; loop < tokenx.length; loop++){

				obj2.put(krypton.item_layout[loop], tokenx[loop]);

			}//**********************************************

			StringWriter out2 = new StringWriter();
			obj2.writeJSONString(out2);
			String jsonText2 = out2.toString();
			System.out.println(jsonText2);

			JSONObject obj = new JSONObject();
			obj.put("request", "set_transfer_block");
			obj.put("password", "1234");
			obj.put("item_array", jsonText2);

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


			JSONParser parserx = new JSONParser();
			Object objx = parserx.parse(modifiedSentence);
			JSONObject jsonObjectx = (JSONObject) objx;

			String item_xf = (String) jsonObjectx.get("message");

		}catch(Exception e){e.printStackTrace(); System.out.println("API SERVER OFFLINE!"); modifiedSentence = "API SERVER OFFLINE!";}



	}//*************************************













//handel all the button clicks. 
public void actionPerformed(ActionEvent event){

	if(event.getSource() == get_id)      {showid++; show_token();}
	if(event.getSource() == transferx)   {update_yes();}

}//********************************************







}//last****************************************