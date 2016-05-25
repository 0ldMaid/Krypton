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





public class edit_item extends JFrame implements ActionListener{//************************************************************************

static String tokenx[] = new String[krypton.listing_size];

static int showid = 0;

Color bluex2 = new Color(0.2f, 0.27f, 0.39f);
Color grayx1 = new Color(0.7f, 0.7f, 0.7f);
Color grayx2 = new Color(0.6f, 0.6f, 0.6f);
Color xstripesc = new Color(0.0f, 0.0f, 0.0f);
Color blackx = new Color(0.0f, 0.0f, 0.0f);
Color greenx = new Color(0.15f, 0.6f, 0.25f);
Color redx = new Color(0.9f, 0.1f, 0.1f);
Color whitex = new Color(1.0f, 1.0f, 1.0f);
Color grayxb = new Color(0.6f, 0.6f, 0.6f);
Color purple = new Color(1.0f, 0.0f, 0.8f);
Color bluex1 = new Color(0.4f, 0.4f, 0.9f);
Color selectx = new Color(1.0f, 0.9f, 0.0f);
Color yellowx = new Color(1.0f, 0.9f, 0.0f);
Color yellowx2 = new Color(0.8f, 0.7f, 0.0f);
Color lightgreenx = new Color(0.5f, 0.9f, 0.5f);
Color darkgreenx = new Color(0.1f, 0.3f, 0.1f);
Color darkgreebnx = new Color(0.1f, 0.3f, 0.1f);
Color gray5 = new Color(0.5f, 0.5f, 0.5f);
Color gray6 = new Color(0.6f, 0.6f, 0.6f);
Color gray7 = new Color(0.7f, 0.7f, 0.7f);
Color gray8 = new Color(0.8f, 0.8f, 0.8f);
Color gray9 = new Color(0.9f, 0.9f, 0.9f);


String cx0 = new String("");
String cx1 = new String("");
String cx2 = new String("");



static JLabel token_id;
JLabel header1_l = new JLabel("currency", JLabel.RIGHT);
JLabel header2_l = new JLabel("custom_template", JLabel.RIGHT);
JLabel header3_l = new JLabel("custom_1", JLabel.RIGHT);
JLabel header4_l = new JLabel("custom_2", JLabel.RIGHT);
JLabel header5_l = new JLabel("custom_3", JLabel.RIGHT);
JLabel header6_l = new JLabel("item_errors", JLabel.RIGHT);
JLabel header7_l = new JLabel("item_date_listed", JLabel.RIGHT);
JLabel header8_l = new JLabel("item_date_listed_day", JLabel.RIGHT);
JLabel header9_l = new JLabel("item_date_listed_int", JLabel.RIGHT);
JLabel header10_l = new JLabel("hits", JLabel.RIGHT);
JLabel header11_l = new JLabel("item_confirm_code", JLabel.RIGHT);
JLabel header12_l = new JLabel("item_confirmed", JLabel.RIGHT);
JLabel header13_l = new JLabel("cost", JLabel.RIGHT);
JLabel header14_l = new JLabel("item_description", JLabel.RIGHT);
JLabel header15_l = new JLabel("item_id", JLabel.RIGHT);
JLabel header16_l = new JLabel("sale_price", JLabel.RIGHT);
JLabel header17_l = new JLabel("weight", JLabel.RIGHT);
JLabel header18_l = new JLabel("item_listing_id", JLabel.RIGHT);
JLabel header19_l = new JLabel("item_notes", JLabel.RIGHT);
JLabel header20_l = new JLabel("item_package_d", JLabel.RIGHT);
JLabel header21_l = new JLabel("item_package_l", JLabel.RIGHT);
JLabel header22_l = new JLabel("item_package_w", JLabel.RIGHT);
JLabel header23_l = new JLabel("item_part_number", JLabel.RIGHT);
JLabel header24_l = new JLabel("title", JLabel.RIGHT);
JLabel header25_l = new JLabel("item_title_url", JLabel.RIGHT);
JLabel header26_l = new JLabel("item_type", JLabel.RIGHT);
JLabel header27_l = new JLabel("item_search_1", JLabel.RIGHT);
JLabel header28_l = new JLabel("item_search_2", JLabel.RIGHT);
JLabel header29_l = new JLabel("item_search_3", JLabel.RIGHT);
JLabel header30_l = new JLabel("seller_id", JLabel.RIGHT);
JLabel header31_l = new JLabel("item_site_url", JLabel.RIGHT);
JLabel header32_l = new JLabel("picture_1", JLabel.RIGHT);
JLabel header33_l = new JLabel("item_total_on_hand", JLabel.RIGHT);




static JTextField currency = new JTextField("", 8);
static JTextField custom_template = new JTextField("", 28);
static JTextField custom_1 = new JTextField("", 28);
static JTextField custom_2 = new JTextField("", 28);
static JTextField custom_3 = new JTextField("", 28);
static JTextField item_errors = new JTextField("", 8);
static JTextField item_date_listed = new JTextField("", 8);
static JTextField item_date_listed_day = new JTextField("", 8);
static JTextField item_date_listed_int = new JTextField("", 8);
static JTextField hits = new JTextField("", 8);
static JTextField item_confirm_code = new JTextField("", 8);
static JTextField item_confirmed = new JTextField("", 8);
static JTextField cost = new JTextField("", 8);
static JTextArea  item_description = new JTextArea("", 6, 40);
static JTextField item_id = new JTextField("", 28);
static JTextField sale_price = new JTextField("", 8);
static JTextField weight = new JTextField("", 8);
static JTextField item_listing_id = new JTextField("", 28);
static JTextField item_notes = new JTextField("", 28);
static JTextField item_package_d = new JTextField("", 8);
static JTextField item_package_l = new JTextField("", 8);
static JTextField item_package_w = new JTextField("", 8);
static JTextField item_part_number = new JTextField("", 28);
static JTextField title = new JTextField("", 28);
static JTextField item_title_url = new JTextField("", 28);
static JTextField item_type = new JTextField("", 8);
static JTextField item_search_1 = new JTextField("", 28);
static JTextField item_search_2 = new JTextField("", 28);
static JTextField item_search_3 = new JTextField("", 28);
static JTextField item_seller_id = new JTextField("", 28);
static JTextField item_site_url = new JTextField("", 28);
static JTextField picture_1 = new JTextField("", 28);
static JTextField item_total_on_hand = new JTextField("", 8);






JLabel header1_space = new JLabel("", JLabel.LEFT);
JLabel header2_space = new JLabel("", JLabel.LEFT);
JLabel header3_space = new JLabel("", JLabel.LEFT);


//int what_item_save = lm.what_item;


JButton update = new JButton("Update");

static JButton back = new JButton("<<<");
static JButton next = new JButton(">>>");












edit_item(){//****************************



	header1_space.setPreferredSize(new Dimension(390, 10));
	header2_space.setPreferredSize(new Dimension(390, 10));
	header3_space.setPreferredSize(new Dimension(390, 10));


	header1_l.setPreferredSize(new Dimension(118, 20));
	header2_l.setPreferredSize(new Dimension(118, 20));
	header3_l.setPreferredSize(new Dimension(118, 20));
	header4_l.setPreferredSize(new Dimension(118, 20));
	header5_l.setPreferredSize(new Dimension(118, 20));
	header6_l.setPreferredSize(new Dimension(118, 20));
	header7_l.setPreferredSize(new Dimension(118, 20));
	header8_l.setPreferredSize(new Dimension(118, 20));
	header9_l.setPreferredSize(new Dimension(118, 20));
	header10_l.setPreferredSize(new Dimension(118, 20));
	header11_l.setPreferredSize(new Dimension(118, 20));
	header12_l.setPreferredSize(new Dimension(118, 20));
	header13_l.setPreferredSize(new Dimension(118, 20));
	header14_l.setPreferredSize(new Dimension(118, 20));
	header15_l.setPreferredSize(new Dimension(118, 20));
	header16_l.setPreferredSize(new Dimension(118, 20));
	header17_l.setPreferredSize(new Dimension(118, 20));
	header18_l.setPreferredSize(new Dimension(118, 20));
	header19_l.setPreferredSize(new Dimension(118, 20));
	header20_l.setPreferredSize(new Dimension(118, 20));
	header21_l.setPreferredSize(new Dimension(118, 20));
	header22_l.setPreferredSize(new Dimension(118, 20));
	header23_l.setPreferredSize(new Dimension(118, 20));
	header24_l.setPreferredSize(new Dimension(118, 20));
	header25_l.setPreferredSize(new Dimension(118, 20));
	header26_l.setPreferredSize(new Dimension(118, 20));
	header27_l.setPreferredSize(new Dimension(118, 20));
	header28_l.setPreferredSize(new Dimension(118, 20));
	header29_l.setPreferredSize(new Dimension(118, 20));
	header30_l.setPreferredSize(new Dimension(118, 20));
	header31_l.setPreferredSize(new Dimension(118, 20));
	header32_l.setPreferredSize(new Dimension(118, 20));
	header33_l.setPreferredSize(new Dimension(118, 20));



	currency.setText("");
	currency.setBorder(BorderFactory.createLineBorder(whitex));

	custom_template.setText("");
	custom_template.setBorder(BorderFactory.createLineBorder(whitex));

	custom_1.setText("");
	custom_1.setBorder(BorderFactory.createLineBorder(whitex));

	custom_2.setText("");
	custom_2.setBorder(BorderFactory.createLineBorder(whitex));

	custom_3.setText("");
	custom_3.setBorder(BorderFactory.createLineBorder(whitex));

	item_errors.setText("");
	item_errors.setBorder(BorderFactory.createLineBorder(whitex));

	item_date_listed.setText("");
	item_date_listed.setBorder(BorderFactory.createLineBorder(whitex));

	item_date_listed_day.setText("");
	item_date_listed_day.setBorder(BorderFactory.createLineBorder(whitex));

	item_date_listed_int.setText("");
	item_date_listed_int.setBorder(BorderFactory.createLineBorder(whitex));

	hits.setText("");
	hits.setBorder(BorderFactory.createLineBorder(whitex));

	item_confirm_code.setText("");
	item_confirm_code.setBorder(BorderFactory.createLineBorder(whitex));

	item_confirmed.setText("");
	item_confirmed.setBorder(BorderFactory.createLineBorder(whitex));

	cost.setText("");
	cost.setBorder(BorderFactory.createLineBorder(whitex));

	item_description.setText("");
	item_description.setBorder(BorderFactory.createLineBorder(whitex));
	item_description.setWrapStyleWord(true);
	item_description.setLineWrap(true);

	sale_price.setText("");
	sale_price.setBorder(BorderFactory.createLineBorder(whitex));

	weight.setText("");
	weight.setBorder(BorderFactory.createLineBorder(whitex));

	item_listing_id.setText("");
	item_listing_id.setBorder(BorderFactory.createLineBorder(whitex));

	item_notes.setText("");
	item_notes.setBorder(BorderFactory.createLineBorder(whitex));

	item_package_d.setText("");
	item_package_d.setBorder(BorderFactory.createLineBorder(whitex));

	item_package_l.setText("");
	item_package_l.setBorder(BorderFactory.createLineBorder(whitex));

	item_package_w.setText("");
	item_package_w.setBorder(BorderFactory.createLineBorder(whitex));

	item_part_number.setText("");
	item_part_number.setBorder(BorderFactory.createLineBorder(whitex));

	title.setText("");
	title.setBorder(BorderFactory.createLineBorder(whitex));

	item_title_url.setText("");
	item_title_url.setBorder(BorderFactory.createLineBorder(whitex));

	item_type.setText("");
	item_type.setBorder(BorderFactory.createLineBorder(whitex));

	item_search_1.setText("");
	item_search_1.setBorder(BorderFactory.createLineBorder(whitex));

	item_search_2.setText("");
	item_search_2.setBorder(BorderFactory.createLineBorder(whitex));

	item_search_3.setText("");
	item_search_3.setBorder(BorderFactory.createLineBorder(whitex));

	item_site_url.setText("");
	item_site_url.setBorder(BorderFactory.createLineBorder(whitex));

	picture_1.setText("");
	picture_1.setBorder(BorderFactory.createLineBorder(whitex));

	item_seller_id.setText("");
	item_seller_id.setBorder(BorderFactory.createLineBorder(redx));
	item_seller_id.setBackground(redx);
	item_seller_id.setForeground(whitex);
	item_seller_id.setEditable(false);

	item_id.setText("");
	item_id.setBorder(BorderFactory.createLineBorder(redx));
	item_id.setBackground(redx);
	item_id.setForeground(whitex);
	item_id.setEditable(false);

	item_total_on_hand.setText("");
	item_total_on_hand.setBorder(BorderFactory.createLineBorder(whitex));



	JScrollPane scrollPaned = new JScrollPane(item_description);
	scrollPaned.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	scrollPaned.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	scrollPaned.setFont(new Font("Arial", Font.PLAIN, 14));






	JPanel jpk2 = new JPanel();
	jpk2.setPreferredSize(new Dimension(490, 620));
	jpk2.setBackground(krypton.jblue);//darkgray08
	jpk2.add(header24_l);		jpk2.add(title);
	jpk2.add(header23_l); 		jpk2.add(item_part_number);
	jpk2.add(header32_l);		jpk2.add(picture_1);
	jpk2.add(header2_l); 		jpk2.add(custom_template);
	jpk2.add(header1_space);
	jpk2.add(header1_l);		jpk2.add(currency);
	jpk2.add(header33_l);		jpk2.add(item_total_on_hand);
	jpk2.add(header13_l); 		jpk2.add(cost);
	jpk2.add(header16_l);		jpk2.add(sale_price);
	jpk2.add(header20_l);		jpk2.add(item_package_d);
	jpk2.add(header21_l);		jpk2.add(item_package_l);
	jpk2.add(header22_l);		jpk2.add(item_package_w);
	jpk2.add(header17_l); 		jpk2.add(weight);
	jpk2.add(header2_space);
	jpk2.add(scrollPaned);
	jpk2.add(header3_space);
	jpk2.add(header19_l);		jpk2.add(item_notes);
	jpk2.add(header27_l); 		jpk2.add(item_search_1);
	jpk2.add(header28_l);		jpk2.add(item_search_2);
	jpk2.add(header29_l);		jpk2.add(item_search_3);
	jpk2.add(header31_l);		jpk2.add(item_site_url);
	jpk2.add(header3_l);		jpk2.add(custom_1);
	jpk2.add(header4_l);		jpk2.add(custom_2);
	jpk2.add(header5_l);		jpk2.add(custom_3);
	jpk2.add(header30_l);		jpk2.add(item_seller_id);
	jpk2.add(header15_l);		jpk2.add(item_id);

	//jpk2.add(header6_l); 		jpk2.add(item_errors);
	//jpk2.add(header7_l);		jpk2.add(item_date_listed);
	//jpk2.add(header8_l);		jpk2.add(item_date_listed_day);
	//jpk2.add(header9_l);		jpk2.add(item_date_listed_int);
	//jpk2.add(header10_l);		jpk2.add(hits);
	//jpk2.add(header11_l);		jpk2.add(item_confirm_code);
	//jpk2.add(header12_l);		jpk2.add(item_confirmed);
	//jpk2.add(header18_l);		jpk2.add(item_listing_id);
	//jpk2.add(header25_l);		jpk2.add(item_title_url);
	//jpk2.add(header26_l);		jpk2.add(item_type);





	JPanel jpk2x = new JPanel();
	jpk2x.setPreferredSize(new Dimension(490, 620));
	jpk2x.setBackground(krypton.jblue);//darkgray08
	jpk2x.add(jpk2);


	JScrollPane scrollPaned_item = new JScrollPane(jpk2x);
	scrollPaned_item.setPreferredSize(new Dimension(690, 487));
	scrollPaned_item.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	scrollPaned_item.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	scrollPaned_item.setFont(new Font("Arial", Font.PLAIN, 14));






	back.setPreferredSize(new Dimension(105, 20));
	back.setOpaque(true);
	back.setBackground(krypton.jblue);//darkgray08
	back.setForeground(blackx);//darkgray08
	back.setToolTipText("Previous Token");
	back.addActionListener(this);

	next.setPreferredSize(new Dimension(105, 20));
	next.setOpaque(true);
	next.setBackground(krypton.jblue);//darkgray08
	next.setForeground(blackx);//darkgray08
	next.setToolTipText("Next Token");
	next.addActionListener(this);


	token_id = new JLabel("Token ID: 24999", JLabel.CENTER);
	token_id.setPreferredSize(new Dimension(118, 20));

	JLabel update_id = new JLabel("", JLabel.RIGHT);
	update_id.setPreferredSize(new Dimension(200, 20));


	update.setPreferredSize(new Dimension(105, 20));
	update.setOpaque(true);
	update.setBackground(krypton.jblue);//darkgray08
	update.setForeground(blackx);//darkgray08
	update.setToolTipText("Update token information");
	update.addActionListener(this);






	JPanel jpk3 = new JPanel();
	jpk3.setPreferredSize(new Dimension(690, 30));
	jpk3.setBackground(krypton.jblue);//darkgray08
	jpk3.add(back);
	jpk3.add(token_id);
	jpk3.add(next);
	jpk3.add(update_id);
	jpk3.add(update);


	krypton.build2.add(jpk3);
	krypton.build2.add(scrollPaned_item);


	show_token();


}//csv_loader_x1********************************************************













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








