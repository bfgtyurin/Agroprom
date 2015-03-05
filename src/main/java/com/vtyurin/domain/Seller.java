package com.vtyurin.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Seller {

    private Long id;

    private String name;
    private String address;
    private String email;
    private Timestamp dateAdded;

    public Seller() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "DATE_ADDED")
    public Timestamp getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Timestamp dateAdded) {
        this.dateAdded = dateAdded;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seller seller = (Seller) o;
        return Objects.equals(name, seller.name) &&
                Objects.equals(address, seller.address) &&
                Objects.equals(email, seller.email) &&
                Objects.equals(dateAdded, seller.dateAdded);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, address, email, dateAdded);
    }
}
