import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;  
import java.lang.Object.*;  
import java.net.*;
import java.math.*;

import java.awt.Toolkit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.CallableStatement;

import java.util.ArrayList;
import java.util.Properties;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.Arrays;

import org.spongycastle.util.encoders.Base64;

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

import org.spongycastle.util.encoders.Base64;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONValue;




public class krypton_database_test_full extends JFrame{

Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize();
int xzx = 370;
int xzy = 300;
int cenx = (scrSize.width / 2) - (xzx / 2);
int ceny = (scrSize.height / 2) - (xzy / 2);


final protected static char[] hexArray = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();


static Long longstamp_hold = (long) 0;

JProgressBar pbar;

static final int MY_MINIMUM = 0;
static final int MY_MAXIMUM = 100;

boolean blocks_verified = false;

int ix0 = 0;
int ix1 = 0;
int ix2 = 0;
int listing_size = 69;

static Font f_00 = new Font("Arial", Font.PLAIN, 10);
static Font f_01 = new Font("Arial", Font.PLAIN, 12);
static Font f_02 = new Font("Arial", Font.PLAIN, 18);

Label status1 = new Label("Testing: 0%", Label.LEFT);
Label status2 = new Label("Loading...", Label.LEFT);
Label status3 = new Label("Loading...", Label.LEFT);
Label status4 = new Label("Loading...", Label.LEFT);
Label status5 = new Label("Loading...", Label.LEFT);
Label status6 = new Label("Loading...", Label.LEFT);
Label status7 = new Label("", Label.LEFT);

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

static ArrayList<Statement> statements = new ArrayList<Statement>(); // list of Statements, 
static PreparedStatement psInsert = null;
static PreparedStatement psUpdate = null;

static Statement s = null;//load network
static Statement s2 = null;//load
static Statement se = null;//edit

static ResultSet rs = null;
static ResultSet rs2 = null; 
static ResultSet rse = null;//edit

/* the default framework is embedded*/
static String framework = "Embedded";
static String driver = "org.apache.derby.jdbc.EmbeddedDriver";
//static String driver = "org.apache.derby.jdbc.ClientDriver";
static String protocol = "jdbc:derby:";//jdbc:derby:

static Connection conn = null;


krypton_database_test_full(){//**************************************************************************
//network.database_in_use = 1;
    
        
    super("Krypton - Database Test");
    setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
    setBackground(Color.black);
    setSize(xzx, xzy);
    setLocation(cenx, (ceny - 30));
    setResizable(false);
    setAlwaysOnTop(false);
    addWindowListener(new WindowAdapter(){public void windowClosing(WindowEvent e){System.exit(0);}});

    //requestFocus();

    //the program icon 
    Image imageppx = new ImageIcon(this.getClass().getResource("images/hex_icon.png")).getImage();
    setIconImage(imageppx);


    pbar = new JProgressBar();
    pbar.setPreferredSize(new Dimension(350, 20));
    pbar.setMinimum(MY_MINIMUM);
    pbar.setMaximum(MY_MAXIMUM);


    status1.setPreferredSize(new Dimension(350, 40));
    status1.setForeground(Color.BLACK);
    status1.setFont(f_01);

    status2.setPreferredSize(new Dimension(350, 20));
    status2.setForeground(Color.BLACK);
    status2.setFont(f_01);

    status3.setPreferredSize(new Dimension(350, 20));
    status3.setForeground(Color.BLACK);
    status3.setFont(f_01);

    status4.setPreferredSize(new Dimension(350, 20));
    status4.setForeground(Color.BLACK);
    status4.setFont(f_01);

    status5.setPreferredSize(new Dimension(350, 20));
    status5.setForeground(Color.BLACK);
    status5.setFont(f_01);

    status6.setPreferredSize(new Dimension(350, 20));
    status6.setForeground(Color.BLACK);
    status6.setFont(f_01);

    status7.setPreferredSize(new Dimension(350, 60));
    status7.setForeground(Color.BLACK);
    status7.setFont(f_01);



    JPanel build1 = new JPanel();
    build1.setLayout(new FlowLayout());
    build1.setBackground(jblue);//darkgray08
    build1.setPreferredSize(new Dimension(360, 275));
    build1.add(status1);
    build1.add(status2);
    build1.add(status3);
    build1.add(status4);
    build1.add(status5);
    build1.add(status6);
    build1.add(status7);
    build1.add(pbar);


    Container cp = getContentPane();
    cp.setLayout(new FlowLayout());
    cp.setBackground(gray7);
    cp.setForeground(gray7);
    cp.add(build1);

    setVisible(true);


    start_driver();
    database_test();


    try{Thread.sleep(1000);} catch(InterruptedException e){}

    try{ 

        conn.close();
        if(conn.isClosed()){System.out.println("Connection closed.");}

    }catch(Exception e){}

    shutdown();


}//load




 

