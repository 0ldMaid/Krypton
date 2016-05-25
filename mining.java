import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;  
import java.lang.Object.*;  
import java.net.*;

import java.security.spec.PKCS8EncodedKeySpec;
import java.security.KeyPair;
import java.security.KeyFactory;
import java.security.KeyPairGenerator;
import java.security.Signature;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Arrays;

import java.awt.Toolkit;
import java.util.Timer;
import java.util.TimerTask;

import java.security.MessageDigest;

import org.spongycastle.util.encoders.Base64;
import org.spongycastle.crypto.digests.SHA256Digest;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.math.BigInteger;


public class mining{

Timer xtimerx;//class loop.
Toolkit toolkit;

final protected static char[] hexArray = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();

int noosex = 0;
String encode_hash = new String("");

static String encode = new String("");
static String encode2 = new String("");
static String encode_date = new String("");
static String new_block_id = new String("");
static String new_block_hash = new String("");
static String old_block_mining_hash = new String("");
static String mining_noose = new String("");

boolean mining1 = true;//should we mine at all?
boolean mining2 = true;//this is to test if there is anything to mine for.
static boolean mining3 = true;//database is working.
static int mining_stop = 0;//turn off and restart...
boolean mining_old_chain = false;//move the chain along....

String tokenx[] = new String[network.listing_size];


public mining(){

	while(network.tor_active == 0){

    	System.out.println("Wait for TOR to mine....");
		try{Thread.sleep(1000);} catch (InterruptedException e){}

    }//*********************************

	toolkit = Toolkit.getDefaultToolkit();
	xtimerx = new Timer();
	xtimerx.schedule(new RemindTask_mining(), 0);

}//makek****




public static String bytesToHex(byte[] bytes) {
    char[] hexChars = new char[bytes.length * 2];

    for ( int j = 0; j < bytes.length; j++ ) {
        int v = bytes[j] & 0xFF;
        hexChars[j * 2] = hexArray[v >>> 4];
        hexChars[j * 2 + 1] = hexArray[v & 0x0F];
    }//***************************************

    return new String(hexChars);
}//********************************************








class RemindTask_mining extends TimerTask{
Runtime rxrunti = Runtime.getRuntime();

public void run(){//************************************************************************************

	System.out.println("Start Mining");

	//this always stays on
	while(mining1){

		mining_stop = 0;
		if(mining2 && mining3){
			
			if(network.database_in_use != 1 && network.xmining == 1){new_hash();}

		}//if******************


		System.out.println("network.xmining " + network.xmining);
		System.out.println("network.database_listings_total " + network.database_listings_total);

		//mining test
		while(network.xmining == 1 && network.database_listings_total >= network.hard_token_limit){
		//System.out.println(">>>");


				//someone already found this block or user abort
				if(mining_stop == 1){System.out.println("Break mining stop 1"); break;}//network.status_x2.setIcon(network.imx0);
				//someone already found this block

				//testings for something to mine for
				if(!mining2){System.out.println("Break mining stop 2 mining 2"); break;}//network.status_x2.setIcon(network.imx0);
				//testings for something to mine for

				//testings for something to mine for
				if(!mining3){System.out.println("Break mining stop 3 mining 3"); break;}//network.status_x2.setIcon(network.imx0);
				//testings for something to mine for

				//testings for internet
				if(network.peersx0 == 0 && network.new_database_start == 0){System.out.println("mining net_client 0"); break;}//network.status_x2.setIcon(network.imx0); 
				//testings for internet

				//testings for status
				if(!krypton_net_client.blocks_uptodate){System.out.println("mining old blocks"); break;}//network.status_x2.setIcon(network.imx0);
				//testings for status

				//restarting
				if(krypton_net_client.breakx2 > 130){System.out.println("TOR is about to restart"); break;}
				//restarting


			if(mining1 == true){network.mining_status = 1;}
			else{network.mining_status = 0;}

			mine();
			try{Thread.sleep(network.mining_speed);} catch(InterruptedException e){e.printStackTrace();}


		}//while**********************************************************************************


		//if(!mining2){network.status_x2.setIcon(network.imx0);}
		//else if(!mining3){network.status_x2.setIcon(network.imx0);}
		//else if(network.peersx1 == 0){network.status_x2.setIcon(network.imx0);}
		//else if(!krypton_net_client.blocks_uptodate){network.status_x2.setIcon(network.imx0);}
		//else if(krypton_net_client.breakx2 > 130){network.status_x2.setIcon(network.imx0);}
		//else if(mining_stop == 0 && network.xmining == 1){network.status_x2.setIcon(network.imx1);}
		//else{network.status_x2.setIcon(network.imx0);}

		try{Thread.sleep(10000);} catch (InterruptedException e){}

	}//while*******

}//runx*************************************************************************************************
}//remindtask












