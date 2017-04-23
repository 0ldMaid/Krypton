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

import org.spongycastle.util.encoders.Base64;





public class edit_seller_account extends JFrame implements ActionListener{//************************************************************************

static String tokenx[] = new String[network.listing_size];

static int showid = 0;


JLabel headerb1_l = new JLabel("Seller Account", JLabel.LEFT);
static JLabel headerb2_l = new JLabel("<html>This info will be inserted into each of your tokens as you update them. You do not have to provide any info here but it might be hard for buyers to contact you if you do not provide something.</html>", JLabel.LEFT);
static JLabel headerb3_l = new JLabel("Your Account ID: ", JLabel.LEFT);
JLabel headerb4_l = new JLabel("", JLabel.LEFT);

JLabel header1_l = new JLabel("First Name", JLabel.RIGHT);
JLabel header2_l = new JLabel("Last Name", JLabel.RIGHT);
JLabel header3_l = new JLabel("Address", JLabel.RIGHT);
JLabel header4_l = new JLabel("Address2", JLabel.RIGHT);
JLabel header5_l = new JLabel("City", JLabel.RIGHT);
JLabel header6_l = new JLabel("State", JLabel.RIGHT);
JLabel header7_l = new JLabel("Zip", JLabel.RIGHT);
JLabel header8_l = new JLabel("County", JLabel.RIGHT);
JLabel header9_l = new JLabel("Bitcoin Address", JLabel.RIGHT);
JLabel header10_l = new JLabel("E-Mail", JLabel.RIGHT);
JLabel header11_l = new JLabel("Phone", JLabel.RIGHT);
JLabel header12_l = new JLabel("Website", JLabel.RIGHT);



JLabel headerb5_l = new JLabel("", JLabel.LEFT);
JLabel headerb6_l = new JLabel("", JLabel.LEFT);


JButton get_id = new JButton("Get ID");
JButton savex = new JButton("Save");

static JTextField btc_id = new JTextField("", 20);
static JTextField email = new JTextField("", 20);
static JTextField phone = new JTextField("", 20);
static JTextField website = new JTextField("", 20);

static JTextField name_first = new JTextField("", 20);
static JTextField name_last = new JTextField("", 20);
static JTextField address = new JTextField("", 20);
static JTextField address2 = new JTextField("", 20);
static JTextField city = new JTextField("", 20);
static JTextField state = new JTextField("", 20);
static JTextField zip = new JTextField("", 20);
static JTextField country = new JTextField("", 20);

JLabel header1_space = new JLabel("", JLabel.LEFT);
JLabel header2_space = new JLabel("", JLabel.LEFT);
JLabel header3_space = new JLabel("", JLabel.LEFT);





edit_seller_account(){//****************************





	//int what_item_save = lm.what_item;

	get_id.setPreferredSize(new Dimension(105, 20));
	get_id.setOpaque(true);
	get_id.setBackground(network.jgray);//darkgray08
	get_id.setForeground(Color.BLACK);//darkgray08
	get_id.setToolTipText("Stop or Start mining");
	get_id.addActionListener(this);



	savex.setForeground(Color.BLACK);//darkgray08
	savex.setBackground(network.jgray);//darkgray08
	savex.addActionListener(this);


	header1_l.setPreferredSize(new Dimension(100, 20));
	header2_l.setPreferredSize(new Dimension(100, 20));
	header3_l.setPreferredSize(new Dimension(100, 20));
	header4_l.setPreferredSize(new Dimension(100, 20));
	header5_l.setPreferredSize(new Dimension(100, 20));
	header6_l.setPreferredSize(new Dimension(100, 20));
	header7_l.setPreferredSize(new Dimension(100, 20));
	header8_l.setPreferredSize(new Dimension(100, 20));
	header9_l.setPreferredSize(new Dimension(100, 20));
	header10_l.setPreferredSize(new Dimension(100, 20));
	header11_l.setPreferredSize(new Dimension(100, 20));
	header12_l.setPreferredSize(new Dimension(100, 20));

	headerb1_l.setPreferredSize(new Dimension(355, 20));

	headerb2_l.setFont(network.f_00);
	headerb2_l.setVerticalAlignment(JLabel.TOP);
	headerb2_l.setVerticalTextPosition(JLabel.TOP);
	headerb2_l.setPreferredSize(new Dimension(355, 50));

	headerb3_l.setPreferredSize(new Dimension(355, 20));
	headerb4_l.setPreferredSize(new Dimension(355, 20));
	headerb5_l.setPreferredSize(new Dimension(355, 10));
	headerb6_l.setPreferredSize(new Dimension(255, 20));

	//System.out.println("base58_id " + network.base58_id);



	headerb3_l.setFont(network.f_00);



	name_first.setBorder(BorderFactory.createLineBorder(Color.WHITE));
	name_last.setBorder(BorderFactory.createLineBorder(Color.WHITE));
	address.setBorder(BorderFactory.createLineBorder(Color.WHITE));
	address2.setBorder(BorderFactory.createLineBorder(Color.WHITE));
	city.setBorder(BorderFactory.createLineBorder(Color.WHITE));
	state.setBorder(BorderFactory.createLineBorder(Color.WHITE));
	zip.setBorder(BorderFactory.createLineBorder(Color.WHITE));
	country.setBorder(BorderFactory.createLineBorder(Color.WHITE));
	btc_id.setBorder(BorderFactory.createLineBorder(Color.WHITE));
	email.setBorder(BorderFactory.createLineBorder(Color.WHITE));
	phone.setBorder(BorderFactory.createLineBorder(Color.WHITE));
	website.setBorder(BorderFactory.createLineBorder(Color.WHITE));



	JPanel transferp = new JPanel();
	transferp.setLayout(new FlowLayout());
	transferp.setBackground(network.jgray);//darkgray08
	transferp.setPreferredSize(new Dimension(400, 470));
	transferp.add(headerb1_l);
	transferp.add(headerb2_l);
	//transferp.add(headerb3_l);
	transferp.add(header1_l);	   transferp.add(name_first);
	transferp.add(header2_l);	   transferp.add(name_last);
	transferp.add(header3_l);	   transferp.add(address);
	transferp.add(header4_l);	   transferp.add(address2);
	transferp.add(header5_l);	   transferp.add(city);
	transferp.add(header6_l);	   transferp.add(state);
	transferp.add(header7_l);	   transferp.add(zip);
	transferp.add(header8_l);	   transferp.add(country);
	transferp.add(header9_l);	   transferp.add(btc_id);
	transferp.add(header10_l);	   transferp.add(email);
	transferp.add(header11_l);	   transferp.add(phone);
	transferp.add(header12_l);	   transferp.add(website);
	transferp.add(headerb5_l);
	transferp.add(headerb6_l);     transferp.add(savex);

	



	network.build5.add(transferp);


	show_info();


}//csv_loader_x1********************************************************






