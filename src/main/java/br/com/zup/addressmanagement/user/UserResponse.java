package br.com.zup.addressmanagement.user;

import br.com.zup.addressmanagement.address.AddressResponse;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

public class UserResponse {
    private String name;
    private String email;
    private String cpf;

    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate dob;

    private Set<AddressResponse> addresses;

    @Deprecated
    public UserResponse() {
    }

    public UserResponse(String name, String email, String cpf, LocalDate dob, Set<AddressResponse> addresses) {
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.dob = dob;
        this.addresses = addresses;
    }

    public UserResponse(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.cpf = user.getCpf();
        this.dob = user.getDob();
        this.addresses = user.getAddresses()
                .stream()
                .map(AddressResponse::new)
                .collect(Collectors.toSet());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Set<AddressResponse> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<AddressResponse> addresses) {
        this.addresses = addresses;
    }
}
