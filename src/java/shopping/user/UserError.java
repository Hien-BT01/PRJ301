/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopping.user;

/**
 *
 * @author HP
 */
public class UserError {
    private String userName;
    private String fullName;
    private String phoneNumber;
    private String address;
    private String password;
    private String confirm;

    public UserError() {
        this.userName = "";
        this.fullName = "";
        this.phoneNumber = "";
        this.address = "";
        this.password = "";
        this.confirm = "";
    }

        
    public UserError(String userName, String fullName, String phoneNumber, String address, String password, String confirm) {
        this.userName = userName;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.password = password;
        this.confirm = confirm;
    }

    public String getUserName() {
        return userName;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }
    
}
