package br.com.zup.addressmanagement.address;


public class AddressResponse {
    private String street;
    private int number;
    private String complement;
    private String district;
    private String city;
    private String state;
    private String cep;

    @Deprecated
    public AddressResponse() {
    }

    public AddressResponse(Address address) {
        this.street = address.getStreet();
        this.number = address.getNumber();
        this.complement = address.getComplement();
        this.district = address.getDistrict();
        this.city = address.getCity();
        this.state = address.getState();
        this.cep = address.getCep();
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
}
