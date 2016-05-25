import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;  
import java.lang.Object.*;  
import java.net.*;

import java.awt.Toolkit;

import java.security.MessageDigest;

import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

import java.awt.Toolkit;
import java.util.Timer;
import java.util.TimerTask;

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

import org.spongycastle.util.encoders.Base64;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONValue;



import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;





public class edit_view_item extends JFrame implements ActionListener{//************************************************************************

static String tokenx[] = new String[krypton.listing_size];

static int showid = 0;

// create jeditorpane
static JEditorPane jEditorPane = new JEditorPane();
static JScrollPane scrollPane;
static JPanel jpk2x;

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


static String pic_url = new String("");
String cx0 = new String("");
String cx1 = new String("");
String cx2 = new String("");

static JTextField show_idx = new JTextField("100000", 8);

static JLabel token_id;


static JLabel currency = new JLabel("", JLabel.LEFT);
static JLabel custom_template = new JLabel("", JLabel.LEFT);
static JLabel custom_1 = new JLabel("", JLabel.LEFT);
static JLabel custom_2 = new JLabel("", JLabel.LEFT);
static JLabel custom_3 = new JLabel("", JLabel.LEFT);
static JLabel item_errors = new JLabel("", JLabel.LEFT);
static JLabel item_date_listed = new JLabel("", JLabel.LEFT);
static JLabel item_date_listed_day = new JLabel("", JLabel.LEFT);
static JLabel item_date_listed_int = new JLabel("", JLabel.LEFT);
static JLabel hits = new JLabel("", JLabel.LEFT);
static JLabel item_confirm_code = new JLabel("", JLabel.LEFT);
static JLabel item_confirmed = new JLabel("", JLabel.LEFT);
static JLabel cost = new JLabel("", JLabel.LEFT);
static JLabel item_description = new JLabel("", JLabel.LEFT);
static JLabel item_id = new JLabel("", JLabel.LEFT);
static JLabel sale_price = new JLabel("", JLabel.LEFT);
static JLabel weight = new JLabel("", JLabel.LEFT);
static JLabel item_listing_id = new JLabel("", JLabel.LEFT);
static JLabel item_notes = new JLabel("", JLabel.LEFT);
static JLabel item_package_d = new JLabel("", JLabel.LEFT);
static JLabel item_package_l = new JLabel("", JLabel.LEFT);
static JLabel item_package_w = new JLabel("", JLabel.LEFT);
static JLabel item_package_dlw = new JLabel("", JLabel.LEFT);
static JLabel item_part_number = new JLabel("", JLabel.LEFT);
static JLabel title = new JLabel("", JLabel.LEFT);
static JLabel item_title_url = new JLabel("", JLabel.LEFT);
static JLabel item_type = new JLabel("", JLabel.LEFT);
static JLabel item_search_1 = new JLabel("", JLabel.LEFT);
static JLabel item_search_2 = new JLabel("", JLabel.LEFT);
static JLabel item_search_3 = new JLabel("", JLabel.LEFT);
static JLabel item_seller_id = new JLabel("", JLabel.LEFT);
static JLabel item_seller_email = new JLabel("", JLabel.LEFT);
static JLabel item_seller_phone = new JLabel("", JLabel.LEFT);
static JLabel item_seller_name = new JLabel("", JLabel.LEFT);
static JLabel item_seller_url = new JLabel("", JLabel.LEFT);
static JLabel item_seller_notes = new JLabel("", JLabel.LEFT);
static JLabel item_seller_location = new JLabel("", JLabel.LEFT);
static JLabel item_site_url = new JLabel("", JLabel.LEFT);
static JLabel picture_1 = new JLabel("", JLabel.LEFT);
static JLabel item_total_on_hand = new JLabel("", JLabel.LEFT);

static JLabel item_verify = new JLabel("Verified", JLabel.RIGHT);

static Toolkit toolkit;
static Timer xtimerx;//class loop.




JLabel header1_space = new JLabel("", JLabel.LEFT);
JLabel header2_space = new JLabel("-Contact Details-", JLabel.LEFT);
JLabel header3_space = new JLabel("", JLabel.LEFT);


//int what_item_save = lm.what_item;


JButton update = new JButton("View");

static JButton back = new JButton("<<<");
static JButton next = new JButton(">>>");

static JButton item_picture = new JButton("");


static Image no_image;







edit_view_item(){//****************************




	no_image = new ImageIcon(this.getClass().getResource("images/download.jpg")).getImage();




	header1_space.setPreferredSize(new Dimension(430, 10));
	header2_space.setPreferredSize(new Dimension(630, 20));
	header3_space.setPreferredSize(new Dimension(630, 10));



	show_idx.setText("100000");
	show_idx.setBorder(BorderFactory.createLineBorder(whitex));





	currency.setText("BTC");
	currency.setPreferredSize(new Dimension(300, 30));
	currency.setFont(krypton.f_02);
	currency.setForeground(krypton.blackx);

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
	item_description.setPreferredSize(new Dimension(630, 500));
	item_description.setVerticalAlignment(JLabel.TOP);
	item_description.setVerticalTextPosition(JLabel.TOP);
	//item_description.setWrapStyleWord(true);
	//item_description.setLineWrap(true);

	sale_price.setText("Price");
	sale_price.setPreferredSize(new Dimension(430, 20));
	sale_price.setFont(krypton.f_02);
	sale_price.setForeground(krypton.blackx);

	weight.setText("Weight Kg");
	weight.setPreferredSize(new Dimension(430, 20));
	weight.setForeground(krypton.blackx);

	item_listing_id.setText("");
	item_listing_id.setPreferredSize(new Dimension(430, 20));

	item_notes.setText("");
	item_notes.setBorder(BorderFactory.createLineBorder(whitex));

	item_package_dlw.setText("");
	item_package_dlw.setPreferredSize(new Dimension(430, 20));
	item_package_dlw.setForeground(krypton.blackx);

	item_part_number.setText("Part number");
	item_part_number.setPreferredSize(new Dimension(430, 20));
	item_part_number.setForeground(krypton.blackx);

	title.setText("Title");
	title.setPreferredSize(new Dimension(430, 30));
	title.setFont(krypton.f_02);
	title.setForeground(krypton.blackx);

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

	item_seller_notes.setPreferredSize(new Dimension(630, 15));
	item_seller_notes.setForeground(krypton.blackx);

	item_seller_id.setPreferredSize(new Dimension(630, 15));
	item_seller_id.setForeground(krypton.blackx);

	item_seller_email.setPreferredSize(new Dimension(630, 15));
	item_seller_email.setForeground(krypton.blackx);

	item_seller_url.setPreferredSize(new Dimension(630, 15));
	item_seller_url.setForeground(krypton.blackx);

	item_seller_location.setPreferredSize(new Dimension(630, 15));
	item_seller_location.setForeground(krypton.blackx);

	item_seller_name.setPreferredSize(new Dimension(630, 15));
	item_seller_name.setForeground(krypton.blackx);

	item_seller_phone.setPreferredSize(new Dimension(630, 15));
	item_seller_phone.setForeground(krypton.blackx);


	item_verify.setPreferredSize(new Dimension(100, 20));
	item_verify.setForeground(krypton.blackx);
	item_verify.setIcon(krypton.imx0);
	item_verify.setHorizontalTextPosition(SwingConstants.LEFT);
	item_verify.setToolTipText("Does the info in this token match its public key?");


	item_id.setText("");
	item_id.setBorder(BorderFactory.createLineBorder(redx));
	item_id.setBackground(redx);
	item_id.setForeground(whitex);

	item_total_on_hand.setText("");
	item_total_on_hand.setPreferredSize(new Dimension(430, 30));
	item_total_on_hand.setForeground(krypton.blackx);




 













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


	token_id = new JLabel("Viewing token ID: ", JLabel.CENTER);
	token_id.setPreferredSize(new Dimension(118, 20));
	JLabel update_id = new JLabel("", JLabel.RIGHT);
	update_id.setPreferredSize(new Dimension(200, 20));

	item_picture.setPreferredSize(new Dimension(200, 200));
	item_picture.setBorderPainted(false);
	item_picture.setBackground(krypton.whitex);//darkgray08
	item_picture.addActionListener(this);

	Icon aicon = new ImageIcon(no_image);
	item_picture.setIcon(aicon);



	update.setPreferredSize(new Dimension(105, 20));
	update.setOpaque(true);
	update.setBackground(krypton.jblue);//darkgray08
	update.setForeground(blackx);//darkgray08
	update.setToolTipText("View token information");
	update.addActionListener(this);





	JPanel jpk2 = new JPanel();
	jpk2.setPreferredSize(new Dimension(445, 200));
	jpk2.setBackground(krypton.jblue);//darkgray08
	jpk2.add(title);
	jpk2.add(sale_price);
	jpk2.add(header1_space);
	jpk2.add(item_part_number);
	jpk2.add(item_total_on_hand);
	jpk2.add(weight);
	jpk2.add(item_package_dlw);

	JPanel jpk2b = new JPanel();
	jpk2b.setPreferredSize(new Dimension(650, 196));
	jpk2b.setBackground(krypton.whitex);//darkgray08
	jpk2b.add(header2_space);
	jpk2b.add(item_seller_name);
	jpk2b.add(item_seller_notes);
	jpk2b.add(item_seller_id);
	jpk2b.add(item_seller_email);
	jpk2b.add(item_seller_phone);
	jpk2b.add(item_seller_url);
	jpk2b.add(item_seller_location);






        
    //make it read-only
    jEditorPane.setEditable(false);
    //jEditorPane.setSize(630, Integer.MAX_VALUE);

    //create a scrollpane; modify its attributes as desired
    scrollPane = new JScrollPane(jEditorPane);
    scrollPane.setPreferredSize(new Dimension(650, 520));

    //add an html editor kit
    HTMLEditorKit kit = new HTMLEditorKit();
    jEditorPane.setEditorKit(kit);
        
	StyleSheet styleSheet = kit.getStyleSheet();
    styleSheet.addRule("body {color:#000; font-family:times; margin: 4px; }");
    styleSheet.addRule("h1 {color: blue;}");
    styleSheet.addRule("h2 {color: blue;}");
    styleSheet.addRule("pre {font : 10px monaco; color : black; background-color : #fafafa; }");

    //create a document, set it on the jeditorpane, then add the html
    Document doc = kit.createDefaultDocument();
    jEditorPane.setDocument(doc);
    jEditorPane.setText("html");





	jpk2x = new JPanel();
	jpk2x.setPreferredSize(new Dimension(490, 950));
	jpk2x.setBackground(krypton.jblue);//darkgray08
	jpk2x.add(item_picture);        jpk2x.add(jpk2);
	jpk2x.add(scrollPane); 
	jpk2x.add(jpk2b);


	JScrollPane scrollPaned_item = new JScrollPane(jpk2x);
	scrollPaned_item.setPreferredSize(new Dimension(690, 487));
	scrollPaned_item.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	scrollPaned_item.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	scrollPaned_item.setFont(new Font("Arial", Font.PLAIN, 14));







	JPanel jpk3 = new JPanel();
	jpk3.setPreferredSize(new Dimension(690, 30));
	jpk3.setBackground(krypton.jblue);//darkgray08
	jpk3.add(back);
	jpk3.add(token_id);
	jpk3.add(show_idx);
	jpk3.add(update);
	jpk3.add(next);
	jpk3.add(item_verify);


	krypton.build4.add(jpk3);
	krypton.build4.add(scrollPaned_item);


	showid = 100000;



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

		if(showid <= 100000){back.setEnabled(false);}
		else{back.setEnabled(true);}

		if(showid >= (100000 + (krypton.hard_token_limit -1))){next.setEnabled(false);}
		else{next.setEnabled(true);}

		show_idx.setText(Integer.toString(showid));


		tokenx = get_token(Integer.toString(showid));


		verify(tokenx);

		currency.setText(tokenx[13]);
		custom_template.setText("");
		custom_1.setText("");
		custom_2.setText("");
		custom_3.setText("");
		item_errors.setText("");
		item_date_listed.setText("");
		item_date_listed_day.setText("");
		item_date_listed_int.setText("");


		int sizes = 0;

		try{

			//item_description.setText("<html>" + tokenx[26] + "</html>");
			jEditorPane.setText("<html>" + tokenx[19] + "</html>");
			System.out.println("size " + jEditorPane.getSize());
			System.out.println("size " + scrollPane.getSize());
			System.out.println("size " + jEditorPane.getPreferredSize());
			System.out.println("size " + scrollPane.getPreferredSize());
			Dimension s = jEditorPane.getPreferredSize();

			Double d = s.getHeight();
			sizes = Integer.valueOf(d.intValue());

			System.out.println("size 2 " + sizes);
			scrollPane.setPreferredSize(new Dimension(650, (sizes + 40)));
			jpk2x.setPreferredSize(new Dimension(490, (sizes + 470)));
			//jEditorPane.setSize(630, Integer.MAX_VALUE);
	
		}catch(Exception e){e.printStackTrace(); sizes = 650;}


		sale_price.setText("Price: " + tokenx[21] + " " + tokenx[6]);
		weight.setText("Weight: " + tokenx[22] + " kg");
		item_listing_id.setText("");
		item_notes.setText(tokenx[22]);
		item_package_dlw.setText("Dimensions: " + tokenx[25] + "x" + tokenx[26] + "x" + tokenx[27] + " cm");
		item_part_number.setText("Part Number: " + tokenx[28]);
		title.setText(tokenx[29]);
		title.setToolTipText(tokenx[29]);
		item_type.setText("");
		item_search_1.setText("");
		item_search_2.setText("");
		item_search_3.setText("");
		item_site_url.setText("");
		item_seller_name.setText("Seller name: " + tokenx[63] + " " + tokenx[64]);
		item_seller_id.setText("Seller ID: " + tokenx[60]);
		item_seller_notes.setText("Seller note: " + tokenx[65]);
		item_seller_email.setText("Seller email: " + tokenx[62]);
		item_seller_phone.setText("Seller phone: " + tokenx[66]);
		item_seller_url.setText("Seller website: " + tokenx[68]);
		item_seller_location.setText("Seller country location: " + tokenx[59]);


		item_description.setMinimumSize(new Dimension(630, 100));
		//item_description.setPreferedSize(new Dimension(630, 300));
		item_description.setMaximumSize(new Dimension(630, 500));


		item_id.setText("");
		item_total_on_hand.setText("(" + tokenx[38] + ") Available");

		pic_url = tokenx[37];

		toolkit = Toolkit.getDefaultToolkit();
    	xtimerx = new Timer();
    	xtimerx.schedule(new RemindTask_pics(), 0);


	}//*****************************






