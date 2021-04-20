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

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String cpf;

    @Column(nullable = false)
    private LocalDate dob;

    @OneToMany(cascade=CascadeType.MERGE)
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

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getCpf() {
        return cpf;
    }

    public LocalDate getDob() {
        return dob;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void addAddress(Address address) {
        this.addresses.add(address);
    }
}
