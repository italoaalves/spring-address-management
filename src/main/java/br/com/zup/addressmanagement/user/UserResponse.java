package br.com.zup.addressmanagement.user;

import br.com.zup.addressmanagement.address.Address;
import br.com.zup.addressmanagement.address.AddressResponse;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserResponse {
    private String name;
    private String email;
    private String cpf;
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

        Set<Address> addresses = user.getAddresses();
        if(addresses != null) {
            this.addresses = addresses.stream()
                    .map(AddressResponse::new)
                    .collect(Collectors.toSet());
        } else {
            this.addresses =  Collections.emptySet();
        }
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

    public Set<AddressResponse> getAddresses() {
        return addresses;
    }
}
