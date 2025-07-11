package com.raven.service;

import OtherFunction.Hash;
import com.raven.dbfunction.*;
import com.raven.model.ModelLogin;
import com.raven.model.ModelUser;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Random;

public class ServiceUser {

    private final Connection con;

    public ServiceUser() throws SQLException {
        con = DBConnection.getConnection();
    }

    public ModelUser login(ModelLogin login) throws SQLException {
        ModelUser data = null;
        PreparedStatement p = con.prepareStatement("SELECT USERNAME, PASSWORD_HASH, USER_ID FROM ACCOUNT WHERE USERNAME = ? AND IS_DELETE = 0");
        p.setString(1, login.getEmail());
        // p.setString(2, login.getPassword());
        
        ResultSet r = p.executeQuery();
        if (r.next()) {
            String passHash = r.getString("PASSWORD_HASH");
            String pass = Hash.change(login.getPassword());
            if (pass.equals(passHash)){
                String userName = r.getString(1);
                //String email = r.getString(1);
                String userID = r.getString(3);
                
                PreparedStatement p2 = con.prepareStatement("SELECT rg.GROUP_NAME FROM ACCOUNT ac JOIN ACCOUNT_ROLE_GROUP arg ON AC.ACCOUNT_ID = ARG.ACCOUNT_ID JOIN ROLE_GROUP rg ON ARG.GROUP_ID = RG.GROUP_ID WHERE AC.USER_ID = ?");
                p2.setString(1, userID);
                // p.setString(2, login.getPassword());
                ResultSet r2 = p2.executeQuery();
                if (r2.next()){
                    String quyenhan = r2.getString(1);
                    data = new ModelUser(userID, userName, "", "",quyenhan);
                }
                
            }
            
            
        }
        r.close();
        p.close();
        return data;
    }

    public void insertUser(ModelUser user) throws SQLException {
        PreparedStatement p = con.prepareStatement("insert into `user` (UserName, Email, `Password`, VerifyCode) values (?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
        String code = generateVerifyCode();
        p.setString(1, user.getUserName());
        p.setString(2, user.getEmail());
        p.setString(3, user.getPassword());
        p.setString(4, code);
        p.execute();
        ResultSet r = p.getGeneratedKeys();
        r.first();
        String userID = r.getString(1);
        r.close();
        p.close();
        user.setUserID(userID);
        user.setVerifyCode(code);
    }

    private String generateVerifyCode() throws SQLException {
        DecimalFormat df = new DecimalFormat("000000");
        Random ran = new Random();
        String code = df.format(ran.nextInt(1000000));  //  Random from 0 to 999999
        while (checkDuplicateCode(code)) {
            code = df.format(ran.nextInt(1000000));
        }
        return code;
    }

    private boolean checkDuplicateCode(String code) throws SQLException {
        boolean duplicate = false;
        PreparedStatement p = con.prepareStatement("select UserID from `user` where VerifyCode=? limit 1");
        p.setString(1, code);
        ResultSet r = p.executeQuery();
        if (r.first()) {
            duplicate = true;
        }
        r.close();
        p.close();
        return duplicate;
    }

    public boolean checkDuplicateUser(String user) throws SQLException {
        boolean duplicate = false;
        PreparedStatement p = con.prepareStatement("select UserID from `user` where UserName=? and `Status`='Verified' limit 1");
        p.setString(1, user);
        ResultSet r = p.executeQuery();
        if (r.first()) {
            duplicate = true;
        }
        r.close();
        p.close();
        return duplicate;
    }

    public boolean checkDuplicateEmail(String user) throws SQLException {
        boolean duplicate = false;
        PreparedStatement p = con.prepareStatement("select UserID from `user` where Email=? and `Status`='Verified' limit 1");
        p.setString(1, user);
        ResultSet r = p.executeQuery();
        if (r.first()) {
            duplicate = true;
        }
        r.close();
        p.close();
        return duplicate;
    }

    public void doneVerify(int userID) throws SQLException {
        PreparedStatement p = con.prepareStatement("update `user` set VerifyCode='', `Status`='Verified' where UserID=? limit 1");
        p.setInt(1, userID);
        p.execute();
        p.close();
    }

    public boolean verifyCodeWithUser(int userID, String code) throws SQLException {
        boolean verify = false;
        PreparedStatement p = con.prepareStatement("select UserID from `user` where UserID=? and VerifyCode=? limit 1");
        p.setInt(1, userID);
        p.setString(2, code);
        ResultSet r = p.executeQuery();
        if (r.first()) {
            verify = true;
        }
        r.close();
        p.close();
        return verify;
    }
}