    public void database_test(){


        try{


            boolean testx0 = false;
            boolean testx1 = false;
            boolean testx2 = false;

            System.out.println("TESTING BLOCKCHAIN...");

            int total_items = 0;
            int testx = 1;

            //test
            System.out.println("START TEST");
            int displaypx = 100;
            String last_package = new String();

            long workx = (long) 0;
            Long start_time = (long) 0;




            s = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            //s.setMaxRows(6);
            rs = s.executeQuery("SELECT * FROM mining_db ORDER BY mining_date ASC");

            rs.last();
            total_items = rs.getRow();
            rs.beforeFirst();

            while(rs.next()){


                Float testgh = (float) (testx * 100) / total_items;

                DecimalFormat twoDForm = new DecimalFormat("#.##");
                Double dx = Double.valueOf(twoDForm.format(testgh));

                status1.setText("Testing: " + Double.toString(dx) + "% " + Integer.toString(total_items));
                pbar.setValue(Math.round(testgh));
                workx = workx + Long.parseLong(rs.getString(4));

                //System.out.println(rs.getString(1));
                //System.out.println(rs.getString(2));//id
                System.out.println(rs.getString(3));//date
                //System.out.println(rs.getString(4));//work
                //System.out.println(rs.getString(5));//mining_noose
                //System.out.println(rs.getString(6));//mining_old_block
                //System.out.println(rs.getString(7));//mining_new_block
                //System.out.println(rs.getString(8));//previous_hash_id
                //System.out.println(rs.getString(9));//hash
                //System.out.println(rs.getString(10));//sig
                //System.out.println("");



                //decode packages
                String ID = krypton_database_driver.rs.getString(2);
                String packagex = krypton_database_driver.rs.getString(11);

                boolean is_in_package = false;
                boolean last_is_package = false;
                boolean this_is_package = false;
                JSONArray jsonObjectx_last = null;
                JSONArray jsonObjectx_this = null;
                int last_package_sizex = 0;
                int this_package_sizex = 0;

                if(packagex.length() < 5){this_is_package = false;}
                else{

                    this_is_package = true;

                    try{

                        JSONParser parserx = new JSONParser();
                        Object objx = parserx.parse(packagex);
                        jsonObjectx_this = (JSONArray) objx;

                        this_package_sizex = jsonObjectx_this.size();

                    }catch(Exception e){this_is_package = false;}

                }//***

                if(last_package.length() < 5){last_is_package = false;}
                else{

                    last_is_package = true;

                    try{

                        JSONParser parserx = new JSONParser();
                        Object objx = parserx.parse(last_package);
                        jsonObjectx_last = (JSONArray) objx;

                        last_package_sizex = jsonObjectx_last.size();

                    }catch(Exception e){last_is_package = false;}


                }//***




                try{

                    System.out.println("last package " + last_package);
                    System.out.println("this package " + packagex);

                    System.out.println("Last Package " + last_package_sizex);
                    System.out.println("This Package " + this_package_sizex);

                    //test size
                    if(this_package_sizex == 0 && last_package_sizex == 0){testx0 = true;}//normal opperation
                    else if(this_package_sizex == 0 && last_package_sizex == 1){testx0 = true;}//package just ended
                    else if(this_package_sizex <= network.block_compress_size && last_package_sizex == 0){

                        if(this_is_package){

                            System.out.println("First package block");
                            System.out.println("id1 " + jsonObjectx_this.get(0));
                            System.out.println("id2 " + ID);

                            if(jsonObjectx_this.get(0).equals(ID)){testx0 = true;}
                            else{testx0 = false;}

                        }//*****************
                        else{testx0 = false;}

                    }//***********************************************************************************
                    else if((this_package_sizex + 1) == last_package_sizex && this_package_sizex <= network.block_compress_size){

                        if(this_is_package){

                            is_in_package = true;

                            System.out.println("Middle package block");
                            System.out.println("id0 " + jsonObjectx_last.get(1));
                            System.out.println("id1 " + jsonObjectx_this.get(0));
                            System.out.println("id2 " + ID);

                            if(jsonObjectx_this.get(0).equals(ID) && jsonObjectx_last.get(1).equals(ID)){testx0 = true;}
                            else{testx0 = false;}

                        }//*****************
                        else{testx0 = false;}

                    }//**********************************************************************************************************
                    else{testx0 = false;}

                }catch(Exception e){testx0 = false;}

                System.out.println("testx0 " + testx0);









                if(testx == 1){start_time = Long.parseLong(rs.getString(3));}

                System.out.println("start_time " + start_time);

                Long work_time = (long) Long.parseLong(rs.getString(3)) - start_time;

                status5.setText("Time: " + Long.toString(work_time));



                s2 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                rs2 = s2.executeQuery("SELECT * FROM listings_db WHERE id=" + rs.getString(2));
                while(rs2.next()){

                    System.out.println("get ID " + rs2.getString("seller_id"));

                    testx1 = false;
                    testx2 = false;

                    //get item
                    String[] move_item = new String[listing_size];
                    for(int loop1 = 0; loop1 < listing_size; loop1++){//***********

                        //System.out.println(rs2.getString(loop1 + 2));
                        move_item[loop1] = rs2.getString(loop1 + 2);
                                
                    }//********************************************************************

                    String build_hash = new String();
                    build_hash = build_hash + rs2.getString(2);
                    for(int loop1 = 3; loop1 < listing_size; loop1++){//***********

                        build_hash = build_hash + rs2.getString(loop1 + 2);

                    }//********************************************************************

                    System.out.println("ID " + move_item[0]);
                    System.out.println("build_hash " + build_hash);
                    status2.setText("Item ID: " + move_item[0]);
                    status4.setText("Work Done: " + Long.toString(workx / testx));

                    BigDecimal tmp = new BigDecimal((workx / testx));
                    tmp = tmp.multiply(new BigDecimal(work_time));

                    status6.setText("Work * Time: " + String.valueOf(tmp) + " (Low = Better)");

                    //test item
                    try{

                        byte[] sha256_1w = MessageDigest.getInstance("SHA-256").digest(build_hash.getBytes());

                        System.out.println("TESTX " + Base64.toBase64String(sha256_1w));
                        System.out.println("GIVEN " + move_item[1]);
                        System.out.println("MININ " + rs.getString(9));
                        status3.setText("Hash: " + Base64.toBase64String(sha256_1w));

                        if(Base64.toBase64String(sha256_1w).equals(move_item[1])){testx1 = true;}
                        else{System.out.println("Bad HASH");}


                        byte[] keyxb3 = Base64.decode(move_item[4]);

                        X509EncodedKeySpec keySpecx3 = new X509EncodedKeySpec(keyxb3);
                        KeyFactory factx3 = KeyFactory.getInstance("RSA");
                        PublicKey pubx3 = factx3.generatePublic(keySpecx3);
                        Arrays.fill(keyxb3, (byte) 0);

                        Signature sigpk3 = Signature.getInstance("SHA1WithRSA");//MD5WithRSA
                        byte[] messagex3 = Base64.toBase64String(sha256_1w).getBytes("UTF8");

                        byte[] signatureBytesx3 = Base64.decode(move_item[2]);

                        sigpk3.initVerify(pubx3);
                        sigpk3.update(messagex3);

                        //System.out.println(sigpk3.verify(signatureBytesx3));


                        if(sigpk3.verify(signatureBytesx3)){testx2 = true;}
                        else{System.out.println("Bad SIG");}

                    }catch(Exception e){e.printStackTrace();}


                    if(testx1 && testx2){blocks_verified = true;}
                    else{blocks_verified = false;}


                }//**************


                //test mining hash

                String mining_date = rs.getString(3);
                String mining_old_block = rs.getString(6);
                String hash_id = rs.getString(9);
                String mining_noose = rs.getString(5);


                //String encode = encode_date + old_block_mining_hash + new_block_hash + Integer.toString(noosex);
                String encode = mining_date + mining_old_block + hash_id + mining_noose;

                System.out.println("mining_date " + mining_date);
                System.out.println("mining_old_block " + mining_old_block);
                System.out.println("hash_id " + hash_id);
                System.out.println("mining_noose " + mining_noose);

                //test the block mining

                boolean testm3 = false;
                Long difficultyx = Long.parseLong(rs.getString(4));

                try{


                    byte[] sha256_1 = MessageDigest.getInstance("SHA-256").digest(encode.getBytes());
                    //System.out.println("SHA1 " + bytesToHex(sha256_1));


                    ByteBuffer buffer = ByteBuffer.wrap(sha256_1);
                    buffer.order(ByteOrder.BIG_ENDIAN);  // if you want little-endian
                    long result = buffer.getLong();

                    //System.out.println("value " + value);
                    System.out.println("result " + result);
                    System.out.println("difficultyx " + difficultyx);
                    System.out.println("SHA1 Mining " + bytesToHex(sha256_1));

                    encode = bytesToHex(sha256_1);

                    long package_difficultyx = (long) 0;

                    //if we are building a package then the other items do not need a hard difficulty. 
                    if(is_in_package){package_difficultyx = network.difficultyx_limit;}
                    else{package_difficultyx = difficultyx;}

                    if(result <= package_difficultyx && result > 0){testm3 = true;}
                    else{blocks_verified = false;}

                }catch(Exception e){e.printStackTrace();}


                System.out.println("testm3 " + testm3);



                System.out.println("");
                System.out.println("");

                status7.setText("Blocks Verified: (" + blocks_verified + ")");

                if(!blocks_verified){break;}

                last_package = packagex;

                testx++;

            }//**************


            if(blocks_verified){status7.setText("Blocks Verified PASS. 100%");}

            System.out.println("Blocks Verified: (" + blocks_verified + ")");
            System.out.println("");



            conn.commit();
            System.out.println("Committed the transaction");



        }catch(Exception e){e.printStackTrace();}


        print();

    }//test**********************






