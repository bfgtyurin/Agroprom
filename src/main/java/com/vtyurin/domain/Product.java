package com.vtyurin.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.pegdown.Parser;
import org.pegdown.PegDownProcessor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Product extends BaseEntity {

    private String name;
    private BigDecimal price;

    @Column(name = "description_markdown")
    private String descriptionMarkdown;

    @Column(name = "description_html")
    private String descriptionHtml;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Manufacturer manufacturer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Category category;

    public Product() {
    }

    public Product(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDescriptionMarkdown() {
        return descriptionMarkdown;
    }

    public void setDescriptionMarkdown(String description) {
        this.descriptionMarkdown = description;
    }

    public String getDescriptionHtml() {
        return descriptionHtml;
    }

    public void setDescriptionHtml(String descriptionHtml) {
        this.descriptionHtml = descriptionHtml;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    @PrePersist
    @PreUpdate
    private void parseMarkdown() {
        String descriptionMarkdown = this.descriptionMarkdown;
        if (descriptionMarkdown != null) {
            PegDownProcessor markdownToHtmlParser = new PegDownProcessor(Parser.SUPPRESS_ALL_HTML);
            this.descriptionHtml = markdownToHtmlParser.markdownToHtml(descriptionMarkdown);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name) &&
                Objects.equals(price, product.price) &&
                Objects.equals(descriptionMarkdown, product.descriptionMarkdown);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, descriptionMarkdown);
    }

    @Override
    public String toString() {
        return "Product{" +
                "category=" + category +
                ", price=" + price +
                ", name='" + name + '\'' +
                ", descriptionMarkdown='" + descriptionMarkdown + '\'' +
                ", descriptionHtml='" + descriptionHtml + '\'' +
                ", descriptionHtml='" + manufacturer + '\'' +
                "} " + super.toString();
    }
}
