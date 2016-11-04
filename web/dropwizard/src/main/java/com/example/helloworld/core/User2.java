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
                name = "com.example.helloworld.core.User2.name",
                query = "SELECT p FROM User2 p where p.name = :name"
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

    public User2(){

    }
    public User2(String name, String role) {
        this.name = name;
        this.role = role;
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
