package com.example.agile.models;

import java.time.Instant;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;



import org.hibernate.annotations.ManyToAny;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
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
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="user_id")
    
    private Long userId;
    @Column(unique=true)
    
    private String username;

    private String password;
    
    @CreatedDate
    private Instant createdAt;
    @LastModifiedDate
    private Instant updatedAt;

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

    public ApplicationUser(Long userId, String username, String password, Set<Role> authorities ){
        super();
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.authorities= authorities;
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }

    public Long getUserId(){
        return this.userId;
    }
    public void setUserId(Long userId){
        this.userId = userId;
    }

    public void setUsername(String username){
        this.username = username;
    }
    

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    public void setAuthorities(Set<Role> authorities) {
        this.authorities = authorities;
    }

    //@Override
    //public Collection<? extends GrantedAuthority> setAuthorities(authorities) {
    //    this.authorities = authorities;
    //}

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

    public void setCreatedAt(Instant timenow){
        this.createdAt = timenow;
    }
    public Instant getCreatedAt(){
        return this.createdAt;
    }
    public void setUpdatedAt(Instant timenow){
        this.updatedAt = timenow;
    }
    public Instant getUpdatedAt(){
        return this.updatedAt;
    }
    
}
