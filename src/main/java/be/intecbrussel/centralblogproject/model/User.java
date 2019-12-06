package be.intecbrussel.centralblogproject.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userId;
    private String userName;
    private String fullName;
    private String password;
    private String adress;
    private String email;

    public void cloneFrom(User user) {
        this.userId = user.userId;
        this.userName = user.userName;
        this.fullName = user.fullName;
        this.password = user.password;
        this.adress = user.adress;
        this.email = user.email;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        while (!email.matches("^[A-Z0-9+_.-]+@[A-Z0-9.-]+$\n")) {
            System.out.println("Wrong email-format");
            setEmail(email);
        }
        this.email = email;
    }


}