	public static void verify(String[] itemx){



        boolean testx1 = false;
        boolean testx2 = false;

        String build_hash = new String();
        build_hash = build_hash + itemx[0];
       	for(int loop1 = 3; loop1 < krypton.listing_size; loop1++){//***********

            build_hash = build_hash + itemx[loop1];

        }//********************************************************************

        System.out.println("ID " + itemx[0]);
        System.out.println("build_hash " + build_hash);



        //test item
        try{

            byte[] sha256_1w = MessageDigest.getInstance("SHA-256").digest(build_hash.getBytes());

            System.out.println("TESTX " + Base64.toBase64String(sha256_1w));
            System.out.println("GIVEN " + itemx[1]);

            if(Base64.toBase64String(sha256_1w).equals(itemx[1])){testx1 = true;}
            else{System.out.println("Bad HASH");}


                byte[] keyxb3 = Base64.decode(itemx[4]);

                X509EncodedKeySpec keySpecx3 = new X509EncodedKeySpec(keyxb3);
                KeyFactory factx3 = KeyFactory.getInstance("RSA");
                PublicKey pubx3 = factx3.generatePublic(keySpecx3);
                Arrays.fill(keyxb3, (byte) 0);

                Signature sigpk3 = Signature.getInstance("SHA1WithRSA");//MD5WithRSA
                byte[] messagex3 = Base64.toBase64String(sha256_1w).getBytes("UTF8");

                byte[] signatureBytesx3 = Base64.decode(itemx[2]);

                sigpk3.initVerify(pubx3);
                sigpk3.update(messagex3);

                //System.out.println(sigpk3.verify(signatureBytesx3));


            if(sigpk3.verify(signatureBytesx3)){testx2 = true;}
            else{System.out.println("Bad SIG");}

        }catch(Exception e){e.printStackTrace();}



        if(testx1 && testx2){item_verify.setIcon(krypton.imx1);}
        else{item_verify.setIcon(krypton.imx0);}



	}//*************************













