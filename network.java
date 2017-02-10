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

import javax.swing.SwingConstants;





public class network extends JFrame implements ActionListener{

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
static krypton_database_driver systemx;

static boolean startm_yn = true;
static boolean program_ready = false;
static boolean blocks_uptodate = false;

static String programst = new String("START");

static List<String> network_list = new ArrayList<String>();//the list of your peers.
static List<String> my_listings = new ArrayList<String>();//get my listing ids.
static String settingsx[] = new String[23];//settings database.
static String item_layout[];//get a few blocks to test.
static String block_difficulty_listx[];//get a few blocks to test.
static String block_date_listx[];//get a few blocks to test.
static String last_block_ql[];//get a few blocks to test.
static String html_block_ql[];//quickload for html pages.


static String base58_id = new String("");//your base 58 key

static String versionx = new String("1.2.4");
static String idx = new String("");
static String xtypex = new String("user");
static String last_block_timestamp = new String("");
static String last_block_id = new String("");
static String last_block_idx = new String("");
static String last_block_mining_idx = new String("");
static String prev_block_mining_idx = new String("");
static String last_unconfirmed_idx = new String("");
static String ltc_mining_speed = new String("Development Mining");
static String buffered_mining_block = new String("");//the mining block that is ready to send.
static String buffered_listing_block = new String("");//the mining block that is ready to send.


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


int ix0 = 0;
int ix1 = 0;
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
static int peer_port = 55544;
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
static int connection_active = 0;//we have a real connection to a host not just a tor start.
static int internet_access = 0;//is the standard internet on?
static int client = 1;//is the client on or off.
static int server = 1;//is the server on or off.
static int xmining = 1;//is mining on or off.
static int mining_status = 0;//is the program mining or not.
static int mining_speed = 1;//time between hashes.
static int blocks_verified = 0;//is mining on or off.
static int block_difficulty_reset = 100;//how many blocks before reset.
static int block_difficulty_test = 0;//test for enough blocks to build test > difficulty reset...
static int hard_token_limit = 25000;//total number of tokens to allow.
static int target_block_speed = 300000;//target speed 300 seconds.
static int target_block_adjustment = 1;//percent
static int confirm_before_delete = 25000;//how many confirmations before we delete the old blocks.
static int database_in_use = 0;//already working.
static int tor_in_use = 0;//already working.
static int no_peers_time = 0;//how long since last peer connection.
static int package_block_size = 20;//how many blocks to send per package.
static int send_buffer_size = 0;//how many blocks are in the send buffer.
static int website_hits = 0;//number of pages requested.
static int website_searches = 0;//number of search requests.
static int mining_block_ready = 0;//if the program has a block that is ready to send.


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


//krypton

JMenu fileMenu = new JMenu("File");
JMenu editMenu = new JMenu("Edit");
JMenu toolMenu = new JMenu("Tools");
JMenu databaseMenu = new JMenu("Database");
JMenu accountMenu = new JMenu("Account");
JMenu spaceMenu = new JMenu("|");
JMenu nodeMenu = new JMenu("Nodes");

JMenuItem exit_save = new JMenuItem("Save");
JMenuItem exit = new JMenuItem("Exit (Shutdown)");

JMenuItem edit_token = new JMenuItem("Edit listing");
JMenuItem edit_get_ids = new JMenuItem("Get my listing IDs");

JMenuItem tools_vote = new JMenuItem("Vote");
JMenuItem tools_new_keys = new JMenuItem("Build new keypair");
JMenuItem tools_print_blocks = new JMenuItem("Print blocks");

JMenuItem database_test = new JMenuItem("Test");

ButtonGroup groupd = new ButtonGroup();
JRadioButtonMenuItem all_yes = new JRadioButtonMenuItem("Verify ALL blocks");
JRadioButtonMenuItem x100_yes = new JRadioButtonMenuItem("Verify 100 blocks");


JMenuItem account_public_key = new JMenuItem("Get public key");
JMenuItem account_private_key = new JMenuItem("Get private key");
JMenuItem account_sign = new JMenuItem("Sign message");
JMenuItem account_import = new JMenuItem("Import private key");

JMenuItem node_import = new JMenuItem("Import node .onion");
JMenuItem node_delete = new JMenuItem("Delete node .onion");
JMenuItem node_delete_all = new JMenuItem("Delete all nodes");

ButtonGroup groupn = new ButtonGroup();
JRadioButtonMenuItem nodes_yes = new JRadioButtonMenuItem("Update node list");
JRadioButtonMenuItem nodes_no = new JRadioButtonMenuItem("Keep my node list");

JMenuItem node_status = new JMenuItem("Node network status");


JPanel build1 = new JPanel();
static JPanel build2 = new JPanel();
static JPanel build3 = new JPanel();
static JPanel build4 = new JPanel();
static JPanel build5 = new JPanel();
static JPanel build6 = new JPanel();






JButton button1 = new JButton("Restart");
JButton button2 = new JButton("");
JButton button3 = new JButton("");
JButton button4 = new JButton("");
JButton button5 = new JButton("");
JButton button6 = new JButton("");
JButton button7 = new JButton("");

JButton buttonqr = new JButton("");


JLabel headerb2_l;//for transfer panel

JLabel status1 = new JLabel("Database: Apache Derby - 10.12.1.1 - (1704137)", JLabel.LEFT);
JLabel status2 = new JLabel("API Client: Java Apache json-simple-1.1.1", JLabel.LEFT);
JLabel status3 = new JLabel("Settings Database", JLabel.LEFT);
JLabel status4 = new JLabel("Description: ", JLabel.LEFT);
JLabel status5 = new JLabel("Database Status: (" + database_active + ")", JLabel.LEFT);
JLabel status6 = new JLabel("Program IP: 127.0.0.1", JLabel.LEFT);
static JLabel status7 = new JLabel("Verify Blockchain", JLabel.LEFT);
static JLabel status8 = new JLabel("Port Bindings: (Server: " + Integer.toString(p2p_port) + ") (API: " + Integer.toString(api_port) + ")", JLabel.LEFT);
static JLabel status9 = new JLabel("LOAD TOR >>> [ 0% ]", JLabel.LEFT);
static JLabel status10 = new JLabel("Peer(s): (" + 0 + ")", JLabel.LEFT);
static JLabel status11 = new JLabel("", JLabel.LEFT);


static Label status_x1 = new Label("Loading...", Label.LEFT);
static JLabel status_x2 = new JLabel("Mining", JLabel.LEFT);
static JLabel status_x3 = new JLabel("Tor Active", JLabel.LEFT);

JLabel status_ax1 = new JLabel("Tokens (KRC): 0", JLabel.LEFT);
Label status_ax2 = new Label("Loading...", Label.LEFT);
Label status_ax3 = new Label("Loading...", Label.LEFT);
Label status_ax4 = new Label("Loading...", Label.LEFT);
Label status_ax5 = new Label("Loading...", Label.LEFT);
Label status_ax6 = new Label("Loading...", Label.LEFT);
Label status_ax7 = new Label("Loading...", Label.LEFT);
Label status_ax8 = new Label("Loading...", Label.LEFT);
Label status_ax9 = new Label("Loading...", Label.LEFT);
Label status_ax10 = new Label("Loading...", Label.LEFT);
Label status_ax11 = new Label("Loading...", Label.LEFT);
Label status_ax12 = new Label("", Label.LEFT);
Label status_ax13 = new Label("", Label.LEFT);
Label status_ax14 = new Label("", Label.LEFT);
Label status_ax15 = new Label("", Label.LEFT);

Label status_ax1_b = new Label("", Label.LEFT);
Label status_ax2_b = new Label("", Label.LEFT);
Label status_ax3_b = new Label("", Label.LEFT);

JLabel info_am_l = new JLabel("Ad Message:", JLabel.LEFT);
JTextField info_am = new JTextField("", 30);



JTextArea consolex = new JTextArea("", 30, 61);
JTextField commandx = new JTextField("", 55);
JButton runx = new JButton("Run");
ArrayList<String> cmdlist = new ArrayList<String>();





network(){


	super("Krypton - A Decentralized Bulletin Board");
	setDefaultCloseOperation(HIDE_ON_CLOSE); 
	setBackground(Color.black);
	setSize(xzx, xzy);
	setLocation(cenx, (ceny - 30));
	setResizable(false);
	setAlwaysOnTop(false);
    addWindowListener(new WindowAdapter(){public void windowClosing(WindowEvent e){setVisible(false); savex(); systemx.shutdown(); System.exit(0);}});

        //requestFocus();

	//the program icon 
	Image imageppx = new ImageIcon(this.getClass().getResource("images/hex_icon.png")).getImage();
	setIconImage(imageppx);


	imx0 = new ImageIcon(this.getClass().getResource("images/message-no.png"));
	imx1 = new ImageIcon(this.getClass().getResource("images/message-yes.png"));
	imx2 = new ImageIcon(this.getClass().getResource("images/settings.png"));
	imx3 = new ImageIcon(this.getClass().getResource("images/hex.png"));
	imx4 = new ImageIcon(this.getClass().getResource("images/qrcode.png"));

	starttime = System.currentTimeMillis();

	cmdlist.add("Loading...");

	System.out.println(System.getProperty("sun.arch.data.model"));

	


	status1.setPreferredSize(new Dimension(480, 20));
	status1.setForeground(Color.BLACK);
	status1.setIcon(imx0);
	status1.setFont(f_01);

	status2.setPreferredSize(new Dimension(480, 20));
	status2.setForeground(Color.BLACK);
	status2.setIcon(imx0);
	status2.setFont(f_01);

	status3.setPreferredSize(new Dimension(480, 20));
	status3.setForeground(Color.BLACK);
	status3.setIcon(imx0);
	status3.setFont(f_01);

	status4.setPreferredSize(new Dimension(480, 20));
	status4.setForeground(Color.BLACK);
	status4.setIcon(imx0);
	status4.setFont(f_01);

	status5.setPreferredSize(new Dimension(480, 20));
	status5.setForeground(Color.BLACK);
	status5.setIcon(imx0);
	status5.setFont(f_01);

	status6.setPreferredSize(new Dimension(480, 20));
	status6.setForeground(Color.BLACK);
	status6.setIcon(imx0);
	status6.setFont(f_01);

	status7.setPreferredSize(new Dimension(480, 20));
	status7.setForeground(Color.BLACK);
	status7.setIcon(imx0);
	status7.setFont(f_01);

	status8.setPreferredSize(new Dimension(480, 20));
	status8.setForeground(Color.BLACK);
	status8.setIcon(imx0);
	status8.setFont(f_01);

	status9.setPreferredSize(new Dimension(480, 20));
	status9.setForeground(Color.BLACK);
	status9.setIcon(imx0);
	status9.setFont(f_01);

	status10.setPreferredSize(new Dimension(480, 20));
	status10.setForeground(Color.BLACK);
	status10.setIcon(imx0);
	status10.setFont(f_01);

	status11.setPreferredSize(new Dimension(480, 20));
	status11.setForeground(Color.BLACK);
	status11.setIcon(imx0);
	status11.setFont(f_01);



	status_x1.setPreferredSize(new Dimension(490, 20));
	status_x1.setForeground(Color.BLACK);
	//status_x1.setIcon(imx2);

	status_x2.setPreferredSize(new Dimension(80, 20));
	status_x2.setForeground(Color.BLACK);
	status_x2.setIcon(imx0);
	status_x2.setFont(f_01);

	status_x3.setPreferredSize(new Dimension(100, 20));
	status_x3.setForeground(Color.BLACK);
	status_x3.setIcon(imx0);
	status_x3.setFont(f_01);


	info_am_l.setPreferredSize(new Dimension(100, 20));
	info_am_l.setForeground(Color.BLACK);
	//info_am_l.setIcon(imx2);




	status_ax1.setPreferredSize(new Dimension(400, 55));
	status_ax1.setFont(f_02);
	status_ax1.setForeground(Color.BLACK);
	//status_ax1.setOpaque(true);
	status_ax1.setBackground(gray8);


	status_ax1_b.setPreferredSize(new Dimension(400, 15));
	status_ax1_b.setForeground(Color.BLACK);

	status_ax2_b.setPreferredSize(new Dimension(400, 15));
	status_ax2_b.setForeground(Color.BLACK);

	status_ax3_b.setPreferredSize(new Dimension(400, 15));
	status_ax3_b.setForeground(Color.BLACK);



	status_ax2.setPreferredSize(new Dimension(400, 15));
	status_ax2.setForeground(Color.BLACK);

	status_ax3.setPreferredSize(new Dimension(400, 15));
	status_ax3.setForeground(Color.BLACK);

	status_ax4.setPreferredSize(new Dimension(400, 15));
	status_ax4.setForeground(Color.BLACK);

	status_ax5.setPreferredSize(new Dimension(400, 15));
	status_ax5.setForeground(Color.BLACK);

	status_ax6.setPreferredSize(new Dimension(400, 15));
	status_ax6.setForeground(Color.BLACK);

	status_ax7.setPreferredSize(new Dimension(464, 15));
	status_ax7.setForeground(Color.BLACK);
	//status_ax7.setBackground(Color.BLACK);
	status_ax7.setFont(f_01);

	status_ax8.setPreferredSize(new Dimension(464, 15));
	status_ax8.setForeground(Color.BLACK);
	status_ax8.setFont(f_01);

	status_ax9.setPreferredSize(new Dimension(464, 15));
	status_ax9.setForeground(Color.BLACK);
	status_ax9.setFont(f_01);

	status_ax10.setPreferredSize(new Dimension(464, 15));
	status_ax10.setForeground(Color.BLACK);
	status_ax10.setFont(f_01);

	status_ax11.setPreferredSize(new Dimension(464, 15));
	status_ax11.setForeground(Color.BLACK);
	status_ax11.setFont(f_01);

	status_ax12.setPreferredSize(new Dimension(464, 15));
	status_ax12.setForeground(Color.BLACK);
	status_ax12.setFont(f_01);

	status_ax13.setPreferredSize(new Dimension(464, 15));
	status_ax13.setForeground(Color.BLACK);
	status_ax13.setFont(f_01);

	status_ax14.setPreferredSize(new Dimension(464, 15));
	status_ax14.setForeground(Color.BLACK);
	status_ax14.setFont(f_01);

	status_ax15.setPreferredSize(new Dimension(464, 15));
	status_ax15.setForeground(Color.BLACK);
	status_ax15.setFont(f_01);




	button1.setPreferredSize(new Dimension(80, 30));
	button1.setOpaque(true);
	button1.setBackground(jblue);//darkgray08
	button1.setForeground(blackx);//darkgray08
	button1.setToolTipText("");
	button1.addActionListener(this);
	button1.setEnabled(false);

	button2.setPreferredSize(new Dimension(80, 30));
	button2.setOpaque(true);
	button2.setBackground(jblue);//darkgray08
	button2.setForeground(blackx);//darkgray08
	button2.setToolTipText("");
	button2.addActionListener(this);
	button2.setEnabled(false);

	button3.setPreferredSize(new Dimension(80, 30));
	button3.setOpaque(true);
	button3.setBackground(jblue);//darkgray08
	button3.setForeground(blackx);//darkgray08
	button3.setToolTipText("");
	button3.addActionListener(this);
	button3.setEnabled(false);

	button4.setPreferredSize(new Dimension(80, 30));
	button4.setOpaque(true);
	button4.setBackground(jblue);//darkgray08
	button4.setForeground(blackx);//darkgray08
	button4.setToolTipText("");
	button4.addActionListener(this);
	button4.setEnabled(false);

	button5.setPreferredSize(new Dimension(80, 30));
	button5.setOpaque(true);
	button5.setBackground(jblue);//darkgray08
	button5.setForeground(blackx);//darkgray08
	button5.setToolTipText("");
	button5.addActionListener(this);
	button5.setEnabled(false);

	button6.setPreferredSize(new Dimension(80, 30));
	button6.setOpaque(true);
	button6.setBackground(jblue);//darkgray08
	button6.setForeground(blackx);//darkgray08
	button6.setToolTipText("");
	button6.addActionListener(this);
	button6.setEnabled(false);

	button7.setPreferredSize(new Dimension(80, 30));
	button7.setOpaque(true);
	button7.setBackground(jblue);//darkgray08
	button7.setForeground(blackx);//darkgray08
	button7.setToolTipText("");
	button7.addActionListener(this);
	button7.setEnabled(false);



	info_am.setBorder(null);
	info_am.setBackground(gray8);
	info_am.setForeground(Color.BLACK);

	buttonqr.setPreferredSize(new Dimension(155, 155));
	buttonqr.setBackground(whitex);
	buttonqr.addActionListener(this);
	buttonqr.setIcon(imx4);







	JPanel jpx0 = new JPanel();
	jpx0.setPreferredSize(new Dimension(584, 266));
	jpx0.setBackground(jblue);//darkgray08
	jpx0.add(status_ax7);
	jpx0.add(status_ax8);
	jpx0.add(status_ax9);
	jpx0.add(status_ax10);
	jpx0.add(status_ax11);
	jpx0.add(status_ax12);
	jpx0.add(status_ax13);
	jpx0.add(status_ax14);
	jpx0.add(status_ax15);
	//jpx0.add(status5);
	//jpx0.add(status6);
	//jpx0.add(status7);
	//jpx0.add(status8);
	//jpx0.add(status9);
	//jpx0.add(status10);
	//jpx0.add(status11);


	JPanel jpx1 = new JPanel();
	jpx1.setPreferredSize(new Dimension(90, 266));
	jpx1.setBackground(jblue);//darkgray08
	jpx1.add(button1);
	jpx1.add(button2);
	jpx1.add(button3);
	jpx1.add(button4);
	jpx1.add(button5);
	jpx1.add(button6);
	jpx1.add(button7);





	JButton bpx2_b = new JButton("");
	bpx2_b.setPreferredSize(new Dimension(65, 65));
	bpx2_b.setOpaque(true);
	bpx2_b.setBackground(gray7);//darkgray08
	bpx2_b.setForeground(blackx);//darkgray08
	bpx2_b.setToolTipText("");
	bpx2_b.setIcon(imx3);
	bpx2_b.setEnabled(false);





	JPanel jpx2_a = new JPanel();
	jpx2_a.setPreferredSize(new Dimension(20, 165));
	jpx2_a.setBackground(jblue);//darkgray08

	JPanel jpx2_b = new JPanel();
	jpx2_b.setPreferredSize(new Dimension(512, 165));
	jpx2_b.setBackground(jblue);//darkgray08
	jpx2_b.add(status_ax1);
	jpx2_b.add(status_ax2);
	jpx2_b.add(status_ax3);
	jpx2_b.add(status_ax4);
	jpx2_b.add(status_ax5);
	jpx2_b.add(status_ax6);
	//jpx2_b.add(status_ax7);

	JPanel jpx2_c = new JPanel();
	jpx2_c.setPreferredSize(new Dimension(165, 165));
	jpx2_c.setBackground(jblue);//darkgray08
	jpx2_c.add(buttonqr);

	JPanel jpx2 = new JPanel();
	jpx2.setPreferredSize(new Dimension(689, 235));
	jpx2.setBackground(jblue);//darkgray08
	//jpx2.add(jpx2_a);
	jpx2.add(jpx2_b);
	jpx2.add(jpx2_c);


	flow0.setVgap(0);
	JPanel jpx3 = new JPanel();
	jpx3.setLayout(flow0);
	jpx3.setPreferredSize(new Dimension(689, 20));
	jpx3.setBackground(jblue);//darkgray08
	jpx3.add(status_x1);
	jpx3.add(status_x2);
	jpx3.add(status_x3);





    JMenuBar menuBar = new JMenuBar();

    database_test.setEnabled(false);
	tools_vote.setEnabled(false);
	tools_print_blocks.setEnabled(false);
	account_sign.setEnabled(false);




    exit.setFont(f_00);
    exit_save.setFont(f_00);

    account_public_key.setFont(f_00);
    account_private_key.setFont(f_00);
    account_sign.setFont(f_00);
    account_import.setFont(f_00);

	edit_token.setFont(f_00);
	edit_get_ids.setFont(f_00);

	all_yes.setFont(f_00);
	x100_yes.setFont(f_00);
	database_test.setFont(f_00); 

	tools_vote.setFont(f_00);
	tools_new_keys.setFont(f_00);
	tools_print_blocks.setFont(f_00);

	node_import.setFont(f_00);
	node_delete.setFont(f_00);
	node_delete_all.setFont(f_00);

	nodes_yes.setFont(f_00);
	nodes_no.setFont(f_00);






	fileMenu.setFont(f_01);
    //fileMenu.add(exit_save);
    fileMenu.add(exit);
    

	editMenu.setFont(f_01);
	editMenu.add(edit_get_ids);
	editMenu.add(tools_vote);
	editMenu.add(tools_print_blocks);


	groupd.add(all_yes);
	groupd.add(x100_yes);
	databaseMenu.setFont(f_01);
	//databaseMenu.add(all_yes);
	//databaseMenu.add(x100_yes);
	//databaseMenu.addSeparator();
	databaseMenu.add(database_test);


    accountMenu.setFont(f_01);
	accountMenu.add(account_public_key);
	accountMenu.add(account_private_key);
	accountMenu.add(account_sign);
	accountMenu.addSeparator();
	accountMenu.add(tools_new_keys);
	accountMenu.add(account_import);


	groupn.add(nodes_yes);
	groupn.add(nodes_no);
    nodeMenu.setFont(f_01);
    nodeMenu.add(nodes_yes);
    nodeMenu.add(nodes_no);
    nodeMenu.addSeparator();
	nodeMenu.add(node_import);
	nodeMenu.add(node_delete);
	nodeMenu.addSeparator();
	nodeMenu.add(node_delete_all);
	



	exit_save.addActionListener(this);
	exit.addActionListener(this);

	account_public_key.addActionListener(this);
    account_private_key.addActionListener(this);
	account_import.addActionListener(this);

	edit_get_ids.addActionListener(this);
	edit_token.addActionListener(this);

	all_yes.addActionListener(this);
	x100_yes.addActionListener(this);

	tools_new_keys.addActionListener(this);
	tools_print_blocks.addActionListener(this);

	nodes_yes.addActionListener(this);
	nodes_no.addActionListener(this);

	node_import.addActionListener(this);
	node_delete.addActionListener(this);
	node_delete_all.addActionListener(this);





	//consolex.setPreferredSize(new Dimension(500, 1165));
	consolex.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
	consolex.setEditable(false);
	consolex.setLineWrap(true);
	consolex.setAlignmentY(BOTTOM_ALIGNMENT);
	//consolex.setBackground(blackx);
	//consolex.setForeground(whitex);

	commandx.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));

	runx.setPreferredSize(new Dimension(80, 20));
	runx.setOpaque(true);
	runx.setBackground(jblue);//darkgray08
	runx.setForeground(blackx);//darkgray08
	runx.setToolTipText("Run a Command");
	runx.addActionListener(this);

	JScrollPane scrollPaned_item = new JScrollPane(consolex);
	scrollPaned_item.setPreferredSize(new Dimension(690, 487));
	scrollPaned_item.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	scrollPaned_item.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	scrollPaned_item.setFont(new Font("Arial", Font.PLAIN, 14));








    setJMenuBar(menuBar);
    menuBar.add(fileMenu);
    menuBar.add(editMenu);
    menuBar.add(databaseMenu);
    menuBar.add(accountMenu);
    menuBar.add(nodeMenu);

	spaceMenu.setEnabled(false);



	build1 = new JPanel();
	build1.setLayout(flow0);
	build1.setBackground(jblue);//darkgray08
	build1.setPreferredSize(new Dimension(700, 540));
	build1.add(jpx2);
	build1.add(jpx0);
	build1.add(jpx1);
	build1.add(jpx3);


	build2 = new JPanel();
	build2.setLayout(new FlowLayout(1,0,0));
	build2.setBackground(jblue);//darkgray08
	build2.setPreferredSize(new Dimension(700, 540));

	build3 = new JPanel();
	build3.setLayout(new FlowLayout(1,0,120));
	build3.setBackground(jblue);//darkgray08
	build3.setPreferredSize(new Dimension(700, 540));

	build4 = new JPanel();
	build4.setLayout(new FlowLayout(1,0,0));
	build4.setBackground(jblue);//darkgray08
	build4.setPreferredSize(new Dimension(700, 540));

	build5 = new JPanel();
	build5.setLayout(new FlowLayout(1,0,20));
	build5.setBackground(jblue);//darkgray08
	build5.setPreferredSize(new Dimension(700, 540));

	build6 = new JPanel();
	build6.setLayout(new FlowLayout(1,0,4));
	build6.setBackground(jblue);//darkgray08
	build6.setPreferredSize(new Dimension(700, 540));
	build6.add(scrollPaned_item);
	build6.add(commandx);
	build6.add(runx);


	edit_item itemx = new edit_item();
	edit_transfer_item items = new edit_transfer_item();
	edit_view_item itemv = new edit_view_item();
	edit_seller_account itemsa = new edit_seller_account();




	cmdlist.add("Display...");

	JPanel topPanel = new JPanel();
	final JTabbedPane tabbedPane = new JTabbedPane();
	tabbedPane.addTab("Overview", build1 );
	tabbedPane.addTab("Edit Token", build2 );
	tabbedPane.addTab("Transfer", build3 );
	tabbedPane.addTab("View Tokens", build4 );
	tabbedPane.addTab("Seller Account", build5 );
	tabbedPane.addTab("Console", build6 );
	topPanel.add( tabbedPane, BorderLayout.CENTER );

	tabbedPane.addChangeListener(new ChangeListener() {
    public void stateChanged(ChangeEvent e) {

        System.out.println("Tab: " + tabbedPane.getSelectedIndex());
        if(tabbedPane.getSelectedIndex() == 0 && program_ready){}
        else if(tabbedPane.getSelectedIndex() == 1 && program_ready){edit_item.show_token();}
        else if(tabbedPane.getSelectedIndex() == 2 && program_ready){edit_transfer_item.resetspx();}
        else if(tabbedPane.getSelectedIndex() == 3 && program_ready){}
        else if(tabbedPane.getSelectedIndex() == 4 && program_ready){edit_seller_account.show_info();}
        else if(tabbedPane.getSelectedIndex() == 5 && program_ready){commandx.requestFocus(); display_commandx();}
        else{JOptionPane.showMessageDialog(null, "Program is not done loading...");}

    }
   	});





	Container cp = getContentPane();
	cp.setLayout(new FlowLayout(1,0,0));
	cp.setBackground(gray7);
	cp.setForeground(gray7);
	cp.add(topPanel);

	//cp.add(jpx2);
	//cp.add(jpx0);	
	//cp.add(jpx1);
	//cp.add(jpx3);



	setVisible(true);

	cmdlist.add("Start...");


	toolkit = Toolkit.getDefaultToolkit();
	xtimerx = new Timer();
	xtimerx.schedule(new RemindTask_engine(), 0);


	build_item_array();

	//start the old network
	network_startup();



}//network






	public void network_startup(){//**************************************************************************

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

			open_network = Integer.parseInt(settingsx[8]);
			if(open_network == 1){System.out.println("Network OPEN"); nodes_yes.setSelected(true);}
			else{System.out.println("Network CLOSED"); nodes_no.setSelected(true);}

		}//***********************
		else{


			krypton_database_build db = new krypton_database_build();
			krypton_database_load xx2 = new krypton_database_load();

			if(database_active == 1){

				System.out.println("Database Status: (" + database_active + ")");

				idx = settingsx[0];

	        	System.out.println("test 6 " + settingsx[8]);

				open_network = Integer.parseInt(settingsx[8]);
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


		program_ready = true;



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
		    		//savex();
		    		System.out.println("Window Opening");


		    		int response = JOptionPane.showConfirmDialog(null, "Force shut down Krypton API?");
	 				if(response == 0){

	 					System.exit(0);

	 				}//***************

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



				if(new_database_start == 0){edit_transfer_item.headerb2_l.setText("You have: " + Integer.toString(database_listings_owner) + " token(s)");}

				status_ax1.setText("Tokens (KRC): " + Integer.toString(database_listings_owner));
				status_ax2.setText("Total: " + Integer.toString(database_listings_total) + " (" + Integer.toString(database_unconfirmed_total) + " Unconfirmed)");
				status_ax3.setText(base58_id);
				status_ax4.setText("Difficulty: " + Long.toString(difficultyx));
				status_ax5.setText("Block Speed: " + Long.toString(blocktimesx / 1000));
				try{status_ax6.setText("Time Since Last Block: " + Long.toString( (long) (thisTick - last_block_time) / 1000) );} catch(Exception e){e.printStackTrace();}
				status_ax7.setText("Website Hits: " + Integer.toString(website_hits));
				status_ax8.setText("Website Searches: " + Integer.toString(website_searches));
				status_ax9.setText("Buffer Size: " + Integer.toString(send_buffer_size));
				status_ax10.setText("Peer list: " + network_size);
				status_ax11.setText("Latest Block: " + last_block_id);
				status_ax12.setText("Version: " + versionx);


				status_ax1_b.setText("(" + requests + ")");
				status_ax2_b.setText("(" + database_messages + ")");
				status_ax3_b.setText("(" + database_messages + ")");

				//status 
				status_x1.setText(programst);

				if(mining_status == 0){status_x2.setIcon(imx0);}
				else{status_x2.setIcon(imx1);}

				if(tor_active == 0){status_x3.setIcon(imx0);}
				else{status_x3.setIcon(imx1);}


				try{

					File x3 = new File(base58_id + ".png");

					//System.out.println("File " + x3.exists());

					if(x3.exists()){

						Icon ccimx2 = new ImageIcon(base58_id + ".png");
						buttonqr.setIcon(ccimx2);

					}//*************
					//else{rebuild_key_image();}
							
				}catch(Exception e){System.out.println("Error loading QR code...");}//*****************





				if(no_peers_time > 100){

					//restartApplication();

				}//*********************

				if(seconds > 1000 && last_block_tl > 3000){

					//System.out.println("time_block_added Test: " + (System.currentTimeMillis() - time_block_added));
					//if((System.currentTimeMillis() - time_block_added) > 1000){restartApplication();}

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

			programst = "";

			//System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

			try{Thread.sleep(100000);} 
			catch(InterruptedException e){}

			//status_x1.setText("Clean up memory...");

			//try{Integer.parseInt(settingsx[6]);}
			//catch(Exception e){restartApplication();}//restartApplication();

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
		item_layout[1] = new String("hash_id");
		item_layout[2] = new String("sig_id");
		item_layout[3] = new String("date_id");
		item_layout[4] = new String("owner_id");
		item_layout[5] = new String("owner_rating");
		item_layout[6] = new String("currency");
		item_layout[7] = new String("custom_template");
		item_layout[8] = new String("custom_1");
		item_layout[9] = new String("custom_2");
		item_layout[10] = new String("custom_3");
		item_layout[11] = new String("item_errors");
		item_layout[12] = new String("item_date_listed");
		item_layout[13] = new String("item_date_listed_day");
		item_layout[14] = new String("item_date_listed_int");
		item_layout[15] = new String("item_hits");
		item_layout[16] = new String("item_confirm_code");
		item_layout[17] = new String("item_confirmed");
		item_layout[18] = new String("item_cost");
		item_layout[19] = new String("item_description");
		item_layout[20] = new String("item_id");
		item_layout[21] = new String("item_price");
		item_layout[22] = new String("item_weight");
		item_layout[23] = new String("item_listing_id");
		item_layout[24] = new String("item_notes");
		item_layout[25] = new String("item_package_d");
		item_layout[26] = new String("item_package_l");
		item_layout[27] = new String("item_package_w");
		item_layout[28] = new String("item_part_number");
		item_layout[29] = new String("item_title");
		item_layout[30] = new String("item_title_url");
		item_layout[31] = new String("item_type");
		item_layout[32] = new String("item_search_1");
		item_layout[33] = new String("item_search_2");
		item_layout[34] = new String("item_search_3");
		item_layout[35] = new String("item_site_id");
		item_layout[36] = new String("item_site_url");
		item_layout[37] = new String("item_picture_1");
		item_layout[38] = new String("item_total_on_hand");
		item_layout[39] = new String("sale_payment_address");
		item_layout[40] = new String("sale_payment_type");
		item_layout[41] = new String("sale_fees");
		item_layout[42] = new String("sale_id");
		item_layout[43] = new String("sale_seller_id");
		item_layout[44] = new String("sale_status");
		item_layout[45] = new String("sale_tax");
		item_layout[46] = new String("sale_shipping_company");
		item_layout[47] = new String("sale_shipping_in");
		item_layout[48] = new String("sale_shipping_out");
		item_layout[49] = new String("sale_source_of_sale");
		item_layout[50] = new String("sale_total_sale_amount");
		item_layout[51] = new String("sale_tracking_number");
		item_layout[52] = new String("sale_transaction_id");
		item_layout[53] = new String("sale_transaction_info");
		item_layout[54] = new String("seller_address_1");
		item_layout[55] = new String("seller_address_2");
		item_layout[56] = new String("seller_address_city");
		item_layout[57] = new String("seller_address_state");
		item_layout[58] = new String("seller_address_zip");
		item_layout[59] = new String("seller_address_country");
		item_layout[60] = new String("seller_id");
		item_layout[61] = new String("seller_ip");
		item_layout[62] = new String("seller_email");
		item_layout[63] = new String("seller_first_name");
		item_layout[64] = new String("seller_last_name");
		item_layout[65] = new String("seller_notes");
		item_layout[66] = new String("seller_phone");
		item_layout[67] = new String("seller_logo");
		item_layout[68] = new String("seller_url");


	}//****************************








	public void run_cmd(){


			String response2 = JOptionPane.showInputDialog(null, "Enter command:", "CMD", JOptionPane.QUESTION_MESSAGE);
            if(response2.length() > 0){



            }//************************



	}//*******************












	public void delete_network(){


			int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the network list?");
 			if(response == 0){


				while(network.database_in_use == 1){

					int test_db = 0;
		    		System.out.println("Database in use... delete node network");
					try{Thread.sleep(1000);} catch (InterruptedException e){}
					test_db++;

		    	}//*********************************

            	krypton_database_delete_network pxd = new krypton_database_delete_network();
            	krypton_database_load xxs = new krypton_database_load();

            }//***************



	}//*******************************









	public void delete_node(){

		System.out.println("Delete node");

		String response2 = JOptionPane.showInputDialog(null, "Enter node .onion:", "Delete Node", JOptionPane.QUESTION_MESSAGE);
		if(response2.length() > 0){

			while(network.database_in_use == 1){

				int test_db = 0;
	    		System.out.println("Database in use... delete node");
				try{Thread.sleep(1000);} catch (InterruptedException e){}
				test_db++;

	    	}//*********************************


	        mining.mining3 = false;


			krypton_database_delete_node pxd = new krypton_database_delete_node();
	        pxd.delete(response2);

			krypton_database_load_network nodesx = new krypton_database_load_network();

			krypton_database_load loadx = new krypton_database_load();


	        mining.mining3 = true;

	    }//


	}//**********************









	public void set_new_node(){

		System.out.println("Add new node");

		String response2 = JOptionPane.showInputDialog(null, "Enter new node .onion:", "Add Node", JOptionPane.QUESTION_MESSAGE);
		if(response2.length() > 0){

			while(network.database_in_use == 1){

				int test_db = 0;
	    		System.out.println("Database in use... import node");
				try{Thread.sleep(1000);} catch (InterruptedException e){}
				test_db++;

	    	}//*********************************


	        mining.mining3 = false;


			krypton_database_nodes add_node = new krypton_database_nodes(response2);

			krypton_database_load_network nodesx = new krypton_database_load_network();

			krypton_database_load loadx = new krypton_database_load();


	        mining.mining3 = true;

	    }//if


	}//**********************











	public void get_new_keys(){//*****************************************************************


		System.out.println("Build new keys");


		while(network.database_in_use == 1){

			int test_db = 0;
    		System.out.println("Database in use...keys");
			try{Thread.sleep(1000);} catch (InterruptedException e){}
			test_db++;

    	}//*********************************


 		krypton_rebuild_keys keysx = new krypton_rebuild_keys();
 		krypton_database_load_network nodesx = new krypton_database_load_network();
 		krypton_database_load loadxs = new krypton_database_load();


	}//*********************************************************************************************
























	public void run_commandx(){

		String cmr = commandx.getText();

		String cmrx = request(cmr);

		cmdlist.add("CMD: " + cmr + " >: " + cmrx);

		display_commandx();

	}//************************




	public void display_commandx(){


		String cmddisplay = new String();

		for (int loop = 0; loop < cmdlist.size(); loop++){//************

			cmddisplay = cmddisplay + cmdlist.get(loop) + "\n\r";

		}//*************************************************************

		consolex.setText(cmddisplay);

		commandx.setText("");
		commandx.requestFocus();


	}//************************




	public void get_ids(){

		String idsx = new String("");


		for (int loop = 0; loop < my_listings.size(); loop++){//********

			idsx = idsx + my_listings.get(loop) + "\n\r";

		}//*************************************************************


		StringSelection selection = new StringSelection(idsx);
    	Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    	clipboard.setContents(selection, selection);

		JOptionPane.showMessageDialog(null, Integer.toString(my_listings.size()) + " ID(s) saved to clipboard");


	}//********************















	public void save_pub_key_clipboard(){

		StringSelection selection = new StringSelection(settingsx[5]);
   		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    	clipboard.setContents(selection, selection);

		JOptionPane.showMessageDialog(null, "Key public saved to clipboard");

	}//******************************


	public void save_pri_key_clipboard(){

		StringSelection selection = new StringSelection(settingsx[4]);
    	Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    	clipboard.setContents(selection, selection);

		JOptionPane.showMessageDialog(null, "Key private saved to clipboard");

	}//******************************









	public void save_key_clipboard(){

		StringSelection selection = new StringSelection(base58_id);
    	Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    	clipboard.setContents(selection, selection);

		JOptionPane.showMessageDialog(null, "Key saved to clipboard");

	}//******************************












	public void import_private_key(){//*****************************************************************

		System.out.println("Build old keys");

		String response2 = JOptionPane.showInputDialog(null, "Enter Private Key:", "Import", JOptionPane.QUESTION_MESSAGE);
		if(response2.length() > 0){


			int test_db = 0;
			while(network.database_in_use == 1){

	    		System.out.println("Database in use... import key");
				try{Thread.sleep(1000);} catch (InterruptedException e){}
				test_db++;

	    	}//*********************************


	        mining.mining3 = false;

			krypton_database_import_keys import_kx = new krypton_database_import_keys(response2);

			krypton_database_load xxs = new krypton_database_load();
			krypton_database_load_network xxn = new krypton_database_load_network();

	        mining.mining3 = true;


	    }//

	}//*********************************************************************************************











	public String request(String request){//*****************************************************************

		String jsonText = new String("");


		try{

			JSONObject obj = new JSONObject();
			obj.put("request", request);
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

		}catch(Exception e){e.printStackTrace(); System.out.println("API SERVER OFFLINE!"); modifiedSentence = "API SERVER OFFLINE!";}

		return modifiedSentence;

	}//*********************************************************************************************




















//***************************************************************************************************************************************
//***************************************************************************************************************************************

//handel all the button clicks. 
public void actionPerformed(ActionEvent event){

	if(event.getSource() == buttonqr)                 {save_key_clipboard();}

	if(event.getSource() == exit_save)                {settingsx[9] = "1"; savex(); xmining = 0; systemx.shutdown(); System.exit(0);}
	if(event.getSource() == exit)                     {System.exit(0);}
	if(event.getSource() == edit_token)               {edit_item xx = new edit_item();}
	if(event.getSource() == node_status)              {}
	if(event.getSource() == edit_get_ids)             {get_ids();}

	if(event.getSource() == account_public_key)       {save_pub_key_clipboard();}
	if(event.getSource() == account_private_key)      {save_pri_key_clipboard();}
	if(event.getSource() == account_import)           {import_private_key();}

	if(event.getSource() == all_yes)                  {settingsx[7] = "1"; savex();}
	if(event.getSource() == x100_yes)                 {settingsx[7] = "0"; savex();}

	if(event.getSource() == nodes_yes)                {settingsx[8] = "1"; savex();}
	if(event.getSource() == nodes_no)                 {settingsx[8] = "0"; savex();}

	if(event.getSource() == tools_new_keys)           {get_new_keys();}
	if(event.getSource() == tools_print_blocks)       {}

	if(event.getSource() == button1)                  {}
	if(event.getSource() == button2)                  {}
	if(event.getSource() == button3)                  {}
	if(event.getSource() == button4)                  {}
	if(event.getSource() == button5)                  {}
	if(event.getSource() == button6)                  {}
	if(event.getSource() == button7)                  {}

	if(event.getSource() == runx)                     {run_commandx();}

	if(event.getSource() == node_import)              {set_new_node();}
	if(event.getSource() == node_delete)              {delete_node();}
	if(event.getSource() == node_delete_all)          {delete_network();}

}//********************************************







//start the program.
    public static void main(String[] args) {

		network black = new network();

    }//main




}//last
