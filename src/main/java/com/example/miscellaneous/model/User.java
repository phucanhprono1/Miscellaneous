package com.example.miscellaneous.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@DiscriminatorColumn(name="user_type")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "tbl_user")
@ToString
@Data
public class User implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, name = "id")
    private Integer id;
    @Column(unique = true, name = "email")
    public String email;
    @Column(name = "password")
    private String password;
    @Column(name = "gender")
    private String gender;
    @Column(unique = true, name = "username")
    private String username;
    @Column(name = "user_type", insertable = false, updatable = false)
    private String user_type;
    private String otp;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
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
    @Override
    public String getUsername(){
        return email;
    };

}