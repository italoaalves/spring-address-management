package br.com.zup.addressmanagement.address;


import br.com.zup.addressmanagement.viacep.ViaCepAddress;

import javax.validation.constraints.*;

public class AddressRequest {
    private String street;

    @NotNull
    @Min(1)
    private int number;

    @NotBlank
    private String complement;

    private String district;
    private String city;
    private String state;

    @NotBlank
    @Size(min = 9, max = 9)
    private String cep;

    public AddressRequest(String street, int number, String complement, String cep) {
        this.street = street;
        this.number = number;
        this.complement = complement;
        this.cep = cep;
    }

    public Address toAddress() {
        return new Address(this.street,
                this.number,
                this.complement,
                this.district,
                this.city,
                this.state,
                this.cep);
    }

    public String getStreet() {
        return street;
    }

    public int getNumber() {
        return number;
    }

    public String getComplement() {
        return complement;
    }

    public String getDistrict() {
        return district;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCep() {
        return cep;
    }

    public void autoComplete(ViaCepAddress viaCepAddress) {
        this.city = viaCepAddress.getLocalidade();
        this.district = viaCepAddress.getBairro();
        this.state = viaCepAddress.getUf();
    }
}