	public void new_hash(){


		int test_db = 0;
		while(network.database_in_use == 1){

    			System.out.println("Database in use...mmh");
				try{Thread.sleep(1000);} catch (InterruptedException e){}
				test_db++;
				if(test_db > 20){break;}

    	}//*********************************


		mining2 = false;

		encode_date = Long.toString(System.currentTimeMillis());

		new_block_id = ("");
		new_block_hash = ("");
		old_block_mining_hash = ("");

		int tests1 = 0;

		mining_new_task new_x_task = new mining_new_task();
		tokenx = new_x_task.new_task();

		krypton_database_load loaddb = new krypton_database_load();

		try{

			tests1 = Integer.parseInt(new_block_id);
			//mining_old_chain = false;

		}catch(Exception e){tests1 = 0; mining_old_chain = true;}




		//for first build
		if(network.new_database_start == 1){mining_old_chain = false;}
		if(network.database_unconfirmed_total == 0){mining_old_chain = true;}

		System.out.println("tests1 " + tests1);
		System.out.println("mining_old_chain " + mining_old_chain);



		if(!mining_old_chain && tests1 > 0){

			System.out.println("Standard mining");
		

			if(network.new_database_start == 0 && tests1 > 0){
				System.out.println("");
				krypton_database_verify_id verifyx = new krypton_database_verify_id(new_block_id);
				System.out.println("");
			}//if*********************************************

			System.out.println("tokenx IDX1 " + tokenx[0]);

			//update block to the new chain
			old_block_mining_hash = network.last_block_mining_idx;

			//krypton_database_print_blocks print = new krypton_database_print_blocks();

			System.out.println("encode_date " + encode_date);
			System.out.println("new_block_id " + new_block_id);
			System.out.println("new_block_hash " + new_block_hash);
			System.out.println("old_block_mining_hash " + old_block_mining_hash);
			System.out.println("network.last_block_mining_idx " + network.last_block_mining_idx);

			mining_old_chain = true;
			mining2 = true;

		}//if*****************
		else{

			//if nothing to work on or we need to move the chain along
			System.out.println("Moving chain");

			mining_new_task_c new_x_task_c = new mining_new_task_c();
			tokenx = new_x_task_c.new_task_c();

			System.out.println("tokenx IDX2 " + tokenx[0]);

			//update block to the new chain
			old_block_mining_hash = network.last_block_mining_idx;

			//krypton_database_print_blocks print = new krypton_database_print_blocks();

			System.out.println("encode_date " + encode_date);
			System.out.println("new_block_id " + new_block_id);
			System.out.println("new_block_hash " + new_block_hash);
			System.out.println("old_block_mining_hash " + old_block_mining_hash);
			System.out.println("network.last_block_mining_idx " + network.last_block_mining_idx);

			mining_old_chain = false;
			mining2 = true;

		}//*********************************




	}//********************