	static public void show_info(){

		System.out.println("Show");

		name_first.setText(network.settingsx[11]);
		name_last.setText(network.settingsx[12]);
		address.setText(network.settingsx[13]);
		address2.setText(network.settingsx[14]);
		city.setText(network.settingsx[15]);
		state.setText(network.settingsx[16]);
		zip.setText(network.settingsx[17]);
		country.setText(network.settingsx[18]);
		btc_id.setText(network.settingsx[19]);
		email.setText(network.settingsx[20]);
		phone.setText(network.settingsx[21]);
		website.setText(network.settingsx[22]);

	}//****************************


	static public void save_info(){

		System.out.println("Save");

		network.settingsx[11] = name_first.getText();
		network.settingsx[12] = name_last.getText();
		network.settingsx[13] = address.getText();
		network.settingsx[14] = address2.getText();
		network.settingsx[15] = city.getText();
		network.settingsx[16] = state.getText();
		network.settingsx[17] = zip.getText();
		network.settingsx[18] = country.getText();
		network.settingsx[19] = btc_id.getText();
		network.settingsx[20] = email.getText();
		network.settingsx[21] = phone.getText();
		network.settingsx[22] = website.getText();

		krypton_database_save savex = new krypton_database_save();

	}//****************************











//handel all the button clicks. 
public void actionPerformed(ActionEvent event){

	if(event.getSource() == savex){save_info();}

}//********************************************







}//last****************************************