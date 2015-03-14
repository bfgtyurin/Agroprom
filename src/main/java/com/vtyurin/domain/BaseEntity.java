package com.vtyurin.domain;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "creation_time", nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime creationTime;

    @Column(name = "modification_time")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime modificationTime;

    @Version
    private long version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(DateTime created) {
        this.creationTime = created;
    }

    public DateTime getModificationTime() {
        return modificationTime;
    }

    public void setModificationTime(DateTime updated) {
        this.modificationTime = updated;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    @PrePersist
    protected void prePersist() {
        DateTime now = DateTime.now();
        this.setCreationTime(now);
        this.setModificationTime(now);
    }

    @PreUpdate
    protected void beforeUpdate() {
        this.setModificationTime(DateTime.now());
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "id=" + id +
                ", creationTime=" + creationTime +
                ", modificationTime=" + modificationTime +
                ", version=" + version +
                '}';
    }
}