	public void mine(){

	//System.out.println("Looking...");


		encode = encode_date + old_block_mining_hash + new_block_hash + Integer.toString(noosex);

		mining_noose = Integer.toString(noosex);

		


		try{



			byte[] sha256_1 = MessageDigest.getInstance("SHA-256").digest(encode.getBytes());
			//System.out.println("SHA1 " + bytesToHex(sha256_1));


			ByteBuffer buffer = ByteBuffer.wrap(sha256_1);
			buffer.order(ByteOrder.BIG_ENDIAN);  // if you want little-endian
			long result = buffer.getLong();
			//Double result2 = buffer.getDouble();

			//System.out.println("value " + value);
			//System.out.println("result " + result);
			//System.out.println("result2 " + result2);
			//System.out.println("SHA1 " + bytesToHex(sha256_1));

			encode = bytesToHex(sha256_1);

			if(result < network.difficultyx && result > 0){

				try{update();}catch(Exception e){mining_stop = 1;}

			}//********************************************
			else{noosex++;}



		}catch(Exception e){e.printStackTrace(); mining_stop = 1;}




	}//****************








	public void update(){

	Long thisTick = System.currentTimeMillis();


		int test_db = 0;
		while(network.database_in_use == 1){

    			System.out.println("Database in use... >>>");
				try{Thread.sleep(1000);} catch (InterruptedException e){}
				test_db++;
				if(test_db > 20){break;}

    	}//*********************************


		//what are we sending?
		System.out.println("encode_date " + encode_date);
		System.out.println("old_block_mining_hash " + old_block_mining_hash);
		System.out.println("new_block_hash " + new_block_hash);
		System.out.println("noosex " + Integer.toString(noosex));


		System.out.println("new block! " + encode);

		try{

			if(encode.length() > 0 && mining.new_block_id.length() > 0){
			
				//mining_new_block xxblock = new mining_new_block(tokenx);

				//network.status_x1.setText("Found new block! (" + mining.new_block_id + ")");

            	String[] move_item = tokenx;
            	String[] mining_item = new String[9];

				mining_item[0] = move_item[0];
            	mining_item[1] = mining.encode_date;
            	mining_item[2] = Long.toString(network.difficultyx);
            	mining_item[3] = mining.mining_noose;
            	mining_item[4] = mining.old_block_mining_hash;
            	mining_item[5] = encode;
            	mining_item[6] = "";
            	mining_item[7] = new_block_hash;
            	mining_item[8] = move_item[2];


					if(network.new_database_start == 0){

							String testerx = new String("error");

							for (int loop = 0; loop < network.listing_size; loop++){//************
								try{ if(move_item[loop].equals("0")){} }catch(Exception e){testerx = "1";}
							}//*******************************************************************


							int testxp = 0;
							while(!testerx.equals("1")){

                                mining_stop = 1;//someone has found the block, go to the next task.

								System.out.println("Mining send new block update >>>>");

								String testg = krypton_net_client.send_new_block_update(mining_item, move_item);
								System.out.println("testg " + testg);

								if(testg.equals("1") || testg.equals("0")){System.out.println("BREAK"); break;}
								if(testxp > 3){break;}

								try{Thread.sleep(30000);} catch (InterruptedException e){}

								testxp++;

							}//while

					}//*********************************
					else{

						System.out.println("UPDATE TT");

						System.out.println("move_item[0] " + move_item[0]);

						krypton_database_get_token getxt = new krypton_database_get_token();
        				String req_id = move_item[0];
        				String old_token[] = new String[network.listing_size];
        				old_token = getxt.get_token(req_id);

        				System.out.println("old_token " + old_token[0]);

						krypton_update_new_block_remote remotexu2 = new krypton_update_new_block_remote();
						boolean test2 = remotexu2.update(move_item, mining_item, old_token);

						noosex = 0;
						new_hash();
						System.out.println("UPDATE TTD");

					}//**


			}//if*******************************************************
			else{System.out.println("Add block ERROR 0003"); mining_stop = 1;}

		}catch(Exception e){e.printStackTrace(); mining_stop = 1;}

		network.dbxmine_longstamp = System.currentTimeMillis() - thisTick;

	}//******************



















//start the program.
    public static void main(String[] args) {

	mining gold = new mining();

    }//main







}//last