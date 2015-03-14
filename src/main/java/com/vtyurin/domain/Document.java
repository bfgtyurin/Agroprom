package com.vtyurin.domain;

import javax.persistence.Entity;
import java.util.Objects;

@Entity
public class Document extends BaseEntity {

    private String name;
    private String link;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Document document = (Document) o;
        return Objects.equals(name, document.name) &&
                Objects.equals(link, document.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, link);
    }

    @Override
    public String toString() {
        return "Document{" +
                "name='" + name + '\'' +
                ", link='" + link + '\'' +
                "} " + super.toString();
    }
}
