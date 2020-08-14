/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giangnth.dtos;

/**
 *
 * @author Dell
 */
public class UserDTO {
    private String email , password ;
    private String role, name, status ;

    public UserDTO() {
    }

    // For login
    public UserDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }
    // For register
    public UserDTO(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name ;
    }
    //for checkLogin and getRole
    public UserDTO(String email, String role, String name, String status) {
        this.email = email;
        this.role = role;
        this.name = name;
        this.status = status;
    }
    

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}
