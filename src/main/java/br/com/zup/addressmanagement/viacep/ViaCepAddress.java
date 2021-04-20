package br.com.zup.addressmanagement.viacep;

public class ViaCepAddress {
    private String cep;
    private String bairro;
    private String localidade;
    private String uf;

    @Deprecated
    public ViaCepAddress() {
    }

    public ViaCepAddress(String cep, String bairro, String localidade, String uf) {
        this.cep = cep;
        this.bairro = bairro;
        this.localidade = localidade;
        this.uf = uf;
    }

    public String getCep() {
        return cep;
    }

    public String getBairro() {
        return bairro;
    }

    public String getLocalidade() {
        return localidade;
    }

    public String getUf() {
        return uf;
    }
}
