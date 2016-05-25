import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.CallableStatement;

import java.util.ArrayList;
import java.util.Properties;

import java.io.*;
import java.security.*;

import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.spec.RSAPrivateCrtKeySpec;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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

import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

import org.spongycastle.util.encoders.Base64;

import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;







public class krypton_database_driver{

    /* the default framework is embedded*/
    static String framework = "embedded";
    static String driver = "org.apache.derby.jdbc.EmbeddedDriver";
    static String protocol = "jdbc:derby:";


    int ix0 = 0;
    int ix1 = 0;

    String cx0 = new String();
    String cx1 = new String();
    String cx2 = new String();

    int worm_size = 10;
    String base58 = new String("");
    KeyPair keyPair;



    static ArrayList<Statement> statements = new ArrayList<Statement>(); // list of Statements, 
    static PreparedStatement psInsert = null;
    static PreparedStatement psUpdate = null;

    static Statement s = null;//load network
    static Statement s2 = null;//load
    static Statement se = null;//edit

    static ResultSet rs = null;
    static ResultSet rs2 = null; 
    static ResultSet rse = null;//edit



    static Connection conn = null;





    krypton_database_driver(){//************************************************************************
    network.database_in_use = 1;

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
            se = conn.createStatement();
            statements.add(se);




            CallableStatement cs = krypton_database_driver.conn.prepareCall("CALL SYSCS_UTIL.SYSCS_COMPRESS_TABLE(?, ?, ?)");
            cs.setString(1, "APP");
            cs.setString(2, "LISTINGS_DB");
            cs.setShort(3, (short) 1);
            cs.execute();


            //CallableStatement cs = krypton_database_driver.conn.prepareCall("CALL SYSCS_UTIL.SYSCS_COMPRESS_TABLE(?, ?, ?)");
            cs.setString(1, "APP");
            cs.setString(2, "UNCONFIRMED_DB");
            cs.setShort(3, (short) 1);
            cs.execute();


            //CallableStatement cs = conn.prepareCall("CALL SYSCS_UTIL.SYSCS_COMPRESS_TABLE(?, ?, ?)");
            cs.setString(1, "APP");
            cs.setString(2, "MINING_DB");
            cs.setShort(3, (short) 1);
            cs.execute();


            //CallableStatement cs = conn.prepareCall("CALL SYSCS_UTIL.SYSCS_COMPRESS_TABLE(?, ?, ?)");
            cs.setString(1, "APP");
            cs.setString(2, "BACKUP_DB");
            cs.setShort(3, (short) 1);
            cs.execute();


            //CallableStatement cs = conn.prepareCall("CALL SYSCS_UTIL.SYSCS_COMPRESS_TABLE(?, ?, ?)");
            cs.setString(1, "APP");
            cs.setString(2, "SETTINGS");
            cs.setShort(3, (short) 1);
            cs.execute();





            conn.commit();
            System.out.println("Committed the transaction");

            System.out.println("KRYPTON DATABASE BUILD");




        }catch(SQLException se){}


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


        network.database_in_use = 0;


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




}//database driver
