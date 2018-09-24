/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;
import org.apache.tomcat.util.codec.binary.Base64;
/**
 *
 * @author Vinayak
 */
public class PasswordUtil {
    public static String hashPassword(String password) 
            throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(password.getBytes());
        byte[] mdArray = md.digest();
        StringBuilder sb = new StringBuilder(mdArray.length *2);
        for (byte b : mdArray) {
            int v = b & 0xff;
            if (v < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(v));
        }
        return sb.toString();
    }
    
    public static String getSaltHashPassword(String salt, String password){
        String saltHash = null;
        try {
            saltHash = hashPassword(password + salt);
        } catch (NoSuchAlgorithmException ex) {
            System.err.println(ex);
        }
        return saltHash;
    }
    
    public static String getSalt(){
        Random r = new SecureRandom();
        byte[] saltBytes = new byte[32];
        r.nextBytes(saltBytes);
        return Base64.encodeBase64String(saltBytes);
    }    
}

