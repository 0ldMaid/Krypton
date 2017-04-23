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

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.ArrayList;

import org.spongycastle.util.encoders.Base64;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.math.BigInteger;




public class x_database_test extends JFrame{

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


x_database_test(){//**************************************************************************
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


            boolean testx1 = false;
            boolean testx2 = false;

            System.out.println("TESTING BLOCKCHAIN...");

            int total_items = 0;
            int testx = 1;

            //test
            System.out.println("START TEST");
            int displaypx = 100;

            long workx = (long) 0;
            Long start_time = (long) 0;




            s = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            s.setMaxRows(100);
            rs = s.executeQuery("SELECT * FROM mining_db WHERE xd < " + 118259 + " ORDER BY xd DESC");

            rs.last();
            total_items = rs.getRow();
            System.out.println("total_items " + total_items);
            rs.beforeFirst();

            ix0 = 0;
            while(rs.next()){

                System.out.println(rs.getString(1));
                //System.out.println(rs.getString(2));//id
                //System.out.println(rs.getString(3));//date
                //System.out.println(rs.getString(4));//work
                //System.out.println(rs.getString(5));//mining_noose
                //System.out.println(rs.getString(6));//mining_old_block
                //System.out.println(rs.getString(7));//mining_new_block
                //System.out.println(rs.getString(8));//previous_hash_id
                //System.out.println(rs.getString(9));//hash
                //System.out.println(rs.getString(10));//sig
                //System.out.println("");

                ix0++;

            }//**************

            System.out.println("ix0 " + ix0);

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

        x_database_test black = new x_database_test();

    }//main




}//class
