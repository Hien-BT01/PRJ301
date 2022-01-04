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
public class UserDTO {
    private String username;
    private String fullName;
    private String address;
    private String phone;
    private String password;
    private String roleID;
    private String gender;

    public UserDTO() {}

    public UserDTO(String username, String fullName, String address, String phone, String password, String roleID, String gender) {
        this.username = username;
        this.fullName = fullName;
        this.address = address;
        this.phone = phone;
        this.password = password;
        this.roleID = roleID;
        this.gender = gender;
    }

    public UserDTO(String username, String fullName, String address, String phone, String password, String gender) {
        this.username = username;
        this.fullName = fullName;
        this.address = address;
        this.phone = phone;
        this.password = password;
        this.gender = gender;
    }
    
    public UserDTO(String username, String fullName, String password, String roleID) {
        this.username = username;
        this.fullName = fullName;
        this.password = password;
        this.roleID = roleID;
    }

    
    public String getUsername() {
        return username;
    }

    public String getFullName() {
        return fullName;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    public String getRoleID() {
        return roleID;
    }

    public String getGender() {
        return gender;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    
}
