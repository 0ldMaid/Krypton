import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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







public class krypton_database_import_keys{



    int ix0 = 0;
    int ix1 = 0;

    String cx0 = new String();
    String cx1 = new String();
    String cx2 = new String();

    int worm_size = 10;
    String base58 = new String("");
    KeyPair keyPair;



    krypton_database_import_keys(String keyx){//************************************************************************
    network.database_in_use = 1;




        try{


            System.out.println("KRYPTON IMPORT KEY.");



	        ix0 = 0;
	        ix1 = 20000;




            network.settingsx[5] = keyx;


	        try{




                    byte[] clear = Base64.decode(keyx);
                    PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(clear);
                    KeyFactory fact = KeyFactory.getInstance("RSA");
                    PrivateKey privateKey = fact.generatePrivate(keySpec);
                    Arrays.fill(clear, (byte) 0);



                    KeyFactory kf = KeyFactory.getInstance("RSA");
                    RSAPrivateKeySpec priv = kf.getKeySpec(privateKey, RSAPrivateKeySpec.class);

                    RSAPublicKeySpec keySpecx = new RSAPublicKeySpec(priv.getModulus(), BigInteger.valueOf(65537));

                    PublicKey publicKey = kf.generatePublic(keySpecx);



                    base58 = Base64.toBase64String(publicKey.getEncoded());


                    int len = base58.length();
                    byte[] data = new byte[len / 2];
                    for (int i = 0; i < len; i += 2) {
                        data[i / 2] = (byte) ((Character.digit(base58.charAt(i), 16) << 4) + Character.digit(base58.charAt(i+1), 16));
                    }


                    byte[] sha256_1 = MessageDigest.getInstance("SHA-256").digest(data);

                    base58 = Base58Encode.encode(sha256_1);

                    System.out.println("base58 " + base58);




                    ByteArrayOutputStream out = QRCode.from(base58).to(ImageType.PNG).withSize(156, 156).stream();
 
                    try {

                        FileOutputStream fout = new FileOutputStream(new File(base58 + ".png"));
 
                        fout.write(out.toByteArray());
 
                        fout.flush();
                        fout.close();
 
                    } catch (FileNotFoundException e) {
                        System.out.println(e.getMessage());
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }






                    System.out.println("");

                    network.base58_id = base58;

                    //network.settingsx[5] = Base64.toBase64String(keyPair.getPrivate().getEncoded());
                    network.settingsx[6] = Base64.toBase64String(publicKey.getEncoded());


                    krypton_database_driver.s.execute("UPDATE settings set valuex='" + Base64.toBase64String(privateKey.getEncoded()) + "' where id=" + 5);
                    System.out.println("1");
                    krypton_database_driver.s.execute("UPDATE settings set valuex='" + Base64.toBase64String(publicKey.getEncoded()) + "' where id=" + 6);
                    System.out.println("2");

	                System.out.println("DBsx");





	        }catch(Exception e){e.printStackTrace(); System.out.println("Krypton build already in use.");}



            krypton_database_driver.conn.commit();
            System.out.println("Committed the transaction");

        }catch(Exception e){e.printStackTrace();}



    network.database_in_use = 0;
    }




}//class
