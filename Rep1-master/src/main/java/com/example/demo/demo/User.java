package com.example.demo.demo;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "age")
    private Integer age;
    public User ( String username,
            String password,
           Integer age) {
        this.age = age;
        this.username = username;
        this.password = password;
    }
    public boolean checkUsername() {
        char[] username = this.username.toCharArray();
        int a = 0;
        for (char c : username) { // проверка того, подходят ли символы username под условие
           if ((c >= 65 && c < 91) || (c>=97 && c<=122)) {
                a += 1;
            }
        }
        if (a==username.length) {
            return true;
        }
        return false;
    }
    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return password;
    }

    public Integer getAge() {
        return age;
    }

    public long getId() {
        return id;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public User(String username,int age) {
        this.username = username;
        this.age = age;
        this.password = null;
    }
}