	static public void show_token(){

		System.out.println("Next");



		if(showid <= 0){back.setEnabled(false);}
		else{back.setEnabled(true);}

		if(showid >= (krypton.my_listings.size() -1)){next.setEnabled(false);}
		else{next.setEnabled(true);}

		System.out.println("showid " + showid);
		System.out.println("krypton.my_listings.size() " + krypton.my_listings.size());




		if(showid > -1 && showid < krypton.my_listings.size()){

			String req_id = krypton.my_listings.get(showid).toString();

			token_id.setText("Token ID: " + req_id);


			//get the token
			tokenx = get_token(req_id);


			currency.setEditable(true);
			custom_template.setEditable(true);
			custom_1.setEditable(true);
			custom_2.setEditable(true);
			custom_3.setEditable(true);
			item_errors.setEditable(true);
			item_date_listed.setEditable(true);
			item_date_listed_day.setEditable(true);
			item_date_listed_int.setEditable(true);
			hits.setEditable(true);
			item_confirm_code.setEditable(true);
			item_confirmed.setEditable(true);
			cost.setEditable(true);
			item_description.setEditable(true);
			//item_id.setEditable(true);
			sale_price.setEditable(true);
			weight.setEditable(true);
			item_listing_id.setEditable(true);
			item_notes.setEditable(true);
			item_package_d.setEditable(true);
			item_package_l.setEditable(true);
			item_package_w.setEditable(true);
			item_part_number.setEditable(true);
			title.setEditable(true);
			item_title_url.setEditable(false);
			item_type.setEditable(true);
			item_search_1.setEditable(true);
			item_search_2.setEditable(true);
			item_search_3.setEditable(true);
			//item_seller_id.setEditable(true);
			item_site_url.setEditable(true);
			picture_1.setEditable(true);
			item_total_on_hand.setEditable(true);

			currency.setText(tokenx[6]);
			custom_template.setText(tokenx[7]);
			custom_1.setText(tokenx[8]);
			custom_2.setText(tokenx[9]);
			custom_3.setText(tokenx[10]);
			item_errors.setText(tokenx[11]);
			item_date_listed.setText(tokenx[12]);
			item_date_listed_day.setText(tokenx[13]);
			item_date_listed_int.setText(tokenx[14]);
			hits.setText(tokenx[15]);
			item_confirm_code.setText(tokenx[16]);
			item_confirmed.setText(tokenx[17]);
			cost.setText(tokenx[18]);
			item_description.setText(tokenx[19]);
			item_id.setText(tokenx[0]);
			sale_price.setText(tokenx[21]);
			weight.setText(tokenx[22]);
			item_listing_id.setText(tokenx[30]);
			item_notes.setText(tokenx[24]);
			item_package_d.setText(tokenx[25]);
			item_package_l.setText(tokenx[26]);
			item_package_w.setText(tokenx[27]);
			item_part_number.setText(tokenx[28]);
			title.setText(tokenx[29]);
			item_title_url.setText(tokenx[30]);
			item_type.setText(tokenx[31]);
			item_search_1.setText(tokenx[32]);
			item_search_2.setText(tokenx[33]);
			item_search_3.setText(tokenx[34]);
			item_seller_id.setText(tokenx[60]);
			item_site_url.setText(tokenx[36]);
			picture_1.setText(tokenx[37]);
			item_total_on_hand.setText(tokenx[38]);

		}//if
		else{

			currency.setEditable(false);
			custom_template.setEditable(false);
			custom_1.setEditable(false);
			custom_2.setEditable(false);
			custom_3.setEditable(false);
			item_errors.setEditable(false);
			item_date_listed.setEditable(false);
			item_date_listed_day.setEditable(false);
			item_date_listed_int.setEditable(false);
			hits.setEditable(false);
			item_confirm_code.setEditable(false);
			item_confirmed.setEditable(false);
			cost.setEditable(false);
			item_description.setEditable(false);
			item_id.setEditable(false);
			sale_price.setEditable(false);
			weight.setEditable(false);
			item_listing_id.setEditable(false);
			item_notes.setEditable(false);
			item_package_d.setEditable(false);
			item_package_l.setEditable(false);
			item_package_w.setEditable(false);
			item_part_number.setEditable(false);
			title.setEditable(false);
			item_title_url.setEditable(false);
			item_type.setEditable(false);
			item_search_1.setEditable(false);
			item_search_2.setEditable(false);
			item_search_3.setEditable(false);
			item_seller_id.setEditable(false);
			item_site_url.setEditable(false);
			picture_1.setEditable(false);
			item_total_on_hand.setEditable(false);

		}//**

	}//*****************








