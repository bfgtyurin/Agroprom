package com.vtyurin.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class Product {

    private Long id;
    private String label;
    private String form;
    private String pack;
    private BigDecimal price;
    private Date dateAdded;
//    private Set<ProductManufacturerRelationship> relationships = new HashSet<>();

    public Product() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public String getPack() {
        return pack;
    }

    public void setPack(String pack) {
        this.pack = pack;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Column(name = "DATE_ADDED")
    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

//    @OneToMany(mappedBy = "", cascade = CascadeType.ALL)
//    public Set<ProductManufacturerRelationship> getRelationships() {
//        return relationships;
//    }
//
//    public void setRelationships(Set<ProductManufacturerRelationship> relationships) {
//        this.relationships = relationships;
//    }
}
