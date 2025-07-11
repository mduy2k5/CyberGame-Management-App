package OtherFunction;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {
    public static String change(String input){
        String returnString = "";
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hasBytes = digest.digest(input.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b: hasBytes){
                hexString.append(String.format("%02x", b));
            }
            returnString = hexString.toString();
        } catch (NoSuchAlgorithmException e){
            System.err.println("An error occurred: " + e.getMessage());
        }
        return returnString;
    }
}
