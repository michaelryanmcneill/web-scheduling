package com.example.helloworld.core;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "userTable")
@NamedQueries(
        {
            @NamedQuery(
                name = "com.example.helloworld.core.User2.findAll",
                query = "SELECT p FROM User2 p"
            ),@NamedQuery(
                name = "com.example.helloworld.core.User2.nameandpassword",
                query = "SELECT p FROM User2 p where p.name = :name "+ "and p.password = :password"
            )
        }
    )
public class User2  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(name = "name", nullable = false)
    private  String name;
    @Column(name = "role", nullable = false)
    private  String role;
    @Column(name = "password", nullable = false)
    private  String password;

    public User2(){

    }
    public User2(String name, String role,String password) {
        this.name = name;
        this.role = role;
        this.password = password;
    }

    public void setPassword(String password){
        this.password= password;
    }

    public String getPassword(){
        return password;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

     public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public void setRole(String role) {
        this.role= role;
    }
    public String getRole() {
        return role;
    }
}