	public void update_yes(){

		int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to send changes to the network?");
 		if(response == 0){



		JOptionPane.showMessageDialog(null, "Updated!");

		}//***************



	}//**********************













	static class RemindTask_pics extends TimerTask{
	Runtime rxrunti = Runtime.getRuntime();

	public void run(){//************************************************************************************

	try {



		Icon aicons = new ImageIcon(no_image);
		item_picture.setIcon(aicons);



    	URL url = new URL(pic_url);

		Image image = java.awt.Toolkit.getDefaultToolkit().getDefaultToolkit().createImage(url);

		Icon aicon = new ImageIcon(image);
		item_picture.setIcon(aicon);


		BufferedImage resizedImage = new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(image, 0, 0, 200, 200, null);
		//g.fillRect(10, 10, 10, 10);

		image = java.awt.Toolkit.getDefaultToolkit().getDefaultToolkit().createImage(resizedImage.getSource());

		aicon = new ImageIcon(image);
		item_picture.setIcon(aicon);

	}catch (Exception e) {

		//e.printStackTrace(); 
		System.out.println("Error Cannot download image!");

		Icon aicon = new ImageIcon(no_image);
		item_picture.setIcon(aicon);


	}//catch************* 

	}//runx***************************************************************************************************
    }//remindtask















	//handel all the button clicks. 
	public void actionPerformed(ActionEvent event){

		if(event.getSource() == back)             {showid--; show_token();}
		if(event.getSource() == next)             {showid++; show_token();}
		if(event.getSource() == update)           {showid = Integer.parseInt(show_idx.getText()); show_token();}

	}//********************************************







}//last****************************************