    public String bytesToHex(byte[] bytes) {

        char[] hexChars = new char[bytes.length * 2];

        for ( int j = 0; j < bytes.length; j++ ) {

            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];

        }//***************************************

        return new String(hexChars);

    }//********************************************


 


    public void print(){


        try{

            PrintWriter writer = new PrintWriter("blockchian_verify_output.txt", "UTF-8");
            writer.println("" + status1.getText());
            writer.println("" + status2.getText());
            writer.println("" + status3.getText());
            writer.println("" + status4.getText());
            writer.println("" + status5.getText());
            writer.println("" + status6.getText());
            writer.println("" + status7.getText());
            writer.close();

        } catch (IOException e) {
           // do something
        }



    }//*****************











    public void start_driver(){//************************************************************************
            

        /* parse the arguments to determine which framework is desired*/
            //parseArguments(args);

        System.out.println("JDB saving starting in " + framework + " mode");

        /* load the desired JDBC driver */
        loadDriver();



        try{

            Properties props = new Properties(); // connection properties

            // providing a user name and password is optional in the embedded
            // and derbyclient frameworks

            //props.put("user", "user1");
            //props.put("password", "pass1");

            /* By default, the schema APP will be used when no username is
             * provided.
             * Otherwise, the schema name is the same as the user name (in this
             * case "user1" or USER1.)
             *
             * Note that user authentication is off by default, meaning that any
             * user can connect to your database using any password. To enable
             * authentication, see the Derby Developer's Guide.
             */

            String dbName = "KRYPTON"; // the name of the database

            /*
             * This connection specifies create=true in the connection URL to
             * cause the database to be created when connecting for the first
             * time. To remove the database, remove the directory derbyDB (the
             * same as the database name) and its contents.
             *
             * The directory derbyDB will be created under the directory that
             * the system property derby.system.home points to, or the current
             * directory (user.dir) if derby.system.home is not set.
             */


            conn = DriverManager.getConnection(protocol + dbName + ";create=true", props);

            System.out.println("Connected to and created database " + dbName);

            // We want to control transactions manually. Autocommit is on by
            // default in JDBC.
            conn.setAutoCommit(false);

            /* Creating a statement object that we can use for running various
             * SQL statements commands against the database.*/
            s = conn.createStatement();
            statements.add(s);
            s2 = conn.createStatement();
            statements.add(s2);
            //se = conn.createStatement();
            //statements.add(se);





            conn.commit();
            System.out.println("Committed the transaction");

            System.out.println("KRYPTON DATABASE BUILD");



        }catch(Exception se){se.printStackTrace();}


    }//database






    public void shutdown(){
      

        try{

            if(framework.equals("embedded")){

                try{

                    // the shutdown=true attribute shuts down Derby
                    DriverManager.getConnection("jdbc:derby:;shutdown=true");

                    // To shut down a specific database only, but keep the
                    // engine running (for example for connecting to other
                    // databases), specify a database in the connection URL:
                    //DriverManager.getConnection("jdbc:derby:" + dbName + ";shutdown=true");

                }catch(SQLException se){

                    if (( (se.getErrorCode() == 50000) && ("XJ015".equals(se.getSQLState()) ))) {

                        // we got the expected exception
                        System.out.println("Derby shut down normally");
                        // Note that for single database shutdown, the expected
                        // SQL state is "08006", and the error code is 45000.

                    }//**************************************************************************
                    else{

                        // if the error code or SQLState is different, we have
                        // an unexpected exception (shutdown failed)
                        System.err.println("Derby did not shut down normally");
                        printSQLException(se);

                    }

                }//*********************

            }//if****************************


        }catch (Exception sqle){

            sqle.printStackTrace();
            //printSQLException(sqle);

        }finally{

            // release all open resources to avoid unnecessary memory usage
            // ResultSet
            try {

                if(rs != null){
                    rs.close();
                    rs = null;
                }//************

            }catch(SQLException sqle){
                printSQLException(sqle);
            }

            // Statements and PreparedStatements
            int i = 0;
            while (!statements.isEmpty()) {

                // PreparedStatement extend Statement
                Statement st = (Statement)statements.remove(i);

                try{

                    if (st != null) {
                        st.close();
                        st = null;
                    }

                }catch(SQLException sqle){
                    printSQLException(sqle);
                }
            }

            //Connection
            try{

                if (conn != null) {
                    conn.close();
                    conn = null;
                }//****************

            }catch(SQLException sqle){
                printSQLException(sqle);
            }

        }//finally



    }//shutdown










    private void loadDriver() {
        /*
         *  The JDBC driver is loaded by loading its class.
         *  If you are using JDBC 4.0 (Java SE 6) or newer, JDBC drivers may
         *  be automatically loaded, making this code optional.
         *
         *  In an embedded environment, this will also start up the Derby
         *  engine (though not any databases), since it is not already
         *  running. In a client environment, the Derby engine is being run
         *  by the network server framework.
         *
         *  In an embedded environment, any static Derby system properties
         *  must be set before loading the driver to take effect.
         */
        try {
            Class.forName(driver).newInstance();
            System.out.println("Loaded the appropriate driver");
        } catch (ClassNotFoundException cnfe) {
            System.err.println("\nUnable to load the JDBC driver " + driver);
            System.err.println("Please check your CLASSPATH.");
            cnfe.printStackTrace(System.err);
        } catch (InstantiationException ie) {
            System.err.println("\nUnable to instantiate the JDBC driver " + driver);
            ie.printStackTrace(System.err);
        } catch (IllegalAccessException iae) {
            System.err.println("\nNot allowed to access the JDBC driver " + driver);
            iae.printStackTrace(System.err);
        }
    }

    /**
     * Reports a data verification failure to System.err with the given message.
     *
     * @param message A message describing what failed.
     */


    private void reportFailure(String message) {
        System.err.println("\nData verification failed:");
        System.err.println('\t' + message);
    }

    /**
     * Prints details of an SQLException chain to <code>System.err</code>.
     * Details included are SQL State, Error code, Exception message.
     *
     * @param e the SQLException from which to print details.
     */


    public static void printSQLException(SQLException e)
    {
        // Unwraps the entire exception chain to unveil the real cause of the
        // Exception.
        while (e != null)
        {
            System.err.println("\n----- SQLException -----");
            System.err.println("  SQL State:  " + e.getSQLState());
            System.err.println("  Error Code: " + e.getErrorCode());
            System.err.println("  Message:    " + e.getMessage());
            // for stack traces, refer to derby.log or uncomment this:
            //e.printStackTrace(System.err);
            e = e.getNextException();
        }
    }



    /**
     * Parses the arguments given and sets the values of this class' instance
     * variables accordingly - that is which framework to use, the name of the
     * JDBC driver class, and which connection protocol protocol to use. The
     * protocol should be used as part of the JDBC URL when connecting to Derby.
     * <p>
     * If the argument is "embedded" or invalid, this method will not change
     * anything, meaning that the default values will be used.</p>
     * <p>
     * @param args JDBC connection framework, either "embedded", "derbyclient".
     * Only the first argument will be considered, the rest will be ignored.
     */
    private void parseArguments(String[] args)
    {
        if (args.length > 0) {
            if (args[0].equalsIgnoreCase("derbyclient"))
            {
                framework = "derbyclient";
                driver = "org.apache.derby.jdbc.ClientDriver";
                protocol = "jdbc:derby://localhost:1527/";
            }
        }
    }












//start the program.
    public static void main(String[] args) {

        krypton_database_test_full black = new krypton_database_test_full();

    }//main




}//class
