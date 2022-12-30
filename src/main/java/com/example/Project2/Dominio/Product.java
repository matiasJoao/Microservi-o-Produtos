package com.example.Project2.Dominio;

public class Product {
    private Long Codigo;
    private String descripton;
    private Float price;
    private Integer amount;

    private String type;
    public Product(){

    }
    public Product(Long codigo, String descripton,Float price,Integer amount, String type) {
        this.Codigo = codigo;
        this.descripton = descripton;
        this.price = price;
        this.amount = amount;
        this.type = type;

    }



    public Long getCodigo() {
        return Codigo;
    }

    public void setCodigo(Long codigo) {
        Codigo = codigo;
    }

  /*  public String getDescripton() {
        return descripton;
    }

    public void setDescripton(String descripton) {
        this.descripton = descripton;
    } */

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
