package com.example.agile.models;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;



import org.hibernate.annotations.ManyToAny;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;


@Entity
@Table(name="users")



public class ApplicationUser  implements UserDetails{

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="user_id")
    
    private Integer userId;
    
    private String username;

    private String password;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
        name="user_role_junction",
        joinColumns = { @JoinColumn (name = "user_id") },
        inverseJoinColumns = {@JoinColumn(name="role_id")}
    )
    private Set<Role> authorities;
    
    
    public ApplicationUser(){
        super();
        this.authorities= new HashSet<Role>();
    }

    public ApplicationUser(Integer userId, String username, String password, Set<Role> authorities ){
        super();
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.authorities= authorities;
    }

    public Integer getUserId(){
        return this.userId;
    }
    public void setUserId(Integer userId){
        this.userId = userId;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
        }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
}
