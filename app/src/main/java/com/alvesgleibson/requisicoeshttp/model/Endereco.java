package com.alvesgleibson.requisicoeshttp.model;

public class Endereco {

    private String logradouro,cep, bairro, uf, localidade;

    public Endereco(String rua, String cep, String bairro, String uf, String cidade) {
        this.logradouro = rua;
        this.cep = cep;
        this.bairro = bairro;
        this.uf = uf;
        this.localidade = cidade;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    @Override
    public String toString() {
        return "Rua= '" + logradouro + '\'' +
                "\nCEP= '" + cep + '\'' +
                "\nBairro= '" + bairro + '\'' +
                "\nEstado= '" + uf + '\'' +
                "\nCidade= '" + localidade + '\'';
    }
}
