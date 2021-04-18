package br.com.zup.addressmanagement.user;

import br.com.zup.addressmanagement.address.Address;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "table_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String cpf;

    @Column(nullable = false)
    private LocalDate dob;

    @OneToMany()
    private Set<Address> addresses;

    @Deprecated
    public User() {
    }

    public User(String name, String email, String cpf, LocalDate dob) {
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.dob = dob;
    }

    public Long getId() {
        return id;
    }

    public void addAddress(Address address) {
        this.addresses.add(address);
    }
}
