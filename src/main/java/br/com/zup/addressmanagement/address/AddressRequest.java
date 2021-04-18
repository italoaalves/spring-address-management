package br.com.zup.addressmanagement.address;

import br.com.zup.addressmanagement.user.User;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

public class AddressRequest {
    @NotBlank
    private String street;

    @NotBlank
    private int number;

    @NotBlank
    private String complement;

    @NotBlank
    private String district;

    @NotBlank
    private String city;

    @NotBlank
    private String state;

    @NotBlank
    private String cep;

    public AddressRequest(String street, int number, String complement, String district, String city, String state, String cep, User user) {
        this.street = street;
        this.number = number;
        this.complement = complement;
        this.district = district;
        this.city = city;
        this.state = state;
        this.cep = cep;
    }
    public Address toAddress() {
        return new Address(this.street, this.number, this.complement, this.district, this.city, this.state, this.cep);
    }


}
