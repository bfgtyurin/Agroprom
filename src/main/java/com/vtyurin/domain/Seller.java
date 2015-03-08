package com.vtyurin.domain;

import javax.persistence.Entity;
import java.util.Objects;

@Entity
public class Seller extends BaseEntity {

    private String name;
    private String address;
    private String email;

    public Seller() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seller seller = (Seller) o;
        return Objects.equals(name, seller.name) &&
                Objects.equals(address, seller.address) &&
                Objects.equals(email, seller.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, address, email);
    }

    @Override
    public String toString() {
        return super.toString() +
                "Seller {" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                "} ";
    }
}