	public void update_yes(){

		int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to send changes to the network?");
 		if(response == 0){

			updatet();

		}//***************

	}//**********************








	public void updatet(){

	System.out.println("Update");

        //update the noose
		tokenx[3] = Long.toString( System.currentTimeMillis() );

		tokenx[4] = krypton.settingsx[5];

		tokenx[6] = currency.getText();
		tokenx[7] = custom_template.getText();
		tokenx[8] = custom_1.getText();
		tokenx[9] = custom_2.getText();
		tokenx[10] = custom_3.getText();
		tokenx[11] = item_errors.getText();
		tokenx[12] = item_date_listed.getText();
		tokenx[13] = item_date_listed_day.getText();
		tokenx[14] = item_date_listed_int.getText();
		tokenx[15] = hits.getText();
		tokenx[16] = item_confirm_code.getText();
		tokenx[17] = item_confirmed.getText();
		tokenx[18] = cost.getText();
		tokenx[19] = item_description.getText();
		tokenx[20] = item_id.getText();
		tokenx[21] = sale_price.getText();
		tokenx[22] = weight.getText();

		tokenx[24] = item_notes.getText();
		tokenx[25] = item_package_d.getText();
		tokenx[26] = item_package_l.getText();
		tokenx[27] = item_package_w.getText();
		tokenx[28] = item_part_number.getText();
		tokenx[29] = title.getText();
		tokenx[30] = item_title_url.getText();
		tokenx[31] = item_type.getText();
		tokenx[32] = item_search_1.getText();
		tokenx[33] = item_search_2.getText();
		tokenx[34] = item_search_3.getText();

		tokenx[36] = item_site_url.getText();
		tokenx[37] = picture_1.getText();
		tokenx[38] = item_total_on_hand.getText();



		//to help with search
		tokenx[30] = tokenx[29].toLowerCase();

		//base 58
		tokenx[60] = krypton.base58_id;


		//seller info
		tokenx[63] = krypton.settingsx[11];//name
		tokenx[64] = krypton.settingsx[12];//last
		tokenx[54] = krypton.settingsx[13];//address
		tokenx[55] = krypton.settingsx[14];//address2
		tokenx[56] = krypton.settingsx[15];//city
		tokenx[57] = krypton.settingsx[16];//state
		tokenx[58] = krypton.settingsx[17];//zip
		tokenx[59] = krypton.settingsx[18];//country

		tokenx[39] = krypton.settingsx[19];//btc
		tokenx[62] = krypton.settingsx[20];//email
		tokenx[66] = krypton.settingsx[21];//phone
		tokenx[68] = krypton.settingsx[22];//website


		//sign

		try{


				krypton_trim trimx = new krypton_trim();
                tokenx = trimx.trim(tokenx);



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




                byte[] keyxb3 = Base64.decode(krypton.settingsx[5]);
                System.out.println("settingsx[5] " + krypton.settingsx[5]);

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

                System.out.println("tokenx[1] " + tokenx[1]);
				


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
			obj.put("request", "set_edit_block");
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

	if(event.getSource() == back)             {showid--; show_token();}
	if(event.getSource() == next)             {showid++; show_token();}
	if(event.getSource() == update)           {update_yes();}



}//********************************************







}//last****************************************