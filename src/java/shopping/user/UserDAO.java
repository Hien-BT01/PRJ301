/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopping.user;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import shopping.helper.utils;

/**
 *
 * @author HP
 */
public class UserDAO{
    private static final String LOGIN = "SELECT fullName, password, roleID FROM Users WHERE userName =? AND password =?";
    private static final String GET_USER = "SELECT fullName, password, roleID FROM Users WHERE userName =?";
    private static final String CREATE_PRODUCT = "INSERT INTO Users(userName, fullName, address, phone, password, roleID, gender) VALUES(?,?,?,?,?,?,?)";
    
    public boolean createUser(UserDTO user) throws SQLException{
        boolean rowEffected = false;
        Connection connection = null;
        PreparedStatement pstm = null;
        try {
            connection = utils.getConnection();
            if (connection != null) {
                String fullN = user.getFullName();
                pstm = connection.prepareStatement(CREATE_PRODUCT);
                pstm.setNString(1, user.getUsername());
                pstm.setNString(2, user.getFullName());
                pstm.setNString(3, user.getAddress());
                pstm.setNString(4, user.getPhone());
                pstm.setString(5, user.getPassword());
                pstm.setNString(6, user.getRoleID());
                pstm.setString(7, user.getGender());
                rowEffected = pstm.executeUpdate() > 0;
            }
        } catch (Exception event) {
            event.printStackTrace();
        } finally {
            if (pstm != null) {
                pstm.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return rowEffected;
    }
    
    public UserDTO getUser(String userName) throws SQLException{
        UserDTO user = null;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet resultSet = null;
        try{
            conn = utils.getConnection();
            if(conn != null){
                pstm = conn.prepareStatement(GET_USER);
                pstm.setString(1, userName);
                resultSet = pstm.executeQuery();
                if (resultSet.next()) {
                    String fullName = resultSet.getString("fullName");
                    String password = resultSet.getString("password");
                    String roleID = resultSet.getString("roleID");
                    user = new UserDTO(userName, fullName, password, roleID);
                }
            }
        }catch(Exception event){
            event.printStackTrace();
        }finally{
            if (resultSet != null) {
                resultSet.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return user;
    }
    
    public UserDTO checkLogin(String userName,String userPassword) throws SQLException{
        UserDTO user = null;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet resultSet = null;
        try{
            conn = utils.getConnection();
            if(conn != null){
                pstm = conn.prepareStatement(LOGIN);
                pstm.setString(1, userName);
                pstm.setString(2, userPassword);
                resultSet = pstm.executeQuery();
                if (resultSet.next()) {
                    String fullName = resultSet.getString("fullName");
                    String password = resultSet.getString("password");
                    String roleID = resultSet.getString("roleID");
                    user = new UserDTO(userName, fullName, password, roleID);
                }
            }
        }catch(Exception event){
            event.printStackTrace();
        }finally{
            if (resultSet != null) {
                resultSet.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return user;
    }
}
