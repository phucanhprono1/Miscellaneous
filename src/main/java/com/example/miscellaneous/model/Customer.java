package com.example.miscellaneous.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Getter
@Setter
@Entity
@AllArgsConstructor
@Table(name="tbl_customer")
@NoArgsConstructor
@Data
public class Customer  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id", nullable = false)
    private Integer id;

    private String address;
    private String phone;
    @OneToOne
    private User user;
    public Customer(User user,
                    String address, String phone) {
        this.user = user;
        this.address = address;
        this.phone = phone;

    }
}
