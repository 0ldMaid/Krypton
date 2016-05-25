import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;  
import java.lang.Object.*;  
import java.net.*;

import java.awt.Toolkit;

import java.security.MessageDigest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Properties;

import java.util.List;





public class krypton_database_get_token_fmh_x{


    String[][] token_ssp2;
    String[][] buffer_ssp1;
    String[][] buffer_ssp2;

    int ix0 = 0;
    int ix1 = 0;
    int ix2 = 0;




    public String[][] get_tokens(String id, int blocks){//**************************************************************************
    network.database_in_use = 1;



        try{




                System.out.println("GET TOKEN FROM MINING HASH X");




                buffer_ssp1 = new String[network.miningx_size][network.package_block_size];
                buffer_ssp2 = new String[network.listing_size][network.package_block_size];


                for(int loop1 = 0; loop1 < network.package_block_size; loop1++){//***********
                for(int loop2 = 0; loop2 < network.listing_size + network.miningx_size; loop2++){//***********

                    try{buffer_ssp1[loop2][loop1] = new String("error");}catch(Exception e){}//only 9
                    try{buffer_ssp2[loop2][loop1] = new String("error");}catch(Exception e){}

                }//*******************************************************************************************
                }//**************************************************************************









                int id_mining = -1;



                krypton_database_driver.s = krypton_database_driver.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                krypton_database_driver.s.setMaxRows(1); 
                krypton_database_driver.rs = krypton_database_driver.s.executeQuery("SELECT xd FROM mining_db WHERE mining_new_block='" + id + "'");

                ix0 = 0;
                while(krypton_database_driver.rs.next()){

                  id_mining = krypton_database_driver.rs.getInt(1);

                }//while





                System.out.println("id_mining " + id_mining);
                List<String> blockx_list = new ArrayList<String>();


                krypton_database_driver.s = krypton_database_driver.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                krypton_database_driver.s.setMaxRows(network.package_block_size); 
                krypton_database_driver.rs = krypton_database_driver.s.executeQuery("SELECT * FROM mining_db WHERE xd > " + id_mining + " ORDER BY xd ASC");

                ix0 = 0;
                while(krypton_database_driver.rs.next()){

                        for(int loop2 = 0; loop2 < network.miningx_size; loop2++){//***********

                            buffer_ssp1[loop2][ix0] = new String(krypton_database_driver.rs.getString((loop2 + 2)));

                        }//********************************************************************

                  System.out.println(krypton_database_driver.rs.getInt("link_id"));
                  blockx_list.add(krypton_database_driver.rs.getString("link_id"));
                  ix0++;

                }//while





                System.out.println("ix0 " + ix0);
                //System.out.println( blockx_list.get(0) );








                System.out.println("Load listings_db... get token FHMX" );

                id = Integer.toString(id_mining);

                String cmdx = new String("");




                for(int loop1 = 0; loop1 < ix0; loop1++){//***********

                    cmdx = cmdx + "id=" + blockx_list.get(loop1);

                    if(loop1 < ix0 -1){cmdx = cmdx + " OR ";}

                }//****************************************************


                if(ix0 == 0){cmdx = cmdx + "id=0";}


                boolean found_item = false;
                if(ix0 > 0){found_item = true;}
                int ixp0 = 0;

                System.out.println("cmdx " + cmdx);
                krypton_database_driver.rs = krypton_database_driver.s.executeQuery("SELECT * FROM listings_db WHERE " + cmdx + " ORDER BY xd ASC");

                while(krypton_database_driver.rs.next()){

                        for(int loop2 = 0; loop2 < network.listing_size; loop2++){//***********

                            buffer_ssp2[loop2][ixp0] = new String(krypton_database_driver.rs.getString((loop2 + 2)));

                        }//********************************************************************

                ixp0++;

                }//while






                System.out.println("ixp0 " + ixp0);
                System.out.println("found_item " + found_item);


                int pp = 0;
                token_ssp2 = new String[network.listing_size + network.miningx_size][ixp0];

                for(int loop1 = 0; loop1 < ixp0; loop1++){//*********************************

                    pp = 0;

                        for(int loop2 = 0; loop2 < network.miningx_size; loop2++){//***********

                            token_ssp2[pp][loop1] = buffer_ssp1[loop2][loop1];
                            pp++;

                        }//********************************************************************

                        for(int loop2 = 0; loop2 < network.listing_size; loop2++){//***********

                            token_ssp2[pp][loop1] = buffer_ssp2[loop2][loop1];
                            pp++;

                        }//********************************************************************

                }//**************************************************************************



                System.out.println("ixp0 " + ixp0);
	            System.out.println("found_item " + found_item);


                System.out.println("");



                krypton_database_driver.conn.commit();
                System.out.println("Committed the transaction");



        }catch(Exception e){e.printStackTrace();}


    network.database_in_use = 0;
    return token_ssp2;

    }//load




 

}//class
